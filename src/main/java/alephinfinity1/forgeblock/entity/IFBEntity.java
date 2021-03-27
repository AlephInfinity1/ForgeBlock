package alephinfinity1.forgeblock.entity;

import alephinfinity1.forgeblock.attribute.FBAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.IAttribute;

public interface IFBEntity {
	int getLevel();
	double getCoins();
	double getCombatXP();

	default void registerFBAttributes() {
		if (this instanceof LivingEntity) {
			LivingEntity living = (LivingEntity) this;
			IAttribute[] fbAttributes = new IAttribute[]{
					FBAttributes.STRENGTH,
					FBAttributes.DEFENSE,
					FBAttributes.TRUE_DEFENSE,
					FBAttributes.CRIT_CHANCE,
					FBAttributes.CRIT_DAMAGE,
					FBAttributes.BONUS_ATTACK_SPEED,
					FBAttributes.INTELLIGENCE,
					FBAttributes.FEROCITY
			};
			for (IAttribute attribute : fbAttributes) {
				if (living.getAttributes().getAttributeInstance(attribute) == null) {
					living.getAttributes().registerAttribute(attribute);
				}
			}
		}
	}
}
