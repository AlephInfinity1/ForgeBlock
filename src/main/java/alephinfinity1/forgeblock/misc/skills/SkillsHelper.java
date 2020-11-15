package alephinfinity1.forgeblock.misc.skills;

import java.util.Objects;

import alephinfinity1.forgeblock.misc.CompareTuple;
import alephinfinity1.forgeblock.misc.event.FBEventHooks;
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
	
	/*
	 * Get Skill level methods, but with default values.
	 */
	public static int getSkillLevelOrElse(PlayerEntity player, SkillType skill, int _default) {
		try {
			return getSkillsCap(player).getLevel(skill);
		} catch(NullPointerException nullptrex) {
			return _default;
		}
	}
	
	public static int getFarmingLevelOrElse(PlayerEntity player, int _default) {
		try {
			return getSkillsCap(player).getLevel(SkillType.FARMING);
		} catch(NullPointerException nullptrex) {
			return _default;
		}
	}
	
	public static int getMiningLevelOrElse(PlayerEntity player, int _default) {
		try {
			return getSkillsCap(player).getLevel(SkillType.MINING);
		} catch(NullPointerException nullptrex) {
			return _default;
		}
	}
	
	public static int getCombatLevelOrElse(PlayerEntity player, int _default) {
		try {
			return getSkillsCap(player).getLevel(SkillType.COMBAT);
		} catch(NullPointerException nullptrex) {
			return _default;
		}	
	}
	
	public static int getForagingLevelOrElse(PlayerEntity player, int _default) {
		try {
			return getSkillsCap(player).getLevel(SkillType.FORAGING);
		} catch(NullPointerException nullptrex) {
			return _default;
		}
	}
	
	public static int getFishingLevelOrElse(PlayerEntity player, int _default) {
		try {
			return getSkillsCap(player).getLevel(SkillType.FISHING);
		} catch(NullPointerException nullptrex) {
			return _default;
		}
	}
	
	public static int getEnchantingLevelOrElse(PlayerEntity player, int _default) {
		try {
			return getSkillsCap(player).getLevel(SkillType.ENCHANTING);
		} catch(NullPointerException nullptrex) {
			return _default;
		}
	}
	
	public static int getAlchemyLevelOrElse(PlayerEntity player, int _default) {
		try {
			return getSkillsCap(player).getLevel(SkillType.ALCHEMY);
		} catch(NullPointerException nullptrex) {
			return _default;
		}
	}
	
	public static int getTamingLevelOrElse(PlayerEntity player, int _default) {
		try {
			return getSkillsCap(player).getLevel(SkillType.TAMING);
		} catch(NullPointerException nullptrex) {
			return _default;
		}
	}
	
	public static int getCarpentryLevelOrElse(PlayerEntity player, int _default) {
		try {
			return getSkillsCap(player).getLevel(SkillType.CARPENTRY);
		} catch(NullPointerException nullptrex) {
			return _default;
		}
	}
	
	public static int getRunecraftingLevelOrElse(PlayerEntity player, int _default) {
		try {
			return getSkillsCap(player).getLevel(SkillType.RUNECRAFTING);
		} catch(NullPointerException nullptrex) {
			return _default;
		}
	}
	
	/**
	 * Add skill XP to a player.
	 * Used instead of ISkills to trigger levelling up.
	 * @param player PlayerEntity
	 * @param type SkillType
	 * @param amount amount
	 */
	@SuppressWarnings("deprecation")
	public static void addXP(PlayerEntity player, SkillType type, double amount) {
		ISkills skills = getSkillsCapOrElse(player, null);
		if(Objects.isNull(skills)) return;
		
		CompareTuple<Integer> cti = skills.addXP(type, amount);
		if(!cti.isEqual()) {
			FBEventHooks.onPlayerSkillLevelUp(player, type, cti.A(), cti.B());
		}
	}
}
