package alephinfinity1.forgeblock.misc.skills;

/*
 * Static import all SkillType's for readability and brevity.
 */
import static alephinfinity1.forgeblock.misc.skills.SkillType.ALCHEMY;
import static alephinfinity1.forgeblock.misc.skills.SkillType.CARPENTRY;
import static alephinfinity1.forgeblock.misc.skills.SkillType.COMBAT;
import static alephinfinity1.forgeblock.misc.skills.SkillType.ENCHANTING;
import static alephinfinity1.forgeblock.misc.skills.SkillType.FARMING;
import static alephinfinity1.forgeblock.misc.skills.SkillType.FISHING;
import static alephinfinity1.forgeblock.misc.skills.SkillType.FORAGING;
import static alephinfinity1.forgeblock.misc.skills.SkillType.MINING;
import static alephinfinity1.forgeblock.misc.skills.SkillType.RUNECRAFTING;
import static alephinfinity1.forgeblock.misc.skills.SkillType.TAMING;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import com.google.common.collect.Multimap;

import alephinfinity1.forgeblock.attribute.FBAttributes;
import alephinfinity1.forgeblock.misc.CompareTuple;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.nbt.CompoundNBT;

public class Skills implements ISkills {
	
	private Map<SkillType, SkillData> data = new HashMap<>();
	public static final UUID FARMING_SKILLS_MODIFIER = UUID.fromString("d54a42a2-4193-45c0-8a26-793476419ff1");
	public static final UUID MINING_SKILLS_MODIFIER = UUID.fromString("e8839231-c0f0-4989-82fe-51a5081a47d8");
	public static final UUID COMBAT_SKILLS_MODIFIER = UUID.fromString("b06853a7-ee65-492b-8807-667ce66ec794");
	public static final UUID FORAGING_SKILLS_MODIFIER = UUID.fromString("2b864aca-8ca6-486a-85cd-0ebb5f15931a");
	public static final UUID FISHING_SKILLS_MODIFIER = UUID.fromString("8a9bc966-ee36-44c4-b9e7-4a719642a04f");
	public static final UUID ENCHANTING_SKILLS_MODIFIER = UUID.fromString("cacedfe3-2776-477d-99eb-f0def6f6c967");
	public static final UUID ALCHEMY_SKILLS_MODIFIER = UUID.fromString("58eda9ef-9c2b-4204-831c-0258c0bc241a");
	public static final UUID TAMING_SKILLS_MODIFIER = UUID.fromString("5be88810-b564-45e1-a394-890486a3dff6");
	
	public Skills() {
		data.put(FARMING, new SkillData(FARMING));
		data.put(MINING, new SkillData(MINING));
		data.put(COMBAT, new SkillData(COMBAT));
		data.put(FORAGING, new SkillData(FORAGING));
		data.put(FISHING, new SkillData(FISHING));
		data.put(ENCHANTING, new SkillData(ENCHANTING));
		data.put(ALCHEMY, new SkillData(ALCHEMY));
		data.put(TAMING, new SkillData(TAMING));
		
		data.put(CARPENTRY, new SkillData(CARPENTRY));
		data.put(RUNECRAFTING, new SkillData(RUNECRAFTING));
	}
	
	public Skills(ISkills other) {
		data.put(FARMING, other.getSkillDataForSkill(FARMING).copy());
		data.put(MINING, other.getSkillDataForSkill(MINING).copy());
		data.put(COMBAT, other.getSkillDataForSkill(COMBAT).copy());
		data.put(FORAGING, other.getSkillDataForSkill(FORAGING).copy());
		data.put(FISHING, other.getSkillDataForSkill(FISHING).copy());
		data.put(ENCHANTING, other.getSkillDataForSkill(ENCHANTING).copy());
		data.put(ALCHEMY, other.getSkillDataForSkill(ALCHEMY).copy());
		data.put(TAMING, other.getSkillDataForSkill(TAMING).copy());
		
		data.put(CARPENTRY, other.getSkillDataForSkill(CARPENTRY).copy());
		data.put(RUNECRAFTING, other.getSkillDataForSkill(RUNECRAFTING).copy());
	}
	
	@Override
	public void set(ISkills other) {
		data.put(FARMING, other.getSkillDataForSkill(FARMING).copy());
		data.put(MINING, other.getSkillDataForSkill(MINING).copy());
		data.put(COMBAT, other.getSkillDataForSkill(COMBAT).copy());
		data.put(FORAGING, other.getSkillDataForSkill(FORAGING).copy());
		data.put(FISHING, other.getSkillDataForSkill(FISHING).copy());
		data.put(ENCHANTING, other.getSkillDataForSkill(ENCHANTING).copy());
		data.put(ALCHEMY, other.getSkillDataForSkill(ALCHEMY).copy());
		data.put(TAMING, other.getSkillDataForSkill(TAMING).copy());
		
		data.put(CARPENTRY, other.getSkillDataForSkill(CARPENTRY).copy());
		data.put(RUNECRAFTING, other.getSkillDataForSkill(RUNECRAFTING).copy());
	}
	
