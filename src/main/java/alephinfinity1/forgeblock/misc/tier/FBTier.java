package alephinfinity1.forgeblock.misc.tier;

import alephinfinity1.forgeblock.config.FBModConfig;
import net.minecraft.item.Rarity;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

/**
 * The rarity/tier of an item. <br>
 * Better known as 'Rarity' in-game, however the official terminology, as shown in the API doc, is 'Tier'.
 */
public enum FBTier {
	COMMON(FBModConfig.COMMON_TIER_COLOR.get(), "misc.forgeblock.tier.common"),
	UNCOMMON(FBModConfig.UNCOMMON_TIER_COLOR.get(), "misc.forgeblock.tier.uncommon"),
	RARE(FBModConfig.RARE_TIER_COLOR.get(), "misc.forgeblock.tier.rare"),
	EPIC(FBModConfig.EPIC_TIER_COLOR.get(), "misc.forgeblock.tier.epic"),
	LEGENDARY(FBModConfig.LEGENDARY_TIER_COLOR.get(), "misc.forgeblock.tier.legendary"),
	MYTHIC(FBModConfig.MYTHIC_TIER_COLOR.get(), "misc.forgeblock.tier.mythic"),
	SUPREME(FBModConfig.SUPREME_TIER_COLOR.get(), "misc.forgeblock.tier.supreme"),
	SPECIAL(FBModConfig.SPECIAL_TIER_COLOR.get(), "misc.forgeblock.tier.special"),
	VERY_SPECIAL(FBModConfig.VERY_SPECIAL_TIER_COLOR.get(), "misc.forgeblock.tier.very_special");
	
	public TextFormatting color;
	public TranslationTextComponent name;
	FBTier(TextFormatting color, String key) {
		this.color = color;
		this.name = new TranslationTextComponent(key);
	}
	
	/**
	 * Checks if this rarity is special.
	 * @return whether this rarity is special.
	 */
	public boolean isSpecial() {
		return this == SPECIAL || this == VERY_SPECIAL;
	}
	
	/**
	 * Changes rarity by a number of tiers.
	 * 
	 * @param original The original tier.
	 * @param amount The number of tiers to change.
	 * @return the new tier after the operation.
	 */
	public static FBTier changeTier(FBTier original, int amount) {
		if(original.isSpecial()) {
			return FBTier.valueOf(original.getInt() + amount, true);
		} else {
			return FBTier.valueOf(original.getInt() + amount, false);
		}
	}
	
	/**
	 * Get the int value of this
	 * 
	 * @return
	 */
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
			return Rarity.create("FB_COMMON", FBModConfig.COMMON_TIER_COLOR.get());
		case UNCOMMON:
			return Rarity.create("FB_UNCOMMON", FBModConfig.UNCOMMON_TIER_COLOR.get());
		case RARE:
			return Rarity.create("FB_RARE", FBModConfig.RARE_TIER_COLOR.get());
		case EPIC:
			return Rarity.create("FB_EPIC", FBModConfig.EPIC_TIER_COLOR.get());
		case LEGENDARY:
			return Rarity.create("FB_LEGENDARY", FBModConfig.LEGENDARY_TIER_COLOR.get());
		case MYTHIC:
			return Rarity.create("FB_MYTHIC", FBModConfig.MYTHIC_TIER_COLOR.get());
		case SUPREME:
			return Rarity.create("FB_SUPREME", FBModConfig.SUPREME_TIER_COLOR.get());
		case SPECIAL:
			return Rarity.create("FB_SPECIAL", FBModConfig.SPECIAL_TIER_COLOR.get());
		case VERY_SPECIAL:
			return Rarity.create("FB_VERY_SPECIAL", FBModConfig.VERY_SPECIAL_TIER_COLOR.get());
		default:
			return Rarity.COMMON;
		}
	}
	
	/**
	 * Get the corresponding FBTier of an integer. Used for dungeon gear calculation only.
	 * Not suitable for any other usage.
	 * 
	 * @param i The integer.
	 * @return The corresponding tier.
	 */
	public static FBTier valueOf(int i) {
		return valueOf(i, true);
	}
	
	/**
	 * Get the corresponding FBTier of an integer. Used for dungeon gear calculation only.
	 * Not suitable for any other usage.
	 * 
	 * @param i The integer.
	 * @param allowSpecial Whether 'special' tiers are allowed to be returned.
	 * @return The corresponding tier.
	 */
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
	
	/**
	 * Double version of valueOf(int).
	 * 
	 * @param d The number.
	 * @return The corresponding tier.
	 */
	public static FBTier valueOf(double d) {
		return valueOf(d, true);
	}
	
	/**
	 * Double version of valueOf(int, boolean).
	 * 
	 * @param d The number.
	 * @return The corresponding tier.
	 */
	public static FBTier valueOf(double d, boolean allowSpecial) {
		return valueOf((int) Math.round(d), allowSpecial);
	}
	
}
