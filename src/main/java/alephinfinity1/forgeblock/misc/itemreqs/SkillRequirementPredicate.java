package alephinfinity1.forgeblock.misc.itemreqs;

import java.util.ArrayList;
import java.util.List;

import alephinfinity1.forgeblock.misc.skills.SkillType;
import alephinfinity1.forgeblock.misc.skills.SkillsHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

/**
 * A predicate used to check whether a player has enough skills to use an item. <br>
 * E.g. AotD can only be used by a player of combat 18 or higher. <br>
 * Each predicate checks one skill only. To require multiple skills, use Predicate#and.
 */
public class SkillRequirementPredicate implements IRequirementPredicate {
	
	private SkillType type;
	private int level;
	
	public SkillRequirementPredicate(SkillType type, int level) {
		this.type = type;
		this.level = level;
	}
	
	@Override
	public boolean test(PlayerEntity t) {
		return SkillsHelper.getSkillLevelOrElse(t, this.type, 0) >= level;
	}
	
	/**
	 * Return a combat requirement.
	 * @param level
	 * @return
	 */
	public static IRequirementPredicate combatRequirement(int level) {
		return new SkillRequirementPredicate(SkillType.COMBAT, level);
	}
	
	/**
	 * Return a skill level requirement.
	 * @param type
	 * @param level
	 * @return
	 */
	public static IRequirementPredicate skillRequirement(SkillType type, int level) {
		return new SkillRequirementPredicate(type, level);
	}
	
	public SkillType getType() {
		return type;
	}
	
	public int getLevel() {
		return level;
	}

	@Override
	public List<ITextComponent> getDisplay(boolean isMet) {	
		List<ITextComponent> list = new ArrayList<>();
		
		/*
		 * \u2714: tick symbol
		 * \u2716: cross symbol
		 */
		
		if (isMet) {
			list.add(new StringTextComponent("\u2714 ").applyTextStyle(TextFormatting.GREEN).appendSibling(new TranslationTextComponent("text.forgeblock.levelReq", this.type.getDisplayName(), this.level).applyTextStyle(TextFormatting.DARK_GREEN)));
		} else {
			list.add(new StringTextComponent("\u2716 ").applyTextStyle(TextFormatting.RED).appendSibling(new TranslationTextComponent("text.forgeblock.levelReq", this.type.getDisplayName(), this.level).applyTextStyle(TextFormatting.RED)));
		}
		return list;
	}
	
	@Override
	public List<ITextComponent> getDisplay(PlayerEntity player) {
		List<ITextComponent> list = new ArrayList<>();
		int playerLvl = SkillsHelper.getSkillLevelOrElse(player, this.type, -1);
		
		/*
		 * \u2714: tick symbol
		 * \u2716: cross symbol
		 */	
		
		if (this.test(player)) {
			list.add(new StringTextComponent("\u2714 ").applyTextStyle(TextFormatting.GREEN).appendSibling(new TranslationTextComponent("text.forgeblock.levelReq", this.type.getDisplayName(), this.level).applyTextStyle(TextFormatting.DARK_GREEN)));
		} else {
			list.add(new StringTextComponent("\u2716 ").applyTextStyle(TextFormatting.RED).appendSibling(new TranslationTextComponent("text.forgeblock.levelReqNotMet", this.type.getDisplayName(), this.level, playerLvl).applyTextStyle(TextFormatting.RED)));
		}
		return list;
	}

}
