package alephinfinity1.forgeblock.misc;

import java.text.DecimalFormat;
import java.util.Random;

import alephinfinity1.forgeblock.attribute.FBAttributes;
import alephinfinity1.forgeblock.init.ModEffects;
import alephinfinity1.forgeblock.init.ModEnchantments;
import alephinfinity1.forgeblock.misc.skills.ISkills;
import alephinfinity1.forgeblock.misc.skills.SkillType;
import alephinfinity1.forgeblock.misc.skills.SkillsProvider;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class DamageHandler {
	
	//Mod-defined DamageSource s
	public static DamageSource getBlazeArmorDamageSource(LivingEntity source) {
		return new EntityDamageSource("blaze_armor", source);
	}

	@SubscribeEvent
	public static void onLivingAttack(LivingHurtEvent event) {
		//Pre: get damager and victim.
		
		LivingEntity damager = (LivingEntity) event.getSource().getTrueSource();
		LivingEntity victim = event.getEntityLiving();
		
		Random rng = new Random();
		
		if(!(event.getSource().getTrueSource() instanceof LivingEntity)) { //If attack is environmental then only apply defense bonus
			double damageMultiplier = 1.0D;
			if(event.getSource().equals(DamageSource.OUT_OF_WORLD)){
				event.setAmount(Float.MAX_VALUE);
				return;
			} else if(event.getSource().equals(DamageSource.IN_FIRE) || event.getSource().equals(DamageSource.ON_FIRE)) {
				event.setAmount(5);
			} else if(event.getSource().equals(DamageSource.LAVA)) {
				event.setAmount(20);
			} else if(event.getSource().equals(DamageSource.STARVE)) { //Disable all starve damage
				event.setAmount(0);
			} else if(event.getSource().equals(DamageSource.MAGIC)) { //If magic, do not multiply by 5
			} else {
				event.setAmount(event.getAmount() * 5);
			}
			
			//If the damage is affected by armor, also apply normal defense bonus
			if((!event.getSource().isUnblockable() && !event.getSource().isDamageAbsolute()) || event.getSource().isMagicDamage()) { //Magic damage should be affected by defense
				//Check for dodge
				double dodge = 0.0D;
				if(victim.getAttribute(FBAttributes.DODGE) != null) {
					dodge = victim.getAttribute(FBAttributes.DODGE).getValue();
				}
				if(rng.nextDouble() * 100 < dodge) {
					if(victim instanceof PlayerEntity) {
						notifyDodge((PlayerEntity) victim, null, true, event.getAmount());
					}
					event.setCanceled(true);
				}
				
				double defense = event.getEntityLiving().getAttribute(FBAttributes.DEFENSE).getValue();
				damageMultiplier = 100.0D / (defense + 100.0D);
				
				
			}
			
			double trueDefense = event.getEntityLiving().getAttribute(FBAttributes.TRUE_DEFENSE).getValue();
			damageMultiplier *= 100.0D / (trueDefense + 100.0D);
			event.setAmount((float) (event.getAmount() * damageMultiplier));
			//victim.hurtResistantTime = 0; //If environmental damage, no damage tick should be consumed.
			addDamageDisplay(victim.world, victim.getPosX(), victim.getPosY(), victim.getPosZ(), event.getAmount() * damageMultiplier, event.getSource());
			return;
		}
		
		
		double result = event.getAmount();
		
		//For physical damage only:
		if(event.getSource().damageType.equals("player") || event.getSource().damageType.equals("mob") || event.getSource().damageType.equals("arrow")) {
			//Prevents crashes
			if(damager == null) return;
			if(damager.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) == null) return;
			if(result == 1.0D) { //Sweep attack; normal attacks can never deal 1 damage.
				event.setCanceled(true);
				return;
			}
			
			//Step 0: check for dodging effect
			double dodge = 0.0D;
			if(victim.getAttribute(FBAttributes.DODGE) != null) {
				dodge = victim.getAttribute(FBAttributes.DODGE).getValue();
			}
			if(rng.nextDouble() * 100 < dodge) {
				if(victim instanceof PlayerEntity) {
					notifyDodge((PlayerEntity) victim, damager, true, event.getAmount());
				} else if(damager instanceof PlayerEntity) {
					notifyDodge((PlayerEntity) damager, victim, false, event.getAmount());
				}
				event.setCanceled(true);
			}
			
			//Step 1: calculate base damage from attackDamage, strength, critChance, and critDamage.
			double damage = damager.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue();
			//if(damager instanceof PlayerEntity) damage += 4; //A wonky workaround to setting the player's attack damage to 5. Might be replaced in the future.
			double strength = damager.getAttribute(FBAttributes.STRENGTH).getValue();
			result = (damage + Math.floor(strength / 5.0D)) * Math.max(1.0D + strength / 100.0D, 0.0D); //Do not allow negative multiplier
			
			//Step 2: calculate enchantment multipliers TODO
			ItemStack weapon = damager.getHeldItemMainhand();
			double enchMultiplier = 0.0D;
			
			//a: sharpness
			enchMultiplier += 0.05 * EnchantmentHelper.getEnchantmentLevel(Enchantments.SHARPNESS, weapon);
			
			//b: execute
			int executeLevel = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.EXECUTE.get(), weapon);
			float victimHealth = victim.getHealth();
			float victimMaxHealth = victim.getMaxHealth();
			enchMultiplier += 0.2 * executeLevel * (1 - victimHealth / victimMaxHealth);
			
			//c: first strike
			if(victimHealth == victimMaxHealth) enchMultiplier += 0.2 * EnchantmentHelper.getEnchantmentLevel(ModEnchantments.FIRST_STRIKE.get(), weapon);
			
			//d: giant killer
			float damagerHealth = damager.getHealth();
			if(victimHealth > damagerHealth) {
				double healthDiffPercentage = (victimHealth - damagerHealth) / damagerHealth * 100.0D;
				int giantKillerLevel = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.GIANT_KILLER.get(), weapon);
				double giantKillerMultiplier = healthDiffPercentage * giantKillerLevel * 0.001;
				if(giantKillerMultiplier > 0.25) giantKillerMultiplier = 0.25;
				enchMultiplier += giantKillerMultiplier;
			}
			
			
			//Step 3: calculate other multipliers (skill, armor effects, etc) TODO
			double skillMultiplier = 0.0D;
			if(damager instanceof PlayerEntity) {
				PlayerEntity player = (PlayerEntity) damager;
				ISkills skills = player.getCapability(SkillsProvider.SKILLS_CAPABILITY).orElseThrow(NullPointerException::new);
				skillMultiplier = 0.04D * skills.getLevel(SkillType.COMBAT);
			}
			
			result *= (1.0D + enchMultiplier + skillMultiplier);
			
			//Step 4: check for critical hit
			boolean isCrit = (new Random().nextDouble() * 100.0D) < damager.getAttribute(FBAttributes.CRIT_CHANCE).getValue();
			double critDamage = damager.getAttribute(FBAttributes.CRIT_DAMAGE).getValue();
			if(isCrit) result *= (1.0D + critDamage / 100.0D);
			
			//Step 4b: activate life steal enchantment
			int lifeSteal = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.LIFE_STEAL.get(), damager.getHeldItemMainhand());
			damager.heal((float) (result * lifeSteal * 0.001));
			
			//Step 5: calculate defense reduction on victim.
			double defense = victim.getAttribute(FBAttributes.DEFENSE).getValue();
			double damageMultiplier = 100.0D / (defense + 100.0D);
			result *= damageMultiplier;
			
			//Step 6: resolve additional effects from enchants
			int venomousLevel = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.VENOMOUS.get(), weapon);
			if(venomousLevel != 0) {
				EffectInstance instance = new EffectInstance(ModEffects.VENOMOUS, 85, venomousLevel - 1, false, false);
				victim.addPotionEffect(instance);
			}
			
			//Step 7: display damage
			if(damager instanceof PlayerEntity) {
				//PlayerEntity player = (PlayerEntity) damager;
				//For debug purposes only
				addDamageDisplay(victim.world, victim.getPosX(), victim.getPosY(), victim.getPosZ(), result, isCrit);
			}
			
			if(victim instanceof PlayerEntity) {
				//PlayerEntity player = (PlayerEntity) victim;
				//For debug purposes only
				addDamageDisplay(victim.world, victim.getPosX(), victim.getPosY(), victim.getPosZ(), result, isCrit);
			}
		} else {
			addDamageDisplay(victim.world, victim.getPosX(), victim.getPosY(), victim.getPosZ(), event.getAmount(), event.getSource());
		}
		
		//Regardless of the type of damage:
		double trueDefense = victim.getAttribute(FBAttributes.TRUE_DEFENSE).getValue();
		double damageMultiplier = 100.0D / (trueDefense + 100.0D);
		result *= damageMultiplier;
		
		//Post: sets damage.
		event.setAmount((float) result);
		
		/**
		 * Sets the victim's hurt resistance time based on the damager's attack speed attribute.
		 * See line 927 of LivingEntity
		 * @see LivingEntity#attackEntityFrom(DamageSource source, float amount)
		 */
		victim.hurtResistantTime = Long.valueOf(Math.round((10.0 / (1 + 0.01 * damager.getAttribute(FBAttributes.BONUS_ATTACK_SPEED).getValue())) + 10)).intValue();
	}
	
	public static void addDamageDisplay(World world, double posX, double posY, double posZ, double amount, boolean isCrit) {
		String num = new DecimalFormat(",###").format(amount).replaceAll("\u00A0", ",");
		ITextComponent comp;
		if(isCrit) {
			comp = new StringTextComponent(TextFormatting.RED.toString() + num);
		} else {
			comp = new StringTextComponent(TextFormatting.GRAY.toString() + num);
		}
		
	}
	
	public static void addDamageDisplay(World world, double posX, double posY, double posZ, double amount, DamageSource source) {
		String num = new DecimalFormat(",###").format(amount).replaceAll("\u00A0", ",");
		ITextComponent comp;
		if(source.equals(DamageSource.MAGIC)) {
			comp = new StringTextComponent(TextFormatting.AQUA.toString() + num);
		} else if(source.equals(DamageSource.LAVA)) {
			comp = new StringTextComponent(TextFormatting.RED.toString() + num);
		} else if(source.equals(DamageSource.ON_FIRE) || source.equals(DamageSource.IN_FIRE)) {
			comp = new StringTextComponent(TextFormatting.GOLD.toString() + num);
		} else if(source.equals(DamageSource.OUT_OF_WORLD)) {
			comp = new StringTextComponent(TextFormatting.DARK_PURPLE.toString() + num);
		} else if(source.equals(DamageSource.DROWN)){
			comp = new StringTextComponent(TextFormatting.DARK_AQUA.toString() + num);
		} else {
			comp = new StringTextComponent(TextFormatting.GRAY.toString() + num);
		}
		
	}
	
	public static void notifyDodge(PlayerEntity player, Entity other, boolean isVictim, double amount) {
		if(other == null) {
			player.sendMessage(new TranslationTextComponent("text.forgeblock.dodge.environmental", new DecimalFormat(",###.#").format(amount).replaceAll("\u00A0", ",")));
		} else {
			if(isVictim) {
				player.sendMessage(new TranslationTextComponent("text.forgeblock.dodge.victim", new DecimalFormat(",###.#").format(amount).replaceAll("\u00A0", ","), other.getDisplayName().getString()));
			} else {
				player.sendMessage(new TranslationTextComponent("text.forgeblock.dodge.attackMiss", new DecimalFormat(",###.#").format(amount).replaceAll("\u00A0", ","), other.getDisplayName().getString()));
			}
		}
	}

}
