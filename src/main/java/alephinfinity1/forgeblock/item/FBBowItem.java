package alephinfinity1.forgeblock.item;

import com.google.common.collect.Multimap;

import alephinfinity1.forgeblock.misc.FBItemType;
import alephinfinity1.forgeblock.misc.reforge.IReforgeableItem;
import alephinfinity1.forgeblock.misc.reforge.Reforge;
import alephinfinity1.forgeblock.misc.tier.FBTier;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;

public class FBBowItem extends BowItem implements IReforgeableItem, IFBTieredItem {

	public FBBowItem(Properties builder) {
		super(builder);
		// TODO Auto-generated constructor stub
	}

	@Override
	public FBItemType getFBItemType() {
		return FBItemType.BOW;
	}

	@Override
	public FBTier getFBTier() {
		return null;
	}

	@Override
	public FBTier getStackTier(ItemStack stack) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reforge getReforge(ItemStack stack) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setReforge(Reforge reforge, ItemStack stack) {
		// TODO Auto-generated method stub

	}

	@Override
	public Multimap<String, AttributeModifier> getReforgeModifiers(ItemStack stack) {
		// TODO Auto-generated method stub
		return null;
	}

}
