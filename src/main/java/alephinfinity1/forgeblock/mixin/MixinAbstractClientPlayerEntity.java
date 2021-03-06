package alephinfinity1.forgeblock.mixin;

import alephinfinity1.forgeblock.item.bows.FBBowItem;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(AbstractClientPlayerEntity.class)
public class MixinAbstractClientPlayerEntity {
    /**
     * @author a
     */
    @Overwrite
    public float getFovModifier() {
        float f = 1.0F;
        if (((AbstractClientPlayerEntity) (Object) this).abilities.isFlying) {
            f *= 1.1F;
        }

        IAttributeInstance iattributeinstance = ((AbstractClientPlayerEntity) (Object) this).getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
        f = (float)((double)f * ((iattributeinstance.getValue() / (double)((AbstractClientPlayerEntity) (Object) this).abilities.getWalkSpeed() + 1.0D) / 2.0D));
        if (((AbstractClientPlayerEntity) (Object) this).abilities.getWalkSpeed() == 0.0F || Float.isNaN(f) || Float.isInfinite(f)) {
            f = 1.0F;
        }


        if (((AbstractClientPlayerEntity) (Object) this).isHandActive() && ((AbstractClientPlayerEntity) (Object) this).getActiveItemStack().getItem() instanceof FBBowItem) {
            int i = ((AbstractClientPlayerEntity) (Object) this).getItemInUseMaxCount();
            float f1 = (float)i * ((FBBowItem) ((AbstractClientPlayerEntity) (Object) this).getActiveItemStack().getItem()).getDrawSpeed() / 20.0F;
            if (f1 > 1.0F) {
                f1 = 1.0F;
            } else {
                f1 = f1 * f1;
            }

            f *= 1.0F - f1 * 0.15F * ((FBBowItem) ((AbstractClientPlayerEntity) (Object) this).getActiveItemStack().getItem()).getVelocityMul();
        }
        
        return f;
    }
}
