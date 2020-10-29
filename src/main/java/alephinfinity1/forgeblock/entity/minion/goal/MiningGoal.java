package alephinfinity1.forgeblock.entity.minion.goal;

import alephinfinity1.forgeblock.entity.minion.MinionEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;

import java.util.Objects;
import java.util.Random;

public class MiningGoal extends Goal {

  private static final Minecraft mc = Minecraft.getInstance();

  private static MinionEntity minionEntity;

  float tick = 0;

  int z = 2;
  int x = -2;

  public MiningGoal(MinionEntity entity) {
    minionEntity = entity;
  }


  @Override
  public boolean shouldExecute() {
    return true;
  }

  @Override
  public void tick() {
    tick++;
    if (tick == 300) {
      blockPlace(
          new BlockPos(randomNumb(x, z) + minionEntity.getPosX(), minionEntity.getPosY() - 1,
              randomNumb(x, z) + minionEntity.getPosZ()), Blocks.DIAMOND_BLOCK);
    }
    if (tick == 600) {
      removeBlock(
          new BlockPos(randomNumb(x, z) + minionEntity.getPosX() - 1, minionEntity.getPosY(),
              randomNumb(x, z) + minionEntity.getPosZ()));
      tick = 0;
    }
  }


  /**
   * Sets the block to any block in game.
   *
   * @param blockPlace Blocks
   * @param blockType  Blocks
   */
  public static void blockPlace(BlockPos blockPlace, Block blockType) {

    if (Minecraft.getInstance().getIntegratedServer() == null) {
      return;
    }

    if (Minecraft.getInstance().getIntegratedServer()
        .getWorld(DimensionType.OVERWORLD) == null) {
      return;
    }
    World world = Minecraft.getInstance().getIntegratedServer()
        .getWorld(DimensionType.OVERWORLD);

    world.setBlockState(blockPlace, blockType.getDefaultState());
  }


  /**
   * deletes the block from the world. Returns the block that has been removed.
   *
   * @param blockRemove
   * @return currentBlock
   */
  public static Block removeBlock(BlockPos blockRemove) {
    ServerWorld world = Objects.requireNonNull(Minecraft.getInstance().getIntegratedServer())
        .getWorld(DimensionType.OVERWORLD);

    BlockState blockState = world.getBlockState(blockRemove);
    Block currentBlock = blockState.getBlock();
    if (!(minionEntity.getPosX() == blockRemove.getX() && minionEntity.getPosZ() == blockRemove
        .getZ())) {
      world.destroyBlock(blockRemove, false);
    }
    return currentBlock;
  }

  public static double randomNumb(int min, int max) {
    return Math.random() * (max - min) + min;
  }

}
