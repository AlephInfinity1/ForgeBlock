package alephinfinity1.forgeblock.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class CriticalEnchantment extends Enchantment {

	protected CriticalEnchantment(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType[] slots) {
		super(rarityIn, typeIn, slots);
	}
	
	public CriticalEnchantment() {
		super(Rarity.RARE, EnchantmentType.WEAPON, new EquipmentSlotType[] {});
	}
	
	@Override
	public int getMaxLevel() {
		return 5;
	}

}
