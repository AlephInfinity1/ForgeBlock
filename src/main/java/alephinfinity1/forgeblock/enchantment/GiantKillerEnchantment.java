package alephinfinity1.forgeblock.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class GiantKillerEnchantment extends Enchantment {

	protected GiantKillerEnchantment(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType[] slots) {
		super(rarityIn, typeIn, slots);
	}
	
	public GiantKillerEnchantment() {
		super(Rarity.UNCOMMON, EnchantmentType.WEAPON, new EquipmentSlotType[] {EquipmentSlotType.MAINHAND});
	}
	
	@Override
	public int getMaxLevel() {
		return 5;
	}

}
