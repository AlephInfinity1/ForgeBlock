package alephinfinity1.forgeblock.mixin;

import alephinfinity1.forgeblock.attribute.FBAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.CombatRules;
import net.minecraft.util.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.Objects;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity {
    /**
     * @author AlephInfinity1
     * @reason
     * Calculate reduction provided by the {@link FBAttributes#DEFENSE} and {@link FBAttributes#TRUE_DEFENSE} stats.
     * Also overrides vanilla handling.
     */
    @Overwrite
    protected float applyArmorCalculations(DamageSource source, float damage) {
        if (!source.isUnblockable() && Objects.nonNull(((LivingEntity) (Object) this).getAttribute(FBAttributes.DEFENSE))) {
            double defense = ((LivingEntity) (Object) this).getAttribute(FBAttributes.DEFENSE).getValue();
            damage *= 100 / (defense + 100.0D);
        }

        if (Objects.nonNull(((LivingEntity) (Object) this).getAttribute(FBAttributes.TRUE_DEFENSE))) {
            double trueDefense = ((LivingEntity) (Object) this).getAttribute(FBAttributes.TRUE_DEFENSE).getValue();
            damage *= 100 / (trueDefense + 100.0D);
        }

        return damage;
    }
}
