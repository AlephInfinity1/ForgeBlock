package alephinfinity1.forgeblock.item;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import com.google.common.collect.Multimap;

import alephinfinity1.forgeblock.attribute.FBAttributes;
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
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
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
	
	protected static final UUID SWORD_REFORGE_MODIFIER = UUID.fromString("ff67deae-89a0-4ec4-95ad-a50795ff6ad2");
	
	protected static final UUID WOOD_SINGULARITY_MODIFIER = UUID.fromString("fae06830-e6e7-4df2-927b-2beaa3affb27");
	
	protected static final UUID CRITICAL_ENCHANTMENT_MODIFIER = UUID.fromString("ab2c9437-5c8c-4986-8034-4a85dd261c54");
	
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
	
	public FBSwordItem(Properties props, FBTier tier, Multimap<String, AttributeModifier> attributes) {
		super(new FBItemTier(), 0, (float) Double.MAX_VALUE, props);
		this.tier = tier;
		this.attributes = attributes;
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
		
		int criticalLevel = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.CRITICAL.get(), stack);
		if(criticalLevel != 0) builder.put(FBAttributes.CRIT_DAMAGE.getName(), new AttributeModifier(CRITICAL_ENCHANTMENT_MODIFIER, "Crit enchant modifier", 10.0D * criticalLevel, Operation.ADDITION));
		
		boolean woodSingularity = false;
		if(stack.getTag() != null) {
			woodSingularity = (stack.getTag().getByte("WoodSingularity") == 1);
		}
		if(woodSingularity) builder.put(FBAttributes.STRENGTH.getName(), new AttributeModifier(WOOD_SINGULARITY_MODIFIER, "Wood singularity strength modifier", 100.0D, Operation.ADDITION));
		
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
	
	public List<ITextComponent> additionalInformation() {
		return new ArrayList<ITextComponent>();
	}
	
	public List<ITextComponent> additionalInformation(ItemStack stack) {
		return new ArrayList<ITextComponent>();
	}
	
	public List<ITextComponent> additionalInformation(ItemStack stack, PlayerEntity player) {
		return new ArrayList<ITextComponent>();
	}
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		FBTier tier = this.getStackTier(stack);
		tooltip.add(new StringTextComponent(tier.color.toString() + tooltip.get(0).getString()));
		tooltip.remove(0);
		
		Multimap<String, AttributeModifier> modifiers = this.getAttributeModifiers(EquipmentSlotType.MAINHAND, stack);
		List<ITextComponent> additional = this.additionalInformation();
		List<ITextComponent> additional1 = this.additionalInformation(stack);
		
		tooltip.addAll(TextFormatHelper.formatModifierMap(modifiers, this.getReforge(stack), tier));
		
		tooltip.add(new StringTextComponent(""));
		
		//Insert enchantments here
		tooltip.addAll(TextFormatHelper.formatEnchantments(stack));
		
		//Insert item ability description here (unused for some swords)
		tooltip.addAll(additional);
		tooltip.addAll(additional1);
		
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
			return reforge.getModifierMapByTier(getStackTier(stack), SWORD_REFORGE_MODIFIER);
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
		boolean woodSingularity = false;
		if(stack.getTag() != null) {
			woodSingularity = (stack.getTag().getByte("WoodSingularity") == 1);
		}
		if(woodSingularity) {
			if(this.getReforge(stack) != null)
				return new StringTextComponent(color + recombobulateBold + new TranslationTextComponent("misc.forgeblock.woodSingularity").getString() + " " + reforgeName + " " + new TranslationTextComponent(this.getTranslationKey(stack)).getString());
			else
				return new StringTextComponent(color + recombobulateBold + new TranslationTextComponent("misc.forgeblock.woodSingularity").getString() + " " + new TranslationTextComponent(this.getTranslationKey(stack)).getString());
		} else {
			if(this.getReforge(stack) != null)
				return new StringTextComponent(color + recombobulateBold + reforgeName + " " + new TranslationTextComponent(this.getTranslationKey(stack)).getString());
			else
				return new StringTextComponent(color + recombobulateBold + new TranslationTextComponent(this.getTranslationKey(stack)).getString());
		}
	}

	@Override
	public void setReforge(Reforge reforge, ItemStack stack) {
		stack.getTag().putString("Reforge", reforge.getID());
	}

	@Override
	public FBItemType getFBItemType() {
		return FBItemType.SWORD;
	}
	
	@Override
	public Rarity getRarity(ItemStack stack) {
		return getStackTier(stack).getVanillaRarity();
	}
}
