package alephinfinity1.forgeblock.client;

import java.text.DecimalFormat;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Matrix4f;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderWorldLastEvent;
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
		mc.fontRenderer.drawStringWithShadow((new DecimalFormat("#")).format(health) + "/" + (new DecimalFormat("#")).format(maxHealth), width / 2 - 75, height - 40, 0xFF5555);
	}
	
	//@SubscribeEvent
	public static void onWorldRender(RenderWorldLastEvent event) {
		Matrix4f test = new Matrix4f();
		test.setIdentity();
		mc.fontRenderer.renderString("Lorem ipsum", 0, 0, 0xFFFFFF, false, test, mc.getRenderTypeBuffers().getBufferSource(), false, 0, 0xFF00FF);
	}

}
