package alephinfinity1.forgeblock.misc.coins;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class CoinsProvider implements ICapabilitySerializable<INBT> {
	
	@CapabilityInject(ICoins.class)
	public static final Capability<ICoins> COINS_CAPABILITY = null;
	
	private LazyOptional<ICoins> defaultInstance = LazyOptional.of(COINS_CAPABILITY::getDefaultInstance);

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		if(cap == COINS_CAPABILITY) {
			return defaultInstance.cast();
		}
		return LazyOptional.empty();
	}

	@Override
	public INBT serializeNBT() {
		return COINS_CAPABILITY.getStorage().writeNBT(COINS_CAPABILITY, this.defaultInstance.orElseThrow(NullPointerException::new), null);
	}

	@Override
	public void deserializeNBT(INBT nbt) {
		COINS_CAPABILITY.getStorage().readNBT(COINS_CAPABILITY, this.defaultInstance.orElseThrow(NullPointerException::new), null, nbt);
	}

}
