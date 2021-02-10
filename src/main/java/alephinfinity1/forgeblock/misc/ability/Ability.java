package alephinfinity1.forgeblock.misc.ability;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

/**
 * A
 */
public abstract class Ability {
    /**
     * Casts this spell.
     * @param entity The player/mob casting this spell.
     * @param stack The ItemStack used to cast this spell.
     */
    public abstract void cast(LivingEntity entity, ItemStack stack);
}
