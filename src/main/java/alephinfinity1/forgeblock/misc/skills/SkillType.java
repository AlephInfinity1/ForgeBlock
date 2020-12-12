package alephinfinity1.forgeblock.misc.skills;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Function;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableMap;

import alephinfinity1.forgeblock.attribute.FBAttributes;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

public enum SkillType {
	FARMING(false, "farming", 50, null, null, SharedMonsterAttributes.MAX_HEALTH, SkillsCapabilityHandler.ATTRIBUTE_MODIFIER_AMOUNT_1, Skills.FARMING_SKILLS_MODIFIER),
	MINING(false, "mining", 50, null, null, FBAttributes.DEFENSE, SkillsCapabilityHandler.ATTRIBUTE_MODIFIER_AMOUNT_2, Skills.MINING_SKILLS_MODIFIER),
	COMBAT(false, "combat", 50, null, null, FBAttributes.CRIT_CHANCE, SkillsCapabilityHandler.ATTRIBUTE_MODIFIER_AMOUNT_COMBAT, Skills.COMBAT_SKILLS_MODIFIER),
	FORAGING(false, "foraging", 50, null, null, FBAttributes.STRENGTH, SkillsCapabilityHandler.ATTRIBUTE_MODIFIER_AMOUNT_2, Skills.FORAGING_SKILLS_MODIFIER),
	FISHING(false, "fishing", 50, null, null, SharedMonsterAttributes.MAX_HEALTH, SkillsCapabilityHandler.ATTRIBUTE_MODIFIER_AMOUNT_1, Skills.FISHING_SKILLS_MODIFIER),
	ENCHANTING(false, "enchanting", 50, null, null, FBAttributes.INTELLIGENCE, SkillsCapabilityHandler.ATTRIBUTE_MODIFIER_AMOUNT_2, Skills.ENCHANTING_SKILLS_MODIFIER),
	ALCHEMY(false, "alchemy", 50, null, null, FBAttributes.INTELLIGENCE, SkillsCapabilityHandler.ATTRIBUTE_MODIFIER_AMOUNT_2, Skills.ALCHEMY_SKILLS_MODIFIER),
	TAMING(false, "taming", 50, null, null, FBAttributes.PET_LUCK, SkillsCapabilityHandler.ATTRIBUTE_MODIFIER_AMOUNT_TAMING, Skills.TAMING_SKILLS_MODIFIER),
	CARPENTRY(true, "carpentry", 50),
	RUNECRAFTING(true, "runecrafting", 25);
	
	/**
	 * Whether the SkillType is cosmetic or not. If cosmetic, doesn't reward coins and modifiers, and does not count towards skill avg.
	 */
	private boolean isCosmetic;
	
	/**
	 * The name of the skill.
	 */
	private final String id;
	
	/**
	 * The maximum level achievable.
	 */
	private final int maxLevel;
	
	/**
	 * The attribute that the skill modifies.
	 * Null if cosmetic.
	 */
	@Nullable
	private final IAttribute attribute;
	
	/**
	 * A function that maps between skill levels and its corresponding modifier amount.
	 * Null if cosmetic.
	 */
	@Nullable
	private final Function<Integer, Double> modifierAmount;
	
	/**
	 * The UUID of the skill's modifier.
	 * Null if cosmetic.
	 */
	@Nullable
	private final UUID modifierID;
	
	/**
	 * The operation of the skill modifier.
	 * Addition by default, but can be changed.
	 * Null if cosmetic.
	 */
	@Nullable
	private final AttributeModifier.Operation operation;
	
	public Function<Integer, Double> expTable;
	public @Nullable Function<Integer, Double> coinsTable; //If null, the skill doesn't reward coins.
	
	private static final Map<Integer, Double> DEFAULT_XP_TABLE;
	private static final Map<Integer, Double> DEFAULT_COINS_TABLE;
	private static final Map<Integer, Double> RUNECRAFTING_XP_TABLE;
	
