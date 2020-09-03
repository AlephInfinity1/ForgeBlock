package alephinfinity1.forgeblock.init;

import alephinfinity1.forgeblock.ForgeBlock;
import alephinfinity1.forgeblock.item.AspectOfTheEndItem;
import alephinfinity1.forgeblock.item.FBArmorItem;
import alephinfinity1.forgeblock.item.FBDyeableArmorItem;
import alephinfinity1.forgeblock.item.FBGlintedItem;
import alephinfinity1.forgeblock.item.FBLapisArmorItem;
import alephinfinity1.forgeblock.item.FBMinerOutfitArmorItem;
import alephinfinity1.forgeblock.item.FBPickaxeItem;
import alephinfinity1.forgeblock.item.FBPotionItem;
import alephinfinity1.forgeblock.item.FBSwordItem;
import alephinfinity1.forgeblock.item.RogueSwordItem;
import alephinfinity1.forgeblock.item.StonkItem;
import alephinfinity1.forgeblock.misc.tier.FBTier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber
public class ModItems {
	
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ForgeBlock.MOD_ID);
	
	public static final DeferredRegister<Item> OVERRIDE = DeferredRegister.create(ForgeRegistries.ITEMS, ForgeBlock.MINECRAFT_ID);
	
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
	public static final RegistryObject<Item> ROGUE_SWORD = ITEMS.register("rogue_sword", () -> new RogueSwordItem(new Item.Properties().group(ModItemGroups.FB_SWORDS), FBTier.COMMON, 20, 0, 0, 0));
	
	/*
	 * Pickaxes
	 */
	public static final RegistryObject<Item> WOODEN_PICKAXE = OVERRIDE.register("wooden_pickaxe", () -> new FBPickaxeItem(new Item.Properties().group(ModItemGroups.FB_HARVESTING_TOOLS), FBTier.COMMON, 15, 1, 2, 1));
	public static final RegistryObject<Item> GOLDEN_PICKAXE = OVERRIDE.register("golden_pickaxe", () -> new FBPickaxeItem(new Item.Properties().group(ModItemGroups.FB_HARVESTING_TOOLS), FBTier.COMMON, 15, 1, 12, 1));
	public static final RegistryObject<Item> STONE_PICKAXE = OVERRIDE.register("stone_pickaxe", () -> new FBPickaxeItem(new Item.Properties().group(ModItemGroups.FB_HARVESTING_TOOLS), FBTier.COMMON, 20, 2, 4, 1));
	public static final RegistryObject<Item> IRON_PICKAXE = OVERRIDE.register("iron_pickaxe", () -> new FBPickaxeItem(new Item.Properties().group(ModItemGroups.FB_HARVESTING_TOOLS), FBTier.COMMON, 25, 3, 6, 1));
	public static final RegistryObject<Item> DIAMOND_PICKAXE = OVERRIDE.register("diamond_pickaxe", () -> new FBPickaxeItem(new Item.Properties().group(ModItemGroups.FB_HARVESTING_TOOLS), FBTier.UNCOMMON, 30, 4, 8, 1));
	
	public static final RegistryObject<Item> STONK = ITEMS.register("stonk", () -> new StonkItem(new Item.Properties(), FBTier.EPIC, 15, 1, 12, 1));
	
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
	
	public static final RegistryObject<Item> LAPIS_HELMET = ITEMS.register("lapis_helmet", () -> new FBLapisArmorItem(EquipmentSlotType.HEAD, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.UNCOMMON, 25, 0));
	public static final RegistryObject<Item> LAPIS_CHESTPLATE = ITEMS.register("lapis_chestplate", () -> new FBLapisArmorItem(EquipmentSlotType.CHEST, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.UNCOMMON, 40, 0));
	public static final RegistryObject<Item> LAPIS_LEGGINGS = ITEMS.register("lapis_leggings", () -> new FBLapisArmorItem(EquipmentSlotType.LEGS, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.UNCOMMON, 35, 0));
	public static final RegistryObject<Item> LAPIS_BOOTS = ITEMS.register("lapis_boots", () -> new FBLapisArmorItem(EquipmentSlotType.FEET, "minecraft:diamond", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.UNCOMMON, 20, 0));
	
	public static final RegistryObject<Item> MINER_OUTFIT_HELMET = ITEMS.register("miner_outfit_helmet", () -> new FBMinerOutfitArmorItem(EquipmentSlotType.HEAD, "minecraft:iron", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.UNCOMMON, 15, 0));
	public static final RegistryObject<Item> MINER_OUTFIT_CHESTPLATE = ITEMS.register("miner_outfit_chestplate", () -> new FBMinerOutfitArmorItem(EquipmentSlotType.CHEST, "minecraft:iron", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.UNCOMMON, 40, 0));
	public static final RegistryObject<Item> MINER_OUTFIT_LEGGINGS = ITEMS.register("miner_outfit_leggings", () -> new FBMinerOutfitArmorItem(EquipmentSlotType.LEGS, "minecraft:iron", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.UNCOMMON, 30, 0));
	public static final RegistryObject<Item> MINER_OUTFIT_BOOTS = ITEMS.register("miner_outfit_boots", () -> new FBMinerOutfitArmorItem(EquipmentSlotType.FEET, "minecraft:iron", new Item.Properties().group(ModItemGroups.FB_ARMOR), FBTier.UNCOMMON, 15, 0));
	
	/*
	 * Potion
	 */
	public static final RegistryObject<Item> FB_POTION = OVERRIDE.register("potion", () -> new FBPotionItem(new Item.Properties().group(ModItemGroups.FB_POTIONS)));

}
