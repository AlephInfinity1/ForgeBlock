package alephinfinity1.forgeblock.itemgroup;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class FBBowsItemGroup extends ItemGroup {
    public FBBowsItemGroup(int index, String label) {
        super(index, label);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(Items.BOW);
    }
}
