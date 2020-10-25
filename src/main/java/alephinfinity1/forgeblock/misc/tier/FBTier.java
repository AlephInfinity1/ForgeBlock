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
		if(original.isSpecial()) {
			return FBTier.valueOf(original.getInt() + amount, true);
		} else {
			return FBTier.valueOf(original.getInt() + amount, false);
		}
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
			return 7;
		case VERY_SPECIAL:
			return 8;
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
	
	public static FBTier valueOf(int i) {
		return valueOf(i, true);
	}
	
	public static FBTier valueOf(int i, boolean allowSpecial) {
		switch(i) {
		case 0:
			return COMMON;
		case 1:
			return UNCOMMON;
		case 2:
			return RARE;
		case 3:
			return EPIC;
		case 4:
			return LEGENDARY;
		case 5:
			return MYTHIC;
		case 6:
			return SUPREME;
		case 7:
			return allowSpecial ? SPECIAL : SUPREME;
		case 8:
			return allowSpecial ? VERY_SPECIAL : SUPREME;
		default:
			if(i < 0) return COMMON;
			else return allowSpecial ? VERY_SPECIAL: SUPREME;
		}
	}
	
	public static FBTier valueOf(double d) {
		return valueOf(d, true);
	}
	
	public static FBTier valueOf(double d, boolean allowSpecial) {
		return valueOf((int) Math.round(d), allowSpecial);
	}
}