	@Override
	public Map<SkillType, SkillData> getData() {
		return data;
	}
	
	@Override
	public SkillData getSkillDataForSkill(SkillType skill) {
		return data.get(skill);
	}

	@Override
	public int getLevel(SkillType skill) {
		return data.get(skill).getLevel();
	}

	/*
	 * Gets the absolute progress to the next level. E.g. 4,530/100,000
	 */
	@Override
	public double getAbsoluteProgress(SkillType skill) {
		return data.get(skill).getAbsoluteProgress();
	}

	/*
	 * Gets the percentage progress to the next level. E.g. 4.53%
	 */
	@Override
	public double getProgressPercentage(SkillType skill) {
		return data.get(skill).getProgressPercentage();
	}

	@Override
	@Deprecated
	public CompareTuple<Integer> addXP(SkillType skill, double amount) {
		int prevLevel = this.getLevel(skill);
		data.get(skill).addXP(amount);
		int newLevel = this.getLevel(skill);
		return new CompareTuple<Integer>(prevLevel, newLevel);
	}

	@Override
	public void setLevel(SkillType skill, int level) {
		data.get(skill).setLevel(level);
	}

	@Override
	public void setProgress(SkillType skill, double progress) {
		data.get(skill).setProgress(progress);
	}

	@Override
	public double getXPNeededToLevelUp(SkillType skill) {
		return skill.getXPForLevel(data.get(skill).getLevel());
	}

	@Override
	public CompoundNBT getCompoundNBTFor(SkillType skill) {
		CompoundNBT nbt = new CompoundNBT();
		nbt.putString("SkillType", skill.getID());
		nbt.putInt("Level", this.getLevel(skill));
		nbt.putDouble("Progress", this.getAbsoluteProgress(skill));
		return nbt;
	}
	
	public Multimap<String, AttributeModifier> getAttributeModifiers() {
		final SkillType[] types = new SkillType[] {FARMING, MINING, COMBAT, FORAGING, FISHING, 
				ENCHANTING, ALCHEMY, TAMING, CARPENTRY, RUNECRAFTING};
		Builder<String, AttributeModifier> builder = ImmutableMultimap.builder();
		
		for(SkillType type : types) {
			switch(type) {
			case FARMING:
				builder.put(SharedMonsterAttributes.MAX_HEALTH.getName(), 
						new AttributeModifier(FARMING_SKILLS_MODIFIER, "Farming bonus", type.getAttributeModifierAmount(this.getLevel(type)), Operation.ADDITION));
				break;
			case MINING:
				builder.put(FBAttributes.DEFENSE.getName(), 
						new AttributeModifier(MINING_SKILLS_MODIFIER, "Mining bonus", type.getAttributeModifierAmount(this.getLevel(type)), Operation.ADDITION));
				break;
			case COMBAT:
				builder.put(FBAttributes.CRIT_CHANCE.getName(), 
						new AttributeModifier(COMBAT_SKILLS_MODIFIER, "Combat bonus", type.getAttributeModifierAmount(this.getLevel(type)), Operation.ADDITION));
				break;
			case FORAGING:
				builder.put(FBAttributes.STRENGTH.getName(), 
						new AttributeModifier(FORAGING_SKILLS_MODIFIER, "Foraging bonus", type.getAttributeModifierAmount(this.getLevel(type)), Operation.ADDITION));
				break;
			case FISHING:
				builder.put(SharedMonsterAttributes.MAX_HEALTH.getName(), 
						new AttributeModifier(FISHING_SKILLS_MODIFIER, "Fishing bonus", type.getAttributeModifierAmount(this.getLevel(type)), Operation.ADDITION));
				break;
			case ENCHANTING:
				builder.put(FBAttributes.INTELLIGENCE.getName(), 
						new AttributeModifier(ENCHANTING_SKILLS_MODIFIER, "Enchanting bonus", type.getAttributeModifierAmount(this.getLevel(type)), Operation.ADDITION));
				break;
			case ALCHEMY:
				builder.put(FBAttributes.INTELLIGENCE.getName(), 
						new AttributeModifier(ALCHEMY_SKILLS_MODIFIER, "Alchemy bonus", type.getAttributeModifierAmount(this.getLevel(type)), Operation.ADDITION));
				break;
			case TAMING:
				builder.put(FBAttributes.PET_LUCK.getName(), 
						new AttributeModifier(TAMING_SKILLS_MODIFIER, "Taming bonus", type.getAttributeModifierAmount(this.getLevel(type)), Operation.ADDITION));
				break;
			default:
				break;
			}
		}
		
		return builder.build();
	}

	@Override
	public ISkills copy() {
		return new Skills(this);
	}

}
