package alephinfinity1.forgeblock.item;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import com.google.common.collect.Multimap;

import alephinfinity1.forgeblock.attribute.FBAttributes;
import alephinfinity1.forgeblock.misc.DisplayHelper;
import alephinfinity1.forgeblock.misc.FBItemType;
import alephinfinity1.forgeblock.misc.reforge.IReforgeableItem;
import alephinfinity1.forgeblock.misc.reforge.Reforge;
import alephinfinity1.forgeblock.misc.tier.FBTier;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class FBSwordItem extends SwordItem implements IFBTieredItem, IReforgeableItem {

	private final FBTier tier;
	private final Multimap<String, AttributeModifier> attributes;
	
	protected static final UUID STRENGTH_MODIFIER = UUID.fromString("0a8af9f9-7880-40af-a8ff-17e6d98ec482");
	protected static final UUID CRIT_CHANCE_MODIFIER = UUID.fromString("5265014e-5ab6-4e86-a9a5-7c9117818fbb");
	protected static final UUID CRIT_DAMAGE_MODIFIER = UUID.fromString("dbda354b-eec5-4b86-88ec-04c9f232bc62");
	
	//Super constructor, highly recommend not using
	@Deprecated
	public FBSwordItem(IItemTier p_i48460_1_, int p_i48460_2_, float p_i48460_3_, Properties p_i48460_4_) {
		super(p_i48460_1_, p_i48460_2_, p_i48460_3_, p_i48460_4_);
		tier = FBTier.COMMON;
		Builder<String, AttributeModifier> builder = ImmutableMultimap.builder();
		attributes = builder.build();
	}
	
	public FBSwordItem(Properties props, FBTier tier, double attackDamageIn, double strengthIn, double critChanceIn, double critDamageIn) {
		super(new FBItemTier(), (int) attackDamageIn, (float) Double.MAX_VALUE, props);
		this.tier = tier;
		Builder<String, AttributeModifier> builder = ImmutableMultimap.builder();
		builder.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Attack damage modifier", attackDamageIn, Operation.ADDITION));
		builder.put(FBAttributes.STRENGTH.getName(), new AttributeModifier(STRENGTH_MODIFIER, "Strength modifier", strengthIn, Operation.ADDITION));
		builder.put(FBAttributes.CRIT_CHANCE.getName(), new AttributeModifier(CRIT_CHANCE_MODIFIER, "Crit chance modifier", critChanceIn, Operation.ADDITION));
		builder.put(FBAttributes.CRIT_DAMAGE.getName(), new AttributeModifier(CRIT_DAMAGE_MODIFIER, "Crit damage modifier", critDamageIn, Operation.ADDITION));
		attributes = builder.build();
	}
	
	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot) {
		return equipmentSlot == EquipmentSlotType.MAINHAND ? this.attributes : super.getAttributeModifiers(equipmentSlot);
	}
	
	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot, ItemStack stack) {
		if(equipmentSlot != EquipmentSlotType.MAINHAND) return super.getAttributeModifiers(equipmentSlot);
		Builder<String, AttributeModifier> builder = ImmutableMultimap.builder();
		builder.putAll(super.getAttributeModifiers(equipmentSlot, stack));
		builder.putAll(this.getReforgeModifiers(stack));
		return builder.build();
	}
	
	@Override
	public FBTier getStackTier(ItemStack stack) {
		if(stack.getTag() != null) {
			boolean recombobulated = (stack.getTag().getByte("Recombobulated") == 1);
			boolean woodSingularity = (stack.getTag().getByte("WoodSingularity") == 1);
			int tierBoost = 0;
			if(recombobulated) tierBoost++;
			if(woodSingularity) tierBoost++;
			return FBTier.changeTier(tier, tierBoost);
		} else {
			return tier;
		}
	}
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		FBTier tier = this.getStackTier(stack);
		tooltip.add(new StringTextComponent(tier.color.toString() + tooltip.get(0).getString()));
		tooltip.remove(0);
		
		Multimap<String, AttributeModifier> modifiers = this.getAttributeModifiers(EquipmentSlotType.MAINHAND, stack);
		Multimap<String, AttributeModifier> reforgeModifiers = this.getReforgeModifiers(stack);
		
		//Base weapon stats
		double damage = 0.0D;
		double strength = 0.0D;
		double critChance = 0.0D;
		double critDamage = 0.0D;
		double bonusAttackSpeed = 0.0D;
		double seaCreatureChance = 0.0D;
		
		double health = 0.0D;
		double defense = 0.0D;
		double speed = 0.0D;
		double intelligence = 0.0D;
		double trueDefense = 0.0D;
		double magicFind = 0.0D;
		double petLuck = 0.0D;
		
		//Reforge bonuses
		double reforgeDamage = 0.0D;
		double reforgeStrength = 0.0D;
		double reforgeCritChance = 0.0D;
		double reforgeCritDamage = 0.0D;
		double reforgeBonusAttackSpeed = 0.0D;
		double reforgeSeaCreatureChance = 0.0D;
		
		double reforgeHealth = 0.0D;
		double reforgeDefense = 0.0D;
		double reforgeSpeed = 0.0D;
		double reforgeIntelligence = 0.0D;
		double reforgeTrueDefense = 0.0D;
		double reforgeMagicFind = 0.0D;
		double reforgePetLuck = 0.0D;
		
		//Reforge name
		String reforgeName = "";
		
		if(this.getReforge(stack) != null) reforgeName = this.getReforge(stack).getDisplayName();
		
		for(AttributeModifier modifier : modifiers.get(SharedMonsterAttributes.ATTACK_DAMAGE.getName())) {
			if(modifier.getOperation().equals(Operation.ADDITION)) {
				damage += modifier.getAmount();
			}
		}
		
		for(AttributeModifier modifier : modifiers.get(FBAttributes.STRENGTH.getName())) {
			if(modifier.getOperation().equals(Operation.ADDITION)) {
				strength += modifier.getAmount();
			}
		}
		
		for(AttributeModifier modifier : modifiers.get(FBAttributes.CRIT_CHANCE.getName())) {
			if(modifier.getOperation().equals(Operation.ADDITION)) {
				critChance += modifier.getAmount();
			}
		}
		
		for(AttributeModifier modifier : modifiers.get(FBAttributes.CRIT_DAMAGE.getName())) {
			if(modifier.getOperation().equals(Operation.ADDITION)) {
				critDamage += modifier.getAmount();
			}
		}
		
		for(AttributeModifier modifier : modifiers.get(FBAttributes.BONUS_ATTACK_SPEED.getName())) {
			if(modifier.getOperation().equals(Operation.ADDITION)) {
				bonusAttackSpeed += modifier.getAmount();
			}
		}
		
		for(AttributeModifier modifier : modifiers.get(FBAttributes.SEA_CREATURE_CHANCE.getName())) {
			if(modifier.getOperation().equals(Operation.ADDITION)) {
				seaCreatureChance += modifier.getAmount();
			}
		}
		
		for(AttributeModifier modifier : modifiers.get(SharedMonsterAttributes.MAX_HEALTH.getName())) {
			if(modifier.getOperation().equals(Operation.ADDITION)) {
				health += modifier.getAmount();
			}
		}
		
		for(AttributeModifier modifier : modifiers.get(FBAttributes.DEFENSE.getName())) {
			if(modifier.getOperation().equals(Operation.ADDITION)) {
				defense += modifier.getAmount();
			}
		}
		
		for(AttributeModifier modifier : modifiers.get(SharedMonsterAttributes.MOVEMENT_SPEED.getName())) {
			if(modifier.getOperation().equals(Operation.ADDITION)) {
				speed += modifier.getAmount();
			}
		}
		
		for(AttributeModifier modifier : modifiers.get(FBAttributes.INTELLIGENCE.getName())) {
			if(modifier.getOperation().equals(Operation.ADDITION)) {
				intelligence += modifier.getAmount();
			}
		}
		
		for(AttributeModifier modifier : modifiers.get(FBAttributes.TRUE_DEFENSE.getName())) {
			if(modifier.getOperation().equals(Operation.ADDITION)) {
				trueDefense += modifier.getAmount();
			}
		}
		
		for(AttributeModifier modifier : modifiers.get(FBAttributes.MAGIC_FIND.getName())) {
			if(modifier.getOperation().equals(Operation.ADDITION)) {
				magicFind += modifier.getAmount();
			}
		}
		
		for(AttributeModifier modifier : modifiers.get(FBAttributes.PET_LUCK.getName())) {
			if(modifier.getOperation().equals(Operation.ADDITION)) {
				petLuck += modifier.getAmount();
			}
		}
		
		//Reforge modifiers
		for(AttributeModifier modifier : reforgeModifiers.get(SharedMonsterAttributes.ATTACK_DAMAGE.getName())) {
			if(modifier.getOperation().equals(Operation.ADDITION)) {
				reforgeDamage += modifier.getAmount();
			}
		}
		
		for(AttributeModifier modifier : reforgeModifiers.get(FBAttributes.STRENGTH.getName())) {
			if(modifier.getOperation().equals(Operation.ADDITION)) {
				reforgeStrength += modifier.getAmount();
			}
		}
		
		for(AttributeModifier modifier : reforgeModifiers.get(FBAttributes.CRIT_CHANCE.getName())) {
			if(modifier.getOperation().equals(Operation.ADDITION)) {
				reforgeCritChance += modifier.getAmount();
			}
		}
		
		for(AttributeModifier modifier : reforgeModifiers.get(FBAttributes.CRIT_DAMAGE.getName())) {
			if(modifier.getOperation().equals(Operation.ADDITION)) {
				reforgeCritDamage += modifier.getAmount();
			}
		}
		
		for(AttributeModifier modifier : reforgeModifiers.get(FBAttributes.BONUS_ATTACK_SPEED.getName())) {
			if(modifier.getOperation().equals(Operation.ADDITION)) {
				reforgeBonusAttackSpeed += modifier.getAmount();
			}
		}
		
		for(AttributeModifier modifier : reforgeModifiers.get(FBAttributes.SEA_CREATURE_CHANCE.getName())) {
			if(modifier.getOperation().equals(Operation.ADDITION)) {
				reforgeSeaCreatureChance += modifier.getAmount();
			}
		}
		
		for(AttributeModifier modifier : reforgeModifiers.get(SharedMonsterAttributes.MAX_HEALTH.getName())) {
			if(modifier.getOperation().equals(Operation.ADDITION)) {
				reforgeHealth += modifier.getAmount();
			}
		}
		
		for(AttributeModifier modifier : reforgeModifiers.get(FBAttributes.DEFENSE.getName())) {
			if(modifier.getOperation().equals(Operation.ADDITION)) {
				reforgeDefense += modifier.getAmount();
			}
		}
		
		for(AttributeModifier modifier : reforgeModifiers.get(SharedMonsterAttributes.MOVEMENT_SPEED.getName())) {
			if(modifier.getOperation().equals(Operation.ADDITION)) {
				reforgeSpeed += modifier.getAmount();
			}
		}
		
		for(AttributeModifier modifier : reforgeModifiers.get(FBAttributes.INTELLIGENCE.getName())) {
			if(modifier.getOperation().equals(Operation.ADDITION)) {
				reforgeIntelligence += modifier.getAmount();
			}
		}
		
		for(AttributeModifier modifier : reforgeModifiers.get(FBAttributes.TRUE_DEFENSE.getName())) {
			if(modifier.getOperation().equals(Operation.ADDITION)) {
				reforgeTrueDefense += modifier.getAmount();
			}
		}
		
		for(AttributeModifier modifier : reforgeModifiers.get(FBAttributes.MAGIC_FIND.getName())) {
			if(modifier.getOperation().equals(Operation.ADDITION)) {
				reforgeMagicFind += modifier.getAmount();
			}
		}
		
		for(AttributeModifier modifier : reforgeModifiers.get(FBAttributes.PET_LUCK.getName())) {
			if(modifier.getOperation().equals(Operation.ADDITION)) {
				reforgePetLuck += modifier.getAmount();
			}
		}
		
		boolean offensiveStats = damage != 0.0D || strength != 0.0D || critChance != 0.0D || critDamage != 0.0D || bonusAttackSpeed != 0.0D || seaCreatureChance != 0.0D;
		boolean defensiveStats = health != 0.0D || defense != 0.0D || speed != 0.0D || intelligence != 0.0D || trueDefense != 0.0D || magicFind != 0.0D || petLuck != 0.0D;
		
		if(damage != 0.0D) {
			if(reforgeDamage == 0.0D) tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("misc.forgeblock.fakeattribute.damage").getString() + ": " + TextFormatting.RED.toString() + DisplayHelper.formatModifier(damage)));
			else tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("misc.forgeblock.fakeattribute.damage").getString() + ": " + TextFormatting.RED.toString() + DisplayHelper.formatModifier(damage) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + DisplayHelper.formatModifier(reforgeDamage) + ")"));
		} else if(reforgeDamage != 0.0D) {
			tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("misc.forgeblock.fakeattribute.damage").getString() + ": " + TextFormatting.RED.toString() + DisplayHelper.formatModifier(damage) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + DisplayHelper.formatModifier(reforgeDamage) + ")"));
		}
		
		if(strength != 0.0D) {
			if(reforgeStrength == 0.0D) tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.strength").getString() + ": " + TextFormatting.RED.toString() + DisplayHelper.formatModifier(strength)));
			else tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.strength").getString() + ": " + TextFormatting.RED.toString() + DisplayHelper.formatModifier(strength) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + DisplayHelper.formatModifier(reforgeStrength) + ")"));
		} else if(reforgeStrength != 0.0D) {
			tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.strength").getString() + ": " + TextFormatting.RED.toString() + DisplayHelper.formatModifier(strength) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + DisplayHelper.formatModifier(reforgeStrength) + ")"));
		}
		
		if(critChance != 0.0D) {
			if(reforgeCritChance == 0.0D) tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.critChance").getString() + ": " + TextFormatting.RED.toString() + DisplayHelper.formatModifier(critChance)));
			else tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.critChance").getString() + ": " + TextFormatting.RED.toString() + DisplayHelper.formatModifier(critChance) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + DisplayHelper.formatModifier(reforgeCritChance) + ")"));
		} else if(reforgeCritChance != 0.0D) {
			tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.critChance").getString() + ": " + TextFormatting.RED.toString() + DisplayHelper.formatModifier(critChance) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + DisplayHelper.formatModifier(reforgeCritChance) + ")"));
		}
		
		if(critDamage != 0.0D) {
			if(reforgeCritDamage == 0.0D) tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.critDamage").getString() + ": " + TextFormatting.RED.toString() + DisplayHelper.formatModifier(critDamage)));
			else tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.critDamage").getString() + ": " + TextFormatting.RED.toString() + DisplayHelper.formatModifier(critDamage) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + DisplayHelper.formatModifier(reforgeCritDamage) + ")"));
		} else if(reforgeCritDamage != 0.0D) {
			tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.critDamage").getString() + ": " + TextFormatting.RED.toString() + DisplayHelper.formatModifier(critDamage) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + DisplayHelper.formatModifier(reforgeCritDamage) + ")"));
		}
		
		if(bonusAttackSpeed != 0.0D) {
			if(reforgeBonusAttackSpeed == 0.0D) tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.bonusAttackSpeed").getString() + ": " + TextFormatting.RED.toString() + DisplayHelper.formatModifier(bonusAttackSpeed)));
			else tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.bonusAttackSpeed").getString() + ": " + TextFormatting.RED.toString() + DisplayHelper.formatModifier(bonusAttackSpeed) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + DisplayHelper.formatModifier(reforgeBonusAttackSpeed) + ")"));
		} else if(reforgeBonusAttackSpeed != 0.0D) {
			tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.bonusAttackSpeed").getString() + ": " + TextFormatting.RED.toString() + DisplayHelper.formatModifier(bonusAttackSpeed) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + DisplayHelper.formatModifier(reforgeBonusAttackSpeed) + ")"));
		}
		
		if(seaCreatureChance != 0.0D) {
			if(reforgeSeaCreatureChance == 0.0D) tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.seaCreatureChance").getString() + ": " + TextFormatting.RED.toString() + DisplayHelper.formatModifier(seaCreatureChance)));
			else tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.seaCreatureChance").getString() + ": " + TextFormatting.RED.toString() + DisplayHelper.formatModifier(seaCreatureChance) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + DisplayHelper.formatModifier(reforgeSeaCreatureChance) + ")"));
		} else if(reforgeSeaCreatureChance != 0.0D) {
			tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.seaCreatureChance").getString() + ": " + TextFormatting.RED.toString() + DisplayHelper.formatModifier(seaCreatureChance) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + DisplayHelper.formatModifier(reforgeSeaCreatureChance) + ")"));
		}
		
		if(offensiveStats && defensiveStats) tooltip.add(new StringTextComponent("")); //Separates offensive and defensive stats
		
		if(health != 0.0D) {
			if(reforgeHealth == 0.0D) tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("misc.forgeblock.fakeattribute.health").getString() + ": " + TextFormatting.GREEN.toString() + DisplayHelper.formatModifier(health)));
			else tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("misc.forgeblock.fakeattribute.health").getString() + ": " + TextFormatting.GREEN.toString() + DisplayHelper.formatModifier(health) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + DisplayHelper.formatModifier(reforgeHealth) + ")"));
		} else if(reforgeHealth != 0.0D) {
			tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("misc.forgeblock.fakeattribute.health").getString() + ": " + TextFormatting.GREEN.toString() + DisplayHelper.formatModifier(health) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + DisplayHelper.formatModifier(reforgeHealth) + ")"));
		}
		
		if(defense != 0.0D) {
			if(reforgeDefense == 0.0D) tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.defense").getString() + ": " + TextFormatting.GREEN.toString() + DisplayHelper.formatModifier(defense)));
			else tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.defense").getString() + ": " + TextFormatting.GREEN.toString() + DisplayHelper.formatModifier(defense) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + DisplayHelper.formatModifier(reforgeDefense) + ")"));
		} else if(reforgeDefense != 0.0D) {
			tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.defense").getString() + ": " + TextFormatting.GREEN.toString() + DisplayHelper.formatModifier(defense) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + DisplayHelper.formatModifier(reforgeDefense) + ")"));
		}
		
		if(speed != 0.0D) {
			if(reforgeSpeed == 0.0D) tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("misc.forgeblock.fakeattribute.speed").getString() + ": " + TextFormatting.GREEN.toString() + DisplayHelper.formatModifier(speed * 1000.0D)));
			else tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("misc.forgeblock.fakeattribute.speed").getString() + ": " + TextFormatting.GREEN.toString() + DisplayHelper.formatModifier(speed * 1000.0D) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + DisplayHelper.formatModifier(reforgeSpeed * 1000.0D) + ")"));
		} else if(reforgeSpeed != 0.0D) {
			tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("misc.forgeblock.fakeattribute.speed").getString() + ": " + TextFormatting.GREEN.toString() + DisplayHelper.formatModifier(speed * 1000.0D) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + DisplayHelper.formatModifier(reforgeSpeed * 1000.0D) + ")"));
		}
		
		if(intelligence != 0.0D) {
			if(reforgeIntelligence == 0.0D) tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.intelligence").getString() + ": " + TextFormatting.GREEN.toString() + DisplayHelper.formatModifier(intelligence)));
			else tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.intelligence").getString() + ": " + TextFormatting.GREEN.toString() + DisplayHelper.formatModifier(intelligence) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + DisplayHelper.formatModifier(reforgeIntelligence) + ")"));
		} else if(reforgeIntelligence != 0.0D) {
			tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.intelligence").getString() + ": " + TextFormatting.GREEN.toString() + DisplayHelper.formatModifier(intelligence) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + DisplayHelper.formatModifier(reforgeIntelligence) + ")"));
		}
		
		if(trueDefense != 0.0D) {
			if(reforgeTrueDefense == 0.0D) tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.trueDefense").getString() + ": " + TextFormatting.GREEN.toString() + DisplayHelper.formatModifier(trueDefense)));
			else tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.trueDefense").getString() + ": " + TextFormatting.GREEN.toString() + DisplayHelper.formatModifier(trueDefense) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + DisplayHelper.formatModifier(reforgeTrueDefense) + ")"));
		} else if(reforgeTrueDefense != 0.0D) {
			tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.trueDefense").getString() + ": " + TextFormatting.GREEN.toString() + DisplayHelper.formatModifier(trueDefense) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + DisplayHelper.formatModifier(reforgeTrueDefense) + ")"));
		}
		
		if(magicFind != 0.0D) {
			if(reforgeMagicFind == 0.0D) tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.magicFind").getString() + ": " + TextFormatting.GREEN.toString() + DisplayHelper.formatModifier(magicFind)));
			else tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.magicFind").getString() + ": " + TextFormatting.GREEN.toString() + DisplayHelper.formatModifier(magicFind) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + DisplayHelper.formatModifier(reforgeMagicFind) + ")"));
		} else if(reforgeMagicFind != 0.0D) {
			tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.magicFind").getString() + ": " + TextFormatting.GREEN.toString() + DisplayHelper.formatModifier(magicFind) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + DisplayHelper.formatModifier(reforgeMagicFind) + ")"));
		}
		
		if(petLuck != 0.0D) {
			if(reforgePetLuck == 0.0D) tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.petLuck").getString() + ": " + TextFormatting.GREEN.toString() + DisplayHelper.formatModifier(petLuck)));
			else tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.petLuck").getString() + ": " + TextFormatting.GREEN.toString() + DisplayHelper.formatModifier(petLuck) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + DisplayHelper.formatModifier(reforgePetLuck) + ")"));
		} else if(reforgePetLuck != 0.0D) {
			tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("attribute.name.forgeblock.petLuck").getString() + ": " + TextFormatting.GREEN.toString() + DisplayHelper.formatModifier(petLuck) + TextFormatting.BLUE.toString() + " (" + reforgeName + " " + DisplayHelper.formatModifier(reforgePetLuck) + ")"));
		}
		
		tooltip.add(new StringTextComponent(""));
		
		//Insert enchantments here
		Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(stack);
		Set<Map.Entry<Enchantment, Integer>> set = enchantments.entrySet();
		for(Map.Entry<Enchantment, Integer> entry : set) {
			tooltip.add(new StringTextComponent(TextFormatting.BLUE.toString() + new TranslationTextComponent(entry.getKey().getName()).getString() + " " + DisplayHelper.getRomanNumeral(entry.getValue())));
		}
		
		if(!set.isEmpty()) tooltip.add(new StringTextComponent(""));
		
		//Insert item ability description here (unused for some swords)
		
		//If this item is reforgeable but not reforged
		if(this.getReforge(stack) == null) tooltip.add(new StringTextComponent(new TranslationTextComponent("text.forgeblock.reforgeable").getString()));
		
		boolean recombobulated = false;
		if(stack.getTag() != null) recombobulated = (stack.getTag().getByte("Recombobulated") == 1);
		String color = tier.color.toString();
		String bold = TextFormatting.BOLD.toString();
		String obfuscated = TextFormatting.OBFUSCATED.toString();
		String reset = TextFormatting.RESET.toString();
		if(!recombobulated) tooltip.add(new StringTextComponent(color + bold + tier.name.getString() + " " + this.getFBItemType().getDisplayName()));
		else tooltip.add(new StringTextComponent(color + bold + obfuscated + "n " + reset + color + bold + tier.name.getString() + " " + this.getFBItemType().getDisplayName() + obfuscated + " n"));
	}

	@Override
	public FBTier getFBTier() {
		return tier;
	}

	@Override
	@Nullable
	public Reforge getReforge(ItemStack stack) {
		if(stack.getTag() == null) return null;
		String reforgeName = stack.getTag().getString("Reforge");
		if(reforgeName.isEmpty()) return null;
		else return Reforge.findReforgeByID(reforgeName);
	}

	@Override
	public Multimap<String, AttributeModifier> getReforgeModifiers(ItemStack stack) {
		if(getReforge(stack) == null) return Reforge.emptyModifier();
		else {
			Reforge reforge = getReforge(stack);
			switch(getStackTier(stack)) {
			case COMMON:
				return reforge.commonModifiers;
			case UNCOMMON:
				return reforge.uncommonModifiers;
			case RARE:
				return reforge.rareModifiers;
			case EPIC:
				return reforge.epicModifiers;
			case LEGENDARY:
				return reforge.legendaryModifiers;
			case MYTHIC:
				return reforge.mythicModifiers;
			default:
				return Reforge.emptyModifier();
			}
		}
	}
	
	@Override
	public ITextComponent getDisplayName(ItemStack stack) {
		String reforgeName = "";
		if(this.getReforge(stack) != null) {
			reforgeName = this.getReforge(stack).getDisplayName();
		}
		FBTier tier = getStackTier(stack);
		String color = tier.color.toString();
		if(this.getReforge(stack) != null)
			return new StringTextComponent(color + reforgeName + " " + new TranslationTextComponent(this.getTranslationKey(stack)).getString());
		else
			return new StringTextComponent(color + new TranslationTextComponent(this.getTranslationKey(stack)).getString());
	}

	@Override
	public void setReforge(Reforge reforge, ItemStack stack) {
		stack.getTag().putString("Reforge", reforge.getID());
	}

	@Override
	public FBItemType getFBItemType() {
		return FBItemType.SWORD;
	}
	
}
