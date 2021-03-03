package alephinfinity1.forgeblock.mixin;

import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.util.SoundEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import java.util.List;

@Mixin(AbstractArrowEntity.class)
public interface AccessorAbstractArrowEntity {
    @Accessor
    IntOpenHashSet getPiercedEntities();

    @Accessor
    void setPiercedEntities(IntOpenHashSet piercedEntities);

    @Accessor
    int getKnockbackStrength();

    @Accessor
    void setKnockbackStrength(int knockbackStrength);

    @Accessor
    List<Entity> getHitEntities();

    @Accessor
    void setHitEntities(List<Entity> hitEntities);

    @Accessor
    SoundEvent getHitSound();

    @Accessor
    void setHitSound(SoundEvent hitSound);

    @Accessor
    int getTicksInAir();

    @Accessor
    void setTicksInAir(int ticksInAir);
}