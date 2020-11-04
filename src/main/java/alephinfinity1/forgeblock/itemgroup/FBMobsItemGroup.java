package alephinfinity1.forgeblock.itemgroup;

import alephinfinity1.forgeblock.init.ModItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class FBMobsItemGroup extends ItemGroup {

	public FBMobsItemGroup(int i, String label) {
		super(i, label);
	}

	@Override
	public ItemStack createIcon() {
		return new ItemStack(ModItems.LV1_ZOMBIE_SPAWN_EGG.get());
	}

}
