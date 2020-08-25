package alephinfinity1.forgeblock.itemgroup;

import alephinfinity1.forgeblock.init.ModItems;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.NonNullList;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class FBHarvestingToolsItemGroup extends ItemGroup {

	public FBHarvestingToolsItemGroup(String label) {
		super(label);
		// TODO Auto-generated constructor stub
	}

	public FBHarvestingToolsItemGroup(int index, String label) {
		super(index, label);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ItemStack createIcon() {
		return new ItemStack(ModItems.STONK.get());
	}
	
	@OnlyIn(Dist.CLIENT)
	@Override
	public void fill(NonNullList<ItemStack> items) {
		ItemStack stonks = new ItemStack(ModItems.STONK.get());
		stonks.addEnchantment(Enchantments.EFFICIENCY, 6);
		
		items.add(stonks);
		for(Item item : Registry.ITEM) {
			item.fillItemGroup(this, items);
		}
	}

}
