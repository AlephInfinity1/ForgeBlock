package alephinfinity1.forgeblock.effect;

import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.DisplayEffectsScreen;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.extensions.IForgeEffect;

public class EnderWarpEffect extends Effect implements IForgeEffect {

	public EnderWarpEffect(EffectType typeIn, int liquidColorIn) {
		super(typeIn, liquidColorIn);
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void renderInventoryEffect(EffectInstance effect, DisplayEffectsScreen<?> gui, int x, int y, float z) {
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
    public void renderHUDEffect(EffectInstance effect, AbstractGui gui, int x, int y, float z, float alpha) {
	}

}
