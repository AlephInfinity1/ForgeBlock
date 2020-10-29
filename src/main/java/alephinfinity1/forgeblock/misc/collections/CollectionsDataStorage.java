package alephinfinity1.forgeblock.misc.collections;

import java.util.Map;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class CollectionsDataStorage implements IStorage<ICollectionsData> {

  @Override
  public INBT writeNBT(Capability<ICollectionsData> capability, ICollectionsData instance,
      Direction side) {
    ListNBT list = new ListNBT();
    Map<Collection, Integer> data = instance.getCollections();
    for (Map.Entry<Collection, Integer> entry : data.entrySet()) {
      CompoundNBT nbt = new CompoundNBT();

    }
    return list;
  }

  @Override
  public void readNBT(Capability<ICollectionsData> capability, ICollectionsData instance,
      Direction side, INBT nbt) {
  }

}
