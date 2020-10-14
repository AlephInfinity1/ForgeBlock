package alephinfinity1.forgeblock.misc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Triple;

import alephinfinity1.forgeblock.attribute.FBAttributes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.DeathScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.item.ArmorStandEntity;
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
	public static Map<ArmorStandEntity, Long> damageDisplay = new HashMap<>();
	public static List<Triple<LivingEntity, Double, Long>> damageIndicatorFix = new ArrayList<>();
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
				if(healthDirty.get(player) - tickElapsed < -5) {
					player.heal(Float.MAX_VALUE);
					healthDirty.remove(player);
				}
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
