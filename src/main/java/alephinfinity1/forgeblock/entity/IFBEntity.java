package alephinfinity1.forgeblock.entity;

import alephinfinity1.forgeblock.attribute.FBAttributes;
import net.minecraft.entity.LivingEntity;

public interface IFBEntity {
	int getLevel();
	double getCoins();
	double getCombatXP();

	default void registerFBAttributes() {
		if (this instanceof LivingEntity) {
			LivingEntity living = (LivingEntity) this;
			if (living.getAttributes().getAttributeInstance(FBAttributes.STRENGTH) == null) {
				living.getAttributes().registerAttribute(FBAttributes.STRENGTH);
			}
			if (living.getAttributes().getAttributeInstance(FBAttributes.DEFENSE) == null) {
				living.getAttributes().registerAttribute(FBAttributes.DEFENSE);
			}
			if (living.getAttributes().getAttributeInstance(FBAttributes.TRUE_DEFENSE) == null) {
				living.getAttributes().registerAttribute(FBAttributes.TRUE_DEFENSE);
			}
			if (living.getAttributes().getAttributeInstance(FBAttributes.CRIT_CHANCE) == null) {
				living.getAttributes().registerAttribute(FBAttributes.CRIT_CHANCE);
			}
			if (living.getAttributes().getAttributeInstance(FBAttributes.CRIT_DAMAGE) == null) {
				living.getAttributes().registerAttribute(FBAttributes.CRIT_DAMAGE);
			}
			if (living.getAttributes().getAttributeInstance(FBAttributes.BONUS_ATTACK_SPEED) == null) {
				living.getAttributes().registerAttribute(FBAttributes.BONUS_ATTACK_SPEED);
			}
			if (living.getAttributes().getAttributeInstance(FBAttributes.INTELLIGENCE) == null) {
				living.getAttributes().registerAttribute(FBAttributes.INTELLIGENCE);
			}
			if (living.getAttributes().getAttributeInstance(FBAttributes.FEROCITY) == null) {
				living.getAttributes().registerAttribute(FBAttributes.FEROCITY);
			}
		}
	}
}
