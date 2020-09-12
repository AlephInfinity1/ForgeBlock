package alephinfinity1.forgeblock.item;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import com.google.common.collect.Multimap;

import alephinfinity1.forgeblock.attribute.ModifierHelper;
import alephinfinity1.forgeblock.init.ModItemGroups;
import alephinfinity1.forgeblock.misc.tier.FBTier;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class CustomSwordItem extends FBSwordItem {
	
	protected static final UUID CUSTOM_MODIFIER = UUID.fromString("9fd33888-7c8b-40e4-94d0-f6728bbd5d08");

	public CustomSwordItem(Properties props, FBTier tier, double attackDamageIn, double strengthIn, double critChanceIn,
			double critDamageIn) {
		super(props, tier, attackDamageIn, strengthIn, critChanceIn, critDamageIn);
	}
	
	public CustomSwordItem() {
		super(new Item.Properties().group(ModItemGroups.FB_SWORDS), FBTier.SPECIAL, 0, 0, 0, 0);
	}
	
	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot, ItemStack stack) {
		Multimap<String, AttributeModifier> map = super.getAttributeModifiers(equipmentSlot, stack);
		Builder<String, AttributeModifier> builder = ImmutableMultimap.builder();
		builder.putAll(map);
		if(stack.getTag() != null) {
			CompoundNBT nbt = stack.getTag();
			double damage = nbt.getDouble("Damage");
			double strength = nbt.getDouble("Strength");
			double critChance = nbt.getDouble("CritChance");
			double critDamage = nbt.getDouble("CritDamage");
			double bonusAttackSpeed = nbt.getDouble("BonusAttackSpeed");
			double seaCreatureChance = nbt.getDouble("SeaCreatureChance");
			double health = nbt.getDouble("Health");
			double defense = nbt.getDouble("Defense");
			double speed = nbt.getDouble("Speed");
			double intelligence = nbt.getDouble("Intelligence");
			double trueDefense = nbt.getDouble("TrueDefense");
			double magicFind = nbt.getDouble("MagicFind");
			double petLuck = nbt.getDouble("PetLuck");
			builder.putAll(ModifierHelper.modifierMapFromDoubles(damage, strength, critChance, critDamage, bonusAttackSpeed, seaCreatureChance, health, defense, speed, intelligence, trueDefense, magicFind, petLuck, "Custom modifier", CUSTOM_MODIFIER));
		}
		
		return builder.build();
	}
	
	@Override
	public FBTier getStackTier(ItemStack stack) {
		if(stack.getTag() != null) {
			switch(stack.getTag().getString("Rarity").toLowerCase()) {
			case "common":
				return FBTier.COMMON;
			case "uncommon":
				return FBTier.UNCOMMON;
			case "rare":
				return FBTier.RARE;
			case "epic":
				return FBTier.EPIC;
			case "legendary":
				return FBTier.LEGENDARY;
			case "mythic":
				return FBTier.MYTHIC;
			case "special":
				return FBTier.SPECIAL;
			case "very_special":
			case "very special":
				return FBTier.VERY_SPECIAL;
			}
			
			switch(stack.getTag().getByte("Rarity")) {
			case 0:
				return FBTier.COMMON;
			case 1:
				return FBTier.UNCOMMON;
			case 2:
				return FBTier.RARE;
			case 3:
				return FBTier.EPIC;
			case 4:
				return FBTier.LEGENDARY;
			case 5:
				return FBTier.MYTHIC;
			case 6:
				return FBTier.SPECIAL;
			case 7:
				return FBTier.VERY_SPECIAL;
			}
		}
		
		return FBTier.COMMON;
		
	}
	
	@Override
	public ITextComponent getDisplayName(ItemStack stack) {
		if(stack.getTag() != null) {
			String name = stack.getTag().getString("Name");
			if(!name.equals("")) {
				if(this.getReforge(stack) != null) {
					return new StringTextComponent(this.getStackTier(stack).color.toString() + this.getReforge(stack).getDisplayName() + " " + name);
				}
				return new StringTextComponent(this.getStackTier(stack).color.toString() + name);
			}
		}
		if(this.getReforge(stack) != null) {
			return new StringTextComponent(this.getStackTier(stack).color.toString() + this.getReforge(stack).getDisplayName() + " " + "Custom Sword");
		}
		return new StringTextComponent(this.getStackTier(stack).color.toString() + "Custom Sword");
	}
	
	@Override
	public List<ITextComponent> additionalInformation(ItemStack stack) {
		List<ITextComponent> list = new ArrayList<>();
		if(stack.getTag() != null) {
			ListNBT lore = stack.getTag().getList("Lore", 9);
			lore.forEach((line) -> {list.add(new StringTextComponent(TextFormatting.GRAY.toString() + line.getString()));});
			return list;
		}
		return list;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack stack = playerIn.getHeldItemMainhand();
		if(stack.getTag() != null) {
			String command = stack.getTag().getString("RightClickAction");
			if(!worldIn.isRemote())
				worldIn.getServer().getCommandManager().handleCommand(playerIn.getCommandSource(), command);
			return ActionResult.resultSuccess(stack);
		}
		return ActionResult.resultPass(stack);
	}

}
