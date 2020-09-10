package alephinfinity1.forgeblock.misc;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerRespawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class FoodHandler {
	
	@SubscribeEvent
	public static void onPlayerLogin(PlayerLoggedInEvent event) {
		PlayerEntity player = event.getPlayer();
		player.foodStats = new FBFoodStats();
	}
	
	@SubscribeEvent
	public static void onPlayerCreateWorld(EntityConstructing event) {
		if(event.getEntity() instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) event.getEntity();
			player.foodStats = new FBFoodStats();
		}
	}
	
	@SubscribeEvent
	public static void onPlayerRespawn(PlayerRespawnEvent event) {
		PlayerEntity player = event.getPlayer();
		player.foodStats = new FBFoodStats();
	}
}
