package alephinfinity1.forgeblock.misc;

import net.minecraft.util.text.TranslationTextComponent;

public enum FBItemType {
	GENERAL("misc.forgeblock.itemtype.general"), //Any item that does not below in any categories below.
	SWORD("misc.forgeblock.itemtype.sword"),
	BOW("misc.forgeblock.itemtype.bow"),
	PICKAXE("misc.forgeblock.itemtype.pickaxe"),
	AXE("misc.forgeblock.itemtype.axe"),
	SHOVEL("misc.forgeblock.itemtype.shovel"),
	HOE("misc.forgeblock.itemtype.hoe"),
	SHEARS("misc.forgeblock.itemtype.shears"),
	FLINT_AND_STEEL("misc.forgeblock.itemtype.flint_and_steel"),
	ARROW_POISON("misc.forgeblock.itemtype.arrow_poison"),
	HELMET("misc.forgeblock.itemtype.helmet"),
	CHESTPLATE("misc.forgeblock.itemtype.chestplate"),
	LEGGINGS("misc.forgeblock.itemtype.leggings"),
	BOOTS("misc.forgeblock.itemtype.boots"),
	ACCESSORY("misc.forgeblock.itemtype.accessory"),
	RING("misc.forgeblock.itemtype.ring"),
	POTION("misc.forgeblock.itemtype.potion"),
	ENCHANTED_BOOK("misc.forgeblock.itemtype.enchanted_book");
	
	String unlocalizedName;
	
	private FBItemType(String unlocalizedName) {
		this.unlocalizedName = unlocalizedName;
	}
	
	public String getDisplayName() {
		return (new TranslationTextComponent(unlocalizedName)).getString();
	}
}
