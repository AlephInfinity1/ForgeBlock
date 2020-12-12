package alephinfinity1.forgeblock.misc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.tuple.Triple;
import org.apache.logging.log4j.Level;

import alephinfinity1.forgeblock.ForgeBlock;
import alephinfinity1.forgeblock.attribute.FBAttributes;
import alephinfinity1.forgeblock.item.BlazeArmorItem;
import alephinfinity1.forgeblock.item.FrozenBlazeArmorItem;
import alephinfinity1.forgeblock.item.RogueSwordItem;
import alephinfinity1.forgeblock.item.ShadowFuryItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.DeathScreen;
import net.minecraft.command.arguments.EntityAnchorArgument;
import net.minecraft.command.arguments.LocalLocationArgument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * A class dedicated to handle all tick-based events.
 */
@Mod.EventBusSubscriber
public class TickHandler {
	/**
	 * The number of ticks elapsed.
	 */
	public static long ticksElapsed = 0;
	
	/**
	 * All entities that are newly spawned.
	 */
	private static List<Entity> allEntitiesPre = new Vector<>();
	
	/**
	 * All living entities that are newly spawned.
	 */
	private static List<LivingEntity> allLivingPre = new Vector<>();
	
	/**
	 * All entities in the world.
	 */
	public static List<Entity> allEntities = new Vector<>();
	
	/**
	 * All living entities in the world.
	 */
	public static List<LivingEntity> allLiving = new Vector<>();
	
	/**
	 * Marks all {@link LivingEntity} that have dirty health values (i.e. needs update).
	 * All entities on this list will be healed to full health in 5 gt.
	 */
	public static Map<LivingEntity, Long> healthDirty = new HashMap<>();
	
	/**
	 * Unused.
	 */
	@Deprecated(forRemoval = true)
	public static Map<ArmorStandEntity, Long> damageDisplay = new HashMap<>();
	
	/**
	 * Unused.
	 */
	@Deprecated(forRemoval = true)
	public static List<Triple<LivingEntity, Double, Long>> damageIndicatorFix = new ArrayList<>();
	
	/**
	 * All entities thar are wearing {@link BlazeArmorItem}.
	 * Affects full set bonus actuation.
	 */
	public static Map<LivingEntity, Boolean> isWearingBlazeArmor = new ConcurrentHashMap<>();
	
	/**
	 * All entities thar are wearing {@link FrozenBlazeArmorItem}.
	 * Affects full set bonus actuation.
	 */
	public static Map<LivingEntity, Boolean> isWearingFrozenBlazeArmor = new ConcurrentHashMap<>();
	
	/**
	 * Scheduled extra attacks from {@link FBAttributes#FEROCITY}.
	 */
	public static Map<Tuple<LivingEntity, DamageSource>, Long> ferocitySchedule = new ConcurrentHashMap<>();
	
	/**
	 * The expiry times for all temporary attribute modifiers (e.g. Pigman Defense Boost, Rogue Speed Boost).
	 */
	public static Map<Tuple<LivingEntity, UUID>, Long> attModifierExpiry = new ConcurrentHashMap<>();
	
	/**
	 * Whether {@link RogueSwordItem} effect is active.
	 * If active, Rogue Sword item ability will give half the bonus speed instead.
	 */
	public static Map<LivingEntity, Long> rogueActive = new ConcurrentHashMap<>();
	
	/**
	 * Scheduled attacks from {@link ShadowFuryItem}'s ability.
	 */
	public static Map<Tuple<LivingEntity, LivingEntity>, Long> shadowFuryTarget = new ConcurrentHashMap<>();
	
	public static final Minecraft minecraft = Minecraft.getInstance();
	
