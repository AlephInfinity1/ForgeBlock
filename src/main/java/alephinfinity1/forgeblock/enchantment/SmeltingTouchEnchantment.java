package alephinfinity1.forgeblock.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.inventory.EquipmentSlotType;

public class SmeltingTouchEnchantment extends Enchantment {

	protected SmeltingTouchEnchantment(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType[] slots) {
		super(rarityIn, typeIn, slots);
	}
	
	public SmeltingTouchEnchantment() {
		super(Rarity.COMMON, EnchantmentType.DIGGER, new EquipmentSlotType[] {EquipmentSlotType.MAINHAND});
	}
	
	@Override
	public boolean canApplyTogether(Enchantment other) {
		return !other.equals(Enchantments.SILK_TOUCH);
	}

}
