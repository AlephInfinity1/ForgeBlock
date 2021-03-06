package alephinfinity1.forgeblock.misc.capability.skills;

import java.util.Objects;

import alephinfinity1.forgeblock.misc.CompareTuple;
import alephinfinity1.forgeblock.misc.event.FBEventHooks;
import alephinfinity1.forgeblock.network.FBPacketHandler;
import alephinfinity1.forgeblock.network.SkillUpdatePacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.fml.network.PacketDistributor;

/**
 * A collection of helper methods for viewing/manipulating player skills.
 */
public class SkillsHelper {
	
	private SkillsHelper() {
		throw new AssertionError("Class SkillsHelper is a helper methods class and should not be instantiated!");
	}

	public static ISkills getSkillsCapOrNull(PlayerEntity player) {
		return player.getCapability(SkillsProvider.SKILLS_CAPABILITY).orElse(null);
	}
	
	public static ISkills getRequiredSkillsCap(PlayerEntity player) {
		return player.getCapability(SkillsProvider.SKILLS_CAPABILITY).orElseThrow(() -> new NullPointerException("Error: Skills capability not present on player " + player.getName().getString()));
	}
	
	public static ISkills getRequiredSkillsCap(PlayerEntity player, String message) {
		return player.getCapability(SkillsProvider.SKILLS_CAPABILITY).orElseThrow(() -> new NullPointerException(message));
	}
	
	public static ISkills getSkillsCapOrElse(PlayerEntity player, ISkills other) {
		return player.getCapability(SkillsProvider.SKILLS_CAPABILITY).orElse(other);
	}
	
	/**
	 * Get level methods
	 */
	public static int getSkillLevel(PlayerEntity player, SkillType skill) {
		return getSkillsCapOrNull(player).getLevel(skill);
	}
	
	public static int getFarmingLevel(PlayerEntity player) {
		return getSkillsCapOrNull(player).getLevel(SkillType.FARMING);
	}
	
	public static int getMiningLevel(PlayerEntity player) {
		return getSkillsCapOrNull(player).getLevel(SkillType.MINING);
	}
	
	public static int getCombatLevel(PlayerEntity player) {
		return getSkillsCapOrNull(player).getLevel(SkillType.COMBAT);
	}
	
	public static int getForagingLevel(PlayerEntity player) {
		return getSkillsCapOrNull(player).getLevel(SkillType.FORAGING);
	}
	
	public static int getFishingLevel(PlayerEntity player) {
		return getSkillsCapOrNull(player).getLevel(SkillType.FISHING);
	}
	
	public static int getEnchantingLevel(PlayerEntity player) {
		return getSkillsCapOrNull(player).getLevel(SkillType.ENCHANTING);
	}
	
	public static int getAlchemyLevel(PlayerEntity player) {
		return getSkillsCapOrNull(player).getLevel(SkillType.ALCHEMY);
	}
	
	public static int getTamingLevel(PlayerEntity player) {
		return getSkillsCapOrNull(player).getLevel(SkillType.TAMING);
	}
	
	public static int getCarpentryLevel(PlayerEntity player) {
		return getSkillsCapOrNull(player).getLevel(SkillType.CARPENTRY);
	}
	
	public static int getRunecraftingLevel(PlayerEntity player) {
		return getSkillsCapOrNull(player).getLevel(SkillType.RUNECRAFTING);
	}
	
	/*
	 * Get Skill level methods, but with default values.
	 */
	public static int getSkillLevelOrElse(PlayerEntity player, SkillType skill, int _default) {
		try {
			return getSkillsCapOrNull(player).getLevel(skill);
		} catch(NullPointerException nullptrex) {
			return _default;
		}
	}
	
	public static int getFarmingLevelOrElse(PlayerEntity player, int _default) {
		try {
			return getSkillsCapOrNull(player).getLevel(SkillType.FARMING);
		} catch(NullPointerException nullptrex) {
			return _default;
		}
	}
	
	public static int getMiningLevelOrElse(PlayerEntity player, int _default) {
		try {
			return getSkillsCapOrNull(player).getLevel(SkillType.MINING);
		} catch(NullPointerException nullptrex) {
			return _default;
		}
	}
	
