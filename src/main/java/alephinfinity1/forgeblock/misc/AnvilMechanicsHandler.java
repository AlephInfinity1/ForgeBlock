package alephinfinity1.forgeblock.misc;

import java.util.List;

import alephinfinity1.forgeblock.item.FBEnchantedBookItem;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.entity.player.AnvilRepairEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class AnvilMechanicsHandler {

	//@SubscribeEvent
	public static void onAnvilUpdate(AnvilUpdateEvent event) {
		if(event.getRight().getItem() instanceof FBEnchantedBookItem) {
			ItemStack right = event.getRight();
			ItemStack result = event.getLeft().copy();
			List<EnchantmentData> list = FBEnchantedBookItem.getEnchantmentsAsList(right);
			int i = 0;
			for(EnchantmentData data : list) {
				result.addEnchantment(data.enchantment, data.enchantmentLevel);
				++i;
			}
			event.setCost(i * 40);
			event.setOutput(result);
		}
	}
	
	@SubscribeEvent
	public static void onAnvilUse(AnvilRepairEvent event) {
		event.setBreakChance(0.0f);
	}
}
