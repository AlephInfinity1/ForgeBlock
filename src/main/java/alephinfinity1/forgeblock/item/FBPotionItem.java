package alephinfinity1.forgeblock.item;

import java.util.List;

import javax.annotation.Nullable;

import alephinfinity1.forgeblock.ForgeBlock;
import alephinfinity1.forgeblock.misc.FBItemType;
import alephinfinity1.forgeblock.misc.tier.FBTier;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

public class FBPotionItem extends PotionItem implements IFBTieredItem {

	public FBPotionItem(Properties builder) {
		super(builder);
		// TODO Auto-generated constructor stub
	}

	@Override
	public FBItemType getFBItemType() {
		return FBItemType.POTION;
	}

	@Override
	public FBTier getFBTier() {
		return FBTier.COMMON;
	}

	@Override
	public FBTier getStackTier(ItemStack stack) {
		List<EffectInstance> effects = PotionUtils.getEffectsFromStack(stack);
		int maxAmplifier = 0;
		for(EffectInstance effect : effects) {
			if(effect.getAmplifier() > maxAmplifier) {
				maxAmplifier = effect.getAmplifier();
			}
		}
		
		FBTier tier;
		switch(maxAmplifier) {
		case 0:
		case 1:
			tier = FBTier.COMMON;
			break;
		case 2:
		case 3:
			tier = FBTier.UNCOMMON;
			break;
		case 4:
		case 5:
			tier = FBTier.RARE;
			break;
		case 6:
		case 7:
			tier = FBTier.EPIC;
			break;
		case 8:
		case 9:
			tier = FBTier.LEGENDARY;
			break;
		case 10:
		case 11:
		case 12:
		case 13:
		case 14:
			tier = FBTier.MYTHIC;
			break;
		default:
			tier = FBTier.SPECIAL;
			break;
		}
		
		if(stack.getTag() != null) {
			boolean recombobulated = (stack.getTag().getByte("Recombobulated") == 1);
			boolean woodSingularity = (stack.getTag().getByte("WoodSingularity") == 1);
			int tierBoost = 0;
			if(recombobulated) tierBoost++;
			if(woodSingularity) tierBoost++;
			return FBTier.changeTier(tier, tierBoost);
		} else {
			return tier;
		}
	}
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		FBTier tier = getStackTier(stack);
		tooltip.add(new StringTextComponent(tier.color.toString() + tooltip.get(0).getString()));
		tooltip.remove(0);
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
		
		boolean recombobulated = false;
		if(stack.getTag() != null) recombobulated = (stack.getTag().getByte("Recombobulated") == 1);
		String color = tier.color.toString();
		String bold = TextFormatting.BOLD.toString();
		String obfuscated = TextFormatting.OBFUSCATED.toString();
		String reset = TextFormatting.RESET.toString();
		if(!recombobulated) tooltip.add(new StringTextComponent(color + bold + tier.name.getString() + " " + this.getFBItemType().getDisplayName()));
		else tooltip.add(new StringTextComponent(color + bold + obfuscated + "n " + reset + color + bold + tier.name.getString() + " " + this.getFBItemType().getDisplayName() + obfuscated + " n"));
	}
	
	@Override
	public ITextComponent getDisplayName(ItemStack stack) {
		FBTier tier = getStackTier(stack);
		String color = tier.color.toString();
		return new StringTextComponent(color + new TranslationTextComponent(this.getTranslationKey(stack)).getString());
	}
	
	@Override
	public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
		if (this.isInGroup(group)) {
			for(Potion potion : ForgeRegistries.POTION_TYPES) {
				if (potion != Potions.EMPTY && potion.getRegistryName().getNamespace().equals(ForgeBlock.MOD_ID)) {
					items.add(PotionUtils.addPotionToItemStack(new ItemStack(this), potion));
				}
			}
		}
	}

}
