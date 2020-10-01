package alephinfinity1.forgeblock.misc.collections;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class CollectionsData implements ICollectionsData {
	
	private Map<Collection, Integer> data = new HashMap<>();

	@Override
	public Map<Collection, Integer> getCollections() {
		return data;
	}

	@Override
	public void collectItem(ItemStack stack, PlayerEntity player) {
		for(Map.Entry<Collection, Integer> entry : data.entrySet()) {
			Collection col = entry.getKey();
			if(col.getItemStackValue(stack) != 0) {
				int value = col.getItemStackValue(stack);
				col.applyRewards(player, entry.getValue(), entry.getValue() + value);
				data.put(col, entry.getValue() + value);
			}
		}

	}

	@Override
	public int getAmount(Collection collection) {
		return data.get(collection);
	}

	@Override
	public int getTier(Collection collection) {
		return collection.getTier(data.get(collection));
	}

	@Override
	public void setAmount(Collection collection, int amount) {
		data.put(collection, amount);
	}

}
