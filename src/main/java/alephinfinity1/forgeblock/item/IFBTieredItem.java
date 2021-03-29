package alephinfinity1.forgeblock.item;

import java.util.List;
import java.util.Objects;

import javax.annotation.Nullable;

import alephinfinity1.forgeblock.init.ModStatsModifiers;
import alephinfinity1.forgeblock.misc.capability.stats_modifier.capability.IItemModifiers;
import alephinfinity1.forgeblock.misc.capability.stats_modifier.capability.ItemModifiersProvider;
import alephinfinity1.forgeblock.misc.tier.FBTier;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public interface IFBTieredItem extends IFBItem {
	/*
	 * Gets the base tier of this item.
	 */
	FBTier getFBTier();

	/*
	 * Gets the tier of the ItemStack. May be modified
	 */
	FBTier getStackTier(ItemStack stack);
	
	default Rarity getRarity(ItemStack stack) {
		return getStackTier(stack).getVanillaRarity();
	}
	
	default void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		FBTier tier = getStackTier(stack);
		tooltip.add(new StringTextComponent(tier.color.toString() + tooltip.get(0).getString()));
		tooltip.remove(0);
		boolean recombobulated = false;
		if(stack.getTag() != null) recombobulated = this.isRecombobulated(stack);
		String color = tier.color.toString();
		String bold = TextFormatting.BOLD.toString();
		String obfuscated = TextFormatting.OBFUSCATED.toString();
		String reset = TextFormatting.RESET.toString();
		if(!recombobulated) tooltip.add(new StringTextComponent(color + bold + tier.name.getString()));
		else tooltip.add(new StringTextComponent(color + bold + obfuscated + "n " + reset + color + bold + tier.name.getString() + obfuscated + " n"));
	}
	
	default boolean isRecombobulated(ItemStack stack) {
		IItemModifiers itemMod = stack.getCapability(ItemModifiersProvider.ITEM_MODIFIERS_CAPABILITY).orElse(null);
		if(Objects.isNull(itemMod)) return false;
		else return itemMod.isPresent(ModStatsModifiers.RECOMBOBULATOR.get());
	}

}
