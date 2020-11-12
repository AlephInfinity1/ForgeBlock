package alephinfinity1.forgeblock.init;

import alephinfinity1.forgeblock.itemgroup.FBArmorItemGroup;
import alephinfinity1.forgeblock.itemgroup.FBCustomItemGroup;
import alephinfinity1.forgeblock.itemgroup.FBHarvestingToolsItemGroup;
import alephinfinity1.forgeblock.itemgroup.FBMaterialsItemGroup;
import alephinfinity1.forgeblock.itemgroup.FBMiscItemGroup;
import alephinfinity1.forgeblock.itemgroup.FBMobsItemGroup;
import alephinfinity1.forgeblock.itemgroup.FBSwordsItemGroup;
import net.minecraft.item.ItemGroup;

public class ModItemGroups {

	public static final ItemGroup FB_MATERIALS = new FBMaterialsItemGroup(12, "forgeblock_materials");
	public static final ItemGroup FB_SWORDS = new FBSwordsItemGroup(13, "forgeblock_swords");
	public static final ItemGroup FB_HARVESTING_TOOLS = new FBHarvestingToolsItemGroup(14, "forgeblock_harvesting_tools");
	public static final ItemGroup FB_ARMOR = new FBArmorItemGroup(15, "forgeblock_armor");
	public static final ItemGroup FB_POTIONS = new FBPotionsItemGroup(16, "forgeblock_potions");	
	public static final ItemGroup FB_MOBS = new FBMobsItemGroup(17, "forgeblock_mobs");
	public static final ItemGroup FB_MISC = new FBMiscItemGroup(18, "forgeblock_misc");
	public static final ItemGroup FB_CUSTOM = new FBCustomItemGroup(19, "forgeblock_custom");

}
