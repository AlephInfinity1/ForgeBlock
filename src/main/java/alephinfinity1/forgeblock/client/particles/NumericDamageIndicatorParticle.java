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
import net.minecraft.client.renderer.Matrix4f;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.event.EntityViewRenderEvent.CameraSetup;

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
		
		MatrixStack matrixStackIn = new MatrixStack();
		
		matrixStackIn.push();
		ActiveRenderInfo activerenderinfo = ForgeBlock.MINECRAFT.gameRenderer.getActiveRenderInfo();
		CameraSetup cam = ForgeHooksClient.onCameraSetup(ForgeBlock.MINECRAFT.gameRenderer, activerenderinfo, partialTicks);
		
		activerenderinfo.setAnglesInternal(cam.getYaw(), cam.getPitch());
		
		//matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(cam.getRoll()));
		
		matrixStackIn.rotate(Vector3f.XP.rotationDegrees(activerenderinfo.getPitch()));
		matrixStackIn.rotate(Vector3f.YP.rotationDegrees(activerenderinfo.getYaw() + 180.0F));
		
		matrixStackIn.push();
		
		double x = MathHelper.lerp(partialTicks, prevPosX, posX);
		double y = MathHelper.lerp(partialTicks, prevPosY, posY);
		double z = MathHelper.lerp(partialTicks, prevPosZ, posZ);
		
		matrixStackIn.translate(x - ForgeBlock.MINECRAFT.gameRenderer.getActiveRenderInfo().getProjectedView().getX(), 
        		y - ForgeBlock.MINECRAFT.gameRenderer.getActiveRenderInfo().getProjectedView().getY(), 
        		z - ForgeBlock.MINECRAFT.gameRenderer.getActiveRenderInfo().getProjectedView().getZ());

		IRenderTypeBuffer.Impl irendertypebufferimpl = Minecraft.getInstance().getRenderTypeBuffers().getBufferSource();
		FontRenderer font = Minecraft.getInstance().fontRenderer;
		EntityRendererManager renderManager = Minecraft.getInstance().getRenderManager();
		
		matrixStackIn.push();
		matrixStackIn.rotate(renderManager.getCameraOrientation());
		matrixStackIn.scale(-0.025F, -0.025F, 0.025F);
		Matrix4f matrix4f = matrixStackIn.getLast().getMatrix();
	    
		font.renderString(this.message, 0, (float)0, 553648127, false, matrix4f, irendertypebufferimpl, false, 0, 0);
		
		//System.out.println("Particle is rendering");
	}
	
	@Override
    public void tick() {
		age++;
		if(this.age > this.maxAge) {
			this.setExpired();
		}
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