	@SubscribeEvent
	public static void onEntityJoinWorld(EntityJoinWorldEvent event) {
		allEntitiesPre.add(event.getEntity());
		if(event.getEntity() instanceof LivingEntity) {
			allLivingPre.add((LivingEntity) event.getEntity());
		}
	}

	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if(event.phase == Phase.START && event.player instanceof ServerPlayerEntity) {
			++ticksElapsed;
			PlayerEntity player = event.player;
			if(ticksElapsed % 40 == 0) {
				player.heal((float) (player.getMaxHealth() / 50.0D));
			}
			if(healthDirty.get(player) != null) {
				if(healthDirty.get(player) - ticksElapsed < -5) {
					player.heal(Float.MAX_VALUE);
					healthDirty.remove(player);
				}
			}
			
			//NaN fix
			if(Float.isNaN(player.getHealth())) {
				player.setHealth(0.0f);
			}
		}
	}
	
	@SuppressWarnings("resource")
	@SubscribeEvent
	public static void onServerTick(TickEvent.ServerTickEvent event) {
		
		if(event.phase == Phase.START) return; 
		
		//Complex operation, so updated every 5 seconds.
		if(ticksElapsed % 100 == 0) {
			
			//To prevent ConcurrentModificationException. All operations are done in one thread.
			allEntities.addAll(allEntitiesPre);
			allLiving.addAll(allLivingPre);
			allEntitiesPre.clear();
			allLivingPre.clear();
			
			Iterator<Entity> entityItr = allEntities.iterator();
			while(entityItr.hasNext()) {
				Entity entry = entityItr.next();
				if(!entry.isAlive()) entityItr.remove();
			}
			
			Iterator<LivingEntity> livingItr = allLiving.iterator();
			while(livingItr.hasNext()) {
				LivingEntity living = livingItr.next();
				if(!living.isAlive()) livingItr.remove();
			}
		}
		
		Iterator<LivingEntity> livingItr = allLiving.iterator();
		while(livingItr.hasNext()) {
			LivingEntity living = livingItr.next();
			if(!living.isAlive()) {
				livingItr.remove();
				continue;
			}
			living.heal((float) (living.getAttribute(FBAttributes.HEALTH_REGEN).getValue() / 20.0D));
		}
		
		if(!damageDisplay.isEmpty()) {
			for(Map.Entry<ArmorStandEntity, Long> entry : damageDisplay.entrySet()) {
				if(ticksElapsed - entry.getValue() > 20) {
					entry.getKey().remove();
				}
			}
		}
		if(!damageIndicatorFix.isEmpty()) {
			Iterator<Triple<LivingEntity, Double, Long>> itr = damageIndicatorFix.iterator();
			while(itr.hasNext()) {
				Triple<LivingEntity, Double, Long> entry = itr.next(); //Using iterators to fix java.util.ConcurrentModificationException. See https://www.cnblogs.com/dolphin0520/p/3933551.html
				if(ticksElapsed - entry.getRight() > 0) {
					entry.getLeft().setHealth((float) (entry.getLeft().getHealth() - entry.getMiddle()));
					itr.remove();
				}
			}
		}

		if(ticksElapsed % 20 == 0) {
			Set<LivingEntity> entities = isWearingBlazeArmor.keySet();
			Iterator<LivingEntity> itr = entities.iterator();
			while(itr.hasNext()) {
				LivingEntity living = itr.next();
				if(!living.isAlive()) {
					itr.remove(); //If dead, remove this from blaze handler to avoid ghost damage.
					continue;
				}
				if(living.getEntityWorld().isRemote) continue;
				if(isWearingBlazeArmor.get(living) != null) {
					if(isWearingBlazeArmor.get(living).equals(Boolean.TRUE)) {
						handleBlazeArmor(living);
					} else {
						isWearingBlazeArmor.remove(living);
					}
				}
				ForgeBlock.LOGGER.log(Level.TRACE, "Entity being blaze armour logged: " + living.getType().getName().getString());
				ForgeBlock.LOGGER.log(Level.TRACE, "Value: " + isWearingBlazeArmor.get(living).toString());
			}
		}
		if(ticksElapsed % 20 == 0) {
			Set<LivingEntity> entities = isWearingFrozenBlazeArmor.keySet();
			Iterator<LivingEntity> itr = entities.iterator();
			while(itr.hasNext()) {
				LivingEntity living = itr.next();
				if(!living.isAlive()) {
					itr.remove(); //If dead, remove this from blaze handler to avoid ghost damage.
					continue;
				}
				if(living.getEntityWorld().isRemote) continue;
				if(isWearingFrozenBlazeArmor.get(living) != null) {
					if(isWearingFrozenBlazeArmor.get(living).equals(Boolean.TRUE)) {
						handleFrozenBlazeArmor(living);
					} else {
						isWearingFrozenBlazeArmor.remove(living);
					}
				}
				ForgeBlock.LOGGER.log(Level.TRACE, "Entity being frozen blaze armour logged: " + living.getType().getName().getString());
				ForgeBlock.LOGGER.log(Level.TRACE, "Value: " + isWearingFrozenBlazeArmor.get(living).toString());
			}
		}
		
		if(!ferocitySchedule.isEmpty()) {
			Set<Tuple<LivingEntity, DamageSource>> entries = ferocitySchedule.keySet();
			Iterator<Tuple<LivingEntity, DamageSource>> itr = entries.iterator();
			while(itr.hasNext()) {
				Tuple<LivingEntity, DamageSource> living = itr.next();
				if(ticksElapsed - ferocitySchedule.get(living) >= 5) {
					living.getA().attackEntityFrom(living.getB(), 2.0f); 
					//No need to worry about amount, DamageHandler will take care of that. Do not set to 1.0f though as that would be confused with sweep attack
					itr.remove();
					continue;
				}
			}
		}
		
		if(!attModifierExpiry.isEmpty()) {
			Set<Tuple<LivingEntity, UUID>> entries = attModifierExpiry.keySet();
			Iterator<Tuple<LivingEntity, UUID>> itr = entries.iterator();
			while(itr.hasNext()) {
				Tuple<LivingEntity, UUID> living = itr.next();
				if(ticksElapsed >= attModifierExpiry.get(living)) {
					Collection<IAttributeInstance> attributes = living.getA().getAttributes().getAllAttributes();
					for(IAttributeInstance attribute : attributes) {
						attribute.removeModifier(living.getB());
					}
					itr.remove();
					continue;
				}
			}
		}
		
		if(!rogueActive.isEmpty()) {
			Set<LivingEntity> entries = rogueActive.keySet();
			Iterator<LivingEntity> itr = entries.iterator();
			while(itr.hasNext()) {
				LivingEntity living = itr.next();
				if(ticksElapsed >= rogueActive.get(living)) {
					itr.remove();
					continue;
				}
			}
		}
		
		if(!shadowFuryTarget.isEmpty()) {
			Set<Map.Entry<Tuple<LivingEntity, LivingEntity>, Long>> entries = shadowFuryTarget.entrySet();
			Iterator<Map.Entry<Tuple<LivingEntity, LivingEntity>, Long>> itr = entries.iterator();
			while(itr.hasNext()) {
				Map.Entry<Tuple<LivingEntity, LivingEntity>, Long> entry = itr.next();
				if(ticksElapsed >= entry.getValue()) {
					Tuple<LivingEntity, LivingEntity> targets = entry.getKey();
					LivingEntity attacker = targets.getA();
					LivingEntity victim = targets.getB();
					Vec3d pos = new LocalLocationArgument(0.0D, 0.0D, -2.0D).getPosition(victim.getCommandSource()); //A hack using LocalLocationArgument to teleport the player 2 blocks behind the victim.
					attacker.setPositionAndRotation(pos.x, pos.y, pos.z, victim.getYaw(0.0f), victim.getPitch(0.0f));
					attacker.lookAt(EntityAnchorArgument.Type.EYES, victim.getPositionVec());
					attacker.setPositionAndUpdate(pos.x, pos.y, pos.z);
					
					//Prevents the attacker from being stuck in blocks.
					while(!attacker.getEntityWorld().isAirBlock(attacker.getPosition())) {
						attacker.setPositionAndUpdate(attacker.getPosX(), attacker.getPosY() + 1, attacker.getPosZ());
					}
					
					if(targets.getA() instanceof PlayerEntity) {
						((PlayerEntity) targets.getA()).attackTargetEntityWithCurrentItem(targets.getB()); //Attacks the target with current item. This is to display the attack animation properly.
					} else {
						targets.getB().attackEntityFrom(DamageSource.causeMobDamage(targets.getA()), 1.0f);
					}
					
					itr.remove();
				}
			}
		}
	}
	
	/*
	 * XXX An extremely wonky attempt to fix MC-19690. Death screen still appears for a client tick. 
	 */
	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public static void onClientTick(TickEvent.ClientTickEvent event) {
		if(minecraft.currentScreen != null) {
			if(minecraft.currentScreen instanceof DeathScreen && minecraft.player.getHealth() > 0) {
				minecraft.displayGuiScreen(null);
				minecraft.player.deathTime = 0;
				minecraft.player.getDataManager().set(Entity.POSE, Pose.STANDING);
			}
		}
		if(minecraft.player != null) {
			if(minecraft.player.deathTime != 0 && minecraft.player.getHealth() > 0) {
				minecraft.player.deathTime = 0;
			}
		}
	}
	
	@SuppressWarnings("resource")
	public static void handleBlazeArmor(LivingEntity living) {
		List<Entity> entities = living.getEntityWorld().getEntitiesWithinAABBExcludingEntity(living, new AxisAlignedBB(living.getPositionVec().add(-5, -5, -5), living.getPositionVec().add(5, 5, 5)));
		Iterable<ItemStack> armor = living.getArmorInventoryList(); //An additional check if the player/mob is wearing blaze armour.
		if(!living.getEntityWorld().isRemote)
			for(ItemStack stack : armor)
				if(!(stack.getItem() instanceof BlazeArmorItem)) continue;
		for(Entity entity : entities) {
			if(!living.getEntityWorld().isRemote) {
				if(!(entity instanceof LivingEntity)) continue;
				if(entity.getDistanceSq(living) > 25.0) continue;
				LivingEntity victim = (LivingEntity) entity;
				Vec3d prevVel = victim.getMotion();
				victim.attackEntityFrom(DamageHandler.causeBlazeArmorDamage(living), victim.getMaxHealth() > 166666.67f ? 5000.0f : victim.getMaxHealth() * 0.03f);
				victim.setMotion(prevVel);
				victim.hurtResistantTime = 0; //No damage tick here
			} 
			for(int i = 0; i < 5; i++)
				living.world.addParticle(ParticleTypes.FLAME, living.getPosXRandom(5.0D), living.getPosY(), living.getPosZRandom(5.0D), 0, 0, 0);
		}
	}
	
	@SuppressWarnings("resource")
	public static void handleFrozenBlazeArmor(LivingEntity living) {
		List<Entity> entities = living.getEntityWorld().getEntitiesWithinAABBExcludingEntity(living, new AxisAlignedBB(living.getPositionVec().add(-5, -5, -5), living.getPositionVec().add(5, 5, 5)));
		Iterable<ItemStack> armor = living.getArmorInventoryList(); //An additional check if the player/mob is wearing blaze armour.
		if(!living.getEntityWorld().isRemote)
			for(ItemStack stack : armor)
				if(!(stack.getItem() instanceof FrozenBlazeArmorItem)) continue;
		for(Entity entity : entities) {
			if(!living.getEntityWorld().isRemote) {
				if(!(entity instanceof LivingEntity)) continue;
				if(entity.getDistanceSq(living) > 25.0) continue;
				LivingEntity victim = (LivingEntity) entity;
				Vec3d prevVel = victim.getMotion();
				victim.attackEntityFrom(DamageHandler.causeBlazeArmorDamage(living), victim.getMaxHealth() > 166666.67f ? 5000.0f : victim.getMaxHealth() * 0.03f + 300.0f);
				victim.setMotion(prevVel);
				victim.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 80, 0, false, false));
				victim.hurtResistantTime = 0; //No damage tick here
			}
			for(int i = 0; i < 5; i++)
				living.world.addParticle(ParticleTypes.FLAME, living.getPosXRandom(5.0D), living.getPosY(), living.getPosZRandom(5.0D), 0, 0, 0);
		}
	}
	
	public static boolean isRogueActive(LivingEntity living) {
		return rogueActive.get(living) != null;
	}
	
}
