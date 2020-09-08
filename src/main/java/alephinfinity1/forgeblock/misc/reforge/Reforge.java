package alephinfinity1.forgeblock.misc.reforge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;
import java.util.function.Function;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import com.google.common.collect.Multimap;

import alephinfinity1.forgeblock.attribute.FBAttributes;
import alephinfinity1.forgeblock.item.IFBItem;
import alephinfinity1.forgeblock.misc.FBItemType;
import alephinfinity1.forgeblock.misc.tier.FBTier;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TranslationTextComponent;

//See https://hypixel-skyblock.fandom.com/wiki/Reforging
public enum Reforge {
	//Melee weapon reforges
	GENTLE(new FBItemType[] {FBItemType.SWORD}, "gentle", "misc.forgeblock.reforge.gentle", false, 
			(uuid) -> modifierMapFromDoubles(0, 3, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, uuid), 
			(uuid) -> modifierMapFromDoubles(0, 5, 0, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0, uuid), 
			(uuid) -> modifierMapFromDoubles(0, 7, 0, 0, 15, 0, 0, 0, 0, 0, 0, 0, 0, uuid), 
			(uuid) -> modifierMapFromDoubles(0, 10, 0, 0, 20, 0, 0, 0, 0, 0, 0, 0, 0, uuid), 
			(uuid) -> modifierMapFromDoubles(0, 15, 0, 0, 25, 0, 0, 0, 0, 0, 0, 0, 0, uuid), 
			(uuid) -> modifierMapFromDoubles(0, 20, 0, 0, 30, 0, 0, 0, 0, 0, 0, 0, 0, uuid)),
	ODD(new FBItemType[] {FBItemType.SWORD}, "odd", "misc.forgeblock.reforge.odd", false, 
			(uuid) -> modifierMapFromDoubles(0, 0, 12, 10, 0, 0, 0, 0, 0, -5, 0, 0, 0, uuid), 
			(uuid) -> modifierMapFromDoubles(0, 0, 15, 15, 0, 0, 0, 0, 0, -10, 0, 0, 0, uuid), 
			(uuid) -> modifierMapFromDoubles(0, 0, 15, 15, 0, 0, 0, 0, 0, -18, 0, 0, 0, uuid), 
			(uuid) -> modifierMapFromDoubles(0, 0, 25, 30, 0, 0, 0, 0, 0, -24, 0, 0, 0, uuid), 
			(uuid) -> modifierMapFromDoubles(0, 0, 25, 30, 0, 0, 0, 0, 0, -50, 0, 0, 0, uuid), 
			(uuid) -> modifierMapFromDoubles(0, 0, 35, 45, 0, 0, 0, 0, 0, -75, 0, 0, 0, uuid)),
	FAST(new FBItemType[] {FBItemType.SWORD}, "fast", "misc.forgeblock.reforge.fast", false, 
			(uuid) -> modifierMapFromDoubles(0, 0, 0, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 0, 0, 0, 20, 0, 0, 0, 0, 0, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 0, 0, 0, 30, 0, 0, 0, 0, 0, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 0, 0, 0, 40, 0, 0, 0, 0, 0, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 0, 0, 0, 50, 0, 0, 0, 0, 0, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 0, 0, 0, 60, 0, 0, 0, 0, 0, 0, 0, 0, uuid)),
	FAIR(new FBItemType[] {FBItemType.SWORD}, "fair", "misc.forgeblock.reforge.fair", false,
			(uuid) -> modifierMapFromDoubles(0, 2, 2, 2, 2, 0, 0, 0, 0, 2, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 3, 3, 3, 3, 0, 0, 0, 0, 3, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 4, 4, 4, 4, 0, 0, 0, 0, 4, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 7, 7, 7, 7, 0, 0, 0, 0, 7, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 10, 10, 10, 10, 0, 0, 0, 0, 10, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 14, 14, 14, 14, 0, 0, 0, 0, 14, 0, 0, 0, uuid)),
	EPIC(new FBItemType[] {FBItemType.SWORD}, "epic", "misc.forgeblock.reforge.epic", false,
			(uuid) -> modifierMapFromDoubles(0, 15, 0, 10, 1, 0, 0, 0, 0, 0, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 20, 0, 15, 2, 0, 0, 0, 0, 0, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 25, 0, 20, 4, 0, 0, 0, 0, 0, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 32, 0, 27, 7, 0, 0, 0, 0, 0, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 40, 0, 35, 10, 0, 0, 0, 0, 0, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 49, 0, 45, 14, 0, 0, 0, 0, 0, 0, 0, 0, uuid)),
	SHARP(new FBItemType[] {FBItemType.SWORD}, "sharp", "misc.forgeblock.reforge.sharp", false,
			(uuid) -> modifierMapFromDoubles(0, 0, 10, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 0, 12, 30, 0, 0, 0, 0, 0, 0, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 0, 14, 40, 0, 0, 0, 0, 0, 0, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 0, 17, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 0, 20, 75, 0, 0, 0, 0, 0, 0, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 0, 25, 100, 0, 0, 0, 0, 0, 0, 0, 0, 0, uuid)),
	HEROIC(new FBItemType[] {FBItemType.SWORD}, "heroic", "misc.forgeblock.reforge.heroic", false,
			(uuid) -> modifierMapFromDoubles(0, 15, 0, 0, 1, 0, 0, 0, 0, 40, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 20, 0, 0, 2, 0, 0, 0, 0, 50, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 25, 0, 0, 2, 0, 0, 0, 0, 65, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 32, 0, 0, 3, 0, 0, 0, 0, 80, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 40, 0, 0, 5, 0, 0, 0, 0, 100, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 49, 0, 0, 7, 0, 0, 0, 0, 125, 0, 0, 0, uuid)),
	SPICY(new FBItemType[] {FBItemType.SWORD}, "spicy", "misc.forgeblock.reforge.spicy", false,
			(uuid) -> modifierMapFromDoubles(0, 2, 1, 25, 1, 0, 0, 0, 0, 0, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 3, 1, 35, 2, 0, 0, 0, 0, 0, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 4, 1, 45, 4, 0, 0, 0, 0, 0, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 7, 1, 60, 7, 0, 0, 0, 0, 0, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 10, 1, 80, 10, 0, 0, 0, 0, 0, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 14, 1, 105, 14, 0, 0, 0, 0, 0, 0, 0, 0, uuid)),
	LEGENDARY(new FBItemType[] {FBItemType.SWORD}, "legendary", "misc.forgeblock.reforge.legendary", false,
			(uuid) -> modifierMapFromDoubles(0, 3, 5, 5, 2, 0, 0, 0, 0, 5, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 7, 7, 10, 3, 0, 0, 0, 0, 8, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 12, 9, 15, 5, 0, 0, 0, 0, 12, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 18, 12, 22, 7, 0, 0, 0, 0, 18, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 25, 15, 28, 10, 0, 0, 0, 0, 25, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 33, 20, 35, 14, 0, 0, 0, 0, 33, 0, 0, 0, uuid)),
	/*
	 * Armour reforges
	 */
	CLEAN(new FBItemType[] {FBItemType.HELMET, FBItemType.CHESTPLATE, FBItemType.LEGGINGS, FBItemType.BOOTS}, "clean", "misc.forgeblock.reforge.clean", false,
			(uuid) -> modifierMapFromDoubles(0, 0, 2, 0, 0, 0, 5, 5, 0, 0, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 0, 4, 0, 0, 0, 7, 7, 0, 0, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 0, 6, 0, 0, 0, 10, 10, 0, 0, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 0, 8, 0, 0, 0, 15, 15, 0, 0, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 0, 10, 0, 0, 0, 20, 20, 0, 0, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 0, 12, 0, 0, 0, 27, 27, 0, 0, 0, 0, 0, uuid)),
	FIERCE(new FBItemType[] {FBItemType.HELMET, FBItemType.CHESTPLATE, FBItemType.LEGGINGS, FBItemType.BOOTS}, "fierce", "misc.forgeblock.reforge.fierce", false,
			(uuid) -> modifierMapFromDoubles(0, 2, 2, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 4, 3, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 6, 4, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 8, 5, 14, 0, 0, 0, 0, 0, 0, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 10, 6, 18, 0, 0, 0, 0, 0, 0, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 12, 7, 23, 0, 0, 0, 0, 0, 0, 0, 0, 0, uuid)),
	HEAVY(new FBItemType[] {FBItemType.HELMET, FBItemType.CHESTPLATE, FBItemType.LEGGINGS, FBItemType.BOOTS}, "heavy", "misc.forgeblock.reforge.heavy", false,
			(uuid) -> modifierMapFromDoubles(0, 0, 0, -1, 0, 0, 0, 25, -1, 0, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 0, 0, -2, 0, 0, 0, 35, -1, 0, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 0, 0, -2, 0, 0, 0, 50, -1, 0, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 0, 0, -3, 0, 0, 0, 65, -1, 0, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 0, 0, -5, 0, 0, 0, 80, -1, 0, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 0, 0, -7, 0, 0, 0, 100, -1, 0, 0, 0, 0, uuid)),
	LIGHT(new FBItemType[] {FBItemType.HELMET, FBItemType.CHESTPLATE, FBItemType.LEGGINGS, FBItemType.BOOTS}, "light", "misc.forgeblock.reforge.light", false,
			(uuid) -> modifierMapFromDoubles(0, 0, 1, 1, 1, 0, 5, 1, 1, 0, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 0, 1, 2, 2, 0, 7, 2, 2, 0, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 0, 2, 3, 3, 0, 10, 3, 3, 0, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 0, 2, 4, 4, 0, 15, 4, 4, 0, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 0, 3, 5, 5, 0, 20, 5, 5, 0, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 0, 3, 6, 6, 0, 27, 6, 6, 0, 0, 0, 0, uuid)),
	MYTHIC(new FBItemType[] {FBItemType.HELMET, FBItemType.CHESTPLATE, FBItemType.LEGGINGS, FBItemType.BOOTS}, "mythic", "misc.forgeblock.reforge.mythic", false,
			(uuid) -> modifierMapFromDoubles(0, 2, 1, 0, 0, 0, 2, 2, 2, 20, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 4, 2, 0, 0, 0, 4, 4, 2, 25, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 6, 3, 0, 0, 0, 6, 6, 2, 30, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 8, 4, 0, 0, 0, 8, 8, 2, 40, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 10, 5, 0, 0, 0, 10, 10, 2, 50, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 12, 6, 0, 0, 0, 12, 12, 2, 65, 0, 0, 0, uuid)),
	PURE(new FBItemType[] {FBItemType.HELMET, FBItemType.CHESTPLATE, FBItemType.LEGGINGS, FBItemType.BOOTS}, "pure", "misc.forgeblock.reforge.pure", false,
			(uuid) -> modifierMapFromDoubles(0, 2, 2, 2, 1, 0, 2, 2, 1, 2, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 3, 4, 3, 1, 0, 3, 3, 1, 3, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 4, 6, 4, 2, 0, 4, 4, 1, 4, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 6, 8, 6, 3, 0, 6, 6, 1, 6, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 8, 10, 8, 4, 0, 8, 8, 1, 8, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 10, 12, 10, 5, 0, 10, 10, 1, 10, 0, 0, 0, uuid)),
	SMART(new FBItemType[] {FBItemType.HELMET, FBItemType.CHESTPLATE, FBItemType.LEGGINGS, FBItemType.BOOTS}, "smart", "misc.forgeblock.reforge.smart", false,
			(uuid) -> modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 4, 4, 0, 20, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 6, 6, 0, 40, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 9, 9, 0, 60, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 12, 12, 0, 80, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 15, 15, 0, 100, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 20, 20, 0, 120, 0, 0, 0, uuid)),
	TITANIC(new FBItemType[] {FBItemType.HELMET, FBItemType.CHESTPLATE, FBItemType.LEGGINGS, FBItemType.BOOTS}, "titanic", "misc.forgeblock.reforge.titanic", false,
			(uuid) -> modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 10, 10, 0, 0, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 15, 15, 0, 0, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 20, 20, 0, 0, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 25, 25, 0, 0, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 35, 35, 0, 0, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 50, 50, 0, 0, 0, 0, 0, uuid)),
	WISE(new FBItemType[] {FBItemType.HELMET, FBItemType.CHESTPLATE, FBItemType.LEGGINGS, FBItemType.BOOTS}, "wise", "misc.forgeblock.reforge.wise", false,
			(uuid) -> modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 6, 0, 1, 25, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 8, 0, 1, 50, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 10, 0, 1, 75, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 12, 0, 2, 100, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 15, 0, 2, 125, 0, 0, 0, uuid),
			(uuid) -> modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 20, 0, 2, 150, 0, 0, 0, uuid));
	
	private FBItemType[] types;
	private String id;
	private boolean isSpecial;
	public Function<UUID, Multimap<String, AttributeModifier>> commonModifiers;
	public Function<UUID, Multimap<String, AttributeModifier>> uncommonModifiers;
	public Function<UUID, Multimap<String, AttributeModifier>> rareModifiers;
	public Function<UUID, Multimap<String, AttributeModifier>> epicModifiers;
	public Function<UUID, Multimap<String, AttributeModifier>> legendaryModifiers;
	public Function<UUID, Multimap<String, AttributeModifier>> mythicModifiers;
	public String translationKey;
	
	private Reforge(FBItemType[] types, String id, String translationKey, boolean isSpecial, Function<UUID, Multimap<String, AttributeModifier>> commonModifiers, Function<UUID, Multimap<String, AttributeModifier>> uncommonModifiers, Function<UUID, Multimap<String, AttributeModifier>> rareModifiers, Function<UUID, Multimap<String, AttributeModifier>> epicModifiers, Function<UUID, Multimap<String, AttributeModifier>> legendaryModifiers, Function<UUID, Multimap<String, AttributeModifier>> mythicModifiers) {
		this.types = types;
		this.id = id;
		this.translationKey = translationKey;
		this.isSpecial = isSpecial;
		this.commonModifiers = commonModifiers;
		this.uncommonModifiers = uncommonModifiers;
		this.rareModifiers = rareModifiers;
		this.epicModifiers = epicModifiers;
		this.legendaryModifiers = legendaryModifiers;
		this.mythicModifiers = mythicModifiers;
	}
	
	public static Multimap<String, AttributeModifier> modifierMapFromDoubles(double damage, double strength, double critChance, double critDamage, double bonusAttackSpeed, double seaCreatureChance, double health, double defense, double speed, double intelligence, double trueDefense, double magicFind, double petLuck, UUID uuid) {
		Builder<String, AttributeModifier> builder = ImmutableMultimap.builder(); 
		if(damage != 0.0D) builder.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(uuid, "Reforge damage modifier", damage, Operation.ADDITION));
		if(strength != 0.0D) builder.put(FBAttributes.STRENGTH.getName(), new AttributeModifier(uuid, "Reforge strength modifier", strength, Operation.ADDITION));
		if(critChance != 0.0D) builder.put(FBAttributes.CRIT_CHANCE.getName(), new AttributeModifier(uuid, "Reforge crit chance modifier", critChance, Operation.ADDITION));
		if(critDamage != 0.0D) builder.put(FBAttributes.CRIT_DAMAGE.getName(), new AttributeModifier(uuid, "Reforge crit damage modifier", critDamage, Operation.ADDITION));
		if(bonusAttackSpeed != 0.0D) builder.put(FBAttributes.BONUS_ATTACK_SPEED.getName(), new AttributeModifier(uuid, "Reforge bonus attack speed modifier", bonusAttackSpeed, Operation.ADDITION));
		if(seaCreatureChance != 0.0D) builder.put(FBAttributes.SEA_CREATURE_CHANCE.getName(), new AttributeModifier(uuid, "Reforge sea creature chance modifier", seaCreatureChance, Operation.ADDITION));
		if(health != 0.0D) builder.put(SharedMonsterAttributes.MAX_HEALTH.getName(), new AttributeModifier(uuid, "Reforge health modifier", health, Operation.ADDITION));
		if(defense != 0.0D) builder.put(FBAttributes.DEFENSE.getName(), new AttributeModifier(uuid, "Reforge defense modifier", defense, Operation.ADDITION));
		if(speed != 0.0D) builder.put(SharedMonsterAttributes.MOVEMENT_SPEED.getName(), new AttributeModifier(uuid, "Reforge speed modifier", speed * 0.001D, Operation.ADDITION));
		if(intelligence != 0.0D) builder.put(FBAttributes.INTELLIGENCE.getName(), new AttributeModifier(uuid, "Reforge intelligence modifier", intelligence, Operation.ADDITION));
		if(trueDefense != 0.0D) builder.put(FBAttributes.TRUE_DEFENSE.getName(), new AttributeModifier(uuid, "Reforge true defense modifier", trueDefense, Operation.ADDITION));
		if(magicFind != 0.0D) builder.put(FBAttributes.MAGIC_FIND.getName(), new AttributeModifier(uuid, "Reforge magic find modifier", magicFind, Operation.ADDITION));
		if(petLuck != 0.0D) builder.put(FBAttributes.PET_LUCK.getName(), new AttributeModifier(uuid, "Reforge pet luck modifier", petLuck, Operation.ADDITION));
		return builder.build();
	}
	
	public static Multimap<String, AttributeModifier> emptyModifier() {
		Builder<String, AttributeModifier> builder = ImmutableMultimap.builder();
		return builder.build();
	}
	
	public static final Reforge[] REFORGES = new Reforge[] {GENTLE, ODD, FAST, FAIR, EPIC, SHARP, HEROIC, SPICY, LEGENDARY,
			CLEAN, FIERCE, HEAVY, LIGHT, MYTHIC, PURE, SMART, TITANIC, WISE};
	
	public String getID() {
		return id;
	}
	
	public static Reforge findReforgeByID(String ID) {
		for(Reforge reforge : REFORGES) {
			if(reforge.getID().contentEquals(ID)) return reforge;
		}
		return null;
	}
	
	public Multimap<String, AttributeModifier> getModifierMapByTier(FBTier tier, UUID uuid) {
		switch(tier) {
		case COMMON:
			return commonModifiers.apply(uuid);
		case UNCOMMON:
			return uncommonModifiers.apply(uuid);
		case RARE:
			return rareModifiers.apply(uuid);
		case EPIC:
			return epicModifiers.apply(uuid);
		case LEGENDARY:
			return legendaryModifiers.apply(uuid);
		case MYTHIC:
			return mythicModifiers.apply(uuid);
		default:
			return Reforge.emptyModifier();
		}
	}
	
	public boolean isSpecial() {
		return this.isSpecial;
	}
	
	public FBItemType[] getTypes() {
		return this.types;
	}
	
	public String getDisplayName() {
		return (new TranslationTextComponent(translationKey)).getString();
	}
	
	public static Reforge getRandomReforge(ItemStack stack) {
		FBItemType type = ((IFBItem) stack.getItem()).getFBItemType();
		java.util.List<Reforge> applicable = new ArrayList<>();
		for(Reforge reforge : REFORGES) {
			if((Arrays.asList(reforge.getTypes())).contains(type) && !reforge.isSpecial() && !reforge.equals(((IReforgeableItem) stack.getItem()).getReforge(stack))) {
				applicable.add(reforge);
			}
		}
		
		return applicable.get((new Random()).nextInt(applicable.size()));
	}
}
