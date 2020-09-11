package alephinfinity1.forgeblock.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

/*
 * An ultimate enchantment.
 */
public class UltimateEnchantment extends Enchantment {

	protected UltimateEnchantment(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType[] slots) {
		super(rarityIn, typeIn, slots);
	}
	
	/*
	 * Only 1 ultimate enchantment can be applied to one item.
	 */
	@Override
	protected boolean canApplyTogether(Enchantment ench) {
		return !(ench instanceof UltimateEnchantment);
	}

}
