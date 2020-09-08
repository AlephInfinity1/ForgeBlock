package alephinfinity1.forgeblock.init;

import alephinfinity1.forgeblock.ForgeBlock;
import net.minecraft.entity.EntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntities {

	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, ForgeBlock.MOD_ID);
	public static final DeferredRegister<EntityType<?>> OVERRIDE = DeferredRegister.create(ForgeRegistries.ENTITIES, ForgeBlock.MINECRAFT_ID);
	
}
