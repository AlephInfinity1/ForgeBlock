package alephinfinity1.forgeblock.item;

import java.util.List;

import javax.annotation.Nullable;

import alephinfinity1.forgeblock.misc.tier.FBTier;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class HotPotatoBookItem extends FBTieredItem {
	
	private final boolean FUMING;

	public HotPotatoBookItem(Properties properties, FBTier tier) {
		super(properties, tier);
		this.FUMING = false;
	}
	
	public HotPotatoBookItem(Properties properties, FBTier tier, boolean fuming) {
		super(properties, tier);
		this.FUMING = fuming;
	}
	
	public boolean isFuming() {
		return FUMING;
	}
	
	@Override
	public boolean hasEffect(ItemStack stack) {
		return this.FUMING ? true : super.hasEffect(stack);
	}
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		FBTier tier = getStackTier(stack);
		tooltip.add(new StringTextComponent(tier.color.toString() + tooltip.get(0).getString()));
		tooltip.remove(0);	
		
		tooltip.add(new TranslationTextComponent("text.forgeblock.item_desc.hpb_0"));
		tooltip.add(new TranslationTextComponent("text.forgeblock.item_desc.hpb_1"));
		tooltip.add(new TranslationTextComponent("text.forgeblock.item_desc.hpb_2"));
		tooltip.add(new TranslationTextComponent("text.forgeblock.item_desc.hpb_3"));
		if(this.FUMING) {
			tooltip.add(new StringTextComponent(""));
			tooltip.add(new TranslationTextComponent("text.forgeblock.item_desc.fuming_0"));
			tooltip.add(new TranslationTextComponent("text.forgeblock.item_desc.fuming_1"));
			tooltip.add(new TranslationTextComponent("text.forgeblock.item_desc.fuming_2"));
			tooltip.add(new TranslationTextComponent("text.forgeblock.item_desc.fuming_3"));
		} else {
			tooltip.add(new TranslationTextComponent("text.forgeblock.item_desc.hpb_limit"));
		}
		tooltip.add(new StringTextComponent(""));
		
		boolean recombobulated = false;
		if(stack.getTag() != null) recombobulated = (stack.getTag().getByte("Recombobulated") == 1);
		String color = tier.color.toString();
		String bold = TextFormatting.BOLD.toString();
		String obfuscated = TextFormatting.OBFUSCATED.toString();
		String reset = TextFormatting.RESET.toString();
		if(!recombobulated) tooltip.add(new StringTextComponent(color + bold + tier.name.getString()));
		else tooltip.add(new StringTextComponent(color + bold + obfuscated + "n " + reset + color + bold + tier.name.getString() + obfuscated + " n"));
	}

}
