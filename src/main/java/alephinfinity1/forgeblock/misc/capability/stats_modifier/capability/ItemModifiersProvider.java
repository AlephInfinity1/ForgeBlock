package alephinfinity1.forgeblock.misc.capability.stats_modifier.capability;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class ItemModifiersProvider implements ICapabilitySerializable<INBT> {
	
	@CapabilityInject(IItemModifiers.class)
	public static final Capability<IItemModifiers> ITEM_MODIFIERS_CAPABILITY = null;
	
	private LazyOptional<IItemModifiers> defaultInstance = LazyOptional.of(ITEM_MODIFIERS_CAPABILITY::getDefaultInstance);

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		if(cap == ITEM_MODIFIERS_CAPABILITY) {
			return defaultInstance.cast();
		}
		return LazyOptional.empty();
	}
	
	@Override
	public INBT serializeNBT() {
		return ITEM_MODIFIERS_CAPABILITY.getStorage().writeNBT(ITEM_MODIFIERS_CAPABILITY, this.defaultInstance.orElseThrow(NullPointerException::new), null);
	}

	@Override
	public void deserializeNBT(INBT nbt) {
		ITEM_MODIFIERS_CAPABILITY.getStorage().readNBT(ITEM_MODIFIERS_CAPABILITY, this.defaultInstance.orElseThrow(NullPointerException::new), null, nbt);
	}
	
}