	static {
		Map<Integer, Double> aMap = new HashMap<>();
		aMap.put(0, 50.0);
		aMap.put(1, 125.0);
		aMap.put(2, 200.0);
		aMap.put(3, 300.0);
		aMap.put(4, 500.0);
		aMap.put(5, 750.0);
		aMap.put(6, 1_000.0);
		aMap.put(7, 1_500.0);
		aMap.put(8, 2_000.0);
		aMap.put(9, 3_500.0);
		aMap.put(10, 5_000.0);
		aMap.put(11, 7_500.0);
		aMap.put(12, 10_000.0);
		aMap.put(13, 15_000.0);
		aMap.put(14, 20_000.0);
		aMap.put(15, 30_000.0);
		aMap.put(16, 50_000.0);
		aMap.put(17, 75_000.0);
		aMap.put(18, 100_000.0);
		aMap.put(19, 200_000.0);
		aMap.put(20, 300_000.0);
		aMap.put(21, 400_000.0);
		aMap.put(22, 500_000.0);
		aMap.put(23, 600_000.0);
		aMap.put(24, 700_000.0);
		aMap.put(25, 800_000.0);
		aMap.put(26, 900_000.0);
		aMap.put(27, 1_000_000.0);
		aMap.put(28, 1_100_000.0);
		aMap.put(29, 1_200_000.0);
		aMap.put(30, 1_300_000.0);
		aMap.put(31, 1_400_000.0);
		aMap.put(32, 1_500_000.0);
		aMap.put(33, 1_600_000.0);
		aMap.put(34, 1_700_000.0);
		aMap.put(35, 1_800_000.0);
		aMap.put(36, 1_900_000.0);
		aMap.put(37, 2_000_000.0);
		aMap.put(38, 2_100_000.0);
		aMap.put(39, 2_200_000.0);
		aMap.put(40, 2_300_000.0);
		aMap.put(41, 2_400_000.0);
		aMap.put(42, 2_500_000.0);
		aMap.put(43, 2_600_000.0);
		aMap.put(44, 2_750_000.0);
		aMap.put(45, 2_900_000.0);
		aMap.put(46, 3_100_000.0);
		aMap.put(47, 3_400_000.0);
		aMap.put(48, 3_700_000.0);
		aMap.put(49, 4_000_000.0);
		aMap.put(50, 4_400_000.0);
		aMap.put(51, 4_800_000.0);
		aMap.put(52, 5_200_000.0);
		aMap.put(53, 5_600_000.0);
		aMap.put(54, 6_000_000.0);
		aMap.put(55, 6_500_000.0);
		aMap.put(56, 7_000_000.0);
		aMap.put(57, 7_500_000.0);
		aMap.put(58, 8_100_000.0);
		aMap.put(59, 8_750_000.0);
		aMap.put(60, 9_450_000.0);
		aMap.put(61, 10_200_000.0);
		aMap.put(62, 11_000_000.0);
		aMap.put(63, 11_850_000.0);
		aMap.put(64, 12_700_000.0);
		aMap.put(65, 13_550_000.0);
		aMap.put(66, 14_400_000.0);
		aMap.put(67, 15_250_000.0);
		aMap.put(68, 16_100_000.0);
		aMap.put(69, 17_000_000.0);
		aMap.put(70, 17_950_000.0);
		aMap.put(71, 18_950_000.0);
		aMap.put(72, 20_000_000.0);
		aMap.put(73, 21_050_000.0);
		aMap.put(74, 22_150_000.0);
		aMap.put(75, 23_500_000.0);
		aMap.put(76, 25_000_000.0);
		aMap.put(77, 26_500_000.0);
		aMap.put(78, 28_000_000.0);
		aMap.put(79, 29_500_000.0);
		aMap.put(80, 31_000_000.0);
		aMap.put(81, 32_500_000.0);
		aMap.put(82, 34_000_000.0);
		aMap.put(83, 35_750_000.0);
		aMap.put(84, 37_500_000.0);
		aMap.put(85, 39_500_000.0);
		aMap.put(86, 41_500_000.0);
		aMap.put(87, 44_000_000.0);
		aMap.put(88, 47_000_000.0);
		aMap.put(89, 50_000_000.0);
		aMap.put(90, 53_500_000.0);
		aMap.put(91, 57_500_000.0);
		aMap.put(92, 62_000_000.0);
		aMap.put(93, 67_000_000.0);
		aMap.put(94, 72_500_000.0);
		aMap.put(95, 78_500_000.0);
		aMap.put(96, 85_000_000.0);
		aMap.put(97, 92_000_000.0);
		aMap.put(98, 99_500_000.0);
		aMap.put(99, 107_500_000.0);
		DEFAULT_XP_TABLE = ImmutableMap.copyOf(aMap);
		
		Map<Integer, Double> bMap = new HashMap<>();
		bMap.put(0, 0.0);
		bMap.put(1, 25.0);
		bMap.put(2, 50.0);
		bMap.put(3, 100.0);
		bMap.put(4, 200.0);
		bMap.put(5, 300.0);
		bMap.put(6, 400.0);
		bMap.put(7, 500.0);
		bMap.put(8, 600.0);
		bMap.put(9, 700.0);
		bMap.put(10, 800.0);
		bMap.put(11, 900.0);
		bMap.put(12, 1000.0);
		bMap.put(13, 1100.0);
		bMap.put(14, 1200.0);
		bMap.put(15, 1300.0);
		bMap.put(16, 1400.0);
		bMap.put(17, 1500.0);
		bMap.put(18, 1600.0);
		bMap.put(19, 1800.0);
		bMap.put(20, 2000.0);
		bMap.put(21, 2200.0);
		bMap.put(22, 2400.0);
		bMap.put(23, 2600.0);
		bMap.put(24, 2800.0);
		bMap.put(25, 3000.0);
		bMap.put(26, 3500.0);
		bMap.put(27, 4000.0);
		bMap.put(28, 5000.0);
		bMap.put(29, 6000.0);
		bMap.put(30, 7500.0);
		bMap.put(31, 10000.0);
		bMap.put(32, 12500.0);
		bMap.put(33, 15000.0);
		bMap.put(34, 17500.0);
		bMap.put(35, 20000.0);
		bMap.put(36, 25000.0);
		bMap.put(37, 30000.0);
		bMap.put(38, 35000.0);
		bMap.put(39, 40000.0);
		bMap.put(40, 45000.0);
		bMap.put(41, 50000.0);
		bMap.put(42, 60000.0);
		bMap.put(43, 70000.0);
		bMap.put(44, 80000.0);
		bMap.put(45, 90000.0);
		bMap.put(46, 100000.0);
		bMap.put(47, 125000.0);
		bMap.put(48, 150000.0);
		bMap.put(49, 175000.0);
		bMap.put(50, 200000.0);
		bMap.put(51, 225000.0);
		bMap.put(52, 250000.0);
		bMap.put(53, 280000.0);
		bMap.put(54, 310000.0);
		bMap.put(55, 350000.0);
		bMap.put(56, 400000.0);
		bMap.put(57, 465000.0);
		bMap.put(58, 530000.0);
		bMap.put(59, 607500.0);
		bMap.put(60, 700000.0);
		bMap.put(61, 800000.0);
		bMap.put(62, 900000.0);
		bMap.put(63, 1000000.0);
		bMap.put(64, 1100000.0);
		bMap.put(65, 1200000.0);
		bMap.put(66, 1300000.0);
		bMap.put(67, 1400000.0);
		bMap.put(68, 1500000.0);
		bMap.put(69, 1600000.0);
		bMap.put(70, 1700000.0);
		bMap.put(71, 1800000.0);
		bMap.put(72, 1900000.0);
		bMap.put(73, 2000000.0);
		bMap.put(74, 2100000.0);
		bMap.put(75, 2200000.0);
		bMap.put(76, 2300000.0);
		bMap.put(77, 2400000.0);
		bMap.put(78, 2500000.0);
		bMap.put(79, 2600000.0);
		bMap.put(80, 2700000.0);
		bMap.put(81, 2800000.0);
		bMap.put(82, 2900000.0);
		bMap.put(83, 3000000.0);
		bMap.put(84, 3100000.0);
		bMap.put(85, 3200000.0);
		bMap.put(86, 3300000.0);
		bMap.put(87, 3500000.0);
		bMap.put(88, 3750000.0);
		bMap.put(89, 4000000.0);
		bMap.put(90, 4250000.0);
		bMap.put(91, 4500000.0);
		bMap.put(92, 4750000.0);
		bMap.put(93, 5000000.0);
		bMap.put(94, 5500000.0);
		bMap.put(95, 6000000.0);
		bMap.put(96, 7000000.0);
		bMap.put(97, 8000000.0);
		bMap.put(98, 9000000.0);
		bMap.put(99, 10000000.0);
		bMap.put(100, 12000000.0);
		DEFAULT_COINS_TABLE = ImmutableMap.copyOf(bMap);
		
		Map<Integer, Double> cMap = new HashMap<>();
		cMap.put(0, 50.0);
		cMap.put(1, 100.0);
		cMap.put(2, 125.0);
		cMap.put(3, 160.0);
		cMap.put(4, 200.0);
		cMap.put(5, 250.0);
		cMap.put(6, 315.0);
		cMap.put(7, 400.0);
		cMap.put(8, 500.0);
		cMap.put(9, 625.0);
		cMap.put(10, 785.0);
		cMap.put(11, 1000.0);
		cMap.put(12, 1250.0);
		cMap.put(13, 1600.0);
		cMap.put(14, 2000.0);
		cMap.put(15, 2465.0);
		cMap.put(16, 3125.0);
		cMap.put(17, 4000.0);
		cMap.put(18, 5000.0);
		cMap.put(19, 6200.0);
		cMap.put(20, 7800.0);
		cMap.put(21, 9800.0);
		cMap.put(22, 12200.0);
		cMap.put(23, 15300.0);
		cMap.put(24, 19050.0);
		RUNECRAFTING_XP_TABLE = ImmutableMap.copyOf(cMap);
		
		Function<Integer, Double> DEFAULT_XP = (level) -> {
			if(level < 0) throw new IllegalArgumentException();
			else if (level >= 100) return Double.NaN;
			else return DEFAULT_XP_TABLE.get(level);
		};
		
		Function<Integer, Double> RUNECRAFTING_XP = (level) -> {
			if(level < 0) throw new IllegalArgumentException();
			else if (level >= 25) return Double.NaN;
			else return RUNECRAFTING_XP_TABLE.get(level);
		};
		
		Function<Integer, Double> DEFAULT_COINS = (level) -> {
			if(level < 0) throw new IllegalArgumentException();
			else if (level >= 100) return 0.0D;
			else return DEFAULT_COINS_TABLE.get(level);
		};
		
		for(SkillType type : SkillType.values()) {
			if(type != RUNECRAFTING) {
				type.expTable = DEFAULT_XP;
				type.coinsTable = DEFAULT_COINS;
			} else {
				type.expTable = RUNECRAFTING_XP;
				type.coinsTable = null;
			}
		}
	}
	
