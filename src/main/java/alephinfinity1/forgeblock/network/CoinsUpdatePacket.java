package alephinfinity1.forgeblock.network;

import java.util.function.Supplier;

import alephinfinity1.forgeblock.misc.capability.coins.CoinsProvider;
import alephinfinity1.forgeblock.misc.capability.coins.ICoins;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class CoinsUpdatePacket {
	
	private double amount;
	
	CoinsUpdatePacket(final PacketBuffer buf) {
		amount = buf.readDouble();
	}
	
	public CoinsUpdatePacket(double amount) {
		this.amount = amount;
	}
	
	void encode(final PacketBuffer buf) {
		buf.writeDouble(amount);
	}
	
	@SuppressWarnings("resource")
	public static void handle(CoinsUpdatePacket msg, Supplier<NetworkEvent.Context> ctx) {
		if(ctx.get().getDirection().getReceptionSide().isClient()) {
			ctx.get().enqueueWork(() -> {
				ClientPlayerEntity player = Minecraft.getInstance().player;
				ICoins coins = player.getCapability(CoinsProvider.COINS_CAPABILITY).orElse(null);
				if (coins != null) {
					coins.set(msg.amount);
				}
			});
		}
		
		ctx.get().setPacketHandled(true);
	}
}
