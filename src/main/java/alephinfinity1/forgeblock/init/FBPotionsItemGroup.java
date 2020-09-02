package alephinfinity1.forgeblock.init;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class FBPotionsItemGroup extends ItemGroup {

	public FBPotionsItemGroup(String label) {
		super(label);
	}

	@Override
	public ItemStack createIcon() {
		return new ItemStack(Items.POTION);
	}

}
