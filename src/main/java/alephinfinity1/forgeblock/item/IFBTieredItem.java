package alephinfinity1.forgeblock.item;

import alephinfinity1.forgeblock.misc.tier.FBTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;

public interface IFBTieredItem extends IFBItem {
	/*
	 * Gets the base tier of this item.
	 */
	public FBTier getFBTier();

	/*
	 * Gets the tier of the ItemStack. May be modified
	 */
	public FBTier getStackTier(ItemStack stack);

}
