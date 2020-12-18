package alephinfinity1.forgeblock.client;

import static alephinfinity1.forgeblock.ForgeBlock.MINECRAFT;

import java.text.DecimalFormat;

import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class HealthBarRenderer {

	@SubscribeEvent
	public static void onHealthBarRender(RenderGameOverlayEvent.Pre event) {
		if(!event.getType().equals(ElementType.HEALTH) || event.isCanceled()) return;
		event.setCanceled(true);
		PlayerEntity player = (PlayerEntity) MINECRAFT.getRenderViewEntity();
		float health = player.getHealth();
		float maxHealth = player.getMaxHealth();
		float absorption = player.getAbsorptionAmount();
		
		int width = MINECRAFT.getMainWindow().getScaledWidth();
		int height = MINECRAFT.getMainWindow().getScaledHeight();
		if (MathHelper.epsilonEquals(absorption, 0)) {
			MINECRAFT.fontRenderer.drawStringWithShadow((new DecimalFormat("#")).format(health + absorption) + "/" + (new DecimalFormat("#")).format(maxHealth), width / 2 - 75, height - 50, 0xFF5555);
		} else {
			MINECRAFT.fontRenderer.drawStringWithShadow((new DecimalFormat("#")).format(health + absorption) + "/" + (new DecimalFormat("#")).format(maxHealth), width / 2 - 75, height - 50, 0xFFFF55);
		}
		
		double progress = MathHelper.clamp(health / maxHealth, 0.0D, 1.0D);
		double absorpProgress = MathHelper.clamp(absorption / maxHealth, 0.0D, 1.0D);
		MINECRAFT.getTextureManager().bindTexture(new ResourceLocation("minecraft", "textures/gui/bars.png"));
		RenderSystem.color3f(1.0f, 0.33f, 0.33f);
		MINECRAFT.ingameGUI.blit(width / 2 - 91, height - 40, 0, 60, 91, 5); //White unfilled bar
		//MINECRAFT.ingameGUI.blit(width / 2 - 91, height - 40, 0, 110, 91, 5); //Notches
		MINECRAFT.ingameGUI.blit(width / 2 - 91, height - 40, 0, 65, (int) (91 * progress), 5); //White filled bar
		//MINECRAFT.ingameGUI.blit(width / 2 - 91, height - 40, 0, 115, (int) (91 * progress), 5); //Filled notches
		
		RenderSystem.color3f(1.0f, 1.0f, 0.33f); //Absorption bar
		MINECRAFT.ingameGUI.blit(width / 2 - 91, height - 40, 0, 65, (int) (91 * absorpProgress), 5);
		//MINECRAFT.ingameGUI.blit(width / 2 - 91, height - 40, 0, 115, (int) (91 * absorpProgress), 5);
		
		RenderSystem.popAttributes();
	}
	
	@SubscribeEvent(priority = EventPriority.LOW)
	public static void hideFoodBar(RenderGameOverlayEvent.Pre event) {
		if(event.getType().equals(ElementType.FOOD)) {
			event.setCanceled(true);
		}
	}

}
