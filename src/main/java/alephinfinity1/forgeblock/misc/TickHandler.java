package alephinfinity1.forgeblock.misc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.tuple.Triple;
import org.apache.logging.log4j.Level;

import alephinfinity1.forgeblock.ForgeBlock;
import alephinfinity1.forgeblock.attribute.FBAttributes;
import alephinfinity1.forgeblock.item.BlazeArmorItem;
import alephinfinity1.forgeblock.item.FrozenBlazeArmorItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.DeathScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
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
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class TickHandler {
	public static long tickElapsed = 0;
	private static List<Entity> allEntitiesPre = new Vector<>();
	private static List<LivingEntity> allLivingPre = new Vector<>();
	public static List<Entity> allEntities = new Vector<>();
	public static List<LivingEntity> allLiving = new Vector<>();
	public static Map<LivingEntity, Long> healthDirty = new HashMap<>();
	public static Map<ArmorStandEntity, Long> damageDisplay = new HashMap<>();
	public static List<Triple<LivingEntity, Double, Long>> damageIndicatorFix = new ArrayList<>();
	public static Map<LivingEntity, Boolean> isWearingBlazeArmor = new ConcurrentHashMap<>();
	public static Map<LivingEntity, Boolean> isWearingFrozenBlazeArmor = new ConcurrentHashMap<>();
	public static Map<Tuple<LivingEntity, DamageSource>, Long> ferocitySchedule = new ConcurrentHashMap<>();
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
			++tickElapsed;
			PlayerEntity player = event.player;
			if(tickElapsed % 40 == 0) {
				player.heal((float) (player.getMaxHealth() / 50.0D));
			}
			if(healthDirty.get(player) != null) {
				if(healthDirty.get(player) - tickElapsed < -5) {
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
		if(tickElapsed % 100 == 0) {
			
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
				if(tickElapsed - entry.getValue() > 20) {
					entry.getKey().remove();
				}
			}
		}
		if(!damageIndicatorFix.isEmpty()) {
			Iterator<Triple<LivingEntity, Double, Long>> itr = damageIndicatorFix.iterator();
			while(itr.hasNext()) {
				Triple<LivingEntity, Double, Long> entry = itr.next(); //Using iterators to fix java.util.ConcurrentModificationException. See https://www.cnblogs.com/dolphin0520/p/3933551.html
				if(tickElapsed - entry.getRight() > 0) {
					entry.getLeft().setHealth((float) (entry.getLeft().getHealth() - entry.getMiddle()));
					itr.remove();
				}
			}
		}

		if(tickElapsed % 20 == 0) {
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
		if(tickElapsed % 20 == 0) {
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
				if(tickElapsed - ferocitySchedule.get(living) >= 5) {
					living.getA().attackEntityFrom(living.getB(), 2.0f); 
					//No need to worry about amount, DamageHandler will take care of that. Do not set to 1.0f though as that would be confused with sweep attack
					itr.remove();
					continue;
				}
			}
		}
	}
	
	/*
	 * An extremely wonky attempt to fix MC-19690. Death screen still appears for a client tick.
	 * XXX
	 */
	@SubscribeEvent
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
				victim.attackEntityFrom(DamageHandler.getBlazeArmorDamageSource(living), victim.getMaxHealth() > 166666.67f ? 5000.0f : victim.getMaxHealth() * 0.03f);
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
				victim.attackEntityFrom(DamageHandler.getBlazeArmorDamageSource(living), victim.getMaxHealth() > 166666.67f ? 5000.0f : victim.getMaxHealth() * 0.03f + 300.0f);
				victim.setMotion(prevVel);
				victim.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 80, 0, false, false));
				victim.hurtResistantTime = 0; //No damage tick here
			}
			for(int i = 0; i < 5; i++)
				living.world.addParticle(ParticleTypes.FLAME, living.getPosXRandom(5.0D), living.getPosY(), living.getPosZRandom(5.0D), 0, 0, 0);
		}
	}
	
}
