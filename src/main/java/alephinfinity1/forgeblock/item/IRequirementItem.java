package alephinfinity1.forgeblock.item;

import alephinfinity1.forgeblock.misc.itemreqs.IRequirementPredicate;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public interface IRequirementItem {
	
	public IRequirementPredicate[] getRequirements(ItemStack stack);
	
	/**
	 * Returns whether if a player can use an item.
	 * @param player
	 * @param stack
	 * @return
	 */
	default boolean canPlayerUseItem(PlayerEntity player, ItemStack stack) {
		for (IRequirementPredicate i : this.getRequirements(stack)) {
			if(!i.test(player)) return false;
		}
		return true;
	}	

}
