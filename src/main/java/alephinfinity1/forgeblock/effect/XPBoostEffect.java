package alephinfinity1.forgeblock.effect;

import alephinfinity1.forgeblock.init.ModEffects;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/*
 * Note: this is the vanilla XP boost effect. It does not affect skill XP.
 *
 * When debugging, keep note that there is a degree of randomness to this effect. E.g. gaining 5 XP through 
 * killing a mob (2 1-XP orbs, 1 3-XP orb) under XP Boost IV may give anywhere between 5–8 XP, as opposed to
 * the expected 6 XP. Expectancy value is still 6, though.
 * 
 * Also, don't expect a big change in XP gained. Even at Lvl 256, will still only give a +12800% boost. Enough
 * to bring up 75% of a level from a zombie, but not enough to reach Lv100 in one hit.
 */
@Mod.EventBusSubscriber
public class XPBoostEffect extends Effect {

	public XPBoostEffect(EffectType typeIn, int liquidColorIn) {
		super(typeIn, liquidColorIn);
	}
	
	//Note: the event is PickupXp but not XpChange so that it doesn't affect XP given through commands.
	@SubscribeEvent
	public static void onPlayerPickupXPOrb(PlayerXpEvent.PickupXp event) {
		ExperienceOrbEntity orb = event.getOrb();
		PlayerEntity player = event.getPlayer();
		if(player.getActivePotionEffect(ModEffects.EXPERIENCE_BOOST) == null) return;
		float xpMultiplier = 0.05f + 0.05f * player.getActivePotionEffect(ModEffects.EXPERIENCE_BOOST).getAmplifier(); //Only add the bonus part, the event is not cancelled.
		int originalValue = orb.getXpValue();
		float value = originalValue * xpMultiplier;
		player.giveExperiencePoints(MathHelper.floor(value));
		//If fractional value, chance of giving 1 more XP.
		if(MathHelper.frac((double) value) > 0) {
			if(MathHelper.frac((double) value) > Math.random()) {
				player.giveExperiencePoints(1);
			}
		}
	}

}
