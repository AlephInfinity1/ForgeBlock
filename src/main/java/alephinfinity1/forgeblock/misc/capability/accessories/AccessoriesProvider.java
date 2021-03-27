package alephinfinity1.forgeblock.misc.capability.accessories;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class AccessoriesProvider implements ICapabilitySerializable<INBT> {
    @CapabilityInject(IAccessoriesData.class)
    public static final Capability<IAccessoriesData> ACCESSORIES_CAPABILITY = null;

    private LazyOptional<IAccessoriesData> defaultInstance = LazyOptional.of(ACCESSORIES_CAPABILITY::getDefaultInstance);

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == ACCESSORIES_CAPABILITY) {
            return defaultInstance.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public INBT serializeNBT() {
        return ACCESSORIES_CAPABILITY.getStorage().writeNBT(ACCESSORIES_CAPABILITY, this.defaultInstance.orElseThrow(NullPointerException::new), null);
    }

    @Override
    public void deserializeNBT(INBT nbt) {
        ACCESSORIES_CAPABILITY.getStorage().readNBT(ACCESSORIES_CAPABILITY, this.defaultInstance.orElseThrow(NullPointerException::new), null, nbt);
    }
}
