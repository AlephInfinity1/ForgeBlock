package alephinfinity1.forgeblock.attribute;

import java.util.UUID;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import com.google.common.collect.Multimap;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;

public class ModifierHelper {

	public static Multimap<String, AttributeModifier> modifierMapFromDoubles(double damage, double strength, double critChance, double critDamage, double bonusAttackSpeed, double seaCreatureChance, double health, double defense, double speed, double intelligence, double trueDefense, double magicFind, double petLuck) {
		return modifierMapFromDoubles(damage, strength, critChance, critDamage, bonusAttackSpeed, seaCreatureChance, health, defense, speed, intelligence, trueDefense, magicFind, petLuck, "");
	}
	
	public static Multimap<String, AttributeModifier> modifierMapFromDoubles(double damage, double strength, double critChance, double critDamage, double bonusAttackSpeed, double seaCreatureChance, double health, double defense, double speed, double intelligence, double trueDefense, double magicFind, double petLuck, String name) {
		Builder<String, AttributeModifier> builder = ImmutableMultimap.builder(); 
		if(damage != 0.0D) builder.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(name + " damage modifier", damage, Operation.ADDITION));
		if(strength != 0.0D) builder.put(FBAttributes.STRENGTH.getName(), new AttributeModifier(name + " strength modifier", strength, Operation.ADDITION));
		if(critChance != 0.0D) builder.put(FBAttributes.CRIT_CHANCE.getName(), new AttributeModifier(name + " crit chance modifier", critChance, Operation.ADDITION));
		if(critDamage != 0.0D) builder.put(FBAttributes.CRIT_DAMAGE.getName(), new AttributeModifier(name + " crit damage modifier", critDamage, Operation.ADDITION));
		if(bonusAttackSpeed != 0.0D) builder.put(FBAttributes.BONUS_ATTACK_SPEED.getName(), new AttributeModifier(name + " bonus attack speed modifier", bonusAttackSpeed, Operation.ADDITION));
		if(seaCreatureChance != 0.0D) builder.put(FBAttributes.SEA_CREATURE_CHANCE.getName(), new AttributeModifier(name + " sea creature chance modifier", seaCreatureChance, Operation.ADDITION));
		if(health != 0.0D) builder.put(SharedMonsterAttributes.MAX_HEALTH.getName(), new AttributeModifier(name + " health modifier", health, Operation.ADDITION));
		if(defense != 0.0D) builder.put(FBAttributes.DEFENSE.getName(), new AttributeModifier(name + " defense modifier", defense, Operation.ADDITION));
		if(speed != 0.0D) builder.put(SharedMonsterAttributes.MOVEMENT_SPEED.getName(), new AttributeModifier(name + " speed modifier", speed * 0.001D, Operation.ADDITION));
		if(intelligence != 0.0D) builder.put(FBAttributes.INTELLIGENCE.getName(), new AttributeModifier(name + " intelligence modifier", intelligence, Operation.ADDITION));
		if(trueDefense != 0.0D) builder.put(FBAttributes.TRUE_DEFENSE.getName(), new AttributeModifier(name + " true defense modifier", trueDefense, Operation.ADDITION));
		if(magicFind != 0.0D) builder.put(FBAttributes.MAGIC_FIND.getName(), new AttributeModifier(name + " magic find modifier", magicFind, Operation.ADDITION));
		if(petLuck != 0.0D) builder.put(FBAttributes.PET_LUCK.getName(), new AttributeModifier(name + " pet luck modifier", petLuck, Operation.ADDITION));
		return builder.build();
	}
	
	public static Multimap<String, AttributeModifier> modifierMapFromDoubles(double damage, double strength, double critChance, double critDamage, double bonusAttackSpeed, double seaCreatureChance, double health, double defense, double speed, double intelligence, double trueDefense, double magicFind, double petLuck, String name, UUID uuid) {
		Builder<String, AttributeModifier> builder = ImmutableMultimap.builder(); 
		if(damage != 0.0D) builder.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(uuid, name + " damage modifier", damage, Operation.ADDITION));
		if(strength != 0.0D) builder.put(FBAttributes.STRENGTH.getName(), new AttributeModifier(uuid, name + " strength modifier", strength, Operation.ADDITION));
		if(critChance != 0.0D) builder.put(FBAttributes.CRIT_CHANCE.getName(), new AttributeModifier(uuid, name + " crit chance modifier", critChance, Operation.ADDITION));
		if(critDamage != 0.0D) builder.put(FBAttributes.CRIT_DAMAGE.getName(), new AttributeModifier(uuid, name + " crit damage modifier", critDamage, Operation.ADDITION));
		if(bonusAttackSpeed != 0.0D) builder.put(FBAttributes.BONUS_ATTACK_SPEED.getName(), new AttributeModifier(uuid, name + " bonus attack speed modifier", bonusAttackSpeed, Operation.ADDITION));
		if(seaCreatureChance != 0.0D) builder.put(FBAttributes.SEA_CREATURE_CHANCE.getName(), new AttributeModifier(uuid, name + " sea creature chance modifier", seaCreatureChance, Operation.ADDITION));
		if(health != 0.0D) builder.put(SharedMonsterAttributes.MAX_HEALTH.getName(), new AttributeModifier(uuid, name + " health modifier", health, Operation.ADDITION));
		if(defense != 0.0D) builder.put(FBAttributes.DEFENSE.getName(), new AttributeModifier(uuid, name + " defense modifier", defense, Operation.ADDITION));
		if(speed != 0.0D) builder.put(SharedMonsterAttributes.MOVEMENT_SPEED.getName(), new AttributeModifier(uuid, name + " speed modifier", speed * 0.001D, Operation.ADDITION));
		if(intelligence != 0.0D) builder.put(FBAttributes.INTELLIGENCE.getName(), new AttributeModifier(uuid, name + " intelligence modifier", intelligence, Operation.ADDITION));
		if(trueDefense != 0.0D) builder.put(FBAttributes.TRUE_DEFENSE.getName(), new AttributeModifier(uuid, name + " true defense modifier", trueDefense, Operation.ADDITION));
		if(magicFind != 0.0D) builder.put(FBAttributes.MAGIC_FIND.getName(), new AttributeModifier(uuid, name + " magic find modifier", magicFind, Operation.ADDITION));
		if(petLuck != 0.0D) builder.put(FBAttributes.PET_LUCK.getName(), new AttributeModifier(uuid, name + " pet luck modifier", petLuck, Operation.ADDITION));
		return builder.build();
	}
	
	public static Multimap<String, AttributeModifier> emptyModifier() {
		Builder<String, AttributeModifier> builder = ImmutableMultimap.builder();
		return builder.build();
	}
}
