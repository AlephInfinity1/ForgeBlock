package alephinfinity1.forgeblock.misc.tier;

import java.util.List;

import alephinfinity1.forgeblock.item.IFBTieredItem;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class TooltipHandler {

	@SubscribeEvent
	public static void modifyTooltip(ItemTooltipEvent event) {
		/*
		if(event.getPlayer() == null) {
			List<ITextComponent> tooltip = event.getToolTip();
			if(!(event.getItemStack().getItem() instanceof IFBTieredItem)) {
				tooltip.add(new StringTextComponent(TextFormatting.BOLD.toString() + FBTier.COMMON.name.getString()));
			}
		}
		*/
	}

}
