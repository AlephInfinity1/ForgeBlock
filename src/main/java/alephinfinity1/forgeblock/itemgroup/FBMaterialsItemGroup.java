package alephinfinity1.forgeblock.itemgroup;

import alephinfinity1.forgeblock.init.ModItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class FBMaterialsItemGroup extends ItemGroup {

	public FBMaterialsItemGroup(String label) {
		super(label);
	}

	public FBMaterialsItemGroup(int index, String label) {
		super(index, label);
	}

	@Override
	public ItemStack createIcon() {
		return new ItemStack(ModItems.ENCHANTED_IRON.get());
	}
	
}
