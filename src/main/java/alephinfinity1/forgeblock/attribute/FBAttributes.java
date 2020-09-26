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
	public static final IAttribute STRENGTH = (new RangedAttribute((IAttribute) null, "forgeblock.strength", 0.0D, 0.0D, Double.MAX_VALUE)).setDescription("Strength").setShouldWatch(true);
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
	public static final IAttribute DODGE = (new RangedAttribute((IAttribute) null, "forgeblock.dodge", 0.0D, 0.0D, 1.0D)).setDescription("Dodge chance").setShouldWatch(true); //The chance that an enemy attack should miss. Affected by Dodge and Agility potions.
	public static final IAttribute HEALTH_REGEN = (new RangedAttribute((IAttribute) null, "forgeblock.healthRegen", 0.0D, 0.0D, Double.MAX_VALUE)).setDescription("Health Regeneration").setShouldWatch(true); //The amount of health regenerated every second. Non-negative. Affected by regen potions.
	public static final IAttribute MANA_EFFICIENCY = (new RangedAttribute((IAttribute) null, "forgeblock.manaEfficiency", 0.0D, -1.0D, Double.MAX_VALUE)).setDescription("Mana Efficiency").setShouldWatch(true); //How efficient mana is used. Affected by a variety of effects.
	
	//Raw mana cost/damage reduction.
	public static final IAttribute RAW_MANA_COST_REDUCTION = (new RangedAttribute((IAttribute) null, "forgeblock.rawManaCostReduction", 0.0D, -Double.MAX_VALUE, Double.MAX_VALUE)).setDescription("Raw Mana Cost Reduction").setShouldWatch(true); //Directly decreases mana cost. Applied after mana reduction.
	public static final IAttribute RAW_DAMAGE_REDUCTION = (new RangedAttribute((IAttribute) null, "forgeblock.rawDamageReduction", 0.0D, -Double.MAX_VALUE, Double.MAX_VALUE)).setDescription("Raw Damage Reduction").setShouldWatch(true); //Directly decreases damage taken. Applied after defense calculation. Does not heal.
	public static final IAttribute RAW_DAMAGE_DEALT = (new RangedAttribute((IAttribute) null, "forgeblock.rawDamageDealt", 0.0D, -Double.MAX_VALUE, Double.MAX_VALUE)).setDescription("Raw Damage Dealt").setShouldWatch(true); //Directly increases damage. Applied after all other calculations.
	public static final IAttribute RAW_CRIT_DAMAGE = (new RangedAttribute((IAttribute) null, "forgeblock.rawCritDamage", 0.0D, -Double.MAX_VALUE, Double.MAX_VALUE)).setDescription("Raw Crit Damage").setShouldWatch(true); //Directly increases damage on crit.
	
	//Skill XP bonuses
	public static final IAttribute FARMING_XP_BONUS = (new RangedAttribute((IAttribute) null, "forgeblock.farmingXpBonus", 0.0D, -1.0D, Double.MAX_VALUE)).setDescription("Farming XP Bonus").setShouldWatch(true);
	public static final IAttribute MINING_XP_BONUS = (new RangedAttribute((IAttribute) null, "forgeblock.miningXpBonus", 0.0D, -1.0D, Double.MAX_VALUE)).setDescription("Mining XP Bonus").setShouldWatch(true);
	public static final IAttribute COMBAT_XP_BONUS = (new RangedAttribute((IAttribute) null, "forgeblock.combatXpBonus", 0.0D, -1.0D, Double.MAX_VALUE)).setDescription("Combat XP Bonus").setShouldWatch(true);
	public static final IAttribute FORAGING_XP_BONUS = (new RangedAttribute((IAttribute) null, "forgeblock.foragingXpBonus", 0.0D, -1.0D, Double.MAX_VALUE)).setDescription("Foraging XP Bonus").setShouldWatch(true);
	public static final IAttribute FISHING_XP_BONUS = (new RangedAttribute((IAttribute) null, "forgeblock.fishingXpBonus", 0.0D, -1.0D, Double.MAX_VALUE)).setDescription("Fishing XP Bonus").setShouldWatch(true);
	public static final IAttribute ENCHANTING_XP_BONUS = (new RangedAttribute((IAttribute) null, "forgeblock.enchantingXpBonus", 0.0D, -1.0D, Double.MAX_VALUE)).setDescription("Enchanting XP Bonus").setShouldWatch(true);
	public static final IAttribute ALCHEMY_XP_BONUS = (new RangedAttribute((IAttribute) null, "forgeblock.alchemyXpBonus", 0.0D, -1.0D, Double.MAX_VALUE)).setDescription("Alchemy XP Bonus").setShouldWatch(true);
	public static final IAttribute TAMING_XP_BONUS = (new RangedAttribute((IAttribute) null, "forgeblock.tamingXpBonus", 0.0D, -1.0D, Double.MAX_VALUE)).setDescription("Taming XP Bonus").setShouldWatch(true);
	
	//Additional magic find for slayer drops.
	public static final IAttribute ZOMBIE_SLAYER_LUCK = (new RangedAttribute((IAttribute) null, "forgeblock.zombieSlayerLuck", 0.0D, -Double.MAX_VALUE, Double.MAX_VALUE)).setDescription("Zombie Slayer Luck").setShouldWatch(true);
	public static final IAttribute SPIDER_SLAYER_LUCK = (new RangedAttribute((IAttribute) null, "forgeblock.spiderSlayerLuck", 0.0D, -Double.MAX_VALUE, Double.MAX_VALUE)).setDescription("Spider Slayer Luck").setShouldWatch(true);
	public static final IAttribute WOLF_SLAYER_LUCK = (new RangedAttribute((IAttribute) null, "forgeblock.wolfSlayerLuck", 0.0D, -Double.MAX_VALUE, Double.MAX_VALUE)).setDescription("Wolf Slayer Luck").setShouldWatch(true);
	
	//List
	public static final IAttribute[] PRIMARY_ATTRIBUTES = new IAttribute[] {SharedMonsterAttributes.ATTACK_DAMAGE, STRENGTH, CRIT_CHANCE, CRIT_DAMAGE, BONUS_ATTACK_SPEED, SEA_CREATURE_CHANCE, SharedMonsterAttributes.MAX_HEALTH, DEFENSE, SharedMonsterAttributes.MOVEMENT_SPEED, INTELLIGENCE, TRUE_DEFENSE, MAGIC_FIND, PET_LUCK};
}
