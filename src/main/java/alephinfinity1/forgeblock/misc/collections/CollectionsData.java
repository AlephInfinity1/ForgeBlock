package alephinfinity1.forgeblock.misc.collections;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class CollectionsData implements ICollectionsData {
	
	private Map<FBCollection, Integer> data = new HashMap<>();

	@Override
	public Map<FBCollection, Integer> getCollections() {
		return data;
	}

	@Override
	public void collectItem(ItemStack stack, PlayerEntity player) {
		for(Map.Entry<FBCollection, Integer> entry : data.entrySet()) {
			FBCollection col = entry.getKey();
			if(col.getItemStackValue(stack) != 0) {
				int value = col.getItemStackValue(stack);
				col.applyRewards(player, entry.getValue(), entry.getValue() + value);
				data.put(col, entry.getValue() + value);
			}
		}

	}

	@Override
	public int getAmount(FBCollection fBCollection) {
		return data.get(fBCollection);
	}

	@Override
	public int getTier(FBCollection fBCollection) {
		return fBCollection.getTier(data.get(fBCollection));
	}

	@Override
	public void setAmount(FBCollection fBCollection, int amount) {
		data.put(fBCollection, amount);
	}

	/**
	 * Deep copies the map.
	 */
	@Override
	public void setCollections(Map<FBCollection, Integer> map) {	
		this.data.clear();
		this.data.putAll(map);
	}

}
