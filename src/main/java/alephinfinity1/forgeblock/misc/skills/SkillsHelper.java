package alephinfinity1.forgeblock.misc.skills;

import net.minecraft.entity.player.PlayerEntity;

/**
 * A collection of helper methods for viewing/manipulating player skills.
 */
public class SkillsHelper {

	public static ISkills getSkillsCap(PlayerEntity player) {
		return player.getCapability(SkillsProvider.SKILLS_CAPABILITY).orElseThrow(NullPointerException::new);
	}
	
	public static ISkills getSkillsCapOrElse(PlayerEntity player, ISkills other) {
		return player.getCapability(SkillsProvider.SKILLS_CAPABILITY).orElse(other);
	}
	
	/**
	 * Get level methods
	 */
	public static int getSkillLevel(PlayerEntity player, SkillType skill) {
		return getSkillsCap(player).getLevel(skill);
	}
	
	public static int getFarmingLevel(PlayerEntity player) {
		return getSkillsCap(player).getLevel(SkillType.FARMING);
	}
	
	public static int getMiningLevel(PlayerEntity player) {
		return getSkillsCap(player).getLevel(SkillType.MINING);
	}
	
	public static int getCombatLevel(PlayerEntity player) {
		return getSkillsCap(player).getLevel(SkillType.COMBAT);
	}
	
	public static int getForagingLevel(PlayerEntity player) {
		return getSkillsCap(player).getLevel(SkillType.FORAGING);
	}
	
	public static int getFishingLevel(PlayerEntity player) {
		return getSkillsCap(player).getLevel(SkillType.FISHING);
	}
	
	public static int getEnchantingLevel(PlayerEntity player) {
		return getSkillsCap(player).getLevel(SkillType.ENCHANTING);
	}
	
	public static int getAlchemyLevel(PlayerEntity player) {
		return getSkillsCap(player).getLevel(SkillType.ALCHEMY);
	}
	
	public static int getTamingLevel(PlayerEntity player) {
		return getSkillsCap(player).getLevel(SkillType.TAMING);
	}
	
	public static int getCarpentryLevel(PlayerEntity player) {
		return getSkillsCap(player).getLevel(SkillType.CARPENTRY);
	}
	
	public static int getRunecraftingLevel(PlayerEntity player) {
		return getSkillsCap(player).getLevel(SkillType.RUNECRAFTING);
	}
}
