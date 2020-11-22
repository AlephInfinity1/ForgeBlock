package alephinfinity1.forgeblock.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public interface IDamageAbilityItem extends IAbilityItem {
	
	/**
	 * Gets the base ability damage of an ItemStack.
	 * @param stack The ItemStack
	 * @return The base ability damage of the ItemStack. Player-insensitive.
	 */
	public float getAbilityDamage(ItemStack stack);
	
	/**
	 * Gets the base ability damage of an ItemStack. Player-sensitive.
	 * @param stack The ItemStack
	 * @param player The player holding this item.
	 * @return The base ability damage of the ItemStack.
	 */
	public float getAbilityDamage(ItemStack stack, PlayerEntity player);
	
	/**
	 * Gets the damage scaling of an itemstack of this item, in % of damage per intelligence.
	 * The result should be a decimal. E.g. if the damage increases by 1% per point of intelligence, return 0.01 instead of 1.
	 * @param stack An ItemStack of this item
	 * @return Damage scaling of the ItemStack
	 */
	public float getDamageScaling(ItemStack stack);
	
	/**
	 * Gets the damage scaling of an itemstack of this item, in % of damage per intelligence.
	 * Player-sensitive version.
	 * The result should be a decimal. E.g. if the damage increases by 1% per point of intelligence, return 0.01 instead of 1.
	 * @param stack An ItemStack of this item
	 * @param player The player holding this item.
	 * @return Damage scaling of the ItemStack
	 */
	public float getDamageScaling(ItemStack stack, PlayerEntity player);

}
