package alephinfinity1.forgeblock.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class SharpnessEnchantment extends Enchantment implements IFBEnchantment {

	protected SharpnessEnchantment(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType[] slots) {
		super(rarityIn, typeIn, slots);	
	}
	
	public SharpnessEnchantment() {
		super(Rarity.COMMON, EnchantmentType.WEAPON, new EquipmentSlotType[] {EquipmentSlotType.MAINHAND});
	}
	
	@Override
	public int getMaxLevel() {
		return 6;
	}

	@Override
	public int getEnchantingTableMaxLevel() {
		return 5;
	}

	@Override
	public int getRequiredSkillLevel(int level) {
		return 0;
	}

}
