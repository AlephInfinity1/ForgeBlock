package alephinfinity1.forgeblock.effect;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.InstantEffect;

public class InstantHealthEffect extends InstantEffect {

	public InstantHealthEffect(EffectType p_i50392_1_, int p_i50392_2_) {
		super(p_i50392_1_, p_i50392_2_);
	}
	
	@Override
	public void performEffect(LivingEntity living, int amplifier) {
		this.affectEntity(null, null, living, amplifier, 1.0D);
	}
	
	@Override
	public void affectEntity(@Nullable Entity source, @Nullable Entity indirectSource, LivingEntity entityLivingBaseIn, int amplifier, double health) {
		float healAmount;
		switch(amplifier) {
		case 0:
			healAmount = 20;
			break;
		case 1:
			healAmount = 50;
			break;
		case 2:
			healAmount = 100;
			break;
		case 3:
			healAmount = 150;
			break;
		case 4:
			healAmount = 200;
			break;
		case 5:
			healAmount = 300;
			break;
		case 6:
			healAmount = 400;
			break;
		case 7:
			healAmount = 500;
			break;
		default:
			healAmount = (amplifier - 2) * 100;
		}
		entityLivingBaseIn.heal((float) (healAmount * health));
	}

}
