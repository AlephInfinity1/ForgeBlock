package alephinfinity1.forgeblock.init;

import alephinfinity1.forgeblock.itemgroup.*;
import net.minecraft.item.ItemGroup;

public class ModItemGroups {

	public static final ItemGroup FB_MATERIALS = new FBMaterialsItemGroup(12, "forgeblock_materials");
	public static final ItemGroup FB_SWORDS = new FBSwordsItemGroup(13, "forgeblock_swords");
	public static final ItemGroup FB_BOWS = new FBBowsItemGroup(14, "forgeblock_bows");
	public static final ItemGroup FB_HARVESTING_TOOLS = new FBHarvestingToolsItemGroup(15, "forgeblock_harvesting_tools");
	public static final ItemGroup FB_ARMOR = new FBArmorItemGroup(16, "forgeblock_armor");
	public static final ItemGroup FB_POTIONS = new FBPotionsItemGroup(17, "forgeblock_potions");
	public static final ItemGroup FB_MOBS = new FBMobsItemGroup(18, "forgeblock_mobs");
	public static final ItemGroup FB_MISC = new FBMiscItemGroup(19, "forgeblock_misc");
	public static final ItemGroup FB_CUSTOM = new FBCustomItemGroup(20, "forgeblock_custom");

}
