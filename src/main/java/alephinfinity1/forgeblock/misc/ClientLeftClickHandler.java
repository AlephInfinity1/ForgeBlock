package alephinfinity1.forgeblock.misc;

import alephinfinity1.forgeblock.network.ClientLeftClickPacket;
import alephinfinity1.forgeblock.network.FBPacketHandler;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ClientLeftClickHandler {
    @SubscribeEvent
    public static void onClientLeftClick(PlayerInteractEvent.LeftClickEmpty event) {
        FBPacketHandler.INSTANCE.sendToServer(new ClientLeftClickPacket(event));
    }
}
