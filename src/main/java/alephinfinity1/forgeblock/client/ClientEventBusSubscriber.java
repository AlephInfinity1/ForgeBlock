package alephinfinity1.forgeblock.client;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.MarkerManager.Log4jMarker;

import alephinfinity1.forgeblock.ForgeBlock;
import alephinfinity1.forgeblock.entity.render.FBZombieRenderer;
import alephinfinity1.forgeblock.init.ModEntities;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientEventBusSubscriber {

	public static final Minecraft mc = Minecraft.getInstance();
	
	public static void onClientSetup(FMLClientSetupEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.LV1_ZOMBIE.get(), FBZombieRenderer::new);
		ForgeBlock.LOGGER.log(Level.INFO, new Log4jMarker("Test"), "Client stuff fired!");
	}
}
