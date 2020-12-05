package alephinfinity1.forgeblock.misc;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.collect.Multimap;

import alephinfinity1.forgeblock.attribute.FBAttributes;
import alephinfinity1.forgeblock.attribute.ModifierHelper;
import alephinfinity1.forgeblock.config.FBModConfig;
import alephinfinity1.forgeblock.enchantment.IFBEnchantment;
import alephinfinity1.forgeblock.enchantment.UltimateEnchantment;
import alephinfinity1.forgeblock.misc.itemreqs.IRequirementPredicate;
import alephinfinity1.forgeblock.misc.itemreqs.SkillRequirementPredicate;
import alephinfinity1.forgeblock.misc.reforge.Reforge;
import alephinfinity1.forgeblock.misc.stats_modifier.AbstractStatsModifier;
import alephinfinity1.forgeblock.misc.stats_modifier.capability.IItemModifiers;
import alephinfinity1.forgeblock.misc.stats_modifier.capability.ItemModifiersProvider;
import alephinfinity1.forgeblock.misc.tier.FBTier;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.registries.ForgeRegistries;

public class TextFormatHelper {
	
	private static final Map<Integer, String> EMPTY_DICTIONARY = new HashMap<>();
	private static final Map<Integer, String> SINGLE_LETTER_DICTIONARY = new HashMap<>();
	private static final Map<Integer, String> AA_DICTIONARY = new HashMap<>();
	private static final Map<Integer, String> SI_DICTIONARY = new HashMap<>();
	
	static {
		SINGLE_LETTER_DICTIONARY.put(0, "");
		SINGLE_LETTER_DICTIONARY.put(1, "K");
		SINGLE_LETTER_DICTIONARY.put(2, "M");
		SINGLE_LETTER_DICTIONARY.put(3, "B");
		SINGLE_LETTER_DICTIONARY.put(4, "t");
		SINGLE_LETTER_DICTIONARY.put(5, "q");
		SINGLE_LETTER_DICTIONARY.put(6, "Q");
		SINGLE_LETTER_DICTIONARY.put(7, "s");
		SINGLE_LETTER_DICTIONARY.put(8, "S");
		SINGLE_LETTER_DICTIONARY.put(9, "o");
		SINGLE_LETTER_DICTIONARY.put(10, "n");
		SINGLE_LETTER_DICTIONARY.put(11, "d");
		SINGLE_LETTER_DICTIONARY.put(12, "U");
		SINGLE_LETTER_DICTIONARY.put(13, "D");
		SINGLE_LETTER_DICTIONARY.put(14, "T");
		SINGLE_LETTER_DICTIONARY.put(15, "Qt");
		SINGLE_LETTER_DICTIONARY.put(16, "Qd");
		SINGLE_LETTER_DICTIONARY.put(17, "Sd");
		SINGLE_LETTER_DICTIONARY.put(18, "St");
		SINGLE_LETTER_DICTIONARY.put(19, "O");
		SINGLE_LETTER_DICTIONARY.put(20, "N");
		SINGLE_LETTER_DICTIONARY.put(21, "v");
		SINGLE_LETTER_DICTIONARY.put(22, "c");
		
		AA_DICTIONARY.put(0, "");
		AA_DICTIONARY.put(1, "k");
		AA_DICTIONARY.put(2, "M");
		AA_DICTIONARY.put(3, "B");
		AA_DICTIONARY.put(4, "T");
		AA_DICTIONARY.put(5, "aa");
		AA_DICTIONARY.put(6, "ab");
		AA_DICTIONARY.put(7, "ac");
		AA_DICTIONARY.put(8, "ad");
		AA_DICTIONARY.put(9, "ae");
		AA_DICTIONARY.put(10, "af");
		AA_DICTIONARY.put(11, "ag");
		AA_DICTIONARY.put(12, "ah");
		AA_DICTIONARY.put(13, "ai");
		AA_DICTIONARY.put(14, "aj");
		AA_DICTIONARY.put(15, "ak");
		AA_DICTIONARY.put(16, "al");
		AA_DICTIONARY.put(17, "am");
		AA_DICTIONARY.put(18, "an");
		AA_DICTIONARY.put(19, "ao");
		AA_DICTIONARY.put(20, "ap");
		AA_DICTIONARY.put(21, "aq");
		AA_DICTIONARY.put(22, "ar");
		AA_DICTIONARY.put(23, "as");
		AA_DICTIONARY.put(24, "at");
		AA_DICTIONARY.put(25, "au");
		AA_DICTIONARY.put(26, "av");
		AA_DICTIONARY.put(27, "aw");
		AA_DICTIONARY.put(28, "ax");
		AA_DICTIONARY.put(29, "ay");
		AA_DICTIONARY.put(30, "az");
		AA_DICTIONARY.put(31, "ba");
		AA_DICTIONARY.put(32, "bb");
		AA_DICTIONARY.put(33, "bc");
		AA_DICTIONARY.put(34, "bd");
		AA_DICTIONARY.put(35, "be");
		AA_DICTIONARY.put(36, "bf");
		AA_DICTIONARY.put(37, "bg");
		AA_DICTIONARY.put(38, "bh");
		AA_DICTIONARY.put(39, "bi");
		AA_DICTIONARY.put(40, "bj");
		AA_DICTIONARY.put(41, "bk");
		AA_DICTIONARY.put(42, "bl");
		AA_DICTIONARY.put(43, "bm");
		AA_DICTIONARY.put(44, "bn");
		
		SI_DICTIONARY.put(0, "");
		SI_DICTIONARY.put(1, "k");
		SI_DICTIONARY.put(2, "M");
		SI_DICTIONARY.put(3, "G");
		SI_DICTIONARY.put(4, "T");
		SI_DICTIONARY.put(5, "P");
		SI_DICTIONARY.put(6, "E");
		SI_DICTIONARY.put(7, "Z");
		SI_DICTIONARY.put(8, "Y");
	}
	
