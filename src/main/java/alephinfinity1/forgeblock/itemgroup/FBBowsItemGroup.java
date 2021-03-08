package alephinfinity1.forgeblock.itemgroup;

import alephinfinity1.forgeblock.attribute.FBAttributes;
import alephinfinity1.forgeblock.item.IFBTieredItem;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.NonNullList;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class FBBowsItemGroup extends ItemGroup {
    public FBBowsItemGroup(int index, String label) {
        super(index, label);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(Items.BOW);
    }

    @Override
    public void fill(NonNullList<ItemStack> items) {
        super.fill(items);
        items.sort((a, b) -> {
            int tier = ((IFBTieredItem) a.getItem()).getStackTier(a) //No need to check for instanceof, the items are all IFBTieredItems.
                    .compareTo(((IFBTieredItem) b.getItem()).getStackTier(b));
            if (tier != 0) return tier;

            Collection<AttributeModifier> damageA = a.getAttributeModifiers(EquipmentSlotType.MAINHAND).get(SharedMonsterAttributes.ATTACK_DAMAGE.getName());
            double damageANum = 0.0D;
            for (AttributeModifier modifier : damageA) {
                damageANum += modifier.getAmount();
            }

            Collection<AttributeModifier> damageB = b.getAttributeModifiers(EquipmentSlotType.MAINHAND).get(SharedMonsterAttributes.ATTACK_DAMAGE.getName());
            double damageBNum = 0.0D;
            for (AttributeModifier modifier : damageB) {
                damageBNum += modifier.getAmount();
            }

            if (damageANum > damageBNum) {
                return 1;
            } else if (damageANum < damageBNum) {
                return -1;
            } else { //If both damages are equal, compare strength.
                Collection<AttributeModifier> strengthA = a.getAttributeModifiers(EquipmentSlotType.MAINHAND).get(FBAttributes.STRENGTH.getName());
                double strengthANum = 0.0D;
                for (AttributeModifier modifier : damageA) {
                    strengthANum += modifier.getAmount();
                }

                Collection<AttributeModifier> strengthB = b.getAttributeModifiers(EquipmentSlotType.MAINHAND).get(FBAttributes.STRENGTH.getName());
                double strengthBNum = 0.0D;
                for (AttributeModifier modifier : damageB) {
                    strengthBNum += modifier.getAmount();
                }

                return Double.compare(strengthANum, strengthBNum);
            }
        });
    }
}
