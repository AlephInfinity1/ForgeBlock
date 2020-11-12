package alephinfinity1.forgeblock.network;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

import alephinfinity1.forgeblock.init.ModRegistries;
import alephinfinity1.forgeblock.misc.stats_modifier.AbstractStatsModifier;
import alephinfinity1.forgeblock.misc.stats_modifier.capability.IItemModifiers;
import alephinfinity1.forgeblock.misc.stats_modifier.capability.ItemModifiersProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkEvent;

public class ItemModifiersUpdatePacket {
	
	private Map<AbstractStatsModifier, CompoundNBT> modifiers = new HashMap<>();
	
	ItemModifiersUpdatePacket(final PacketBuffer buf) {
		short length = buf.readShort();
		for(int i = 0; i < length; i++) {
			ResourceLocation rl = buf.readResourceLocation();
			AbstractStatsModifier type = ModRegistries.STATS_MODIFIER.getValue(rl);
			CompoundNBT nbt = buf.readCompoundTag();
			modifiers.put(type, nbt);
		}
	}
	
	public ItemModifiersUpdatePacket(Map<AbstractStatsModifier, CompoundNBT> map) {
		this.modifiers = map;
	}
	
	void encode(PacketBuffer buf) {
		buf.writeShort(modifiers.size());
		Set<Map.Entry<AbstractStatsModifier, CompoundNBT>> entrySet = modifiers.entrySet();
		for(Map.Entry<AbstractStatsModifier, CompoundNBT> entry : entrySet) {
			buf.writeResourceLocation(entry.getKey().getRegistryName());
			buf.writeCompoundTag(entry.getValue());
		}
	}
	
	@SuppressWarnings("resource")
	public static void handle(ItemModifiersUpdatePacket msg, Supplier<NetworkEvent.Context> ctx) {
		if(ctx.get().getDirection().getReceptionSide().isClient()) {
			ctx.get().enqueueWork(() -> {
				ClientPlayerEntity player = Minecraft.getInstance().player;
				ItemStack stack = player.getHeldItemMainhand();
				IItemModifiers itemModifiers = stack.getCapability(ItemModifiersProvider.ITEM_MODIFIERS_CAPABILITY).orElse(null);
				if(itemModifiers != null) {
					itemModifiers.setMap(msg.modifiers);
				}
			});
		}
	}

}
