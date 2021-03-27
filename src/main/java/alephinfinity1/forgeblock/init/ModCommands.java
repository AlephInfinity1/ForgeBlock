package alephinfinity1.forgeblock.init;

import alephinfinity1.forgeblock.command.*;
import alephinfinity1.forgeblock.misc.capability.accessories.AccessoriesProvider;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

@Mod.EventBusSubscriber
public class ModCommands {
	
	@SubscribeEvent
	public static void onCommandSetup(FMLServerStartingEvent event) {
		ReforgeCommand.register(event.getCommandDispatcher());
		RandomSwordCommand.register(event.getCommandDispatcher());
		SkillCommand.register(event.getCommandDispatcher());
		CoinsCommand.register(event.getCommandDispatcher());
		AccCommand.register(event.getCommandDispatcher());
	}

}
