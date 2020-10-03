package alephinfinity1.forgeblock.item;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import alephinfinity1.forgeblock.misc.FBItemType;
import alephinfinity1.forgeblock.misc.TextFormatHelper;
import alephinfinity1.forgeblock.misc.tier.FBTier;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

public class FBEnchantedBookItem extends EnchantedBookItem implements IFBItem, IFBTieredItem {

	public FBEnchantedBookItem(Properties builder) {
		super(builder);
	}

	@Override
	public FBTier getFBTier() {
		return FBTier.COMMON;
	}

	@Override
	public FBTier getStackTier(ItemStack stack) {
		short maxLvl = 0;
		ListNBT nbt = EnchantedBookItem.getEnchantments(stack);
		for(INBT instance : nbt) {
			CompoundNBT compound = (CompoundNBT) instance;
			if(compound.getShort("lvl") > maxLvl) maxLvl = compound.getShort("lvl");
		}
		switch(maxLvl) {
		case 5:
			return FBTier.UNCOMMON;
		case 6:
			return FBTier.RARE;
		case 7:
			return FBTier.EPIC;
		case 8:
			return FBTier.LEGENDARY;
		case 9:
			return FBTier.MYTHIC;
		case 10:
			return FBTier.SUPREME;
		default:
			return FBTier.COMMON;
		}
	}

	@Override
	public FBItemType getFBItemType() {
		return FBItemType.ENCHANTED_BOOK;
	}
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		FBTier tier = this.getStackTier(stack);
		tooltip.add(new StringTextComponent(tier.color.toString() + tooltip.get(0).getString()));
		tooltip.remove(0);
		
		tooltip.addAll(TextFormatHelper.formatEnchantments(stack, EnchantedBookItem.getEnchantments(stack)));
		
		boolean recombobulated = false;
		if(stack.getTag() != null) recombobulated = (stack.getTag().getByte("Recombobulated") == 1);
		String color = tier.color.toString();
		String bold = TextFormatting.BOLD.toString();
		String obfuscated = TextFormatting.OBFUSCATED.toString();
		String reset = TextFormatting.RESET.toString();
		if(!recombobulated) tooltip.add(new StringTextComponent(color + bold + tier.name.getString() + " " + this.getFBItemType().getDisplayName()));
		else tooltip.add(new StringTextComponent(color + bold + obfuscated + "n " + reset + color + bold + tier.name.getString() + " " + this.getFBItemType().getDisplayName() + obfuscated + " n"));
	}
	
	public static List<EnchantmentData> getEnchantmentsAsList(ItemStack stack) {
		ListNBT listNBT = EnchantedBookItem.getEnchantments(stack);
		List<EnchantmentData> enchList = new ArrayList<>();
		
		for(INBT nbt : listNBT) {
			CompoundNBT compound = (CompoundNBT) nbt;
			Enchantment ench = ForgeRegistries.ENCHANTMENTS.getValue(ResourceLocation.tryCreate(compound.getString("id")));
			int level = compound.getShort("lvl");
			enchList.add(new EnchantmentData(ench, level));
		}
		
		return enchList;
	}
	
	@Override
	public Rarity getRarity(ItemStack stack) {
		return this.getStackTier(stack).getVanillaRarity();
	}

}
