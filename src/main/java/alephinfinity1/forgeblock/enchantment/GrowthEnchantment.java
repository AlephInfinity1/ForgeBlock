package alephinfinity1.forgeblock.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class GrowthEnchantment extends Enchantment implements IFBEnchantment {

	public GrowthEnchantment(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType[] slots) {
		super(rarityIn, typeIn, slots);
	}
	
	public GrowthEnchantment() {
		super(Rarity.RARE, EnchantmentType.ARMOR, new EquipmentSlotType[] {EquipmentSlotType.HEAD, EquipmentSlotType.CHEST, EquipmentSlotType.LEGS, EquipmentSlotType.FEET});
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
		return 5;
	}

}
