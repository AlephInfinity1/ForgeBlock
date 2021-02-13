package alephinfinity1.forgeblock.misc.ability;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * An ability casted by an item.
 */
public abstract class Ability {
    /**
     * Returns whether the entity can cast the spell with the ItemStack.
     * @param entity The player/mob casting this spell.
     * @param world The world in which this spell will be cast in.
     * @param stack The ItemStack used to cast this spell.
     * @return Whether the player/mob can cast this spell.
     */
    public abstract boolean canCast(LivingEntity entity, World world, ItemStack stack);

    /**
     * Casts this spell.
     * @param entity The player/mob casting this spell.
     * @param world The world in which this spell was cast in.
     * @param stack The ItemStack used to cast this spell.
     */
    public abstract void cast(LivingEntity entity, World world, ItemStack stack);
}
