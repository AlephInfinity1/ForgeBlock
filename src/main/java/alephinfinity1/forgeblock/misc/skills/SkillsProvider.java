package alephinfinity1.forgeblock.misc.skills;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class SkillsProvider implements ICapabilitySerializable<INBT> {
	
	@CapabilityInject(ISkills.class)
	public static final Capability<ISkills> SKILLS_CAPABILITY = null;
	
	private LazyOptional<ISkills> defaultInstance = LazyOptional.of(SKILLS_CAPABILITY::getDefaultInstance);

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		if(cap == SKILLS_CAPABILITY) {
			return defaultInstance.cast();
		}
		return null;
	}

	@Override
	public INBT serializeNBT() {
		return SKILLS_CAPABILITY.getStorage().writeNBT(SKILLS_CAPABILITY, this.defaultInstance.orElseThrow(NullPointerException::new), null);
	}

	@Override
	public void deserializeNBT(INBT nbt) {
		SKILLS_CAPABILITY.getStorage().readNBT(SKILLS_CAPABILITY, this.defaultInstance.orElseThrow(NullPointerException::new), null, nbt);
	}

}
