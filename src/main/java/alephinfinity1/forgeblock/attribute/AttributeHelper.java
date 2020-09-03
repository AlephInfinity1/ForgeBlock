package alephinfinity1.forgeblock.attribute;

import static net.minecraft.entity.SharedMonsterAttributes.ARMOR;
import static net.minecraft.entity.SharedMonsterAttributes.ARMOR_TOUGHNESS;
import static net.minecraft.entity.SharedMonsterAttributes.ATTACK_DAMAGE;
import static net.minecraft.entity.SharedMonsterAttributes.ATTACK_KNOCKBACK;
import static net.minecraft.entity.SharedMonsterAttributes.ATTACK_SPEED;
import static net.minecraft.entity.SharedMonsterAttributes.FLYING_SPEED;
import static net.minecraft.entity.SharedMonsterAttributes.FOLLOW_RANGE;
import static net.minecraft.entity.SharedMonsterAttributes.KNOCKBACK_RESISTANCE;
import static net.minecraft.entity.SharedMonsterAttributes.LUCK;
import static net.minecraft.entity.SharedMonsterAttributes.MAX_HEALTH;
import static net.minecraft.entity.SharedMonsterAttributes.MOVEMENT_SPEED;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerRespawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/*
 * Removes the min/max limits of existing attributes, and adds some new attributes.
 * Some code is taken from https://github.com/Darkhax-Minecraft/AttributeFix
 */
@Mod.EventBusSubscriber
public class AttributeHelper {
	
	/*
	 * Vanilla attributes, for modification
	 */
	public static final IAttribute[] VANILLA_ATTRIBUTES = {MAX_HEALTH, FOLLOW_RANGE, KNOCKBACK_RESISTANCE, MOVEMENT_SPEED, FLYING_SPEED, ATTACK_DAMAGE, ATTACK_KNOCKBACK, ATTACK_SPEED, ARMOR, ARMOR_TOUGHNESS, LUCK};
	
	/*
	 * Custom attributes
	 * Note: American spelling is used in all of the below
	 */
	

	
	public AttributeHelper() {
	}
	
	public static void removeLimits() {
		for(IAttribute attribute : VANILLA_ATTRIBUTES) {
			if(attribute instanceof RangedAttribute) {
				RangedAttribute ranged = (RangedAttribute) attribute;
				ranged.maximumValue = Double.MAX_VALUE;
				ranged.minimumValue = -Double.MAX_VALUE;
			}
		}
	}
	
	@SubscribeEvent
	public static void applyAttributesToPlayer(EntityConstructing event) {
		if(event.getEntity() instanceof LivingEntity) {
			LivingEntity living = (LivingEntity) event.getEntity();
			
			/*
			if(living instanceof PlayerEntity) {
				try {
					//Sets base attack damage to 5
					living.getAttribute(ATTACK_DAMAGE).setBaseValue(5.0D);
					
					//Sets base attack speed to Double.MAX_VALUE
					living.getAttribute(ATTACK_SPEED).setBaseValue(Double.MAX_VALUE);
				} catch(Exception ignored) {
					;
				}
			}
			*/
			
			//Registers custom attributes
			living.getAttributes().registerAttribute(FBAttributes.STRENGTH);
			living.getAttribute(FBAttributes.STRENGTH).setBaseValue(0.0D);
			living.getAttributes().registerAttribute(FBAttributes.DEFENSE);
			living.getAttribute(FBAttributes.DEFENSE).setBaseValue(0.0D);
			living.getAttributes().registerAttribute(FBAttributes.TRUE_DEFENSE);
			living.getAttribute(FBAttributes.TRUE_DEFENSE).setBaseValue(0.0D);
			living.getAttributes().registerAttribute(FBAttributes.CRIT_CHANCE);
			living.getAttribute(FBAttributes.CRIT_CHANCE).setBaseValue(30.0D);
			living.getAttributes().registerAttribute(FBAttributes.CRIT_DAMAGE);
			living.getAttribute(FBAttributes.CRIT_DAMAGE).setBaseValue(50.0D);
			living.getAttributes().registerAttribute(FBAttributes.BONUS_ATTACK_SPEED);
			living.getAttribute(FBAttributes.BONUS_ATTACK_SPEED).setBaseValue(0.0D);
			living.getAttributes().registerAttribute(FBAttributes.INTELLIGENCE);
			living.getAttribute(FBAttributes.INTELLIGENCE).setBaseValue(0.0D);
			//Player-only attributes
			if(living instanceof PlayerEntity) {
				living.getAttributes().registerAttribute(FBAttributes.SEA_CREATURE_CHANCE);
				living.getAttribute(FBAttributes.SEA_CREATURE_CHANCE).setBaseValue(0.0D);
				living.getAttributes().registerAttribute(FBAttributes.MAGIC_FIND);
				living.getAttribute(FBAttributes.MAGIC_FIND).setBaseValue(0.0D);
				living.getAttributes().registerAttribute(FBAttributes.PET_LUCK);
				living.getAttribute(FBAttributes.PET_LUCK).setBaseValue(0.0D);
				living.getAttributes().registerAttribute(FBAttributes.HEALTH_REGEN);
				living.getAttribute(FBAttributes.HEALTH_REGEN).setBaseValue(0.0D);
			}
		}
	}
	
	/*
	@SubscribeEvent
	public static void onPlayerLogin(PlayerLoggedInEvent event) {
		LivingEntity player = event.getPlayer();
		player.getAttribute(MAX_HEALTH).setBaseValue(100.0D);
		player.getAttribute(ATTACK_DAMAGE).setBaseValue(5.0D);
		player.getAttribute(ATTACK_SPEED).setBaseValue(Double.MAX_VALUE);
	}
	*/
	
	@SubscribeEvent
	public static void onPlayerRespawn(PlayerRespawnEvent event) {
		LivingEntity player = event.getPlayer();
		player.getAttribute(MAX_HEALTH).setBaseValue(100.0D);
		player.getAttribute(ATTACK_DAMAGE).setBaseValue(5.0D);
		player.getAttribute(ATTACK_SPEED).setBaseValue(Double.MAX_VALUE);
		player.setHealth(player.getMaxHealth());
	}

}
