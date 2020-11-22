package alephinfinity1.forgeblock.misc.stats_modifier.capability;

import alephinfinity1.forgeblock.ForgeBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ItemModifiersCapabilityHandler {
	
	public static final ResourceLocation ITEM_MODIFIERS_CAPABILITY = new ResourceLocation(ForgeBlock.MOD_ID, "stats_modifiers");
	
	@SubscribeEvent
	public static void onCapabilityAttach(AttachCapabilitiesEvent<ItemStack> event) {
		event.addCapability(ITEM_MODIFIERS_CAPABILITY, new ItemModifiersProvider());
		if(event.getCapabilities().get(ITEM_MODIFIERS_CAPABILITY) == null) {
			ForgeBlock.LOGGER.warn("Item modifiers not successfully attached to item " + event.getObject().getDisplayName().getString());
		} else {
			ForgeBlock.LOGGER.trace("Item modifiers attached successfully!");
		}
	}

}
