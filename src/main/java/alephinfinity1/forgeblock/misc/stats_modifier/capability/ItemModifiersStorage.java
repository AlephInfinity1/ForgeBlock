package alephinfinity1.forgeblock.misc.stats_modifier.capability;

import java.util.Map;
import java.util.Set;

import alephinfinity1.forgeblock.init.ModRegistries;
import alephinfinity1.forgeblock.misc.stats_modifier.AbstractStatsModifier;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class ItemModifiersStorage implements IStorage<IItemModifiers> {

	@Override
	public INBT writeNBT(Capability<IItemModifiers> capability, IItemModifiers instance, Direction side) {
		Map<AbstractStatsModifier, CompoundNBT> map = instance.getMap();
		ListNBT nbt = new ListNBT();
		Set<Map.Entry<AbstractStatsModifier, CompoundNBT>> set = map.entrySet();
		for(Map.Entry<AbstractStatsModifier, CompoundNBT> entry : set) {
			CompoundNBT compound = new CompoundNBT();
			compound.putString("id", entry.getKey().getRegistryName().toString());
			compound.put("value", entry.getValue());
			nbt.add(compound);
		}
		return nbt;
	}

	@Override
	public void readNBT(Capability<IItemModifiers> capability, IItemModifiers instance, Direction side, INBT nbt) {
		if(!(nbt instanceof ListNBT)) return;
		instance.clear();
		ListNBT list = (ListNBT) nbt;
		for(INBT entry : list) {
			if(!(entry instanceof CompoundNBT)) continue;
			CompoundNBT compound = (CompoundNBT) entry;
			ResourceLocation rl = new ResourceLocation(compound.getString("id"));
			CompoundNBT value = compound.getCompound("value");
			instance.put(ModRegistries.STATS_MODIFIER.getValue(rl), value);
		}
	}

}
