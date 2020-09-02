package alephinfinity1.forgeblock.misc;

import alephinfinity1.forgeblock.attribute.FBAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class TickHandler {
	private static long tickElapsed = 0;

	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if(event.phase == Phase.START && event.player instanceof ServerPlayerEntity) {
			++tickElapsed;
			if(tickElapsed % 20 == 0) {
				PlayerEntity player = event.player;
				player.heal((float) (player.getMaxHealth() / 100.0D + player.getAttribute(FBAttributes.HEALTH_REGEN).getValue()));
			}
		}
	}

}