	public enum SuffixType {
		OFF(EMPTY_DICTIONARY, 0),
		SINGLE_LETTER(SINGLE_LETTER_DICTIONARY, 22),
		AA(AA_DICTIONARY, 44),
		SI(SI_DICTIONARY, 8);
		
		public Map<Integer, String> dictionary;
		public int maxMagnitude; //The maximum power, in powers of 1000, that this system can represent.
		
		private SuffixType(Map<Integer, String> dictionary, int maxMagnitude) {
			this.dictionary = dictionary;
			this.maxMagnitude = maxMagnitude;
		}
	}

	/**
	 * Formats a number.
	 * @param amount
	 * @return
	 */
	public static String formatDouble(double amount) {
		StringBuffer str = new StringBuffer(new DecimalFormat(",###.#").format(amount));
		if(amount > 0.0D) {
			str.insert(0, '+');
		}
		return str.toString().replaceAll("\u00A0", ",");
	}
	
	public static String formatLargeNumberWithSuffix(SuffixType suffix, double number) {
		if(suffix == SuffixType.OFF) return Double.toString(number);
		int power = (int) Math.floor(Math.log10(number));
		if(power > suffix.maxMagnitude * 3 + 2) return (new DecimalFormat("0.#E0")).format(number);
		int magnitude = power / 3;
		double c = number / Math.pow(10.0, magnitude * 3); 
		String suffixStr = suffix.dictionary.get(magnitude);
		return (new DecimalFormat("0.#")).format(c) + suffixStr;
	}
	
	public static String formatLargeNumberWithSuffix(SuffixType suffix, double number, int dp) {
		if(suffix == SuffixType.OFF) return Double.toString(number);
		int power = (int) Math.floor(Math.log10(number));
		if(power > suffix.maxMagnitude * 3 + 2) return (new DecimalFormat("0.#E0")).format(number);
		int magnitude = power / 3;
		double c = number / Math.pow(10.0, magnitude * 3); 
		String suffixStr = suffix.dictionary.get(magnitude);
		switch(dp) {
		case 0:
			return (new DecimalFormat("#")).format(c) + suffixStr;
		case 1:
			return (new DecimalFormat("#.#")).format(c) + suffixStr;
		case 2:
			return (new DecimalFormat("#.##")).format(c) + suffixStr;
		case 3:
			return (new DecimalFormat("#.###")).format(c) + suffixStr;
		case 4:
			return (new DecimalFormat("#.####")).format(c) + suffixStr;
		default:
			return (new DecimalFormat("#.#")).format(c) + suffixStr;
		}
		
	}
	
