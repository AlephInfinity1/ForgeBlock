package alephinfinity1.forgeblock.client;

import java.lang.reflect.Field;

import alephinfinity1.forgeblock.entity.FBArrowEntity;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.MarkerManager.Log4jMarker;

import alephinfinity1.forgeblock.ForgeBlock;
import alephinfinity1.forgeblock.client.particles.NumericDamageIndicatorParticle;
import alephinfinity1.forgeblock.client.screen.FBAnvilScreen;
import alephinfinity1.forgeblock.entity.FBExperienceBottleEntity;
import alephinfinity1.forgeblock.entity.minion.basic.render.MinionRenderer;
import alephinfinity1.forgeblock.init.ModContainerTypes;
import alephinfinity1.forgeblock.init.ModEntities;
import alephinfinity1.forgeblock.init.ModParticles;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
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
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.FB_ARROW.get(), erm -> new ArrowRenderer<FBArrowEntity>(erm){
			@Override
			public ResourceLocation getEntityTexture(FBArrowEntity entity) {
				return TippedArrowRenderer.RES_ARROW;
			}
		});

		RenderingRegistry.registerEntityRenderingHandler(ModEntities.MINION.get(), MinionRenderer::new);
		ForgeBlock.LOGGER.log(Level.TRACE, new Log4jMarker("Test"), "Client stuff fired!");	
		
		//Screens
		ScreenManager.FACTORIES.remove(ModContainerTypes.FB_ANVIL.get());
		ScreenManager.FACTORIES.remove(ContainerType.ANVIL);
		ScreenManager.registerFactory(ModContainerTypes.FB_ANVIL.get(), FBAnvilScreen::new);	
		ScreenManager.registerFactory(ContainerType.ANVIL, FBAnvilScreen::new);
		
		/**
		 * XXX overrides {@link net.minecraft.client.Minecraft}'s several renderers.
		 * May be potentially very destructive and unstable
		 */
		try {
			Field itemRenderer = Minecraft.class.getDeclaredField("itemRenderer");
			itemRenderer.setAccessible(true);
			itemRenderer.set(ForgeBlock.MINECRAFT, new FBItemRenderer(ForgeBlock.MINECRAFT.textureManager,
					ForgeBlock.MINECRAFT.getModelManager(),
					ForgeBlock.MINECRAFT.getItemColors()));
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SubscribeEvent
	public static void onTextureStitchPost(TextureStitchEvent.Post event) {
		//XXX Update texture after stitch to prevent magenta-black checkerboxes.
		//Can be unstable.
		try {
			Field itemRenderer = Minecraft.class.getDeclaredField("itemRenderer");
			itemRenderer.setAccessible(true);
			itemRenderer.set(ForgeBlock.MINECRAFT, new FBItemRenderer(ForgeBlock.MINECRAFT.textureManager,
					ForgeBlock.MINECRAFT.getModelManager(),
					ForgeBlock.MINECRAFT.getItemColors()));
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SubscribeEvent
	public static void onParticleFactoryRegister(ParticleFactoryRegisterEvent event) {
		ParticleManager pm = ForgeBlock.MINECRAFT.particles;
		pm.registerFactory(ModParticles.NUMERIC_DAMAGE.get(), new NumericDamageIndicatorParticle.Factory());
	}
}
