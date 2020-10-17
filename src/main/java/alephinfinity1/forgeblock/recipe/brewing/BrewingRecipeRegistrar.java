package alephinfinity1.forgeblock.recipe.brewing;

import net.minecraftforge.common.brewing.BrewingRecipeRegistry;

public class BrewingRecipeRegistrar {

	public static void registerRecipes() {
		BrewingRecipeRegistry.addRecipe(new AmplifierUpgradeRecipe());
		BrewingRecipeRegistry.addRecipe(new DurationUpgradeRecipe());
	}
}
