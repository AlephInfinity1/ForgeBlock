package alephinfinity1.forgeblock.misc.skills;

import net.minecraft.util.math.MathHelper;

public class SkillData {
	private SkillType skill;
	private int level; //The level of the skill
	private double progress; //The absolute progress to the next level, saved even if next level doesn't exist.
	
	public SkillData(SkillType skill, int level, double progress) {
		this.skill = skill;
		this.level = level;
		this.progress = progress;
	}
	
	/*
	 * Initial SkillData, with no progress.
	 */
	public SkillData(SkillType skill) {
		this.skill = skill;
		this.level = 0;
		this.progress = 0.0d;
	}
	
	//For copying only
	private SkillData(SkillData other) {
		this.skill = other.skill;
		this.level = other.level;
		this.progress = other.progress;
	}
	
	public int getLevel() {
		return level;
	}
	
	public double getAbsoluteProgress() {
		return progress;
	}
	
	public double getProgressPercentage() {
		if(level < 50) {
			return progress / skill.getXPForLevel(level);
		} else {
			return 0;
		}
	}
	
	/*
	 * Updates the level of the SkillData, returning whether the skill leveled up or not.
	 */
	public boolean update() {
		boolean flag = false;
		while(progress >= skill.getXPForLevel(level)) {
			flag = true;
			progress -= skill.getXPForLevel(level);
			++level;
		}
		level = MathHelper.clamp(level, 0, 50);
		return flag;
	}
	
	/*
	 * Adds an amount of XP to the SkillData, returning whether the player leveled up or not.
	 */
	public boolean addXP(double amount) {
		progress += amount;
		return update();
	}
	
	public void setLevel(int level) {
		double prog = MathHelper.clamp(this.getProgressPercentage(), 0.0d, 1.0d - Float.MIN_NORMAL);
		this.level = level;
		this.progress = skill.getXPForLevel(level) * prog;
		if(Double.isNaN(progress)) progress = 0.0D;
		update();
	}
	
	public void setProgress(double progress) {
		this.progress = progress;
		update();
	}
	
	public SkillData copy() {
		return new SkillData(this);
	}
}
