package alephinfinity1.forgeblock.itemgroup;

import alephinfinity1.forgeblock.init.ModItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class FBHarvestingToolsItemGroup extends ItemGroup {

	public FBHarvestingToolsItemGroup(String label) {
		super(label);
	}

	public FBHarvestingToolsItemGroup(int index, String label) {
		super(index, label);
	}

	@Override
	public ItemStack createIcon() {
		return new ItemStack(ModItems.STONK.get());
	}

}
