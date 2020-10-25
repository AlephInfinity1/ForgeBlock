package alephinfinity1.forgeblock.loot_modifier;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.conditions.ILootCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.items.ItemHandlerHelper;

public class SmeltingTouchLootModifier extends LootModifier {

	protected SmeltingTouchLootModifier(ILootCondition[] conditionsIn) {
		super(conditionsIn);
	}

	@Override
	protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
		List<ItemStack> modified = new ArrayList<>();
		generatedLoot.forEach((stack) -> {
			List<FurnaceRecipe> recipes = context.getWorld().getRecipeManager().getRecipes(IRecipeType.SMELTING, new Inventory(stack), context.getWorld());
			if(recipes.isEmpty()) {
				modified.add(stack);
			} else {
				ItemStack _new = ItemHandlerHelper.copyStackWithSize(recipes.get(0).getRecipeOutput(), recipes.get(0).getRecipeOutput().getCount() * stack.getCount());
				_new.setTag(stack.getTag());
				modified.add(_new);
			}
		});
		return modified;
	}
	
	public static class Serializer extends GlobalLootModifierSerializer<SmeltingTouchLootModifier> {

		@Override
		public SmeltingTouchLootModifier read(ResourceLocation location, JsonObject object,
				ILootCondition[] ailootcondition) {
			return new SmeltingTouchLootModifier(ailootcondition);
		}
		
	}

}
