package alephinfinity1.forgeblock.effect;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.DamageSource;

public class VenomousEffect extends Effect {

	public VenomousEffect(EffectType typeIn, int liquidColorIn) {
		super(typeIn, liquidColorIn);
	}
	
	@Override
	public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {
		float damageAmount = 5.0f * (amplifier + 1);
		
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
	
	@Override
	public double getAttributeModifierAmount(int amplifier, AttributeModifier modifier) {
		return amplifier * 5.0d / 1000.0d;
	}

}
