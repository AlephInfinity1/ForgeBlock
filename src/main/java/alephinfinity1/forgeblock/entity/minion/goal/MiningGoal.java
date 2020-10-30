package alephinfinity1.forgeblock.entity.minion.goal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import alephinfinity1.forgeblock.entity.minion.MinionEntity;
import alephinfinity1.forgeblock.entity.minion.inventory.MinionInv;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MiningGoal extends Goal {

	private MinionEntity minionEntity; //Should not be static, as only 1 minion would be active otherwise.
	private final MinionInv minionInv;
	private final Block block; //The type of block this minion mines.
	private final int actionDelay; //The delay, in ticks, between two actions.
	private final int radius; //The radius, in blocks, of the minion.

	long tick = 0;

	public MiningGoal(MinionEntity entity,
			MinionInv minionInv) {
		minionEntity = entity;
		this.minionInv = minionInv;
		this.block = Blocks.DIAMOND_BLOCK;
		this.actionDelay = 100;
		this.radius = 2;
	}
	
	public MiningGoal(MinionEntity entity,
			MinionInv minionInv, Block block) {
		minionEntity = entity;
		this.minionInv = minionInv;
		this.block = block;
		this.actionDelay = 100;
		this.radius = 2;
	}
	
	public MiningGoal(MinionEntity entity,
			MinionInv minionInv, Block block, int waitingTime) {
		minionEntity = entity;
		this.minionInv = minionInv;
		this.block = block;
		this.actionDelay = waitingTime;
		this.radius = 2;
	}


	@Override
	public boolean shouldExecute() {
		return true;
	}

	@Override
	public void tick() {
		tick++;
		if (tick % actionDelay == 0) {
			BlockPos pos = new BlockPos(minionEntity.getPosX(), minionEntity.getPosY() - 1,
					minionEntity.getPosZ());
			if(!blockPlace(pos)) { //Attempt to place a block. If unsuccessful, destroy a block instead.
				this.removeBlock(pos);
			}
		}
	}


	/**
	 * Sets the block to any block in game.
	 *
	 * @param blockPlace Blocks
	 * @param blockType  Blocks
	 * @return Whether the block is successfully placed or not.
	 */
	public boolean blockPlace(BlockPos blockPlace) {

		//Don't use integratedServer here cuz it's not multiplayer-compatible.
		/*
		if (Minecraft.getInstance().getIntegratedServer() == null) {
			return;
		}

		if (Minecraft.getInstance().getIntegratedServer()
				.getWorld(DimensionType.OVERWORLD) == null) {
			return;
		}
		*/

		List<BlockPos> positions = new ArrayList<>();
		for(int x = -radius; x <= radius; x++) {
			for(int z = -radius; z <= radius; z++) {
				positions.add(new BlockPos(blockPlace.getX() + x, blockPlace.getY(), blockPlace.getZ() + z));
			}
		}
		
		Collections.shuffle(positions);
		
		World world = minionEntity.getEntityWorld();

		for(BlockPos position : positions) {
			if(world.isAirBlock(position)) { //Only change block if block is air.
				world.setBlockState(position, this.block.getDefaultState());
				minionEntity.rotateTowards(
						Math.atan2(position.getZ() - minionEntity.getPosZ(), position.getX() - minionEntity.getPosX()),
						0);
				return true;
			}
		}
		
		return false;
	}


	/**
	 * deletes the block from the world. Returns the block that has been removed.
	 *
	 * @param blockRemove
	 * @return currentBlock
	 */
	public Block removeBlock(BlockPos blockRemove) {
		try {
			List<BlockPos> positions = new ArrayList<>();
			for(int x = -radius; x <= radius; x++) {
				for(int z = -radius; z <= radius; z++) {
					if(x == 0 && z == 0) continue; //Do not change the block directly underneath.
					positions.add(new BlockPos(blockRemove.getX() + x, blockRemove.getY(), blockRemove.getZ() + z));
				}
			}
			
			Collections.shuffle(positions);
			
			World world = minionEntity.getEntityWorld();
	
			for(PlayerEntity playerEntity : world.getPlayers()){
				minionEntity.minionInv.newMinionInv().openInventory(playerEntity);
			}
	
			for(BlockPos pos : positions) {
				BlockState blockState = world.getBlockState(pos);
				Block currentBlock = blockState.getBlock();
				if(currentBlock.equals(this.block)) {
					world.destroyBlock(pos, false);
					return currentBlock;
				}
			}
			return null; //Unsuccessful, there are no blocks of the specified type.
			
		} catch(NullPointerException ignored) {return null;}
	}

	public static double randomNumb(int min, int max) {
		return Math.random() * (max - min) + min;
	}

}
