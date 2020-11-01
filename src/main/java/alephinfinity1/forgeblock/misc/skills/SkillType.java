package alephinfinity1.forgeblock.misc.skills;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableMap;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public enum SkillType {
	FARMING(false, "farming", 50),
	MINING(false, "mining", 50),
	COMBAT(false, "combat", 50),
	FORAGING(false, "foraging", 50),
	FISHING(false, "fishing", 50),
	ENCHANTING(false, "enchanting", 50),
	ALCHEMY(false, "alchemy", 50),
	TAMING(false, "taming", 50),
	CARPENTRY(true, "carpentry", 50),
	RUNECRAFTING(true, "runecrafting", 25);
	
	private boolean isCosmetic;
	private final String id;
	private final int maxLevel;
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
		aMap.put(6, 1000.0);
		aMap.put(7, 1500.0);
		aMap.put(8, 2000.0);
		aMap.put(9, 3500.0);
		aMap.put(10, 5000.0);
		aMap.put(11, 7500.0);
		aMap.put(12, 10000.0);
		aMap.put(13, 15000.0);
		aMap.put(14, 20000.0);
		aMap.put(15, 30000.0);
		aMap.put(16, 50000.0);
		aMap.put(17, 75000.0);
		aMap.put(18, 100000.0);
		aMap.put(19, 200000.0);
		aMap.put(20, 300000.0);
		aMap.put(21, 400000.0);
		aMap.put(22, 500000.0);
		aMap.put(23, 600000.0);
		aMap.put(24, 700000.0);
		aMap.put(25, 800000.0);
		aMap.put(26, 900000.0);
		aMap.put(27, 1000000.0);
		aMap.put(28, 1100000.0);
		aMap.put(29, 1200000.0);
		aMap.put(30, 1300000.0);
		aMap.put(31, 1400000.0);
		aMap.put(32, 1500000.0);
		aMap.put(33, 1600000.0);
		aMap.put(34, 1700000.0);
		aMap.put(35, 1800000.0);
		aMap.put(36, 1900000.0);
		aMap.put(37, 2000000.0);
		aMap.put(38, 2100000.0);
		aMap.put(39, 2200000.0);
		aMap.put(40, 2300000.0);
		aMap.put(41, 2400000.0);
		aMap.put(42, 2500000.0);
		aMap.put(43, 2600000.0);
		aMap.put(44, 2750000.0);
		aMap.put(45, 2900000.0);
		aMap.put(46, 3100000.0);
		aMap.put(47, 3400000.0);
		aMap.put(48, 3700000.0);
		aMap.put(49, 4000000.0);
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
			else if (level >= 50) return Double.NaN;
			else return DEFAULT_XP_TABLE.get(level);
		};
		
		Function<Integer, Double> RUNECRAFTING_XP = (level) -> {
			if(level < 0) throw new IllegalArgumentException();
			else if (level >= 25) return Double.NaN;
			else return RUNECRAFTING_XP_TABLE.get(level);
		};
		
		Function<Integer, Double> DEFAULT_COINS = (level) -> {
			if(level < 0) throw new IllegalArgumentException();
			else if (level >= 50) return 0.0D;
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
	}
	
	private SkillType(boolean isCosmetic, String id, int maxLevel, Function<Integer, Double> expTable, Function<Integer, Double> coinsTable) {
		this.isCosmetic = isCosmetic;
		this.id = id;
		this.maxLevel = maxLevel;
		this.expTable = expTable;
		this.coinsTable = coinsTable;
	}
	
	private SkillType(boolean isCosmetic, String id, int maxLevel) {
		this.isCosmetic = isCosmetic;
		this.id = id;
		this.maxLevel = maxLevel;
		this.expTable = null;
		this.coinsTable = null;
	}
	
	public double getXPForLevel(int level) {
		if(this.expTable != null) {
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
		if(this.coinsTable != null) {
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
		final SkillType[] types = new SkillType[] {FARMING, MINING, COMBAT, FORAGING, FISHING, 
				ENCHANTING, ALCHEMY, TAMING, CARPENTRY, RUNECRAFTING};
		for(SkillType type : types) {
			if(type.getID().equals(id)) return type;
		}
		
		throw new IllegalArgumentException("ID does not match with any of the skills!");
	}
	
	/*
	 * Get the magnitude of attribute modifier. Used for farming and fishing.
	 */
	private static final Function<Integer, Double> ATTRIBUTE_MODIFIER_AMOUNT_1 = (level) -> {
		if(level <= 14) {
			return Double.valueOf(level * 2);
		} else if(level >= 15 && level <= 19) {
			return Double.valueOf(level * 3 - 14);
		} else if(level >= 20 && level <= 25) {
			return Double.valueOf(level * 4 - 33);
		} else {
			return Double.valueOf(level * 5 - 58);
		}
	};
	
	/*
	 * Get the magnitude of attribute modifier. Used for mining, foraging, enchanting, and alchemy.
	 */
	private static final Function<Integer, Double> ATTRIBUTE_MODIFIER_AMOUNT_2 = (level) -> {
		if(level <= 14) {
			return Double.valueOf(level);
		} else {
			return Double.valueOf(level * 2 - 14);
		}
	};
	
	/*
	 * Get the magnitude of attribute modifier. Used for combat.
	 */
	private static final Function<Integer, Double> ATTRIBUTE_MODIFIER_AMOUNT_COMBAT = (level) -> {
		return Double.valueOf(level * 0.5);
	};
	
	/*
	 * Get the magnitude of attribute modifier. Used for taming. (Purely for semantic reasons lol)
	 */
	private static final Function<Integer, Double> ATTRIBUTE_MODIFIER_AMOUNT_TAMING = (level) -> {
		return Double.valueOf(level);
	};
	
	public double getAttributeModifierAmount(int level) {
		switch(this) {
		case FARMING:
		case FISHING:
			return ATTRIBUTE_MODIFIER_AMOUNT_1.apply(level);
		case MINING:
		case FORAGING:
		case ENCHANTING:
		case ALCHEMY:
			return ATTRIBUTE_MODIFIER_AMOUNT_2.apply(level);
		case COMBAT:
			return ATTRIBUTE_MODIFIER_AMOUNT_COMBAT.apply(level);
		case TAMING:
			return ATTRIBUTE_MODIFIER_AMOUNT_TAMING.apply(level);
		default:
			return 0.0d;
		}
	}
	
	public ITextComponent getDisplayName() {
		return new TranslationTextComponent("skills.forgeblock." + id + ".name");
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
}
