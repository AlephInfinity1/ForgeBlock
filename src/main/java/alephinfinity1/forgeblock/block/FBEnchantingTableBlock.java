package alephinfinity1.forgeblock.block;

import javax.annotation.Nullable;

import alephinfinity1.forgeblock.container.FBEnchantmentContainer;
import alephinfinity1.forgeblock.tileentity.FBEnchantingTableTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.EnchantingTableBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.INameable;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class FBEnchantingTableBlock extends EnchantingTableBlock {

	public FBEnchantingTableBlock(Properties builder) {
		super(builder);	
	}

	@Override
	@Nullable
	public INamedContainerProvider getContainer(BlockState state, World worldIn, BlockPos pos) {
		TileEntity tileentity = worldIn.getTileEntity(pos);
		if (tileentity instanceof FBEnchantingTableTileEntity) {
			ITextComponent itextcomponent = ((INameable)tileentity).getDisplayName();
			return new SimpleNamedContainerProvider((p_220147_2_, p_220147_3_, p_220147_4_) -> {
				return new FBEnchantmentContainer(p_220147_2_, p_220147_3_, IWorldPosCallable.of(worldIn, pos));
			}, itextcomponent);
		} else {
			return null;
		}
	}

	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new FBEnchantingTableTileEntity();
	}

	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		if (stack.hasDisplayName()) {
			TileEntity tileentity = worldIn.getTileEntity(pos);
			if (tileentity instanceof FBEnchantingTableTileEntity) {
				((FBEnchantingTableTileEntity) tileentity).setCustomName(stack.getDisplayName());
			}
		}
	}

}
