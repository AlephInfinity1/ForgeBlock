package alephinfinity1.forgeblock.misc.tier;

import alephinfinity1.forgeblock.config.CustomModConfig;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

/*
 * The rarity/tier of an item.
 */
public enum FBTier {
	COMMON(CustomModConfig.COMMON_TIER_COLOR.get(), "misc.forgeblock.tier.common"),
	UNCOMMON(CustomModConfig.UNCOMMON_TIER_COLOR.get(), "misc.forgeblock.tier.uncommon"),
	RARE(CustomModConfig.RARE_TIER_COLOR.get(), "misc.forgeblock.tier.rare"),
	EPIC(CustomModConfig.EPIC_TIER_COLOR.get(), "misc.forgeblock.tier.epic"),
	LEGENDARY(CustomModConfig.LEGENDARY_TIER_COLOR.get(), "misc.forgeblock.tier.legendary"),
	MYTHIC(CustomModConfig.MYTHIC_TIER_COLOR.get(), "misc.forgeblock.tier.mythic"),
	SPECIAL(CustomModConfig.SPECIAL_TIER_COLOR.get(), "misc.forgeblock.tier.special"),
	VERY_SPECIAL(CustomModConfig.VERY_SPECIAL_TIER_COLOR.get(), "misc.forgeblock.tier.very_special");
	
	public TextFormatting color;
	public TranslationTextComponent name;
	private FBTier(TextFormatting color, String key) {
		this.color = color;
		this.name = new TranslationTextComponent(key);
	}
	
	/*
	 * Checks if this rarity is special.
	 */
	public boolean isSpecial() {
		if(this == SPECIAL || this == VERY_SPECIAL) {
			return true;
		} else return false;
	}
	
	/*
	 * Changes rarity by a number of tiers.
	 */
	public static FBTier changeTier(FBTier tier, int num) {
		switch(num) {
		case -5:
			switch(tier) {
			case COMMON:
			case UNCOMMON:
			case RARE:
			case EPIC:
			case LEGENDARY:
			case MYTHIC:
				return COMMON;
			case SPECIAL:
			case VERY_SPECIAL:
				return SPECIAL;
			}
			break;
		case -4:
			switch(tier) {
			case COMMON:
			case UNCOMMON:
			case RARE:
			case EPIC:
			case LEGENDARY:
				return COMMON;
			case MYTHIC:
				return UNCOMMON;
			case SPECIAL:
			case VERY_SPECIAL:
				return SPECIAL;
			}
			break;
		case -3:
			switch(tier) {
			case COMMON:
			case UNCOMMON:
			case RARE:
			case EPIC:
				return COMMON;
			case LEGENDARY:
				return UNCOMMON;
			case MYTHIC:
				return RARE;
			case SPECIAL:
			case VERY_SPECIAL:
				return SPECIAL;
			}
			break;
		case -2:
			switch(tier) {
			case COMMON:
			case UNCOMMON:
			case RARE:
				return COMMON;
			case EPIC:
				return UNCOMMON;
			case LEGENDARY:
				return RARE;
			case MYTHIC:
				return EPIC;
			case SPECIAL:
			case VERY_SPECIAL:
				return SPECIAL;
			}
			break;
		case -1:
			switch(tier) {
			case COMMON:
			case UNCOMMON:
				return COMMON;
			case RARE:
				return UNCOMMON;
			case EPIC:
				return RARE;
			case LEGENDARY:
				return EPIC;
			case MYTHIC:
				return LEGENDARY;
			case SPECIAL:
			case VERY_SPECIAL:
				return SPECIAL;
			}
			break;
		case 0:
			return tier;
		case 1:
			switch(tier) {
			case COMMON:
				return UNCOMMON;
			case UNCOMMON:
				return RARE;
			case RARE:
				return EPIC;
			case EPIC:
				return LEGENDARY;
			case LEGENDARY:
			case MYTHIC:
				return MYTHIC;
			case SPECIAL:
			case VERY_SPECIAL:
				return VERY_SPECIAL;
			}
			break;
		case 2:
			switch(tier) {
			case COMMON:
				return RARE;
			case UNCOMMON:
				return EPIC;
			case RARE:
				return LEGENDARY;
			case EPIC:
			case LEGENDARY:
			case MYTHIC:
				return MYTHIC;
			case SPECIAL:
			case VERY_SPECIAL:
				return VERY_SPECIAL;
			}
			break;
		case 3:
			switch(tier) {
			case COMMON:
				return EPIC;
			case UNCOMMON:
				return LEGENDARY;
			case RARE:
			case EPIC:
			case LEGENDARY:
			case MYTHIC:
				return MYTHIC;
			case SPECIAL:
			case VERY_SPECIAL:
				return VERY_SPECIAL;
			}
			break;
		case 4:
			switch(tier) {
			case COMMON:
				return LEGENDARY;
			case UNCOMMON:
			case RARE:
			case EPIC:
			case LEGENDARY:
			case MYTHIC:
				return MYTHIC;
			case SPECIAL:
			case VERY_SPECIAL:
				return VERY_SPECIAL;
			}
			break;
		case 5:
			switch(tier) {
			case COMMON:
			case UNCOMMON:
			case RARE:
			case EPIC:
			case LEGENDARY:
			case MYTHIC:
				return MYTHIC;
			case SPECIAL:
			case VERY_SPECIAL:
				return VERY_SPECIAL;
			}
			break;
		default:
			if(num < -5) {
				if(tier.isSpecial()) {
					return SPECIAL;
				} else {
					return COMMON;
				}
			} else if (num > 5) {
				if(tier.isSpecial()) {
					return VERY_SPECIAL;
				} else {
					return MYTHIC;
				}
			}
			break;
		}
		return null;
	}
	
	public int getInt() {
		switch(this) {
		case COMMON:
			return 0;
		case UNCOMMON:
			return 1;
		case RARE:
			return 2;
		case EPIC:
			return 3;
		case LEGENDARY:
			return 4;
		case MYTHIC:
			return 5;
		case SPECIAL:
			return 32000;
		case VERY_SPECIAL:
			return 32001;
		default:
			return -1;
		}
	}
}
