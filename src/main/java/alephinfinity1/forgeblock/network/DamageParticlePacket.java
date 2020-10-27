package alephinfinity1.forgeblock.network;

import java.util.function.Supplier;

import alephinfinity1.forgeblock.client.particles.StringParticleData.Style;
import alephinfinity1.forgeblock.misc.DamageHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent;

public class DamageParticlePacket {
	
	private double amount;
	private String style;
	private double posX, posY, posZ;
	
	DamageParticlePacket(final PacketBuffer buf) {
		amount = buf.readDouble();
		style = buf.readString();
		posX = buf.readDouble();
		posY = buf.readDouble();
		posZ = buf.readDouble();
	}
	
	public DamageParticlePacket(double amount, String style, double posX, double posY, double posZ) {
		this.amount = amount;
		this.style = style;
		this.posX = posX;
		this.posY = posY;
		this.posZ = posZ;
	}
	
	public DamageParticlePacket(double amount, String style, Vec3d vec) {
		this.amount = amount;
		this.style = style;
		this.posX = vec.x;
		this.posY = vec.y;
		this.posZ = vec.z;
	}
	
	void encode(final PacketBuffer buf) {
		buf.writeDouble(amount);
		buf.writeString(style);
		buf.writeDouble(posX);
		buf.writeDouble(posY);
		buf.writeDouble(posZ);
	}
	
	@SuppressWarnings("resource")
	public static void handle(DamageParticlePacket msg, Supplier<NetworkEvent.Context> ctx) {
		if(ctx.get().getDirection().getReceptionSide().isClient()) {
			ctx.get().enqueueWork(() -> {
				ClientPlayerEntity player = Minecraft.getInstance().player;
				World world = player.getEntityWorld();
				DamageHandler.addDamageDisplay(world, msg.posX, msg.posY, msg.posZ, msg.amount, Style.parse(msg.style));
			});
		}
		
		ctx.get().setPacketHandled(true);
	}

}
