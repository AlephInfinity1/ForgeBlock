package alephinfinity1.forgeblock.init;

import alephinfinity1.forgeblock.ForgeBlock;
import alephinfinity1.forgeblock.attribute.ModifierHelper;
import alephinfinity1.forgeblock.item.*;
import alephinfinity1.forgeblock.item.armor.*;
import alephinfinity1.forgeblock.item.bows.FBBowItem;
import alephinfinity1.forgeblock.item.bows.HurricaneBowItem;
import alephinfinity1.forgeblock.item.bows.RunaansBowItem;
import alephinfinity1.forgeblock.item.pickaxes.FBPickaxeItem;
import alephinfinity1.forgeblock.item.pickaxes.StonkItem;
import alephinfinity1.forgeblock.item.swords.*;
import alephinfinity1.forgeblock.misc.tier.FBTier;
import net.minecraft.block.Blocks;
import net.minecraft.entity.item.minecart.AbstractMinecartEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.MinecartItem;
import net.minecraft.item.SpawnEggItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber
@SuppressWarnings("unused")
public class ModItems {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ForgeBlock.MOD_ID);

	public static final DeferredRegister<Item> OVERRIDE = DeferredRegister.create(ForgeRegistries.ITEMS, ForgeBlock.MINECRAFT_ID);

	/*
	 * Enchanted items
	 */

	//Mining enchanted
	public static final RegistryObject<Item> ENCHANTED_COBBLESTONE = ITEMS.register("enchanted_cobblestone", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.UNCOMMON));
	public static final RegistryObject<Item> ENCHANTED_IRON = ITEMS.register("enchanted_iron", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.UNCOMMON));
	public static final RegistryObject<Item> ENCHANTED_GOLD = ITEMS.register("enchanted_gold", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.UNCOMMON));
	public static final RegistryObject<Item> ENCHANTED_COAL = ITEMS.register("enchanted_coal", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.UNCOMMON));
	public static final RegistryObject<Item> ENCHANTED_REDSTONE = ITEMS.register("enchanted_redstone", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.UNCOMMON));
	public static final RegistryObject<Item> ENCHANTED_LAPIS_LAZULI = ITEMS.register("enchanted_lapis_lazuli", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.UNCOMMON));
	public static final RegistryObject<Item> ENCHANTED_DIAMOND = ITEMS.register("enchanted_diamond", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.UNCOMMON));
	public static final RegistryObject<Item> ENCHANTED_EMERALD = ITEMS.register("enchanted_emerald", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.UNCOMMON));
	public static final RegistryObject<Item> ENCHANTED_QUARTZ = ITEMS.register("enchanted_quartz", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.UNCOMMON));
	public static final RegistryObject<Item> ENCHANTED_OBSIDIAN = ITEMS.register("enchanted_obsidian", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.UNCOMMON));
	public static final RegistryObject<Item> ENCHANTED_ICE = ITEMS.register("enchanted_ice", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.UNCOMMON));
	public static final RegistryObject<Item> ENCHANTED_GLOWSTONE_DUST = ITEMS.register("enchanted_glowstone_dust", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.UNCOMMON));
	public static final RegistryObject<Item> ENCHANTED_END_STONE = ITEMS.register("enchanted_end_stone", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.UNCOMMON));

	//Mining enchanted II
	public static final RegistryObject<Item> ENCHANTED_STONE = ITEMS.register("enchanted_stone", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.RARE));
	public static final RegistryObject<Item> ENCHANTED_IRON_BLOCK = ITEMS.register("enchanted_iron_block", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.RARE));
	public static final RegistryObject<Item> ENCHANTED_GOLD_BLOCK = ITEMS.register("enchanted_gold_block", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.RARE));
	public static final RegistryObject<Item> ENCHANTED_COAL_BLOCK = ITEMS.register("enchanted_coal_block", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.RARE));
	public static final RegistryObject<Item> ENCHANTED_REDSTONE_BLOCK = ITEMS.register("enchanted_redstone_block", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.RARE));
	public static final RegistryObject<Item> ENCHANTED_LAPIS_BLOCK = ITEMS.register("enchanted_lapis_block", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.RARE));
	public static final RegistryObject<Item> ENCHANTED_DIAMOND_BLOCK = ITEMS.register("enchanted_diamond_block", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.RARE));
	public static final RegistryObject<Item> ENCHANTED_EMERALD_BLOCK = ITEMS.register("enchanted_emerald_block", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.RARE));
	public static final RegistryObject<Item> ENCHANTED_QUARTZ_BLOCK = ITEMS.register("enchanted_quartz_block", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.RARE));
	public static final RegistryObject<Item> ENCHANTED_PACKED_ICE = ITEMS.register("enchanted_packed_ice", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.RARE));
	public static final RegistryObject<Item> ENCHANTED_GLOWSTONE = ITEMS.register("enchanted_glowstone", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.UNCOMMON));

	//Combat enchanted
	public static final RegistryObject<Item> ENCHANTED_ROTTEN_FLESH = ITEMS.register("enchanted_rotten_flesh", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.COMMON));
	public static final RegistryObject<Item> ENCHANTED_BONE = ITEMS.register("enchanted_bone", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.COMMON));
	public static final RegistryObject<Item> ENCHANTED_STRING = ITEMS.register("enchanted_string", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.UNCOMMON));
	public static final RegistryObject<Item> ENCHANTED_SPIDER_EYE = ITEMS.register("enchanted_spider_eye", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.UNCOMMON));
	public static final RegistryObject<Item> ENCHANTED_GUNPOWDER = ITEMS.register("enchanted_gunpowder", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.UNCOMMON));
	public static final RegistryObject<Item> ENCHANTED_ENDER_PEARL = ITEMS.register("enchanted_ender_pearl", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS).maxStackSize(16), FBTier.UNCOMMON));
	public static final RegistryObject<Item> ENCHANTED_GHAST_TEAR = ITEMS.register("enchanted_ghast_tear", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.UNCOMMON));
	public static final RegistryObject<Item> ENCHANTED_SLIME_BALL = ITEMS.register("enchanted_slime_ball", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.UNCOMMON));
	public static final RegistryObject<Item> ENCHANTED_BLAZE_POWDER = ITEMS.register("enchanted_blaze_powder", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.UNCOMMON));
	public static final RegistryObject<Item> ENCHANTED_MAGMA_CREAM = ITEMS.register("enchanted_magma_cream", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.UNCOMMON));

	//Combat enchanted II
	public static final RegistryObject<Item> ENCHANTED_SLIME_BLOCK = ITEMS.register("enchanted_slime_block", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.RARE));
	public static final RegistryObject<Item> ENCHANTED_BLAZE_ROD = ITEMS.register("enchanted_blaze_rod", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.UNCOMMON));
	public static final RegistryObject<Item> ENCHANTED_ENDER_EYE = ITEMS.register("enchanted_ender_eye", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.UNCOMMON));

	//Foraging enchanted
	public static final RegistryObject<Item> ENCHANTED_OAK_LOG = ITEMS.register("enchanted_oak_log", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.UNCOMMON));
	public static final RegistryObject<Item> ENCHANTED_SPRUCE_LOG = ITEMS.register("enchanted_spruce_log", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.UNCOMMON));
	public static final RegistryObject<Item> ENCHANTED_BIRCH_LOG = ITEMS.register("enchanted_birch_log", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.UNCOMMON));
	public static final RegistryObject<Item> ENCHANTED_JUNGLE_LOG = ITEMS.register("enchanted_jungle_log", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.UNCOMMON));
	public static final RegistryObject<Item> ENCHANTED_ACACIA_LOG = ITEMS.register("enchanted_acacia_log", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.UNCOMMON));
	public static final RegistryObject<Item> ENCHANTED_DARK_OAK_LOG = ITEMS.register("enchanted_dark_oak_log", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.UNCOMMON));

	//Farming enchanted
	public static final RegistryObject<Item> ENCHANTED_BREAD = ITEMS.register("enchanted_bread", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.COMMON));
	public static final RegistryObject<Item> ENCHANTED_CARROT = ITEMS.register("enchanted_carrot", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.UNCOMMON));
	public static final RegistryObject<Item> ENCHANTED_POTATO = ITEMS.register("enchanted_potato", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.UNCOMMON));
	public static final RegistryObject<Item> ENCHANTED_PUMPKIN = ITEMS.register("enchanted_pumpkin", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.UNCOMMON));
	public static final RegistryObject<Item> ENCHANTED_MELON = ITEMS.register("enchanted_melon", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.UNCOMMON));
	public static final RegistryObject<Item> ENCHANTED_BROWN_MUSHROOM = ITEMS.register("enchanted_brown_mushroom", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.COMMON));
	public static final RegistryObject<Item> ENCHANTED_RED_MUSHROOM = ITEMS.register("enchanted_red_mushroom", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.COMMON));
	public static final RegistryObject<Item> ENCHANTED_COCOA_BEANS = ITEMS.register("enchanted_cocoa_beans", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.UNCOMMON));
	public static final RegistryObject<Item> ENCHANTED_CACTUS_GREEN = ITEMS.register("enchanted_cactus_green", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.UNCOMMON));
	public static final RegistryObject<Item> ENCHANTED_SUGAR = ITEMS.register("enchanted_sugar", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.UNCOMMON));
	public static final RegistryObject<Item> ENCHANTED_PAPER = ITEMS.register("enchanted_paper", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.UNCOMMON));
	public static final RegistryObject<Item> ENCHANTED_FEATHER = ITEMS.register("enchanted_feather", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.UNCOMMON));
	public static final RegistryObject<Item> ENCHANTED_BEEF = ITEMS.register("enchanted_beef", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.UNCOMMON));
	public static final RegistryObject<Item> ENCHANTED_PORKCHOP = ITEMS.register("enchanted_porkchop", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.UNCOMMON));
	public static final RegistryObject<Item> ENCHANTED_CHICKEN = ITEMS.register("enchanted_chicken", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.UNCOMMON));
	public static final RegistryObject<Item> ENCHANTED_EGG = ITEMS.register("enchanted_egg", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS).maxStackSize(16), FBTier.RARE));
	public static final RegistryObject<Item> ENCHANTED_CAKE = ITEMS.register("enchanted_cake", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.UNCOMMON));
	public static final RegistryObject<Item> ENCHANTED_MUTTON = ITEMS.register("enchanted_mutton", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.UNCOMMON));
	public static final RegistryObject<Item> ENCHANTED_RABBIT_FOOT = ITEMS.register("enchanted_rabbit_foot", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.RARE));
	public static final RegistryObject<Item> ENCHANTED_RABBIT_HIDE = ITEMS.register("enchanted_rabbit_hide", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.RARE));
	public static final RegistryObject<Item> ENCHANTED_NETHER_WART = ITEMS.register("enchanted_nether_wart", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.UNCOMMON));

	//Farming enchanted II
	public static final RegistryObject<Item> ENCHANTED_HAY_BALE = ITEMS.register("enchanted_hay_bale", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.UNCOMMON));
	public static final RegistryObject<Item> ENCHANTED_GOLDEN_CARROT = ITEMS.register("enchanted_golden_carrot", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.UNCOMMON));
	public static final RegistryObject<Item> ENCHANTED_BAKED_POTATO = ITEMS.register("enchanted_baked_potato", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.UNCOMMON));
	public static final RegistryObject<Item> ENCHANTED_JACK_O_LANTERN = ITEMS.register("enchanted_jack_o_lantern", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.RARE));
	public static final RegistryObject<Item> ENCHANTED_GLISTERING_MELON = ITEMS.register("enchanted_glistering_melon", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.RARE));
	public static final RegistryObject<Item> ENCHANTED_MELON_BLOCK = ITEMS.register("enchanted_melon_block", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.RARE));
	public static final RegistryObject<Item> ENCHANTED_BROWN_MUSHROOM_BLOCK = ITEMS.register("enchanted_brown_mushroom_block", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.COMMON));
	public static final RegistryObject<Item> ENCHANTED_RED_MUSHROOM_BLOCK = ITEMS.register("enchanted_red_mushroom_block", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.COMMON));
	public static final RegistryObject<Item> ENCHANTED_COOKIE = ITEMS.register("enchanted_cookie", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.RARE));
	public static final RegistryObject<Item> ENCHANTED_CACTUS = ITEMS.register("enchanted_cactus", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.RARE));
	public static final RegistryObject<Item> ENCHANTED_SUGAR_CANE = ITEMS.register("enchanted_sugar_cane", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.RARE));
	public static final RegistryObject<Item> ENCHANTED_LEATHER = ITEMS.register("enchanted_leather", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.UNCOMMON));
	public static final RegistryObject<Item> ENCHANTED_GRILLED_PORKCHOP = ITEMS.register("enchanted_grilled_porkchop", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.RARE));
	public static final RegistryObject<Item> SUPER_ENCHANTED_EGG = ITEMS.register("super_enchanted_egg", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.RARE));
	public static final RegistryObject<Item> ENCHANTED_COOKED_MUTTON = ITEMS.register("enchanted_cooked_mutton", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.RARE));

	/*
	 * Swords
	 */
	public static final RegistryObject<Item> WOODEN_SWORD = OVERRIDE.register("wooden_sword", () -> new WoodSingularityApplicableSwordItem(new Item.Properties().group(ModItemGroups.FB_SWORDS), FBTier.COMMON, 20, 0, 0, 0));
	public static final RegistryObject<Item> GOLDEN_SWORD = OVERRIDE.register("golden_sword", () -> new FBSwordItem(new Item.Properties().group(ModItemGroups.FB_SWORDS), FBTier.COMMON, 20, 0, 0, 0));
	public static final RegistryObject<Item> STONE_SWORD = OVERRIDE.register("stone_sword", () -> new FBSwordItem(new Item.Properties().group(ModItemGroups.FB_SWORDS), FBTier.COMMON, 25, 0, 0, 0));
	public static final RegistryObject<Item> IRON_SWORD = OVERRIDE.register("iron_sword", () -> new FBSwordItem(new Item.Properties().group(ModItemGroups.FB_SWORDS), FBTier.COMMON, 30, 0, 0, 0));
	public static final RegistryObject<Item> DIAMOND_SWORD = OVERRIDE.register("diamond_sword", () -> new FBSwordItem(new Item.Properties().group(ModItemGroups.FB_SWORDS), FBTier.UNCOMMON, 35, 0, 0, 0));

	public static final RegistryObject<Item> ASPECT_OF_THE_END = ITEMS.register("aspect_of_the_end", () -> new AspectOfTheEndItem(new Item.Properties().group(ModItemGroups.FB_SWORDS), FBTier.RARE, 100, 100, 0, 0));
	public static final RegistryObject<Item> SILVER_FANG = ITEMS.register("silver_fang", () -> new FBSwordItem(new Item.Properties().group(ModItemGroups.FB_SWORDS), FBTier.UNCOMMON, 100, 0, 0, 0));
	public static final RegistryObject<Item> LEAPING_SWORD = ITEMS.register("leaping_sword", () -> new FBSwordItem(new Item.Properties().group(ModItemGroups.FB_SWORDS), FBTier.EPIC, 150, 100, 0, 25));
	public static final RegistryObject<Item> ASPECT_OF_THE_DRAGONS = ITEMS.register("aspect_of_the_dragons", () -> new AspectOfTheDragonsItem(new Item.Properties().group(ModItemGroups.FB_SWORDS), FBTier.LEGENDARY, 225, 100, 0, 0));
	public static final RegistryObject<Item> SWORD_OF_THE_STARS = ITEMS.register("sword_of_the_stars", () -> new SwordOfTheStarsItem(new Item.Properties().group(ModItemGroups.FB_SWORDS), FBTier.SPECIAL, 99999, 0, 0, 0));
	public static final RegistryObject<Item> ROGUE_SWORD = ITEMS.register("rogue_sword", () -> new RogueSwordItem(new Item.Properties().group(ModItemGroups.FB_SWORDS), FBTier.COMMON, 20, 0, 0, 0));
	public static final RegistryObject<Item> UNDEAD_SWORD = ITEMS.register("undead_sword", () -> new UndeadSwordItem(new Item.Properties().group(ModItemGroups.FB_SWORDS), FBTier.COMMON, 30, 0, 0, 0));
	public static final RegistryObject<Item> SPIDER_SWORD = ITEMS.register("spider_sword", () -> new SpiderSwordItem(new Item.Properties().group(ModItemGroups.FB_SWORDS), FBTier.COMMON, 30, 0, 0, 0));
	public static final RegistryObject<Item> END_SWORD = ITEMS.register("end_sword", () -> new EndSwordItem(new Item.Properties().group(ModItemGroups.FB_SWORDS), FBTier.UNCOMMON, 35, 0, 0, 0));
	public static final RegistryObject<Item> FANCY_SWORD = ITEMS.register("fancy_sword", () -> new FancySwordItem(new Item.Properties().group(ModItemGroups.FB_SWORDS), FBTier.COMMON, 20, 0, 0, 0));
	public static final RegistryObject<Item> SHADOW_FURY = ITEMS.register("shadow_fury", () -> new ShadowFuryItem(new Item.Properties().group(ModItemGroups.FB_SWORDS), FBTier.LEGENDARY, ModifierHelper.modifierMapFromDoubles(300, 125, 0, 0, 0, 0, 0, 0, 30, 0, 0, 0, 0, 0, "Shadow Fury Modifier", ShadowFuryItem.SHADOW_FURY_MODIFIER)));
	public static final RegistryObject<Item> SILENT_DEATH = ITEMS.register("silent_death", () -> new SilentDeathItem(new Item.Properties().group(ModItemGroups.FB_SWORDS), FBTier.RARE, 130, 50, 1, 10));

	public static final RegistryObject<Item> ASPECT_OF_THE_ENDER = ITEMS.register("aspect_of_the_ender", () -> new AspectOfTheEnderItem(new Item.Properties(), FBTier.EPIC, 175, 100, 0, 0));
	public static final RegistryObject<Item> CUSTOM_SWORD = ITEMS.register("custom_sword", CustomSwordItem::new);

	/*
	 * Bows
	 */
	public static final RegistryObject<Item> BOW = OVERRIDE.register("bow", () -> new FBBowItem(new Item.Properties().maxStackSize(1).group(ModItemGroups.FB_BOWS), FBTier.COMMON, 30, 0, 0, 0));
	public static final RegistryObject<Item> DECENT_BOW = ITEMS.register("decent_bow", () -> new FBBowItem(new Item.Properties().maxStackSize(1).group(ModItemGroups.FB_BOWS), FBTier.UNCOMMON, 45, 0, 0, 0));
	public static final RegistryObject<Item> SAVANNA_BOW = ITEMS.register("savanna_bow", () -> new FBBowItem(new Item.Properties().maxStackSize(1).group(ModItemGroups.FB_BOWS), FBTier.UNCOMMON, 50, 0, 0, 0, true));
	public static final RegistryObject<Item> HURRICANE_BOW = ITEMS.register("hurricane_bow", () -> new HurricaneBowItem(new Item.Properties().maxStackSize(1).group(ModItemGroups.FB_BOWS), FBTier.EPIC, 120, 50, 0, 0));
	public static final RegistryObject<Item> RUNAANS_BOW = ITEMS.register("runaans_bow", () -> new RunaansBowItem(new Item.Properties().maxStackSize(1).group(ModItemGroups.FB_BOWS), FBTier.LEGENDARY, 160, 50, 0, 0));
	/*
	 * Pickaxes
	 */
	public static final RegistryObject<Item> WOODEN_PICKAXE = OVERRIDE.register("wooden_pickaxe", () -> new FBPickaxeItem(new Item.Properties().group(ModItemGroups.FB_HARVESTING_TOOLS), FBTier.COMMON, 15, 1, 1.5f, 1));
	public static final RegistryObject<Item> GOLDEN_PICKAXE = OVERRIDE.register("golden_pickaxe", () -> new FBPickaxeItem(new Item.Properties().group(ModItemGroups.FB_HARVESTING_TOOLS), FBTier.COMMON, 15, 1, 6f, 1));
	public static final RegistryObject<Item> STONE_PICKAXE = OVERRIDE.register("stone_pickaxe", () -> new FBPickaxeItem(new Item.Properties().group(ModItemGroups.FB_HARVESTING_TOOLS), FBTier.COMMON, 20, 2, 2f, 1));
	public static final RegistryObject<Item> IRON_PICKAXE = OVERRIDE.register("iron_pickaxe", () -> new FBPickaxeItem(new Item.Properties().group(ModItemGroups.FB_HARVESTING_TOOLS), FBTier.COMMON, 25, 3, 3f, 1));
	public static final RegistryObject<Item> DIAMOND_PICKAXE = OVERRIDE.register("diamond_pickaxe", () -> new FBPickaxeItem(new Item.Properties().group(ModItemGroups.FB_HARVESTING_TOOLS), FBTier.UNCOMMON, 30, 4, 4.5f, 1));

	public static final RegistryObject<Item> STONK = ITEMS.register("stonk", () -> new StonkItem(new Item.Properties().group(ModItemGroups.FB_HARVESTING_TOOLS), FBTier.EPIC, 15, 1, 12, 1));

	/*
	 * Armor
	 */
	public static final RegistryObject<Item> LEATHER_HELMET = OVERRIDE.register("leather_helmet", () -> new FBDyeableArmorItem(EquipmentSlotType.HEAD, "leather", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.COMMON, 5, 0));
	public static final RegistryObject<Item> LEATHER_CHESTPLATE = OVERRIDE.register("leather_chestplate", () -> new FBDyeableArmorItem(EquipmentSlotType.CHEST, "leather", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.COMMON, 15, 0));
	public static final RegistryObject<Item> LEATHER_LEGGINGS = OVERRIDE.register("leather_leggings", () -> new FBDyeableArmorItem(EquipmentSlotType.LEGS, "leather", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.COMMON, 10, 0));
	public static final RegistryObject<Item> LEATHER_BOOTS = OVERRIDE.register("leather_boots", () -> new FBDyeableArmorItem(EquipmentSlotType.FEET, "leather", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.COMMON, 5, 0));

	public static final RegistryObject<Item> GOLDEN_HELMET = OVERRIDE.register("golden_helmet", () -> new FBArmorItem(EquipmentSlotType.HEAD, "gold", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.COMMON, 10, 0));
	public static final RegistryObject<Item> GOLDEN_CHESTPLATE = OVERRIDE.register("golden_chestplate", () -> new FBArmorItem(EquipmentSlotType.CHEST, "gold", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.COMMON, 25, 0));
	public static final RegistryObject<Item> GOLDEN_LEGGINGS = OVERRIDE.register("golden_leggings", () -> new FBArmorItem(EquipmentSlotType.LEGS, "gold", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.COMMON, 15, 0));
	public static final RegistryObject<Item> GOLDEN_BOOTS = OVERRIDE.register("golden_boots", () -> new FBArmorItem(EquipmentSlotType.FEET, "gold", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.COMMON, 5, 0));

	public static final RegistryObject<Item> CHAINMAIL_HELMET = OVERRIDE.register("chainmail_helmet", () -> new FBArmorItem(EquipmentSlotType.HEAD, "chainmail", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.UNCOMMON, 12, 0));
	public static final RegistryObject<Item> CHAINMAIL_CHESTPLATE = OVERRIDE.register("chainmail_chestplate", () -> new FBArmorItem(EquipmentSlotType.CHEST, "chainmail", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.UNCOMMON, 30, 0));
	public static final RegistryObject<Item> CHAINMAIL_LEGGINGS = OVERRIDE.register("chainmail_leggings", () -> new FBArmorItem(EquipmentSlotType.LEGS, "chainmail", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.UNCOMMON, 20, 0));
	public static final RegistryObject<Item> CHAINMAIL_BOOTS = OVERRIDE.register("chainmail_boots", () -> new FBArmorItem(EquipmentSlotType.FEET, "chainmail", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.UNCOMMON, 7, 0));

	public static final RegistryObject<Item> IRON_HELMET = OVERRIDE.register("iron_helmet", () -> new FBArmorItem(EquipmentSlotType.HEAD, "iron", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.COMMON, 10, 0));
	public static final RegistryObject<Item> IRON_CHESTPLATE = OVERRIDE.register("iron_chestplate", () -> new FBArmorItem(EquipmentSlotType.CHEST, "iron", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.COMMON, 30, 0));
	public static final RegistryObject<Item> IRON_LEGGINGS = OVERRIDE.register("iron_leggings", () -> new FBArmorItem(EquipmentSlotType.LEGS, "iron", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.COMMON, 25, 0));
	public static final RegistryObject<Item> IRON_BOOTS = OVERRIDE.register("iron_boots", () -> new FBArmorItem(EquipmentSlotType.FEET, "iron", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.COMMON, 10, 0));

	public static final RegistryObject<Item> DIAMOND_HELMET = OVERRIDE.register("diamond_helmet", () -> new FBArmorItem(EquipmentSlotType.HEAD, "diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.UNCOMMON, 15, 0));
	public static final RegistryObject<Item> DIAMOND_CHESTPLATE = OVERRIDE.register("diamond_chestplate", () -> new FBArmorItem(EquipmentSlotType.CHEST, "diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.UNCOMMON, 40, 0));
	public static final RegistryObject<Item> DIAMOND_LEGGINGS = OVERRIDE.register("diamond_leggings", () -> new FBArmorItem(EquipmentSlotType.LEGS, "diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.UNCOMMON, 30, 0));
	public static final RegistryObject<Item> DIAMOND_BOOTS = OVERRIDE.register("diamond_boots", () -> new FBArmorItem(EquipmentSlotType.FEET, "diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.UNCOMMON, 15, 0));

	public static final RegistryObject<Item> LAPIS_HELMET = ITEMS.register("lapis_helmet", () -> new LapisArmorItem(EquipmentSlotType.HEAD, "forgeblock:lapis", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.UNCOMMON, 25, 0));
	public static final RegistryObject<Item> LAPIS_CHESTPLATE = ITEMS.register("lapis_chestplate", () -> new LapisArmorItem(EquipmentSlotType.CHEST, "forgeblock:lapis", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.UNCOMMON, 40, 0));
	public static final RegistryObject<Item> LAPIS_LEGGINGS = ITEMS.register("lapis_leggings", () -> new LapisArmorItem(EquipmentSlotType.LEGS, "forgeblock:lapis", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.UNCOMMON, 35, 0));
	public static final RegistryObject<Item> LAPIS_BOOTS = ITEMS.register("lapis_boots", () -> new LapisArmorItem(EquipmentSlotType.FEET, "forgeblock:lapis", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.UNCOMMON, 20, 0));

	public static final RegistryObject<Item> HARDENED_DIAMOND_HELMET = ITEMS.register("hardened_diamond_helmet", () -> new FBArmorItem(EquipmentSlotType.HEAD, "forgeblock:hardened_diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.RARE, 60, 0));
	public static final RegistryObject<Item> HARDENED_DIAMOND_CHESTPLATE = ITEMS.register("hardened_diamond_chestplate", () -> new FBArmorItem(EquipmentSlotType.CHEST, "forgeblock:hardened_diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.RARE, 120, 0));
	public static final RegistryObject<Item> HARDENED_DIAMOND_LEGGINGS = ITEMS.register("hardened_diamond_leggings", () -> new FBArmorItem(EquipmentSlotType.LEGS, "forgeblock:hardened_diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.RARE, 95, 0));
	public static final RegistryObject<Item> HARDENED_DIAMOND_BOOTS = ITEMS.register("hardened_diamond_boots", () -> new FBArmorItem(EquipmentSlotType.FEET, "forgeblock:hardened_diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.RARE, 55, 0));

	public static final RegistryObject<Item> MINER_OUTFIT_HELMET = ITEMS.register("miner_outfit_helmet", () -> new MinerOutfitArmorItem(EquipmentSlotType.HEAD, "forgeblock:miner", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.UNCOMMON, 15, 0));
	public static final RegistryObject<Item> MINER_OUTFIT_CHESTPLATE = ITEMS.register("miner_outfit_chestplate", () -> new MinerOutfitArmorItem(EquipmentSlotType.CHEST, "forgeblock:miner", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.UNCOMMON, 40, 0));
	public static final RegistryObject<Item> MINER_OUTFIT_LEGGINGS = ITEMS.register("miner_outfit_leggings", () -> new MinerOutfitArmorItem(EquipmentSlotType.LEGS, "forgeblock:miner", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.UNCOMMON, 30, 0));
	public static final RegistryObject<Item> MINER_OUTFIT_BOOTS = ITEMS.register("miner_outfit_boots", () -> new MinerOutfitArmorItem(EquipmentSlotType.FEET, "forgeblock:miner", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.UNCOMMON, 15, 0));

	public static final RegistryObject<Item> GOLEM_HELMET = ITEMS.register("golem_helmet", () -> new GolemArmorItem(EquipmentSlotType.HEAD, "minecraft:iron", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.RARE, 45, 45));
	public static final RegistryObject<Item> GOLEM_CHESTPLATE = ITEMS.register("golem_chestplate", () -> new GolemArmorItem(EquipmentSlotType.CHEST, "minecraft:iron", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.RARE, 90, 65));
	public static final RegistryObject<Item> GOLEM_LEGGINGS = ITEMS.register("golem_leggings", () -> new GolemArmorItem(EquipmentSlotType.LEGS, "minecraft:iron", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.RARE, 75, 55));
	public static final RegistryObject<Item> GOLEM_BOOTS = ITEMS.register("golem_boots", () -> new GolemArmorItem(EquipmentSlotType.FEET, "minecraft:iron", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.RARE, 40, 40));

	public static final RegistryObject<Item> HELMET_OF_THE_STARS = ITEMS.register("helmet_of_the_stars", () -> new FBArmorItem(EquipmentSlotType.HEAD, "minecraft:gold", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.SPECIAL, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 10000, 3000, 0, 1000, 0, 0, 0)));
	public static final RegistryObject<Item> CHESTPLATE_OF_THE_STARS = ITEMS.register("chestplate_of_the_stars", () -> new FBArmorItem(EquipmentSlotType.CHEST, "minecraft:gold", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.SPECIAL, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 20000, 5000, 0, 2000, 0, 0, 0)));
	public static final RegistryObject<Item> LEGGINGS_OF_THE_STARS = ITEMS.register("leggings_of_the_stars", () -> new FBArmorItem(EquipmentSlotType.LEGS, "minecraft:gold", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.SPECIAL, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 15000, 4000, 0, 1500, 0, 0, 0)));
	public static final RegistryObject<Item> BOOTS_OF_THE_STARS = ITEMS.register("boots_of_the_stars", () -> new FBArmorItem(EquipmentSlotType.FEET, "minecraft:gold", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.SPECIAL, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 7500, 2500, 0, 1000, 0, 0, 0)));

	public static final RegistryObject<Item> YOUNG_DRAGON_HELMET = ITEMS.register("young_dragon_helmet", () -> new YoungDragonArmorItem(EquipmentSlotType.HEAD, "minecraft:iron", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.LEGENDARY, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 70, 110, 20, 0, 0, 0, 0)));
	public static final RegistryObject<Item> YOUNG_DRAGON_CHESTPLATE = ITEMS.register("young_dragon_chestplate", () -> new YoungDragonArmorItem(EquipmentSlotType.CHEST, "minecraft:iron", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.LEGENDARY, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 120, 160, 20, 0, 0, 0, 0)));
	public static final RegistryObject<Item> YOUNG_DRAGON_LEGGINGS = ITEMS.register("young_dragon_leggings", () -> new YoungDragonArmorItem(EquipmentSlotType.LEGS, "minecraft:iron", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.LEGENDARY, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 100, 140, 20, 0, 0, 0, 0)));
	public static final RegistryObject<Item> YOUNG_DRAGON_BOOTS = ITEMS.register("young_dragon_boots", () -> new YoungDragonArmorItem(EquipmentSlotType.FEET, "minecraft:iron", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.LEGENDARY, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 60, 90, 20, 0, 0, 0, 0)));

	public static final RegistryObject<Item> WISE_DRAGON_HELMET = ITEMS.register("wise_dragon_helmet", () -> new WiseDragonArmorItem(EquipmentSlotType.HEAD, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.LEGENDARY, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 70, 110, 0, 125, 0, 0, 0)));
	public static final RegistryObject<Item> WISE_DRAGON_CHESTPLATE = ITEMS.register("wise_dragon_chestplate", () -> new WiseDragonArmorItem(EquipmentSlotType.CHEST, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.LEGENDARY, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 120, 160, 0, 75, 0, 0, 0)));
	public static final RegistryObject<Item> WISE_DRAGON_LEGGINGS = ITEMS.register("wise_dragon_leggings", () -> new WiseDragonArmorItem(EquipmentSlotType.LEGS, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.LEGENDARY, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 100, 140, 0, 75, 0, 0, 0)));
	public static final RegistryObject<Item> WISE_DRAGON_BOOTS = ITEMS.register("wise_dragon_boots", () -> new WiseDragonArmorItem(EquipmentSlotType.FEET, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.LEGENDARY, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 60, 90, 0, 75, 0, 0, 0)));

	public static final RegistryObject<Item> UNSTABLE_DRAGON_HELMET = ITEMS.register("unstable_dragon_helmet", () -> new UnstableDragonArmorItem(EquipmentSlotType.HEAD, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.LEGENDARY, ModifierHelper.modifierMapFromDoubles(0, 0, 5, 15, 0, 0, 70, 110, 0, 25, 0, 0, 0)));
	public static final RegistryObject<Item> UNSTABLE_DRAGON_CHESTPLATE = ITEMS.register("unstable_dragon_chestplate", () -> new UnstableDragonArmorItem(EquipmentSlotType.CHEST, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.LEGENDARY, ModifierHelper.modifierMapFromDoubles(0, 0, 5, 15, 0, 0, 120, 160, 0, 0, 0, 0, 0)));
	public static final RegistryObject<Item> UNSTABLE_DRAGON_LEGGINGS = ITEMS.register("unstable_dragon_leggings", () -> new UnstableDragonArmorItem(EquipmentSlotType.LEGS, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.LEGENDARY, ModifierHelper.modifierMapFromDoubles(0, 0, 5, 15, 0, 0, 100, 140, 0, 0, 0, 0, 0)));
	public static final RegistryObject<Item> UNSTABLE_DRAGON_BOOTS = ITEMS.register("unstable_dragon_boots", () -> new UnstableDragonArmorItem(EquipmentSlotType.FEET, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.LEGENDARY, ModifierHelper.modifierMapFromDoubles(0, 0, 5, 15, 0, 0, 60, 90, 0, 0, 0, 0, 0)));

	public static final RegistryObject<Item> STRONG_DRAGON_HELMET = ITEMS.register("strong_dragon_helmet", () -> new StrongDragonArmorItem(EquipmentSlotType.HEAD, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.LEGENDARY, ModifierHelper.modifierMapFromDoubles(0, 25, 0, 0, 0, 0, 70, 110, 0, 0, 0, 0, 0)));
	public static final RegistryObject<Item> STRONG_DRAGON_CHESTPLATE = ITEMS.register("strong_dragon_chestplate", () -> new StrongDragonArmorItem(EquipmentSlotType.CHEST, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.LEGENDARY, ModifierHelper.modifierMapFromDoubles(0, 25, 0, 0, 0, 0, 120, 160, 0, 0, 0, 0, 0)));
	public static final RegistryObject<Item> STRONG_DRAGON_LEGGINGS = ITEMS.register("strong_dragon_leggings", () -> new StrongDragonArmorItem(EquipmentSlotType.LEGS, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.LEGENDARY, ModifierHelper.modifierMapFromDoubles(0, 25, 0, 0, 0, 0, 100, 140, 0, 0, 0, 0, 0)));
	public static final RegistryObject<Item> STRONG_DRAGON_BOOTS = ITEMS.register("strong_dragon_boots", () -> new StrongDragonArmorItem(EquipmentSlotType.FEET, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.LEGENDARY, ModifierHelper.modifierMapFromDoubles(0, 25, 0, 0, 0, 0, 60, 90, 0, 0, 0, 0, 0)));

	public static final RegistryObject<Item> SUPERIOR_DRAGON_HELMET = ITEMS.register("superior_dragon_helmet", () -> new SuperiorDragonArmorItem(EquipmentSlotType.HEAD, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.LEGENDARY, ModifierHelper.modifierMapFromDoubles(0, 10, 2, 8, 0, 0, 90, 130, 3, 25, 0, 0, 0)));
	public static final RegistryObject<Item> SUPERIOR_DRAGON_CHESTPLATE = ITEMS.register("superior_dragon_chestplate", () -> new SuperiorDragonArmorItem(EquipmentSlotType.CHEST, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.LEGENDARY, ModifierHelper.modifierMapFromDoubles(0, 10, 2, 8, 0, 0, 150, 190, 3, 25, 0, 0, 0)));
	public static final RegistryObject<Item> SUPERIOR_DRAGON_LEGGINGS = ITEMS.register("superior_dragon_leggings", () -> new SuperiorDragonArmorItem(EquipmentSlotType.LEGS, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.LEGENDARY, ModifierHelper.modifierMapFromDoubles(0, 10, 2, 8, 0, 0, 130, 170, 3, 25, 0, 0, 0)));
	public static final RegistryObject<Item> SUPERIOR_DRAGON_BOOTS = ITEMS.register("superior_dragon_boots", () -> new SuperiorDragonArmorItem(EquipmentSlotType.FEET, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.LEGENDARY, ModifierHelper.modifierMapFromDoubles(0, 10, 2, 8, 0, 0, 80, 110, 3, 25, 0, 0, 0)));

	public static final RegistryObject<Item> PROTECTOR_DRAGON_HELMET = ITEMS.register("protector_dragon_helmet", () -> new ProtectorDragonArmorItem(EquipmentSlotType.HEAD, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.LEGENDARY, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 70, 135, 0, 0, 0, 0, 0)));
	public static final RegistryObject<Item> PROTECTOR_DRAGON_CHESTPLATE = ITEMS.register("protector_dragon_chestplate", () -> new ProtectorDragonArmorItem(EquipmentSlotType.CHEST, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.LEGENDARY, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 120, 185, 0, 0, 0, 0, 0)));
	public static final RegistryObject<Item> PROTECTOR_DRAGON_LEGGINGS = ITEMS.register("protector_dragon_leggings", () -> new ProtectorDragonArmorItem(EquipmentSlotType.LEGS, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.LEGENDARY, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 100, 165, 0, 0, 0, 0, 0)));
	public static final RegistryObject<Item> PROTECTOR_DRAGON_BOOTS = ITEMS.register("protector_dragon_boots", () -> new ProtectorDragonArmorItem(EquipmentSlotType.FEET, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.LEGENDARY, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 60, 115, 0, 0, 0, 0, 0)));

	public static final RegistryObject<Item> PERFECT_HELMET_T1 = ITEMS.register("perfect_helmet_t1", () -> new FBArmorItem(EquipmentSlotType.HEAD, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.RARE, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 0, 110, 0, 0, 0, 0, 0)));
	public static final RegistryObject<Item> PERFECT_CHESTPLATE_T1 = ITEMS.register("perfect_chestplate_t1", () -> new FBArmorItem(EquipmentSlotType.CHEST, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.RARE, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 0, 160, 0, 0, 0, 0, 0)));
	public static final RegistryObject<Item> PERFECT_LEGGINGS_T1 = ITEMS.register("perfect_leggings_t1", () -> new FBArmorItem(EquipmentSlotType.LEGS, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.RARE, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 0, 140, 0, 0, 0, 0, 0)));
	public static final RegistryObject<Item> PERFECT_BOOTS_T1 = ITEMS.register("perfect_boots_t1", () -> new FBArmorItem(EquipmentSlotType.FEET, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.RARE, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 0, 90, 0, 0, 0, 0, 0)));

	public static final RegistryObject<Item> PERFECT_HELMET_T2 = ITEMS.register("perfect_helmet_t2", () -> new FBArmorItem(EquipmentSlotType.HEAD, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.RARE, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 0, 130, 0, 0, 0, 0, 0)));
	public static final RegistryObject<Item> PERFECT_CHESTPLATE_T2 = ITEMS.register("perfect_chestplate_t2", () -> new FBArmorItem(EquipmentSlotType.CHEST, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.RARE, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 0, 180, 0, 0, 0, 0, 0)));
	public static final RegistryObject<Item> PERFECT_LEGGINGS_T2 = ITEMS.register("perfect_leggings_t2", () -> new FBArmorItem(EquipmentSlotType.LEGS, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.RARE, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 0, 160, 0, 0, 0, 0, 0)));
	public static final RegistryObject<Item> PERFECT_BOOTS_T2 = ITEMS.register("perfect_boots_t2", () -> new FBArmorItem(EquipmentSlotType.FEET, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.RARE, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 0, 110, 0, 0, 0, 0, 0)));

	public static final RegistryObject<Item> PERFECT_HELMET_T3 = ITEMS.register("perfect_helmet_t3", () -> new FBArmorItem(EquipmentSlotType.HEAD, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.RARE, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 0, 150, 0, 0, 0, 0, 0)));
	public static final RegistryObject<Item> PERFECT_CHESTPLATE_T3 = ITEMS.register("perfect_chestplate_t3", () -> new FBArmorItem(EquipmentSlotType.CHEST, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.RARE, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 0, 200, 0, 0, 0, 0, 0)));
	public static final RegistryObject<Item> PERFECT_LEGGINGS_T3 = ITEMS.register("perfect_leggings_t3", () -> new FBArmorItem(EquipmentSlotType.LEGS, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.RARE, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 0, 180, 0, 0, 0, 0, 0)));
	public static final RegistryObject<Item> PERFECT_BOOTS_T3 = ITEMS.register("perfect_boots_t3", () -> new FBArmorItem(EquipmentSlotType.FEET, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.RARE, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 0, 130, 0, 0, 0, 0, 0)));

	public static final RegistryObject<Item> PERFECT_HELMET_T4 = ITEMS.register("perfect_helmet_t4", () -> new FBArmorItem(EquipmentSlotType.HEAD, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.EPIC, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 0, 170, 0, 0, 0, 0, 0)));
	public static final RegistryObject<Item> PERFECT_CHESTPLATE_T4 = ITEMS.register("perfect_chestplate_t4", () -> new FBArmorItem(EquipmentSlotType.CHEST, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.EPIC, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 0, 220, 0, 0, 0, 0, 0)));
	public static final RegistryObject<Item> PERFECT_LEGGINGS_T4 = ITEMS.register("perfect_leggings_t4", () -> new FBArmorItem(EquipmentSlotType.LEGS, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.EPIC, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 0, 200, 0, 0, 0, 0, 0)));
	public static final RegistryObject<Item> PERFECT_BOOTS_T4 = ITEMS.register("perfect_boots_t4", () -> new FBArmorItem(EquipmentSlotType.FEET, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.EPIC, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 0, 150, 0, 0, 0, 0, 0)));

	public static final RegistryObject<Item> PERFECT_HELMET_T5 = ITEMS.register("perfect_helmet_t5", () -> new FBArmorItem(EquipmentSlotType.HEAD, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.EPIC, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 0, 190, 0, 0, 0, 0, 0)));
	public static final RegistryObject<Item> PERFECT_CHESTPLATE_T5 = ITEMS.register("perfect_chestplate_t5", () -> new FBArmorItem(EquipmentSlotType.CHEST, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.EPIC, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 0, 240, 0, 0, 0, 0, 0)));
	public static final RegistryObject<Item> PERFECT_LEGGINGS_T5 = ITEMS.register("perfect_leggings_t5", () -> new FBArmorItem(EquipmentSlotType.LEGS, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.EPIC, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 0, 220, 0, 0, 0, 0, 0)));
	public static final RegistryObject<Item> PERFECT_BOOTS_T5 = ITEMS.register("perfect_boots_t5", () -> new FBArmorItem(EquipmentSlotType.FEET, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.EPIC, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 0, 170, 0, 0, 0, 0, 0)));

	public static final RegistryObject<Item> PERFECT_HELMET_T6 = ITEMS.register("perfect_helmet_t6", () -> new FBArmorItem(EquipmentSlotType.HEAD, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.EPIC, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 0, 210, 0, 0, 0, 0, 0)));
	public static final RegistryObject<Item> PERFECT_CHESTPLATE_T6 = ITEMS.register("perfect_chestplate_t6", () -> new FBArmorItem(EquipmentSlotType.CHEST, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.EPIC, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 0, 260, 0, 0, 0, 0, 0)));
	public static final RegistryObject<Item> PERFECT_LEGGINGS_T6 = ITEMS.register("perfect_leggings_t6", () -> new FBArmorItem(EquipmentSlotType.LEGS, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.EPIC, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 0, 240, 0, 0, 0, 0, 0)));
	public static final RegistryObject<Item> PERFECT_BOOTS_T6 = ITEMS.register("perfect_boots_t6", () -> new FBArmorItem(EquipmentSlotType.FEET, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.EPIC, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 0, 190, 0, 0, 0, 0, 0)));

	public static final RegistryObject<Item> PERFECT_HELMET_T7 = ITEMS.register("perfect_helmet_t7", () -> new FBArmorItem(EquipmentSlotType.HEAD, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.EPIC, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 0, 230, 0, 0, 0, 0, 0)));
	public static final RegistryObject<Item> PERFECT_CHESTPLATE_T7 = ITEMS.register("perfect_chestplate_t7", () -> new FBArmorItem(EquipmentSlotType.CHEST, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.EPIC, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 0, 280, 0, 0, 0, 0, 0)));
	public static final RegistryObject<Item> PERFECT_LEGGINGS_T7 = ITEMS.register("perfect_leggings_t7", () -> new FBArmorItem(EquipmentSlotType.LEGS, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.EPIC, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 0, 260, 0, 0, 0, 0, 0)));
	public static final RegistryObject<Item> PERFECT_BOOTS_T7 = ITEMS.register("perfect_boots_t7", () -> new FBArmorItem(EquipmentSlotType.FEET, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.EPIC, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 0, 210, 0, 0, 0, 0, 0)));

	public static final RegistryObject<Item> PERFECT_HELMET_T8 = ITEMS.register("perfect_helmet_t8", () -> new FBArmorItem(EquipmentSlotType.HEAD, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.LEGENDARY, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 0, 250, 0, 0, 0, 0, 0)));
	public static final RegistryObject<Item> PERFECT_CHESTPLATE_T8 = ITEMS.register("perfect_chestplate_t8", () -> new FBArmorItem(EquipmentSlotType.CHEST, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.LEGENDARY, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 0, 300, 0, 0, 0, 0, 0)));
	public static final RegistryObject<Item> PERFECT_LEGGINGS_T8 = ITEMS.register("perfect_leggings_t8", () -> new FBArmorItem(EquipmentSlotType.LEGS, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.LEGENDARY, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 0, 280, 0, 0, 0, 0, 0)));
	public static final RegistryObject<Item> PERFECT_BOOTS_T8 = ITEMS.register("perfect_boots_t8", () -> new FBArmorItem(EquipmentSlotType.FEET, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.LEGENDARY, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 0, 230, 0, 0, 0, 0, 0)));

	public static final RegistryObject<Item> PERFECT_HELMET_T9 = ITEMS.register("perfect_helmet_t9", () -> new FBArmorItem(EquipmentSlotType.HEAD, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.LEGENDARY, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 0, 270, 0, 0, 0, 0, 0)));
	public static final RegistryObject<Item> PERFECT_CHESTPLATE_T9 = ITEMS.register("perfect_chestplate_t9", () -> new FBArmorItem(EquipmentSlotType.CHEST, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.LEGENDARY, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 0, 320, 0, 0, 0, 0, 0)));
	public static final RegistryObject<Item> PERFECT_LEGGINGS_T9 = ITEMS.register("perfect_leggings_t9", () -> new FBArmorItem(EquipmentSlotType.LEGS, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.LEGENDARY, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 0, 300, 0, 0, 0, 0, 0)));
	public static final RegistryObject<Item> PERFECT_BOOTS_T9 = ITEMS.register("perfect_boots_t9", () -> new FBArmorItem(EquipmentSlotType.FEET, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.LEGENDARY, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 0, 250, 0, 0, 0, 0, 0)));

	public static final RegistryObject<Item> PERFECT_HELMET_T10 = ITEMS.register("perfect_helmet_t10", () -> new FBArmorItem(EquipmentSlotType.HEAD, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.LEGENDARY, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 0, 290, 0, 0, 0, 0, 0)));
	public static final RegistryObject<Item> PERFECT_CHESTPLATE_T10 = ITEMS.register("perfect_chestplate_t10", () -> new FBArmorItem(EquipmentSlotType.CHEST, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.LEGENDARY, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 0, 340, 0, 0, 0, 0, 0)));
	public static final RegistryObject<Item> PERFECT_LEGGINGS_T10 = ITEMS.register("perfect_leggings_t10", () -> new FBArmorItem(EquipmentSlotType.LEGS, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.LEGENDARY, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 0, 320, 0, 0, 0, 0, 0)));
	public static final RegistryObject<Item> PERFECT_BOOTS_T10 = ITEMS.register("perfect_boots_t10", () -> new FBArmorItem(EquipmentSlotType.FEET, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.LEGENDARY, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 0, 270, 0, 0, 0, 0, 0)));

	public static final RegistryObject<Item> PERFECT_HELMET_T11 = ITEMS.register("perfect_helmet_t11", () -> new FBArmorItem(EquipmentSlotType.HEAD, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.LEGENDARY, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 0, 310, 0, 0, 0, 0, 0)));
	public static final RegistryObject<Item> PERFECT_CHESTPLATE_T11 = ITEMS.register("perfect_chestplate_t11", () -> new FBArmorItem(EquipmentSlotType.CHEST, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.LEGENDARY, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 0, 360, 0, 0, 0, 0, 0)));
	public static final RegistryObject<Item> PERFECT_LEGGINGS_T11 = ITEMS.register("perfect_leggings_t11", () -> new FBArmorItem(EquipmentSlotType.LEGS, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.LEGENDARY, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 0, 340, 0, 0, 0, 0, 0)));
	public static final RegistryObject<Item> PERFECT_BOOTS_T11 = ITEMS.register("perfect_boots_t11", () -> new FBArmorItem(EquipmentSlotType.FEET, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.LEGENDARY, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 0, 290, 0, 0, 0, 0, 0)));

	public static final RegistryObject<Item> PERFECT_HELMET_T12 = ITEMS.register("perfect_helmet_t12", () -> new FBArmorItem(EquipmentSlotType.HEAD, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.LEGENDARY, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 0, 330, 0, 0, 0, 0, 0)));
	public static final RegistryObject<Item> PERFECT_CHESTPLATE_T12 = ITEMS.register("perfect_chestplate_t12", () -> new FBArmorItem(EquipmentSlotType.CHEST, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.LEGENDARY, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 0, 380, 0, 0, 0, 0, 0)));
	public static final RegistryObject<Item> PERFECT_LEGGINGS_T12 = ITEMS.register("perfect_leggings_t12", () -> new FBArmorItem(EquipmentSlotType.LEGS, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.LEGENDARY, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 0, 360, 0, 0, 0, 0, 0)));
	public static final RegistryObject<Item> PERFECT_BOOTS_T12 = ITEMS.register("perfect_boots_t12", () -> new FBArmorItem(EquipmentSlotType.FEET, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.LEGENDARY, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 0, 310, 0, 0, 0, 0, 0)));

	public static final RegistryObject<Item> BLAZE_HELMET = ITEMS.register("blaze_helmet", () -> new BlazeArmorItem(EquipmentSlotType.HEAD, "minecraft:gold", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.EPIC, ModifierHelper.modifierMapFromDoubles(0, 10, 0, 0, 0, 0, 0, 80, 2, 0, 0, 0, 0)));
	public static final RegistryObject<Item> BLAZE_CHESTPLATE = ITEMS.register("blaze_chestplate", () -> new BlazeArmorItem(EquipmentSlotType.CHEST, "minecraft:gold", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.EPIC, ModifierHelper.modifierMapFromDoubles(0, 10, 0, 0, 0, 0, 0, 150, 2, 0, 0, 0, 0)));
	public static final RegistryObject<Item> BLAZE_LEGGINGS = ITEMS.register("blaze_leggings", () -> new BlazeArmorItem(EquipmentSlotType.LEGS, "minecraft:gold", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.EPIC, ModifierHelper.modifierMapFromDoubles(0, 10, 0, 0, 0, 0, 0, 110, 2, 0, 0, 0, 0)));
	public static final RegistryObject<Item> BLAZE_BOOTS = ITEMS.register("blaze_boots", () -> new BlazeArmorItem(EquipmentSlotType.FEET, "minecraft:gold", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.EPIC, ModifierHelper.modifierMapFromDoubles(0, 10, 0, 0, 0, 0, 0, 70, 2, 0, 0, 0, 0)));

	public static final RegistryObject<Item> FROZEN_BLAZE_HELMET = ITEMS.register("frozen_blaze_helmet", () -> new FrozenBlazeArmorItem(EquipmentSlotType.HEAD, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.EPIC, ModifierHelper.modifierMapFromDoubles(0, 40, 0, 0, 0, 0, 0, 110, 2, 0, 0, 0, 0)));
	public static final RegistryObject<Item> FROZEN_BLAZE_CHESTPLATE = ITEMS.register("frozen_blaze_chestplate", () -> new FrozenBlazeArmorItem(EquipmentSlotType.CHEST, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.EPIC, ModifierHelper.modifierMapFromDoubles(0, 40, 0, 0, 0, 0, 0, 180, 2, 0, 0, 0, 0)));
	public static final RegistryObject<Item> FROZEN_BLAZE_LEGGINGS = ITEMS.register("frozen_blaze_leggings", () -> new FrozenBlazeArmorItem(EquipmentSlotType.LEGS, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.EPIC, ModifierHelper.modifierMapFromDoubles(0, 40, 0, 0, 0, 0, 0, 140, 2, 0, 0, 0, 0)));
	public static final RegistryObject<Item> FROZEN_BLAZE_BOOTS = ITEMS.register("frozen_blaze_boots", () -> new FrozenBlazeArmorItem(EquipmentSlotType.FEET, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.EPIC, ModifierHelper.modifierMapFromDoubles(0, 40, 0, 0, 0, 0, 0, 100, 2, 0, 0, 0, 0)));
	
	public static final RegistryObject<Item> CROWN_OF_GREED = ITEMS.register("crown_of_greed", () -> new CrownOfGreedItem(EquipmentSlotType.HEAD, "minecraft:gold", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.LEGENDARY, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 130, 90, 0, 0, 0, 4, 0, 0)));
	public static final RegistryObject<Item> BLAZE_HAT = ITEMS.register("blaze_hat", () -> new FBArmorItem(EquipmentSlotType.HEAD, "minecraft:gold", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.COMMON, ModifierHelper.modifierMapFromDoubles(0, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)));
	public static final RegistryObject<Item> CHICKEN_HEAD = ITEMS.register("chicken_head", () -> new FBArmorItem(EquipmentSlotType.HEAD, "minecraft:gold", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.COMMON, ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 20, 0, 0, 0, 0, 0, 0, 0)));

	/*
	 * Potion
	 */
	public static final RegistryObject<Item> FB_POTION = OVERRIDE.register("potion", () -> new FBPotionItem(new Item.Properties().group(ModItemGroups.FB_POTIONS).maxStackSize(1)));

	/*
	 * Spawn Eggs
	 */
	public static final RegistryObject<Item> LV1_ZOMBIE_SPAWN_EGG = ITEMS.register("lv1_zombie_spawn_egg", () -> new SpawnEggItem(ModEntities.LV1_ZOMBIE_TYPE, 44975, 7969893, new Item.Properties().group(ModItemGroups.FB_MOBS)));
	public static final RegistryObject<Item> LAPIS_ZOMBIE_SPAWN_EGG = ITEMS.register("lapis_zombie_spawn_egg", () -> new SpawnEggItem(ModEntities.LAPIS_ZOMBIE_TYPE, 1269462, 7969893, new Item.Properties().group(ModItemGroups.FB_MOBS)));
	public static final RegistryObject<Item> ZEALOT_SPAWN_EGG = ITEMS.register("zealot_spawn_egg", () -> new SpawnEggItem(ModEntities.ZEALOT_TYPE, 1447446, 0, new Item.Properties().group(ModItemGroups.FB_MOBS)));
	public static final RegistryObject<Item> LV15_WOLF_SPAWN_EGG = ITEMS.register("lv15_wolf_spawn_egg", () -> new SpawnEggItem(ModEntities.LV15_WOLF_TYPE, 14144467, 13545366, (new Item.Properties()).group(ModItemGroups.FB_MOBS)));
	public static final RegistryObject<Item> OLD_WOLF_SPAWN_EGG = ITEMS.register("old_wolf_spawn_egg", () -> new SpawnEggItem(ModEntities.OLD_WOLF_TYPE, 14144467, 13545366, (new Item.Properties()).group(ModItemGroups.FB_MOBS)));
	public static final RegistryObject<Item> LV1_ZOMBIE_VILLAGER_SPAWN_EGG = ITEMS.register("lv1_zombie_villager_spawn_egg", () -> new SpawnEggItem(ModEntities.LV1_ZOMBIE_VILLAGER_TYPE, 5651507, 7969893, (new Item.Properties()).group(ModItemGroups.FB_MOBS)));
	public static final RegistryObject<Item> TANK_ZOMBIE_SPAWN_EGG = ITEMS.register("tank_zombie_spawn_egg", () -> new SpawnEggItem(ModEntities.TANK_ZOMBIE_TYPE, 5651507, 7969893, (new Item.Properties()).group(ModItemGroups.FB_MOBS)));
	public static final RegistryObject<Item> CRYPT_GHOUL_SPAWN_EGG = ITEMS.register("crypt_ghoul_spawn_egg", () -> new SpawnEggItem(ModEntities.CRYPT_GHOUL_TYPE, 44975, 7969893, new Item.Properties().group(ModItemGroups.FB_MOBS)));
	public static final RegistryObject<Item> ATONED_REVENANT_SPAWN_EGG = ITEMS.register("atoned_revenant_spawn_egg", () -> new SpawnEggItem(ModEntities.ATONED_REVENANT_TYPE, 44975, 7969893, new Item.Properties().group(ModItemGroups.FB_MOBS)));
	public static final RegistryObject<Item> ATONED_CHAMPION_SPAWN_EGG = ITEMS.register("atoned_champion_spawn_egg", () -> new SpawnEggItem(ModEntities.ATONED_CHAMPION_TYPE, 44975, 7969893, new Item.Properties().group(ModItemGroups.FB_MOBS)));
	public static final RegistryObject<Item> DEFORMED_REVENANT_SPAWN_EGG = ITEMS.register("deformed_revenant_spawn_egg", () -> new SpawnEggItem(ModEntities.DEFORMED_REVENANT_TYPE, 44975, 7969893, new Item.Properties().group(ModItemGroups.FB_MOBS)));
	public static final RegistryObject<Item> REVENANT_CHAMPION_SPAWN_EGG = ITEMS.register("revenant_champion_spawn_egg", () -> new SpawnEggItem(ModEntities.REVENANT_CHAMPION_TYPE, 44975, 7969893, new Item.Properties().group(ModItemGroups.FB_MOBS)));
	public static final RegistryObject<Item> REVENANT_SYCOPHANT_SPAWN_EGG = ITEMS.register("revenant_sycophant_spawn_egg", () -> new SpawnEggItem(ModEntities.REVENANT_SYCOPHANT_TYPE, 44975, 7969893, new Item.Properties().group(ModItemGroups.FB_MOBS)));
	public static final RegistryObject<Item> GOLDEN_GHOUL_SPAWN_EGG = ITEMS.register("golden_ghoul_spawn_egg", () -> new SpawnEggItem(ModEntities.GOLDEN_GHOUL_TYPE, 0xFFCC00, 7969893, new Item.Properties().group(ModItemGroups.FB_MOBS)));

	/**
	 * Not final
	 */
	public static final RegistryObject<Item> MINION_SPAWN_EGG = ITEMS.register("minion_spawn_egg", () -> new SpawnEggItem(ModEntities.MINION_TYPE, 1080802, 0, new Item.Properties().group(ModItemGroups.FB_MOBS)));

	/*
	 * Misc
	 */
	public static final RegistryObject<Item> ENCHANTED_BOOK = OVERRIDE.register("enchanted_book", () -> new FBEnchantedBookItem(new Item.Properties().maxStackSize(1)));
	public static final RegistryObject<Item> EXPERIENCE_BOTTLE = OVERRIDE.register("experience_bottle", () -> new FBExperienceBottleItem(new Item.Properties().group(ModItemGroups.FB_MISC), FBTier.COMMON, 3, 11));
	public static final RegistryObject<Item> GRAND_EXPERIENCE_BOTTLE = ITEMS.register("grand_experience_bottle", () -> new FBExperienceBottleItem(new Item.Properties().group(ModItemGroups.FB_MISC), FBTier.UNCOMMON, 1500 - 307, 1500 + 307));
	public static final RegistryObject<Item> TITANIC_EXPERIENCE_BOTTLE = ITEMS.register("titanic_experience_bottle", () -> new FBExperienceBottleItem(new Item.Properties().group(ModItemGroups.FB_MISC), FBTier.RARE, 139770 - 2477, 139770 + 2477));
	public static final RegistryObject<Item> COLOSSAL_EXPERIENCE_BOTTLE = ITEMS.register("colossal_experience_bottle", () -> new FBExperienceBottleItem(new Item.Properties().group(ModItemGroups.FB_MISC), FBTier.EPIC, 279540 - 4977, 279540 + 4977));
	
	public static final RegistryObject<Item> HOT_POTATO_BOOK = ITEMS.register("hot_potato_book", () -> new HotPotatoBookItem(new Item.Properties().maxStackSize(1).group(ModItemGroups.FB_MISC), FBTier.EPIC));
	public static final RegistryObject<Item> FUMING_POTATO_BOOK = ITEMS.register("fuming_potato_book", () -> new HotPotatoBookItem(new Item.Properties().maxStackSize(1).group(ModItemGroups.FB_MISC), FBTier.EPIC, true));
	public static final RegistryObject<Item> WOOD_SINGULARITY = ITEMS.register("wood_singularity", () -> new WoodSingularityItem(new Item.Properties().maxStackSize(1).group(ModItemGroups.FB_MISC), FBTier.LEGENDARY));
	public static final RegistryObject<Item> RECOMBOBULATOR_3000 = ITEMS.register("recombobulator_3000", () -> new RecombobulatorItem(new Item.Properties().maxStackSize(1).group(ModItemGroups.FB_MISC), FBTier.LEGENDARY));
	
	public static final RegistryObject<Item> ANVIL = OVERRIDE.register("anvil", () -> new BlockItem(ModBlocks.ANVIL.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));
	public static final RegistryObject<Item> CHIPPED_ANVIL = OVERRIDE.register("chipped_anvil", () -> new BlockItem(ModBlocks.CHIPPED_ANVIL.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));
	public static final RegistryObject<Item> DAMAGED_ANVIL = OVERRIDE.register("damaged_anvil", () -> new BlockItem(ModBlocks.DAMAGED_ANVIL.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));
	
	public static final RegistryObject<Item> COMMAND_BLOCK = OVERRIDE.register("command_block", () -> new BlockItem(Blocks.COMMAND_BLOCK, new Item.Properties().group(ItemGroup.REDSTONE)));
	public static final RegistryObject<Item> CHAIN_COMMAND_BLOCK = OVERRIDE.register("chain_command_block", () -> new BlockItem(Blocks.CHAIN_COMMAND_BLOCK, new Item.Properties().group(ItemGroup.REDSTONE)));
	public static final RegistryObject<Item> REPEATING_COMMAND_BLOCK = OVERRIDE.register("repeating_command_block", () -> new BlockItem(Blocks.REPEATING_COMMAND_BLOCK, new Item.Properties().group(ItemGroup.REDSTONE)));
	public static final RegistryObject<Item> COMMAND_BLOCK_MINECART = OVERRIDE.register("command_block_minecart", () -> new MinecartItem(AbstractMinecartEntity.Type.COMMAND_BLOCK, new Item.Properties().maxStackSize(1).group(ItemGroup.REDSTONE)));
	
	/*
	 * Custom items/ideas/debug
	 */
	public static final RegistryObject<Item> DIMENSIUS = ITEMS.register("dimensius", () -> new FBArmorItem(EquipmentSlotType.HEAD, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_CUSTOM), FBTier.MYTHIC, ModifierHelper.modifierMapFromDoubles(0, -1000, 0, 0, 0, 0, -1000, 0, 0, 3000, 0, 0, 0, Integer.MAX_VALUE)));
	public static final RegistryObject<Item> JESTER_HELMET = ITEMS.register("jester_helmet", () -> new FBArmorItem(EquipmentSlotType.HEAD, "minecraft:gold", new Item.Properties().group(ModItemGroups.FB_CUSTOM), FBTier.EPIC, ModifierHelper.modifierMapFromDoubles(0, -40, 0, 0, 0, 0, 0, -150, 0, 400, 0, -3, 0, 0)));
	public static final RegistryObject<Item> JESTER_CHESTPLATE = ITEMS.register("jester_chestplate", () -> new FBArmorItem(EquipmentSlotType.CHEST, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_CUSTOM), FBTier.EPIC, ModifierHelper.modifierMapFromDoubles(0, -40, 0, 0, 0, 0, 0, 300, 0, -200, 0, -3, 0, 0)));
	public static final RegistryObject<Item> JESTER_LEGGINGS = ITEMS.register("jester_leggings", () -> new FBArmorItem(EquipmentSlotType.LEGS, "minecraft:iron", new Item.Properties().group(ModItemGroups.FB_CUSTOM), FBTier.EPIC, ModifierHelper.modifierMapFromDoubles(0, 80, 0, 0, 0, 0, 0, -150, 0, -200, 0, -3, 0, 0)));
	public static final RegistryObject<Item> JESTER_BOOTS = ITEMS.register("jester_boots", () -> new FBArmorItem(EquipmentSlotType.FEET, "minecraft:chainmail", new Item.Properties().group(ModItemGroups.FB_CUSTOM), FBTier.EPIC, ModifierHelper.modifierMapFromDoubles(0, -40, 0, 0, 0, 0, 0, -150, 0, -200, 0, 6, 0, 0)));
	public static final RegistryObject<Item> ONE = ITEMS.register("one", () -> new OneSwordItem(new Item.Properties().group(ModItemGroups.FB_CUSTOM), FBTier.SPECIAL));
	public static final RegistryObject<Item> TEST_HELMET = ITEMS.register("test", () -> new TestArmorItem(EquipmentSlotType.HEAD, "minecraft:gold", new Item.Properties().group(ModItemGroups.FB_CUSTOM), FBTier.EPIC));

}
