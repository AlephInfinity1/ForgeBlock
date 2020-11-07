package alephinfinity1.forgeblock.misc.stats_modifier;

import java.util.function.Function;
import java.util.function.Predicate;

import com.google.common.collect.Multimap;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.registries.ForgeRegistryEntry;

/**
 * A class that stores a modifier to an item (e.g. Hot Potato Book, Recombobulator 3000, Wood Singularity etc.)
 */
public abstract class StatsModifier extends ForgeRegistryEntry<StatsModifier> {
	
	/*
	 * A list of attribute modifiers.
	 * This is a function rather than a multimap because modifiers may be different depending on item.
	 * (E.g. HPB gives +2/+2 Damage/Strength on weapons, but +4/+2 Health/Defense on armour.)
	 */
	private Function<ItemStack, Multimap<String, AttributeModifier>> attributeModifiers;
	
	/*
	 * The number of rarity changes. 1 for Wood Singularity and Recombobulator 3000, 0 for others.
	 */
	private int rarityChange;
	
	/*
	 * The colour of the modifier when applied to an item. E.g. TextFormatting#YELLOW for HPB, GOLD for Wood Singularity.	
	 */
	private TextFormatting color;
	
	/*
	 * A predicate of ItemStack this is applicable on.
	 */
	private Predicate<ItemStack> applicableOn;
	
	/*
	 * A CompoundNBT for storing additional data.
	 */
	private CompoundNBT data;
	
	public StatsModifier(Function<ItemStack, Multimap<String, AttributeModifier>> attributeModifiers, int rarityChange, TextFormatting color, Predicate<ItemStack> applicableOn) {
		this.attributeModifiers = attributeModifiers;
		this.rarityChange = rarityChange;
		this.color = color;
		this.applicableOn = applicableOn;
	}
	
	public Multimap<String, AttributeModifier> getModifiers(ItemStack stack) {
		return this.attributeModifiers.apply(stack);
	}
		
	public int getRarityChange() {
		return this.rarityChange;
	}
	
	public TextFormatting getColor() {
		return this.color;
	}
	
	public boolean isApplicable(ItemStack stack) {
		return this.applicableOn.test(stack);
	}
	
	public CompoundNBT getData() {
		return data;
	}
}
