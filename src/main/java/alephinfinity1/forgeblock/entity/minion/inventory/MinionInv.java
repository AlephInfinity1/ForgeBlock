package alephinfinity1.forgeblock.entity.minion.inventory;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class MinionInv {


  public IInventory newMinionInv() {

    IInventory inv = new IInventory() {
      @Override
      public int getSizeInventory() {
        return 30;
      }

      @Override
      public boolean isEmpty() {
        return false;
      }

      @Override
      public ItemStack getStackInSlot(int index) {
        return null;
      }

      @Override
      public ItemStack decrStackSize(int index, int count) {
        return null;
      }

      @Override
      public ItemStack removeStackFromSlot(int index) {
        return null;
      }

      @Override
      public void setInventorySlotContents(int index, ItemStack stack) {

      }

      @Override
      public void markDirty() {

      }

      @Override
      public boolean isUsableByPlayer(PlayerEntity player) {
        return true;
      }

      @Override
      public void clear() {

      }
    };


    return inv;
  }
}
