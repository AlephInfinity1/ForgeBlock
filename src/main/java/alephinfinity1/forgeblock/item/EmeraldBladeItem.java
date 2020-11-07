package alephinfinity1.forgeblock.item;

import alephinfinity1.forgeblock.misc.tier.FBTier;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class EmeraldBladeItem extends FBSwordItem {

	public EmeraldBladeItem(Properties props, FBTier tier, double attackDamageIn, double strengthIn,
			double critChanceIn, double critDamageIn) {
		super(props, tier, attackDamageIn, strengthIn, critChanceIn, critDamageIn);
	}
	
	@SubscribeEvent
	public static void onPlayerUpdate(LivingUpdateEvent event) {
		;
	}

}
