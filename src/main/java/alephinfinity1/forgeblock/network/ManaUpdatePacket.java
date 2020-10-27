package alephinfinity1.forgeblock.network;

import java.util.function.Supplier;

import alephinfinity1.forgeblock.misc.mana.IMana;
import alephinfinity1.forgeblock.misc.mana.ManaProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class ManaUpdatePacket {
	
	private double amount;
	
	ManaUpdatePacket(final PacketBuffer buf) {
		amount = buf.readDouble();
	}
	
	public ManaUpdatePacket(double amount) {
		this.amount = amount;
	}
	
	void encode(final PacketBuffer buf) {
		buf.writeDouble(amount);
	}
	
	@SuppressWarnings("resource")
	public static void handle(ManaUpdatePacket msg, Supplier<NetworkEvent.Context> ctx) {
		if(ctx.get().getDirection().getReceptionSide().isClient()) {
			ctx.get().enqueueWork(() -> {
				ClientPlayerEntity player = Minecraft.getInstance().player;
				IMana mana = player.getCapability(ManaProvider.MANA_CAPABILITY).orElseThrow(NullPointerException::new);
				mana.set(msg.amount);
			});
		}
		
		ctx.get().setPacketHandled(true);
	}
}
