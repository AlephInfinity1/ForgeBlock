package alephinfinity1.forgeblock.network;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * A class that serves no other purpose other than informing the server about {@link net.minecraftforge.event.entity.player.PlayerInteractEvent.LeftClickEmpty}.
 */
public class ClientLeftClickPacket {
    ClientLeftClickPacket(final PacketBuffer buf) {
    }

    public ClientLeftClickPacket() {
    }

    void encode(final PacketBuffer buf) {
    }

    public static void handle(CoinsUpdatePacket msg, Supplier<NetworkEvent.Context> ctx) {
        if(ctx.get().getDirection().getReceptionSide().isServer()) {
            ctx.get().enqueueWork(() -> {
                ServerPlayerEntity player = ctx.get().getSender();
            });
        }

        ctx.get().setPacketHandled(true);
    }
}
