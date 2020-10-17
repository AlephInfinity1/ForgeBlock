package alephinfinity1.forgeblock.recipe.brewing;

import alephinfinity1.forgeblock.init.ModItems;
import alephinfinity1.forgeblock.item.FBPotionItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.common.brewing.IBrewingRecipe;

public class DurationUpgradeRecipe implements IBrewingRecipe {

	@Override
	public boolean isInput(ItemStack input) {
		boolean flag = true;
		if(input.getTag() != null) {
			if(input.getTag().getByte("DurationModifier") != 0) {
				flag = false;
			}
		}
		return (input.getItem() instanceof FBPotionItem && flag);
	}

	@Override
	public boolean isIngredient(ItemStack ingredient) {
		Item item = ingredient.getItem();
		return (item.equals(Items.REDSTONE) || item.equals(ModItems.ENCHANTED_REDSTONE.get()) || item.equals(ModItems.ENCHANTED_REDSTONE_BLOCK.get()));
	}

	@Override
	public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
		Item item = ingredient.getItem();
		ItemStack output = input.copy();
		if(item.equals(Items.REDSTONE)) {
			output.getOrCreateTag().putByte("DurationModifier", (byte) 1);
		} else if(item.equals(ModItems.ENCHANTED_REDSTONE.get())) {
			output.getOrCreateTag().putByte("DurationModifier", (byte) 2);
		} else if(item.equals(ModItems.ENCHANTED_REDSTONE_BLOCK.get())) {
			output.getOrCreateTag().putByte("DurationModifier", (byte) 3);
		}
		return output.copy();
	}

}
