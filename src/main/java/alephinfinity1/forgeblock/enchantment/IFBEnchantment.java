package alephinfinity1.forgeblock.enchantment;

public interface IFBEnchantment {
	
	/**
	 * Gets the repair cost in anvils for a specific level of this enchantment.
	 * @param level The level of this enchantment
	 * @return The amount of XP levels costed in anvils.
	 */
	default int getRepairCostForLevel(int level) {
		switch (level) {
		case 1:
		case 2:
		case 3:
		case 4:
			return level;
		case 5:
			return 6;
		case 6:
			return 9;
		case 7:
			return 12;
		case 8:
			return 16;
		case 9:
			return 20;
		case 10:
			return 25;
		default:
			return Integer.MIN_VALUE;
		}
	}
	
	/**
	 * Gets the maximum level of this enchantment obtainable on an enchanting table,
	 * as opposed to the 'actual' maximum level that can be obtained elsewhere
	 * @return The max level of this enchantment obtainable on an enchanting table.
	 */
	public int getEnchantingTableMaxLevel();

}
