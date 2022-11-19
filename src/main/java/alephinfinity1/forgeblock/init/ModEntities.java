package alephinfinity1.forgeblock.init;

import alephinfinity1.forgeblock.ForgeBlock;
import alephinfinity1.forgeblock.entity.*;
import alephinfinity1.forgeblock.entity.minion.MinionEntity;
import alephinfinity1.forgeblock.entity.minion.impl.CoalMinion;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntities {

        public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, ForgeBlock.MOD_ID);
        public static final DeferredRegister<EntityType<?>> OVERRIDE = DeferredRegister.create(ForgeRegistries.ENTITIES, ForgeBlock.MINECRAFT_ID);
        
        public static final EntityType<AtonedChampionEntity> ATONED_CHAMPION_TYPE = EntityType.Builder.create(
                AtonedChampionEntity::new,
                EntityClassification.MONSTER).size(0.6f, 2.0f).build(new ResourceLocation(ForgeBlock.MOD_ID, "atoned_champion").toString());

        public static final EntityType<AtonedRevenantEntity> ATONED_REVENANT_TYPE = EntityType.Builder.create(
                AtonedRevenantEntity::new,
                EntityClassification.MONSTER).size(0.6f, 2.0f).build(new ResourceLocation(ForgeBlock.MOD_ID, "atoned_revenant").toString());
        
        public static final EntityType<DeformedRevenantEntity> DEFORMED_REVENANT_TYPE = EntityType.Builder.create(
                DeformedRevenantEntity::new,
                EntityClassification.MONSTER).size(0.6f, 2.0f).build(new ResourceLocation(ForgeBlock.MOD_ID, "deformed_revenant").toString());
                
        public static final EntityType<RevenantChampionEntity> REVENANT_CHAMPION_TYPE = EntityType.Builder.create(
                RevenantChampionEntity::new,
                EntityClassification.MONSTER).size(0.6f, 2.0f).build(new ResourceLocation(ForgeBlock.MOD_ID, "revenant_champion").toString());
                
        public static final EntityType<RevenantSycophantEntity> REVENANT_SYCOPHANT_TYPE = EntityType.Builder.create(
                RevenantSycophantEntity::new,
                EntityClassification.MONSTER).size(0.6f, 2.0f).build(new ResourceLocation(ForgeBlock.MOD_ID, "revenant_sycophant").toString());

        public static final EntityType<SvenFollowerEntity> SVEN_FOLLOWER_TYPE = EntityType.Builder.create(
                SvenFollowerEntity::new,
                EntityClassification.MONSTER).size(0.6f, 0.9f).build(new ResourceLocation(ForgeBlock.MOD_ID, "sven_follower").toString());
        
        public static final EntityType<SvenAlphaEntity> SVEN_ALPHA_TYPE = EntityType.Builder.create(
                SvenAlphaEntity::new,
                EntityClassification.MONSTER).size(0.6f, 0.9f).build(new ResourceLocation(ForgeBlock.MOD_ID, "sven_alpha").toString());

        public static final EntityType<PackEnforcerEntity> PACK_ENFORCER_TYPE = EntityType.Builder.create(
                PackEnforcerEntity::new,
                EntityClassification.MONSTER).size(0.6f, 0.9f).build(new ResourceLocation(ForgeBlock.MOD_ID, "pack_enforcer").toString());

        public static final EntityType<TarantulaBeastEntity> TARANTULA_BEAST_TYPE = EntityType.Builder.create(
                TarantulaBeastEntity::new,
                EntityClassification.MONSTER).size(2.0f, 0.9f).build(new ResourceLocation(ForgeBlock.MOD_ID, "tarantula_beast").toString());

        public static final EntityType<WeaverSpiderEntity> WEAVER_SPIDER_TYPE = EntityType.Builder.create(
                WeaverSpiderEntity::new,
                EntityClassification.MONSTER).size(2.0f, 0.9f).build(new ResourceLocation(ForgeBlock.MOD_ID, "weaver_spider_beast").toString());

        public static final EntityType<TarantulaVerminEntity> TARANTULA_VERMIN_TYPE = EntityType.Builder.create(
                TarantulaVerminEntity::new,
                EntityClassification.MONSTER).size(2.0f, 0.9f).build(new ResourceLocation(ForgeBlock.MOD_ID, "tarantula_vermin").toString());

        public static final EntityType<MutantTarantulaEntity> MUTANT_TARANTULA_TYPE = EntityType.Builder.create(
                MutantTarantulaEntity::new,
                EntityClassification.MONSTER).size(2.0f, 0.9f).build(new ResourceLocation(ForgeBlock.MOD_ID, "mutant_tarantula").toString());

        public static final EntityType<VoidlingDevoteeEntity> VOIDLING_DEVOTEE_TYPE = EntityType.Builder.create(
                VoidlingDevoteeEntity::new,
                EntityClassification.MONSTER).size(0.6f, 2.9f).build(new ResourceLocation(ForgeBlock.MOD_ID, "voidling_devotee").toString());

        public static final EntityType<VoidlingManiacEntity> VOIDLING_MANIAC_TYPE = EntityType.Builder.create(
                VoidlingManiacEntity::new,
                EntityClassification.MONSTER).size(0.6f, 2.9f).build(new ResourceLocation(ForgeBlock.MOD_ID, "voidling_maniac").toString());

        public static final EntityType<VoidlingRadicalEntity> VOIDLING_RADICAL_TYPE = EntityType.Builder.create(
                VoidlingRadicalEntity::new,
                EntityClassification.MONSTER).size(0.6f, 2.9f).build(new ResourceLocation(ForgeBlock.MOD_ID, "voidling_radical").toString());

        public static final EntityType<Lv1ZombieEntity> LV1_ZOMBIE_TYPE = EntityType.Builder.create(
                Lv1ZombieEntity::new,
                EntityClassification.MONSTER).size(0.6f, 2.0f).build(new ResourceLocation(ForgeBlock.MOD_ID, "lv1_zombie").toString());

        public static final EntityType<Lv42EndermanEntity> LV42_ENDERMAN_TYPE = EntityType.Builder.create(
                Lv42EndermanEntity::new,
                EntityClassification.MONSTER).size(0.6f, 2.9f).build(new ResourceLocation(ForgeBlock.MOD_ID, "lv42_enderman").toString());

        public static final EntityType<Lv45EndermanEntity> LV45_ENDERMAN_TYPE = EntityType.Builder.create(
                Lv45EndermanEntity::new,
                EntityClassification.MONSTER).size(0.6f, 2.9f).build(new ResourceLocation(ForgeBlock.MOD_ID, "lv45_enderman").toString());

        public static final EntityType<Lv50EndermanEntity> LV50_ENDERMAN_TYPE = EntityType.Builder.create(
                Lv50EndermanEntity::new,
                EntityClassification.MONSTER).size(0.6f, 2.9f).build(new ResourceLocation(ForgeBlock.MOD_ID, "lv50_enderman").toString());

        public static final EntityType<LapisZombieEntity> LAPIS_ZOMBIE_TYPE = EntityType.Builder.create(
                LapisZombieEntity::new,
                EntityClassification.MONSTER).size(0.6f, 2.0f).build(new ResourceLocation(ForgeBlock.MOD_ID, "lapis_zombie").toString());

        public static final EntityType<DasherSpiderEntity> DASHER_SPIDER_TYPE = EntityType.Builder.create(
                DasherSpiderEntity::new,
                EntityClassification.MONSTER).size(2.0f, 0.9f).build(new ResourceLocation(ForgeBlock.MOD_ID, "dasher_spider").toString());

        public static final EntityType<SplitterSpiderEntity> SPLITTER_SPIDER_TYPE = EntityType.Builder.create(
                SplitterSpiderEntity::new,
                EntityClassification.MONSTER).size(2.0f, 0.9f).build(new ResourceLocation(ForgeBlock.MOD_ID, "splitter_spider").toString());
        
        public static final EntityType<TankZombieEntity> TANK_ZOMBIE_TYPE = EntityType.Builder.create(
                TankZombieEntity::new,
                EntityClassification.MONSTER).size(0.6f, 2.0f).build(new ResourceLocation(ForgeBlock.MOD_ID, "tank_zombie").toString());

        public static final EntityType<ZealotEntity> ZEALOT_TYPE = EntityType.Builder.create(
                ZealotEntity::new,
                EntityClassification.MONSTER).size(0.6f, 2.9f).build(new ResourceLocation(ForgeBlock.MOD_ID, "zealot").toString());

        public static final EntityType<SpecialZealotEntity> SPECIAL_ZEALOT_TYPE = EntityType.Builder.create(
                SpecialZealotEntity::new,
                EntityClassification.MONSTER).size(0.6f, 2.9f).build(new ResourceLocation(ForgeBlock.MOD_ID, "special_zealot").toString());

        public static final EntityType<FBExperienceBottleEntity> FB_EXPERIENCE_BOTTLE_TYPE = EntityType.Builder.<FBExperienceBottleEntity>create(
                FBExperienceBottleEntity::new,
                EntityClassification.MISC).size(0.25f, 0.25f).build(new ResourceLocation(ForgeBlock.MOD_ID, "fb_experience_bottle").toString());
        
        public static final EntityType<Lv15WolfEntity> LV15_WOLF_TYPE = EntityType.Builder.create(
                Lv15WolfEntity::new,
                EntityClassification.MONSTER).size(0.6f, 0.9f).build(new ResourceLocation(ForgeBlock.MOD_ID, "lv15_wolf").toString());
        
        public static final EntityType<OldWolfEntity> OLD_WOLF_TYPE = EntityType.Builder.create(
                OldWolfEntity::new,
                EntityClassification.MONSTER).size(0.6f, 0.9f).build(new ResourceLocation(ForgeBlock.MOD_ID, "old_wolf").toString());
        
        public static final EntityType<PackSpiritEntity> PACK_SPIRIT_TYPE = EntityType.Builder.create(
                PackSpiritEntity::new,
                EntityClassification.MONSTER).size(0.6f, 0.9f).build(new ResourceLocation(ForgeBlock.MOD_ID, "pack_spirit").toString());

        public static final EntityType<SoulOfTheAlphaEntity> SOUL_OF_THE_ALPHA_TYPE = EntityType.Builder.create(
                SoulOfTheAlphaEntity::new,
                EntityClassification.MONSTER).size(0.6f, 0.9f).build(new ResourceLocation(ForgeBlock.MOD_ID, "soul_of_the_alpha").toString());

        public static final EntityType<HowlingSpiritEntity> HOWLING_SPIRIT_TYPE = EntityType.Builder.create(
                HowlingSpiritEntity::new,
                EntityClassification.MONSTER).size(0.6f, 0.9f).build(new ResourceLocation(ForgeBlock.MOD_ID, "howling_spirit_wolf").toString());

        public static final EntityType<Lv1ZombieVillagerEntity> LV1_ZOMBIE_VILLAGER_TYPE = EntityType.Builder.create(
                Lv1ZombieVillagerEntity::new,
                EntityClassification.MONSTER).size(0.6f, 2.0f).build(new ResourceLocation(ForgeBlock.MOD_ID, "lv1_zombie_villager").toString());
        
        public static final EntityType<CryptGhoulEntity> CRYPT_GHOUL_TYPE = EntityType.Builder.create(
                CryptGhoulEntity::new,
                EntityClassification.MONSTER).size(0.6f, 2.0f).build(new ResourceLocation(ForgeBlock.MOD_ID, "crypt_ghoul").toString());
        
        public static final EntityType<GoldenGhoulEntity> GOLDEN_GHOUL_TYPE = EntityType.Builder.create(
                GoldenGhoulEntity::new,
                EntityClassification.MONSTER).size(0.6f, 2.0f).build(new ResourceLocation(ForgeBlock.MOD_ID, "golden_ghoul").toString());

        public static final EntityType<MinionEntity> MINION_TYPE = EntityType.Builder.<MinionEntity>create(
                CoalMinion::new,
                EntityClassification.MISC).size(0.50f, 0.50f).build(new ResourceLocation(ForgeBlock.MOD_ID, "minion").toString());

        public static final EntityType<FBArrowEntity> FB_ARROW_TYPE = EntityType.Builder.<FBArrowEntity>create(
                FBArrowEntity::new,
                EntityClassification.MISC).size(0.1f, 0.1f).build(new ResourceLocation(ForgeBlock.MOD_ID, "arrow").toString());

        public static final EntityType<Lv1SpiderEntity> LV1_SPIDER_TYPE = EntityType.Builder.<Lv1SpiderEntity>create(
                Lv1SpiderEntity::new,
                EntityClassification.MONSTER).size(2.0f, 0.9f).build(new ResourceLocation(ForgeBlock.MOD_ID, "lv1_spider").toString());

        public static final EntityType<Lv1EndermanEntity> LV1_ENDERMAN_TYPE = EntityType.Builder.<Lv1EndermanEntity>create(
                Lv1EndermanEntity::new,
                EntityClassification.MONSTER).size(0.6f, 2.9f).build(new ResourceLocation(ForgeBlock.MOD_ID, "lv1_enderman").toString());

        public static final EntityType<Lv1SlimeEntity> LV1_SLIME_TYPE = EntityType.Builder.<Lv1SlimeEntity>create(
                Lv1SlimeEntity::new,
                EntityClassification.MONSTER).size(1.0f, 1.0f).build(new ResourceLocation(ForgeBlock.MOD_ID, "lv1_slime").toString());

        public static final EntityType<RedstonePigmanEntity> REDSTONE_PIGMAN_TYPE = EntityType.Builder.create(
                RedstonePigmanEntity::new,
                EntityClassification.MONSTER).size(0.6f, 2.0f).build(new ResourceLocation(ForgeBlock.MOD_ID, "redstone_pigman").toString());
  
        public static final EntityType<YoungLostAdventurerEntity> YOUNG_LOST_ADVENTURER_TYPE = EntityType.Builder.create(
                YoungLostAdventurerEntity::new,
                EntityClassification.MONSTER).size(0.5f, 2.0f).build(new ResourceLocation(ForgeBlock.MOD_ID, "young_lost_adventurer").toString());

        public static final EntityType<TribeMember01Entity> TRIBE_MEMBER_TYPE = EntityType.Builder.create(
                TribeMember01Entity::new,
                EntityClassification.CREATURE).size(0.5f, 2.0f).build(new ResourceLocation(ForgeBlock.MOD_ID, "kalhuiki_tribe_member").toString());


        
        public static final RegistryObject<EntityType<RedstonePigmanEntity>> REDSTONE_PIGMAN = ENTITIES.register("redstone_pigman", () -> REDSTONE_PIGMAN_TYPE);
        public static final RegistryObject<EntityType<TribeMember01Entity>> KALHUIKI_TRIBE_MEMBER = ENTITIES.register("kalhuiki_tribe_member", () -> TRIBE_MEMBER_TYPE);
        public static final RegistryObject<EntityType<YoungLostAdventurerEntity>> YOUNG_LOST_ADVENTURER = ENTITIES.register("young_lost_adventurer", () -> YOUNG_LOST_ADVENTURER_TYPE);
        public static final RegistryObject<EntityType<Lv1SlimeEntity>> LV1_SLIME = ENTITIES.register("lv1_slime", () -> LV1_SLIME_TYPE);
        public static final RegistryObject<EntityType<Lv1EndermanEntity>> LV1_ENDERMAN = ENTITIES.register("lv1_enderman", () -> LV1_ENDERMAN_TYPE);
        public static final RegistryObject<EntityType<Lv1SpiderEntity>> LV1_SPIDER = ENTITIES.register("lv1_spider", () -> LV1_SPIDER_TYPE);
        public static final RegistryObject<EntityType<Lv1ZombieEntity>> LV1_ZOMBIE = ENTITIES.register("lv1_zombie", () -> LV1_ZOMBIE_TYPE);
        public static final RegistryObject<EntityType<TankZombieEntity>> TANK_ZOMBIE = ENTITIES.register("tank_zombie", () -> TANK_ZOMBIE_TYPE);
        public static final RegistryObject<EntityType<LapisZombieEntity>> LAPIS_ZOMBIE = ENTITIES.register("lapis_zombie", () -> LAPIS_ZOMBIE_TYPE);
        public static final RegistryObject<EntityType<ZealotEntity>> ZEALOT = ENTITIES.register("zealot", () -> ZEALOT_TYPE);
        public static final RegistryObject<EntityType<Lv42EndermanEntity>> LV42_ENDERMAN = ENTITIES.register("lv42_enderman", () -> LV42_ENDERMAN_TYPE);
        public static final RegistryObject<EntityType<Lv45EndermanEntity>> LV45_ENDERMAN = ENTITIES.register("lv45_enderman", () -> LV45_ENDERMAN_TYPE);
        public static final RegistryObject<EntityType<Lv50EndermanEntity>> LV50_ENDERMAN = ENTITIES.register("lv50_enderman", () -> LV50_ENDERMAN_TYPE);
        public static final RegistryObject<EntityType<SpecialZealotEntity>> SPECIAL_ZEALOT = ENTITIES.register("special_zealot", () -> SPECIAL_ZEALOT_TYPE);
        public static final RegistryObject<EntityType<FBExperienceBottleEntity>> FB_EXPERIENCE_BOTTLE = ENTITIES.register("fb_experience_bottle", () -> FB_EXPERIENCE_BOTTLE_TYPE);
        public static final RegistryObject<EntityType<Lv15WolfEntity>> LV15_WOLF = ENTITIES.register("lv15_wolf", () -> LV15_WOLF_TYPE);
        public static final RegistryObject<EntityType<OldWolfEntity>> OLD_WOLF = ENTITIES.register("old_wolf", () -> OLD_WOLF_TYPE);
        public static final RegistryObject<EntityType<PackSpiritEntity>> PACK_SPIRIT = ENTITIES.register("pack_spirit", () -> PACK_SPIRIT_TYPE);
        public static final RegistryObject<EntityType<SoulOfTheAlphaEntity>> SOUL_OF_THE_ALPHA = ENTITIES.register("soul_of_the_alpha", () -> SOUL_OF_THE_ALPHA_TYPE);
        public static final RegistryObject<EntityType<HowlingSpiritEntity>> HOWLING_SPIRIT = ENTITIES.register("howling_spirit", () -> HOWLING_SPIRIT_TYPE);
        public static final RegistryObject<EntityType<Lv1ZombieVillagerEntity>> LV1_ZOMBIE_VILLAGER = ENTITIES.register("lv1_zombie_villager", () -> LV1_ZOMBIE_VILLAGER_TYPE);
        public static final RegistryObject<EntityType<CryptGhoulEntity>> CRYPT_GHOUL = ENTITIES.register("crypt_ghoul", () -> CRYPT_GHOUL_TYPE);
        public static final RegistryObject<EntityType<GoldenGhoulEntity>> GOLDEN_GHOUL = ENTITIES.register("golden_ghoul", () -> GOLDEN_GHOUL_TYPE);
        public static final RegistryObject<EntityType<FBArrowEntity>> FB_ARROW = ENTITIES.register("arrow", () -> FB_ARROW_TYPE);
        public static final RegistryObject<EntityType<AtonedChampionEntity>> ATONED_CHAMPION = ENTITIES.register("atoned_champion", () -> ATONED_CHAMPION_TYPE);
        public static final RegistryObject<EntityType<AtonedRevenantEntity>> ATONED_REVENANT = ENTITIES.register("atoned_revenant", () -> ATONED_REVENANT_TYPE);
        public static final RegistryObject<EntityType<DeformedRevenantEntity>> DEFORMED_REVENANT = ENTITIES.register("deformed_revenant", () -> DEFORMED_REVENANT_TYPE);
        public static final RegistryObject<EntityType<RevenantChampionEntity>> REVENANT_CHAMPION = ENTITIES.register("revenant_champion", () -> REVENANT_CHAMPION_TYPE);
        public static final RegistryObject<EntityType<RevenantSycophantEntity>> REVENANT_SYCOPHANT = ENTITIES.register("revenant_sycophant", () -> REVENANT_SYCOPHANT_TYPE);
        public static final RegistryObject<EntityType<TarantulaBeastEntity>> TARANTULA_BEAST = ENTITIES.register("tarantula_beast", () -> TARANTULA_BEAST_TYPE);
        public static final RegistryObject<EntityType<TarantulaVerminEntity>> TARANTULA_VERMIN = ENTITIES.register("tarantula_vermin", () -> TARANTULA_VERMIN_TYPE);
        public static final RegistryObject<EntityType<WeaverSpiderEntity>> WEAVER_SPIDER = ENTITIES.register("weaver_spider", () -> WEAVER_SPIDER_TYPE);
        public static final RegistryObject<EntityType<MutantTarantulaEntity>> MUTANT_TARANTULA = ENTITIES.register("mutant_tarantula", () -> MUTANT_TARANTULA_TYPE);
        public static final RegistryObject<EntityType<SplitterSpiderEntity>> SPLITTER_SPIDER = ENTITIES.register("splitter_spider", () -> SPLITTER_SPIDER_TYPE);
        public static final RegistryObject<EntityType<VoidlingDevoteeEntity>> VOIDLING_DEVOTEE = ENTITIES.register("voidling_devotee", () -> VOIDLING_DEVOTEE_TYPE);
        public static final RegistryObject<EntityType<VoidlingManiacEntity>> VOIDLING_MANIAC = ENTITIES.register("voidling_maniac", () -> VOIDLING_MANIAC_TYPE);
        public static final RegistryObject<EntityType<VoidlingRadicalEntity>> VOIDLING_RADICAL = ENTITIES.register("voidling_radical", () -> VOIDLING_RADICAL_TYPE);
        public static final RegistryObject<EntityType<DasherSpiderEntity>> DASHER_SPIDER = ENTITIES.register("dasher_spider", () -> DASHER_SPIDER_TYPE);
        public static final RegistryObject<EntityType<PackEnforcerEntity>> PACK_ENFORCER = ENTITIES.register("pack_enforcer", () -> PACK_ENFORCER_TYPE);
        public static final RegistryObject<EntityType<SvenAlphaEntity>> SVEN_ALPHA = ENTITIES.register("sven_alpha", () -> SVEN_ALPHA_TYPE);
        public static final RegistryObject<EntityType<SvenFollowerEntity>> SVEN_FOLLOWER = ENTITIES.register("sven_follower", () -> SVEN_FOLLOWER_TYPE);
        
        public static final RegistryObject<EntityType<MinionEntity>> MINION = ENTITIES.register("minion", () -> MINION_TYPE);

}