	private SkillType(boolean isCosmetic, String id, int maxLevel, Function<Integer, Double> expTable) {
		this.isCosmetic = isCosmetic;
		this.id = id;
		this.maxLevel = maxLevel;
		this.expTable = expTable;
		this.coinsTable = null;
		this.attribute = null;
		this.modifierAmount = null;
		this.modifierID = null;
		this.operation = isCosmetic ? null : Operation.ADDITION;
	}
	
	private SkillType(boolean isCosmetic, String id, int maxLevel, Function<Integer, Double> expTable, Function<Integer, Double> coinsTable) {
		this.isCosmetic = isCosmetic;
		this.id = id;
		this.maxLevel = maxLevel;
		this.expTable = expTable;
		this.coinsTable = coinsTable;
		this.attribute = null;
		this.modifierAmount = null;
		this.modifierID = null;
		this.operation = isCosmetic ? null : Operation.ADDITION;
	}
	
	private SkillType(boolean isCosmetic, String id, int maxLevel) {
		this.isCosmetic = isCosmetic;
		this.id = id;
		this.maxLevel = maxLevel;
		this.expTable = null;
		this.coinsTable = null;
		this.attribute = null;
		this.modifierAmount = null;
		this.modifierID = null;
		this.operation = isCosmetic ? null : Operation.ADDITION;
	}
	
