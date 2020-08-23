package alephinfinity1.forgeblock.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Matrix4f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(Dist.CLIENT)
public class HealthBarRenderer {
	
	private final static Minecraft minecraft = Minecraft.getInstance();

	@SubscribeEvent
	public static void onHealthBarRender(RenderGameOverlayEvent.Pre event) {
		if(event.getType().equals(ElementType.FOOD)) {
			event.setCanceled(true);
		} else if(!event.getType().equals(ElementType.HEALTH) || event.isCanceled()) return;
		
	}
	
	//@SubscribeEvent
	public static void onWorldRender(RenderWorldLastEvent event) {
		Matrix4f test = new Matrix4f();
		test.setIdentity();
		minecraft.fontRenderer.renderString("Lorem ipsum", 0, 0, 0xFFFFFF, false, test, minecraft.getRenderTypeBuffers().getBufferSource(), false, 0, 0xFF00FF);
	}

}
