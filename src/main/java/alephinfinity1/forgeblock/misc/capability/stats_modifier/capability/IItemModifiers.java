package alephinfinity1.forgeblock.misc.capability.stats_modifier.capability;

import java.util.Map;

import javax.annotation.Nullable;

import com.google.common.collect.Multimap;

import alephinfinity1.forgeblock.misc.capability.stats_modifier.AbstractStatsModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

/**
 * A capability that stores all the {@link AbstractStatsModifier} applied to an item.
 */
public interface IItemModifiers {
	
	/**
	 * Get the modifiers map.
	 * @return The map.
	 */
	public Map<AbstractStatsModifier, CompoundNBT> getMap();
	
	/**
	 * Sets the modifier map.
	 * @param map The map to be set.
	 */
	public void setMap(Map<AbstractStatsModifier, CompoundNBT> map);
	
	/**
	 * Put the AbstractStatsModifier and CompoundNBT pair into the modifiers map.
	 * @param modifier
	 * @param nbt
	 */
	public void put(AbstractStatsModifier modifier, CompoundNBT nbt);
	
	/**
	 * Get the CompoundNBT corresponding to a given {@link AbstractStatsModifier}
	 * @param modifier The stats modifier
	 * @return the corresponding {@link CompoundNBT}
	 */
	public CompoundNBT getData(AbstractStatsModifier modifier);
	
	/**
	 * Modify the data of an {@link AbstractStatsModifier}
	 * @param modifier The specified AbstractStatsModifier
	 * @param nbt The NBT to be appended
	 * @return The old NBT data that is linked to the modifier, or {@code null} if 
	 * modifier does not already exist in map.
	 */
	@Nullable
	public CompoundNBT modifyData(AbstractStatsModifier modifier, CompoundNBT nbt);
	
	/**
	 * Remove the specified modifier from the modifiers map.
	 * @param modifier The {@link AbstractStatsModifier} to be removed.
	 * @return The corresponding {@link CompoundNBT} that is stored with the modifier.
	 */
	public CompoundNBT remove(AbstractStatsModifier modifier);
	
	/**
	 * Check if a specified {@link AbstractStatsModifier} exists in the map.
	 * @param modifier The specified {@code AbstractStatsModifier}
	 * @return Whether the modifier exists.
	 */
	public boolean isPresent(AbstractStatsModifier modifier);
	
	/**
	 * Get all the modifiers provided from stats modifiers.
	 * @param stack The ItemStack
	 * @return All extra stats modifiers that originate from StatsModifier s.
	 */
	public Multimap<String, AttributeModifier> getModifiers(ItemStack stack);
	
	/**
	 * Get the number of rarity changes.
	 * @return The number of rarity changes.
	 */
	public int getRarity(ItemStack stack);
	
	/**
	 * Clear the modifier map.
	 */
	public void clear();

}
