package alephinfinity1.forgeblock.misc.tier;

import net.minecraft.item.ItemStack;

public class TierHelper {

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

}
