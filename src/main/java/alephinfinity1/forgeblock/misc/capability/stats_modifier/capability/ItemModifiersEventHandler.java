package alephinfinity1.forgeblock.misc.capability.stats_modifier.capability;

import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

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
				hpb.putBoolean("Applied", true);
				modifiers.put(ModStatsModifiers.WOOD_SINGULARITY.get(), hpb);
				FBPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new ItemModifiersUpdatePacket(modifiers.getMap()));
			}
		}
		*/
	}

}
