package alephinfinity1.forgeblock.network;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * A class that serves no other purpose other than informing the server about {@link net.minecraftforge.event.entity.player.PlayerInteractEvent.LeftClickEmpty}.
 */
public class ClientLeftClickPacket {
    boolean offhand; //True if offhand, false if main hand
    BlockPos pos;

    ClientLeftClickPacket(final PacketBuffer buf) {
        offhand = buf.readBoolean();
        int x = buf.readVarInt();
        int y = buf.readVarInt();
        int z = buf.readVarInt();
        pos = new BlockPos(x, y, z);
    }

    public ClientLeftClickPacket(PlayerInteractEvent.LeftClickEmpty event) {
        offhand = (event.getHand() == Hand.OFF_HAND);
        pos = event.getPos();
    }

    void encode(final PacketBuffer buf) {
        buf.writeBoolean(offhand);
        buf.writeVarInt(pos.getX());
        buf.writeVarInt(pos.getY());
        buf.writeVarInt(pos.getZ());
    }

    public static void handle(ClientLeftClickPacket msg, Supplier<NetworkEvent.Context> ctx) {
        if(ctx.get().getDirection().getReceptionSide().isServer()) {
            ctx.get().enqueueWork(() -> {
                ServerPlayerEntity player = ctx.get().getSender();
                if (player != null) {
                    player.jump();
                }
            });
        }

        ctx.get().setPacketHandled(true);
    }

}
