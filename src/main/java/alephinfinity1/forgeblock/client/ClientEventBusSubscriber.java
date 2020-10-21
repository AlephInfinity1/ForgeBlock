package alephinfinity1.forgeblock.client;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.MarkerManager.Log4jMarker;

import alephinfinity1.forgeblock.ForgeBlock;
import alephinfinity1.forgeblock.client.particles.NumericDamageIndicatorParticle;
import alephinfinity1.forgeblock.entity.FBExperienceBottleEntity;
import alephinfinity1.forgeblock.init.ModEntities;
import alephinfinity1.forgeblock.init.ModParticles;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.renderer.entity.EndermanRenderer;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = ForgeBlock.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEventBusSubscriber {

	public static final Minecraft mc = Minecraft.getInstance();

	public static void onClientSetup(FMLClientSetupEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.LV1_ZOMBIE.get(), ZombieRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.LAPIS_ZOMBIE.get(), ZombieRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.ZEALOT.get(), EndermanRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.SPECIAL_ZEALOT.get(), EndermanRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.FB_EXPERIENCE_BOTTLE.get(), (em) -> new SpriteRenderer<FBExperienceBottleEntity>(em, mc.getItemRenderer()));

		ForgeBlock.LOGGER.log(Level.TRACE, new Log4jMarker("Test"), "Client stuff fired!");
	}

	@SubscribeEvent
	public static void onParticleFactoryRegister(ParticleFactoryRegisterEvent event) {
		ParticleManager pm = ForgeBlock.MINECRAFT.particles;
		pm.registerFactory(ModParticles.NUMERIC_DAMAGE.get(), new NumericDamageIndicatorParticle.Factory());
	}
}
