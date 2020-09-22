package alephinfinity1.forgeblock.init;

import alephinfinity1.forgeblock.command.RandomSwordCommand;
import alephinfinity1.forgeblock.command.ReforgeCommand;
import alephinfinity1.forgeblock.command.SkillCommand;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

@Mod.EventBusSubscriber
public class ModCommands {
	
	@SubscribeEvent
	public static void onCommonSetup(FMLServerStartingEvent event) {
		ReforgeCommand.register(event.getCommandDispatcher());
		RandomSwordCommand.register(event.getCommandDispatcher());
		SkillCommand.register(event.getCommandDispatcher());
	}

}
