package alephinfinity1.forgeblock.item.armor;

import com.google.common.collect.Multimap;

import alephinfinity1.forgeblock.misc.tier.FBTier;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;

public class ChickenHeadItem extends FBArmorItem {

	public ChickenHeadItem(EquipmentSlotType slot, String name, Properties props, FBTier tier, double damage,
			double strength, double critChance, double critDamage, double bonusAttackSpeed, double seaCreatureChance,
			double health, double defense, double speed, double intelligence, double trueDefense, double magicFind,
			double petLuck) {
		super(slot, name, props, tier, damage, strength, critChance, critDamage, bonusAttackSpeed, seaCreatureChance, health,
				defense, speed, intelligence, trueDefense, magicFind, petLuck);
	}
	
	public ChickenHeadItem(EquipmentSlotType slot, String name, Properties props, FBTier tier, Multimap<String, AttributeModifier> modifiers) {
		super(slot, name, props, tier, modifiers);
	}

}
