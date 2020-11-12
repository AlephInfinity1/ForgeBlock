package alephinfinity1.forgeblock.itemgroup;

import alephinfinity1.forgeblock.init.ModItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class FBMiscItemGroup extends ItemGroup {

	public FBMiscItemGroup(int index, String label) {
		super(index, label);	
	}

	@Override
	public ItemStack createIcon() {
		return new ItemStack(ModItems.FUMING_POTATO_BOOK.get());
	}

}
