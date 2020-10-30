package alephinfinity1.forgeblock.item;

import java.util.Random;

import alephinfinity1.forgeblock.init.ModEnchantments;
import alephinfinity1.forgeblock.misc.tier.FBTier;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class StonkItem extends FBPickaxeItem {

	public StonkItem(Properties props, FBTier tier, double damage, int harvestLevel, float destroySpeed, double yield) {
		super(props, tier, damage, harvestLevel, destroySpeed, yield);
	}
	
	@Override
	public ItemStack getDefaultInstance() {
		ItemStack stack = new ItemStack(this);
		stack.addEnchantment(Enchantments.EFFICIENCY, 6);
		stack.addEnchantment(ModEnchantments.TELEKINESIS.get(), 1);
		return stack;
	}
	
	@SubscribeEvent
	public static void onPlayerBreakBlock(BlockEvent.BreakEvent event) {
		PlayerEntity player = event.getPlayer();
		ItemStack heldItem = player.getHeldItemMainhand();
		if(heldItem.getItem() instanceof StonkItem && event.getState().getBlock().equals(Blocks.END_STONE)) {
			Random random = new Random();
			event.setExpToDrop(event.getExpToDrop() + random.nextInt(3));
		}
	}
	
	@Override
	public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
		if (this.isInGroup(group)) {
			items.add(this.getDefaultInstance());
		}
	}

}
