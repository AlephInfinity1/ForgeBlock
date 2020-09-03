package alephinfinity1.forgeblock.potion;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.PotionUtils;

public class FBPotionUtils extends PotionUtils {

	public static List<EffectInstance> getEffectsFromTag(@Nullable CompoundNBT tag) {
		List<EffectInstance> list = Lists.newArrayList();
		List<EffectInstance> editedList = Lists.newArrayList();
		list.addAll(getPotionTypeFromNBT(tag).getEffects());
		int amplifierModifier = tag.getByte("AmplifierModifier");
		int durationModifier = tag.getByte("DurationModifier");
		for(EffectInstance effect : list) {
			editedList.add(new EffectInstance(effect.getPotion(), effect.getDuration(), effect.getAmplifier() + amplifierModifier));
		}
		addCustomPotionEffectToList(tag, editedList);
		return editedList;
	}
}
