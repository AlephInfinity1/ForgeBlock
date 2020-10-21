package alephinfinity1.forgeblock.init;

import alephinfinity1.forgeblock.ForgeBlock;
import alephinfinity1.forgeblock.entity.FBExperienceBottleEntity;
import alephinfinity1.forgeblock.entity.LapisZombieEntity;
import alephinfinity1.forgeblock.entity.Lv1ZombieEntity;
import alephinfinity1.forgeblock.entity.SpecialZealotEntity;
import alephinfinity1.forgeblock.entity.ZealotEntity;
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
			
	
	public static final RegistryObject<EntityType<Lv1ZombieEntity>> LV1_ZOMBIE = ENTITIES.register("lv1_zombie", () -> LV1_ZOMBIE_TYPE);
	public static final RegistryObject<EntityType<LapisZombieEntity>> LAPIS_ZOMBIE = ENTITIES.register("lapis_zombie", () -> LAPIS_ZOMBIE_TYPE);
	public static final RegistryObject<EntityType<ZealotEntity>> ZEALOT = ENTITIES.register("zealot", () -> ZEALOT_TYPE);
	public static final RegistryObject<EntityType<SpecialZealotEntity>> SPECIAL_ZEALOT = ENTITIES.register("special_zealot", () -> SPECIAL_ZEALOT_TYPE);
	public static final RegistryObject<EntityType<FBExperienceBottleEntity>> FB_EXPERIENCE_BOTTLE = ENTITIES.register("fb_experience_bottle", () -> FB_EXPERIENCE_BOTTLE_TYPE);
	
}
