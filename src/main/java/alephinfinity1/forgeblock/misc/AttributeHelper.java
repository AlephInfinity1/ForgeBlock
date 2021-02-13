package alephinfinity1.forgeblock.misc;

import alephinfinity1.forgeblock.attribute.FBAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;

import java.util.NoSuchElementException;

/**
 * A series of helper methods to help with attributes.
 */
public class AttributeHelper {
    private AttributeHelper() {}

    /**
     * Gets the value of the given attribute of the given Entity.
     * Throws exception if not present.
     * @param entity The attribute holden.
     * @param attribute The attribute to check for.
     * @return The value of the attribute
     * @throws NoSuchElementException If the entity does not have the specified attribute.
     */
    public static double getAttributeValue(LivingEntity entity, IAttribute attribute) throws NoSuchElementException {
        try {
            return entity.getAttribute(attribute).getValue();
        } catch (NullPointerException e) {
            throw new NoSuchElementException(e.getMessage());
        }
    }

    /**
     * Gets the value of the given attribute of the given Entity.
     * Returns the default value if not present.
     * @param entity The attribute holden.
     * @param attribute The attribute to check for.
     * @param _default The return value if not present.
     * @return The value of the attribute
     */
    public static double getAttributeValueOrDefault(LivingEntity entity, IAttribute attribute, double _default) {
        try {
            return entity.getAttribute(attribute).getValue();
        } catch (NullPointerException e) {
            return _default;
        }
    }

    //Helper methods for each of the common attributes.

    public static double getMaxHealthOrDefault(LivingEntity entity, double _default) {
        return getAttributeValueOrDefault(entity, SharedMonsterAttributes.MAX_HEALTH, _default);
    }

    public static double getDefenseOrDefault(LivingEntity entity, double _default) {
        return getAttributeValueOrDefault(entity, FBAttributes.DEFENSE, _default);
    }

    public static double getDamageOrDefault(LivingEntity entity, double _default) {
        return getAttributeValueOrDefault(entity, SharedMonsterAttributes.ATTACK_DAMAGE, _default);
    }

    public static double getStrengthOrDefault(LivingEntity entity, double _default) {
        return getAttributeValueOrDefault(entity, FBAttributes.STRENGTH, _default);
    }

    public static double getCritChanceOrDefault(LivingEntity entity, double _default) {
        return getAttributeValueOrDefault(entity, FBAttributes.CRIT_CHANCE, _default);
    }

    public static double getCritDamageOrDefault(LivingEntity entity, double _default) {
        return getAttributeValueOrDefault(entity, FBAttributes.CRIT_DAMAGE, _default);
    }

    public static double getBonusAttackSpeedOrDefault(LivingEntity entity, double _default) {
        return getAttributeValueOrDefault(entity, FBAttributes.BONUS_ATTACK_SPEED, _default);
    }

    public static double getSpeedOrDefault(LivingEntity entity, double _default) {
        return getAttributeValueOrDefault(entity, SharedMonsterAttributes.MOVEMENT_SPEED, _default);
    }

    public static double getIntelligenceOrDefault(LivingEntity entity, double _default) {
        return getAttributeValueOrDefault(entity, FBAttributes.INTELLIGENCE, _default);
    }

    public static double getAbilityDamageOrDefault(LivingEntity entity, double _default) {
        return getAttributeValueOrDefault(entity, FBAttributes.ABILITY_DAMAGE, _default);
    }
}
