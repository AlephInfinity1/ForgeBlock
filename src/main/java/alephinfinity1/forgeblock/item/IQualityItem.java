package alephinfinity1.forgeblock.item;

import net.minecraft.item.ItemStack;

/**
 * HSB uses an item quality mechanic to determine the stats of an item. ForgeBlock mimics this.<br>
 * The higher the quality, the better stats the item would have. Max-quality
 * items also gain a +1 rarity boost.<br>
 * All item qualities are out of <strong>50</strong>.<br>
 * Item stats also differ from the floor that they're dropped on.<br>
 * See <a href="https://hypixel.net/threads/dungeon-item-quality-mechanics.3243497">this
 * forums post</a> for more info.
 */
public interface IQualityItem {
	
	/**
	 * The minimum quality of an item.
	 */
	int MIN_QUALITY = 0;
	
	/**
	 * The maximum quality of an item.
	 */
	int MAX_QUALITY = 50;
	
	/**
	 * Returns the quality of an ItemStack, as an int.
	 * @param stack The ItemStack of a particular item type.
	 * @return The ItemStack's numeric quality.
	 */
	public int getQuality(ItemStack stack);
	
	/**
	 * Sets the quality of an ItemStack.
	 * @param stack The ItemStack whose quality is to be set.
	 * @param quality The new quality.
	 */
	public void setQuality(ItemStack stack, int quality);
	
	/**
	 * Gets the floor this ItemStack is dropped on.
	 * @param stack The ItemStack of a particular item type.
	 * @return The ItemStack's floor.
	 */
	public int getFloor(ItemStack stack);
	
	/**
	 * Sets the floor this ItemStack is dropped on.
	 * @param stack The ItemStack whose floor is to be set.
	 * @param floor The new floor.
	 */
	public void setFloor(ItemStack stack, int floor);

}
