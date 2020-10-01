package alephinfinity1.forgeblock.misc.collections;

import java.util.Map;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public interface ICollectionsData {
	public Map<Collection, Integer> getCollections();
	public void collectItem(ItemStack stack, PlayerEntity player);
	public int getAmount(Collection collection);
	public int getTier(Collection collection);
	public void setAmount(Collection collection, int amount);
}
