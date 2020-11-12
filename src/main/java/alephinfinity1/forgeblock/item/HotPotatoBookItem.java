package alephinfinity1.forgeblock.item;

import alephinfinity1.forgeblock.misc.tier.FBTier;
import net.minecraft.item.ItemStack;

public class HotPotatoBookItem extends FBTieredItem {
	
	private final boolean FUMING;

	public HotPotatoBookItem(Properties properties, FBTier tier) {
		super(properties, tier);
		this.FUMING = false;
	}
	
	public HotPotatoBookItem(Properties properties, FBTier tier, boolean fuming) {
		super(properties, tier);
		this.FUMING = fuming;
	}
	
	public boolean isFuming() {
		return FUMING;
	}
	
	@Override
	public boolean hasEffect(ItemStack stack) {
		return this.FUMING ? true : super.hasEffect(stack);
	}

}
