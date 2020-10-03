package alephinfinity1.forgeblock.item;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import com.google.common.collect.Multimap;

import alephinfinity1.forgeblock.attribute.FBAttributes;
import alephinfinity1.forgeblock.attribute.ModifierHelper;
import alephinfinity1.forgeblock.init.ModEnchantments;
import alephinfinity1.forgeblock.misc.FBItemType;
import alephinfinity1.forgeblock.misc.TextFormatHelper;
import alephinfinity1.forgeblock.misc.reforge.IReforgeableItem;
import alephinfinity1.forgeblock.misc.reforge.Reforge;
import alephinfinity1.forgeblock.misc.tier.FBTier;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class FBArmorItem extends ArmorItem implements IFBTieredItem, IReforgeableItem {

	private final FBTier tier;
	private final Multimap<String, AttributeModifier> attributes;
	
	protected static final UUID HELMET_GROWTH_HEALTH_MODIFIER = UUID.fromString("1422aa8d-d317-4f6d-8152-56a14eb61f69");
	protected static final UUID HELMET_PROTECTION_DEFENSE_MODIFIER = UUID.fromString("bc8a6c13-c59c-4e25-b2b5-cf1c5b7e96d4");
	
	protected static final UUID CHESTPLATE_GROWTH_HEALTH_MODIFIER = UUID.fromString("659c565a-51e5-4456-b516-922722564f33");
	protected static final UUID CHESTPLATE_PROTECTION_DEFENSE_MODIFIER = UUID.fromString("983b7709-bb61-4065-9891-efcd26d27caa");
	
	protected static final UUID LEGGINGS_GROWTH_HEALTH_MODIFIER = UUID.fromString("8d7e0b86-f0fe-4c59-84eb-d19bdc7517e8");
	protected static final UUID LEGGINGS_PROTECTION_DEFENSE_MODIFIER = UUID.fromString("1390f2e6-ad10-4124-9fd8-d5959f5369af");
	
	protected static final UUID BOOTS_GROWTH_HEALTH_MODIFIER = UUID.fromString("089ccfb9-e549-4285-8ddc-95351c80bef5");
	protected static final UUID BOOTS_PROTECTION_DEFENSE_MODIFIER = UUID.fromString("03d6bd2f-641f-46e3-adfa-53f0ec21f16a");
	
	protected static final UUID HELMET_DEFENSE_MODIFIER = UUID.fromString("bf36f795-91a0-4020-97bd-d03ddfc8bed2");
	protected static final UUID HELMET_HEALTH_MODIFIER = UUID.fromString("00feeb8f-5673-4416-9307-3490644cd458");
	
	protected static final UUID CHESTPLATE_DEFENSE_MODIFIER = UUID.fromString("3a4787d7-9225-4315-bc66-4c87019a4d30");
	protected static final UUID CHESTPLATE_HEALTH_MODIFIER = UUID.fromString("705d3e0d-a019-45e6-9fe0-b3a8e5fce2c2");
	
	protected static final UUID LEGGINGS_DEFENSE_MODIFIER = UUID.fromString("e240962e-4802-44f3-a8fc-f7f613476e8f");
	protected static final UUID LEGGINGS_HEALTH_MODIFIER = UUID.fromString("191d4d61-eb5b-4036-94fa-f779d65496e3");
	
	protected static final UUID BOOTS_DEFENSE_MODIFIER = UUID.fromString("c2ab9230-c894-4d7e-b65e-4ca25be42bfc");
	protected static final UUID BOOTS_HEALTH_MODIFIER = UUID.fromString("93a736f1-2668-417f-8062-b72b43c08427");
	
	protected static final UUID HELMET_REFORGE_MODIFIER = UUID.fromString("93603319-6583-4b10-8f5c-93206b84f91c");
	protected static final UUID CHESTPLATE_REFORGE_MODIFIER = UUID.fromString("87cc5544-7e0c-489b-9150-afb9e862ae59");
	protected static final UUID LEGGINGS_REFORGE_MODIFIER = UUID.fromString("c2d21770-30c0-4995-9353-13ea119d2208");
	protected static final UUID BOOTS_REFORGE_MODIFIER = UUID.fromString("ee72082d-cdc5-4b51-9178-a049ad7b954e");

	
	//Super constructor, don't use
	@Deprecated
	public FBArmorItem(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
		super(materialIn, slot, builder);
		tier = FBTier.COMMON;
		attributes = Reforge.emptyModifier();
		// TODO Auto-generated constructor stub
	}
	
	public FBArmorItem(EquipmentSlotType slot, String name, Properties props, FBTier tier, double defenseIn, double healthIn) {
		super(new FBArmorMaterial(name), slot, props);
		this.tier = tier;
		Builder<String, AttributeModifier> builder = ImmutableMultimap.builder();
		switch(slot) {
		case HEAD:
			builder.put(FBAttributes.DEFENSE.getName(), new AttributeModifier(HELMET_DEFENSE_MODIFIER, "Defense modifier", defenseIn, Operation.ADDITION));
			builder.put(SharedMonsterAttributes.MAX_HEALTH.getName(), new AttributeModifier(HELMET_HEALTH_MODIFIER, "Health modifier", healthIn, Operation.ADDITION));
			break;
		case CHEST:
			builder.put(FBAttributes.DEFENSE.getName(), new AttributeModifier(CHESTPLATE_DEFENSE_MODIFIER, "Defense modifier", defenseIn, Operation.ADDITION));
			builder.put(SharedMonsterAttributes.MAX_HEALTH.getName(), new AttributeModifier(CHESTPLATE_HEALTH_MODIFIER, "Health modifier", healthIn, Operation.ADDITION));
			break;
		case LEGS:
			builder.put(FBAttributes.DEFENSE.getName(), new AttributeModifier(LEGGINGS_DEFENSE_MODIFIER, "Defense modifier", defenseIn, Operation.ADDITION));
			builder.put(SharedMonsterAttributes.MAX_HEALTH.getName(), new AttributeModifier(LEGGINGS_HEALTH_MODIFIER, "Health modifier", healthIn, Operation.ADDITION));
			break;
		case FEET:
			builder.put(FBAttributes.DEFENSE.getName(), new AttributeModifier(BOOTS_DEFENSE_MODIFIER, "Defense modifier", defenseIn, Operation.ADDITION));
			builder.put(SharedMonsterAttributes.MAX_HEALTH.getName(), new AttributeModifier(BOOTS_HEALTH_MODIFIER, "Health modifier", healthIn, Operation.ADDITION));
			break;
		default:
			throw new IllegalArgumentException("Armor type cannot be " + slot.getName() + ", it can only be: HEAD, CHEST, LEGS, FEET.");
		}
		attributes = builder.build();
	}
	
	public FBArmorItem(EquipmentSlotType slot, String name, Properties props, FBTier tier, Multimap<String, AttributeModifier> modifiers) {
		super(new FBArmorMaterial(name), slot, props);
		this.tier = tier;
		attributes = modifiers;
	}
	
	public FBArmorItem(EquipmentSlotType slot, String name, Properties props, FBTier tier, double damage, double strength, double critChance, double critDamage, double bonusAttackSpeed, double seaCreatureChance, double health, double defense, double speed, double intelligence, double trueDefense, double magicFind, double petLuck) {
		super(new FBArmorMaterial(name), slot, props);
		this.tier = tier;
		switch(slot) {
		case HEAD:
			attributes = ModifierHelper.modifierMapFromDoubles(damage, strength, critChance, critDamage, bonusAttackSpeed, seaCreatureChance, health, defense, speed, intelligence, trueDefense, magicFind, petLuck, "Helmet");
			break;
		case CHEST:
			attributes = ModifierHelper.modifierMapFromDoubles(damage, strength, critChance, critDamage, bonusAttackSpeed, seaCreatureChance, health, defense, speed, intelligence, trueDefense, magicFind, petLuck, "Chestplate");
			break;
		case LEGS:
			attributes = ModifierHelper.modifierMapFromDoubles(damage, strength, critChance, critDamage, bonusAttackSpeed, seaCreatureChance, health, defense, speed, intelligence, trueDefense, magicFind, petLuck, "Leggings");
			break;
		case FEET:
			attributes = ModifierHelper.modifierMapFromDoubles(damage, strength, critChance, critDamage, bonusAttackSpeed, seaCreatureChance, health, defense, speed, intelligence, trueDefense, magicFind, petLuck, "Boots");
			break;
		default:
			throw new IllegalArgumentException("Armor type cannot be " + slot.getName() + ", it can only be: HEAD, CHEST, LEGS, FEET.");
		}
	}
	
	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot) {
		return equipmentSlot == this.slot ? this.attributes : super.getAttributeModifiers(equipmentSlot);
	}
	
	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot, ItemStack stack) {
		if(equipmentSlot != this.slot) return super.getAttributeModifiers(equipmentSlot);
		Builder<String, AttributeModifier> builder = ImmutableMultimap.builder();
		builder.putAll(this.attributes);
		builder.putAll(this.getReforgeModifiers(stack));
		
		int protectionLevel = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.PROTECTION.get(), stack);
		int growthLevel = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.GROWTH.get(), stack);
		switch(this.slot) {
		case HEAD:
			builder.put(FBAttributes.DEFENSE.getName(), new AttributeModifier(HELMET_PROTECTION_DEFENSE_MODIFIER, "Enchantment modifier", 3 * protectionLevel, Operation.ADDITION));
			builder.put(SharedMonsterAttributes.MAX_HEALTH.getName(), new AttributeModifier(HELMET_GROWTH_HEALTH_MODIFIER, "Enchantment modifier", 15 * growthLevel, Operation.ADDITION));
			break;
		case CHEST:
			builder.put(FBAttributes.DEFENSE.getName(), new AttributeModifier(CHESTPLATE_PROTECTION_DEFENSE_MODIFIER, "Enchantment modifier", 3 * protectionLevel, Operation.ADDITION));
			builder.put(SharedMonsterAttributes.MAX_HEALTH.getName(), new AttributeModifier(CHESTPLATE_GROWTH_HEALTH_MODIFIER, "Enchantment modifier", 15 * growthLevel, Operation.ADDITION));
			break;
		case LEGS:
			builder.put(FBAttributes.DEFENSE.getName(), new AttributeModifier(LEGGINGS_PROTECTION_DEFENSE_MODIFIER, "Enchantment modifier", 3 * protectionLevel, Operation.ADDITION));
			builder.put(SharedMonsterAttributes.MAX_HEALTH.getName(), new AttributeModifier(LEGGINGS_GROWTH_HEALTH_MODIFIER, "Enchantment modifier", 15 * growthLevel, Operation.ADDITION));
			break;
		case FEET:
			builder.put(FBAttributes.DEFENSE.getName(), new AttributeModifier(BOOTS_PROTECTION_DEFENSE_MODIFIER, "Enchantment modifier", 3 * protectionLevel, Operation.ADDITION));
			builder.put(SharedMonsterAttributes.MAX_HEALTH.getName(), new AttributeModifier(BOOTS_GROWTH_HEALTH_MODIFIER, "Enchantment modifier", 15 * growthLevel, Operation.ADDITION));
			break;
		default:
			break;
		}
		
		return builder.build();
	}

	@Override
	public FBItemType getFBItemType() {
		switch(this.slot) {
		case HEAD:
			return FBItemType.HELMET;
		case CHEST:
			return FBItemType.CHESTPLATE;
		case LEGS:
			return FBItemType.LEGGINGS;
		case FEET:
			return FBItemType.BOOTS;
		default:
			return FBItemType.GENERAL;
		}
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
	public void setReforge(Reforge reforge, ItemStack stack) {
		stack.getTag().putString("Reforge", reforge.getID());
	}

	@Override
	public Multimap<String, AttributeModifier> getReforgeModifiers(ItemStack stack) {
		if(getReforge(stack) == null) return Reforge.emptyModifier();
		else {
			Reforge reforge = getReforge(stack);
			switch(this.slot) {
			case HEAD:
				return reforge.getModifierMapByTier(getStackTier(stack), HELMET_REFORGE_MODIFIER);
			case CHEST:
				return reforge.getModifierMapByTier(getStackTier(stack), CHESTPLATE_REFORGE_MODIFIER);
			case LEGS:
				return reforge.getModifierMapByTier(getStackTier(stack), LEGGINGS_REFORGE_MODIFIER);
			case FEET:
				return reforge.getModifierMapByTier(getStackTier(stack), BOOTS_REFORGE_MODIFIER);
			default:
				break;
			}
			
			return Reforge.emptyModifier();
		}
	}

	@Override
	public FBTier getFBTier() {
		return tier;
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
	public ITextComponent getDisplayName(ItemStack stack) {
		String reforgeName = "";
		if(this.getReforge(stack) != null) {
			reforgeName = this.getReforge(stack).getDisplayName();
		}
		FBTier tier = getStackTier(stack);
		String color = tier.color.toString();
		String recombobulateBold = stack.getOrCreateTag().getByte("Recombobulated") == 1 ? TextFormatting.BOLD.toString() : "";
		if(this.getReforge(stack) != null)
			return new StringTextComponent(color + recombobulateBold + reforgeName + " " + new TranslationTextComponent(this.getTranslationKey(stack)).getString());
		else
			return new StringTextComponent(color + recombobulateBold + new TranslationTextComponent(this.getTranslationKey(stack)).getString());
	}
	
	/*
	 * Any additional information to appear on the tooltip, to be overridden.
	 */
	public List<ITextComponent> additionalInformation() {
		return new ArrayList<ITextComponent>();
	}
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		FBTier tier = this.getStackTier(stack);
		tooltip.add(new StringTextComponent(tier.color.toString() + tooltip.get(0).getString()));
		tooltip.remove(0);
		
		Multimap<String, AttributeModifier> modifiers = this.getAttributeModifiers(this.slot, stack);
		List<ITextComponent> additional = this.additionalInformation();
		
		tooltip.addAll(TextFormatHelper.formatModifierMap(modifiers, this.getReforge(stack), tier));
		
		tooltip.add(new StringTextComponent(""));
		
		//Insert enchantments here
		tooltip.addAll(TextFormatHelper.formatEnchantments(stack));
		
		//Insert item ability description here (unused for some items)
		tooltip.addAll(additional);
				
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
	public Rarity getRarity(ItemStack stack) {
		return getStackTier(stack).getVanillaRarity();
	}

}
