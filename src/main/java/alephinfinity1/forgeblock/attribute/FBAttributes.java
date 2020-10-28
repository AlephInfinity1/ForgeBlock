package alephinfinity1.forgeblock.attribute;

import java.util.Arrays;
import java.util.stream.Stream;

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
	public static final IAttribute INTELLIGENCE = (new RangedAttribute((IAttribute) null, "forgeblock.intelligence", 0.0D, -Double.MAX_VALUE, Double.MAX_VALUE)).setDescription("Intelligence").setShouldWatch(true);
	public static final IAttribute SEA_CREATURE_CHANCE = (new RangedAttribute((IAttribute) null, "forgeblock.seaCreatureChance", 0.0D, 0.0D, 1.0D)).setDescription("Sea Creature Chance").setShouldWatch(true);
	public static final IAttribute MAGIC_FIND = (new RangedAttribute((IAttribute) null, "forgeblock.magicFind", 0.0D, -Double.MAX_VALUE, Double.MAX_VALUE)).setDescription("Magic Find").setShouldWatch(true);
	public static final IAttribute PET_LUCK = (new RangedAttribute((IAttribute) null, "forgeblock.petLuck", 0.0D, -Double.MAX_VALUE, Double.MAX_VALUE)).setDescription("Pet Luck").setShouldWatch(true);
	public static final IAttribute FEROCITY = (new RangedAttribute((IAttribute) null, "forgeblock.ferocity", 0.0D, 0.0D, Double.MAX_VALUE)).setDescription("Ferocity").setShouldWatch(true);
	
	//Supplementary attributes (not listed as attributes on the wiki page)
	public static final IAttribute ABILITY_DAMAGE = (new RangedAttribute((IAttribute) null, "forgeblock.abilityDamage", 0.0D, -Double.MAX_VALUE, Double.MAX_VALUE)).setDescription("Ability Damage").setShouldWatch(true); //Ability damage. Increased by sheep pet and superior dragon armor (AotD).
	public static final IAttribute DODGE = (new RangedAttribute((IAttribute) null, "forgeblock.dodge", 0.0D, 0.0D, 100.0D)).setDescription("Dodge chance").setShouldWatch(true); //The chance that an enemy attack should miss. Affected by Dodge and Agility potions.
	public static final IAttribute HEALTH_REGEN = (new RangedAttribute((IAttribute) null, "forgeblock.healthRegen", 0.0D, -Double.MAX_VALUE, Double.MAX_VALUE)).setDescription("Health Regeneration").setShouldWatch(true); //The amount of health regenerated every second. Can be negative (damages health). Affected by regen potions.
	public static final IAttribute MANA_EFFICIENCY = (new RangedAttribute((IAttribute) null, "forgeblock.manaEfficiency", 0.0D, -1.0D, Double.MAX_VALUE)).setDescription("Mana Efficiency").setShouldWatch(true); //How efficient mana is used. Affected by a variety of effects.
	public static final IAttribute MANA_REGEN = (new RangedAttribute((IAttribute) null, "forgeblock.manaRegen", 0.0D, -Double.MAX_VALUE, Double.MAX_VALUE)).setDescription("Mana Regeneration").setShouldWatch(true); //The amount of mana regenerated every second. CAN BE NEGATIVE (negative costs mana). Affected by regen potions.
	public static final IAttribute COOLDOWN_REDUCTION = (new RangedAttribute((IAttribute) null, "forgeblock.cooldownReduction", 0.0D, -Double.MAX_VALUE, 100.0D)).setDescription("Cooldown Reduction").setShouldWatch(true); //Cooldown reduction, in %. Max 100.
	public static final IAttribute DEATH_COIN_PENALTY_REDUCTION = (new RangedAttribute((IAttribute) null, "forgeblock.deathCoinsPenaltyReduction", 0.0D, -Double.MAX_VALUE, Double.MAX_VALUE)).setDescription("Death Coins Penalty Reduction").setShouldWatch(true); //Death coins penalty reduction. Affected by Wolf VIII and bank.
	
	//Raw mana cost/damage reduction.
	public static final IAttribute RAW_MANA_COST_REDUCTION = (new RangedAttribute((IAttribute) null, "forgeblock.rawManaCostReduction", 0.0D, -Double.MAX_VALUE, Double.MAX_VALUE)).setDescription("Raw Mana Cost Reduction").setShouldWatch(true); //Directly decreases mana cost. Applied after mana reduction.
	public static final IAttribute RAW_DAMAGE_REDUCTION = (new RangedAttribute((IAttribute) null, "forgeblock.rawDamageReduction", 0.0D, -Double.MAX_VALUE, Double.MAX_VALUE)).setDescription("Raw Damage Reduction").setShouldWatch(true); //Directly decreases damage taken. Applied after defense calculation. Does not heal.
	public static final IAttribute RAW_DAMAGE_DEALT = (new RangedAttribute((IAttribute) null, "forgeblock.rawDamageDealt", 0.0D, -Double.MAX_VALUE, Double.MAX_VALUE)).setDescription("Raw Damage Dealt").setShouldWatch(true); //Directly increases damage. Applied after all other calculations.
	public static final IAttribute RAW_CRIT_DAMAGE = (new RangedAttribute((IAttribute) null, "forgeblock.rawCritDamage", 0.0D, -Double.MAX_VALUE, Double.MAX_VALUE)).setDescription("Raw Crit Damage").setShouldWatch(true); //Directly increases damage on crit.
	public static final IAttribute RAW_COOLDOWN_REDUCTION = (new RangedAttribute((IAttribute) null, "forgeblock.rawCooldownReduction", 0.0D, -Double.MAX_VALUE, Double.MAX_VALUE)).setDescription("Raw Cooldown Reduction").setShouldWatch(true); //Raw cooldown reduction, in game ticks.
	
	//Skill XP bonuses
	public static final IAttribute ALL_SKILLS_XP_BOOST = (new RangedAttribute((IAttribute) null, "forgeblock.allSkillsXpBoost", 0.0D, -1.0D, Double.MAX_VALUE)).setDescription("All Skills XP Boost").setShouldWatch(true);
	public static final IAttribute FARMING_XP_BOOST = (new RangedAttribute((IAttribute) null, "forgeblock.farmingXpBoost", 0.0D, -1.0D, Double.MAX_VALUE)).setDescription("Farming XP Boost").setShouldWatch(true);
	public static final IAttribute MINING_XP_BOOST = (new RangedAttribute((IAttribute) null, "forgeblock.miningXpBoost", 0.0D, -1.0D, Double.MAX_VALUE)).setDescription("Mining XP Boost").setShouldWatch(true);
	public static final IAttribute COMBAT_XP_BOOST = (new RangedAttribute((IAttribute) null, "forgeblock.combatXpBoost", 0.0D, -1.0D, Double.MAX_VALUE)).setDescription("Combat XP Boost").setShouldWatch(true);
	public static final IAttribute FORAGING_XP_BOOST = (new RangedAttribute((IAttribute) null, "forgeblock.foragingXpBoost", 0.0D, -1.0D, Double.MAX_VALUE)).setDescription("Foraging XP Boost").setShouldWatch(true);
	public static final IAttribute FISHING_XP_BOOST = (new RangedAttribute((IAttribute) null, "forgeblock.fishingXpBoost", 0.0D, -1.0D, Double.MAX_VALUE)).setDescription("Fishing XP Boost").setShouldWatch(true);
	public static final IAttribute ENCHANTING_XP_BOOST = (new RangedAttribute((IAttribute) null, "forgeblock.enchantingXpBoost", 0.0D, -1.0D, Double.MAX_VALUE)).setDescription("Enchanting XP Boost").setShouldWatch(true);
	public static final IAttribute ALCHEMY_XP_BOOST = (new RangedAttribute((IAttribute) null, "forgeblock.alchemyXpBoost", 0.0D, -1.0D, Double.MAX_VALUE)).setDescription("Alchemy XP Boost").setShouldWatch(true);
	public static final IAttribute TAMING_XP_BOOST = (new RangedAttribute((IAttribute) null, "forgeblock.tamingXpBoost", 0.0D, -1.0D, Double.MAX_VALUE)).setDescription("Taming XP Boost").setShouldWatch(true);
	
	//Additional magic find for slayer drops.
	public static final IAttribute ZOMBIE_SLAYER_LUCK = (new RangedAttribute((IAttribute) null, "forgeblock.zombieSlayerLuck", 0.0D, -Double.MAX_VALUE, Double.MAX_VALUE)).setDescription("Zombie Slayer Luck").setShouldWatch(true);
	public static final IAttribute SPIDER_SLAYER_LUCK = (new RangedAttribute((IAttribute) null, "forgeblock.spiderSlayerLuck", 0.0D, -Double.MAX_VALUE, Double.MAX_VALUE)).setDescription("Spider Slayer Luck").setShouldWatch(true);
	public static final IAttribute WOLF_SLAYER_LUCK = (new RangedAttribute((IAttribute) null, "forgeblock.wolfSlayerLuck", 0.0D, -Double.MAX_VALUE, Double.MAX_VALUE)).setDescription("Wolf Slayer Luck").setShouldWatch(true);
	
	//List
	public static final IAttribute[] PRIMARY_ATTRIBUTES = new IAttribute[] {SharedMonsterAttributes.ATTACK_DAMAGE, STRENGTH, CRIT_CHANCE,
			CRIT_DAMAGE, BONUS_ATTACK_SPEED, SEA_CREATURE_CHANCE, SharedMonsterAttributes.MAX_HEALTH, DEFENSE, SharedMonsterAttributes.MOVEMENT_SPEED,
			INTELLIGENCE, TRUE_DEFENSE, MAGIC_FIND, PET_LUCK, FEROCITY};
	
	public static final IAttribute[] OFFENSIVE_ATTRIBUTES = new IAttribute[] {SharedMonsterAttributes.ATTACK_DAMAGE, STRENGTH, CRIT_CHANCE, CRIT_DAMAGE,
			BONUS_ATTACK_SPEED, SEA_CREATURE_CHANCE, FEROCITY};
	
	public static final IAttribute[] DEFENSIVE_ATTRIBUTES = new IAttribute[] {SharedMonsterAttributes.MAX_HEALTH, DEFENSE, SharedMonsterAttributes.MOVEMENT_SPEED, INTELLIGENCE, TRUE_DEFENSE, MAGIC_FIND, PET_LUCK};
	
	public static final IAttribute[] ADDITIONAL_ATTRIBUTES = new IAttribute[] {ABILITY_DAMAGE, DODGE, HEALTH_REGEN, MANA_EFFICIENCY,
			MANA_REGEN, COOLDOWN_REDUCTION, DEATH_COIN_PENALTY_REDUCTION};
	
	public static final IAttribute[] RAW_ATTRIBUTES = new IAttribute[] {RAW_MANA_COST_REDUCTION, RAW_DAMAGE_REDUCTION, RAW_DAMAGE_DEALT,
			RAW_CRIT_DAMAGE, RAW_COOLDOWN_REDUCTION};
	
	public static final IAttribute[] EXTRA_ATTRIBUTES = Stream.concat(Arrays.stream(ADDITIONAL_ATTRIBUTES), Arrays.stream(RAW_ATTRIBUTES)).toArray(IAttribute[]::new);
	
	public static final IAttribute[] SKILL_XP_BOOSTS = new IAttribute[] {ALL_SKILLS_XP_BOOST, FARMING_XP_BOOST, MINING_XP_BOOST, COMBAT_XP_BOOST,
			FORAGING_XP_BOOST, FISHING_XP_BOOST, ENCHANTING_XP_BOOST, ALCHEMY_XP_BOOST, TAMING_XP_BOOST};

	public static final IAttribute[] SLAYER_LUCKS = new IAttribute[] {ZOMBIE_SLAYER_LUCK, SPIDER_SLAYER_LUCK, WOLF_SLAYER_LUCK};
}
