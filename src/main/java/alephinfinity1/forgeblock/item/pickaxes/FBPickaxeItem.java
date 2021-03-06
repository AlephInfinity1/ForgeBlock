package alephinfinity1.forgeblock.item.pickaxes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.annotation.Nullable;

import alephinfinity1.forgeblock.item.FBItemTier;
import alephinfinity1.forgeblock.item.IFBTieredItem;
import alephinfinity1.forgeblock.item.IRequirementItem;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import com.google.common.collect.Multimap;

import alephinfinity1.forgeblock.ForgeBlock;
import alephinfinity1.forgeblock.attribute.ModifierHelper;
import alephinfinity1.forgeblock.init.ModRegistries;
import alephinfinity1.forgeblock.misc.FBItemType;
import alephinfinity1.forgeblock.misc.TextFormatHelper;
import alephinfinity1.forgeblock.misc.itemreqs.IRequirementPredicate;
import alephinfinity1.forgeblock.misc.reforge.IReforgeableItem;
import alephinfinity1.forgeblock.misc.reforge.Reforge;
import alephinfinity1.forgeblock.misc.capability.stats_modifier.capability.IItemModifiers;
import alephinfinity1.forgeblock.misc.capability.stats_modifier.capability.ItemModifiersProvider;
import alephinfinity1.forgeblock.misc.tier.FBTier;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.Rarity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class FBPickaxeItem extends PickaxeItem implements IFBTieredItem, IReforgeableItem {
	
	private final FBTier tier;
	private final Multimap<String, AttributeModifier> attributes;
	private final int harvestLevel;
	private final float destroySpeed;
	private final double yield;
	
	protected static final UUID PICKAXE_REFORGE_MODIFIER = UUID.fromString("d443b451-ca3c-413d-a03b-e07088f21ad3");

	@Deprecated
	public FBPickaxeItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties properties) {
		super(tier, attackDamageIn, attackSpeedIn, properties);
		this.tier = FBTier.COMMON;
		Builder<String, AttributeModifier> builder = ImmutableMultimap.builder();
		attributes = builder.build();
		harvestLevel = 0;
		destroySpeed = 1;
		yield = 1;
	}
	
	public FBPickaxeItem(Properties props, FBTier tier, double damage, int harvestLevel, float destroySpeed, double yield) {
		super(new FBItemTier(harvestLevel), (int) damage, (float) Double.MAX_VALUE, props);
		Builder<String, AttributeModifier> builder = ImmutableMultimap.builder();
		builder.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Attack damage modifier", damage, Operation.ADDITION));
		attributes = builder.build();
		
		this.tier = tier;
		this.harvestLevel = harvestLevel;
		this.destroySpeed = destroySpeed;
		this.yield = yield;
	}
	
	@Override
	public boolean canHarvestBlock(BlockState blockIn) {
		int i = this.getTier().getHarvestLevel();
		if (blockIn.getHarvestTool() == net.minecraftforge.common.ToolType.PICKAXE) {
			return i >= blockIn.getHarvestLevel();
		}
		Material material = blockIn.getMaterial();
		return material == Material.ROCK || material == Material.IRON || material == Material.ANVIL;
	}
	
	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) {
		Material material = state.getMaterial();
		return material != Material.ROCK && material != Material.IRON && material != Material.ANVIL ? super.getDestroySpeed(stack, state) : (EnchantmentHelper.getEnchantmentLevel(Enchantments.EFFICIENCY, stack) != 0 ? destroySpeed + EnchantmentHelper.getEnchantmentLevel(Enchantments.EFFICIENCY, stack) * EnchantmentHelper.getEnchantmentLevel(Enchantments.EFFICIENCY, stack) + 1: destroySpeed);
	}
	
	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot, ItemStack stack) {
		if(equipmentSlot != EquipmentSlotType.MAINHAND) return super.getAttributeModifiers(equipmentSlot);
		Builder<String, AttributeModifier> builder = ImmutableMultimap.builder();
		builder.putAll(this.attributes);
		builder.putAll(this.getReforgeModifiers(stack));
		
		IItemModifiers itemMod = stack.getCapability(ItemModifiersProvider.ITEM_MODIFIERS_CAPABILITY).orElse(null);
		if(itemMod != null) {
			builder.putAll(itemMod.getModifiers(stack));
		}
		
		return builder.build();
	}

	@Override
	@Nullable
	public Reforge getReforge(ItemStack stack) {
		if(stack.getTag() == null) return null;
		String reforgeName = stack.getTag().getString("Reforge");
		if(reforgeName.isEmpty()) return null;
		else return ModRegistries.REFORGE.getValue(new ResourceLocation(reforgeName));
	}

	@Override
	public void setReforge(Reforge reforge, ItemStack stack) {
		stack.getTag().putString("Reforge", ModRegistries.REFORGE.getKey(reforge).toString());
	}

	@Override
	public Multimap<String, AttributeModifier> getReforgeModifiers(ItemStack stack) {
		if(getReforge(stack) == null) return ModifierHelper.emptyModifier();
		else {
			Reforge reforge = getReforge(stack);
			return reforge.getModifierMapByTier(getStackTier(stack), PICKAXE_REFORGE_MODIFIER);
		}
	}

	@Override
	public FBTier getFBTier() {
		return tier;
	}

	@Override
	public FBTier getStackTier(ItemStack stack) {
		if(stack.getTag() != null) {
			IItemModifiers itemMod = stack.getCapability(ItemModifiersProvider.ITEM_MODIFIERS_CAPABILITY).orElse(null);
			if(itemMod != null) {
				return FBTier.changeTier(tier, itemMod.getRarity(stack));
			}
			return tier;
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
	
	@OnlyIn(Dist.CLIENT)
	protected List<ITextComponent> requirementsInformation(ItemStack stack) {
		List<ITextComponent> list = new ArrayList<>();
		if (this instanceof IRequirementItem) {
			IRequirementPredicate[] reqs = ((IRequirementItem) this).getRequirements(stack);
			PlayerEntity cplayer = ForgeBlock.MINECRAFT.player;
			if (Objects.isNull(cplayer)) return list; //If null, return right away.
			for (IRequirementPredicate irp : reqs) {
				list.addAll(irp.getDisplay(cplayer));
			}
		}
		
		//Insert a newline if not empty
		if (!list.isEmpty()) {
			list.add(new StringTextComponent(""));
		}
		
		return list;
	}
	
	@SuppressWarnings("unused")
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		FBTier tier = this.getStackTier(stack);
		tooltip.add(new StringTextComponent(tier.color.toString() + tooltip.get(0).getString()));
		tooltip.remove(0);
		
		Multimap<String, AttributeModifier> modifiers = this.getAttributeModifiers(EquipmentSlotType.MAINHAND, stack);
		Multimap<String, AttributeModifier> reforgeModifiers = this.getReforgeModifiers(stack);
		
		float destroySpeed = this.getDestroySpeed(stack, new BlockState(Blocks.STONE, null));
		
		String harvestLevelDescriptor; //Describes the harvestLevel
		
		switch(harvestLevel) {
		case 0:
			harvestLevelDescriptor = TextFormatting.GRAY + new TranslationTextComponent("misc.forgeblock.harvestLevel.0").getString();
			break;
		case 1:
			harvestLevelDescriptor = TextFormatting.GRAY + new TranslationTextComponent("misc.forgeblock.harvestLevel.1").getString();
			break;
		case 2:
			harvestLevelDescriptor = TextFormatting.WHITE + new TranslationTextComponent("misc.forgeblock.harvestLevel.2").getString();
			break;
		case 3:
			harvestLevelDescriptor = TextFormatting.AQUA + new TranslationTextComponent("misc.forgeblock.harvestLevel.3").getString();
			break;
		case 4:
			harvestLevelDescriptor = TextFormatting.DARK_PURPLE + new TranslationTextComponent("misc.forgeblock.harvestLevel.4").getString();
			break;
		case 5:
			harvestLevelDescriptor = TextFormatting.BLUE + new TranslationTextComponent("misc.forgeblock.harvestLevel.5").getString();
			break;
		default:
			harvestLevelDescriptor = TextFormatting.LIGHT_PURPLE + new TranslationTextComponent("misc.forgeblock.harvestLevel.high").getString();
			break;
		}
		
		tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("misc.forgeblock.fakeattribute.destroySpeed").getString() + ": " + TextFormatting.GREEN.toString() + Float.toString(destroySpeed)));
		tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("misc.forgeblock.fakeattribute.harvestLevel").getString() + ": " + TextFormatting.GREEN.toString() + Integer.toString(harvestLevel) + " " + harvestLevelDescriptor));
		tooltip.add(new StringTextComponent(TextFormatting.GRAY.toString() + new TranslationTextComponent("misc.forgeblock.fakeattribute.yield").getString() + ": " + TextFormatting.GREEN.toString() + Double.toString(yield)));
		tooltip.add(new StringTextComponent(""));

		tooltip.addAll(TextFormatHelper.formatModifierMap(modifiers, this.getReforge(stack), tier, stack.getCapability(ItemModifiersProvider.ITEM_MODIFIERS_CAPABILITY).orElse(null), stack));
		
		tooltip.add(new StringTextComponent(""));
		
		//Insert enchantments here
		tooltip.addAll(TextFormatHelper.formatEnchantments(stack));
		
		//Insert item ability description here (unused for some swords)
		tooltip.addAll(this.requirementsInformation(stack));
		
		boolean recombobulated = false;
		if(stack.getTag() != null) recombobulated = this.isRecombobulated(stack);
		String color = tier.color.toString();
		String bold = TextFormatting.BOLD.toString();
		String obfuscated = TextFormatting.OBFUSCATED.toString();
		String reset = TextFormatting.RESET.toString();
		if(!recombobulated) tooltip.add(new StringTextComponent(color + bold + tier.name.getString() + " " + this.getFBItemType().getDisplayName()));
		else tooltip.add(new StringTextComponent(color + bold + obfuscated + "n " + reset + color + bold + tier.name.getString() + " " + this.getFBItemType().getDisplayName() + obfuscated + " n"));
	}

	@Override
	public FBItemType getFBItemType() {
		return FBItemType.PICKAXE;
	}
	
	@Override
	public Rarity getRarity(ItemStack stack) {
		return getStackTier(stack).getVanillaRarity();
	}

}
