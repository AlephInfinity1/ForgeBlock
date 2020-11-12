package alephinfinity1.forgeblock.misc.stats_modifier.capability;

import alephinfinity1.forgeblock.ForgeBlock;
import alephinfinity1.forgeblock.init.ModStatsModifiers;
import alephinfinity1.forgeblock.item.FBSwordItem;
import alephinfinity1.forgeblock.network.FBPacketHandler;
import alephinfinity1.forgeblock.network.ItemModifiersUpdatePacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.PacketDistributor;

@Mod.EventBusSubscriber
public class ItemModifiersEventHandler {
	
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		/*
		if(event.side.equals(LogicalSide.CLIENT)) return;
		PlayerEntity player = event.player;
		if(player.getHeldItemMainhand().getItem() instanceof FBSwordItem) {
			ItemStack stack = player.getHeldItemMainhand();
			IItemModifiers modifiers = stack.getCapability(ItemModifiersProvider.ITEM_MODIFIERS_CAPABILITY).orElse(null);
			if(modifiers == null) {
				ForgeBlock.LOGGER.error("Error: IItemModifiers not attached to FBSwordItem");
			} else {
				CompoundNBT hpb = new CompoundNBT();
				hpb.putShort("Amount", (short) 1);
				modifiers.put(ModStatsModifiers.HOT_POTATO_BOOK.get(), hpb);
				FBPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new ItemModifiersUpdatePacket(modifiers.getMap()));
			}
		}
		*/
	}

}
