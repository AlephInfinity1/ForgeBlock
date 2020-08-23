package alephinfinity1.forgeblock.misc.reforge;

import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;

import alephinfinity1.forgeblock.item.IFBTieredItem;
import alephinfinity1.forgeblock.misc.tier.FBTier;
import alephinfinity1.forgeblock.misc.tier.TierHelper;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ReforgeHandler {

	//Buggy, disabled as of now
	//@SubscribeEvent
	public static void onTick(PlayerTickEvent event) {
		if(event.phase == TickEvent.Phase.START) {
			PlayerEntity player = event.player;
			for(EquipmentSlotType est : EquipmentSlotType.values()) {
				ItemStack item = player.getItemStackFromSlot(est);
				if(!(item.getItem() instanceof IReforgeableItem)) continue;
				IReforgeableItem reforgeable = (IReforgeableItem) item.getItem();
				Reforge reforge = reforgeable.getReforge(item);
				if(reforge == null) continue;
				FBTier tier = TierHelper.getStackTier(item, ((IFBTieredItem) item.getItem()).getFBTier());
				Multimap<String, AttributeModifier> map = reforge.getModifierMapByTier(tier);
				Multiset<String> keys = map.keys();
				for(String key : keys) {
					AttributeModifier modifier = (AttributeModifier) map.get(key).toArray()[0];
					item.addAttributeModifier(key, modifier, est);
				}
			}
		}
	}

}