	private SkillType(boolean isCosmetic, String id, int maxLevel, Function<Integer, Double> expTable, 
			Function<Integer, Double> coinsTable, IAttribute attribute, Function<Integer, Double> modifierAmount, UUID modifierID) {
		this.isCosmetic = isCosmetic;
		this.id = id;
		this.maxLevel = maxLevel;
		this.expTable = expTable;
		this.coinsTable = coinsTable;
		this.attribute = attribute;
		this.modifierAmount = modifierAmount;
		this.modifierID = modifierID;
		this.operation = isCosmetic ? null : Operation.ADDITION;
	}
	
	private SkillType(boolean isCosmetic, String id, int maxLevel, Function<Integer, Double> expTable, 
			Function<Integer, Double> coinsTable, IAttribute attribute, 
			Function<Integer, Double> modifierAmount, UUID modifierID, AttributeModifier.Operation operation) {
		this.isCosmetic = isCosmetic;
		this.id = id;
		this.maxLevel = maxLevel;
		this.expTable = expTable;
		this.coinsTable = coinsTable;
		this.attribute = attribute;
		this.modifierAmount = modifierAmount;
		this.modifierID = modifierID;
		this.operation = operation;
	}
	
	public double getXPForLevel(int level) {
		if (this.expTable != null) {
			return this.expTable.apply(level);
		} else return 0;
	}
	
