package alephinfinity1.forgeblock.client;

import com.mojang.blaze3d.systems.RenderSystem;

import alephinfinity1.forgeblock.ForgeBlock;
import alephinfinity1.forgeblock.config.FBModConfig;
import alephinfinity1.forgeblock.item.IFBTieredItem;
import alephinfinity1.forgeblock.misc.tier.FBTier;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ModelManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class FBItemRenderer extends ItemRenderer {

	public FBItemRenderer(TextureManager textureManagerIn, ModelManager modelManagerIn, ItemColors itemColorsIn) {
		super(textureManagerIn, modelManagerIn, itemColorsIn);
	}

	public void renderItemModelIntoGUI(ItemStack stack, int x, int y, IBakedModel bakedmodel) {
		super.renderItemModelIntoGUI(stack, x, y, bakedmodel);
		if (stack.getItem() instanceof IFBTieredItem && FBModConfig.RARITY_GLOW.get()) {
			FBTier tier = ((IFBTieredItem) stack.getItem()).getStackTier(stack);
			this.drawRarityGlow(tier, x, y);
		}
	}

	private void drawRarityGlow(FBTier tier, int x, int y) {
		RenderSystem.enableBlend();
		this.zLevel -= 50.0f;
		int color = tier.color.getColor();
		int red = (color & 0x00ff0000) >> 16;
		int green = (color & 0x0000ff00) >> 8;
		int blue = color & 0x000000ff;
		ForgeBlock.MINECRAFT.getTextureManager().bindTexture(new ResourceLocation(ForgeBlock.MOD_ID, "textures/gui/glow.png"));
		this.drawWithTexture(Tessellator.getInstance().getBuffer(), x, y, 16, 16, red, green, blue, 
				(int) MathHelper.clamp(Math.ceil(FBModConfig.RARITY_GLOW_OPACITY.get() * 255), 0, 255));
		this.zLevel += 50.0f;
	}

	public void drawWithTexture(BufferBuilder renderer, int x, int y, int width, int height, int red, int green, int blue, int alpha) {
		renderer.begin(7, DefaultVertexFormats.POSITION_COLOR_TEX);
		renderer.pos(x, y, 0.0D).color(red, green, blue, alpha).tex(0, 0).endVertex();
		renderer.pos(x, y + height, 0.0D).color(red, green, blue, alpha).tex(0, 1).endVertex();
		renderer.pos(x + width, y + height, 0.0D).color(red, green, blue, alpha).tex(1, 1).endVertex();
		renderer.pos(x + width, y, 0.0D).color(red, green, blue, alpha).tex(1, 0).endVertex();
		Tessellator.getInstance().draw();
	}

}
