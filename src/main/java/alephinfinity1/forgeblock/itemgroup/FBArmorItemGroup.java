package alephinfinity1.forgeblock.itemgroup;

import alephinfinity1.forgeblock.init.ModItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class FBArmorItemGroup extends ItemGroup {

	public FBArmorItemGroup(String label) {
		super(label);
		// TODO Auto-generated constructor stub
	}

	public FBArmorItemGroup(int index, String label) {
		super(index, label);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ItemStack createIcon() {
		return new ItemStack(ModItems.LAPIS_HELMET.get());
	}

}
