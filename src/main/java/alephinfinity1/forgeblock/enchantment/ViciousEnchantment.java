package alephinfinity1.forgeblock.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class ViciousEnchantment extends Enchantment implements IFBEnchantment {

	protected ViciousEnchantment(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType[] slots) {
		super(rarityIn, typeIn, slots);	
	}
	
	public ViciousEnchantment() {
		super(Rarity.VERY_RARE, EnchantmentType.WEAPON, new EquipmentSlotType[] {EquipmentSlotType.MAINHAND});
	}
	
	@Override
	public int getMaxLevel() {
		return 5;
	}

	//Unobtainable at ench table.
	@Override
	public int getEnchantingTableMaxLevel() {
		return -1;
	}

}