	public boolean getIsMaxLevel(int level) {
		return level == this.maxLevel;
	}
	
	public boolean getIsValidLevel(int level) {
		return level >= 0 && level <= this.maxLevel;
	}
	
	public int getMaxLevel() {
		return this.maxLevel;
	}
	
	public double getCoinsRewardUponReachingLevel(int level) {
		if (this.coinsTable != null) {
			return this.coinsTable.apply(level);
		} else return 0;
	}
	
	public boolean isCosmetic() {
		return isCosmetic;
	}
	
	public String getID() {
		return id;
	}
	
	public static SkillType getSkillTypeByID(String id) {	
		for (SkillType type : SkillType.values()) {
			if (type.getID().equals(id)) return type;
		}
		
		throw new IllegalArgumentException("ID does not match with any of the skills!");
	}
	
	public double getAttributeModifierAmount(int level) {
		if (Objects.isNull(this.modifierAmount)) return 0.0D;
		return this.modifierAmount.apply(level);
	}
	
	public ITextComponent getDisplayName() {
		return new TranslationTextComponent("skills.forgeblock." + id + ".name");
	}
	
	public ITextComponent getAbilityName(int level) {
		return new TranslationTextComponent("skills.forgeblock." + id + ".ability", level).applyTextStyle(TextFormatting.YELLOW);
	}
	
	public static final SkillType[] NON_COSMETIC_TYPES = new SkillType[] {
			FARMING,
			MINING,
			COMBAT,
			FORAGING,
			FISHING,
			ENCHANTING,
			ALCHEMY,
			TAMING
	};
	
	/**
	 * Returns the attribute to be modified.
	 * Null iff cosmetic.
	 * @return
	 */
	@Nullable
	public IAttribute getAttribute() {
		return this.attribute;
	}
	
	/**
	 * Returns the UUID to be used with the modifier.
	 * Null iff cosmetic.
	 * @return
	 */
	@Nullable
	public UUID getModifierUUID() {
		return this.modifierID;
	}
	
	/**
	 * Returns the operation of the modifier.
	 * Null iff cosmetic.
	 * @return
	 */
	@Nullable
	public AttributeModifier.Operation getOperation() {
		return this.operation;
	}
}
