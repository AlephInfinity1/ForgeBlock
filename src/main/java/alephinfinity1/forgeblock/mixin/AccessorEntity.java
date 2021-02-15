package alephinfinity1.forgeblock.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.Random;

@Mixin(Entity.class)
public interface AccessorEntity {
    @Invoker
    void callApplyEnchantments(LivingEntity entityLivingBaseIn, Entity entityIn);

    @Accessor
    Random getRand();
}
