package alephinfinity1.forgeblock.client;

import java.text.DecimalFormat;

import com.mojang.blaze3d.matrix.MatrixStack;

import alephinfinity1.forgeblock.ForgeBlock;
import alephinfinity1.forgeblock.entity.IFBEntity;
import alephinfinity1.forgeblock.misc.TextFormatHelper;
import alephinfinity1.forgeblock.misc.TextFormatHelper.SuffixType;
import alephinfinity1.forgeblock.misc.skills.ISkills;
import alephinfinity1.forgeblock.misc.skills.SkillType;
import alephinfinity1.forgeblock.misc.skills.SkillsProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Matrix4f;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class EntityRenderHandler {
	
	@SubscribeEvent
	public static void onLivingRender(RenderLivingEvent.Post<? extends LivingEntity, ? extends EntityModel<?>> event) {
		//Some code copied and modified from EntityRenderer$renderName
		Entity entityIn = event.getEntity();
		if(entityIn instanceof ArmorStandEntity) return;
		MatrixStack matrixStackIn = event.getMatrixStack();
		IRenderTypeBuffer bufferIn = event.getBuffers();
		String displayNameIn = getEntityString(entityIn);
		int packedLightIn = event.getLight();
		EntityRendererManager renderManager = Minecraft.getInstance().getRenderManager();
		boolean flag = !entityIn.isDiscrete();
        float f = entityIn.getHeight() + 0.5F;
        int i = "deadmau5".equals(displayNameIn) ? -10 : 0;
        matrixStackIn.push();
        matrixStackIn.translate(0.0D, (double)f, 0.0D);
        matrixStackIn.rotate(renderManager.getCameraOrientation());
        matrixStackIn.scale(-0.025F, -0.025F, 0.025F);
        Matrix4f matrix4f = matrixStackIn.getLast().getMatrix();
        float f1 = ForgeBlock.MINECRAFT.gameSettings.getTextBackgroundOpacity(0.25F);
        int j = (int)(f1 * 255.0F) << 24;
        FontRenderer fontrenderer = renderManager.getFontRenderer();
        float f2 = (float)(-fontrenderer.getStringWidth(displayNameIn) / 2);
        fontrenderer.renderString(displayNameIn, f2, (float)i, 553648127, false, matrix4f, bufferIn, flag, j, packedLightIn);
        if (flag) {
           fontrenderer.renderString(displayNameIn, f2, (float)i, -1, false, matrix4f, bufferIn, false, 0, packedLightIn);
        }

        matrixStackIn.pop();
	}
	
	public static String getEntityString(Entity entity) {
		StringBuffer str = new StringBuffer("\u00A78[\u00A77Lv");
		
		//Displays entity level
		if(entity instanceof IFBEntity) {
			str.append(((IFBEntity) entity).getLevel());
		} else if(entity instanceof PlayerEntity) { //If player, level is the sum of all non-cosmetic skill levels (max 400), or 8 times the player's true skill avg.
			ISkills skills = ((PlayerEntity) entity).getCapability(SkillsProvider.SKILLS_CAPABILITY).orElseThrow(NullPointerException::new);
			int playerLvl = 0;
			for(SkillType type : SkillType.NON_COSMETIC_TYPES) {
				playerLvl += skills.getLevel(type);
			}
			str.append(playerLvl);
		} else { //If not a ForgeBlock mob, default to 0.
			str.append(0);
		}
		
		/*
		 * Name colour based on entity type
		 * Monster -> red
		 * Player -> dark cyan
		 * Passive -> dark green
		 */
		if(entity instanceof MonsterEntity || entity instanceof SlimeEntity || entity instanceof EnderDragonEntity || entity instanceof FlyingEntity) {
			str.append("\u00A78] \u00A7c");
		} else if(entity instanceof PlayerEntity) {
			str.append("\u00A78] \u00A73");
		} else {
			str.append("\u00A78] \u00A72");
		}
		str.append(entity.getName().getString());
		
		/*
		 * Colour based on HP percentage
		 * above 50%: green
		 * 0%-50%: yellow
		 */
		if(((LivingEntity) entity).getHealth() / ((LivingEntity) entity).getMaxHealth() >= 0.5f) {
			str.append(" \u00A7a"); //Green TextFormatting
		} else {
			str.append(" \u00A7e"); //Yellow TextFormatting
		}
		
		//Health display
		//If above 100k, use big number formatting, otherwise use normal formatting
		if(((LivingEntity) entity).getHealth() < 100000.0f) {
			str.append(new DecimalFormat("#").format(((LivingEntity) entity).getHealth()));
		} else {
			str.append(TextFormatHelper.formatLargeNumberWithSuffix(SuffixType.SINGLE_LETTER, ((LivingEntity) entity).getHealth(), 1));
		}
		str.append("\u00A77/\u00A7a"); //Slash
		if(((LivingEntity) entity).getMaxHealth() < 100000.0f) {
			str.append(new DecimalFormat("#").format(((LivingEntity) entity).getMaxHealth()));
		} else {
			str.append(TextFormatHelper.formatLargeNumberWithSuffix(SuffixType.SINGLE_LETTER, ((LivingEntity) entity).getMaxHealth(), 1));
		}
		str.append("\u00A7c\u2764"); //Heart icon
		return str.toString();
	}
}
