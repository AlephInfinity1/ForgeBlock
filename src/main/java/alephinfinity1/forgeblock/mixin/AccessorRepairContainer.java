package alephinfinity1.forgeblock.mixin;

import net.minecraft.inventory.container.RepairContainer;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.IntReferenceHolder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(RepairContainer.class)
public interface AccessorRepairContainer {
    @Accessor
    IntReferenceHolder getMaximumCost();

    @Accessor
    void setMaximumCost(IntReferenceHolder maximumCost);
}
