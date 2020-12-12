package alephinfinity1.forgeblock.init;

import alephinfinity1.forgeblock.ForgeBlock;
import alephinfinity1.forgeblock.tileentity.FBEnchantingTableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntityTypes {
	
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, ForgeBlock.MOD_ID);
	public static final DeferredRegister<TileEntityType<?>> OVERRIDE = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, ForgeBlock.MINECRAFT_ID);
	
	public static final RegistryObject<TileEntityType<FBEnchantingTableTileEntity>> ENCHANTING_TABLE = OVERRIDE.register("enchanting_table", () -> TileEntityType.Builder.create(FBEnchantingTableTileEntity::new, ModBlocks.ENCHANTING_TABLE.get()).build(null));

}
