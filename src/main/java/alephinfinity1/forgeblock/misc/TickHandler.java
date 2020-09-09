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
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		if(event.phase == Phase.START && event.player instanceof ServerPlayerEntity) {
			++tickElapsed;
			PlayerEntity player = event.player;
			if(tickElapsed % 40 == 0) {
				player.heal((float) (player.getMaxHealth() / 50.0D));
			}
			player.heal((float) (player.getAttribute(FBAttributes.HEALTH_REGEN).getValue() / 20.0D));
		}
	}

}
