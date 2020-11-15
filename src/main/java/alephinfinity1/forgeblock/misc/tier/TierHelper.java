package alephinfinity1.forgeblock.misc.tier;

import java.util.HashMap;
import java.util.Map;

import alephinfinity1.forgeblock.misc.stats_modifier.capability.IItemModifiers;
import alephinfinity1.forgeblock.misc.stats_modifier.capability.ItemModifiersProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class TierHelper {
	
	/**
	 * A map for holding all vanilla items that has a tier other than COMMON.
	 */
	private static final Map<Item, FBTier> SPECIAL_VANILLA_ITEMS;
	
	static {
		SPECIAL_VANILLA_ITEMS = new HashMap<>();
		SPECIAL_VANILLA_ITEMS.put(Items.ENCHANTED_GOLDEN_APPLE, FBTier.UNCOMMON);
		SPECIAL_VANILLA_ITEMS.put(Items.NETHER_STAR, FBTier.LEGENDARY);
		SPECIAL_VANILLA_ITEMS.put(Items.BEACON, FBTier.LEGENDARY);
		SPECIAL_VANILLA_ITEMS.put(Items.BEDROCK, FBTier.SPECIAL);
		SPECIAL_VANILLA_ITEMS.put(Items.COMMAND_BLOCK, FBTier.SPECIAL);
		SPECIAL_VANILLA_ITEMS.put(Items.COMMAND_BLOCK_MINECART, FBTier.SPECIAL);
		SPECIAL_VANILLA_ITEMS.put(Items.CHAIN_COMMAND_BLOCK, FBTier.SPECIAL);
		SPECIAL_VANILLA_ITEMS.put(Items.REPEATING_COMMAND_BLOCK, FBTier.SPECIAL);
		SPECIAL_VANILLA_ITEMS.put(Items.STRUCTURE_BLOCK, FBTier.SPECIAL);
		SPECIAL_VANILLA_ITEMS.put(Items.BARRIER, FBTier.SPECIAL);
	}

	/**
	 * Gets the tier of an ItemStack.
	 * This method should be used for IFBTieredItem only. For non-tiered items, use getItemTier(ItemStack)
	 * @param stack The ItemStack.
	 * @param tier The base tier of that ItemStack (determined by Item).
	 * @return The ItemStack's tier.
	 */
	public static FBTier getStackTier(ItemStack stack, FBTier tier) {
		if(stack.getTag() != null) {
			IItemModifiers itemMod = stack.getCapability(ItemModifiersProvider.ITEM_MODIFIERS_CAPABILITY).orElse(null);
			if(itemMod != null) {
				return FBTier.changeTier(tier, itemMod.getRarity(stack));
			}
			return tier;
		} else {
			return tier;
		}
	}

	/**
	 * Gets the tier of an ItemStack.
	 * This method should be used for non-IFBTieredItem only. For tiered items, use getStackTier(ItemStack, FBTier)
	 * @param stack The ItemStack.
	 * @return The ItemStack's tier.
	 */
	public static FBTier getItemTier(ItemStack stack) {
		FBTier tier = SPECIAL_VANILLA_ITEMS.getOrDefault(stack.getItem(), FBTier.COMMON);
		if(stack.getTag() != null) {
			IItemModifiers itemMod = stack.getCapability(ItemModifiersProvider.ITEM_MODIFIERS_CAPABILITY).orElse(null);
			if(itemMod != null) {
				return FBTier.changeTier(tier, itemMod.getRarity(stack));
			}
			return tier;
		} else {
			return tier;
		}
	}

}
