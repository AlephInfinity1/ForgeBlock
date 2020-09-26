package alephinfinity1.forgeblock.misc;

import java.util.HashMap;
import java.util.Map;

import alephinfinity1.forgeblock.attribute.FBAttributes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.DeathScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class TickHandler {
	public static long tickElapsed = 0;
	public static Map<PlayerEntity, Long> healthDirty = new HashMap<>();
	public static final Minecraft minecraft = Minecraft.getInstance();

	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if(event.phase == Phase.START && event.player instanceof ServerPlayerEntity) {
			++tickElapsed;
			PlayerEntity player = event.player;
			if(tickElapsed % 40 == 0) {
				player.heal((float) (player.getMaxHealth() / 50.0D));
			}
			player.heal((float) (player.getAttribute(FBAttributes.HEALTH_REGEN).getValue() / 20.0D));
			if(healthDirty.get(player) != null) {
				if(healthDirty.get(player) - tickElapsed > 5) {
					player.heal(1000000000.0f);
					healthDirty.remove(player);
				}
			}
		}
	}
	
	/*
	 * An extremely wonky attempt to fix MC-19690. Death screen still appears for a client tick.
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

}
