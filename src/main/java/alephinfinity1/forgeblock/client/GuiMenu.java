package alephinfinity1.forgeblock.client;

import com.mojang.blaze3d.systems.RenderSystem;

import alephinfinity1.forgeblock.misc.skills.ISkills;
import alephinfinity1.forgeblock.misc.skills.SkillType;
import alephinfinity1.forgeblock.misc.skills.SkillsProvider;
import alephinfinity1.forgeblock.network.FBPacketHandler;
import alephinfinity1.forgeblock.network.SkillUpdatePacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class GuiMenu extends Screen {

	public GuiMenu(ITextComponent titleIn) {
		super(titleIn);
	}
	
	@Override
	public void init() {
		this.setSize(this.minecraft.currentScreen.width, this.minecraft.currentScreen.height);
		this.addButton(new Button(100, 120, 100, 20, "+1 Combat LVL", new Button.IPressable() {
			
			@Override
			public void onPress(Button p_onPress_1_) {
				ClientPlayerEntity player = Minecraft.getInstance().player;
				ISkills skills = player.getCapability(SkillsProvider.SKILLS_CAPABILITY).orElseThrow(NullPointerException::new);
				skills.setLevel(SkillType.COMBAT, skills.getLevel(SkillType.COMBAT) + 1);
				CompoundNBT nbt = new CompoundNBT();
				nbt.putString("SkillType", "combat");
				nbt.putInt("Level", skills.getLevel(SkillType.COMBAT));
				nbt.putDouble("Progress", skills.getAbsoluteProgress(SkillType.COMBAT));
				FBPacketHandler.INSTANCE.sendToServer(new SkillUpdatePacket(nbt));
			}
		}));
		
		this.addButton(new Button(100, 140, 100, 20, "+100 Combat LVL", new Button.IPressable() {

			@Override
			public void onPress(Button p_onPress_1_) {
				ClientPlayerEntity player = Minecraft.getInstance().player;
				ISkills skills = player.getCapability(SkillsProvider.SKILLS_CAPABILITY).orElseThrow(NullPointerException::new);
				skills.setLevel(SkillType.COMBAT, skills.getLevel(SkillType.COMBAT) + 100);
				CompoundNBT nbt = new CompoundNBT();
				nbt.putString("SkillType", "combat");
				nbt.putInt("Level", skills.getLevel(SkillType.COMBAT));
				nbt.putDouble("Progress", skills.getAbsoluteProgress(SkillType.COMBAT));
				FBPacketHandler.INSTANCE.sendToServer(new SkillUpdatePacket(nbt));
			}
		}));
	}
	
	@Override
	public void render(int p_render_1_, int p_render_2_, float p_render_3_) {
		super.renderBackground();
		super.render(p_render_1_, p_render_2_, p_render_3_);
		ClientPlayerEntity player = this.minecraft.player;
		ISkills skills = player.getCapability(SkillsProvider.SKILLS_CAPABILITY).orElseThrow(NullPointerException::new);
		this.font.drawStringWithShadow("Combat level: " + Integer.toString(skills.getLevel(SkillType.COMBAT)), 75, 40, 0xFF5555);
		this.minecraft.getTextureManager().bindTexture(new ResourceLocation("minecraft", "textures/gui/icons.png"));
		double progress = skills.getProgressPercentage(SkillType.COMBAT);
		RenderSystem.color3f(1.0f, 0.21569f, 0.21569f);
		this.blit(175, 40, 0, 64, 182, 5);
		this.blit(175, 40, 0, 69, (int) (182 * progress), 5);
	}
	
	@Override
	public boolean isPauseScreen() {
		return true;
	}

}
