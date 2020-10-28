package alephinfinity1.forgeblock.entity.minion.goal;

import alephinfinity1.forgeblock.entity.minion.MinionEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Objects;
import java.util.Random;

public class MiningGoal extends Goal {
    private static final Minecraft mc = Minecraft.getInstance();

    static MinionEntity minionEntity;
    private Random random = new Random();

    float tick = 0;

    public MiningGoal(MinionEntity entity) {
        this.minionEntity = entity;
    }

    @Override
    public boolean shouldExecute() {
        return true;
    }

    @Override
    public void tick() {
        tick++;
        if (tick == 300) {
            blockPlace(new BlockPos(random.nextInt(5) + minionEntity.getPosX(), minionEntity.getPosY(), random.nextInt(5) + minionEntity.getPosZ()), Blocks.DIAMOND_BLOCK);
        }
        if (tick == 600) {

            removeBlock(new BlockPos(random.nextInt(5) + minionEntity.getPosX(), minionEntity.getPosY(), random.nextInt(5) + minionEntity.getPosZ()));
            tick = 0;
        }
    }

    @Override
    public void startExecuting() {

    }


    /**
     * Sets the block to any block in game.
     *
     * @param blockPlace
     * @param blockType
     */
    public static void blockPlace(BlockPos blockPlace, Block blockType) {
        World world = Objects.requireNonNull(Minecraft.getInstance().getIntegratedServer()).getWorld(mc.player.dimension);


        if (minionEntity.getPosX() == blockPlace.getX() && minionEntity.getPosZ() == blockPlace.getZ()) {
            return;
        }

        world.setBlockState(blockPlace, blockType.getDefaultState());


    }


    /**
     * deletes the block from the world. Returns the block that has been removed.
     *
     * @param blockRemove
     * @return currentBlock
     */
    public static Block removeBlock(BlockPos blockRemove) {
        ServerWorld world = Minecraft.getInstance().getIntegratedServer().getWorld(mc.player.dimension);

        BlockState blockState = world.getBlockState(blockRemove);
        Block currentBlock = blockState.getBlock();
        if (!(minionEntity.getPosX() == blockRemove.getX() && minionEntity.getPosZ() == blockRemove.getZ())) {
            world.destroyBlock(blockRemove, false);
        }
        return currentBlock;
    }

}
