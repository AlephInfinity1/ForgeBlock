package alephinfinity1.forgeblock.misc.capability.accessories;

import alephinfinity1.forgeblock.item.accessories.IAccessoryItem;
import alephinfinity1.forgeblock.mixin.AccessorInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.IInventoryChangedListener;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public class AccessoriesData implements IAccessoriesData {
    private Inventory contents = new Inventory(45);

    /**
     * Returns the number of slots in the inventory.
     */
    @Override
    public int getSizeInventory() {
        return contents.getSizeInventory();
    }

    @Override
    public boolean isEmpty() {
        return contents.isEmpty();
    }

    /**
     * Returns the stack in the given slot.
     *
     * @param index
     */
    @Override
    public ItemStack getStackInSlot(int index) {
        return contents.getStackInSlot(index);
    }

    /**
     * Removes up to a specified number of items from an inventory slot and returns them in a new stack.
     *
     * @param index
     * @param count
     */
    @Override
    public ItemStack decrStackSize(int index, int count) {
        return contents.decrStackSize(index, count);
    }

    /**
     * Removes a stack from the given slot and returns it.
     *
     * @param index
     */
    @Override
    public ItemStack removeStackFromSlot(int index) {
        return contents.removeStackFromSlot(index);
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     *
     * @param index
     * @param stack
     */
    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        contents.setInventorySlotContents(index, stack);
    }

    /**
     * For tile entities, ensures the chunk containing the tile entity is saved to disk later - the game won't think it
     * hasn't changed and skip it.
     */
    @Override
    public void markDirty() {
        contents.markDirty();
    }

    /**
     * Don't rename this method to canInteractWith due to conflicts with Container
     *
     * @param player
     */
    @Override
    public boolean isUsableByPlayer(PlayerEntity player) {
        return contents.isUsableByPlayer(player);
    }

    @Override
    public void clear() {
        contents.clear();
    }

    public INBT writeNBT() {
        return ItemStackHelper.saveAllItems(new CompoundNBT(), ((AccessorInventory) contents).getInventoryContents());
    }

    public void readNBT(INBT tag) {
        if (tag instanceof CompoundNBT) {
            ItemStackHelper.loadAllItems((CompoundNBT) tag, ((AccessorInventory) contents).getInventoryContents());
        }
    }

    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended.
     */
    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot. For
     * guis use Slot.isItemValid
     *
     * @param index
     * @param stack
     */
    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return stack.getItem() instanceof IAccessoryItem;
    }

    @Override
    public void addListener(IInventoryChangedListener listener) {
        contents.addListener(listener);
    }

    @Override
    public boolean isPresent(Item item) {
        for (int i = 0; i < this.getSizeInventory(); i++) {
            ItemStack itemstack = this.getStackInSlot(i);
            if (itemstack.getItem().equals(item)) {
                return true;
            }
        }
        return false;
    }
}
