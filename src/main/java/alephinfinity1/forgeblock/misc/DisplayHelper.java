package alephinfinity1.forgeblock.misc;

import java.text.DecimalFormat;

public class DisplayHelper {

	public static String formatModifier(double amount) {
		StringBuffer str = new StringBuffer(new DecimalFormat("#").format(amount));
		if(amount > 0.0D) {
			str.insert(0, '+');
		}
		return str.toString();
	}

}
