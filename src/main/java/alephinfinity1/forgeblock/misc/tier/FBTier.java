package alephinfinity1.forgeblock.misc.tier;

import alephinfinity1.forgeblock.config.CustomModConfig;
import net.minecraft.item.Rarity;
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
	SUPREME(CustomModConfig.SUPREME_TIER_COLOR.get(), "misc.forgeblock.tier.supreme"),
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
	public static FBTier changeTier(FBTier original, int amount) {
		switch(amount) {
		case -5:
			switch(original) {
			case COMMON:
			case UNCOMMON:
			case RARE:
			case EPIC:
			case LEGENDARY:
			case MYTHIC:
				return COMMON;
			case SUPREME:
				return UNCOMMON;
			case SPECIAL:
			case VERY_SPECIAL:
				return SPECIAL;
			}
			break;
		case -4:
			switch(original) {
			case COMMON:
			case UNCOMMON:
			case RARE:
			case EPIC:
			case LEGENDARY:
				return COMMON;
			case MYTHIC:
				return UNCOMMON;
			case SUPREME:
				return RARE;
			case SPECIAL:
			case VERY_SPECIAL:
				return SPECIAL;
			}
			break;
		case -3:
			switch(original) {
			case COMMON:
			case UNCOMMON:
			case RARE:
			case EPIC:
				return COMMON;
			case LEGENDARY:
				return UNCOMMON;
			case MYTHIC:
				return RARE;
			case SUPREME:
				return EPIC;
			case SPECIAL:
			case VERY_SPECIAL:
				return SPECIAL;
			}
			break;
		case -2:
			switch(original) {
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
			case SUPREME:
				return LEGENDARY;
			case SPECIAL:
			case VERY_SPECIAL:
				return SPECIAL;
			}
			break;
		case -1:
			switch(original) {
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
			case SUPREME:
				return MYTHIC;
			case SPECIAL:
			case VERY_SPECIAL:
				return SPECIAL;
			}
			break;
		case 0:
			return original;
		case 1:
			switch(original) {
			case COMMON:
				return UNCOMMON;
			case UNCOMMON:
				return RARE;
			case RARE:
				return EPIC;
			case EPIC:
				return LEGENDARY;
			case LEGENDARY:
				return MYTHIC;
			case MYTHIC:
			case SUPREME:
				return SUPREME;
			case SPECIAL:
			case VERY_SPECIAL:
				return VERY_SPECIAL;
			}
			break;
		case 2:
			switch(original) {
			case COMMON:
				return RARE;
			case UNCOMMON:
				return EPIC;
			case RARE:
				return LEGENDARY;
			case EPIC:
				return MYTHIC;
			case LEGENDARY:
			case MYTHIC:
			case SUPREME:
				return SUPREME;
			case SPECIAL:
			case VERY_SPECIAL:
				return VERY_SPECIAL;
			}
			break;
		case 3:
			switch(original) {
			case COMMON:
				return EPIC;
			case UNCOMMON:
				return LEGENDARY;
			case RARE:
				return MYTHIC;
			case EPIC:
			case LEGENDARY:
			case MYTHIC:
			case SUPREME:
				return SUPREME;
			case SPECIAL:
			case VERY_SPECIAL:
				return VERY_SPECIAL;
			}
			break;
		case 4:
			switch(original) {
			case COMMON:
				return LEGENDARY;
			case UNCOMMON:
				return MYTHIC;
			case RARE:
			case EPIC:
			case LEGENDARY:
			case MYTHIC:
			case SUPREME:
				return SUPREME;
			case SPECIAL:
			case VERY_SPECIAL:
				return VERY_SPECIAL;
			}
			break;
		case 5:
			switch(original) {
			case COMMON:
				return MYTHIC;
			case UNCOMMON:
			case RARE:
			case EPIC:
			case LEGENDARY:
			case MYTHIC:
			case SUPREME:
				return SUPREME;
			case SPECIAL:
			case VERY_SPECIAL:
				return VERY_SPECIAL;
			}
			break;
		default:
			if(amount < -5) {
				if(original.isSpecial()) {
					return SPECIAL;
				} else {
					return COMMON;
				}
			} else if (amount > 5) {
				if(original.isSpecial()) {
					return VERY_SPECIAL;
				} else {
					return SUPREME;
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
		case SUPREME:
			return 6;
		case SPECIAL:
			return 32000;
		case VERY_SPECIAL:
			return 32001;
		default:
			return -1;
		}
	}
	
	public Rarity getVanillaRarity() {
		switch(this) {
		case COMMON:
			return Rarity.create("FB_COMMON", CustomModConfig.COMMON_TIER_COLOR.get());
		case UNCOMMON:
			return Rarity.create("FB_UNCOMMON", CustomModConfig.UNCOMMON_TIER_COLOR.get());
		case RARE:
			return Rarity.create("FB_RARE", CustomModConfig.RARE_TIER_COLOR.get());
		case EPIC:
			return Rarity.create("FB_EPIC", CustomModConfig.EPIC_TIER_COLOR.get());
		case LEGENDARY:
			return Rarity.create("FB_LEGENDARY", CustomModConfig.LEGENDARY_TIER_COLOR.get());
		case MYTHIC:
			return Rarity.create("FB_MYTHIC", CustomModConfig.MYTHIC_TIER_COLOR.get());
		case SUPREME:
			return Rarity.create("FB_SUPREME", CustomModConfig.SUPREME_TIER_COLOR.get());
		case SPECIAL:
			return Rarity.create("FB_SPECIAL", CustomModConfig.SPECIAL_TIER_COLOR.get());
		case VERY_SPECIAL:
			return Rarity.create("FB_VERY_SPECIAL", CustomModConfig.VERY_SPECIAL_TIER_COLOR.get());
		default:
			return Rarity.COMMON;
		}
	}
}
