package alephinfinity1.forgeblock.item.armor;

import com.google.common.collect.Multimap;

import alephinfinity1.forgeblock.misc.tier.FBTier;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class CrownOfGreedItem extends FBArmorItem {

	public CrownOfGreedItem(EquipmentSlotType slot, String name, Properties props, FBTier tier,
			Multimap<String, AttributeModifier> modifiers) {
		super(slot, name, props, tier, modifiers);	
	}
		
}
