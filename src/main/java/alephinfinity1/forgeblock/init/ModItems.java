package alephinfinity1.forgeblock.init;

import alephinfinity1.forgeblock.ForgeBlock;
import alephinfinity1.forgeblock.item.AspectOfTheEndItem;
import alephinfinity1.forgeblock.item.FBGlintedItem;
import alephinfinity1.forgeblock.item.FBPickaxeItem;
import alephinfinity1.forgeblock.item.FBSwordItem;
import alephinfinity1.forgeblock.item.StonkItem;
import alephinfinity1.forgeblock.misc.tier.FBTier;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber
public class ModItems {
	
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ForgeBlock.MOD_ID);
	
	public static final DeferredRegister<Item> OVERRIDE = DeferredRegister.create(ForgeRegistries.ITEMS, "minecraft");
	
	/*
	 * Enchanted items
	 */
	public static final RegistryObject<Item> ENCHANTED_COBBLESTONE = ITEMS.register("enchanted_cobblestone", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.UNCOMMON));
	public static final RegistryObject<Item> ENCHANTED_IRON = ITEMS.register("enchanted_iron", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.UNCOMMON));
	public static final RegistryObject<Item> ENCHANTED_GOLD = ITEMS.register("enchanted_gold", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.UNCOMMON));
	public static final RegistryObject<Item> ENCHANTED_COAL = ITEMS.register("enchanted_coal", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.UNCOMMON));
	public static final RegistryObject<Item> ENCHANTED_REDSTONE = ITEMS.register("enchanted_redstone", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.UNCOMMON));
	public static final RegistryObject<Item> ENCHANTED_LAPIS_LAZULI = ITEMS.register("enchanted_lapis_lazuli", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.UNCOMMON));
	public static final RegistryObject<Item> ENCHANTED_DIAMOND = ITEMS.register("enchanted_diamond", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.UNCOMMON));
	public static final RegistryObject<Item> ENCHANTED_EMERALD = ITEMS.register("enchanted_emerald", () -> new FBGlintedItem(new Item.Properties().group(ModItemGroups.FB_MATERIALS), FBTier.UNCOMMON));
	
	/*
	 * Swords
	 */
	public static final RegistryObject<Item> WOODEN_SWORD = OVERRIDE.register("wooden_sword", () -> new FBSwordItem(new Item.Properties().group(ModItemGroups.FB_SWORDS), FBTier.COMMON, 20, 0, 0, 0));
	public static final RegistryObject<Item> GOLDEN_SWORD = OVERRIDE.register("golden_sword", () -> new FBSwordItem(new Item.Properties().group(ModItemGroups.FB_SWORDS), FBTier.COMMON, 20, 0, 0, 0));
	public static final RegistryObject<Item> STONE_SWORD = OVERRIDE.register("stone_sword", () -> new FBSwordItem(new Item.Properties().group(ModItemGroups.FB_SWORDS), FBTier.COMMON, 25, 0, 0, 0));
	public static final RegistryObject<Item> IRON_SWORD = OVERRIDE.register("iron_sword", () -> new FBSwordItem(new Item.Properties().group(ModItemGroups.FB_SWORDS), FBTier.COMMON, 30, 0, 0, 0));
	public static final RegistryObject<Item> DIAMOND_SWORD = OVERRIDE.register("diamond_sword", () -> new FBSwordItem(new Item.Properties().group(ModItemGroups.FB_SWORDS), FBTier.UNCOMMON, 35, 0, 0, 0));
	
	public static final RegistryObject<Item> ASPECT_OF_THE_END = ITEMS.register("aspect_of_the_end", () -> new AspectOfTheEndItem(new Item.Properties().group(ModItemGroups.FB_SWORDS), FBTier.RARE, 100, 100, 0, 0));
	public static final RegistryObject<Item> SILVER_FANG = ITEMS.register("silver_fang", () -> new FBSwordItem(new Item.Properties().group(ModItemGroups.FB_SWORDS), FBTier.UNCOMMON, 100, 0, 0, 0));
	public static final RegistryObject<Item> LEAPING_SWORD = ITEMS.register("leaping_sword", () -> new FBSwordItem(new Item.Properties().group(ModItemGroups.FB_SWORDS), FBTier.EPIC, 150, 100, 0, 25));
	public static final RegistryObject<Item> ASPECT_OF_THE_DRAGONS = ITEMS.register("aspect_of_the_dragons", () -> new FBSwordItem(new Item.Properties().group(ModItemGroups.FB_SWORDS), FBTier.LEGENDARY, 225, 100, 0, 0));
	public static final RegistryObject<Item> SWORD_OF_THE_STARS = ITEMS.register("sword_of_the_stars", () -> new FBSwordItem(new Item.Properties().group(ModItemGroups.FB_SWORDS), FBTier.SPECIAL, 99999, 0, 0, 0));
	
	/*
	 * Pickaxes
	 */
	public static final RegistryObject<Item> WOODEN_PICKAXE = OVERRIDE.register("wooden_pickaxe", () -> new FBPickaxeItem(new Item.Properties().group(ModItemGroups.FB_HARVESTING_TOOLS), FBTier.COMMON, 15, 1, 2, 1));
	public static final RegistryObject<Item> GOLDEN_PICKAXE = OVERRIDE.register("golden_pickaxe", () -> new FBPickaxeItem(new Item.Properties().group(ModItemGroups.FB_HARVESTING_TOOLS), FBTier.COMMON, 15, 1, 12, 1));
	public static final RegistryObject<Item> STONE_PICKAXE = OVERRIDE.register("stone_pickaxe", () -> new FBPickaxeItem(new Item.Properties().group(ModItemGroups.FB_HARVESTING_TOOLS), FBTier.COMMON, 20, 2, 4, 1));
	public static final RegistryObject<Item> IRON_PICKAXE = OVERRIDE.register("iron_pickaxe", () -> new FBPickaxeItem(new Item.Properties().group(ModItemGroups.FB_HARVESTING_TOOLS), FBTier.COMMON, 25, 3, 6, 1));
	public static final RegistryObject<Item> DIAMOND_PICKAXE = OVERRIDE.register("diamond_pickaxe", () -> new FBPickaxeItem(new Item.Properties().group(ModItemGroups.FB_HARVESTING_TOOLS), FBTier.UNCOMMON, 30, 4, 8, 1));
	
	public static final RegistryObject<Item> STONK = ITEMS.register("stonk", () -> new StonkItem(new Item.Properties(), FBTier.EPIC, 15, 1, 12, 1));
	
}
