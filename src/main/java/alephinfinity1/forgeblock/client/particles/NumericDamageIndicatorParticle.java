package alephinfinity1.forgeblock.client.particles;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import alephinfinity1.forgeblock.ForgeBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.World;

public class NumericDamageIndicatorParticle extends Particle {
	
	private final String message;

	public NumericDamageIndicatorParticle(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn,
			double xSpeedIn, double ySpeedIn, double zSpeedIn, String damageInfo) {
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
		this.message = damageInfo;
		this.maxAge = 20;
	}

	@Override
	public void renderParticle(IVertexBuilder buffer, ActiveRenderInfo renderInfo, float partialTicks) {
		
		MatrixStack matrixStack = new MatrixStack();
		matrixStack.getLast().getMatrix().mul(ForgeBlock.MINECRAFT.gameRenderer.getProjectionMatrix(renderInfo, partialTicks, true));
		matrixStack.rotate(renderInfo.getRotation());
		matrixStack.scale(-1.0f, -1.0f, 1.0f);

		IRenderTypeBuffer.Impl irendertypebufferimpl = Minecraft.getInstance().getRenderTypeBuffers().getBufferSource();
		IVertexBuilder builder = irendertypebufferimpl.getBuffer(RenderType.getSolid());
		FontRenderer font = Minecraft.getInstance().fontRenderer;
		font.renderString(message, Minecraft.getInstance().getMainWindow().getScaledWidth() / 2, Minecraft.getInstance().getMainWindow().getScaledHeight() / 2, 0xffffff, false, matrixStack.getLast().getMatrix(), irendertypebufferimpl, false, 0, 15728880);
		irendertypebufferimpl.finish();
		//System.out.println("Particle is rendering");
	}
	
	@Override
    public void tick() {
		
    }

	@Override
	public IParticleRenderType getRenderType() { 
		return IParticleRenderType.CUSTOM;
	}
	
	public static class Factory implements IParticleFactory<StringParticleData> {

		@Override
		public Particle makeParticle(StringParticleData typeIn, World worldIn, double x, double y, double z,
				double xSpeed, double ySpeed, double zSpeed) {
			return new NumericDamageIndicatorParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, typeIn.getString());
		}
		
	}

}
