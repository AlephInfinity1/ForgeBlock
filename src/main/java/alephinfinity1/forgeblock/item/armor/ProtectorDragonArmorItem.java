package alephinfinity1.forgeblock.item.armor;

import java.util.UUID;

import alephinfinity1.forgeblock.item.IRequirementItem;
import com.google.common.collect.Multimap;

import alephinfinity1.forgeblock.attribute.FBAttributes;
import alephinfinity1.forgeblock.misc.itemreqs.IRequirementPredicate;
import alephinfinity1.forgeblock.misc.itemreqs.SkillRequirementPredicate;
import alephinfinity1.forgeblock.misc.tier.FBTier;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ProtectorDragonArmorItem extends FBArmorItem implements IRequirementItem {
	
	public static final UUID PROTECTOR_MODIFIER = UUID.fromString("73ee2d0f-56e0-485f-af9b-b69b755ba7e0");

	public ProtectorDragonArmorItem(EquipmentSlotType slot, String name, Properties props, FBTier tier,
			Multimap<String, AttributeModifier> modifiers) {
		super(slot, name, props, tier, modifiers);
	}
	
	@SubscribeEvent
	public static void onLivingUpdate(LivingUpdateEvent event) {
		LivingEntity living = event.getEntityLiving();
		if(!(living instanceof PlayerEntity)) return;
		Iterable<ItemStack> armor = living.getArmorInventoryList();
		for(ItemStack stack : armor) {
			if(!(stack.getItem() instanceof ProtectorDragonArmorItem)) {
				if(living.getAttribute(FBAttributes.DEFENSE).getModifier(PROTECTOR_MODIFIER) != null) {
					living.getAttribute(FBAttributes.DEFENSE).removeModifier(PROTECTOR_MODIFIER);
				}
				return;
			}
		}
		living.getAttribute(FBAttributes.DEFENSE).removeModifier(PROTECTOR_MODIFIER);
		living.getAttribute(FBAttributes.DEFENSE).applyModifier(new AttributeModifier(PROTECTOR_MODIFIER, "Protector modifier", 1.0 - (living.getHealth() / living.getMaxHealth()), Operation.MULTIPLY_TOTAL));
	}
	
	@Override
	public IRequirementPredicate[] getRequirements(ItemStack stack) {
		return new IRequirementPredicate[] {SkillRequirementPredicate.combatRequirement(15)};
	}

}
