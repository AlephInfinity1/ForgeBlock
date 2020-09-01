package alephinfinity1.forgeblock.item;

import alephinfinity1.forgeblock.misc.tier.FBTier;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;

public class StonkItem extends FBPickaxeItem {

	public StonkItem(Properties props, FBTier tier, double damage, int harvestLevel, float destroySpeed, double yield) {
		super(props, tier, damage, harvestLevel, destroySpeed, yield);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public ItemStack getDefaultInstance() {
		ItemStack stack = new ItemStack(this);
		stack.addEnchantment(Enchantments.EFFICIENCY, 6);
		return stack;
	}

}
