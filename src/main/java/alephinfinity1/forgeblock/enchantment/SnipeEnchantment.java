package alephinfinity1.forgeblock.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class SnipeEnchantment extends Enchantment {
    public SnipeEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentType.BOW, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND});
    }

    public int getMaxLevel() {
        return 4;
    }
}
