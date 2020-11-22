package alephinfinity1.forgeblock.misc.collections;

import java.util.Map;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public interface ICollectionsData {
	public Map<FBCollection, Integer> getCollections();
	public void setCollections(Map<FBCollection, Integer> map);
	public void collectItem(ItemStack stack, PlayerEntity player);
	public int getAmount(FBCollection fBCollection);
	public int getTier(FBCollection fBCollection);
	public void setAmount(FBCollection fBCollection, int amount);
}
