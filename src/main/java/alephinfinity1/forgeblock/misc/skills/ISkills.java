package alephinfinity1.forgeblock.misc.skills;

import java.util.Map;

import com.google.common.collect.Multimap;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.nbt.CompoundNBT;

/**
 * The capability interface for player Skills.
 * Note:
 * - Dungeoneering isn't included here because it is a compound skill and has a different mechanic than all other skills.
 *
 */
public interface ISkills {
	/**
	 * Sets this ISkills to a copy of other.
	 * @param other The ISkills to copy from.
	 */
	public void set(ISkills other);
	
	/**
	 * Returns the Map that stores the player's Skills data.
	 * @return map
	 */
	public Map<SkillType, SkillData> getData();
	
	/**
	 * Gets the corresponding SkillData of a particular SkillType
	 * @param skill SkillType
	 * @return the corresponding SkillData
	 */
	public SkillData getSkillDataForSkill(SkillType skill);
	
	/**
	 * Gets the integer level of a SkillType
	 * @param skill SkillType
	 * @return integer level
	 */
	public int getLevel(SkillType skill);
	
	/**
	 * Gets the double absolute progress to the next level of a SkillType
	 * E.g. If a player is at Combat 23 and (200,000/600k), this method will return 200000.0d.
	 * @param skill SkillType
	 * @return double, absolute progress to the next level
	 */
	public double getAbsoluteProgress(SkillType skill);
	
	/**
	 * Gets the double percentage progress, between 0–1, to the next level of a SkillType
	 * E.g. If a player is at Combat 23 and (200,000/600k), this method will return 0.333...d.
	 * @param skill SkillType
	 * @return double, percentage progress to the next level (Between 0.0d and 1.0d).
	 */
	public double getProgressPercentage(SkillType skill);
	
	/**
	 * Gets the XP required to level up from the current level.
	 * E.g. If the player is at Combat 23, this method will return 600k.
	 * Note: the XP required for 23 -> 24 would be returned, not 22 -> 23.
	 * TODO bugginess when dealing with Lv50 skills.
	 * 
	 * @param skill SkillType
	 * @return double, XP needed to level up to the next level.
	 */
	public double getXPNeededToLevelUp(SkillType skill);
	public void addXP(SkillType skill, double amount);
	public void setLevel(SkillType skill, int level);
	public void setProgress(SkillType skill, double progress);
	
	/**
	 * Gets the CompoundNBT data for a particular SkillType.
	 * Used by packets.
	 * @param skill SkillType
	 * @return CompoundNBT that stores the corresponding SkillData.
	 * @see alephinfinity1.forgeblock.network.SkillUpdatePacket
	 */
	public CompoundNBT getCompoundNBTFor(SkillType skill);
	public Multimap<String, AttributeModifier> getAttributeModifiers();
	public ISkills copy();
}
