package alephinfinity1.forgeblock.misc.reforge;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import com.google.common.collect.Multimap;

import alephinfinity1.forgeblock.attribute.FBAttributes;
import alephinfinity1.forgeblock.misc.FBItemType;
import alephinfinity1.forgeblock.misc.tier.FBTier;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.util.text.TranslationTextComponent;

//See https://hypixel-skyblock.fandom.com/wiki/Reforging
public enum Reforge {
	//Melee weapon reforges
	GENTLE(new FBItemType[] {FBItemType.SWORD}, "gentle", "misc.forgeblock.reforge.gentle", false, 
			modifierMapFromDoubles(0, 3, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0), 
			modifierMapFromDoubles(0, 5, 0, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0), 
			modifierMapFromDoubles(0, 7, 0, 0, 15, 0, 0, 0, 0, 0, 0, 0, 0), 
			modifierMapFromDoubles(0, 10, 0, 0, 20, 0, 0, 0, 0, 0, 0, 0, 0), 
			modifierMapFromDoubles(0, 15, 0, 0, 25, 0, 0, 0, 0, 0, 0, 0, 0), 
			modifierMapFromDoubles(0, 20, 0, 0, 30, 0, 0, 0, 0, 0, 0, 0, 0)),
	ODD(new FBItemType[] {FBItemType.SWORD}, "odd", "misc.forgeblock.reforge.odd", false, 
			modifierMapFromDoubles(0, 0, 12, 10, 0, 0, 0, 0, 0, -5, 0, 0, 0), 
			modifierMapFromDoubles(0, 0, 15, 15, 0, 0, 0, 0, 0, -10, 0, 0, 0), 
			modifierMapFromDoubles(0, 0, 15, 15, 0, 0, 0, 0, 0, -18, 0, 0, 0), 
			modifierMapFromDoubles(0, 0, 25, 30, 0, 0, 0, 0, 0, -24, 0, 0, 0), 
			modifierMapFromDoubles(0, 0, 25, 30, 0, 0, 0, 0, 0, -50, 0, 0, 0), 
			modifierMapFromDoubles(0, 0, 35, 45, 0, 0, 0, 0, 0, -75, 0, 0, 0)),
	FAST(new FBItemType[] {FBItemType.SWORD}, "fast", "misc.forgeblock.reforge.fast", false, 
			modifierMapFromDoubles(0, 0, 0, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0),
			modifierMapFromDoubles(0, 0, 0, 0, 20, 0, 0, 0, 0, 0, 0, 0, 0),
			modifierMapFromDoubles(0, 0, 0, 0, 30, 0, 0, 0, 0, 0, 0, 0, 0),
			modifierMapFromDoubles(0, 0, 0, 0, 40, 0, 0, 0, 0, 0, 0, 0, 0),
			modifierMapFromDoubles(0, 0, 0, 0, 50, 0, 0, 0, 0, 0, 0, 0, 0),
			modifierMapFromDoubles(0, 0, 0, 0, 60, 0, 0, 0, 0, 0, 0, 0, 0)),
	FAIR(new FBItemType[] {FBItemType.SWORD}, "fair", "misc.forgeblock.reforge.fair", false,
			modifierMapFromDoubles(0, 2, 2, 2, 2, 0, 0, 0, 0, 2, 0, 0, 0),
			modifierMapFromDoubles(0, 3, 3, 3, 3, 0, 0, 0, 0, 3, 0, 0, 0),
			modifierMapFromDoubles(0, 4, 4, 4, 4, 0, 0, 0, 0, 4, 0, 0, 0),
			modifierMapFromDoubles(0, 7, 7, 7, 7, 0, 0, 0, 0, 7, 0, 0, 0),
			modifierMapFromDoubles(0, 10, 10, 10, 10, 0, 0, 0, 0, 10, 0, 0, 0),
			modifierMapFromDoubles(0, 14, 14, 14, 14, 0, 0, 0, 0, 14, 0, 0, 0)),
	EPIC(new FBItemType[] {FBItemType.SWORD}, "epic", "misc.forgeblock.reforge.epic", false,
			modifierMapFromDoubles(0, 15, 0, 10, 1, 0, 0, 0, 0, 0, 0, 0, 0),
			modifierMapFromDoubles(0, 20, 0, 15, 2, 0, 0, 0, 0, 0, 0, 0, 0),
			modifierMapFromDoubles(0, 25, 0, 20, 4, 0, 0, 0, 0, 0, 0, 0, 0),
			modifierMapFromDoubles(0, 32, 0, 27, 7, 0, 0, 0, 0, 0, 0, 0, 0),
			modifierMapFromDoubles(0, 40, 0, 35, 10, 0, 0, 0, 0, 0, 0, 0, 0),
			modifierMapFromDoubles(0, 49, 0, 45, 14, 0, 0, 0, 0, 0, 0, 0, 0)),
	SHARP(new FBItemType[] {FBItemType.SWORD}, "sharp", "misc.forgeblock.reforge.sharp", false,
			modifierMapFromDoubles(0, 0, 10, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0),
			modifierMapFromDoubles(0, 0, 12, 30, 0, 0, 0, 0, 0, 0, 0, 0, 0),
			modifierMapFromDoubles(0, 0, 14, 40, 0, 0, 0, 0, 0, 0, 0, 0, 0),
			modifierMapFromDoubles(0, 0, 17, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0),
			modifierMapFromDoubles(0, 0, 20, 75, 0, 0, 0, 0, 0, 0, 0, 0, 0),
			modifierMapFromDoubles(0, 0, 25, 100, 0, 0, 0, 0, 0, 0, 0, 0, 0)),
	HEROIC(new FBItemType[] {FBItemType.SWORD}, "heroic", "misc.forgeblock.reforge.heroic", false,
			modifierMapFromDoubles(0, 15, 0, 0, 1, 0, 0, 0, 0, 40, 0, 0, 0),
			modifierMapFromDoubles(0, 20, 0, 0, 2, 0, 0, 0, 0, 50, 0, 0, 0),
			modifierMapFromDoubles(0, 25, 0, 0, 2, 0, 0, 0, 0, 65, 0, 0, 0),
			modifierMapFromDoubles(0, 32, 0, 0, 3, 0, 0, 0, 0, 80, 0, 0, 0),
			modifierMapFromDoubles(0, 40, 0, 0, 5, 0, 0, 0, 0, 100, 0, 0, 0),
			modifierMapFromDoubles(0, 49, 0, 0, 7, 0, 0, 0, 0, 125, 0, 0, 0)),
	SPICY(new FBItemType[] {FBItemType.SWORD}, "spicy", "misc.forgeblock.reforge.spicy", false,
			modifierMapFromDoubles(0, 2, 1, 25, 1, 0, 0, 0, 0, 0, 0, 0, 0),
			modifierMapFromDoubles(0, 3, 1, 35, 2, 0, 0, 0, 0, 0, 0, 0, 0),
			modifierMapFromDoubles(0, 4, 1, 45, 4, 0, 0, 0, 0, 0, 0, 0, 0),
			modifierMapFromDoubles(0, 7, 1, 60, 7, 0, 0, 0, 0, 0, 0, 0, 0),
			modifierMapFromDoubles(0, 10, 1, 80, 10, 0, 0, 0, 0, 0, 0, 0, 0),
			modifierMapFromDoubles(0, 14, 1, 105, 14, 0, 0, 0, 0, 0, 0, 0, 0)),
	LEGENDARY(new FBItemType[] {FBItemType.SWORD}, "legendary", "misc.forgeblock.reforge.legendary", false,
			modifierMapFromDoubles(0, 3, 5, 5, 2, 0, 0, 0, 0, 5, 0, 0, 0),
			modifierMapFromDoubles(0, 7, 7, 10, 3, 0, 0, 0, 0, 8, 0, 0, 0),
			modifierMapFromDoubles(0, 12, 9, 15, 5, 0, 0, 0, 0, 12, 0, 0, 0),
			modifierMapFromDoubles(0, 18, 12, 22, 7, 0, 0, 0, 0, 18, 0, 0, 0),
			modifierMapFromDoubles(0, 25, 15, 28, 10, 0, 0, 0, 0, 25, 0, 0, 0),
			modifierMapFromDoubles(0, 33, 20, 35, 14, 0, 0, 0, 0, 33, 0, 0, 0));
	
