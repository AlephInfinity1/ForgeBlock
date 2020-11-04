package alephinfinity1.forgeblock.itemgroup;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class FBCustomItemGroup extends ItemGroup {

	public FBCustomItemGroup(String label) {
		super(label);
	}

	public FBCustomItemGroup(int i, String string) {
		super(i, string);
	}

	@Override
	public ItemStack createIcon() {
		return new ItemStack(Items.PAINTING);
	}

}
