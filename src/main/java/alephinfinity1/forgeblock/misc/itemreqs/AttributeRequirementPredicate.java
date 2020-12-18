package alephinfinity1.forgeblock.misc.itemreqs;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import alephinfinity1.forgeblock.attribute.FBAttributes;
import alephinfinity1.forgeblock.misc.Interval.DoubleInterval;
import alephinfinity1.forgeblock.misc.Interval.IntervalType;
import alephinfinity1.forgeblock.misc.TextFormatHelper;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

public class AttributeRequirementPredicate implements IRequirementPredicate {
	
	private IAttribute attribute;
	private DoubleInterval range;
	
	public AttributeRequirementPredicate(IAttribute attribute, DoubleInterval range) {
		this.attribute = attribute;
		this.range = range;
	}

	@Override
	public boolean test(PlayerEntity t) {
		return range.isValueWithin(t.getAttribute(this.attribute).getValue());
	}
	
	public static AttributeRequirementPredicate minimum(IAttribute attr, double min) {
		return new AttributeRequirementPredicate(attr, DoubleInterval.aboveOrEqualsTo(min));
	}
	
	public static AttributeRequirementPredicate minStrength(double min) {
		return AttributeRequirementPredicate.minimum(FBAttributes.STRENGTH, min);
	}
	
	public static AttributeRequirementPredicate minHealth(double min) {
		return AttributeRequirementPredicate.minimum(SharedMonsterAttributes.MAX_HEALTH, min);
	}
	
	public static AttributeRequirementPredicate minDefense(double min) {
		return AttributeRequirementPredicate.minimum(FBAttributes.DEFENSE, min);
	}
	
	public static AttributeRequirementPredicate minIntelligence(double min) {
		return AttributeRequirementPredicate.minimum(FBAttributes.INTELLIGENCE, min);
	}

	@Override
	public List<ITextComponent> getDisplay(boolean isMet) {
		List<ITextComponent> list = new ArrayList<>();
		if (range.getType().equals(IntervalType.ABOVE)) {
			if (isMet) {
				list.add(new StringTextComponent("\u2714 ").applyTextStyle(TextFormatting.GREEN).appendSibling(new TranslationTextComponent("text.forgeblock.attrReqAbove", new TranslationTextComponent(TextFormatHelper.getUnlocalizedAttrName(attribute)).getString(), new DecimalFormat(",###.#").format(range.getMin())).applyTextStyle(TextFormatting.DARK_GREEN)));
			} else {
				list.add(new StringTextComponent("\u2716 ").applyTextStyle(TextFormatting.RED).appendSibling(new TranslationTextComponent("text.forgeblock.attrReqAbove", new TranslationTextComponent(TextFormatHelper.getUnlocalizedAttrName(attribute)).getString(), new DecimalFormat(",###.#").format(range.getMin())).applyTextStyle(TextFormatting.RED)));
			}
		} else if (range.getType().equals(IntervalType.UNDER)) {
			if (isMet) {
				list.add(new StringTextComponent("\u2714 ").applyTextStyle(TextFormatting.GREEN).appendSibling(new TranslationTextComponent("text.forgeblock.attrReqBelow", new TranslationTextComponent(TextFormatHelper.getUnlocalizedAttrName(attribute)).getString(), new DecimalFormat(",###.#").format(range.getMin())).applyTextStyle(TextFormatting.DARK_GREEN)));
			} else {
				list.add(new StringTextComponent("\u2716 ").applyTextStyle(TextFormatting.RED).appendSibling(new TranslationTextComponent("text.forgeblock.attrReqBelow", new TranslationTextComponent(TextFormatHelper.getUnlocalizedAttrName(attribute)).getString(), new DecimalFormat(",###.#").format(range.getMin())).applyTextStyle(TextFormatting.RED)));
			}
		}
		return list;
	}

}
