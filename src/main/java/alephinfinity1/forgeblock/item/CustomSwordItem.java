package alephinfinity1.forgeblock.item;

import java.util.UUID;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import com.google.common.collect.Multimap;

import alephinfinity1.forgeblock.attribute.ModifierHelper;
import alephinfinity1.forgeblock.init.ModItemGroups;
import alephinfinity1.forgeblock.misc.tier.FBTier;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

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

}
