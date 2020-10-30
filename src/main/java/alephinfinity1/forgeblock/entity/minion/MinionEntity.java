package alephinfinity1.forgeblock.entity.minion;

import alephinfinity1.forgeblock.entity.minion.goal.MiningGoal;
import alephinfinity1.forgeblock.entity.minion.inventory.MinionInv;
import net.minecraft.block.Blocks;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;


public class MinionEntity extends CreatureEntity {


	public MinionInv minionInv;

	public MinionEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		this.minionInv = new MinionInv();
	}


	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new MiningGoal(this, minionInv, Blocks.COBBLESTONE, 1));
	}


	/**
	 * Disable killing with items. TODO: Make minions invinsable.
	 *
	 *
	 */
	@Override
	public boolean canBeAttackedWithItem() {
		return false;
	}

	@Override
	public boolean canBeCollidedWith() {
		return false;
	}
	
	@Override
	protected void collideWithEntity(Entity entityIn) {
		//Do nothing
	}
	
	@Override
	public boolean canBePushed() {
		return false;
	}
	
	@Override
	public void outOfWorld() {
		this.remove();
	}

	@Override
	public void livingTick() {
		super.livingTick();
	}
	
	//Immune to all damage sources except /kill and the void.
	@Override
	public boolean isInvulnerableTo(DamageSource source) {
		return source != DamageSource.OUT_OF_WORLD;
	}
	
	@Override
	public boolean canDespawn(double distanceToClosestPlayer) {
		return false;
	}
	
	@Override
	public boolean preventDespawn() {
		return true;
	}
}
