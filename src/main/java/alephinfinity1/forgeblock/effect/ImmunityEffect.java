package alephinfinity1.forgeblock.effect;

import alephinfinity1.forgeblock.init.ModEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ImmunityEffect extends Effect {

	public ImmunityEffect(EffectType typeIn, int liquidColorIn) {
		super(typeIn, liquidColorIn);	
	}
	
	@SubscribeEvent
	public static void onLivingTakeDamage(LivingDamageEvent event) {
		LivingEntity living = event.getEntityLiving();
		if(living.getActivePotionEffect(ModEffects.IMMUNITY) != null) {
			event.setCanceled(true);
		}
	}
	
	@SubscribeEvent
	public static void onLivingKnockbacked(LivingKnockBackEvent event) {
		LivingEntity living = event.getEntityLiving();
		if(living.getActivePotionEffect(ModEffects.IMMUNITY) != null) {
			event.setCanceled(true);
		}
	}

}
