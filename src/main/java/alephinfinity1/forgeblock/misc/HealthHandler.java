package alephinfinity1.forgeblock.misc;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Prevents players from spawning with 100 health on login.
 */
@Mod.EventBusSubscriber
public class HealthHandler {
	
	@SubscribeEvent
	public static void onLivingChangeEquipment(LivingEquipmentChangeEvent event) {
		LivingEntity living = event.getEntityLiving();
		//If living is at max health and is not engaged in combat, increase its health to the equipment's health.
		if(MathHelper.epsilonEquals(living.getHealth(), living.getMaxHealth()) && living.getCombatTracker().getBestAttacker() == null) {
			TickHandler.healthDirty.put(living, TickHandler.ticksElapsed);
		}
	}
}
