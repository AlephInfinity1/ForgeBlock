package alephinfinity1.forgeblock.client;

import java.lang.reflect.Field;

import alephinfinity1.forgeblock.entity.FBArrowEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.entity.model.PlayerModel;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.MarkerManager.Log4jMarker;

import alephinfinity1.forgeblock.ForgeBlock;
import alephinfinity1.forgeblock.client.particles.NumericDamageIndicatorParticle;
import alephinfinity1.forgeblock.client.screen.FBAnvilScreen;
import alephinfinity1.forgeblock.entity.FBExperienceBottleEntity;
import alephinfinity1.forgeblock.entity.TribeMember01Entity;
import alephinfinity1.forgeblock.entity.YoungLostAdventurerEntity;
import alephinfinity1.forgeblock.entity.minion.basic.render.MinionRenderer;
import alephinfinity1.forgeblock.init.ModContainerTypes;
import alephinfinity1.forgeblock.init.ModEntities;
import alephinfinity1.forgeblock.init.ModParticles;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.particle.ParticleManager;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntitySpawnPlacementRegistry.PlacementType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.Heightmap.Type;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = ForgeBlock.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEventBusSubscriber {

    public static final Minecraft mc = Minecraft.getInstance();
    
    @SubscribeEvent
    public static void spawnEntities(FMLLoadCompleteEvent event) {
        EntitySpawnPlacementRegistry.register(ModEntities.LV50_ENDERMAN.get(), PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canMonsterSpawn);
        EntitySpawnPlacementRegistry.register(ModEntities.LV42_ENDERMAN.get(), PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canMonsterSpawn);
        EntitySpawnPlacementRegistry.register(ModEntities.LV45_ENDERMAN.get(), PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canMonsterSpawn);
        EntitySpawnPlacementRegistry.register(ModEntities.VOIDLING_MANIAC.get(), PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canMonsterSpawn);
        EntitySpawnPlacementRegistry.register(ModEntities.VOIDLING_DEVOTEE.get(), PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canMonsterSpawn);
        EntitySpawnPlacementRegistry.register(ModEntities.ZEALOT.get(), PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canMonsterSpawn);
        EntitySpawnPlacementRegistry.register(ModEntities.ATONED_REVENANT.get(), PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canMonsterSpawn);
        EntitySpawnPlacementRegistry.register(ModEntities.YOUNG_LOST_ADVENTURER.get(), PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canMonsterSpawn);
        EntitySpawnPlacementRegistry.register(ModEntities.CRYPT_GHOUL.get(), PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canMonsterSpawn);
        EntitySpawnPlacementRegistry.register(ModEntities.GOLDEN_GHOUL.get(), PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canMonsterSpawn);
        EntitySpawnPlacementRegistry.register(ModEntities.PACK_ENFORCER.get(), PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
        EntitySpawnPlacementRegistry.register(ModEntities.PACK_SPIRIT.get(), PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES,AnimalEntity::canAnimalSpawn);
        EntitySpawnPlacementRegistry.register(ModEntities.LV15_WOLF.get(), PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
        EntitySpawnPlacementRegistry.register(ModEntities.OLD_WOLF.get(), PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
        EntitySpawnPlacementRegistry.register(ModEntities.SOUL_OF_THE_ALPHA.get(), PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
        EntitySpawnPlacementRegistry.register(ModEntities.LV1_ZOMBIE.get(), PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canMonsterSpawn);
        EntitySpawnPlacementRegistry.register(ModEntities.LV1_ZOMBIE_VILLAGER.get(), PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canMonsterSpawn);

        for (Biome biome : ForgeRegistries.BIOMES) {

            // Some Spicy in the nether
            if (biome.getCategory() == Biome.Category.NETHER) { 
                biome.getSpawns(EntityClassification.MONSTER)
                        .add(new Biome.SpawnListEntry(ModEntities.ATONED_REVENANT.get(), 1, 1, 1));
                biome.getSpawns(EntityClassification.MONSTER)
                        .add(new Biome.SpawnListEntry(ModEntities.LV50_ENDERMAN.get(), 40, 1, 6));
                biome.getSpawns(EntityClassification.MONSTER)
                        .add(new Biome.SpawnListEntry(ModEntities.YOUNG_LOST_ADVENTURER.get(), 1, 1, 1));
                
            }
            // Mega spice in the end
            else if (biome.getCategory() == Biome.Category.THEEND) {
                biome.getSpawns(EntityClassification.MONSTER)
                        .add(new Biome.SpawnListEntry(ModEntities.LV50_ENDERMAN.get(), 8, 1, 3));
                biome.getSpawns(EntityClassification.MONSTER)
                        .add(new Biome.SpawnListEntry(ModEntities.LV42_ENDERMAN.get(), 10, 1, 5));
                biome.getSpawns(EntityClassification.MONSTER)
                        .add(new Biome.SpawnListEntry(ModEntities.LV45_ENDERMAN.get(), 20, 1, 7));
                biome.getSpawns(EntityClassification.MONSTER)
                        .add(new Biome.SpawnListEntry(ModEntities.VOIDLING_MANIAC.get(), 1, 1, 1));
                biome.getSpawns(EntityClassification.MONSTER)
                        .add(new Biome.SpawnListEntry(ModEntities.VOIDLING_DEVOTEE.get(), 1, 1, 2));
                biome.getSpawns(EntityClassification.MONSTER)
                        .add(new Biome.SpawnListEntry(ModEntities.ZEALOT.get(), 1, 5, 7));
            }
            // Overwold spiceness
            else {
                if (biome.getCategory() != Biome.Category.OCEAN && biome.getCategory() != Biome.Category.RIVER && biome.getCategory() != Biome.Category.JUNGLE) {
                    biome.getSpawns(EntityClassification.MONSTER)
                        .add(new Biome.SpawnListEntry(ModEntities.LV1_ZOMBIE.get(), 100, 5, 6));
                    biome.getSpawns(EntityClassification.MONSTER)
                        .add(new Biome.SpawnListEntry(ModEntities.LV1_ZOMBIE_VILLAGER.get(), 70, 2, 3));
                    biome.getSpawns(EntityClassification.MONSTER)
                        .add(new Biome.SpawnListEntry(ModEntities.CRYPT_GHOUL.get(), 50, 3, 6));
                    biome.getSpawns(EntityClassification.MONSTER)
                        .add(new Biome.SpawnListEntry(ModEntities.GOLDEN_GHOUL.get(), 20, 1, 1));
                    biome.getSpawns(EntityClassification.MONSTER)
                        .add(new Biome.SpawnListEntry(ModEntities.DASHER_SPIDER.get(), 15, 1, 3));
                    biome.getSpawns(EntityClassification.MONSTER)
                        .add(new Biome.SpawnListEntry(ModEntities.TARANTULA_BEAST.get(), 3, 1, 3));
                    biome.getSpawns(EntityClassification.MONSTER)
                        .add(new Biome.SpawnListEntry(ModEntities.TARANTULA_VERMIN.get(), 15, 1, 5));
                    biome.getSpawns(EntityClassification.MONSTER)
                        .add(new Biome.SpawnListEntry(ModEntities.PACK_ENFORCER.get(), 10, 2, 3));
                    biome.getSpawns(EntityClassification.MONSTER)
                        .add(new Biome.SpawnListEntry(ModEntities.PACK_SPIRIT.get(), 10, 2, 3));
                    biome.getSpawns(EntityClassification.MONSTER)
                        .add(new Biome.SpawnListEntry(ModEntities.LV15_WOLF.get(), 10, 2, 3));
                    biome.getSpawns(EntityClassification.MONSTER)
                        .add(new Biome.SpawnListEntry(ModEntities.OLD_WOLF.get(), 5, 1, 1));
                    biome.getSpawns(EntityClassification.MONSTER)
                        .add(new Biome.SpawnListEntry(ModEntities.SOUL_OF_THE_ALPHA.get(), 1, 1, 1));
                }
            }
        }
    }
    /**
     * XXX The anvil register part is wonky.
     */
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        //Entities
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.KALHUIKI_TRIBE_MEMBER.get(), (erm) -> new RenderPlayerMob<TribeMember01Entity, PlayerModel<TribeMember01Entity>>(erm, new PlayerModel<>(0, false), 0));
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.YOUNG_LOST_ADVENTURER.get(), (erm) -> new RenderPlayerMob<YoungLostAdventurerEntity, PlayerModel<YoungLostAdventurerEntity>>(erm, new PlayerModel<>(0, false), 0));
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.LV1_ZOMBIE.get(), ZombieRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.LV42_ENDERMAN.get(), EndermanRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.LV45_ENDERMAN.get(), EndermanRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.LV50_ENDERMAN.get(), EndermanRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.TANK_ZOMBIE.get(), ZombieRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.ATONED_CHAMPION.get(), ZombieRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.ATONED_REVENANT.get(), ZombieRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.DEFORMED_REVENANT.get(), ZombieRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.REVENANT_CHAMPION.get(), ZombieRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.REVENANT_SYCOPHANT.get(), ZombieRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.SPLITTER_SPIDER.get(), SpiderRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.DASHER_SPIDER.get(), SpiderRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.TARANTULA_BEAST.get(), SpiderRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.TARANTULA_VERMIN.get(), SpiderRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.MUTANT_TARANTULA.get(), SpiderRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.PACK_ENFORCER.get(), WolfRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.SVEN_ALPHA.get(), WolfRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.SVEN_FOLLOWER.get(), WolfRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.VOIDLING_DEVOTEE.get(), EndermanRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.VOIDLING_MANIAC.get(), EndermanRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.VOIDLING_RADICAL.get(), EndermanRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.LAPIS_ZOMBIE.get(), ZombieRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.ZEALOT.get(), EndermanRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.SPECIAL_ZEALOT.get(), EndermanRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.FB_EXPERIENCE_BOTTLE.get(), (erm) -> new SpriteRenderer<FBExperienceBottleEntity>(erm, mc.getItemRenderer()));
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.LV15_WOLF.get(), WolfRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.OLD_WOLF.get(), WolfRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.PACK_SPIRIT.get(), WolfRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.SOUL_OF_THE_ALPHA.get(), WolfRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.HOWLING_SPIRIT.get(), WolfRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.LV1_ZOMBIE_VILLAGER.get(), (erm) -> new ZombieVillagerRenderer(erm, mc.resourceManager));
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.CRYPT_GHOUL.get(), ZombieRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.GOLDEN_GHOUL.get(), ZombieRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.FB_ARROW.get(), erm -> new ArrowRenderer<FBArrowEntity>(erm) {
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
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
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
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @SubscribeEvent
    public static void onParticleFactoryRegister(ParticleFactoryRegisterEvent event) {
        ParticleManager pm = ForgeBlock.MINECRAFT.particles;
        pm.registerFactory(ModParticles.NUMERIC_DAMAGE.get(), new NumericDamageIndicatorParticle.Factory());
    }
}
