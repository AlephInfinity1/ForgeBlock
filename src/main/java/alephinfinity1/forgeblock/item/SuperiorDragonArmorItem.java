package alephinfinity1.forgeblock.item;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.google.common.collect.Multimap;

import alephinfinity1.forgeblock.attribute.FBAttributes;
import alephinfinity1.forgeblock.misc.itemreqs.IRequirementPredicate;
import alephinfinity1.forgeblock.misc.itemreqs.SkillRequirementPredicate;
import alephinfinity1.forgeblock.misc.tier.FBTier;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class SuperiorDragonArmorItem extends FBArmorItem implements IRequirementItem {
	
	public static final UUID SUPERIOR_MODIFIER = UUID.fromString("0f370ec2-fa7e-4dc3-9308-6cec80de3053");

	public SuperiorDragonArmorItem(EquipmentSlotType slot, String name, Properties props, FBTier tier,
			Multimap<String, AttributeModifier> modifiers) {
		super(slot, name, props, tier, modifiers);
	}
	
	@Override
	public List<ITextComponent> additionalInformation() {
		List<ITextComponent> list = new ArrayList<>();
		list.add(new StringTextComponent(new TranslationTextComponent("text.forgeblock.armor_desc.superior_0").getString()));
		list.add(new StringTextComponent(new TranslationTextComponent("text.forgeblock.armor_desc.superior_1").getString()));
		list.add(new StringTextComponent(new TranslationTextComponent("text.forgeblock.armor_desc.superior_2").getString()));
		list.add(new StringTextComponent(new TranslationTextComponent("text.forgeblock.armor_desc.superior_3").getString()));
		list.add(new StringTextComponent(""));
		return list;
	}
	
	@SubscribeEvent
	public static void onLivingUpdate(LivingUpdateEvent event) {
		LivingEntity living = event.getEntityLiving();
		if(living == null || !(living instanceof PlayerEntity)) return;
		Iterable<ItemStack> armor = living.getArmorInventoryList();
		for(ItemStack stack : armor) {
			if(!(stack.getItem() instanceof SuperiorDragonArmorItem)) {
				for(IAttribute attribute : FBAttributes.PRIMARY_ATTRIBUTES) {
					if(living.getAttribute(attribute) != null && living.getAttribute(attribute).getModifier(SUPERIOR_MODIFIER) != null) {
						living.getAttribute(attribute).removeModifier(SUPERIOR_MODIFIER);
					}
				}
				return;
			}
		}
		for(IAttribute attribute : FBAttributes.PRIMARY_ATTRIBUTES) {
			if(living.getAttribute(attribute).getModifier(SUPERIOR_MODIFIER) == null) {
				living.getAttribute(attribute).applyModifier(new AttributeModifier(SUPERIOR_MODIFIER, "Superior modifier", 0.05, Operation.MULTIPLY_TOTAL));
			}
		}
	}

	@Override
	public IRequirementPredicate[] getRequirements(ItemStack stack) {
		return new IRequirementPredicate[] {SkillRequirementPredicate.combatRequirement(20)};
	}

}
