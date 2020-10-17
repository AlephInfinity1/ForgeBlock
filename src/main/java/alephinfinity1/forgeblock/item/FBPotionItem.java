package alephinfinity1.forgeblock.item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

import alephinfinity1.forgeblock.ForgeBlock;
import alephinfinity1.forgeblock.misc.FBItemType;
import alephinfinity1.forgeblock.misc.TextFormatHelper;
import alephinfinity1.forgeblock.misc.tier.FBTier;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.PotionItem;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectUtils;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.stats.Stats;
import net.minecraft.util.NonNullList;
import net.minecraft.util.Tuple;
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
	public boolean hasEffect(ItemStack stack) {
		int maxAmplifier = 0;
		for(EffectInstance effect : getEffectsFromStack(stack)) {
			if(effect.getAmplifier() > maxAmplifier) {
				maxAmplifier = effect.getAmplifier();
			}
		}
		return maxAmplifier >= 4;
	}

	@Override
	public FBTier getStackTier(ItemStack stack) {
		List<EffectInstance> effects = getEffectsFromStack(stack);
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
			tier = FBTier.MYTHIC;
			break;
		case 12:
		case 13:
		case 14:
		case 15:
		case 16:
		case 17:
		case 18:
		case 19:
			tier = FBTier.SUPREME;
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
		
		addPotionTooltip(stack, tooltip, 1.0f);
		
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
	
	//TODO complete potion logic
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
		PlayerEntity playerentity = entityLiving instanceof PlayerEntity ? (PlayerEntity)entityLiving : null;
		if (playerentity instanceof ServerPlayerEntity) {
			CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayerEntity)playerentity, stack);
		}

		if (!worldIn.isRemote) {
			for(EffectInstance effectinstance : getEffectsFromStack(stack)) {
				if (effectinstance.getPotion().isInstant()) {
					effectinstance.getPotion().affectEntity(playerentity, playerentity, entityLiving, effectinstance.getAmplifier(), 1.0D);
				} else {
					entityLiving.addPotionEffect(new EffectInstance(effectinstance));
				}
			}
		}

		if (playerentity != null) {
			playerentity.addStat(Stats.ITEM_USED.get(this));
			if (!playerentity.abilities.isCreativeMode) {
				stack.shrink(1);
			}
		}

		if (playerentity == null || !playerentity.abilities.isCreativeMode) {
			if (stack.isEmpty()) {
				return new ItemStack(Items.GLASS_BOTTLE);
			}

			if (playerentity != null) {
				playerentity.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
			}
		}

		return stack;
	}
	
	private static final Function<Byte, Double> DURATION_MULTIPLIER = (duration) -> {
		switch(duration) {
		case 1:
			return 8.0/3.0;
		case 2:
			return 16.0/3.0;
		case 3:
			return 40.0/3.0;
		default:
			return 1.0;
		}
	};
	
	
	//Note: Only use this for FB potions! Do not use for anything else
	public static List<EffectInstance> getEffectsFromStack(ItemStack stack) {
		List<EffectInstance> list = new ArrayList<>();
		if(stack.getTag() == null) return list; //If null tag, return no effects.
		list.addAll(PotionUtils.getPotionTypeFromNBT(stack.getTag()).getEffects());
		//Modifiers
		byte durationModifier = stack.getTag().getByte("DurationModifier");
		byte amplifierModifier = stack.getTag().getByte("AmplifierModifier");
		
		//Edits each effect to their new version.
		//Using numeric iteration here for editing purposes.
		for(int i = 0; i < list.size(); i++) {
			EffectInstance old = list.get(i);
			EffectInstance _new = new EffectInstance(old.getPotion(), 
					(int) (old.getDuration() * DURATION_MULTIPLIER.apply(durationModifier)),
					old.getAmplifier() + amplifierModifier, 
					old.isAmbient(), old.doesShowParticles());
			list.set(i, _new);
		}
		return list;
	}
	
	/*
	 * A modified version of PotionUtils#addPotionTooltip
	 */
	public static void addPotionTooltip(ItemStack itemIn, List<ITextComponent> lores, float durationFactor) {
		List<EffectInstance> list = getEffectsFromStack(itemIn);
		List<Tuple<String, AttributeModifier>> list1 = Lists.newArrayList();
		if (list.isEmpty()) {
			lores.add((new TranslationTextComponent("effect.none")).applyTextStyle(TextFormatting.GRAY));
		} else {
			for(EffectInstance effectinstance : list) {
				ITextComponent itextcomponent = new TranslationTextComponent(effectinstance.getEffectName());
				Effect effect = effectinstance.getPotion();
				Map<IAttribute, AttributeModifier> map = effect.getAttributeModifierMap();
				if (!map.isEmpty()) {
					for(Entry<IAttribute, AttributeModifier> entry : map.entrySet()) {
						AttributeModifier attributemodifier = entry.getValue();
						AttributeModifier attributemodifier1 = new AttributeModifier(attributemodifier.getName(), effect.getAttributeModifierAmount(effectinstance.getAmplifier(), attributemodifier), attributemodifier.getOperation());
						list1.add(new Tuple<>(entry.getKey().getName(), attributemodifier1));
					}
				}

				if (effectinstance.getAmplifier() > 0) {
					itextcomponent.appendText(" ").appendSibling(new TranslationTextComponent("potion.potency." + effectinstance.getAmplifier()));
				}

				if (effectinstance.getDuration() > 20) {
					itextcomponent.appendText(" (").appendText(EffectUtils.getPotionDurationString(effectinstance, durationFactor)).appendText(")");
				}

				lores.add(itextcomponent.applyTextStyle(effect.getEffectType().getColor()));
			}
		}

		if (!list1.isEmpty()) {
			lores.add(new StringTextComponent(""));
			lores.add((new TranslationTextComponent("potion.whenDrank")).applyTextStyle(TextFormatting.DARK_PURPLE));

			Builder<String, AttributeModifier> builder = ImmutableMultimap.builder();
			for(Tuple<String, AttributeModifier> tuple : list1) {
				builder.put(tuple.getA(), tuple.getB());
			}
			Multimap<String, AttributeModifier> modifierMap = builder.build();
			lores.addAll(TextFormatHelper.formatModifierMap(modifierMap, null, FBTier.COMMON));
			lores.add(new StringTextComponent(""));
		}

	}

}
