package alephinfinity1.forgeblock.misc.capability.accessories;

import net.minecraftforge.items.IItemHandler;

public interface IAccessoriesData extends IItemHandler {
    /**
     * Returns the capacity of the accessory bag
     * @return The capacity of the accessory bag
     */
    int getCapacity();

    /**
     * Sets the capacity of the accessory bag
     * @param capacity The new capacity of the accessory bag
     */
    void setCapacity(int capacity);
}
