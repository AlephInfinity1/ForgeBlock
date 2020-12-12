package alephinfinity1.forgeblock.enchantment;

import alephinfinity1.forgeblock.misc.skills.SkillsHelper;
import net.minecraft.entity.player.PlayerEntity;

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
	 * as opposed to the 'actual' maximum level that can be obtained elsewhere. <br>
	 * If unobtainable at enchantment table (e.g. Replenish and Vicious), return -1.
	 * @return The max level of this enchantment obtainable on an enchanting table.
	 */
	public int getEnchantingTableMaxLevel();
	
	/**
	 * Gets the Enchanting skill level required to use this enchantment.
	 * If not met, the enchantment would be grayed out and won't function.
	 * @param level The level of the enchantment
	 * @return The Enchanting skill level required to use this enchantment.
	 */
	public int getRequiredSkillLevel(int level);
	
	/**
	 * Returns whether the given player can use the specified level of this enchantment.
	 * @param player The provided player
	 * @param level The level of the enchantment.
	 * @return Whether the player can use this level of enchantment.
	 */
	default boolean canUse(PlayerEntity player, int level) {
		return SkillsHelper.getEnchantingLevelOrElse(player, 0) >= this.getRequiredSkillLevel(level);
	}

}
