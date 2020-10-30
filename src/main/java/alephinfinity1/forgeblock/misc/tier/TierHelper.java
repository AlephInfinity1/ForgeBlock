package alephinfinity1.forgeblock.misc.tier;

import net.minecraft.item.ItemStack;

public class TierHelper {

	/**
	 * Gets the tier of an ItemStack.
	 * This method should be used for IFBTieredItem only. For non-tiered items, use getItemTier(ItemStack)
	 * @param stack The ItemStack.
	 * @param baseTier The base tier of that ItemStack (determined by Item).
	 * @return The ItemStack's tier.
	 */
	public static FBTier getStackTier(ItemStack stack, FBTier baseTier) {
		if(stack.getTag() != null) {
			boolean recombobulated = (stack.getTag().getByte("Recombobulated") == 1);
			boolean woodSingularity = (stack.getTag().getByte("WoodSingularity") == 1);
			int tierBoost = 0;
			if(recombobulated) tierBoost++;
			if(woodSingularity) tierBoost++;
			return FBTier.changeTier(baseTier, tierBoost);
		} else {
			return baseTier;
		}
	}

	/**
	 * Gets the tier of an ItemStack.
	 * This method should be used for non-IFBTieredItem only. For tiered items, use getStackTier(ItemStack, FBTier)
	 * @param stack The ItemStack.
	 * @return The ItemStack's tier.
	 */
	public static FBTier getItemTier(ItemStack stack) {
		if(stack.getTag() != null) {
			boolean recombobulated = (stack.getTag().getByte("Recombobulated") == 1);
			boolean woodSingularity = (stack.getTag().getByte("WoodSingularity") == 1);
			int tierBoost = 0;
			if(recombobulated) tierBoost++;
			if(woodSingularity) tierBoost++;
			return FBTier.changeTier(FBTier.COMMON, tierBoost);
		} else {
			return FBTier.COMMON;
		}
	}

}
