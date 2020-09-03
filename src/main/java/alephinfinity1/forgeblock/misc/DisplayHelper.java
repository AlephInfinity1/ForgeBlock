package alephinfinity1.forgeblock.misc;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class DisplayHelper {
	
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
		StringBuffer str = new StringBuffer(new DecimalFormat("#").format(amount));
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

}
