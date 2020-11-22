package alephinfinity1.forgeblock.misc;

import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Objects;
import java.util.Random;

//import org.apache.commons.lang3.tuple.Triple;

import alephinfinity1.forgeblock.attribute.FBAttributes;
import alephinfinity1.forgeblock.client.particles.StringParticleData;
import alephinfinity1.forgeblock.client.particles.StringParticleData.Style;
import alephinfinity1.forgeblock.init.ModEffects;
import alephinfinity1.forgeblock.init.ModEnchantments;
import alephinfinity1.forgeblock.init.ModParticles;
import alephinfinity1.forgeblock.item.CrownOfGreedItem;
import alephinfinity1.forgeblock.item.EndSwordItem;
import alephinfinity1.forgeblock.item.SpiderSwordItem;
import alephinfinity1.forgeblock.item.UndeadSwordItem;
import alephinfinity1.forgeblock.misc.coins.CoinsProvider;
import alephinfinity1.forgeblock.misc.coins.ICoins;
import alephinfinity1.forgeblock.misc.skills.SkillsHelper;
import alephinfinity1.forgeblock.network.DamageParticlePacket;
import alephinfinity1.forgeblock.network.FBPacketHandler;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.EnderDragonPartEntity;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.entity.monster.EndermiteEntity;
import net.minecraft.entity.monster.MagmaCubeEntity;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.network.play.server.SEntityVelocityPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stats;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.PacketDistributor.TargetPoint;

@Mod.EventBusSubscriber
public class DamageHandler {
	
	//Mod-defined DamageSource s
	public static DamageSource getBlazeArmorDamageSource(LivingEntity source) {
		return new EntityDamageSource("blaze_armor", source);
	}
	
	public static DamageSource getFerocitySource(LivingEntity source, int stacking) {
		return new FerocityDamageSource("ferocity", source, stacking);
	}
	
	

	@SubscribeEvent
	public static void onLivingAttack(LivingHurtEvent event) {
		//Pre: get damager and victim.
		
		Entity trueSource = event.getSource().getTrueSource();
		LivingEntity damager = trueSource instanceof LivingEntity ? (LivingEntity) trueSource : null;
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
			FBPacketHandler.INSTANCE.send(PacketDistributor.NEAR.with(TargetPoint.p(victim.getPosX(), victim.getPosY(), victim.getPosZ(), 64.0D, victim.dimension)), new DamageParticlePacket(event.getAmount(), event.getSource().damageType, victim.getPositionVec().add(0, victim.getHeight() / 2.0, 0)));
			if(Float.isNaN(event.getAmount())) {
				event.setAmount(0);
			}
			return;
		}
		
		
		double result = event.getAmount();
		
		//For physical damage only:
		if(event.getSource().damageType.equals("player") || event.getSource().damageType.equals("mob") || event.getSource().damageType.equals("arrow") || event.getSource().damageType.equals("ferocity") || event.getSource().damageType.equals("greedy")) {
			//Prevents crashes
			if(damager == null) return;
			if(damager.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) == null) return;
			
			/* Since sweep attack is patched, the below code is no longer needed.
			if(result == 1.0D) { //Sweep attack; normal attacks can never deal 1 damage.
				event.setCanceled(true);
				return;
			}
			*/
			
			//Step 0: check for dodging effect
			double dodge = 0.0D;
			if(victim.getAttribute(FBAttributes.DODGE) != null) {
				dodge = victim.getAttribute(FBAttributes.DODGE).getValue();
			}
			if(RNGHelper.randomChance(dodge / 100.0D, new Random())) {
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
			
			//TODO Step 2: calculate enchantment multipliers 
			ItemStack weapon = damager.getHeldItemMainhand();
			double enchMultiplier = 0.0D;
			
			//a: sharpness & variants
			enchMultiplier += 0.05 * EnchantmentHelper.getEnchantmentLevel(Enchantments.SHARPNESS, weapon);
			if(victim.getCreatureAttribute().equals(CreatureAttribute.ARTHROPOD)) {
				enchMultiplier += 0.08 * EnchantmentHelper.getEnchantmentLevel(Enchantments.BANE_OF_ARTHROPODS, weapon);
			} else if(victim.getCreatureAttribute().equals(CreatureAttribute.UNDEAD)) {
				enchMultiplier += 0.08 * EnchantmentHelper.getEnchantmentLevel(Enchantments.SMITE, weapon);
			} else if(victim.getCreatureAttribute().equals(FBCreatureAttributes.CUBE) || victim instanceof CreeperEntity || victim instanceof SlimeEntity || victim instanceof MagmaCubeEntity) {
				enchMultiplier += 0.1 * EnchantmentHelper.getEnchantmentLevel(ModEnchantments.CUBISM.get(), weapon);
			} else if(victim.getCreatureAttribute().equals(FBCreatureAttributes.ENDER) || victim instanceof EndermanEntity || victim instanceof EndermiteEntity) {
				enchMultiplier += 0.12 * EnchantmentHelper.getEnchantmentLevel(ModEnchantments.ENDER_SLAYER.get(), weapon);
			}
			
			//b: execute
			int executeLevel = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.EXECUTE.get(), weapon);
			float victimHealth = victim.getHealth();
			float victimMaxHealth = victim.getMaxHealth();
			enchMultiplier += 0.2 * executeLevel * (1 - victimHealth / victimMaxHealth);
			
