package alephinfinity1.forgeblock.misc;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import com.google.common.collect.Multimap;

import alephinfinity1.forgeblock.attribute.FBAttributes;
import alephinfinity1.forgeblock.attribute.ModifierHelper;
import alephinfinity1.forgeblock.config.CustomModConfig;
import alephinfinity1.forgeblock.misc.reforge.Reforge;
import alephinfinity1.forgeblock.misc.tier.FBTier;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

public class TextFormatHelper {
	
	private static final Map<Integer, String> EMPTY_DICTIONARY = new HashMap<>();
	private static final Map<Integer, String> SINGLE_LETTER_DICTIONARY = new HashMap<>();
	private static final Map<Integer, String> AA_DICTIONARY = new HashMap<>();
	private static final Map<Integer, String> SI_DICTIONARY = new HashMap<>();
	
	static {
		SINGLE_LETTER_DICTIONARY.put(0, "");
		SINGLE_LETTER_DICTIONARY.put(1, "k");
		SINGLE_LETTER_DICTIONARY.put(2, "M");
		SINGLE_LETTER_DICTIONARY.put(3, "B");
		SINGLE_LETTER_DICTIONARY.put(4, "T");
		SINGLE_LETTER_DICTIONARY.put(5, "Qa");
		SINGLE_LETTER_DICTIONARY.put(6, "Qi");
		SINGLE_LETTER_DICTIONARY.put(7, "Sx");
		SINGLE_LETTER_DICTIONARY.put(8, "Sp");
		SINGLE_LETTER_DICTIONARY.put(9, "O");
		SINGLE_LETTER_DICTIONARY.put(10, "N");
		SINGLE_LETTER_DICTIONARY.put(11, "D");
		SINGLE_LETTER_DICTIONARY.put(12, "Ud");
		SINGLE_LETTER_DICTIONARY.put(13, "Dd");
		SINGLE_LETTER_DICTIONARY.put(14, "Td");
		SINGLE_LETTER_DICTIONARY.put(15, "Qad");
		SINGLE_LETTER_DICTIONARY.put(16, "Qid");
		SINGLE_LETTER_DICTIONARY.put(17, "Sxd");
		SINGLE_LETTER_DICTIONARY.put(18, "Spd");
		SINGLE_LETTER_DICTIONARY.put(19, "Od");
		SINGLE_LETTER_DICTIONARY.put(20, "Nd");
		
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
		SINGLE_LETTER(SINGLE_LETTER_DICTIONARY, 20),
		AA(AA_DICTIONARY, 20),
		SI(SI_DICTIONARY, 8);
		
		public Map<Integer, String> dictionary;
		public int maxMagnitude; //The maximum power, in powers of 1000, that this system can represent.
		
		private SuffixType(Map<Integer, String> dictionary, int maxMagnitude) {
			this.dictionary = dictionary;
			this.maxMagnitude = maxMagnitude;
		}
	}

