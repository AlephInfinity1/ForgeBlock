package alephinfinity1.forgeblock.item.swords;

import alephinfinity1.forgeblock.init.ModEnchantments;
import alephinfinity1.forgeblock.misc.tier.FBTier;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class FancySwordItem extends FBSwordItem {

	public FancySwordItem(Properties props, FBTier tier, double attackDamageIn, double strengthIn, double critChanceIn,
			double critDamageIn) {
		super(props, tier, attackDamageIn, strengthIn, critChanceIn, critDamageIn);
	}
	
	@Override
	public ItemStack getDefaultInstance() {
		ItemStack stack = new ItemStack(this);
		stack.addEnchantment(ModEnchantments.FIRST_STRIKE.get(), 1);
		stack.addEnchantment(ModEnchantments.SCAVENGER.get(), 1);
		stack.addEnchantment(Enchantments.SHARPNESS, 2);
		stack.addEnchantment(ModEnchantments.VAMPIRISM.get(), 1);
		return stack;
	}
	
	@Override
	public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
		if (this.isInGroup(group)) {
			items.add(this.getDefaultInstance());
		}
	}

}
