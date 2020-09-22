package alephinfinity1.forgeblock.client;

import java.text.DecimalFormat;

import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(Dist.CLIENT)
public class HealthBarRenderer {
	
	private final static Minecraft mc = Minecraft.getInstance();

	@SubscribeEvent
	public static void onHealthBarRender(RenderGameOverlayEvent.Pre event) {
		if(event.getType().equals(ElementType.FOOD)) {
			event.setCanceled(true);
		} else if(!event.getType().equals(ElementType.HEALTH) || event.isCanceled()) return;
		event.setCanceled(true);
		PlayerEntity player = (PlayerEntity) mc.getRenderViewEntity();
		float health = player.getHealth();
		float maxHealth = player.getMaxHealth();
		
		int width = mc.getMainWindow().getScaledWidth();
		int height = mc.getMainWindow().getScaledHeight();
		mc.fontRenderer.drawStringWithShadow((new DecimalFormat("#")).format(health) + "/" + (new DecimalFormat("#")).format(maxHealth), width / 2 - 75, height - 50, 0xFF5555);
		
		double progress = health / maxHealth;
		if(progress > 1.0d) progress = 1.0d;
		mc.getTextureManager().bindTexture(new ResourceLocation("minecraft", "textures/gui/icons.png"));
		RenderSystem.color3f(1.0f, 0.5f, 0.5f);
		mc.ingameGUI.blit(width / 2 - 91, height - 40, 0, 64, 91, 5);
		mc.ingameGUI.blit(width / 2 - 91, height - 40, 0, 69, (int) (91 * progress), 5);
	}

}
