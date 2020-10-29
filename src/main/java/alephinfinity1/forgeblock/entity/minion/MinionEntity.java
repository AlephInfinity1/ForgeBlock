package alephinfinity1.forgeblock.entity.minion;

import alephinfinity1.forgeblock.entity.minion.goal.MiningGoal;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;


public class MinionEntity extends CreatureEntity {


  public MinionEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
    super(type, worldIn);
  }


  @Override
  protected void registerGoals() {
    this.goalSelector.addGoal(0, new MiningGoal(this));
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
  public void setHealth(float health) {
    super.setHealth(10000000);
  }


  @Override
  public boolean canBeCollidedWith() {
    return false;
  }


  @Override
  public void livingTick() {
    super.livingTick();
  }
}
