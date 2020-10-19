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
public class BlazeArmorItem extends FBArmorItem {

	public BlazeArmorItem(EquipmentSlotType slot, String name, Properties props, FBTier tier, double damage,
			double strength, double critChance, double critDamage, double bonusAttackSpeed, double seaCreatureChance,
			double health, double defense, double speed, double intelligence, double trueDefense, double magicFind,
			double petLuck) {
		super(slot, name, props, tier, damage, strength, critChance, critDamage, bonusAttackSpeed, seaCreatureChance, health,
				defense, speed, intelligence, trueDefense, magicFind, petLuck);
	}
	
	public BlazeArmorItem(EquipmentSlotType head, String string, Properties group, FBTier epic,
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
			if(!(stack.getItem() instanceof BlazeArmorItem)) {
				if(TickHandler.isWearingBlazeArmor != null) {
					if(TickHandler.isWearingBlazeArmor.get(living) != null) {
						TickHandler.isWearingBlazeArmor.remove(living);
					}
				}
				return;
			}
		}
		if(TickHandler.isWearingBlazeArmor != null) {
			if(TickHandler.isWearingBlazeArmor.get(living) == null) {
				TickHandler.isWearingBlazeArmor.put(living, Boolean.TRUE);
			} else if(TickHandler.isWearingBlazeArmor.get(living).equals(Boolean.FALSE)) {
				TickHandler.isWearingBlazeArmor.put(living, Boolean.TRUE);
			}
		}
	}

}
