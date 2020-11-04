package alephinfinity1.forgeblock.itemgroup;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import alephinfinity1.forgeblock.init.ModItems;
import alephinfinity1.forgeblock.item.IFBTieredItem;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class FBSwordsItemGroup extends ItemGroup {

	public FBSwordsItemGroup(String label) {
		super(label);
	}

	public FBSwordsItemGroup(int index, String label) {
		super(index, label);
	}

	@Override
	public ItemStack createIcon() {
		return new ItemStack(ModItems.ASPECT_OF_THE_END.get());
	}
	
	@Override
	public void fill(NonNullList<ItemStack> items) {
		super.fill(items);
		Collections.sort(items, new Comparator<ItemStack>() {
			public int compare(ItemStack a, ItemStack b) {
				int tier = ((IFBTieredItem) a.getItem()).getStackTier(a) //No need to check for instanceof, the items are all IFBTieredItems.
						.compareTo(((IFBTieredItem) b.getItem()).getStackTier(b));
				if(tier != 0) return tier;
				
				Collection<AttributeModifier> damageA = a.getAttributeModifiers(EquipmentSlotType.MAINHAND).get(SharedMonsterAttributes.ATTACK_DAMAGE.getName());
				double damageANum = 0.0D;
				for(AttributeModifier modifier : damageA) {
					damageANum += modifier.getAmount();
				}
				
				Collection<AttributeModifier> damageB = b.getAttributeModifiers(EquipmentSlotType.MAINHAND).get(SharedMonsterAttributes.ATTACK_DAMAGE.getName());
				double damageBNum = 0.0D;
				for(AttributeModifier modifier : damageB) {
					damageBNum += modifier.getAmount();
				}
				
				return (int) (damageANum - damageBNum);
			}
		});
	}

}
