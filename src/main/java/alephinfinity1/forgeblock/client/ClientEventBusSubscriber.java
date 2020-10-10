package alephinfinity1.forgeblock.client;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.MarkerManager.Log4jMarker;

import alephinfinity1.forgeblock.ForgeBlock;
import alephinfinity1.forgeblock.init.ModEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EndermanRenderer;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientEventBusSubscriber {

	public static final Minecraft mc = Minecraft.getInstance();
	
	public static void onClientSetup(FMLClientSetupEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.LV1_ZOMBIE.get(), ZombieRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.LAPIS_ZOMBIE.get(), ZombieRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.ZEALOT.get(), EndermanRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.SPECIAL_ZEALOT.get(), EndermanRenderer::new);
		ForgeBlock.LOGGER.log(Level.INFO, new Log4jMarker("Test"), "Client stuff fired!");
	}
}
