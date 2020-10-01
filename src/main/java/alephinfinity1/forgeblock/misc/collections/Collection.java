package alephinfinity1.forgeblock.misc.collections;

import java.util.Map;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Collection {
	private CollectionType type;
	private Map<Integer, CollectionRewards> rewards;
	private Map<Item, Integer> items;
	private String id;
	
	public Collection(CollectionType type, Map<Integer, CollectionRewards> rewards, Map<Item, Integer> items, String id) {
		this.type = type;
		this.rewards = rewards;
		this.items = items;
		this.id = id;
	}
	
	public int getItemStackValue(ItemStack stack) {
		for(Map.Entry<Item, Integer> item : items.entrySet()) {
			if(stack.getTag() != null)
				if(stack.getTag().getByte("Collected") == 1) return 0;
			if(stack.getItem().equals(item.getKey())) {
				stack.getOrCreateTag().putByte("Collected", (byte) 1);
				return item.getValue() * stack.getCount();
			}
		}
		return 0;
	}
	
	public boolean applyRewards(PlayerEntity player, int oldValue, int newValue) {
		boolean flag = false;
		if(player.isServerWorld()) {
			for(Map.Entry<Integer, CollectionRewards> reward : rewards.entrySet()) {
				if(newValue >= reward.getKey() && oldValue < reward.getKey()) {
					flag = true;
					reward.getValue().applyReward(player);
				}
			}
		}
		return flag;
	}
	
	public int getTier(int value) {
		int i = 0;
		for(Map.Entry<Integer, CollectionRewards> reward : rewards.entrySet()) {
			if(value >= reward.getKey()) ++i;
		}
		return i;
	}
	
	public String getID() {
		return id;
	}
	
}

