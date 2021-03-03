package alephinfinity1.forgeblock.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class InfiniteQuiverEnchantment extends Enchantment {
    public InfiniteQuiverEnchantment() {
        super(Enchantment.Rarity.UNCOMMON, EnchantmentType.BOW, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND});
    }

    @Override
    public int getMaxLevel() {
        return 10;
    }
}
