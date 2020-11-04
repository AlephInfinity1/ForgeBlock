package alephinfinity1.forgeblock.misc.coins;

import net.minecraft.nbt.DoubleNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class CoinsStorage implements IStorage<ICoins> {

	@Override
	public INBT writeNBT(Capability<ICoins> capability, ICoins instance, Direction side) {
		return DoubleNBT.valueOf(instance.getCoins());
	}

	@Override
	public void readNBT(Capability<ICoins> capability, ICoins instance, Direction side, INBT nbt) {
		instance.set(((DoubleNBT) nbt).getDouble());
	}

}
