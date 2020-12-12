package alephinfinity1.forgeblock.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class SyphonEnchantment extends Enchantment implements IFBEnchantment {

	protected SyphonEnchantment(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType[] slots) {
		super(rarityIn, typeIn, slots);	
	}
	
	public SyphonEnchantment() {
		super(Rarity.RARE, EnchantmentType.WEAPON, new EquipmentSlotType[] {EquipmentSlotType.MAINHAND});
	}
	
	@Override
	public int getMaxLevel() {
		return 4;
	}
	
	@Override
	public boolean canApplyTogether(Enchantment other) {
		return !(other instanceof LifeStealEnchantment);
	}

	@Override
	public int getEnchantingTableMaxLevel() {
		return 3;
	}

	@Override
	public int getRequiredSkillLevel(int level) {
		return 15;
	}

}
