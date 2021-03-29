package alephinfinity1.forgeblock.misc.capability.accessories;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.IInventoryChangedListener;
import net.minecraft.item.Item;
import net.minecraft.nbt.INBT;
import net.minecraftforge.items.IItemHandler;

public interface IAccessoriesData extends IInventory {
    void addListener(IInventoryChangedListener listener);

    boolean isPresent(Item item);

    INBT writeNBT();

    void readNBT(INBT nbt);
}
