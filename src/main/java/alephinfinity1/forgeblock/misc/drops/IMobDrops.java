package alephinfinity1.forgeblock.misc.drops;

import java.util.List;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public interface IMobDrops {
	public List<DropData> getDrops();
	public List<ItemStack> drop(PlayerEntity player);
}
