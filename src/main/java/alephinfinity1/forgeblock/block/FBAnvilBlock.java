package alephinfinity1.forgeblock.block;

import javax.annotation.Nullable;

import alephinfinity1.forgeblock.container.FBRepairContainer;
import net.minecraft.block.AnvilBlock;
import net.minecraft.block.BlockState;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class FBAnvilBlock extends AnvilBlock {

	public FBAnvilBlock(Properties properties) {
		super(properties);	
	}
	
	@Nullable
	public INamedContainerProvider getContainer(BlockState state, World worldIn, BlockPos pos) {
		return new SimpleNamedContainerProvider((p_220272_2_, p_220272_3_, p_220272_4_) -> {
			return new FBRepairContainer(p_220272_2_, p_220272_3_, IWorldPosCallable.of(worldIn, pos));
		}, new TranslationTextComponent("container.repair"));
	}

}
