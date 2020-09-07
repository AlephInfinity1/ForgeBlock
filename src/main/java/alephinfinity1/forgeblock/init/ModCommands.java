package alephinfinity1.forgeblock.init;

import alephinfinity1.forgeblock.command.ReforgeCommand;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

@Mod.EventBusSubscriber
public class ModCommands {
	
	@SubscribeEvent
	public static void onCommonSetup(FMLServerStartingEvent event) {
		ReforgeCommand.register(event.getCommandDispatcher());
	}

}