	public static String formatModifier(double amount) {
		StringBuffer str = new StringBuffer(new DecimalFormat(",###").format(amount));
		if(amount > 0.0D) {
			str.insert(0, '+');
		}
		return str.toString();
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
	
	public static String getRomanNumeral(int value) {
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
	
	public static List<ITextComponent> formatModifierMap(Multimap<String, AttributeModifier> modifiers, @Nullable Reforge reforge, FBTier tier) {
		
		List<ITextComponent> tooltip = new ArrayList<>();
		
		//Base weapon stats
		double damage = 0.0D;
		double strength = 0.0D;
		double critChance = 0.0D;
		double critDamage = 0.0D;
		double bonusAttackSpeed = 0.0D;
		double seaCreatureChance = 0.0D;
		
		double health = 0.0D;
		double defense = 0.0D;
		double speed = 0.0D;
		double intelligence = 0.0D;
		double trueDefense = 0.0D;
		double magicFind = 0.0D;
		double petLuck = 0.0D;
		
		//Reforge bonuses
		double reforgeDamage = 0.0D;
		double reforgeStrength = 0.0D;
		double reforgeCritChance = 0.0D;
		double reforgeCritDamage = 0.0D;
		double reforgeBonusAttackSpeed = 0.0D;
		double reforgeSeaCreatureChance = 0.0D;
		
		double reforgeHealth = 0.0D;
		double reforgeDefense = 0.0D;
		double reforgeSpeed = 0.0D;
		double reforgeIntelligence = 0.0D;
		double reforgeTrueDefense = 0.0D;
		double reforgeMagicFind = 0.0D;
		double reforgePetLuck = 0.0D;
		
		String reforgeName = "";
		if(reforge != null) reforgeName = reforge.getDisplayName();
		
		Multimap<String, AttributeModifier> reforgeModifiers;
		
		if(reforge != null) reforgeModifiers = reforge.getModifierMapByTier(tier);
		else reforgeModifiers = ModifierHelper.emptyModifier();
		
		for(AttributeModifier modifier : modifiers.get(SharedMonsterAttributes.ATTACK_DAMAGE.getName())) {
			if(modifier.getOperation().equals(Operation.ADDITION)) {
				damage += modifier.getAmount();
			}
		}
		
		for(AttributeModifier modifier : modifiers.get(FBAttributes.STRENGTH.getName())) {
			if(modifier.getOperation().equals(Operation.ADDITION)) {
				strength += modifier.getAmount();
			}
		}
		
		for(AttributeModifier modifier : modifiers.get(FBAttributes.CRIT_CHANCE.getName())) {
			if(modifier.getOperation().equals(Operation.ADDITION)) {
				critChance += modifier.getAmount();
			}
		}
		
		for(AttributeModifier modifier : modifiers.get(FBAttributes.CRIT_DAMAGE.getName())) {
			if(modifier.getOperation().equals(Operation.ADDITION)) {
				critDamage += modifier.getAmount();
			}
		}
		
		for(AttributeModifier modifier : modifiers.get(FBAttributes.BONUS_ATTACK_SPEED.getName())) {
			if(modifier.getOperation().equals(Operation.ADDITION)) {
				bonusAttackSpeed += modifier.getAmount();
			}
		}
		
		for(AttributeModifier modifier : modifiers.get(FBAttributes.SEA_CREATURE_CHANCE.getName())) {
			if(modifier.getOperation().equals(Operation.ADDITION)) {
				seaCreatureChance += modifier.getAmount();
			}
		}
		
		for(AttributeModifier modifier : modifiers.get(SharedMonsterAttributes.MAX_HEALTH.getName())) {
			if(modifier.getOperation().equals(Operation.ADDITION)) {
				health += modifier.getAmount();
			}
		}
		
		for(AttributeModifier modifier : modifiers.get(FBAttributes.DEFENSE.getName())) {
			if(modifier.getOperation().equals(Operation.ADDITION)) {
				defense += modifier.getAmount();
			}
		}
		
		for(AttributeModifier modifier : modifiers.get(SharedMonsterAttributes.MOVEMENT_SPEED.getName())) {
			if(modifier.getOperation().equals(Operation.ADDITION)) {
				speed += modifier.getAmount();
			}
		}
		
		for(AttributeModifier modifier : modifiers.get(FBAttributes.INTELLIGENCE.getName())) {
			if(modifier.getOperation().equals(Operation.ADDITION)) {
				intelligence += modifier.getAmount();
			}
		}
		
		for(AttributeModifier modifier : modifiers.get(FBAttributes.TRUE_DEFENSE.getName())) {
			if(modifier.getOperation().equals(Operation.ADDITION)) {
				trueDefense += modifier.getAmount();
			}
		}
		
		for(AttributeModifier modifier : modifiers.get(FBAttributes.MAGIC_FIND.getName())) {
			if(modifier.getOperation().equals(Operation.ADDITION)) {
				magicFind += modifier.getAmount();
			}
		}
		
		for(AttributeModifier modifier : modifiers.get(FBAttributes.PET_LUCK.getName())) {
			if(modifier.getOperation().equals(Operation.ADDITION)) {
				petLuck += modifier.getAmount();
			}
		}
		
		//Reforge modifiers
		for(AttributeModifier modifier : reforgeModifiers.get(SharedMonsterAttributes.ATTACK_DAMAGE.getName())) {
			if(modifier.getOperation().equals(Operation.ADDITION)) {
				reforgeDamage += modifier.getAmount();
			}
		}
		
		for(AttributeModifier modifier : reforgeModifiers.get(FBAttributes.STRENGTH.getName())) {
			if(modifier.getOperation().equals(Operation.ADDITION)) {
				reforgeStrength += modifier.getAmount();
			}
		}
		
		for(AttributeModifier modifier : reforgeModifiers.get(FBAttributes.CRIT_CHANCE.getName())) {
			if(modifier.getOperation().equals(Operation.ADDITION)) {
				reforgeCritChance += modifier.getAmount();
			}
		}
		
		for(AttributeModifier modifier : reforgeModifiers.get(FBAttributes.CRIT_DAMAGE.getName())) {
			if(modifier.getOperation().equals(Operation.ADDITION)) {
				reforgeCritDamage += modifier.getAmount();
			}
		}
		
		for(AttributeModifier modifier : reforgeModifiers.get(FBAttributes.BONUS_ATTACK_SPEED.getName())) {
			if(modifier.getOperation().equals(Operation.ADDITION)) {
				reforgeBonusAttackSpeed += modifier.getAmount();
			}
		}
		
		for(AttributeModifier modifier : reforgeModifiers.get(FBAttributes.SEA_CREATURE_CHANCE.getName())) {
			if(modifier.getOperation().equals(Operation.ADDITION)) {
				reforgeSeaCreatureChance += modifier.getAmount();
			}
		}
		
		for(AttributeModifier modifier : reforgeModifiers.get(SharedMonsterAttributes.MAX_HEALTH.getName())) {
			if(modifier.getOperation().equals(Operation.ADDITION)) {
				reforgeHealth += modifier.getAmount();
			}
		}
		
		for(AttributeModifier modifier : reforgeModifiers.get(FBAttributes.DEFENSE.getName())) {
			if(modifier.getOperation().equals(Operation.ADDITION)) {
				reforgeDefense += modifier.getAmount();
			}
		}
		
		for(AttributeModifier modifier : reforgeModifiers.get(SharedMonsterAttributes.MOVEMENT_SPEED.getName())) {
			if(modifier.getOperation().equals(Operation.ADDITION)) {
				reforgeSpeed += modifier.getAmount();
			}
		}
		
		for(AttributeModifier modifier : reforgeModifiers.get(FBAttributes.INTELLIGENCE.getName())) {
			if(modifier.getOperation().equals(Operation.ADDITION)) {
				reforgeIntelligence += modifier.getAmount();
			}
		}
		
		for(AttributeModifier modifier : reforgeModifiers.get(FBAttributes.TRUE_DEFENSE.getName())) {
			if(modifier.getOperation().equals(Operation.ADDITION)) {
				reforgeTrueDefense += modifier.getAmount();
			}
		}
		
		for(AttributeModifier modifier : reforgeModifiers.get(FBAttributes.MAGIC_FIND.getName())) {
			if(modifier.getOperation().equals(Operation.ADDITION)) {
				reforgeMagicFind += modifier.getAmount();
			}
		}
		
		for(AttributeModifier modifier : reforgeModifiers.get(FBAttributes.PET_LUCK.getName())) {
			if(modifier.getOperation().equals(Operation.ADDITION)) {
				reforgePetLuck += modifier.getAmount();
			}
		}
		
		boolean offensiveStats = damage != 0.0D || strength != 0.0D || critChance != 0.0D || critDamage != 0.0D || bonusAttackSpeed != 0.0D || seaCreatureChance != 0.0D;
		boolean defensiveStats = health != 0.0D || defense != 0.0D || speed != 0.0D || intelligence != 0.0D || trueDefense != 0.0D || magicFind != 0.0D || petLuck != 0.0D;
		
		AttributeDisplayType displayType = AttributeDisplayType.TEXT;
		if(CustomModConfig.ATTRIBUTE_DISPLAY_TYPE != null) {
			if(CustomModConfig.ATTRIBUTE_DISPLAY_TYPE.get() != null) displayType = CustomModConfig.ATTRIBUTE_DISPLAY_TYPE.get();
		}
		
		switch(displayType) {
		case TEXT:
			if(damage != 0.0D) {
				if(reforgeDamage == 0.0D) tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("misc.forgeblock.fakeattribute.damage").getString() + ": " + TextFormatting.RED.toString() + TextFormatHelper.formatModifier(damage)));
				else tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("misc.forgeblock.fakeattribute.damage").getString() + ": " + TextFormatting.RED.toString() + TextFormatHelper.formatModifier(damage) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + TextFormatHelper.formatModifier(reforgeDamage) + ")"));
			} else if(reforgeDamage != 0.0D) {
				tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("misc.forgeblock.fakeattribute.damage").getString() + ": " + TextFormatting.RED.toString() + TextFormatHelper.formatModifier(damage) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + TextFormatHelper.formatModifier(reforgeDamage) + ")"));
			}
			
			if(strength != 0.0D) {
				if(reforgeStrength == 0.0D) tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.strength").getString() + ": " + TextFormatting.RED.toString() + TextFormatHelper.formatModifier(strength)));
				else tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.strength").getString() + ": " + TextFormatting.RED.toString() + TextFormatHelper.formatModifier(strength) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + TextFormatHelper.formatModifier(reforgeStrength) + ")"));
			} else if(reforgeStrength != 0.0D) {
				tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.strength").getString() + ": " + TextFormatting.RED.toString() + TextFormatHelper.formatModifier(strength) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + TextFormatHelper.formatModifier(reforgeStrength) + ")"));
			}
			
			if(critChance != 0.0D) {
				if(reforgeCritChance == 0.0D) tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.critChance").getString() + ": " + TextFormatting.RED.toString() + TextFormatHelper.formatModifier(critChance)));
				else tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.critChance").getString() + ": " + TextFormatting.RED.toString() + TextFormatHelper.formatModifier(critChance) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + TextFormatHelper.formatModifier(reforgeCritChance) + ")"));
			} else if(reforgeCritChance != 0.0D) {
				tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.critChance").getString() + ": " + TextFormatting.RED.toString() + TextFormatHelper.formatModifier(critChance) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + TextFormatHelper.formatModifier(reforgeCritChance) + ")"));
			}
			
			if(critDamage != 0.0D) {
				if(reforgeCritDamage == 0.0D) tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.critDamage").getString() + ": " + TextFormatting.RED.toString() + TextFormatHelper.formatModifier(critDamage)));
				else tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.critDamage").getString() + ": " + TextFormatting.RED.toString() + TextFormatHelper.formatModifier(critDamage) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + TextFormatHelper.formatModifier(reforgeCritDamage) + ")"));
			} else if(reforgeCritDamage != 0.0D) {
				tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.critDamage").getString() + ": " + TextFormatting.RED.toString() + TextFormatHelper.formatModifier(critDamage) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + TextFormatHelper.formatModifier(reforgeCritDamage) + ")"));
			}
			
			if(bonusAttackSpeed != 0.0D) {
				if(reforgeBonusAttackSpeed == 0.0D) tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.bonusAttackSpeed").getString() + ": " + TextFormatting.RED.toString() + TextFormatHelper.formatModifier(bonusAttackSpeed)));
				else tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.bonusAttackSpeed").getString() + ": " + TextFormatting.RED.toString() + TextFormatHelper.formatModifier(bonusAttackSpeed) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + TextFormatHelper.formatModifier(reforgeBonusAttackSpeed) + ")"));
			} else if(reforgeBonusAttackSpeed != 0.0D) {
				tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.bonusAttackSpeed").getString() + ": " + TextFormatting.RED.toString() + TextFormatHelper.formatModifier(bonusAttackSpeed) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + TextFormatHelper.formatModifier(reforgeBonusAttackSpeed) + ")"));
			}
			
			if(seaCreatureChance != 0.0D) {
				if(reforgeSeaCreatureChance == 0.0D) tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.seaCreatureChance").getString() + ": " + TextFormatting.RED.toString() + TextFormatHelper.formatModifier(seaCreatureChance)));
				else tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.seaCreatureChance").getString() + ": " + TextFormatting.RED.toString() + TextFormatHelper.formatModifier(seaCreatureChance) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + TextFormatHelper.formatModifier(reforgeSeaCreatureChance) + ")"));
			} else if(reforgeSeaCreatureChance != 0.0D) {
				tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.seaCreatureChance").getString() + ": " + TextFormatting.RED.toString() + TextFormatHelper.formatModifier(seaCreatureChance) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + TextFormatHelper.formatModifier(reforgeSeaCreatureChance) + ")"));
			}
			
			if(offensiveStats && defensiveStats) tooltip.add(new StringTextComponent("")); //Separates offensive and defensive stats
			
			if(health != 0.0D) {
				if(reforgeHealth == 0.0D) tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("misc.forgeblock.fakeattribute.health").getString() + ": " + TextFormatting.GREEN.toString() + TextFormatHelper.formatModifier(health)));
				else tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("misc.forgeblock.fakeattribute.health").getString() + ": " + TextFormatting.GREEN.toString() + TextFormatHelper.formatModifier(health) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + TextFormatHelper.formatModifier(reforgeHealth) + ")"));
			} else if(reforgeHealth != 0.0D) {
				tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("misc.forgeblock.fakeattribute.health").getString() + ": " + TextFormatting.GREEN.toString() + TextFormatHelper.formatModifier(health) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + TextFormatHelper.formatModifier(reforgeHealth) + ")"));
			}
			
			if(defense != 0.0D) {
				if(reforgeDefense == 0.0D) tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.defense").getString() + ": " + TextFormatting.GREEN.toString() + TextFormatHelper.formatModifier(defense)));
				else tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.defense").getString() + ": " + TextFormatting.GREEN.toString() + TextFormatHelper.formatModifier(defense) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + TextFormatHelper.formatModifier(reforgeDefense) + ")"));
			} else if(reforgeDefense != 0.0D) {
				tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.defense").getString() + ": " + TextFormatting.GREEN.toString() + TextFormatHelper.formatModifier(defense) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + TextFormatHelper.formatModifier(reforgeDefense) + ")"));
			}
			
			if(speed != 0.0D) {
				if(reforgeSpeed == 0.0D) tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("misc.forgeblock.fakeattribute.speed").getString() + ": " + TextFormatting.GREEN.toString() + TextFormatHelper.formatModifier(speed * 1000.0D)));
				else tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("misc.forgeblock.fakeattribute.speed").getString() + ": " + TextFormatting.GREEN.toString() + TextFormatHelper.formatModifier(speed * 1000.0D) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + TextFormatHelper.formatModifier(reforgeSpeed * 1000.0D) + ")"));
			} else if(reforgeSpeed != 0.0D) {
				tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("misc.forgeblock.fakeattribute.speed").getString() + ": " + TextFormatting.GREEN.toString() + TextFormatHelper.formatModifier(speed * 1000.0D) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + TextFormatHelper.formatModifier(reforgeSpeed * 1000.0D) + ")"));
			}
			
			if(intelligence != 0.0D) {
				if(reforgeIntelligence == 0.0D) tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.intelligence").getString() + ": " + TextFormatting.GREEN.toString() + TextFormatHelper.formatModifier(intelligence)));
				else tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.intelligence").getString() + ": " + TextFormatting.GREEN.toString() + TextFormatHelper.formatModifier(intelligence) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + TextFormatHelper.formatModifier(reforgeIntelligence) + ")"));
			} else if(reforgeIntelligence != 0.0D) {
				tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.intelligence").getString() + ": " + TextFormatting.GREEN.toString() + TextFormatHelper.formatModifier(intelligence) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + TextFormatHelper.formatModifier(reforgeIntelligence) + ")"));
			}
			
			if(trueDefense != 0.0D) {
				if(reforgeTrueDefense == 0.0D) tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.trueDefense").getString() + ": " + TextFormatting.GREEN.toString() + TextFormatHelper.formatModifier(trueDefense)));
				else tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.trueDefense").getString() + ": " + TextFormatting.GREEN.toString() + TextFormatHelper.formatModifier(trueDefense) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + TextFormatHelper.formatModifier(reforgeTrueDefense) + ")"));
			} else if(reforgeTrueDefense != 0.0D) {
				tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.trueDefense").getString() + ": " + TextFormatting.GREEN.toString() + TextFormatHelper.formatModifier(trueDefense) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + TextFormatHelper.formatModifier(reforgeTrueDefense) + ")"));
			}
			
			if(magicFind != 0.0D) {
				if(reforgeMagicFind == 0.0D) tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.magicFind").getString() + ": " + TextFormatting.GREEN.toString() + TextFormatHelper.formatModifier(magicFind)));
				else tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.magicFind").getString() + ": " + TextFormatting.GREEN.toString() + TextFormatHelper.formatModifier(magicFind) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + TextFormatHelper.formatModifier(reforgeMagicFind) + ")"));
			} else if(reforgeMagicFind != 0.0D) {
				tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.magicFind").getString() + ": " + TextFormatting.GREEN.toString() + TextFormatHelper.formatModifier(magicFind) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + TextFormatHelper.formatModifier(reforgeMagicFind) + ")"));
			}
			
			if(petLuck != 0.0D) {
				if(reforgePetLuck == 0.0D) tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.petLuck").getString() + ": " + TextFormatting.GREEN.toString() + TextFormatHelper.formatModifier(petLuck)));
				else tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.petLuck").getString() + ": " + TextFormatting.GREEN.toString() + TextFormatHelper.formatModifier(petLuck) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + TextFormatHelper.formatModifier(reforgePetLuck) + ")"));
			} else if(reforgePetLuck != 0.0D) {
				tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.petLuck").getString() + ": " + TextFormatting.GREEN.toString() + TextFormatHelper.formatModifier(petLuck) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + TextFormatHelper.formatModifier(reforgePetLuck) + ")"));
			}
			break;
		case ICON:
			break;
		case BOTH:
			if(damage != 0.0D) {
				if(reforgeDamage == 0.0D) tooltip.add(new StringTextComponent(new TranslationTextComponent("misc.forgeblock.attributeicon.damage").getString() + " " + TextFormatting.GRAY.toString() + new TranslationTextComponent("misc.forgeblock.fakeattribute.damage").getString() + ": " + TextFormatting.RED.toString() + TextFormatHelper.formatModifier(damage)));
				else tooltip.add(new StringTextComponent(new TranslationTextComponent("misc.forgeblock.attributeicon.damage").getString() + " " + TextFormatting.GRAY.toString() + new TranslationTextComponent("misc.forgeblock.fakeattribute.damage").getString() + ": " + TextFormatting.RED.toString() + TextFormatHelper.formatModifier(damage) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + TextFormatHelper.formatModifier(reforgeDamage) + ")"));
			} else if(reforgeDamage != 0.0D) {
				tooltip.add(new StringTextComponent(new TranslationTextComponent("misc.forgeblock.attributeicon.damage").getString() + " " + TextFormatting.GRAY.toString() + new TranslationTextComponent("misc.forgeblock.fakeattribute.damage").getString() + ": " + TextFormatting.RED.toString() + TextFormatHelper.formatModifier(damage) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + TextFormatHelper.formatModifier(reforgeDamage) + ")"));
			}
			
			if(strength != 0.0D) {
				if(reforgeStrength == 0.0D) tooltip.add(new StringTextComponent(new TranslationTextComponent("misc.forgeblock.attributeicon.strength").getString() + " " + TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.strength").getString() + ": " + TextFormatting.RED.toString() + TextFormatHelper.formatModifier(strength)));
				else tooltip.add(new StringTextComponent(new TranslationTextComponent("misc.forgeblock.attributeicon.strength").getString() + " " + TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.strength").getString() + ": " + TextFormatting.RED.toString() + TextFormatHelper.formatModifier(strength) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + TextFormatHelper.formatModifier(reforgeStrength) + ")"));
			} else if(reforgeStrength != 0.0D) {
				tooltip.add(new StringTextComponent(new TranslationTextComponent("misc.forgeblock.attributeicon.strength").getString() + " " + TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.strength").getString() + ": " + TextFormatting.RED.toString() + TextFormatHelper.formatModifier(strength) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + TextFormatHelper.formatModifier(reforgeStrength) + ")"));
			}
			
			if(critChance != 0.0D) {
				if(reforgeCritChance == 0.0D) tooltip.add(new StringTextComponent(new TranslationTextComponent("misc.forgeblock.attributeicon.critChance").getString() + " " + TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.critChance").getString() + ": " + TextFormatting.RED.toString() + TextFormatHelper.formatModifier(critChance)));
				else tooltip.add(new StringTextComponent(new TranslationTextComponent("misc.forgeblock.attributeicon.critChance").getString() + " " + TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.critChance").getString() + ": " + TextFormatting.RED.toString() + TextFormatHelper.formatModifier(critChance) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + TextFormatHelper.formatModifier(reforgeCritChance) + ")"));
			} else if(reforgeCritChance != 0.0D) {
				tooltip.add(new StringTextComponent(new TranslationTextComponent("misc.forgeblock.attributeicon.critChance").getString() + " " + TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.critChance").getString() + ": " + TextFormatting.RED.toString() + TextFormatHelper.formatModifier(critChance) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + TextFormatHelper.formatModifier(reforgeCritChance) + ")"));
			}
			
			if(critDamage != 0.0D) {
				if(reforgeCritDamage == 0.0D) tooltip.add(new StringTextComponent(new TranslationTextComponent("misc.forgeblock.attributeicon.critDamage").getString() + " " + TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.critDamage").getString() + ": " + TextFormatting.RED.toString() + TextFormatHelper.formatModifier(critDamage)));
				else tooltip.add(new StringTextComponent(new TranslationTextComponent("misc.forgeblock.attributeicon.critDamage").getString() + " " + TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.critDamage").getString() + ": " + TextFormatting.RED.toString() + TextFormatHelper.formatModifier(critDamage) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + TextFormatHelper.formatModifier(reforgeCritDamage) + ")"));
			} else if(reforgeCritDamage != 0.0D) {
				tooltip.add(new StringTextComponent(new TranslationTextComponent("misc.forgeblock.attributeicon.critDamage").getString() + " " + TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.critDamage").getString() + ": " + TextFormatting.RED.toString() + TextFormatHelper.formatModifier(critDamage) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + TextFormatHelper.formatModifier(reforgeCritDamage) + ")"));
			}
			
			if(bonusAttackSpeed != 0.0D) {
				if(reforgeBonusAttackSpeed == 0.0D) tooltip.add(new StringTextComponent(new TranslationTextComponent("misc.forgeblock.attributeicon.bonusAttackSpeed").getString() + " " + TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.bonusAttackSpeed").getString() + ": " + TextFormatting.RED.toString() + TextFormatHelper.formatModifier(bonusAttackSpeed)));
				else tooltip.add(new StringTextComponent(new TranslationTextComponent("misc.forgeblock.attributeicon.bonusAttackSpeed").getString() + " " + TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.bonusAttackSpeed").getString() + ": " + TextFormatting.RED.toString() + TextFormatHelper.formatModifier(bonusAttackSpeed) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + TextFormatHelper.formatModifier(reforgeBonusAttackSpeed) + ")"));
			} else if(reforgeBonusAttackSpeed != 0.0D) {
				tooltip.add(new StringTextComponent(new TranslationTextComponent("misc.forgeblock.attributeicon.bonusAttackSpeed").getString() + " " + TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.bonusAttackSpeed").getString() + ": " + TextFormatting.RED.toString() + TextFormatHelper.formatModifier(bonusAttackSpeed) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + TextFormatHelper.formatModifier(reforgeBonusAttackSpeed) + ")"));
			}
			
			if(seaCreatureChance != 0.0D) {
				if(reforgeSeaCreatureChance == 0.0D) tooltip.add(new StringTextComponent(new TranslationTextComponent("misc.forgeblock.attributeicon.seaCreatureChance").getString() + " " + TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.seaCreatureChance").getString() + ": " + TextFormatting.RED.toString() + TextFormatHelper.formatModifier(seaCreatureChance)));
				else tooltip.add(new StringTextComponent(new TranslationTextComponent("misc.forgeblock.attributeicon.seaCreatureChance").getString() + " " + TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.seaCreatureChance").getString() + ": " + TextFormatting.RED.toString() + TextFormatHelper.formatModifier(seaCreatureChance) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + TextFormatHelper.formatModifier(reforgeSeaCreatureChance) + ")"));
			} else if(reforgeSeaCreatureChance != 0.0D) {
				tooltip.add(new StringTextComponent(new TranslationTextComponent("misc.forgeblock.attributeicon.seaCreatureChance").getString() + " " + TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.seaCreatureChance").getString() + ": " + TextFormatting.RED.toString() + TextFormatHelper.formatModifier(seaCreatureChance) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + TextFormatHelper.formatModifier(reforgeSeaCreatureChance) + ")"));
			}
			
			if(offensiveStats && defensiveStats) tooltip.add(new StringTextComponent("")); //Separates offensive and defensive stats
			
			if(health != 0.0D) {
				if(reforgeHealth == 0.0D) tooltip.add(new StringTextComponent(new TranslationTextComponent("misc.forgeblock.attributeicon.health").getString() + " " + TextFormatting.GRAY.toString() + new TranslationTextComponent("misc.forgeblock.fakeattribute.health").getString() + ": " + TextFormatting.GREEN.toString() + TextFormatHelper.formatModifier(health)));
				else tooltip.add(new StringTextComponent(new TranslationTextComponent("misc.forgeblock.attributeicon.health").getString() + " " + TextFormatting.GRAY.toString() + new TranslationTextComponent("misc.forgeblock.fakeattribute.health").getString() + ": " + TextFormatting.GREEN.toString() + TextFormatHelper.formatModifier(health) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + TextFormatHelper.formatModifier(reforgeHealth) + ")"));
			} else if(reforgeHealth != 0.0D) {
				tooltip.add(new StringTextComponent(new TranslationTextComponent("misc.forgeblock.attributeicon.health").getString() + " " + TextFormatting.GRAY.toString() + new TranslationTextComponent("misc.forgeblock.fakeattribute.health").getString() + ": " + TextFormatting.GREEN.toString() + TextFormatHelper.formatModifier(health) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + TextFormatHelper.formatModifier(reforgeHealth) + ")"));
			}
			
			if(defense != 0.0D) {
				if(reforgeDefense == 0.0D) tooltip.add(new StringTextComponent(new TranslationTextComponent("misc.forgeblock.attributeicon.defense").getString() + " " + TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.defense").getString() + ": " + TextFormatting.GREEN.toString() + TextFormatHelper.formatModifier(defense)));
				else tooltip.add(new StringTextComponent(new TranslationTextComponent("misc.forgeblock.attributeicon.defense").getString() + " " + TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.defense").getString() + ": " + TextFormatting.GREEN.toString() + TextFormatHelper.formatModifier(defense) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + TextFormatHelper.formatModifier(reforgeDefense) + ")"));
			} else if(reforgeDefense != 0.0D) {
				tooltip.add(new StringTextComponent(new TranslationTextComponent("misc.forgeblock.attributeicon.defense").getString() + " " + TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.defense").getString() + ": " + TextFormatting.GREEN.toString() + TextFormatHelper.formatModifier(defense) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + TextFormatHelper.formatModifier(reforgeDefense) + ")"));
			}
			
			if(speed != 0.0D) {
				if(reforgeSpeed == 0.0D) tooltip.add(new StringTextComponent(new TranslationTextComponent("misc.forgeblock.attributeicon.speed").getString() + " " + TextFormatting.GRAY.toString() + new TranslationTextComponent("misc.forgeblock.fakeattribute.speed").getString() + ": " + TextFormatting.GREEN.toString() + TextFormatHelper.formatModifier(speed * 1000.0D)));
				else tooltip.add(new StringTextComponent(new TranslationTextComponent("misc.forgeblock.attributeicon.speed").getString() + " " + TextFormatting.GRAY.toString() + new TranslationTextComponent("misc.forgeblock.fakeattribute.speed").getString() + ": " + TextFormatting.GREEN.toString() + TextFormatHelper.formatModifier(speed * 1000.0D) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + TextFormatHelper.formatModifier(reforgeSpeed * 1000.0D) + ")"));
			} else if(reforgeSpeed != 0.0D) {
				tooltip.add(new StringTextComponent(new TranslationTextComponent("misc.forgeblock.attributeicon.speed").getString() + " " + TextFormatting.GRAY.toString() + new TranslationTextComponent("misc.forgeblock.fakeattribute.speed").getString() + ": " + TextFormatting.GREEN.toString() + TextFormatHelper.formatModifier(speed * 1000.0D) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + TextFormatHelper.formatModifier(reforgeSpeed * 1000.0D) + ")"));
			}
			
			if(intelligence != 0.0D) {
				if(reforgeIntelligence == 0.0D) tooltip.add(new StringTextComponent(new TranslationTextComponent("misc.forgeblock.attributeicon.intelligence").getString() + " " + TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.intelligence").getString() + ": " + TextFormatting.GREEN.toString() + TextFormatHelper.formatModifier(intelligence)));
				else tooltip.add(new StringTextComponent(new TranslationTextComponent("misc.forgeblock.attributeicon.intelligence").getString() + " " + TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.intelligence").getString() + ": " + TextFormatting.GREEN.toString() + TextFormatHelper.formatModifier(intelligence) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + TextFormatHelper.formatModifier(reforgeIntelligence) + ")"));
			} else if(reforgeIntelligence != 0.0D) {
				tooltip.add(new StringTextComponent(new TranslationTextComponent("misc.forgeblock.attributeicon.intelligence").getString() + " " + TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.intelligence").getString() + ": " + TextFormatting.GREEN.toString() + TextFormatHelper.formatModifier(intelligence) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + TextFormatHelper.formatModifier(reforgeIntelligence) + ")"));
			}
			
			if(trueDefense != 0.0D) {
				if(reforgeTrueDefense == 0.0D) tooltip.add(new StringTextComponent(new TranslationTextComponent("misc.forgeblock.attributeicon.trueDefense").getString() + " " + TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.trueDefense").getString() + ": " + TextFormatting.GREEN.toString() + TextFormatHelper.formatModifier(trueDefense)));
				else tooltip.add(new StringTextComponent(new TranslationTextComponent("misc.forgeblock.attributeicon.trueDefense").getString() + " " + TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.trueDefense").getString() + ": " + TextFormatting.GREEN.toString() + TextFormatHelper.formatModifier(trueDefense) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + TextFormatHelper.formatModifier(reforgeTrueDefense) + ")"));
			} else if(reforgeTrueDefense != 0.0D) {
				tooltip.add(new StringTextComponent(new TranslationTextComponent("misc.forgeblock.attributeicon.trueDefense").getString() + " " + TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.trueDefense").getString() + ": " + TextFormatting.GREEN.toString() + TextFormatHelper.formatModifier(trueDefense) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + TextFormatHelper.formatModifier(reforgeTrueDefense) + ")"));
			}
			
			if(magicFind != 0.0D) {
				if(reforgeMagicFind == 0.0D) tooltip.add(new StringTextComponent(new TranslationTextComponent("misc.forgeblock.attributeicon.magicFind").getString() + " " + TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.magicFind").getString() + ": " + TextFormatting.GREEN.toString() + TextFormatHelper.formatModifier(magicFind)));
				else tooltip.add(new StringTextComponent(new TranslationTextComponent("misc.forgeblock.attributeicon.magicFind").getString() + " " + TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.magicFind").getString() + ": " + TextFormatting.GREEN.toString() + TextFormatHelper.formatModifier(magicFind) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + TextFormatHelper.formatModifier(reforgeMagicFind) + ")"));
			} else if(reforgeMagicFind != 0.0D) {
				tooltip.add(new StringTextComponent(new TranslationTextComponent("misc.forgeblock.attributeicon.magicFind").getString() + " " + TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.magicFind").getString() + ": " + TextFormatting.GREEN.toString() + TextFormatHelper.formatModifier(magicFind) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + TextFormatHelper.formatModifier(reforgeMagicFind) + ")"));
			}
			
			if(petLuck != 0.0D) {
				if(reforgePetLuck == 0.0D) tooltip.add(new StringTextComponent(new TranslationTextComponent("misc.forgeblock.attributeicon.petLuck").getString() + " " + TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.petLuck").getString() + ": " + TextFormatting.GREEN.toString() + TextFormatHelper.formatModifier(petLuck)));
				else tooltip.add(new StringTextComponent(new TranslationTextComponent("misc.forgeblock.attributeicon.petLuck").getString() + " " + TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.petLuck").getString() + ": " + TextFormatting.GREEN.toString() + TextFormatHelper.formatModifier(petLuck) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + TextFormatHelper.formatModifier(reforgePetLuck) + ")"));
			} else if(reforgePetLuck != 0.0D) {
				tooltip.add(new StringTextComponent(new TranslationTextComponent("misc.forgeblock.attributeicon.petLuck").getString() + " " + TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.petLuck").getString() + ": " + TextFormatting.GREEN.toString() + TextFormatHelper.formatModifier(petLuck) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + TextFormatHelper.formatModifier(reforgePetLuck) + ")"));
			}
			break;
		default:
			break;
		}
		
		return tooltip;
	}

}
