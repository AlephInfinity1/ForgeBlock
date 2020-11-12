package alephinfinity1.forgeblock.misc.stats_modifier.capability;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import com.google.common.collect.Multimap;

import alephinfinity1.forgeblock.misc.stats_modifier.AbstractStatsModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class ItemModifiers implements IItemModifiers {
	
	private Map<AbstractStatsModifier, CompoundNBT> map = new HashMap<>();
	
	@Override
	public Map<AbstractStatsModifier, CompoundNBT> getMap() {	
		return map;
	}
	
	@Override
	public void setMap(Map<AbstractStatsModifier, CompoundNBT> map) {
		this.map = map;
	}

	@Override
	public CompoundNBT getData(AbstractStatsModifier modifier) {
		return map.get(modifier);
	}

	@Override
	public void put(AbstractStatsModifier modifier, CompoundNBT nbt) {
		map.put(modifier, nbt);
	}

	@Override
	@Nullable
	public CompoundNBT modifyData(AbstractStatsModifier modifier, CompoundNBT nbt) {
		return map.put(modifier, nbt);
	}

	@Override
	public CompoundNBT remove(AbstractStatsModifier modifier) {
		return map.remove(modifier);
	}

	@Override
	public boolean isPresent(AbstractStatsModifier modifier) {
		return map.containsKey(modifier);
	}

	@Override
	public Multimap<String, AttributeModifier> getModifiers(ItemStack stack) {
		Builder<String, AttributeModifier> builder = ImmutableMultimap.builder();
		Set<Map.Entry<AbstractStatsModifier, CompoundNBT>> data = map.entrySet();
		for(Map.Entry<AbstractStatsModifier, CompoundNBT> entry : data) {
			builder.putAll(entry.getKey().getModifiers(stack, entry.getValue()));
		}
		return builder.build();
	}
	
	@Override
	public int getRarity() {
		int changes = 0;
		Set<Map.Entry<AbstractStatsModifier, CompoundNBT>> data = map.entrySet();
		for(Map.Entry<AbstractStatsModifier, CompoundNBT> entry : data) {
			changes += entry.getKey().getRarityChange();
		}
		return changes;
	}

	@Override
	public void clear() {	
		map.clear();
	}
	
}
