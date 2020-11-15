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

public class RecombobulatorItem extends FBTieredItem {

	public RecombobulatorItem(Properties properties, FBTier tier) {
		super(properties, tier);	
	}
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		FBTier tier = getStackTier(stack);
		tooltip.add(new StringTextComponent(tier.color.toString() + tooltip.get(0).getString()));
		tooltip.remove(0);	
		
		tooltip.add(new TranslationTextComponent("text.forgeblock.item_desc.recomb_0"));
		tooltip.add(new TranslationTextComponent("text.forgeblock.item_desc.recomb_1"));
		tooltip.add(new TranslationTextComponent("text.forgeblock.item_desc.recomb_2"));
		tooltip.add(new TranslationTextComponent("text.forgeblock.item_desc.recomb_3"));
		tooltip.add(new TranslationTextComponent("text.forgeblock.item_desc.recomb_4"));	
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
