package alephinfinity1.forgeblock.recipe;

import java.util.Collection;
import java.util.Collections;
import java.util.function.Predicate;
import java.util.stream.Stream;

import com.google.gson.JsonObject;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient.IItemList;
import net.minecraftforge.registries.ForgeRegistries;

public class CountIngredient implements Predicate<ItemStack> {
	
	IItemList list;

	protected CountIngredient(Stream<? extends IItemList> itemLists) {
		super();
	}

	@Override
	public boolean test(ItemStack t) {
		return false;
	}
	
	public static class SingleItemList implements IItemList {

		private ItemStack stack;
		
		public SingleItemList(ItemStack stack) {
			this.stack = stack;
		}
		
		public SingleItemList(ItemStack stack, int count) {
			this.stack = stack;
			this.stack.setCount(count);
		}
		
		@Override
		public Collection<ItemStack> getStacks() {
			return Collections.singleton(stack);
		}

		@Override
		public JsonObject serialize() {
			JsonObject json = new JsonObject();
			json.addProperty("item", ForgeRegistries.ITEMS.getKey(stack.getItem()).toString());
			json.addProperty("count", stack.getCount());
			return json;
		}
		
	}
	
	/*
	public static class MultiItemList implements IItemList {

		private List<ItemStack> stacks;
		
		public MultiItemList(ItemStack stack) {
			stacks = Collections.singletonList(stack);
		}
		
		public MultiItemList(ItemStack... stacks) {
			this.stacks = Arrays.asList(stacks);
		}
		
		@Override
		public Collection<ItemStack> getStacks() {
			return stacks;
		}

		@Override
		public JsonObject serialize() {
			JsonObject json = new JsonObject();
			json.addProperty("item", ForgeRegistries.ITEMS.getKey(stacks.get(0).getItem()).toString());
			json.addProperty("count", stacks.get(0).getCount());
			return json;
		}
		
	}
	*/

	

}