			//c: first strike
			if(victim.getCombatTracker().getBestAttacker() == null)
				enchMultiplier += 0.2 * EnchantmentHelper.getEnchantmentLevel(ModEnchantments.FIRST_STRIKE.get(), weapon);
			
			//d: giant killer
			float damagerHealth = damager.getHealth();
			if(victimHealth > damagerHealth) {
				double healthDiffPercentage = (victimHealth - damagerHealth) / damagerHealth * 100.0D;
				int giantKillerLevel = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.GIANT_KILLER.get(), weapon);
				double giantKillerMultiplier = healthDiffPercentage * giantKillerLevel * 0.001;
				if(giantKillerMultiplier > 0.25) giantKillerMultiplier = 0.25;
				enchMultiplier += giantKillerMultiplier;
			}
			
			
			//TODO Step 3: calculate other 'pre' multipliers (skill, etc) 
			
			//Combat skill
			double skillMultiplier = 0.0D;
			if(damager instanceof PlayerEntity) {
				PlayerEntity player = (PlayerEntity) damager;
				skillMultiplier = 0.04D * SkillsHelper.getCombatLevelOrElse(player, 0);
			}
			
			result *= (1.0D + enchMultiplier + skillMultiplier);
			
			//Mob type-specific weapon modifier
			if(weapon.getItem() instanceof UndeadSwordItem && victim.getCreatureAttribute().equals(CreatureAttribute.UNDEAD)) result *= 2;
			if(weapon.getItem() instanceof SpiderSwordItem && victim.getCreatureAttribute().equals(CreatureAttribute.ARTHROPOD)) result *= 2;
			if(weapon.getItem() instanceof EndSwordItem && (victim instanceof EndermanEntity || victim instanceof EndermiteEntity
					|| victim instanceof EnderDragonEntity || victim.getCreatureAttribute().equals(FBCreatureAttributes.ENDER))) result *= 2;
			
			//Step 4: check for critical hit
			boolean isCrit = RNGHelper.randomChance(damager.getAttribute(FBAttributes.CRIT_CHANCE).getValue() / 100.0D, new Random());
			double critDamage = damager.getAttribute(FBAttributes.CRIT_DAMAGE).getValue();
			if(isCrit) result *= (1.0D + critDamage / 100.0D);
			
			//Step 4b: activate life steal enchantment (before defence calculation)
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
			
			int vampirismLevel = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.VAMPIRISM.get(), weapon);
			if(vampirismLevel != 0) {
				float missingHealth = damager.getMaxHealth() - damager.getHealth();
				damager.heal(missingHealth * vampirismLevel * 0.01f);
			}
			
			//TODO Step 7: calculate all 'post' multipliers (armour, absolute effects).
			
			//Crown of Greed
			ItemStack helmet = damager.getItemStackFromSlot(EquipmentSlotType.HEAD);
			if(helmet.getItem() instanceof CrownOfGreedItem) {
				ICoins coinsCap = damager.getCapability(CoinsProvider.COINS_CAPABILITY).orElse(null);
				if(Objects.isNull(coinsCap)) return;
				Collection<AttributeModifier> attackModifiers = weapon.getAttributeModifiers(EquipmentSlotType.MAINHAND).get(SharedMonsterAttributes.ATTACK_DAMAGE.getName());
				double weaponDamage = 0.0D;
				for(AttributeModifier modifier : attackModifiers) {
					if(modifier.getOperation().equals(Operation.ADDITION)) {
						weaponDamage += modifier.getAmount();
					}
				}
				if(coinsCap.consume(weaponDamage)) {
					result *= 1.25;
				}
			}
			
			//Step 8: display damage
			if(damager instanceof PlayerEntity) {
				//PlayerEntity player = (PlayerEntity) damager;
				//For debug purposes only
				if(isCrit) {
					FBPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) damager), new DamageParticlePacket(result, "crit", victim.getPositionVec().add(0, victim.getHeight() / 2.0, 0)));
					((PlayerEntity) damager).onCriticalHit(victim); //Display crit particles
				}
				else
					FBPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) damager), new DamageParticlePacket(result, "normal", victim.getPositionVec().add(0, victim.getHeight() / 2.0, 0)));
			}
			
			if(victim instanceof PlayerEntity) {
				//PlayerEntity player = (PlayerEntity) victim;
				//For debug purposes only
				if(isCrit)
					FBPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) victim), new DamageParticlePacket(result, "crit", victim.getPositionVec().add(0, victim.getHeight() / 2.0, 0)));
				else
					FBPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) victim), new DamageParticlePacket(result, "normal", victim.getPositionVec().add(0, victim.getHeight() / 2.0, 0)));
			}
		} else {
			FBPacketHandler.INSTANCE.send(PacketDistributor.NEAR.with(TargetPoint.p(victim.getPosX(), victim.getPosY(), victim.getPosZ(), 64.0D, victim.dimension)), new DamageParticlePacket(result, event.getSource().damageType, victim.getPositionVec().add(0, victim.getHeight() / 2.0, 0)));
		}
		
		//Regardless of the type of damage:
		double trueDefense = victim.getAttribute(FBAttributes.TRUE_DEFENSE).getValue();
		double damageMultiplier = 100.0D / (trueDefense + 100.0D);
		result *= damageMultiplier;
		
		//Post: sets damage.
		//if(result <= DAMAGE_INDICATOR_FIX_THRESHOLD) {
			event.setAmount((float) result);
			if(Float.isNaN(event.getAmount())) {
				event.setAmount(0);
			}
		/*} else {
			event.setAmount(0);
			TickHandler.damageIndicatorFix.add(Triple.of(victim, result, TickHandler.tickElapsed));
		}*/
		
		/**
		 * Sets the victim's hurt resistance time based on the damager's attack speed attribute.
		 * See line 927 of LivingEntity
		 * @see LivingEntity#attackEntityFrom(DamageSource source, float amount)
		 */
		victim.hurtResistantTime = (int) (Math.round(10.0 / (1 + 0.01 * damager.getAttribute(FBAttributes.BONUS_ATTACK_SPEED).getValue())) + 10);
		
		//Adds to player stats.
		if(damager instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) damager;
			player.addStat(Stats.DAMAGE_DEALT, (int) (result * 10.0D));
		}
		
		//Schedule another attack if ferocity, but with reduced ferocity.
		double ferocity = damager.getAttribute(FBAttributes.FEROCITY).getValue();
		int penalty = 0; //Recursive penalty if Damage is from ferocity already.
		if(event.getSource().damageType.equals("ferocity")) {
			penalty = ((FerocityDamageSource) event.getSource()).getStacking();
		}
		double f = (ferocity / 100.0D) - penalty; //Chance of applying another ferocity strike.
		if(RNGHelper.randomChance(f, new Random())) {
			victim.hurtResistantTime = 0;
			DamageSource ferocitySource = getFerocitySource(damager, penalty + 1);
			Tuple<LivingEntity, DamageSource> tuple = new Tuple<LivingEntity, DamageSource>(victim, ferocitySource);
			TickHandler.ferocitySchedule.put(tuple, TickHandler.ticksElapsed);
		}
	}
	
	public static void addDamageDisplay(World world, double posX, double posY, double posZ, double amount, Style style) {
		if(world.isRemote) {
			ClientWorld cw = ((ClientWorld) world);
			StringParticleData particle = new StringParticleData(ModParticles.NUMERIC_DAMAGE.get(), new DecimalFormat(",###").format(amount).replaceAll("\u00A0", ","), style);
			cw.addParticle(particle, posX, posY + MathHelper.nextDouble(new Random(), -0.25, 0.25), posZ, 
					MathHelper.nextDouble(new Random(), -1, 1), 
					MathHelper.nextDouble(new Random(), 0.5, 1.25), 
					MathHelper.nextDouble(new Random(), -1, 1));
		}
	}
	
	public static void addDamageDisplay(World world, double posX, double posY, double posZ, double amount, DamageSource source) {
		String num = new DecimalFormat(",###").format(amount).replaceAll("\u00A0", ",");
		@SuppressWarnings("unused")
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
	
	/*
	 * Disables sweep attack
	 * Code mostly copied from PlayerEntity, lines 1150–1299
	 */
	@SubscribeEvent
	@SuppressWarnings("unused")
	public static void onPlayerAttack(AttackEntityEvent event) {
		Entity targetEntity = event.getTarget();
		PlayerEntity player = event.getPlayer();
		if (targetEntity.canBeAttackedWithItem()) {
			if (!targetEntity.hitByEntity(player)) {
				float f = (float)player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue();
				float f1;
				if (targetEntity instanceof LivingEntity) {
					f1 = EnchantmentHelper.getModifierForCreature(player.getHeldItemMainhand(), ((LivingEntity)targetEntity).getCreatureAttribute());
				} else {
					f1 = EnchantmentHelper.getModifierForCreature(player.getHeldItemMainhand(), CreatureAttribute.UNDEFINED);
				}

				float f2 = player.getCooledAttackStrength(0.5F);
				f = f * (0.2F + f2 * f2 * 0.8F);
				f1 = f1 * f2;
				player.resetCooldown();
				if (f > 0.0F || f1 > 0.0F) {
					boolean flag = f2 > 0.9F;
					boolean flag1 = false;
					int i = 0;
					i = i + EnchantmentHelper.getKnockbackModifier(player);
					if (player.isSprinting() && flag) {
						player.world.playSound((PlayerEntity)null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_PLAYER_ATTACK_KNOCKBACK, player.getSoundCategory(), 1.0F, 1.0F);
						++i;
						flag1 = true;
					}

					boolean flag2 = flag && player.fallDistance > 0.0F && !player.onGround && !player.isOnLadder() && !player.isInWater() && !player.isPotionActive(Effects.BLINDNESS) && !player.isPassenger() && targetEntity instanceof LivingEntity;
					flag2 = flag2 && !player.isSprinting();
					net.minecraftforge.event.entity.player.CriticalHitEvent hitResult = net.minecraftforge.common.ForgeHooks.getCriticalHit(player, targetEntity, flag2, flag2 ? 1.5F : 1.0F);
					flag2 = hitResult != null;
					if (flag2) {
						f *= hitResult.getDamageModifier();
					}

					f = f + f1;
					boolean flag3 = false;
					double d0 = (double)(player.distanceWalkedModified - player.prevDistanceWalkedModified);
					if (flag && !flag2 && !flag1 && player.onGround && d0 < (double)player.getAIMoveSpeed()) {
						ItemStack itemstack = player.getHeldItem(Hand.MAIN_HAND);
						if (itemstack.getItem() instanceof SwordItem) {
							//flag3 = true;
							//Never sweep attack
						}
					}

					float f4 = 0.0F;
					boolean flag4 = false;
					int j = EnchantmentHelper.getFireAspectModifier(player);
					if (targetEntity instanceof LivingEntity) {
						f4 = ((LivingEntity)targetEntity).getHealth();
						if (j > 0 && !targetEntity.isBurning()) {
							flag4 = true;
							targetEntity.setFire(1);
						}
					}

					Vec3d vec3d = targetEntity.getMotion();
					boolean flag5 = targetEntity.attackEntityFrom(DamageSource.causePlayerDamage(player), f);
					if (flag5) {
						if (i > 0) {
							if (targetEntity instanceof LivingEntity) {
								((LivingEntity)targetEntity).knockBack(player, (float)i * 0.5F, (double)MathHelper.sin(player.rotationYaw * ((float)Math.PI / 180F)), (double)(-MathHelper.cos(player.rotationYaw * ((float)Math.PI / 180F))));
							} else {
								targetEntity.addVelocity((double)(-MathHelper.sin(player.rotationYaw * ((float)Math.PI / 180F)) * (float)i * 0.5F), 0.1D, (double)(MathHelper.cos(player.rotationYaw * ((float)Math.PI / 180F)) * (float)i * 0.5F));
							}

							player.setMotion(player.getMotion().mul(0.6D, 1.0D, 0.6D));
							player.setSprinting(false);
						}

						if (flag3) {
							float f3 = 1.0F + EnchantmentHelper.getSweepingDamageRatio(player) * f;

							for(LivingEntity livingentity : player.world.getEntitiesWithinAABB(LivingEntity.class, targetEntity.getBoundingBox().grow(1.0D, 0.25D, 1.0D))) {
								if (livingentity != player && livingentity != targetEntity && !player.isOnSameTeam(livingentity) && (!(livingentity instanceof ArmorStandEntity) || !((ArmorStandEntity)livingentity).hasMarker()) && player.getDistanceSq(livingentity) < 9.0D) {
									livingentity.knockBack(player, 0.4F, (double)MathHelper.sin(player.rotationYaw * ((float)Math.PI / 180F)), (double)(-MathHelper.cos(player.rotationYaw * ((float)Math.PI / 180F))));
									livingentity.attackEntityFrom(DamageSource.causePlayerDamage(player), f3);
								}
							}

							player.world.playSound((PlayerEntity)null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, player.getSoundCategory(), 1.0F, 1.0F);
							player.spawnSweepParticles();
						}

						if (targetEntity instanceof ServerPlayerEntity && targetEntity.velocityChanged) {
							((ServerPlayerEntity)targetEntity).connection.sendPacket(new SEntityVelocityPacket(targetEntity));
							targetEntity.velocityChanged = false;
							targetEntity.setMotion(vec3d);
						}

						if (flag2) {
							player.world.playSound((PlayerEntity)null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_PLAYER_ATTACK_CRIT, player.getSoundCategory(), 1.0F, 1.0F);
							//player.onCriticalHit(targetEntity);
						}

						if (!flag2 && !flag3) {
							if (flag) {
								player.world.playSound((PlayerEntity)null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_PLAYER_ATTACK_STRONG, player.getSoundCategory(), 1.0F, 1.0F);
							} else {
								player.world.playSound((PlayerEntity)null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_PLAYER_ATTACK_WEAK, player.getSoundCategory(), 1.0F, 1.0F);
							}
						}

						if (f1 > 0.0F) {
							player.onEnchantmentCritical(targetEntity);
						}

						player.setLastAttackedEntity(targetEntity);
						if (targetEntity instanceof LivingEntity) {
							EnchantmentHelper.applyThornEnchantments((LivingEntity)targetEntity, player);
						}

						EnchantmentHelper.applyArthropodEnchantments(player, targetEntity);
						ItemStack itemstack1 = player.getHeldItemMainhand();
						Entity entity = targetEntity;
						if (targetEntity instanceof EnderDragonPartEntity) {
							entity = ((EnderDragonPartEntity)targetEntity).dragon;
						}

						if (!player.world.isRemote && !itemstack1.isEmpty() && entity instanceof LivingEntity) {
							ItemStack copy = itemstack1.copy();
							itemstack1.hitEntity((LivingEntity)entity, player);
							if (itemstack1.isEmpty()) {
								net.minecraftforge.event.ForgeEventFactory.onPlayerDestroyItem(player, copy, Hand.MAIN_HAND);
								player.setHeldItem(Hand.MAIN_HAND, ItemStack.EMPTY);
							}
						}

						if (targetEntity instanceof LivingEntity) {
							//float f5 = f4 - ((LivingEntity)targetEntity).getHealth();
							//Disable vanilla stats handling.
							//player.addStat(Stats.DAMAGE_DEALT, Math.round(f5 * 10.0F));
							if (j > 0) {
								targetEntity.setFire(j * 4);
							}

							/* No damage indicator particles.
							if (player.world instanceof ServerWorld && f5 > 2.0F) {
								int k = (int)((double)f5 * 0.5D);
								//((ServerWorld)player.world).spawnParticle(ParticleTypes.DAMAGE_INDICATOR, targetEntity.getPosX(), targetEntity.getPosYHeight(0.5D), targetEntity.getPosZ(), k, 0.1D, 0.0D, 0.1D, 0.2D);
							}
							*/
						}

						player.addExhaustion(0.1F);
					} else {
						player.world.playSound((PlayerEntity)null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_PLAYER_ATTACK_NODAMAGE, player.getSoundCategory(), 1.0F, 1.0F);
						if (flag4) {
							targetEntity.extinguish();
						}
					}
				}
			}
		}
		event.setCanceled(true); //Cancel event to prevent duplicate vanilla handling.
	}
	
}
