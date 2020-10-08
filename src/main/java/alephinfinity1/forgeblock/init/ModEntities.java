package alephinfinity1.forgeblock.init;

import alephinfinity1.forgeblock.ForgeBlock;
import alephinfinity1.forgeblock.entity.Lv1ZombieEntity;
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
	
	public static final RegistryObject<EntityType<Lv1ZombieEntity>> LV1_ZOMBIE = ENTITIES.register("lv1_zombie", () -> LV1_ZOMBIE_TYPE);
	
}
