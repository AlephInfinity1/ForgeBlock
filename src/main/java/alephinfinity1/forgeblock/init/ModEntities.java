package alephinfinity1.forgeblock.init;

import alephinfinity1.forgeblock.ForgeBlock;
import alephinfinity1.forgeblock.entity.CryptGhoulEntity;
import alephinfinity1.forgeblock.entity.FBExperienceBottleEntity;
import alephinfinity1.forgeblock.entity.GoldenGhoulEntity;
import alephinfinity1.forgeblock.entity.LapisZombieEntity;
import alephinfinity1.forgeblock.entity.Lv15WolfEntity;
import alephinfinity1.forgeblock.entity.Lv1ZombieEntity;
import alephinfinity1.forgeblock.entity.Lv1ZombieVillagerEntity;
import alephinfinity1.forgeblock.entity.OldWolfEntity;
import alephinfinity1.forgeblock.entity.SpecialZealotEntity;
import alephinfinity1.forgeblock.entity.ZealotEntity;
import alephinfinity1.forgeblock.entity.minion.MinionEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, ForgeBlock.MOD_ID);
    public static final DeferredRegister<EntityType<?>> OVERRIDE = DeferredRegister.create(ForgeRegistries.ENTITIES, ForgeBlock.MINECRAFT_ID);

    public static final EntityType<Lv1ZombieEntity> LV1_ZOMBIE_TYPE = EntityType.Builder.create(
            Lv1ZombieEntity::new,
            EntityClassification.MONSTER).size(0.6f, 1.95f).build(new ResourceLocation(ForgeBlock.MOD_ID, "lv1_zombie").toString());

    public static final EntityType<LapisZombieEntity> LAPIS_ZOMBIE_TYPE = EntityType.Builder.create(
            LapisZombieEntity::new,
            EntityClassification.MONSTER).size(0.6f, 1.95f).build(new ResourceLocation(ForgeBlock.MOD_ID, "lapis_zombie").toString());

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
    		EntityClassification.MONSTER).size(0.6f, 0.85f).build(new ResourceLocation(ForgeBlock.MOD_ID, "lv15_wolf").toString());
    
    public static final EntityType<OldWolfEntity> OLD_WOLF_TYPE = EntityType.Builder.create(
    		OldWolfEntity::new,
    		EntityClassification.MONSTER).size(0.6f, 0.85f).build(new ResourceLocation(ForgeBlock.MOD_ID, "old_wolf").toString());
    
    public static final EntityType<Lv1ZombieVillagerEntity> LV1_ZOMBIE_VILLAGER_TYPE = EntityType.Builder.create(
            Lv1ZombieVillagerEntity::new,
            EntityClassification.MONSTER).size(0.6f, 1.95f).build(new ResourceLocation(ForgeBlock.MOD_ID, "lv1_zombie_villager").toString());
    
    public static final EntityType<CryptGhoulEntity> CRYPT_GHOUL_TYPE = EntityType.Builder.create(
    		CryptGhoulEntity::new,
    		EntityClassification.MONSTER).size(0.6f, 1.95f).build(new ResourceLocation(ForgeBlock.MOD_ID, "crypt_ghoul").toString());
    
    public static final EntityType<GoldenGhoulEntity> GOLDEN_GHOUL_TYPE = EntityType.Builder.create(
    		GoldenGhoulEntity::new,
    		EntityClassification.MONSTER).size(0.6f, 1.95f).build(new ResourceLocation(ForgeBlock.MOD_ID, "golden_ghoul").toString());

    public static final EntityType<MinionEntity> MINION_TYPE = EntityType.Builder.<MinionEntity>create(
            MinionEntity::new,
            EntityClassification.MISC).size(0.50f, 0.50f).build(new ResourceLocation(ForgeBlock.MOD_ID, "minion").toString());

    public static final RegistryObject<EntityType<Lv1ZombieEntity>> LV1_ZOMBIE = ENTITIES.register("lv1_zombie", () -> LV1_ZOMBIE_TYPE);
    public static final RegistryObject<EntityType<LapisZombieEntity>> LAPIS_ZOMBIE = ENTITIES.register("lapis_zombie", () -> LAPIS_ZOMBIE_TYPE);
    public static final RegistryObject<EntityType<ZealotEntity>> ZEALOT = ENTITIES.register("zealot", () -> ZEALOT_TYPE);
    public static final RegistryObject<EntityType<SpecialZealotEntity>> SPECIAL_ZEALOT = ENTITIES.register("special_zealot", () -> SPECIAL_ZEALOT_TYPE);
    public static final RegistryObject<EntityType<FBExperienceBottleEntity>> FB_EXPERIENCE_BOTTLE = ENTITIES.register("fb_experience_bottle", () -> FB_EXPERIENCE_BOTTLE_TYPE);
    public static final RegistryObject<EntityType<Lv15WolfEntity>> LV15_WOLF = ENTITIES.register("lv15_wolf", () -> LV15_WOLF_TYPE);
    public static final RegistryObject<EntityType<OldWolfEntity>> OLD_WOLF = ENTITIES.register("old_wolf", () -> OLD_WOLF_TYPE);
    public static final RegistryObject<EntityType<Lv1ZombieVillagerEntity>> LV1_ZOMBIE_VILLAGER = ENTITIES.register("lv1_zombie_villager", () -> LV1_ZOMBIE_VILLAGER_TYPE);
    public static final RegistryObject<EntityType<CryptGhoulEntity>> CRYPT_GHOUL = ENTITIES.register("crypt_ghoul", () -> CRYPT_GHOUL_TYPE);
    public static final RegistryObject<EntityType<GoldenGhoulEntity>> GOLDEN_GHOUL = ENTITIES.register("golden_ghoul", () -> GOLDEN_GHOUL_TYPE);


    public static final RegistryObject<EntityType<MinionEntity>> MINION = ENTITIES.register("minion", () -> MINION_TYPE);

}
