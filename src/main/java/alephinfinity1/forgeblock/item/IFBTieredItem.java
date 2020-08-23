package alephinfinity1.forgeblock.item;

import alephinfinity1.forgeblock.misc.tier.FBTier;
import net.minecraft.item.ItemStack;

public interface IFBTieredItem {
	/*
	 * Gets the base tier of this item.
	 */
	public FBTier getFBTier();

	/*
	 * Gets the tier of the ItemStack. May be modified
	 */
	public FBTier getStackTier(ItemStack stack);

}
