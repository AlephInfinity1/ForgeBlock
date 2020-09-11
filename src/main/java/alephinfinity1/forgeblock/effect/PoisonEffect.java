package alephinfinity1.forgeblock.effect;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.DamageSource;

public class PoisonEffect extends Effect {

	public PoisonEffect(EffectType typeIn, int liquidColorIn) {
		super(typeIn, liquidColorIn);
	}
	
	@Override
	public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {
		float damageAmount;
		switch(amplifier) {
		case 0:
			damageAmount = 10;
			break;
		case 1:
			damageAmount = 20;
			break;
		case 2:
			damageAmount = 40;
			break;
		case 3:
			damageAmount = 60;
			break;
		default:
			damageAmount = 0;
			break;
		}
		
		entityLivingBaseIn.attackEntityFrom(DamageSource.MAGIC, damageAmount);
	}
	
	@Override
	public void affectEntity(@Nullable Entity source, @Nullable Entity indirectSource, LivingEntity entityLivingBaseIn, int amplifier, double health) {
		this.performEffect(entityLivingBaseIn, amplifier);
	}
	
	@Override
	public boolean isReady(int duration, int amplifier) {
		return duration % 20 == 0;
	}

}
