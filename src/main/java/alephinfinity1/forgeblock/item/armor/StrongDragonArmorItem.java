package alephinfinity1.forgeblock.item.armor;

import java.util.ArrayList;
import java.util.List;

import alephinfinity1.forgeblock.item.IRequirementItem;
import com.google.common.collect.Multimap;

import alephinfinity1.forgeblock.misc.itemreqs.IRequirementPredicate;
import alephinfinity1.forgeblock.misc.itemreqs.SkillRequirementPredicate;
import alephinfinity1.forgeblock.misc.tier.FBTier;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class StrongDragonArmorItem extends FBArmorItem implements IRequirementItem {

	public StrongDragonArmorItem(EquipmentSlotType slot, String name, Properties props, FBTier tier,
			Multimap<String, AttributeModifier> modifiers) {
		super(slot, name, props, tier, modifiers);
	}
	
	@Override
	public List<ITextComponent> additionalInformation() {
		List<ITextComponent> list = new ArrayList<>();
		list.add(new StringTextComponent(new TranslationTextComponent("text.forgeblock.armor_desc.strong_0").getString()));
		list.add(new StringTextComponent(new TranslationTextComponent("text.forgeblock.armor_desc.strong_1").getString()));
		list.add(new StringTextComponent(new TranslationTextComponent("text.forgeblock.armor_desc.strong_2").getString()));
		list.add(new StringTextComponent(new TranslationTextComponent("text.forgeblock.armor_desc.strong_3").getString()));
		list.add(new StringTextComponent(""));
		return list;
	}

	@Override
	public IRequirementPredicate[] getRequirements(ItemStack stack) {
		return new IRequirementPredicate[] {SkillRequirementPredicate.combatRequirement(18)};
	}

}
