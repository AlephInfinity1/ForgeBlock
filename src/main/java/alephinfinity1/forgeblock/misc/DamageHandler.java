package alephinfinity1.forgeblock.misc;

import java.util.Random;

import alephinfinity1.forgeblock.attribute.FBAttributes;
import alephinfinity1.forgeblock.init.ModEffects;
import alephinfinity1.forgeblock.init.ModEnchantments;
import alephinfinity1.forgeblock.misc.TextFormatHelper.SuffixType;
import alephinfinity1.forgeblock.misc.skills.ISkills;
import alephinfinity1.forgeblock.misc.skills.SkillType;
import alephinfinity1.forgeblock.misc.skills.SkillsProvider;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class DamageHandler {

	@SubscribeEvent
	public static void onLivingAttack(LivingHurtEvent event) {
		//Pre: get damager and victim.
		
		LivingEntity damager = (LivingEntity) event.getSource().getTrueSource();
		LivingEntity victim = event.getEntityLiving();
		
		if(!(event.getSource().getTrueSource() instanceof LivingEntity)) { //If attack is environmental then only apply defense bonus
			double damageMultiplier = 1.0D;
			//If the damage is affected by armor, also apply normal defense bonus
			if(!(event.getSource().isUnblockable()) || event.getSource().isMagicDamage()) { //Magic damage should be affected by defense
				double defense = event.getEntityLiving().getAttribute(FBAttributes.DEFENSE).getValue();
				damageMultiplier = 100.0D / (defense + 100.0D);
			} else if (event.getSource().equals(DamageSource.OUT_OF_WORLD)){
				event.setAmount(Float.MAX_VALUE);
				return;
			}
			
			double trueDefense = event.getEntityLiving().getAttribute(FBAttributes.TRUE_DEFENSE).getValue();
			damageMultiplier *= 100.0D / (trueDefense + 100.0D);
			event.setAmount((float) (event.getAmount() * damageMultiplier));
			victim.hurtResistantTime = 0; //If environmental damage, no damage tick should be consumed.
			return;
		}
		
		
		double result = event.getAmount();
		
		//For physical damage only:
		if(event.getSource().damageType.equals("player") || event.getSource().damageType.equals("mob") || event.getSource().damageType.equals("arrow")) {
			//Prevents crashes
			if(damager == null) return;
			if(damager.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) == null) return;
			
			//Step 1: calculate base damage from attackDamage, strength, critChance, and critDamage.
			double damage = damager.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue();
			//if(damager instanceof PlayerEntity) damage += 4; //A wonky workaround to setting the player's attack damage to 5. Might be replaced in the future.
			double strength = damager.getAttribute(FBAttributes.STRENGTH).getValue();
			result = (damage + Math.floor(strength / 5.0D)) * (1.0D + strength / 100.0D);
			
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
				skillMultiplier = 1.0D + 0.04D * skills.getLevel(SkillType.COMBAT);
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
			
			//Step 7: debug only. Temporary
			if(damager instanceof PlayerEntity) {
				PlayerEntity player = (PlayerEntity) damager;
				//For debug purposes only
				if(!isCrit) {
					player.sendMessage(new StringTextComponent("Dealt " + TextFormatHelper.formatLargeNumberWithSuffix(SuffixType.SINGLE_LETTER, result) + " damage!"));
				} else {
					player.sendMessage(new StringTextComponent("Dealt " + TextFormatHelper.formatLargeNumberWithSuffix(SuffixType.SINGLE_LETTER, result) + " damage! (Crit!)"));
				}
			}
			
			if(victim instanceof PlayerEntity) {
				PlayerEntity player = (PlayerEntity) victim;
				//For debug purposes only
				if(!isCrit) {
					player.sendMessage(new StringTextComponent("Taken " + TextFormatHelper.formatLargeNumberWithSuffix(SuffixType.SINGLE_LETTER, result) + " damage!"));
					player.sendMessage(new StringTextComponent("Defense: " + Double.toString(defense)));
				} else {
					player.sendMessage(new StringTextComponent("Taken " + TextFormatHelper.formatLargeNumberWithSuffix(SuffixType.SINGLE_LETTER, result) + " damage! (Crit!)"));
					player.sendMessage(new StringTextComponent("Defense: " + Double.toString(defense)));
				}
			}
		}
		
		//Regardless of the type of damage:
		double trueDefense = victim.getAttribute(FBAttributes.TRUE_DEFENSE).getValue();
		double damageMultiplier = 100.0D / (trueDefense + 100.0D);
		result *= damageMultiplier;
		
		//Rounding
		result = Math.round(result);
		
		//Post: sets damage.
		event.setAmount((float) result);
		victim.hurtResistantTime = (int) (20 / (1 + 0.01 * damager.getAttribute(FBAttributes.BONUS_ATTACK_SPEED).getValue()));
	}

}
