package alephinfinity1.forgeblock.misc;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class FoodHandler {
	
	@SubscribeEvent
	public static void onPlayerLogin(PlayerLoggedInEvent event) {
		PlayerEntity player = event.getPlayer();
		player.foodStats = new FBFoodStats();
	}
}
