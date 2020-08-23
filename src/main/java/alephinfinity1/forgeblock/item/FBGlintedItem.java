package alephinfinity1.forgeblock.item;

import alephinfinity1.forgeblock.misc.tier.FBTier;
import net.minecraft.item.ItemStack;

public class FBGlintedItem extends FBTieredItem {

	public FBGlintedItem(Properties properties) {
		super(properties);
	}

	public FBGlintedItem(Properties properties, FBTier tier) {
		super(properties, tier);
	}
	
	@Override
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

}
