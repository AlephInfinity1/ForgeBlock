package alephinfinity1.forgeblock.enchantment;

import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class UltimateWiseEnchantment extends UltimateEnchantment implements IFBEnchantment {

	protected UltimateWiseEnchantment(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType[] slots) {
		super(rarityIn, typeIn, slots);
	}
	
	public UltimateWiseEnchantment() {
		super(Rarity.VERY_RARE, EnchantmentType.WEAPON, new EquipmentSlotType[] {});
	}
	
	@Override
	public int getMaxLevel() {
		return 5;
	}

	@Override
	public int getEnchantingTableMaxLevel() {
		return -1;
	}

	@Override
	public int getRequiredSkillLevel(int level) {
		return 20;
	}

}
