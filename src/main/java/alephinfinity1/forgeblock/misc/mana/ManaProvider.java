package alephinfinity1.forgeblock.misc.mana;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class ManaProvider implements ICapabilitySerializable<INBT> {

	@CapabilityInject(IMana.class)
	public static final Capability<IMana> MANA_CAPABILITY = null;
	
	private IMana defaultInstance = MANA_CAPABILITY.getDefaultInstance();
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		if(cap == MANA_CAPABILITY) {
			return LazyOptional.of(() -> cap.getDefaultInstance());
		}
		return null;
	}

	@Override
	public INBT serializeNBT() {
		return MANA_CAPABILITY.getStorage().writeNBT(MANA_CAPABILITY, this.defaultInstance, null);
	}

	@Override
	public void deserializeNBT(INBT nbt) {
		MANA_CAPABILITY.getStorage().readNBT(MANA_CAPABILITY, this.defaultInstance, null, nbt);
	}

}
