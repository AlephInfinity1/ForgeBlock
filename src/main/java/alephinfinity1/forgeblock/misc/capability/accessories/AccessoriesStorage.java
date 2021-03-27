package alephinfinity1.forgeblock.misc.capability.accessories;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class AccessoriesStorage implements Capability.IStorage<IAccessoriesData> {
    @Nullable
    @Override
    public INBT writeNBT(Capability<IAccessoriesData> capability, IAccessoriesData instance, Direction side) {
        return instance.writeNBT();
    }

    @Override
    public void readNBT(Capability<IAccessoriesData> capability, IAccessoriesData instance, Direction side, INBT nbt) {
        instance.readNBT(nbt);
    }
}
