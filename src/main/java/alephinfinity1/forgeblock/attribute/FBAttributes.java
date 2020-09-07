package alephinfinity1.forgeblock.attribute;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;

/*
 * A list of all custom attributes added by ForgeBlock
 */
public final class FBAttributes {
	/*
	 * Attributes that are primarily used in-game, but not listed here:
	 * - Health (Use SharedMonsterAttributes.MAX_HEALTH)
	 * - Damage (Use SharedMonsterAttributes.ATTACK_DAMAGE)
	 * - Speed (Use SharedMonsterAttributes.MOVEMENT_SPEED)
	 */
	
	//Primary attributes
	public static final IAttribute STRENGTH = (new RangedAttribute((IAttribute) null, "forgeblock.strength", 0.0D, -Double.MAX_VALUE, Double.MAX_VALUE)).setDescription("Strength").setShouldWatch(true);
	public static final IAttribute DEFENSE = (new RangedAttribute((IAttribute) null, "forgeblock.defense", 0.0D, 0.0D, Double.MAX_VALUE)).setDescription("Defense").setShouldWatch(true);
	public static final IAttribute TRUE_DEFENSE = (new RangedAttribute((IAttribute) null, "forgeblock.trueDefense", 0.0D, 0.0D, Double.MAX_VALUE)).setDescription("True Defense").setShouldWatch(true);
	public static final IAttribute CRIT_CHANCE = (new RangedAttribute((IAttribute) null, "forgeblock.critChance", 30.0D, 0.0D, 100.0D)).setDescription("Crit Chance").setShouldWatch(true);
	public static final IAttribute CRIT_DAMAGE = (new RangedAttribute((IAttribute) null, "forgeblock.critDamage", 50.0D, -Double.MAX_VALUE, Double.MAX_VALUE)).setDescription("Crit Damage").setShouldWatch(true);
	public static final IAttribute BONUS_ATTACK_SPEED = (new RangedAttribute((IAttribute) null, "forgeblock.bonusAttackSpeed", 0.0D, 0.0D, 1.0D)).setDescription("Bonus Attack Speed").setShouldWatch(true);
	public static final IAttribute INTELLIGENCE = (new RangedAttribute((IAttribute) null, "forgeblock.intellgence", 0.0D, -Double.MAX_VALUE, Double.MAX_VALUE)).setDescription("Intelligence").setShouldWatch(true);
	public static final IAttribute SEA_CREATURE_CHANCE = (new RangedAttribute((IAttribute) null, "forgeblock.seaCreatureChance", 0.0D, 0.0D, 1.0D)).setDescription("Sea Creature Chance").setShouldWatch(true);
	public static final IAttribute MAGIC_FIND = (new RangedAttribute((IAttribute) null, "forgeblock.magicFind", 0.0D, -Double.MAX_VALUE, Double.MAX_VALUE)).setDescription("Magic Find").setShouldWatch(true);
	public static final IAttribute PET_LUCK = (new RangedAttribute((IAttribute) null, "forgeblock.petLuck", 0.0D, -Double.MAX_VALUE, Double.MAX_VALUE)).setDescription("Pet Luck").setShouldWatch(true);
	
	//Supplementary attributes (not listed as attributes on the wiki page)
	public static final IAttribute ABILITY_DAMAGE = (new RangedAttribute((IAttribute) null, "forgeblock.abilityDamaage", 0.0D, -Double.MAX_VALUE, Double.MAX_VALUE)).setDescription("Ability Damage").setShouldWatch(true); //Ability damage. Increased by sheep pet and superior dragon armor (AotD).
	public static final IAttribute COMBAT_XP_BOOST = (new RangedAttribute((IAttribute) null, "forgeblock.combatXPBoost", 0.0D, -1.0D, Double.MAX_VALUE)).setDescription("Combat XP Boost").setShouldWatch(true); //The percentage increase in Combat XP gain. Affected by Slayers and XP Boost potions.
	public static final IAttribute DODGE = (new RangedAttribute((IAttribute) null, "forgeblock.dodge", 0.0D, 0.0D, 1.0D)).setDescription("Dodge chance").setShouldWatch(true); //The chance that an enemy attack should miss. Affected by Dodge and Agility potions.
	public static final IAttribute HEALTH_REGEN = (new RangedAttribute((IAttribute) null, "forgeblock.healthRegen", 0.0D, 0.0D, Double.MAX_VALUE)).setDescription("Health Regeneration").setShouldWatch(true); //The amount of health regenerated every second. Non-negative. Affected by regen potions.
	public static final IAttribute MANA_EFFICIENCY = (new RangedAttribute((IAttribute) null, "forgeblock.manaEfficiency", 0.0D, -1.0D, Double.MAX_VALUE)).setDescription("Mana Efficiency").setShouldWatch(true); //How efficient mana is used. Affected by a variety of effects.
	
	//List
	public static final IAttribute[] PRIMARY_ATTRIBUTES = new IAttribute[] {SharedMonsterAttributes.ATTACK_DAMAGE, STRENGTH, CRIT_CHANCE, CRIT_DAMAGE, BONUS_ATTACK_SPEED, SEA_CREATURE_CHANCE, SharedMonsterAttributes.MAX_HEALTH, DEFENSE, SharedMonsterAttributes.MOVEMENT_SPEED, INTELLIGENCE, TRUE_DEFENSE, MAGIC_FIND, PET_LUCK};
}
