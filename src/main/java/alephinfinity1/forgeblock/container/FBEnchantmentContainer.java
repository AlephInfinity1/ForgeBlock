package alephinfinity1.forgeblock.container;

import java.util.List;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.EnchantmentContainer;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.stats.Stats;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;

public class FBEnchantmentContainer extends EnchantmentContainer {

	public FBEnchantmentContainer(int p_i50086_1_, PlayerInventory p_i50086_2_, IWorldPosCallable p_i50086_3_) {
		super(p_i50086_1_, p_i50086_2_, p_i50086_3_);	
	}
	
	public boolean enchantItem(PlayerEntity playerIn, int id) {
		ItemStack itemstack = this.tableInventory.getStackInSlot(0);
		ItemStack itemstack1 = this.tableInventory.getStackInSlot(1);
		int i = id + 1;
		if ((itemstack1.isEmpty() || itemstack1.getCount() < i) && !playerIn.abilities.isCreativeMode) {
			return false;
		} else if (this.enchantLevels[id] <= 0 || itemstack.isEmpty() || (playerIn.experienceLevel < i || playerIn.experienceLevel < this.enchantLevels[id]) && !playerIn.abilities.isCreativeMode) {
			return false;
		} else {
			this.field_217006_g.consume((p_217003_6_, p_217003_7_) -> {
				ItemStack itemstack2 = itemstack;
				List<EnchantmentData> list = this.getEnchantmentList(itemstack, id, 999);
				if (!list.isEmpty()) {
					playerIn.onEnchant(itemstack, i);
					boolean flag = itemstack.getItem() == Items.BOOK;
					if (flag) {
						itemstack2 = new ItemStack(Items.ENCHANTED_BOOK);
						this.tableInventory.setInventorySlotContents(0, itemstack2);
					}

					for(int j = 0; j < list.size(); ++j) {
						EnchantmentData enchantmentdata = list.get(j);
						if (flag) {
							EnchantedBookItem.addEnchantment(itemstack2, new EnchantmentData(enchantmentdata.enchantment, enchantmentdata.enchantmentLevel));
						} else {
							itemstack2.addEnchantment(enchantmentdata.enchantment, enchantmentdata.enchantmentLevel);
						}
					}

					if (!playerIn.abilities.isCreativeMode) {
						itemstack1.shrink(i);
						if (itemstack1.isEmpty()) {
							this.tableInventory.setInventorySlotContents(1, ItemStack.EMPTY);
						}
					}

					playerIn.addStat(Stats.ENCHANT_ITEM);
					if (playerIn instanceof ServerPlayerEntity) {
						CriteriaTriggers.ENCHANTED_ITEM.trigger((ServerPlayerEntity)playerIn, itemstack2, i);
					}

					this.tableInventory.markDirty();
					this.xpSeed.set(playerIn.getXPSeed());
					this.onCraftMatrixChanged(this.tableInventory);
					p_217003_6_.playSound((PlayerEntity)null, p_217003_7_, SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.BLOCKS, 1.0F, p_217003_6_.rand.nextFloat() * 0.1F + 0.9F);
				}

			});
			return true;
		}
	}

}
