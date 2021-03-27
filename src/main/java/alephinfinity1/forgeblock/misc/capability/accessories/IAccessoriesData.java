package alephinfinity1.forgeblock.misc.capability.accessories;

import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.INBT;
import net.minecraftforge.items.IItemHandler;

public interface IAccessoriesData extends IInventory {
    INBT writeNBT();

    void readNBT(INBT nbt);
}
