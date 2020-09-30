package alephinfinity1.forgeblock.misc.reforge;

import com.google.common.collect.Multimap;

import alephinfinity1.forgeblock.item.IFBTieredItem;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.ItemStack;

/*
 * Dummy interface
 */
public interface IReforgeableItem extends IFBTieredItem {
	default Reforge getReforge(ItemStack stack) {
		if(stack.getTag() == null) return null;
		String reforgeName = stack.getTag().getString("Reforge");
		if(reforgeName.isEmpty()) return null;
		else return Reforge.findReforgeByID(reforgeName);
	}
	
	default void setReforge(Reforge reforge, ItemStack stack) {
		stack.getTag().putString("Reforge", reforge.getID());
	}

	public Multimap<String, AttributeModifier> getReforgeModifiers(ItemStack stack);
}
