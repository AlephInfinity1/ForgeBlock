package alephinfinity1.forgeblock.itemgroup;

import alephinfinity1.forgeblock.init.ModItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class FBSwordsItemGroup extends ItemGroup {

	public FBSwordsItemGroup(String label) {
		super(label);
	}

	public FBSwordsItemGroup(int index, String label) {
		super(index, label);
	}

	@Override
	public ItemStack createIcon() {
		return new ItemStack(ModItems.ASPECT_OF_THE_END.get());
	}

}
