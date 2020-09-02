package alephinfinity1.forgeblock.misc.mana;

import alephinfinity1.forgeblock.attribute.FBAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ManaEventHandler {
	
	@SubscribeEvent
	public static void onPlayerLoggedIn(PlayerLoggedInEvent event) {
		PlayerEntity player = event.getPlayer();
		IMana mana = player.getCapability(ManaProvider.MANA_CAPABILITY).orElseThrow(NullPointerException::new);
		
		player.sendMessage(new StringTextComponent(Double.toString(mana.getMana())));
	}
	
	@SubscribeEvent
	public static void onTick(TickEvent.PlayerTickEvent event) {
		if(event.phase == Phase.END && !event.player.getEntityWorld().isRemote) {
			PlayerEntity player = event.player;
			IMana mana = player.getCapability(ManaProvider.MANA_CAPABILITY).orElseThrow(NullPointerException::new);
			double maxMana = player.getAttribute(FBAttributes.INTELLIGENCE).getValue() + 100;
			if(mana.getMana() >= maxMana) return;
			else {
				mana.increase(maxMana / 1000.0);
			}
			ManaProvider.MANA_CAPABILITY.getStorage().writeNBT(ManaProvider.MANA_CAPABILITY, mana, null);
			//player.sendMessage(new StringTextComponent(Double.toString(mana.getMana())));
		}
	}

}
