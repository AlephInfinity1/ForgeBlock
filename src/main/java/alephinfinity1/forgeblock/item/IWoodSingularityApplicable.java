package alephinfinity1.forgeblock.item;

import net.minecraft.item.ItemStack;

/**
 * A dummy interface to mark if Wood Singularity can be applied to an item.
 */
public interface IWoodSingularityApplicable {
	
	public boolean isWoodSingularityApplied(ItemStack stack);
	
}
