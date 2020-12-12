package alephinfinity1.forgeblock.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class EnderSlayerEnchantment extends Enchantment implements IFBEnchantment {

	protected EnderSlayerEnchantment(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType[] slots) {
		super(rarityIn, typeIn, slots);
	}
	
	public EnderSlayerEnchantment() {
		super(Rarity.UNCOMMON, EnchantmentType.WEAPON, new EquipmentSlotType[] {EquipmentSlotType.MAINHAND});
	}
	
	@Override
	public int getMaxLevel() {
		return 6;
	}
	
	@Override
	public int getRepairCostForLevel(int level) {
		switch (level) {
			case 1:
			case 2:
			case 3:
			case 4:
				return level * 2;
			case 5:
				return 12;
			case 6:
				return 18;
			default:
				return Integer.MAX_VALUE;
		}
	}


	@Override
	public int getEnchantingTableMaxLevel() {
		return 5;
	}

	@Override
	public int getRequiredSkillLevel(int level) {	
		return 11;
	}

}
