package alephinfinity1.forgeblock.misc.stats_modifier;

import alephinfinity1.forgeblock.attribute.ModifierHelper;
import alephinfinity1.forgeblock.init.ModStatsModifiers;
import alephinfinity1.forgeblock.misc.stats_modifier.capability.IItemModifiers;
import alephinfinity1.forgeblock.misc.stats_modifier.capability.ItemModifiersProvider;
import net.minecraft.util.text.TextFormatting;

public class RecombobulatorModifier extends AbstractStatsModifier {
	
	public RecombobulatorModifier() {
		super(
				(stack, nbt) -> ModifierHelper.emptyModifier(),
				(stack, nbt) -> 1,
				TextFormatting.GOLD,
				(stack) -> {	
					IItemModifiers im = stack.getCapability(ItemModifiersProvider.ITEM_MODIFIERS_CAPABILITY).orElse(null);
					if(im == null) return false;
					else {
						if(im.isPresent(ModStatsModifiers.RECOMBOBULATOR.get())) return false;
						else return true;
					}
				});
	}

}
