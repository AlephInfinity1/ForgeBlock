package alephinfinity1.forgeblock.effect;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.InstantEffect;
import net.minecraft.util.DamageSource;

public class InstantDamageEffect extends InstantEffect {

	public InstantDamageEffect(EffectType p_i50392_1_, int p_i50392_2_) {
		super(p_i50392_1_, p_i50392_2_);
	}
	
	@Override
	public void performEffect(LivingEntity living, int amplifier) {
		this.affectEntity(null, null, living, amplifier, 1.0D);
	}
	
	@Override
	public void affectEntity(@Nullable Entity source, @Nullable Entity indirectSource, LivingEntity entityLivingBaseIn, int amplifier, double health) {
		float damageAmount;
		switch(amplifier) {
		case 0:
			damageAmount = 5;
			break;
		case 1:
			damageAmount = 10;
			break;
		case 2:
			damageAmount = 15;
			break;
		case 3:
			damageAmount = 20;
			break;
		case 4:
			damageAmount = 25;
			break;
		case 5:
			damageAmount = 30;
			break;
		case 6:
			damageAmount = 40;
			break;
		case 7:
			damageAmount = 50;
			break;
		default:
			damageAmount = (amplifier - 2) * 10;
		}
		if(source == null) {
			entityLivingBaseIn.attackEntityFrom(DamageSource.MAGIC, (float) (damageAmount * health));
		} else {
			entityLivingBaseIn.attackEntityFrom(DamageSource.causeIndirectMagicDamage(source, indirectSource), (float) (damageAmount * health));
		}
	}

}
