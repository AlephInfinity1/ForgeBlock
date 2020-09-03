package alephinfinity1.forgeblock.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.potion.AbsorptionEffect;
import net.minecraft.potion.EffectType;

public class FBAbsorptionEffect extends AbsorptionEffect {

	public FBAbsorptionEffect(EffectType p_i50395_1_, int p_i50395_2_) {
		super(p_i50395_1_, p_i50395_2_);
	}

	@Override
	public void removeAttributesModifiersFromEntity(LivingEntity entityLivingBaseIn, AbstractAttributeMap attributeMapIn, int amplifier) {
		entityLivingBaseIn.setAbsorptionAmount(entityLivingBaseIn.getAbsorptionAmount() - this.getAbsorptionAmountForAmplifier(amplifier));
		super.removeAttributesModifiersFromEntity(entityLivingBaseIn, attributeMapIn, amplifier);
	}

	@Override
	public void applyAttributesModifiersToEntity(LivingEntity entityLivingBaseIn, AbstractAttributeMap attributeMapIn, int amplifier) {
		entityLivingBaseIn.setAbsorptionAmount(entityLivingBaseIn.getAbsorptionAmount() + this.getAbsorptionAmountForAmplifier(amplifier));
		super.applyAttributesModifiersToEntity(entityLivingBaseIn, attributeMapIn, amplifier);
	}
	
	private float getAbsorptionAmountForAmplifier(int amplifier) {
		switch(amplifier) {
		case 0:
			return 20.0f;
		case 1:
			return 40.0f;
		case 2:
			return 60.0f;
		case 3:
			return 80.0f;
		case 4:
			return 100.0f;
		case 5:
			return 150.0f;
		case 6:
			return 200.0f;
		case 7:
			return 300.0f;
		default:
			return 0.0f;
		}
	}

}
