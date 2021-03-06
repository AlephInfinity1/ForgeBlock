package alephinfinity1.forgeblock.misc.capability.skills;

import java.util.Map;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class SkillsStorage implements IStorage<ISkills> {

	@Override
	public INBT writeNBT(Capability<ISkills> capability, ISkills instance, Direction side) {
		Map<SkillType, SkillData> data = instance.getData();
		ListNBT list = new ListNBT();
		for(Map.Entry<SkillType, SkillData> entry : data.entrySet()) {
			CompoundNBT nbt = new CompoundNBT();
			nbt.putString("SkillType", entry.getKey().getID());
			nbt.putInt("Level", entry.getValue().getLevel());
			nbt.putDouble("Progress", entry.getValue().getAbsoluteProgress());
			list.add(nbt);
		}
		
		return list;
	}

	@Override
	public void readNBT(Capability<ISkills> capability, ISkills instance, Direction side, INBT nbt) {
		if(!(nbt instanceof ListNBT)) {
			throw new IllegalArgumentException("NBT for skill capability must be a ListNBT, something must be wrong.");
		}
		
		ListNBT list = (ListNBT) nbt;
		for(INBT skillData : list) {
			if(!(skillData instanceof CompoundNBT)) {
				throw new IllegalArgumentException("SkillData is not a CompoundNBT");
			}
			CompoundNBT compound = (CompoundNBT) skillData;
			SkillType type = SkillType.getSkillTypeByID(compound.getString("SkillType"));
			instance.setLevel(type, compound.getInt("Level"));
			instance.setProgress(type, compound.getDouble("Progress"));
		}
		
	}

}
