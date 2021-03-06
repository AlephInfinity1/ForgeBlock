package alephinfinity1.forgeblock.misc.capability.coins;

import java.text.DecimalFormat;
import java.util.Objects;

import alephinfinity1.forgeblock.entity.IFBEntity;
import alephinfinity1.forgeblock.network.CoinsUpdatePacket;
import alephinfinity1.forgeblock.network.FBPacketHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.PacketDistributor;

@Mod.EventBusSubscriber
public class CoinsEventHandler {
	
	@SubscribeEvent
	public static void onPlayerLogin(PlayerLoggedInEvent event) {
		ICoins coins = event.getPlayer().getCapability(CoinsProvider.COINS_CAPABILITY).orElseThrow(NullPointerException::new);
		FBPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) event.getPlayer()), new CoinsUpdatePacket(coins.getCoins()));
	}

	@SubscribeEvent
	public static void onEntityDeath(LivingDeathEvent event) {
		
		//final DecimalFormat format = new DecimalFormat(",###.#");
		
		if(event.getSource() instanceof EntityDamageSource && event.getSource() != null) {
			if(((EntityDamageSource) event.getSource()).getTrueSource() instanceof PlayerEntity) {
				Entity e = event.getSource().getTrueSource();
				if(!(e instanceof PlayerEntity)) return;
				PlayerEntity player = (PlayerEntity) e;
				ICoins coins = player.getCapability(CoinsProvider.COINS_CAPABILITY).orElse(null);
				if(Objects.nonNull(coins)) {
					if(event.getEntity() instanceof IFBEntity)
						coins.add(((IFBEntity) event.getEntity()).getCoins());
					FBPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new CoinsUpdatePacket(coins.getCoins()));
				}
			}
		}
	}
	
	@SubscribeEvent
	public static void onPlayerClone(PlayerEvent.Clone event) {	
		ICoins oldCoins = event.getOriginal().getCapability(CoinsProvider.COINS_CAPABILITY).orElseThrow(NullPointerException::new);
		ICoins newCoins = event.getPlayer().getCapability(CoinsProvider.COINS_CAPABILITY).orElseThrow(NullPointerException::new);
		newCoins.set(event.isWasDeath() ? oldCoins.getCoins() / 2.0D : oldCoins.getCoins()); //If not death, do not reduce coins.
		FBPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) event.getPlayer()), new CoinsUpdatePacket(newCoins.getCoins()));
		if(oldCoins.getCoins() != 0 && event.isWasDeath())
			event.getPlayer().sendMessage(new TranslationTextComponent("text.forgeblock.lostCoins", new DecimalFormat(",###.#").format(oldCoins.getCoins() / 2.0D).replaceAll("\u00A0", ",")).applyTextStyle(TextFormatting.RED));
		FBPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) event.getPlayer()), new CoinsUpdatePacket(newCoins.getCoins()));
	}
	
	@SubscribeEvent
	public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
		if(event.getPlayer() instanceof ServerPlayerEntity) {
			ICoins coins = event.getPlayer().getCapability(CoinsProvider.COINS_CAPABILITY).orElseThrow(NullPointerException::new);
			FBPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) event.getPlayer()), new CoinsUpdatePacket(coins.getCoins()));	
		}
	}
}
