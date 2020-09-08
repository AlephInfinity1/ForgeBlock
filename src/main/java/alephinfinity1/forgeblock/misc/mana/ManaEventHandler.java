package alephinfinity1.forgeblock.misc.mana;

import java.text.DecimalFormat;

import alephinfinity1.forgeblock.attribute.FBAttributes;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ManaEventHandler {
	
	private static final Minecraft mc = Minecraft.getInstance(); 
	private static double manaValue;
	private static double maxManaValue;
	
	@SubscribeEvent
	public static void onPlayerLoggedIn(PlayerLoggedInEvent event) {
		PlayerEntity player = event.getPlayer();
		IMana mana = player.getCapability(ManaProvider.MANA_CAPABILITY).orElseThrow(NullPointerException::new);
		
		//player.sendMessage(new StringTextComponent(Double.toString(mana.getMana())));
		manaValue = mana.getMana();
	}
	
	@SubscribeEvent
	public static void onTick(TickEvent.PlayerTickEvent event) {
		if(event.phase == Phase.END && !event.player.getEntityWorld().isRemote) {
			PlayerEntity player = event.player;
			IMana mana = player.getCapability(ManaProvider.MANA_CAPABILITY).orElseThrow(NullPointerException::new);
			double maxMana = player.getAttribute(FBAttributes.INTELLIGENCE).getValue() + 100;
			maxManaValue = maxMana;
			if(mana.getMana() >= maxMana) return;
			else {
				mana.increase(maxMana / 1000.0);
			}
			ManaProvider.MANA_CAPABILITY.getStorage().writeNBT(ManaProvider.MANA_CAPABILITY, mana, null);
			manaValue = mana.getMana();
		}
	}
	
	@SubscribeEvent
	public static void renderMana(RenderGameOverlayEvent.Pre event) {
		
		int width = mc.getMainWindow().getScaledWidth();
		int height = mc.getMainWindow().getScaledHeight();
		
		if(event.getType() == ElementType.FOOD) {
			mc.fontRenderer.drawStringWithShadow((new DecimalFormat("#")).format(manaValue) + "/" + (new DecimalFormat("#")).format(maxManaValue), width / 2 + 25, height - 40, 0x5555FF);
		}
	}

}
