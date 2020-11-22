package alephinfinity1.forgeblock.client;

import java.util.Objects;

import org.lwjgl.glfw.GLFW;

import alephinfinity1.forgeblock.ForgeBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
@OnlyIn(Dist.CLIENT)
public class KeyPressHandler {

	@SubscribeEvent
	public static void onKeyPress(InputEvent.KeyInputEvent event) {
		if(Objects.isNull(ForgeBlock.MINECRAFT.currentScreen) 
				&& event.getKey() == GLFW.GLFW_KEY_LEFT_SHIFT
				&& event.getAction() == GLFW.GLFW_PRESS) {
			;
		}
	}
	
}