	public static int getCombatLevelOrElse(PlayerEntity player, int _default) {
		try {
			return getSkillsCapOrNull(player).getLevel(SkillType.COMBAT);
		} catch(NullPointerException nullptrex) {
			return _default;
		}	
	}
	
	public static int getForagingLevelOrElse(PlayerEntity player, int _default) {
		try {
			return getSkillsCapOrNull(player).getLevel(SkillType.FORAGING);
		} catch(NullPointerException nullptrex) {
			return _default;
		}
	}
	
	public static int getFishingLevelOrElse(PlayerEntity player, int _default) {
		try {
			return getSkillsCapOrNull(player).getLevel(SkillType.FISHING);
		} catch(NullPointerException nullptrex) {
			return _default;
		}
	}
	
	public static int getEnchantingLevelOrElse(PlayerEntity player, int _default) {
		try {
			return getSkillsCapOrNull(player).getLevel(SkillType.ENCHANTING);
		} catch(NullPointerException nullptrex) {
			return _default;
		}
	}
	
	public static int getAlchemyLevelOrElse(PlayerEntity player, int _default) {
		try {
			return getSkillsCapOrNull(player).getLevel(SkillType.ALCHEMY);
		} catch(NullPointerException nullptrex) {
			return _default;
		}
	}
	
	public static int getTamingLevelOrElse(PlayerEntity player, int _default) {
		try {
			return getSkillsCapOrNull(player).getLevel(SkillType.TAMING);
		} catch(NullPointerException nullptrex) {
			return _default;
		}
	}
	
	public static int getCarpentryLevelOrElse(PlayerEntity player, int _default) {
		try {
			return getSkillsCapOrNull(player).getLevel(SkillType.CARPENTRY);
		} catch(NullPointerException nullptrex) {
			return _default;
		}
	}
	
	public static int getRunecraftingLevelOrElse(PlayerEntity player, int _default) {
		try {
			return getSkillsCapOrNull(player).getLevel(SkillType.RUNECRAFTING);
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
	
	/**
	 * A helper method to adding XP and updating.
	 * @param player PlayerEntity
	 * @param type SkillType
	 * @param amount amount
	 */
	public static void addXPAndUpdate(PlayerEntity player, SkillType type, double amount) {
		SkillsHelper.addXP(player, type, amount);
		if (player instanceof ServerPlayerEntity) {
			SkillsHelper.updateSkill((ServerPlayerEntity) player, type);
		}
	}
	
	/**
	 * Updates one SkillType for a server player entity.
	 * @param splayer
	 * @param type
	 */
	public static void updateSkill(ServerPlayerEntity splayer, SkillType type) {
		ISkills skills = SkillsHelper.getSkillsCapOrElse(splayer, null);
		if (Objects.isNull(skills)) return;
		FBPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> splayer), new SkillUpdatePacket(skills.getCompoundNBTFor(type)));
	}
	
	/**
	 * UpdateSkill, but with optional notification.
	 * @param splayer
	 * @param type
	 * @param notify
	 */
	public static void updateSkill(ServerPlayerEntity splayer, SkillType type, boolean notify) {
		ISkills skills = SkillsHelper.getSkillsCapOrElse(splayer, null);
		if (Objects.isNull(skills)) return;
		FBPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> splayer), new SkillUpdatePacket(skills.getCompoundNBTFor(type), notify));
	}
	
	/**
	 * Updates all SkillTypes for a server player entity
	 */
	public static void updateAllSkills(ServerPlayerEntity splayer) {
		ISkills skills = SkillsHelper.getSkillsCapOrElse(splayer, null);
		if (Objects.isNull(skills)) return;
		for (SkillType type : SkillType.values())
			FBPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> splayer), new SkillUpdatePacket(skills.getCompoundNBTFor(type)));
	}
	
	/**
	 * Updates all SkillTypes for a server player entity
	 */
	public static void updateAllSkills(ServerPlayerEntity splayer, boolean notify) {
		ISkills skills = SkillsHelper.getSkillsCapOrElse(splayer, null);
		if (Objects.isNull(skills)) return;
		for (SkillType type : SkillType.values())
			FBPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> splayer), new SkillUpdatePacket(skills.getCompoundNBTFor(type), notify));
	}
	
}
