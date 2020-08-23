package alephinfinity1.forgeblock.init;

import alephinfinity1.forgeblock.ForgeBlock;
import alephinfinity1.forgeblock.item.AspectOfTheEndItem;
import alephinfinity1.forgeblock.item.FBGlintedItem;
import alephinfinity1.forgeblock.item.FBSwordItem;
import alephinfinity1.forgeblock.misc.tier.FBTier;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
	
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ForgeBlock.MOD_ID);
	
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
	public static final RegistryObject<Item> ASPECT_OF_THE_END = ITEMS.register("aspect_of_the_end", () -> new AspectOfTheEndItem(new Item.Properties().group(ModItemGroups.FB_SWORDS), FBTier.RARE, 100, 100, 0, 0));
	public static final RegistryObject<Item> SILVER_FANG = ITEMS.register("silver_fang", () -> new FBSwordItem(new Item.Properties().group(ModItemGroups.FB_SWORDS), FBTier.UNCOMMON, 100, 0, 0, 0));
	public static final RegistryObject<Item> LEAPING_SWORD = ITEMS.register("leaping_sword", () -> new FBSwordItem(new Item.Properties().group(ModItemGroups.FB_SWORDS), FBTier.EPIC, 150, 100, 0, 25));
	public static final RegistryObject<Item> ASPECT_OF_THE_DRAGONS = ITEMS.register("aspect_of_the_dragons", () -> new FBSwordItem(new Item.Properties().group(ModItemGroups.FB_SWORDS), FBTier.LEGENDARY, 225, 100, 0, 0));
	public static final RegistryObject<Item> SWORD_OF_THE_STARS = ITEMS.register("sword_of_the_stars", () -> new FBSwordItem(new Item.Properties().group(ModItemGroups.FB_SWORDS), FBTier.SPECIAL, 99999, 0, 0, 0));

}
