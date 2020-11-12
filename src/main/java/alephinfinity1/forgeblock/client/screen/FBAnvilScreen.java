package alephinfinity1.forgeblock.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.screen.inventory.AnvilScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.RepairContainer;
import net.minecraft.util.text.ITextComponent;

public class FBAnvilScreen extends AnvilScreen {

	public FBAnvilScreen(RepairContainer p_i51103_1_, PlayerInventory p_i51103_2_, ITextComponent p_i51103_3_) {
		super(p_i51103_1_, p_i51103_2_, p_i51103_3_);	
	}

	/**
	 * Overridden in order to prevent 'Too Expensive!' from being displayed.
	 */
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		RenderSystem.disableBlend();
		this.font.drawString(this.title.getFormattedText(), 60.0F, 6.0F, 4210752);
		int i = this.container.getMaximumCost();
		if (i > 0) {
			int j = 8453920;
			boolean flag = true;
			String s = I18n.format("container.repair.cost", i);
			if (!this.container.getSlot(2).getHasStack()) {
				flag = false;
			} else if (!this.container.getSlot(2).canTakeStack(this.playerInventory.player)) {
				j = 16736352;
			}

			if (flag) {
				int k = this.xSize - 8 - this.font.getStringWidth(s) - 2;
				int l = 69;
				fill(k - 2, 67, this.xSize - 8, 79, 1325400064);
				this.font.drawStringWithShadow(s, (float)k, 69.0F, j);
			}
		}

	}

}
