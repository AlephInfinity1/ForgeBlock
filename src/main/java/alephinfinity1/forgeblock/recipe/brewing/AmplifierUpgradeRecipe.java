package alephinfinity1.forgeblock.recipe.brewing;

import alephinfinity1.forgeblock.init.ModItems;
import alephinfinity1.forgeblock.item.FBPotionItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.common.brewing.IBrewingRecipe;

public class AmplifierUpgradeRecipe implements IBrewingRecipe {

	@Override
	public boolean isInput(ItemStack input) {
		boolean flag = true;
		if(input.getTag() != null) {
			if(input.getTag().getByte("AmplifierModifier") != 0) {
				flag = false;
			}
		}
		return (input.getItem() instanceof FBPotionItem && flag);
	}

	@Override
	public boolean isIngredient(ItemStack ingredient) {
		Item item = ingredient.getItem();
		return (item.equals(Items.GLOWSTONE) || item.equals(ModItems.ENCHANTED_GLOWSTONE_DUST.get()) || item.equals(ModItems.ENCHANTED_GLOWSTONE.get()));
	}

	@Override
	public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
		Item item = ingredient.getItem();
		ItemStack output = input.copy();
		if(item.equals(Items.GLOWSTONE)) {
			output.getOrCreateTag().putByte("AmplifierModifier", (byte) 1);
		} else if(item.equals(ModItems.ENCHANTED_GLOWSTONE_DUST.get())) {
			output.getOrCreateTag().putByte("AmplifierModifier", (byte) 2);
		} else if(item.equals(ModItems.ENCHANTED_GLOWSTONE.get())) {
			output.getOrCreateTag().putByte("AmplifierModifier", (byte) 3);
		}
		return output.copy();
	}

}
