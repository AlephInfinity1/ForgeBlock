package alephinfinity1.forgeblock.item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import com.google.common.collect.Multimap;

import alephinfinity1.forgeblock.attribute.ModifierHelper;
import alephinfinity1.forgeblock.init.ModEnchantments;
import alephinfinity1.forgeblock.init.ModItemGroups;
import alephinfinity1.forgeblock.init.ModItems;
import alephinfinity1.forgeblock.misc.reforge.Reforge;
import alephinfinity1.forgeblock.misc.tier.FBTier;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.ByteNBT;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
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
			ListNBT lore = stack.getTag().getList("Lore", 8);
			lore.forEach((line) -> {
				String str = line.getString();
				String newStr = str.replace('&', '\u00A7');
				list.add(new StringTextComponent(newStr));
			});
			return list;
		}
		return list;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack stack = playerIn.getHeldItemMainhand();
		if(stack.getTag() != null) {
			String command = stack.getTag().getString("RightClickAction");
			if(command.equals("")) return ActionResult.resultPass(stack);
			if(!worldIn.isRemote())
				worldIn.getServer().getCommandManager().handleCommand(playerIn.getCommandSource(), command);
			return ActionResult.resultSuccess(stack);
		}
		return ActionResult.resultPass(stack);
	}
	
	@Override
	public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
		if(this.isInGroup(group)) {
			ItemStack item = new ItemStack(this);
			ListNBT list = new ListNBT();
			list.add(StringNBT.valueOf("&7This is a customizable sword item"));
			list.add(StringNBT.valueOf("&aCustomize it using commands!"));
			list.add(StringNBT.valueOf("&eMore info on the wiki (coming soon TM)"));
			list.add(StringNBT.valueOf("&bFor now, have some AotE stats :p"));
			list.add(StringNBT.valueOf(""));
			item.getOrCreateTag().put("Lore", list);
			item.getOrCreateTag().put("Rarity", ByteNBT.valueOf((byte) 6));
			item.getOrCreateTag().putInt("Damage", 100);
			item.getOrCreateTag().putInt("Strength", 100);
			items.add(item);
		}
	}
	
	/*
	 * Creates a completely random sword cuz RNG is fun
	 */
	public static ItemStack randomSword() {
		ItemStack sword = new ItemStack(ModItems.CUSTOM_SWORD.get());
		CompoundNBT nbt = sword.getOrCreateTag();
		Random rng = new Random();
		
		/*
		 * Stats randomization
		 */
		nbt.putInt("Damage", rng.nextInt(101) + rng.nextInt(101));
		nbt.putInt("Strength", rng.nextInt(101) + rng.nextInt(101));
		if(rng.nextDouble() < 0.35) {
			nbt.putInt("CritChance", rng.nextInt(10) + rng.nextInt(15));
		}
		if(rng.nextDouble() < 0.35) {
			nbt.putInt("CritDamage", rng.nextInt(25) + rng.nextInt(45));
		}
		if(rng.nextDouble() < 0.27) {
			nbt.putInt("Intelligence", rng.nextInt(125) + rng.nextInt(175));
		}
		
		/*
		 * Enchantments randomization
		 */
		int sharpnessLevel = rng.nextInt(6);
		int lootingLevel = rng.nextInt(4);
		int sweepingLevel = rng.nextInt(4);
		int lifeStealLevel = rng.nextInt(4);
		int ultimateWiseLevel = 0;
		if(rng.nextDouble() < 0.2) ultimateWiseLevel = rng.nextInt(6);
		int telekinesisLevel = rng.nextInt(2);
		
		//1% chance for enchantment levels to be increased by 1
		if(rng.nextDouble() < 0.01) ++sharpnessLevel;
		if(rng.nextDouble() < 0.01) ++lootingLevel;
		if(rng.nextDouble() < 0.01) ++sweepingLevel;
		if(rng.nextDouble() < 0.01) ++lifeStealLevel;
		if(rng.nextDouble() < 0.01) ++ultimateWiseLevel;
		
		if(sharpnessLevel != 0) sword.addEnchantment(Enchantments.SHARPNESS, sharpnessLevel);
		if(lootingLevel != 0) sword.addEnchantment(Enchantments.LOOTING, lootingLevel);
		if(sweepingLevel != 0) sword.addEnchantment(Enchantments.SWEEPING, sweepingLevel);
		if(lifeStealLevel != 0) sword.addEnchantment(ModEnchantments.LIFE_STEAL.get(), lifeStealLevel);
		if(ultimateWiseLevel != 0) sword.addEnchantment(ModEnchantments.ULTIMATE_WISE.get(), ultimateWiseLevel);
		if(telekinesisLevel != 0) sword.addEnchantment(ModEnchantments.TELEKINESIS.get(), telekinesisLevel);
		
		/*
		 * Rarity randomization
		 */
		int rarity = rng.nextInt(20);
		switch(rarity) {
		case 0:
			nbt.putInt("Rarity", 0);
			break;
		case 1:
		case 2:
		case 3:
			nbt.putInt("Rarity", 1);
			break;
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
			nbt.putInt("Rarity", 2);
			break;
		case 10:
		case 11:
		case 12:
		case 13:
		case 14:
		case 15:
			nbt.putInt("Rarity", 3);
			break;
		default:
			nbt.putInt("Rarity", 4);
		}
		
		//1% chance to increase rarity by 1. This allows mythic rarity (albeit very rarely, 0.2% chance).
		if(rng.nextDouble() < 0.01) nbt.putByte("Recombobulated", (byte) 1);
		
		/*
		 * Name randomization
		 */
		List<String> name1 = Arrays.asList("Classy", "Dreamy", "Fancy", "Xyzzy", "Cool", "Stupid", "Simple", "Complex", "Bouncy", "Durable", "Superb", "Aristocratic", "Sharpened", "Dull", "Uninteresting", "Average", "Unusual", "Unique", "Fickle", "Crystalline", "Aspect of the");
		List<String> name2 = Arrays.asList("Dagger", "Sword", "Broadsword", "Cleaver", "Longsword", "Dirk", "Spear", "Trident", "Cutlass", "Foil", "Blade", "Frypan");
		
		String name = name1.get(rng.nextInt(name1.size())) + " " + name2.get(rng.nextInt(name2.size()));
		nbt.putString("Name", name);
		
		/*
		 * Reforge randomization
		 */
		if(rng.nextDouble() < 0.75) {
			Reforge reforge = Reforge.getRandomReforge(sword);
			nbt.putString("Reforge", reforge.getID());
		}
		
		return sword;
		
	}
	
	public static ItemStack randomSword(int quality) {
		ItemStack sword = new ItemStack(ModItems.CUSTOM_SWORD.get());
		CompoundNBT nbt = sword.getOrCreateTag();
		Random rng = new Random();
		
		/*
		 * Stats randomization
		 */
		nbt.putInt("Damage", rng.nextInt(101) + rng.nextInt(101) + quality);
		nbt.putInt("Strength", rng.nextInt(101) + rng.nextInt(101) + quality);
		if(rng.nextDouble() < 0.35 + quality * 0.01) {
			nbt.putInt("CritChance", rng.nextInt(10) + rng.nextInt(15));
		}
		if(rng.nextDouble() < 0.35 + quality * 0.01) {
			nbt.putInt("CritDamage", rng.nextInt(25) + rng.nextInt(45));
		}
		if(rng.nextDouble() < 0.27 + quality * 0.0085) {
			nbt.putInt("Intelligence", rng.nextInt(125) + rng.nextInt(175));
		}
		
		/*
		 * Enchantments randomization
		 */
		int sharpnessLevel = rng.nextInt(6);
		int lootingLevel = rng.nextInt(4);
		int sweepingLevel = rng.nextInt(4);
		int lifeStealLevel = rng.nextInt(4);
		int ultimateWiseLevel = 0;
		if(rng.nextDouble() < 0.2 + quality * 0.002) ultimateWiseLevel = rng.nextInt(6);
		int telekinesisLevel = rng.nextInt(2);
		
		//1% chance for enchantment levels to be increased by 1
		if(rng.nextDouble() < 0.01 + quality * 0.0001) ++sharpnessLevel;
		if(rng.nextDouble() < 0.01 + quality * 0.0001) ++lootingLevel;
		if(rng.nextDouble() < 0.01 + quality * 0.0001) ++sweepingLevel;
		if(rng.nextDouble() < 0.01 + quality * 0.0001) ++lifeStealLevel;
		if(rng.nextDouble() < 0.01 + quality * 0.0001) ++ultimateWiseLevel;
		
		if(sharpnessLevel != 0) sword.addEnchantment(Enchantments.SHARPNESS, sharpnessLevel);
		if(lootingLevel != 0) sword.addEnchantment(Enchantments.LOOTING, lootingLevel);
		if(sweepingLevel != 0) sword.addEnchantment(Enchantments.SWEEPING, sweepingLevel);
		if(lifeStealLevel != 0) sword.addEnchantment(ModEnchantments.LIFE_STEAL.get(), lifeStealLevel);
		if(ultimateWiseLevel != 0) sword.addEnchantment(ModEnchantments.ULTIMATE_WISE.get(), ultimateWiseLevel);
		if(telekinesisLevel != 0) sword.addEnchantment(ModEnchantments.TELEKINESIS.get(), telekinesisLevel);
		
		/*
		 * Rarity randomization
		 */
		int rarity = rng.nextInt(20);
		if(quality > rng.nextInt(20)) ++rarity;
		if(quality > rng.nextInt(50)) ++rarity;
		if(quality > rng.nextInt(100)) ++rarity;
		switch(rarity) {
		case 0:
			nbt.putInt("Rarity", 0);
			break;
		case 1:
		case 2:
		case 3:
			nbt.putInt("Rarity", 1);
			break;
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
			nbt.putInt("Rarity", 2);
			break;
		case 10:
		case 11:
		case 12:
		case 13:
		case 14:
		case 15:
			nbt.putInt("Rarity", 3);
			break;
		default:
			nbt.putInt("Rarity", 4);
		}
		
		//1% chance to increase rarity by 1. This allows mythic rarity (albeit very rarely, 0.2% chance).
		if(rng.nextDouble() < 0.01 + quality * 0.0001) nbt.putByte("Recombobulated", (byte) 1);
		
		/*
		 * Name randomization
		 */
		List<String> name1 = Arrays.asList("Classy", "Dreamy", "Fancy", "Xyzzy", "Cool", "Stupid", "Simple", "Complex", "Bouncy", "Durable", "Superb", "Aristocratic", "Sharpened", "Dull", "Uninteresting", "Average", "Unusual", "Unique", "Fickle", "Crystalline", "Aspect of the");
		List<String> name2 = Arrays.asList("Dagger", "Sword", "Broadsword", "Cleaver", "Longsword", "Dirk", "Spear", "Trident", "Cutlass", "Foil", "Blade", "Frypan");
		
		String name = name1.get(rng.nextInt(name1.size())) + " " + name2.get(rng.nextInt(name2.size()));
		nbt.putString("Name", name);
		
		/*
		 * Reforge randomization
		 */
		if(rng.nextDouble() < 0.75 + quality * 0.01) {
			Reforge reforge = Reforge.getRandomReforge(sword);
			nbt.putString("Reforge", reforge.getID());
		}
		
		return sword;
		
	}

}