	/**
	 * Converts an integer to a roman numeral.
	 * @param value
	 * @return
	 */
	public static String getRomanNumeral(int value) {
		if(value == 5000) return "TOO_HIGH_NUMBER";
		if(value > 3999 || value < 1) {
			return Integer.toString(value);
		}
		String ones = "", tens = "", hundreds = "", thousands = "";
		
		switch(value % 10) {
		case 0:
			ones = "";
			break;
		case 1:
			ones = "I";
			break;
		case 2:
			ones = "II";
			break;
		case 3:
			ones = "III";
			break;
		case 4:
			ones = "IV";
			break;
		case 5:
			ones = "V";
			break;
		case 6:
			ones = "VI";
			break;
		case 7:
			ones = "VII";
			break;
		case 8:
			ones = "VIII";
			break;
		case 9:
			ones = "IX";
			break;
		}
		
		switch(value / 10 % 10) {
		case 0:
			tens = "";
			break;
		case 1:
			tens = "X";
			break;
		case 2:
			tens = "XX";
			break;
		case 3:
			tens = "XXX";
			break;
		case 4:
			tens = "XL";
			break;
		case 5:
			tens = "L";
			break;
		case 6:
			tens = "LX";
			break;
		case 7:
			tens = "LXX";
			break;
		case 8:
			tens = "LXXX";
			break;
		case 9:
			tens = "XC";
			break;
		}
		
		switch(value / 100 % 10) {
		case 0:
			hundreds = "";
			break;
		case 1:
			hundreds = "C";
			break;
		case 2:
			hundreds = "CC";
			break;
		case 3:
			hundreds = "CCC";
			break;
		case 4:
			hundreds = "CD";
			break;
		case 5:
			hundreds = "D";
			break;
		case 6:
			hundreds = "DC";
			break;
		case 7:
			hundreds = "DCC";
			break;
		case 8:
			hundreds = "DCCC";
			break;
		case 9:
			hundreds = "CM";
			break;
		}
		
		switch(value / 1000) {
		case 0:
			thousands = "";
			break;
		case 1:
			thousands = "M";
			break;
		case 2:
			thousands = "MM";
			break;
		case 3:
			thousands = "MMM";
			break;
		}
		
		return thousands + hundreds + tens + ones;
	}
	
