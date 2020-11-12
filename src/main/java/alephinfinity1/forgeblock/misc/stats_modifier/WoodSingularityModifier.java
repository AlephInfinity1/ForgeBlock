package alephinfinity1.forgeblock.misc.stats_modifier;

import java.util.UUID;

import alephinfinity1.forgeblock.attribute.ModifierHelper;
import alephinfinity1.forgeblock.init.ModStatsModifiers;
import alephinfinity1.forgeblock.item.IWoodSingularityApplicable;
import alephinfinity1.forgeblock.misc.stats_modifier.capability.IItemModifiers;
import alephinfinity1.forgeblock.misc.stats_modifier.capability.ItemModifiersProvider;
import net.minecraft.util.text.TextFormatting;

public class WoodSingularityModifier extends AbstractStatsModifier {
	
	private static final UUID MODIFIERS_UUID = UUID.fromString("9032e1b3-aca0-4c15-9336-25e99223136e");	
	
	public WoodSingularityModifier() {
		super(
				(stack, nbt) -> ModifierHelper.modifierMapFromDoubles(0, 100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Wood Singularity Bonus", MODIFIERS_UUID),
				(stack, nbt) -> 1,
				TextFormatting.GOLD,
				(stack) -> {
					if(!(stack.getItem() instanceof IWoodSingularityApplicable)) return false;
					IItemModifiers im = stack.getCapability(ItemModifiersProvider.ITEM_MODIFIERS_CAPABILITY).orElse(null);
					if(im == null) return false;
					else {
						if(im.isPresent(ModStatsModifiers.WOOD_SINGULARITY.get())) return false;
						else return true;
					}
				});
	}

}
