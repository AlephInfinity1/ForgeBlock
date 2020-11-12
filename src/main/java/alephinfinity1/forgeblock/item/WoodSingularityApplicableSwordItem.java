package alephinfinity1.forgeblock.item;

import com.google.common.collect.Multimap;

import alephinfinity1.forgeblock.init.ModStatsModifiers;
import alephinfinity1.forgeblock.misc.stats_modifier.capability.IItemModifiers;
import alephinfinity1.forgeblock.misc.stats_modifier.capability.ItemModifiersProvider;
import alephinfinity1.forgeblock.misc.tier.FBTier;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

/**
 * A basic sword in which Wood Singularity can be applied upon.
 */
public class WoodSingularityApplicableSwordItem extends FBSwordItem implements IWoodSingularityApplicable {

	public WoodSingularityApplicableSwordItem(Properties props, FBTier tier, double attackDamageIn, double strengthIn,
			double critChanceIn, double critDamageIn) {
		super(props, tier, attackDamageIn, strengthIn, critChanceIn, critDamageIn);
	}
	
	public WoodSingularityApplicableSwordItem(Properties props, FBTier tier, Multimap<String, AttributeModifier> attributes) {
		super(props, tier, attributes);
	}

	@Override
	public boolean isWoodSingularityApplied(ItemStack stack) {
		IItemModifiers im = stack.getCapability(ItemModifiersProvider.ITEM_MODIFIERS_CAPABILITY).orElse(null);
		if(im == null) {
			return false;
		} else {
			return im.isPresent(ModStatsModifiers.WOOD_SINGULARITY.get());
		}
	}
	
	@Override
	public ITextComponent getDisplayName(ItemStack stack) {
		String reforgeName = "";
		if(this.getReforge(stack) != null) {
			reforgeName = this.getReforge(stack).getDisplayName();
		}
		FBTier tier = getStackTier(stack);
		String color = tier.color.toString();
		String recombobulateBold = stack.getOrCreateTag().getByte("Recombobulated") == 1 ? TextFormatting.BOLD.toString() : "";
		boolean woodSingularity = this.isWoodSingularityApplied(stack);
		if(woodSingularity) {
			if(this.getReforge(stack) != null)
				return new StringTextComponent(color + recombobulateBold + new TranslationTextComponent("misc.forgeblock.woodSingularity").getString() + " " + reforgeName + " " + new TranslationTextComponent(this.getTranslationKey(stack)).getString());
			else
				return new StringTextComponent(color + recombobulateBold + new TranslationTextComponent("misc.forgeblock.woodSingularity").getString() + " " + new TranslationTextComponent(this.getTranslationKey(stack)).getString());
		} else {
			if(this.getReforge(stack) != null)
				return new StringTextComponent(color + recombobulateBold + reforgeName + " " + new TranslationTextComponent(this.getTranslationKey(stack)).getString());
			else
				return new StringTextComponent(color + recombobulateBold + new TranslationTextComponent(this.getTranslationKey(stack)).getString());
		}
	}

}
