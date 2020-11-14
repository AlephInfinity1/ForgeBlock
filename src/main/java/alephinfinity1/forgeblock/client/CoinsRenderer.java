package alephinfinity1.forgeblock.client;

import static alephinfinity1.forgeblock.ForgeBlock.MINECRAFT;

import java.text.DecimalFormat;

import com.mojang.blaze3d.systems.RenderSystem;

import alephinfinity1.forgeblock.misc.coins.CoinsProvider;
import alephinfinity1.forgeblock.misc.coins.ICoins;
import net.minecraft.client.gui.IngameGui;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class CoinsRenderer {
	
	@SubscribeEvent
	public static void renderCoin(RenderGameOverlayEvent.Post event) {
		if(event.getType() != ElementType.ALL) return;
		int width = MINECRAFT.getMainWindow().getScaledWidth();
		int height = MINECRAFT.getMainWindow().getScaledHeight();
		PlayerEntity player = MINECRAFT.player;
		ICoins coins = player.getCapability(CoinsProvider.COINS_CAPABILITY).orElse(null);
		MINECRAFT.fontRenderer.drawStringWithShadow((new DecimalFormat(",###.#")).format(coins == null ? 0.0D : coins.getCoins()).replaceAll("\u00A0", " "), width / 2 + 135, height - 28, 0xFFAA00);
		
		//Coins icon
		MINECRAFT.getTextureManager().bindTexture(new ResourceLocation("forgeblock", "textures/gui/coins.png"));
		IngameGui.blit(width / 2 + 115, height - 33, 0, 0, 0, 16, 16, 16, 16);
		RenderSystem.popAttributes();
	}
	
	@SubscribeEvent
	public static void renderBits(RenderGameOverlayEvent.Post event) {
		if(event.getType() != ElementType.ALL) return;
		int width = MINECRAFT.getMainWindow().getScaledWidth();
		int height = MINECRAFT.getMainWindow().getScaledHeight();
		MINECRAFT.fontRenderer.drawStringWithShadow((new DecimalFormat(",###.#")).format(0.0D).replaceAll("\u00A0", " "), width / 2 + 135, height - 12, 0x55FFFF);
		
		//Coins icon
		MINECRAFT.getTextureManager().bindTexture(new ResourceLocation("forgeblock", "textures/gui/bits.png"));
		IngameGui.blit(width / 2 + 115, height - 17, 0, 0, 0, 16, 16, 16, 16);
		RenderSystem.popAttributes();
	}

}
