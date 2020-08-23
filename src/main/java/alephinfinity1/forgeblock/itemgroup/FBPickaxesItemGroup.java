package alephinfinity1.forgeblock.itemgroup;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class FBPickaxesItemGroup extends ItemGroup {

	public FBPickaxesItemGroup(String label) {
		super(label);
		// TODO Auto-generated constructor stub
	}

	public FBPickaxesItemGroup(int index, String label) {
		super(index, label);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ItemStack createIcon() {
		return new ItemStack(Items.IRON_PICKAXE);
	}

}
