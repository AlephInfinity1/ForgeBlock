package alephinfinity1.forgeblock.init;

import alephinfinity1.forgeblock.ForgeBlock;
import alephinfinity1.forgeblock.recipe.MultiShapedRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModRecipes {
	
	public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, ForgeBlock.MOD_ID);
	
	//public static final RegistryObject<IRecipeSerializer<?>> MULTI_SHAPED_RECIPE = RECIPE_SERIALIZERS.register("multi_shaped_recipe", () -> new MultiShapedRecipe.Serializer());
}
