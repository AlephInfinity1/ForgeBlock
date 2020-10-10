package alephinfinity1.forgeblock.misc.skills;

import java.util.Map;

import com.google.common.collect.Multimap;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.nbt.CompoundNBT;

public interface ISkills {
	public Map<SkillType, SkillData> getData();
	public SkillData getSkillDataForSkill(SkillType skill);
	public int getLevel(SkillType skill);
	public double getAbsoluteProgress(SkillType skill);
	public double getProgressPercentage(SkillType skill);
	public double getXPNeededToLevelUp(SkillType skill);
	public void addXP(SkillType skill, double amount);
	public void setLevel(SkillType skill, int level);
	public void setProgress(SkillType skill, double progress);
	public CompoundNBT getCompoundNBTFor(SkillType skill);
	public Multimap<String, AttributeModifier> getAttributeModifiers();
	public ISkills copy();
}