	/**
	 * Formats a modifier map present on an item. <br>
	 * Should only be used on Swords, Bows, Armours, and Tools. <br>
	 * @param modifiers
	 * @param reforge
	 * @param tier
	 * @param extraModifiers
	 * @param stack
	 * @return
	 */
	public static List<ITextComponent> formatModifierMap(Multimap<String, AttributeModifier> modifiers, @Nullable Reforge reforge, FBTier tier, @Nullable IItemModifiers extraModifiers, @Nullable ItemStack stack) {
		
		List<ITextComponent> tooltip = new ArrayList<>();
		boolean offensive = false, defensive = false, extras = false, xp = false, slayerLuck = false;
		
		AttributeDisplayType displayType = AttributeDisplayType.TEXT;
		if(FBModConfig.ATTRIBUTE_DISPLAY_TYPE != null) {
			if(FBModConfig.ATTRIBUTE_DISPLAY_TYPE.get() != null) displayType = FBModConfig.ATTRIBUTE_DISPLAY_TYPE.get();
		}
		
		Multimap<String, AttributeModifier> reforgeModifiers;
		if(reforge == null) reforgeModifiers = ModifierHelper.emptyModifier();
		else reforgeModifiers = reforge.getModifierMapByTier(tier, UUID.randomUUID());
		
		String reforgeName;
		if(reforge == null) reforgeName = "";
		else reforgeName = reforge.getDisplayName();
		
		for(IAttribute attribute : FBAttributes.OFFENSIVE_ATTRIBUTES) {
			double value = 0.0D;
			double reforgeValue = 0.0D;
			for(AttributeModifier modifier : modifiers.get(attribute.getName())) {
				if(modifier.getOperation().equals(Operation.ADDITION)) {
					value += modifier.getAmount();
				}
			}
			
			for(AttributeModifier modifier : reforgeModifiers.get(attribute.getName())) {
				if(modifier.getOperation().equals(Operation.ADDITION)) {
					reforgeValue += modifier.getAmount();
				}
			}
			
			if(!MathHelper.epsilonEquals(value, 0.0D) || !MathHelper.epsilonEquals(reforgeValue, 0.0D)) {
				offensive = true;
				if(attribute.equals(FBAttributes.CRIT_CHANCE) || attribute.equals(FBAttributes.CRIT_DAMAGE)) {
					tooltip.add(formatModifier(value, reforgeValue, attribute.getName(), reforgeName, AttributeType.OFFENSIVE, 1, "%", displayType, stack.getCapability(ItemModifiersProvider.ITEM_MODIFIERS_CAPABILITY).orElse(null), stack));
				} else {
					tooltip.add(formatModifier(value, reforgeValue, attribute.getName(), reforgeName, AttributeType.OFFENSIVE, 1, "", displayType, stack.getCapability(ItemModifiersProvider.ITEM_MODIFIERS_CAPABILITY).orElse(null), stack));
				}
			}
		}
		
		for(IAttribute attribute : FBAttributes.DEFENSIVE_ATTRIBUTES) {
			double value = 0.0D;
			double reforgeValue = 0.0D;
			for(AttributeModifier modifier : modifiers.get(attribute.getName())) {
				if(modifier.getOperation().equals(Operation.ADDITION)) {
					value += modifier.getAmount();
				}
			}
			
			for(AttributeModifier modifier : reforgeModifiers.get(attribute.getName())) {
				if(modifier.getOperation().equals(Operation.ADDITION)) {
					reforgeValue += modifier.getAmount();
				}
			}
			
			if(!MathHelper.epsilonEquals(value, 0.0D) || !MathHelper.epsilonEquals(reforgeValue, 0.0D)) {
				if(defensive == false) {
					if(offensive == true) tooltip.add(new StringTextComponent(""));
					defensive = true;
				}
					
				if(attribute.equals(SharedMonsterAttributes.MAX_HEALTH)) {
					tooltip.add(formatModifier(value, reforgeValue, attribute.getName(), reforgeName, AttributeType.DEFENSIVE, 1, " HP", displayType, stack.getCapability(ItemModifiersProvider.ITEM_MODIFIERS_CAPABILITY).orElse(null), stack));
				} else if(attribute.equals(SharedMonsterAttributes.MOVEMENT_SPEED)) {
					tooltip.add(formatModifier(value, reforgeValue, attribute.getName(), reforgeName, AttributeType.DEFENSIVE, 1000, "", displayType, stack.getCapability(ItemModifiersProvider.ITEM_MODIFIERS_CAPABILITY).orElse(null), stack));
				} else {
					tooltip.add(formatModifier(value, reforgeValue, attribute.getName(), reforgeName, AttributeType.DEFENSIVE, 1, "", displayType, stack.getCapability(ItemModifiersProvider.ITEM_MODIFIERS_CAPABILITY).orElse(null), stack));
				}
			}
		}
		
		displayType = AttributeDisplayType.TEXT;
		
		for(IAttribute attribute : FBAttributes.EXTRA_ATTRIBUTES) {
			double value = 0.0D;
			double reforgeValue = 0.0D;
			for(AttributeModifier modifier : modifiers.get(attribute.getName())) {
				if(modifier.getOperation().equals(Operation.ADDITION)) {
					value += modifier.getAmount();
				}
			}
			
			for(AttributeModifier modifier : reforgeModifiers.get(attribute.getName())) {
				if(modifier.getOperation().equals(Operation.ADDITION)) {
					reforgeValue += modifier.getAmount();
				}
			}
			
			if(!MathHelper.epsilonEquals(value, 0.0D) || !MathHelper.epsilonEquals(reforgeValue, 0.0D)) {
				if(extras == false) {
					if(offensive == true || defensive == true) tooltip.add(new StringTextComponent(""));
					extras = true;
				}
					
				if(attribute.equals(FBAttributes.DODGE)) {
					tooltip.add(formatModifier(value, reforgeValue, attribute.getName(), reforgeName, AttributeType.EXTRAS, 1, "%", displayType, stack.getCapability(ItemModifiersProvider.ITEM_MODIFIERS_CAPABILITY).orElse(null), stack));
				} else if (attribute.equals(FBAttributes.HEALTH_REGEN) || attribute.equals(FBAttributes.MANA_REGEN)) {
					tooltip.add(formatModifier(value, reforgeValue, attribute.getName(), reforgeName, AttributeType.EXTRAS, 1, "/s", displayType, stack.getCapability(ItemModifiersProvider.ITEM_MODIFIERS_CAPABILITY).orElse(null), stack));
				} else {
					tooltip.add(formatModifier(value, reforgeValue, attribute.getName(), reforgeName, AttributeType.EXTRAS, 1, "", displayType, stack.getCapability(ItemModifiersProvider.ITEM_MODIFIERS_CAPABILITY).orElse(null), stack));
				}
			}
		}
		
		for(IAttribute attribute : FBAttributes.SKILL_XP_BOOSTS) {
			double value = 0.0D;
			double reforgeValue = 0.0D;
			for(AttributeModifier modifier : modifiers.get(attribute.getName())) {
				if(modifier.getOperation().equals(Operation.ADDITION)) {
					value += modifier.getAmount();
				}
			}
			
			for(AttributeModifier modifier : reforgeModifiers.get(attribute.getName())) {
				if(modifier.getOperation().equals(Operation.ADDITION)) {
					reforgeValue += modifier.getAmount();
				}
			}
			
			if(!MathHelper.epsilonEquals(value, 0.0D) || !MathHelper.epsilonEquals(reforgeValue, 0.0D)) {
				if(xp == false) {
					if(offensive == true || defensive == true || extras == true) tooltip.add(new StringTextComponent(""));
					xp = true;
				}
				tooltip.add(formatModifier(value, reforgeValue, attribute.getName(), reforgeName, AttributeType.XP, 1, "%", displayType, stack.getCapability(ItemModifiersProvider.ITEM_MODIFIERS_CAPABILITY).orElse(null), stack));
			}
		}
		
		for(IAttribute attribute : FBAttributes.SLAYER_LUCKS) {
			double value = 0.0D;
			double reforgeValue = 0.0D;
			for(AttributeModifier modifier : modifiers.get(attribute.getName())) {
				if(modifier.getOperation().equals(Operation.ADDITION)) {
					value += modifier.getAmount();
				}
			}
			
			for(AttributeModifier modifier : reforgeModifiers.get(attribute.getName())) {
				if(modifier.getOperation().equals(Operation.ADDITION)) {
					reforgeValue += modifier.getAmount();
				}
			}
			
			if(!MathHelper.epsilonEquals(value, 0.0D) || !MathHelper.epsilonEquals(reforgeValue, 0.0D)) {
				if(slayerLuck == false) {
					if(offensive == true || defensive == true || extras == true || xp == true) tooltip.add(new StringTextComponent(""));
					slayerLuck = true;
				}
					
				tooltip.add(formatModifier(value, reforgeValue, attribute.getName(), reforgeName, AttributeType.SLAYER_LUCK, 1, "", displayType, stack.getCapability(ItemModifiersProvider.ITEM_MODIFIERS_CAPABILITY).orElse(null), stack));
			}
		}
		
		return tooltip;
		
	}
	
