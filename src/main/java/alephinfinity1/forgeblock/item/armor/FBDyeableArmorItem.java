package alephinfinity1.forgeblock.item.armor;

import alephinfinity1.forgeblock.misc.tier.FBTier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IDyeableArmorItem;

public class FBDyeableArmorItem extends FBArmorItem implements IDyeableArmorItem {

	public FBDyeableArmorItem(EquipmentSlotType slot, String name, Properties props, FBTier tier, double defenseIn,
			double healthIn) {
		super(slot, name, props, tier, defenseIn, healthIn);
	}

}
