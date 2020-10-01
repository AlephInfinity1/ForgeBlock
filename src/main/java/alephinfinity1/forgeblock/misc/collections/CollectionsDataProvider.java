package alephinfinity1.forgeblock.misc.collections;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class CollectionsDataProvider implements ICapabilitySerializable<INBT> {

	@CapabilityInject(ICollectionsData.class)
	public static final Capability<ICollectionsData> COLLECTIONS_CAPABILITY = null;
	
	private LazyOptional<ICollectionsData> defaultInstance = LazyOptional.of(COLLECTIONS_CAPABILITY::getDefaultInstance);
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		if(cap == COLLECTIONS_CAPABILITY) {
			return defaultInstance.cast();
		}
		return LazyOptional.empty();
	}

	@Override
	public INBT serializeNBT() {
		return COLLECTIONS_CAPABILITY.getStorage().writeNBT(COLLECTIONS_CAPABILITY, this.defaultInstance.orElseThrow(NullPointerException::new), null);
	}

	@Override
	public void deserializeNBT(INBT nbt) {
		COLLECTIONS_CAPABILITY.getStorage().readNBT(COLLECTIONS_CAPABILITY, this.defaultInstance.orElseThrow(NullPointerException::new), null, nbt);
	}

}
