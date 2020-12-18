package alephinfinity1.forgeblock.init;

import alephinfinity1.forgeblock.ForgeBlock;
import alephinfinity1.forgeblock.block.FBAnvilBlock;
import alephinfinity1.forgeblock.block.FBEnchantingTableBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {
	
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ForgeBlock.MOD_ID);
	
	public static final DeferredRegister<Block> OVERRIDE = DeferredRegister.create(ForgeRegistries.BLOCKS, ForgeBlock.MINECRAFT_ID);
	
	public static final RegistryObject<Block> ANVIL = OVERRIDE.register("anvil", () -> new FBAnvilBlock(Block.Properties.create(Material.ANVIL, MaterialColor.IRON).hardnessAndResistance(5.0F, 1200.0F).sound(SoundType.ANVIL)));
	public static final RegistryObject<Block> CHIPPED_ANVIL = OVERRIDE.register("chipped_anvil", () -> new FBAnvilBlock(Block.Properties.create(Material.ANVIL, MaterialColor.IRON).hardnessAndResistance(5.0F, 1200.0F).sound(SoundType.ANVIL)));
	public static final RegistryObject<Block> DAMAGED_ANVIL = OVERRIDE.register("damaged_anvil", () -> new FBAnvilBlock(Block.Properties.create(Material.ANVIL, MaterialColor.IRON).hardnessAndResistance(5.0F, 1200.0F).sound(SoundType.ANVIL)));
	
	public static final RegistryObject<Block> ENCHANTING_TABLE = OVERRIDE.register("enchanting_table", () -> new FBEnchantingTableBlock(Block.Properties.create(Material.ROCK, MaterialColor.RED).hardnessAndResistance(5.0F, 1200.0F)));
}
