package alephinfinity1.forgeblock.recipe;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ICraftingRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class MultiShapedRecipe implements ICraftingRecipe, net.minecraftforge.common.crafting.IShapedRecipe<CraftingInventory> {
	static int MAX_WIDTH = 3;
	static int MAX_HEIGHT = 3;
	/**
	 * Expand the max width and height allowed in the deserializer.
	 * This should be called by modders who add custom crafting tables that are larger than the vanilla 3x3.
	 * @param width your max recipe width
	 * @param height your max recipe height
	 */
	public static void setCraftingSize(int width, int height) {
		if (MAX_WIDTH < width) MAX_WIDTH = width;
		if (MAX_HEIGHT < height) MAX_HEIGHT = height;
	}

	private final int recipeWidth;
	private final int recipeHeight;
	private final NonNullList<CountIngredient> recipeItems;
	private final ItemStack recipeOutput;
	private final ResourceLocation id;
	private final String group;

	public MultiShapedRecipe(ResourceLocation idIn, String groupIn, int recipeWidthIn, int recipeHeightIn, NonNullList<CountIngredient> recipeItemsIn, ItemStack recipeOutputIn) {
		this.id = idIn;
		this.group = groupIn;
		this.recipeWidth = recipeWidthIn;
		this.recipeHeight = recipeHeightIn;
		this.recipeItems = recipeItemsIn;
		this.recipeOutput = recipeOutputIn;
	}

	public ResourceLocation getId() {
		return this.id;
	}

	public IRecipeSerializer<?> getSerializer() {
		return IRecipeSerializer.CRAFTING_SHAPED;
	}

	/**
	 * Recipes with equal group are combined into one button in the recipe book
	 */
	public String getGroup() {
		return this.group;
	}

	/**
	 * Get the result of this recipe, usually for display purposes (e.g. recipe book). If your recipe has more than one
	 * possible result (e.g. it's dynamic and depends on its inputs), then return an empty stack.
	 */
	public ItemStack getRecipeOutput() {
		return this.recipeOutput;
	}

	public NonNullList<CountIngredient> getCountIngredients() {
		return this.recipeItems;
	}

	/**
	 * Used to determine if this recipe can fit in a grid of the given width/height
	 */
	public boolean canFit(int width, int height) {
		return width >= this.recipeWidth && height >= this.recipeHeight;
	}

	/**
	 * Used to check if a recipe matches current crafting inventory
	 */
	public boolean matches(CraftingInventory inv, World worldIn) {
		for(int i = 0; i <= inv.getWidth() - this.recipeWidth; ++i) {
			for(int j = 0; j <= inv.getHeight() - this.recipeHeight; ++j) {
				if (this.checkMatch(inv, i, j, true)) {
					return true;
				}

				if (this.checkMatch(inv, i, j, false)) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Checks if the region of a crafting inventory is match for the recipe.
	 */
	private boolean checkMatch(CraftingInventory craftingInventory, int p_77573_2_, int p_77573_3_, boolean p_77573_4_) {
		for(int i = 0; i < craftingInventory.getWidth(); ++i) {
			for(int j = 0; j < craftingInventory.getHeight(); ++j) {
				int k = i - p_77573_2_;
				int l = j - p_77573_3_;
				CountIngredient ingredient = CountIngredient.EMPTY;
				if (k >= 0 && l >= 0 && k < this.recipeWidth && l < this.recipeHeight) {
					if (p_77573_4_) {
						ingredient = this.recipeItems.get(this.recipeWidth - k - 1 + l * this.recipeWidth);
					} else {
						ingredient = this.recipeItems.get(k + l * this.recipeWidth);
					}
				}

				if (!ingredient.test(craftingInventory.getStackInSlot(i + j * craftingInventory.getWidth()))) {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * Returns an Item that is the result of this recipe
	 */
	public ItemStack getCraftingResult(CraftingInventory inv) {
		return this.getRecipeOutput().copy();
	}

	public int getWidth() {
		return this.recipeWidth;
	}

	@Override
	public int getRecipeWidth() {
		return getWidth();
	}

	public int getHeight() {
		return this.recipeHeight;
	}

	@Override
	public int getRecipeHeight() {
		return getHeight();
	}

	private static NonNullList<CountIngredient> deserializeCountIngredients(String[] pattern, Map<String, CountIngredient> keys, int patternWidth, int patternHeight) {
		NonNullList<CountIngredient> nonnulllist = NonNullList.withSize(patternWidth * patternHeight, CountIngredient.EMPTY);
		Set<String> set = Sets.newHashSet(keys.keySet());
		set.remove(" ");

		for(int i = 0; i < pattern.length; ++i) {
			for(int j = 0; j < pattern[i].length(); ++j) {
				String s = pattern[i].substring(j, j + 1);
				CountIngredient ingredient = keys.get(s);
				if (ingredient == null) {
					throw new JsonSyntaxException("Pattern references symbol '" + s + "' but it's not defined in the key");
				}

				set.remove(s);
				nonnulllist.set(j + patternWidth * i, ingredient);
			}
		}

		if (!set.isEmpty()) {
			throw new JsonSyntaxException("Key defines symbols that aren't used in pattern: " + set);
		} else {
			return nonnulllist;
		}
	}

	@VisibleForTesting
	static String[] shrink(String... toShrink) {
		int i = Integer.MAX_VALUE;
		int j = 0;
		int k = 0;
		int l = 0;

		for(int i1 = 0; i1 < toShrink.length; ++i1) {
			String s = toShrink[i1];
			i = Math.min(i, firstNonSpace(s));
			int j1 = lastNonSpace(s);
			j = Math.max(j, j1);
			if (j1 < 0) {
				if (k == i1) {
					++k;
				}

				++l;
			} else {
				l = 0;
			}
		}

		if (toShrink.length == l) {
			return new String[0];
		} else {
			String[] astring = new String[toShrink.length - l - k];

			for(int k1 = 0; k1 < astring.length; ++k1) {
				astring[k1] = toShrink[k1 + k].substring(i, j + 1);
			}

			return astring;
		}
	}

	private static int firstNonSpace(String str) {
		int i;
		for(i = 0; i < str.length() && str.charAt(i) == ' '; ++i) {
			;
		}

		return i;
	}

	private static int lastNonSpace(String str) {
		int i;
		for(i = str.length() - 1; i >= 0 && str.charAt(i) == ' '; --i) {
			;
		}

		return i;
	}

	private static String[] patternFromJson(JsonArray jsonArr) {
		String[] astring = new String[jsonArr.size()];
		if (astring.length > MAX_HEIGHT) {
			throw new JsonSyntaxException("Invalid pattern: too many rows, " + MAX_HEIGHT + " is maximum");
		} else if (astring.length == 0) {
			throw new JsonSyntaxException("Invalid pattern: empty pattern not allowed");
		} else {
			for(int i = 0; i < astring.length; ++i) {
				String s = JSONUtils.getString(jsonArr.get(i), "pattern[" + i + "]");
				if (s.length() > MAX_WIDTH) {
					throw new JsonSyntaxException("Invalid pattern: too many columns, " + MAX_WIDTH + " is maximum");
				}

				if (i > 0 && astring[0].length() != s.length()) {
					throw new JsonSyntaxException("Invalid pattern: each row must be the same width");
				}

				astring[i] = s;
			}

			return astring;
		}
	}

	/**
	 * Returns a key json object as a Java HashMap.
	 */
	private static Map<String, CountIngredient> deserializeKey(JsonObject json) {
		Map<String, CountIngredient> map = Maps.newHashMap();

		for(Entry<String, JsonElement> entry : json.entrySet()) {
			if (entry.getKey().length() != 1) {
				throw new JsonSyntaxException("Invalid key entry: '" + (String)entry.getKey() + "' is an invalid symbol (must be 1 character only).");
			}

			if (" ".equals(entry.getKey())) {
				throw new JsonSyntaxException("Invalid key entry: ' ' is a reserved symbol.");
			}

			map.put(entry.getKey(), CountIngredient.deserialize(entry.getValue()));
		}

		map.put(" ", CountIngredient.EMPTY);
		return map;
	}

	public static ItemStack deserializeItem(JsonObject p_199798_0_) {
		String s = JSONUtils.getString(p_199798_0_, "item");
		Item item = Registry.ITEM.getValue(new ResourceLocation(s)).orElseThrow(() -> {
			return new JsonSyntaxException("Unknown item '" + s + "'");
		});
		if (p_199798_0_.has("data")) {
			throw new JsonParseException("Disallowed data tag found");
		} else {
			int i = JSONUtils.getInt(p_199798_0_, "count", 1);
			return net.minecraftforge.common.crafting.CraftingHelper.getItemStack(p_199798_0_, true);
		}
	}

	public static class Serializer extends net.minecraftforge.registries.ForgeRegistryEntry<IRecipeSerializer<?>>  implements IRecipeSerializer<MultiShapedRecipe> {
		private static final ResourceLocation NAME = new ResourceLocation("forgeblock", "multi_crafting_shaped");
		public MultiShapedRecipe read(ResourceLocation recipeId, JsonObject json) {
			String s = JSONUtils.getString(json, "group", "");
			Map<String, CountIngredient> map = MultiShapedRecipe.deserializeKey(JSONUtils.getJsonObject(json, "key"));
			String[] astring = MultiShapedRecipe.shrink(MultiShapedRecipe.patternFromJson(JSONUtils.getJsonArray(json, "pattern")));
			int i = astring[0].length();
			int j = astring.length;
			NonNullList<CountIngredient> nonnulllist = MultiShapedRecipe.deserializeCountIngredients(astring, map, i, j);
			ItemStack itemstack = MultiShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "result"));
			return new MultiShapedRecipe(recipeId, s, i, j, nonnulllist, itemstack);
		}

		public MultiShapedRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
			int i = buffer.readVarInt();
			int j = buffer.readVarInt();
			String s = buffer.readString(32767);
			NonNullList<CountIngredient> nonnulllist = NonNullList.withSize(i * j, CountIngredient.EMPTY);

			for(int k = 0; k < nonnulllist.size(); ++k) {
				nonnulllist.set(k, CountIngredient.read(buffer));
			}

			ItemStack itemstack = buffer.readItemStack();
			return new MultiShapedRecipe(recipeId, s, i, j, nonnulllist, itemstack);
		}

		public void write(PacketBuffer buffer, MultiShapedRecipe recipe) {
			buffer.writeVarInt(recipe.recipeWidth);
			buffer.writeVarInt(recipe.recipeHeight);
			buffer.writeString(recipe.group);

			for(CountIngredient ingredient : recipe.recipeItems) {
				ingredient.write(buffer);
			}

			buffer.writeItemStack(recipe.recipeOutput);
		}
	}
	
	@Override
	public NonNullList<ItemStack> getRemainingItems(CraftingInventory inv) {
		NonNullList<ItemStack> nonnulllist = NonNullList.withSize(inv.getSizeInventory(), ItemStack.EMPTY);

		if(inv.getSizeInventory() == 9) { //3x3 crafting grid
			boolean row0, row1, row2, col0, col1, col2; //Check if each of the rows/slots is empty or not.
			row0 = !(inv.getStackInSlot(0).isEmpty() && inv.getStackInSlot(1).isEmpty() && inv.getStackInSlot(2).isEmpty());
			row1 = !(inv.getStackInSlot(3).isEmpty() && inv.getStackInSlot(4).isEmpty() && inv.getStackInSlot(5).isEmpty());
			row2 = !(inv.getStackInSlot(6).isEmpty() && inv.getStackInSlot(7).isEmpty() && inv.getStackInSlot(8).isEmpty());
			
			col0 = !(inv.getStackInSlot(0).isEmpty() && inv.getStackInSlot(3).isEmpty() && inv.getStackInSlot(6).isEmpty());
			col1 = !(inv.getStackInSlot(1).isEmpty() && inv.getStackInSlot(4).isEmpty() && inv.getStackInSlot(7).isEmpty());
			col2 = !(inv.getStackInSlot(2).isEmpty() && inv.getStackInSlot(5).isEmpty() && inv.getStackInSlot(8).isEmpty());
			
			int rows = 0, cols = 0, rowstart = 0, colstart = 0; //Rows: the number of rows in the grid. Start: when does the rows/columns start.
			if(row0) {++rows;} else {if(rowstart == 0) rowstart = 1;}
			if(row1) {++rows;} else {if(rowstart == 1) rowstart = 2;}
			if(row2) {++rows;} else {if(rowstart == 2) rowstart = 3;}
			if(row0 && row2 && !row1) ++rows; //If the middle row is skipped
			
			if(col0) {++cols;} else {if(colstart == 0) colstart = 1;}
			if(col1) {++cols;} else {if(colstart == 1) colstart = 2;}
			if(col2) {++cols;} else {if(colstart == 2) colstart = 3;}
			if(col0 && col2 && !col1) ++cols; //If the middle column is skipped
			
			NonNullList<ItemStack> newList = NonNullList.withSize(rows * cols, ItemStack.EMPTY);
			
			for(int i = rowstart; i < rowstart + rows; ++i) { //Gets all grids with an item in it.
				for(int j = colstart; j < colstart + cols; ++j) {
					newList.set((i - rowstart) * cols + (j - colstart), nonnulllist.get(i * 3 + j));
				}
			}
	
			for(int i = 0; i < newList.size(); ++i) {
				ItemStack item = inv.getStackInSlot(3 * (i / cols + rowstart) + i % cols + colstart); //Gets the corresponding item.
				if (item.hasContainerItem()) {
					newList.set(i, item.getContainerItem());
				} else {
					item.shrink(this.recipeItems.get(i).getCount() - 1);
				}
			}
		} else if(inv.getSizeInventory() == 4) { //Inventory crafting grid, 2x2
			boolean row0, row1, col0, col1;
			row0 = !(inv.getStackInSlot(0).isEmpty() && inv.getStackInSlot(1).isEmpty());
			row1 = !(inv.getStackInSlot(2).isEmpty() && inv.getStackInSlot(3).isEmpty());
			
			col0 = !(inv.getStackInSlot(0).isEmpty() && inv.getStackInSlot(2).isEmpty());
			col1 = !(inv.getStackInSlot(1).isEmpty() && inv.getStackInSlot(3).isEmpty());
			
			int rows = 0, cols = 0, rowstart = 0, colstart = 0;
			if(row0) {++rows;} else {if(rowstart == 0) rowstart = 1;}
			if(row1) {++rows;} else {if(rowstart == 1) rowstart = 2;}
			
			if(col0) {++cols;} else {if(colstart == 0) colstart = 1;}
			if(col1) {++cols;} else {if(colstart == 1) colstart = 2;}
			
			NonNullList<ItemStack> newList = NonNullList.withSize(rows * cols, ItemStack.EMPTY);
			
			for(int i = rowstart; i < rowstart + rows; ++i) { //Gets all grids with an item in it.
				for(int j = colstart; j < colstart + cols; ++j) {
					newList.set((i - rowstart) * cols + (j - colstart), nonnulllist.get(i * 2 + j));
				}
			}
			
			for(int i = 0; i < newList.size(); ++i) {
				ItemStack item = inv.getStackInSlot(2 * (i / cols + rowstart) + i % cols + colstart); //Gets the corresponding item.
				if (item.hasContainerItem()) {
					newList.set(i, item.getContainerItem());
				} else {
					item.shrink(this.recipeItems.get(i).getCount() - 1);
				}
			}
		}

		return nonnulllist;
	}
}