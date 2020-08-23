package alephinfinity1.forgeblock.misc.reforge;

import com.google.common.collect.Multimap;

import alephinfinity1.forgeblock.item.IFBTieredItem;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.ItemStack;

/*
 * Dummy interface
 */
public interface IReforgeableItem extends IFBTieredItem {
	public Reforge getReforge(ItemStack stack);
	
	public Multimap<String, AttributeModifier> getModifiers(ItemStack stack);
	
	public void setReforge(Reforge reforge, ItemStack stack);
}
