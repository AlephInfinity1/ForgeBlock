package alephinfinity1.forgeblock.entity.minion;

import alephinfinity1.forgeblock.entity.minion.basic.inventory.MinionInv;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;


public abstract class MinionEntity extends CreatureEntity {


	public MinionInv minionInv;
	private long tick = 0;

	protected MinionEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		this.minionInv = new MinionInv(36);
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
		tick++;
		if(tick % 5 == 0)
			this.alignBlock();
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
	
	@Override
	public boolean processInteract(PlayerEntity player, Hand hand) {
		player.addItemStackToInventory(new ItemStack(Items.BARRIER));
		return true;
	}
	
	/*
	 * Aligns the minion to the centre of a block.
	 * Effectively makes this a block entity of sorts.
	 */
	public void alignBlock() {
		this.setPositionAndUpdate(MathHelper.floor(this.getPosX()) + 0.5, MathHelper.floor(this.getPosY()), MathHelper.floor(this.getPosZ()) + 0.5);
	}


}
