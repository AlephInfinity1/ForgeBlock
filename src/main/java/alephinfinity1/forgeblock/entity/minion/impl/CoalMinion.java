package alephinfinity1.forgeblock.entity.minion.impl;

import alephinfinity1.forgeblock.entity.minion.MinionEntity;
import alephinfinity1.forgeblock.entity.minion.basic.goal.MiningGoal;
import net.minecraft.block.Blocks;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class CoalMinion extends MinionEntity {

    public CoalMinion(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new MiningGoal(this, minionInv, Blocks.COAL_ORE, 100, 2));
    }
}
