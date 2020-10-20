package alephinfinity1.forgeblock.effect;

import alephinfinity1.forgeblock.init.ModEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class KnockbackEffect extends Effect {

	public KnockbackEffect(EffectType typeIn, int liquidColorIn) {
		super(typeIn, liquidColorIn);
	}
	
	@SubscribeEvent
	public static void onLivingKnockBack(LivingKnockBackEvent event) {
		if(!(event.getOriginalAttacker() instanceof LivingEntity)) return;
		else {
			LivingEntity attacker = (LivingEntity) event.getOriginalAttacker();
			int knockbackEffectAmplifier;
			if(attacker.getActivePotionEffect(ModEffects.KNOCKBACK) != null) {
				knockbackEffectAmplifier = attacker.getActivePotionEffect(ModEffects.KNOCKBACK).getAmplifier();
				event.setStrength((float) (event.getStrength() * (1.0D + 0.2D * (knockbackEffectAmplifier + 1))));
			}
		}
	}

}
