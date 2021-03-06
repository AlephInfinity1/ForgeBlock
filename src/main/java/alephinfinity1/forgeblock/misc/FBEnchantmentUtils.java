package alephinfinity1.forgeblock.misc;

import alephinfinity1.forgeblock.enchantment.IFBEnchantment;
import alephinfinity1.forgeblock.misc.capability.skills.SkillType;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class FBEnchantmentUtils {
	
	/**
	 * Gets the effective enchantment level of an itemstack, when wielded by a particular player. <br>
	 * Takes into account the player's {@link SkillType#ENCHANTING} level, returning lower levels or 0 if <br>
	 * the player is incapable of using higher levels of the given enchantment.
	 * @param player The player wielding the itemstack
	 * @param stack The stack
	 * @param ench The type of enchantment
	 * @return The highest level of the enchantment that the player can use, or 0 if the player can use none.
	 */
	public static int getEffectiveEnchantmentLevel(PlayerEntity player, ItemStack stack, Enchantment ench) {
		int itemLvl = EnchantmentHelper.getEnchantmentLevel(ench, stack);
		if (!(ench instanceof IFBEnchantment)) { //If enchantment does not have a requirement, return to vanilla calculation.
			return itemLvl;
		}
		IFBEnchantment fbench = (IFBEnchantment) ench;
		while (!fbench.canUse(player, itemLvl) && itemLvl > 0) {
			itemLvl--;
		}
		return itemLvl;
	}

}
