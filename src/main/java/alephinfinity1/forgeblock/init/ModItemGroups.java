package alephinfinity1.forgeblock.init;

import alephinfinity1.forgeblock.itemgroup.FBArmorItemGroup;
import alephinfinity1.forgeblock.itemgroup.FBCustomItemGroup;
import alephinfinity1.forgeblock.itemgroup.FBHarvestingToolsItemGroup;
import alephinfinity1.forgeblock.itemgroup.FBMaterialsItemGroup;
import alephinfinity1.forgeblock.itemgroup.FBSwordsItemGroup;
import net.minecraft.item.ItemGroup;

public class ModItemGroups {

	public static final ItemGroup FB_MATERIALS = new FBMaterialsItemGroup("forgeblock_materials");
	public static final ItemGroup FB_SWORDS = new FBSwordsItemGroup("forgeblock_swords");
	public static final ItemGroup FB_HARVESTING_TOOLS = new FBHarvestingToolsItemGroup("forgeblock_harvesting_tools");
	public static final ItemGroup FB_ARMOR = new FBArmorItemGroup("forgeblock_armor");
	public static final ItemGroup FB_POTIONS = new FBPotionsItemGroup("forgeblock_potions");
	public static final ItemGroup FB_CUSTOM = new FBCustomItemGroup("forgeblock_custom");

}