	private FBItemType[] types;
	private String id;
	private boolean isSpecial;
	public Multimap<String, AttributeModifier> commonModifiers;
	public Multimap<String, AttributeModifier> uncommonModifiers;
	public Multimap<String, AttributeModifier> rareModifiers;
	public Multimap<String, AttributeModifier> epicModifiers;
	public Multimap<String, AttributeModifier> legendaryModifiers;
	public Multimap<String, AttributeModifier> mythicModifiers;
	public String translationKey;
	
	private Reforge(FBItemType[] types, String id, String translationKey, boolean isSpecial, Multimap<String, AttributeModifier> commonModifiers, Multimap<String, AttributeModifier> uncommonModifiers, Multimap<String, AttributeModifier> rareModifiers, Multimap<String, AttributeModifier> epicModifiers, Multimap<String, AttributeModifier> legendaryModifiers, Multimap<String, AttributeModifier> mythicModifiers) {
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
	
	public static Multimap<String, AttributeModifier> modifierMapFromDoubles(double damage, double strength, double critChance, double critDamage, double bonusAttackSpeed, double seaCreatureChance, double health, double defense, double speed, double intelligence, double trueDefense, double magicFind, double petLuck) {
		Builder<String, AttributeModifier> builder = ImmutableMultimap.builder(); 
		if(damage != 0.0D) builder.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier("Reforge damage modifier", damage, Operation.ADDITION));
		if(strength != 0.0D) builder.put(FBAttributes.STRENGTH.getName(), new AttributeModifier("Reforge strength modifier", strength, Operation.ADDITION));
		if(critChance != 0.0D) builder.put(FBAttributes.CRIT_CHANCE.getName(), new AttributeModifier("Reforge crit chance modifier", critChance, Operation.ADDITION));
		if(critDamage != 0.0D) builder.put(FBAttributes.CRIT_DAMAGE.getName(), new AttributeModifier("Reforge crit damage modifier", critDamage, Operation.ADDITION));
		if(bonusAttackSpeed != 0.0D) builder.put(FBAttributes.BONUS_ATTACK_SPEED.getName(), new AttributeModifier("Reforge bonus attack speed modifier", bonusAttackSpeed, Operation.ADDITION));
		if(seaCreatureChance != 0.0D) builder.put(FBAttributes.SEA_CREATURE_CHANCE.getName(), new AttributeModifier("Reforge sea creature chance modifier", seaCreatureChance, Operation.ADDITION));
		if(health != 0.0D) builder.put(SharedMonsterAttributes.MAX_HEALTH.getName(), new AttributeModifier("Reforge health modifier", health, Operation.ADDITION));
		if(defense != 0.0D) builder.put(FBAttributes.DEFENSE.getName(), new AttributeModifier("Reforge defense modifier", defense, Operation.ADDITION));
		if(speed != 0.0D) builder.put(SharedMonsterAttributes.MOVEMENT_SPEED.getName(), new AttributeModifier("Reforge speed modifier", speed, Operation.ADDITION));
		if(intelligence != 0.0D) builder.put(FBAttributes.INTELLIGENCE.getName(), new AttributeModifier("Reforge intelligence modifier", intelligence, Operation.ADDITION));
		if(trueDefense != 0.0D) builder.put(FBAttributes.TRUE_DEFENSE.getName(), new AttributeModifier("Reforge true defense modifier", trueDefense, Operation.ADDITION));
		if(magicFind != 0.0D) builder.put(FBAttributes.MAGIC_FIND.getName(), new AttributeModifier("Reforge magic find modifier", magicFind, Operation.ADDITION));
		if(petLuck != 0.0D) builder.put(FBAttributes.PET_LUCK.getName(), new AttributeModifier("Reforge pet luck modifier", petLuck, Operation.ADDITION));
		return builder.build();
	}
	
	public static Multimap<String, AttributeModifier> emptyModifier() {
		Builder<String, AttributeModifier> builder = ImmutableMultimap.builder();
		return builder.build();
	}
	
	public static final Reforge[] REFORGES = new Reforge[] {GENTLE, ODD, FAST, FAIR, EPIC, SHARP, HEROIC, SPICY, LEGENDARY};
	
	public String getID() {
		return id;
	}
	
	public static Reforge findReforgeByID(String ID) {
		for(Reforge reforge : REFORGES) {
			if(reforge.getID().contentEquals(ID)) return reforge;
		}
		return null;
	}
	
	public Multimap<String, AttributeModifier> getModifierMapByTier(FBTier tier) {
		switch(tier) {
		case COMMON:
			return commonModifiers;
		case UNCOMMON:
			return uncommonModifiers;
		case RARE:
			return rareModifiers;
		case EPIC:
			return epicModifiers;
		case LEGENDARY:
			return legendaryModifiers;
		case MYTHIC:
			return mythicModifiers;
		default:
			return Reforge.emptyModifier();
		}
	}
	
	public String getDisplayName() {
		return (new TranslationTextComponent(translationKey)).getString();
	}
}