	enum AttributeType {
		OFFENSIVE, DEFENSIVE, EXTRAS, XP, SLAYER_LUCK;
	}
	
	/**
	 * Formats a single attribute modifier, in the item style.
	 * @param value
	 * @param reforgeValue
	 * @param attributeName
	 * @param reforgeName
	 * @param type
	 * @param scale
	 * @param suffix
	 * @param displayType
	 * @param extraModifier
	 * @param stack
	 * @return
	 */
	public static ITextComponent formatModifier(double value, double reforgeValue, String attributeName, String reforgeName, AttributeType type, double scale, String suffix, AttributeDisplayType displayType, @Nullable IItemModifiers extraModifier, @Nullable ItemStack stack) {
		List<ITextComponent> tooltip = new ArrayList<>();
		
		String color;
		switch(type) {
		case OFFENSIVE:
			color = TextFormatting.RED.toString();
			break;
		case DEFENSIVE:
		case EXTRAS:
			color = TextFormatting.GREEN.toString();
			break;
		case XP:
			color = TextFormatting.AQUA.toString();
			break;
		case SLAYER_LUCK:
			color = TextFormatting.LIGHT_PURPLE.toString();
			break;
		default:
			color = "";
			break;
		}
		
		String attrName;
		if(attributeName.equals("generic.attackDamage")) attrName = "attribute.name.forgeblock.damage";
		else if(attributeName.equals("generic.maxHealth")) attrName = "attribute.name.forgeblock.health";
		else if(attributeName.equals("generic.movementSpeed")) attrName = "attribute.name.forgeblock.speed";
		else attrName = "attribute.name." + attributeName;
		
		final int PREFIX_LENGTH = 26; //The length of 'attribute.name.forgeblock.'. Used to avoid magic numbers.
		
		if(type.equals(AttributeType.OFFENSIVE) || type.equals(AttributeType.DEFENSIVE)) {
			String iconName = "misc.forgeblock.attributeicon." + attrName.substring(PREFIX_LENGTH);
			String shortName = "misc.forgeblock.attributeshort." + attrName.substring(PREFIX_LENGTH);
			
			switch(displayType) {
			case BOTH:
				if(value != 0.0D) {
					if(reforgeValue == 0.0D) tooltip.add(new StringTextComponent(new TranslationTextComponent(iconName).getString() + " " + TextFormatting.GRAY.toString() + new TranslationTextComponent(attrName).getString() + ": " + color + TextFormatHelper.formatDouble(value * scale) + suffix));
					else tooltip.add(new StringTextComponent(new TranslationTextComponent(iconName).getString() + " " + TextFormatting.GRAY.toString() + new TranslationTextComponent(attrName).getString() + ": " + color + TextFormatHelper.formatDouble(value * scale) + suffix + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + TextFormatHelper.formatDouble(reforgeValue * scale) + suffix + ")"));
				} else if(reforgeValue != 0.0D) {
					tooltip.add(new StringTextComponent(new TranslationTextComponent(iconName).getString() + " " + TextFormatting.GRAY.toString() + new TranslationTextComponent(attrName).getString() + ": " + color + TextFormatHelper.formatDouble(value * scale) + suffix + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + TextFormatHelper.formatDouble(reforgeValue * scale) + suffix + ")"));
				}
				break;
			case SHORT:
				if(value != 0.0D) {
					if(reforgeValue == 0.0D) tooltip.add(new StringTextComponent(new TranslationTextComponent(shortName).getString() + TextFormatting.GRAY.toString() + ": " + color + TextFormatHelper.formatDouble(value * scale) + suffix));
					else tooltip.add(new StringTextComponent(new TranslationTextComponent(shortName).getString() + TextFormatting.GRAY.toString() + ": " + color + TextFormatHelper.formatDouble(value * scale) + suffix + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + TextFormatHelper.formatDouble(reforgeValue * scale) + suffix + ")"));
				} else if(reforgeValue != 0.0D) {
					tooltip.add(new StringTextComponent(new TranslationTextComponent(shortName).getString() + TextFormatting.GRAY.toString() + ": " + color + TextFormatHelper.formatDouble(value * scale) + suffix + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + TextFormatHelper.formatDouble(reforgeValue * scale) + suffix + ")"));
				}
				break;
			case TEXT:
				if(value != 0.0D) {
					if(reforgeValue == 0.0D) tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent(attrName).getString() + ": " + color + TextFormatHelper.formatDouble(value * scale) + suffix));
					else tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent(attrName).getString() + ": " + color + TextFormatHelper.formatDouble(value * scale) + suffix + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + TextFormatHelper.formatDouble(reforgeValue * scale) + suffix + ")"));
				} else if(reforgeValue != 0.0D) {
					tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent(attrName).getString() + ": " + color + TextFormatHelper.formatDouble(value * scale) + suffix + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + TextFormatHelper.formatDouble(reforgeValue * scale) + suffix + ")"));
				}
				break;
			default:
				break;
			
			}
		}
		
		if(value != 0.0D) {
			if(reforgeValue == 0.0D) tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent(attrName).getString() + ": " + color + TextFormatHelper.formatDouble(value * scale) + suffix));
			else tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent(attrName).getString() + ": " + color + TextFormatHelper.formatDouble(value * scale) + suffix + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + TextFormatHelper.formatDouble(reforgeValue * scale) + suffix + ")"));
		} else if(reforgeValue != 0.0D) {
			tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent(attrName).getString() + ": " + color + TextFormatHelper.formatDouble(value * scale) + suffix + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + TextFormatHelper.formatDouble(reforgeValue * scale) + suffix + ")"));
		}
		
		if(extraModifier != null)
			return tooltip.get(0).appendSibling(formatExtras(attributeName, scale, suffix, extraModifier, stack));
		else
			return tooltip.get(0);
		
	}
	
	/**
	 * Formats enchantments.
	 * @param stack
	 * @return
	 */
	public static List<ITextComponent> formatEnchantments(ItemStack stack) {
		return TextFormatHelper.formatEnchantments(stack, EnchantmentHelper.getEnchantments(stack));
	}
	
	/**
	 * Formats enchantments.
	 * @param stack
	 * @param enchantments
	 * @return
	 */
	private static List<ITextComponent> formatEnchantments(ItemStack stack, Map<Enchantment, Integer> enchantments) {
		List<ITextComponent> list = new ArrayList<>();
		
		Set<Map.Entry<Enchantment, Integer>> set = enchantments.entrySet();
		for(Map.Entry<Enchantment, Integer> entry : set) {
			if(entry.getKey() instanceof UltimateEnchantment) {
				list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE.toString() + TextFormatting.BOLD.toString() + new TranslationTextComponent(entry.getKey().getName()).getString() + " " + TextFormatHelper.getRomanNumeral(entry.getValue())));
			} else {
				if (entry.getKey() instanceof IFBEnchantment) {
					if (entry.getValue() > ((IFBEnchantment) entry.getKey()).getEnchantingTableMaxLevel()) {
						list.add(new StringTextComponent((FBModConfig.ENABLE_GOLDEN_ENCHANTS.get() ? TextFormatting.GOLD.toString() : TextFormatting.BLUE.toString()) + new TranslationTextComponent(entry.getKey().getName()).getString() + " " + TextFormatHelper.getRomanNumeral(entry.getValue())));
					} else {
						list.add(new StringTextComponent(TextFormatting.BLUE.toString() + new TranslationTextComponent(entry.getKey().getName()).getString() + " " + TextFormatHelper.getRomanNumeral(entry.getValue())));
					}
				} else if (entry.getValue() > entry.getKey().getMaxLevel()) {
					list.add(new StringTextComponent((FBModConfig.ENABLE_GOLDEN_ENCHANTS.get() ? TextFormatting.GOLD.toString() : TextFormatting.BLUE.toString()) + new TranslationTextComponent(entry.getKey().getName()).getString() + " " + TextFormatHelper.getRomanNumeral(entry.getValue())));
				} else {
					list.add(new StringTextComponent(TextFormatting.BLUE.toString() + new TranslationTextComponent(entry.getKey().getName()).getString() + " " + TextFormatHelper.getRomanNumeral(entry.getValue())));
				}
			}
		}
		
		if(!set.isEmpty()) list.add(new StringTextComponent(""));
		
		return list;
	}
	
	/**
	 * Formats enchantments, through a given ListNBT.
	 * @param stack
	 * @param enchants
	 * @return
	 */
	public static List<ITextComponent> formatEnchantments(ItemStack stack, ListNBT enchants) {
		Map<Enchantment, Integer> enchantments = new HashMap<>();
		for(INBT nbt : enchants) {
			CompoundNBT compound = (CompoundNBT) nbt;
			Enchantment ench = ForgeRegistries.ENCHANTMENTS.getValue(ResourceLocation.tryCreate(compound.getString("id")));
			if(ench != null) enchantments.put(ench, compound.getInt("lvl"));
		}	
		
		return TextFormatHelper.formatEnchantments(stack, enchantments);
	}
	
	/**
	 * Formats extra modifiers provided by IItemModifiers capability.
	 * @param attributeName
	 * @param scale
	 * @param suffix
	 * @param extraModifier
	 * @param stack
	 * @return
	 */
	public static ITextComponent formatExtras(String attributeName, double scale, String suffix, @Nonnull IItemModifiers extraModifier, @Nonnull ItemStack stack) {
		ITextComponent tc = new StringTextComponent(""); //Initialise empty component first, append later.
		Map<AbstractStatsModifier, CompoundNBT> modMap = extraModifier.getMap();
		Set<Map.Entry<AbstractStatsModifier, CompoundNBT>> modSet = modMap.entrySet();
		for(Map.Entry<AbstractStatsModifier, CompoundNBT> entry : modSet) { //Apply for every modifier in the map
			TextFormatting color = entry.getKey().getColor();
			if(color.equals(TextFormatting.RESET)) continue; //If set to RESET, the modifier is not displayed.	
			Multimap<String, AttributeModifier> modifiers = entry.getKey().getModifiers(stack, entry.getValue());
			double value = 0.0D;
			for(Map.Entry<String, AttributeModifier> attr : modifiers.entries()) { //Get all additional modifiers
				if(!attr.getKey().equals(attributeName)) continue;
				tc.appendSibling(new StringTextComponent(" "));	
				if(attr.getValue().getOperation().equals(AttributeModifier.Operation.ADDITION)) {
					value += attr.getValue().getAmount();
				}
			}
			if(!MathHelper.epsilonEquals(value, 0.0D)) { //Only apply modifier if non-zero.
				ITextComponent modTc = new StringTextComponent("(").appendText(formatDouble(value * scale)).appendText(suffix).appendText(")").applyTextStyle(color);
				tc.appendSibling(modTc);
			}
		}
		return tc;
	}
	
	/**
	 * Gets the representative colour of an attribute. Gray for secondary attributes.
	 * @param attr
	 * @return
	 */
	private static TextFormatting getAttributeColor(IAttribute attr) {
		if (attr.equals(SharedMonsterAttributes.ATTACK_DAMAGE)) return TextFormatting.RED;
		else if (attr.equals(FBAttributes.STRENGTH)) return TextFormatting.RED;
		else if (attr.equals(FBAttributes.CRIT_CHANCE)) return TextFormatting.BLUE;
		else if (attr.equals(FBAttributes.CRIT_DAMAGE)) return TextFormatting.BLUE;
		else if (attr.equals(FBAttributes.BONUS_ATTACK_SPEED)) return TextFormatting.YELLOW;
		else if (attr.equals(FBAttributes.SEA_CREATURE_CHANCE)) return TextFormatting.DARK_AQUA;
		else if (attr.equals(SharedMonsterAttributes.MAX_HEALTH)) return TextFormatting.RED;
		else if (attr.equals(FBAttributes.DEFENSE)) return TextFormatting.GREEN;
		else if (attr.equals(SharedMonsterAttributes.MOVEMENT_SPEED)) return TextFormatting.WHITE;
		else if (attr.equals(FBAttributes.INTELLIGENCE)) return TextFormatting.AQUA;
		else if (attr.equals(FBAttributes.TRUE_DEFENSE)) return TextFormatting.WHITE;
		else if (attr.equals(FBAttributes.MAGIC_FIND)) return TextFormatting.AQUA;
		else if (attr.equals(FBAttributes.PET_LUCK)) return TextFormatting.LIGHT_PURPLE;
		else if (attr.equals(FBAttributes.FEROCITY)) return TextFormatting.RED;
		else return TextFormatting.GRAY;
	}
	
	/**
	 * Formats a special modifier, as used in potions, skill rewards, and etc. <br>
	 * E.g. "+100❁ Strength", "+25%❈ Defense" <br>
	 * Should only be used on attributes with an icon and a colour.
	 * @param modifier
	 * @return
	 */
	public static ITextComponent formatSpecialModifier(String attributeName, AttributeModifier modifier) {
		double amount = modifier.getAmount();
		AttributeModifier.Operation operation = modifier.getOperation();
		
		String attrName;
		if (attributeName.equals("generic.attackDamage")) attrName = "attribute.name.forgeblock.damage";
		else if (attributeName.equals("generic.maxHealth")) attrName = "attribute.name.forgeblock.health";
		else if (attributeName.equals("generic.movementSpeed")) attrName = "attribute.name.forgeblock.speed";
		else attrName = "attribute.name." + attributeName;
		
		final int PREFIX_LENGTH = 26; //The length of 'attribute.name.forgeblock.'. Used to avoid magic numbers.
		
		String iconName = "misc.forgeblock.attributeicon." + attrName.substring(PREFIX_LENGTH);
		
		switch (operation) {
		case ADDITION:
			return new StringTextComponent(TextFormatHelper.formatDouble(amount))
				.appendSibling(new TranslationTextComponent(iconName))
				.appendText(" ")
				.appendSibling(new TranslationTextComponent(attrName))
				.applyTextStyle(TextFormatHelper.getAttributeColor(FBAttributes.getPrimaryAttributeByName(attributeName)));
		case MULTIPLY_BASE: //TODO fix formatSpecialModifier for +x% and *x.
			return new StringTextComponent(TextFormatHelper.formatDouble(amount))
				.appendSibling(new TranslationTextComponent(iconName))
				.appendText(" ")
				.appendSibling(new TranslationTextComponent(attrName))
				.applyTextStyle(TextFormatHelper.getAttributeColor(FBAttributes.getPrimaryAttributeByName(attributeName)));
		case MULTIPLY_TOTAL:
			return new StringTextComponent(TextFormatHelper.formatDouble(amount))
				.appendSibling(new TranslationTextComponent(iconName))
				.appendText(" ")
				.appendSibling(new TranslationTextComponent(attrName))
				.applyTextStyle(TextFormatHelper.getAttributeColor(FBAttributes.getPrimaryAttributeByName(attributeName)));
		default:
			return new StringTextComponent(TextFormatHelper.formatDouble(amount))
				.appendSibling(new TranslationTextComponent(iconName))
				.appendText(" ")
				.appendSibling(new TranslationTextComponent(attrName))
				.applyTextStyle(TextFormatHelper.getAttributeColor(FBAttributes.getPrimaryAttributeByName(attributeName)));
		}
	}
	
	public static List<ITextComponent> formatRequirements(IRequirementPredicate predicate) {
		List<ITextComponent> list = new ArrayList<>();
		
		//TODO Add formatting for other types of predicates.
		if (predicate instanceof SkillRequirementPredicate) {	
			list.add(new TranslationTextComponent("text.forgeblock.levelReq", 
					((SkillRequirementPredicate) predicate).getType().getDisplayName(), 
					((SkillRequirementPredicate) predicate).getLevel()).applyTextStyle(TextFormatting.RED));
			list.add(new StringTextComponent(""));
		}
		
		return list;
	}
	
	public static String getUnlocalizedAttrName(IAttribute attribute) {
		String attributeName = attribute.getName();
		
		String attrName;
		if (attributeName.equals("generic.attackDamage")) attrName = "attribute.name.forgeblock.damage";
		else if (attributeName.equals("generic.maxHealth")) attrName = "attribute.name.forgeblock.health";
		else if (attributeName.equals("generic.movementSpeed")) attrName = "attribute.name.forgeblock.speed";
		else attrName = "attribute.name." + attributeName;	
		
		return attrName;
	}

}
