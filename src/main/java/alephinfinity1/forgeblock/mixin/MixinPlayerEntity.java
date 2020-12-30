package alephinfinity1.forgeblock.mixin;

import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class MixinPlayerEntity {

    //@Inject(at = @At("RETURN"), method = "xpBarCap()I", cancellable = true)
    public void xpBarCap(CallbackInfoReturnable<Integer> cir) {
        cir.cancel();
        cir.setReturnValue(5);
    }

}
