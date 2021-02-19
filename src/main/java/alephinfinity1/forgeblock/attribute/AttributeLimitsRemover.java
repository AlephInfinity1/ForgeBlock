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

import alephinfinity1.forgeblock.misc.TickHandler;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerRespawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/*
 * Removes the min/max limits of existing attributes, and adds some new attributes.
 * Some code is taken from https://github.com/Darkhax-Minecraft/AttributeFix
 */
@Mod.EventBusSubscriber
public class AttributeLimitsRemover {
	
	/*
	 * Vanilla attributes, for modification
	 */
	public static final IAttribute[] VANILLA_ATTRIBUTES = {MAX_HEALTH, FOLLOW_RANGE, KNOCKBACK_RESISTANCE, MOVEMENT_SPEED, FLYING_SPEED, ATTACK_DAMAGE, ATTACK_KNOCKBACK, ATTACK_SPEED, ARMOR, ARMOR_TOUGHNESS, LUCK};
	
	/*
	 * Custom attributes
	 */
	private AttributeLimitsRemover() {
		throw new AssertionError("AttributeHelper should not be instantiated!");
	}
	
	public static void removeLimits() {
		for(IAttribute attribute : VANILLA_ATTRIBUTES) {
			if(attribute instanceof RangedAttribute) {
				RangedAttribute ranged = (RangedAttribute) attribute;
				ranged.maximumValue = Double.MAX_VALUE;
				if (attribute.equals(MAX_HEALTH)) ranged.minimumValue = 1.0D;
				else ranged.minimumValue = Double.MIN_VALUE;
			}
		}
	}

//	@SubscribeEvent
//	public static void onPlayerLogin(PlayerLoggedInEvent event) {
//		LivingEntity player = event.getPlayer();
//		player.getAttribute(MAX_HEALTH).setBaseValue(100.0D);
//		player.getAttribute(ATTACK_DAMAGE).setBaseValue(5.0D);
//		player.getAttribute(ATTACK_SPEED).setBaseValue(Double.MAX_VALUE);
//	}
	
	@SubscribeEvent
	public static void onPlayerRespawn(PlayerRespawnEvent event) {
		LivingEntity player = event.getPlayer();
//		player.getAttribute(MAX_HEALTH).setBaseValue(100.0D);
//		player.getAttribute(ATTACK_DAMAGE).setBaseValue(5.0D);
//		player.getAttribute(ATTACK_SPEED).setBaseValue(Double.MAX_VALUE);
		TickHandler.healthDirty.put((PlayerEntity) player, TickHandler.serverTicksElapsed);
	}

}
