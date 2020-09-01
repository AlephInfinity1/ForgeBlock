package alephinfinity1.forgeblock.misc.mana;

import net.minecraft.nbt.DoubleNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class ManaStorage implements IStorage<IMana> {

	@Override
	public INBT writeNBT(Capability<IMana> capability, IMana instance, Direction side) {
		return DoubleNBT.valueOf(instance.getMana());
	}

	@Override
	public void readNBT(Capability<IMana> capability, IMana instance, Direction side, INBT nbt) {
		instance.set(((DoubleNBT) nbt).getDouble());
	}

}
