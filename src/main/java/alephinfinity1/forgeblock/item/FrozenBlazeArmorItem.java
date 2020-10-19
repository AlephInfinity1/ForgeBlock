package alephinfinity1.forgeblock.item;

import com.google.common.collect.Multimap;

import alephinfinity1.forgeblock.misc.TickHandler;
import alephinfinity1.forgeblock.misc.tier.FBTier;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class FrozenBlazeArmorItem extends FBArmorItem {

	public FrozenBlazeArmorItem(EquipmentSlotType head, String string, Properties group, FBTier epic,
			Multimap<String, AttributeModifier> modifierMapFromDoubles) {
		super(head, string, group, epic, modifierMapFromDoubles);
	}
	
	@SuppressWarnings("resource")
	@SubscribeEvent
	public static void onLivingUpdate(LivingUpdateEvent event) {
		LivingEntity living = event.getEntityLiving();
		if(living.getEntityWorld().isRemote) return; 
		Iterable<ItemStack> armor = living.getArmorInventoryList();
		for(ItemStack stack : armor) {
			if(!(stack.getItem() instanceof FrozenBlazeArmorItem)) {
				if(TickHandler.isWearingFrozenBlazeArmor != null) {
					if(TickHandler.isWearingFrozenBlazeArmor.get(living) != null) {
						TickHandler.isWearingFrozenBlazeArmor.remove(living);
					}
				}
				return;
			}
		}
		if(TickHandler.isWearingFrozenBlazeArmor != null) {
			if(TickHandler.isWearingFrozenBlazeArmor.get(living) == null) {
				TickHandler.isWearingFrozenBlazeArmor.put(living, Boolean.TRUE);
			} else if(TickHandler.isWearingFrozenBlazeArmor.get(living).equals(Boolean.FALSE)) {
				TickHandler.isWearingFrozenBlazeArmor.put(living, Boolean.TRUE);
			}
		}
	}

}
