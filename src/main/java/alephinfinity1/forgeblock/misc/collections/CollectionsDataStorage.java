package alephinfinity1.forgeblock.misc.collections;

import java.util.HashMap;
import java.util.Map;

import alephinfinity1.forgeblock.init.ModRegistries;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class CollectionsDataStorage implements IStorage<ICollectionsData> {

	@Override
	public INBT writeNBT(Capability<ICollectionsData> capability, ICollectionsData instance,
			Direction side) {
		ListNBT list = new ListNBT();
		Map<FBCollection, Integer> data = instance.getCollections();
		for (Map.Entry<FBCollection, Integer> entry : data.entrySet()) {
			CompoundNBT nbt = new CompoundNBT();
			nbt.putString("id", entry.getKey().getRegistryName().toString());
			nbt.putInt("value", entry.getValue());
			list.add(nbt);
		}
		return list;
	}

	//FIXME Incomplete as of now.
	@Override
	public void readNBT(Capability<ICollectionsData> capability, ICollectionsData instance,
			Direction side, INBT nbt) {
		Map<FBCollection, Integer> data = new HashMap<>();
		if(nbt instanceof ListNBT) {
			ListNBT list = (ListNBT) nbt;
			for(INBT entry : list) {
				if(!(entry instanceof CompoundNBT)) continue;
				CompoundNBT cpd = (CompoundNBT) entry.copy();
				ResourceLocation resloc = new ResourceLocation(cpd.getString("id"));
				Integer value = cpd.getInt("value");
				data.put(ModRegistries.COLLECTION.getValue(resloc), value);
			}
		}
		instance.setCollections(data);
	}

}
