package alephinfinity1.forgeblock.client;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.MarkerManager.Log4jMarker;

import alephinfinity1.forgeblock.ForgeBlock;
import alephinfinity1.forgeblock.client.particles.NumericDamageIndicatorParticle;
import alephinfinity1.forgeblock.client.screen.FBAnvilScreen;
import alephinfinity1.forgeblock.entity.FBExperienceBottleEntity;
import alephinfinity1.forgeblock.entity.minion.render.MinionRenderer;
import alephinfinity1.forgeblock.init.ModContainerTypes;
import alephinfinity1.forgeblock.init.ModEntities;
import alephinfinity1.forgeblock.init.ModParticles;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.renderer.entity.EndermanRenderer;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.client.renderer.entity.WolfRenderer;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.client.renderer.entity.ZombieVillagerRenderer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = ForgeBlock.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEventBusSubscriber {

	public static final Minecraft mc = Minecraft.getInstance();

	/**
	 * XXX The anvil register part is wonky.
	 */
	@SubscribeEvent
	public static void onClientSetup(FMLClientSetupEvent event) {
		//Entities
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.LV1_ZOMBIE.get(), ZombieRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.LAPIS_ZOMBIE.get(), ZombieRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.ZEALOT.get(), EndermanRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.SPECIAL_ZEALOT.get(), EndermanRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.FB_EXPERIENCE_BOTTLE.get(), (erm) -> new SpriteRenderer<FBExperienceBottleEntity>(erm, mc.getItemRenderer()));
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.LV15_WOLF.get(), WolfRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.OLD_WOLF.get(), WolfRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.LV1_ZOMBIE_VILLAGER.get(), (erm) -> new ZombieVillagerRenderer(erm, mc.resourceManager));
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.CRYPT_GHOUL.get(), ZombieRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.GOLDEN_GHOUL.get(), ZombieRenderer::new);

		RenderingRegistry.registerEntityRenderingHandler(ModEntities.MINION.get(), MinionRenderer::new);
		ForgeBlock.LOGGER.log(Level.TRACE, new Log4jMarker("Test"), "Client stuff fired!");	
		
		//Screens
		ScreenManager.FACTORIES.remove(ModContainerTypes.FB_ANVIL.get());
		ScreenManager.FACTORIES.remove(ContainerType.ANVIL);
		ScreenManager.registerFactory(ModContainerTypes.FB_ANVIL.get(), FBAnvilScreen::new);
		ScreenManager.registerFactory(ContainerType.ANVIL, FBAnvilScreen::new);	
	}

	@SubscribeEvent
	public static void onParticleFactoryRegister(ParticleFactoryRegisterEvent event) {
		ParticleManager pm = ForgeBlock.MINECRAFT.particles;
		pm.registerFactory(ModParticles.NUMERIC_DAMAGE.get(), new NumericDamageIndicatorParticle.Factory());
	}
}
