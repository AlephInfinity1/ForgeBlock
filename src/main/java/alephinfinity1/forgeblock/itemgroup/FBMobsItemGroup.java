package alephinfinity1.forgeblock.itemgroup;

import alephinfinity1.forgeblock.init.ModItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class FBMobsItemGroup extends ItemGroup {

	public FBMobsItemGroup(String label) {
		super(label);
	}

	@Override
	public ItemStack createIcon() {
		return new ItemStack(ModItems.LV1_ZOMBIE_SPAWN_EGG.get());
	}

}
