package alephinfinity1.forgeblock.misc.mana;

import java.text.DecimalFormat;

import com.mojang.blaze3d.systems.RenderSystem;

import alephinfinity1.forgeblock.ForgeBlock;
import alephinfinity1.forgeblock.attribute.FBAttributes;
import alephinfinity1.forgeblock.network.FBPacketHandler;
import alephinfinity1.forgeblock.network.ManaUpdatePacket;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.PacketDistributor;

@Mod.EventBusSubscriber
public class ManaEventHandler {
	
	private static final Minecraft mc = Minecraft.getInstance(); 
	private static double manaValue;
	private static double maxManaValue;
	private static int tickElapsed = 0;
	
	@SubscribeEvent
	public static void onTick(TickEvent.PlayerTickEvent event) {
		if(event.phase == Phase.END && !event.player.getEntityWorld().isRemote) {
			++tickElapsed;
			PlayerEntity player = event.player;
			IMana mana = player.getCapability(ManaProvider.MANA_CAPABILITY).orElseThrow(NullPointerException::new);
			double maxMana = player.getAttribute(FBAttributes.INTELLIGENCE).getValue() + 100;
			if(tickElapsed % 20 == 0) {
				if(mana.getMana() >= maxMana) return;
				else {
					mana.increase(Math.min(maxMana / 50.0 + player.getAttribute(FBAttributes.MANA_REGEN).getValue(), maxMana - mana.getMana()));
				}
				ManaProvider.MANA_CAPABILITY.getStorage().writeNBT(ManaProvider.MANA_CAPABILITY, mana, null);
				
				FBPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new ManaUpdatePacket(mana.getMana()));
			}
		}
		
	}
	
	@SubscribeEvent
	public static void renderMana(RenderGameOverlayEvent.Pre event) {
		
		int width = mc.getMainWindow().getScaledWidth();
		int height = mc.getMainWindow().getScaledHeight();
		
		PlayerEntity player = ForgeBlock.MINECRAFT.player;
		IMana mana;
		manaValue = 0;
		maxManaValue = 100;
		
		try {
			mana = player.getCapability(ManaProvider.MANA_CAPABILITY).orElseThrow(NullPointerException::new);
			manaValue = mana.getMana();
			maxManaValue = player.getAttribute(FBAttributes.INTELLIGENCE).getValue() + 100;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		double progress = manaValue / maxManaValue;
		if(progress > 1.0D) progress = 1.0D;
		if(event.getType() == ElementType.ALL && !player.abilities.disableDamage) {
			mc.fontRenderer.drawStringWithShadow((new DecimalFormat("#")).format(manaValue) + "/" + (new DecimalFormat("#")).format(maxManaValue), width / 2 + 25, height - 50, 0x5555FF);
			mc.getTextureManager().bindTexture(new ResourceLocation("minecraft", "textures/gui/bars.png"));
			RenderSystem.color3f(0.33f, 0.33f, 1.0f);
			mc.ingameGUI.blit(width / 2, height - 40, 91, 60, 91, 5);
			mc.ingameGUI.blit(width / 2 + 91 - (int) (91.0 * progress), height - 40, 182 - (int) (91.0 * progress), 65, (int) (91.0 * progress), 5);
			RenderSystem.clearCurrentColor();
		}
	}
	
	@SubscribeEvent
	public static void onPlayerClone(PlayerEvent.Clone event) {
		if(!event.isWasDeath()) return;
		//IMana manaOld = event.getOriginal().getCapability(ManaProvider.MANA_CAPABILITY).orElseThrow(NullPointerException::new);
	}

}
