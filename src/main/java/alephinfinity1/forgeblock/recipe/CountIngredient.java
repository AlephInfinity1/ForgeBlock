package alephinfinity1.forgeblock.recipe;

import java.util.stream.Stream;

import javax.annotation.Nullable;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IIngredientSerializer;

public class CountIngredient extends Ingredient {
	
	public static final CountIngredient EMPTY = new CountIngredient(new ItemStack(Items.AIR));

	private final ItemStack stack;
	public CountIngredient(ItemStack stack)
	{
		super(Stream.of(new Ingredient.SingleItemList(stack)));
		this.stack = stack;
	}

	@Override
	public boolean test(@Nullable ItemStack input)
	{
		if (input == null)
			return false;
		//As long as the input's stack size >= required, it works.
		return this.stack.getItem() == input.getItem() && this.stack.getCount() <= input.getCount();
	}

	@Override
	public boolean isSimple() {
		return false;
	}

	@Override
	public IIngredientSerializer<? extends Ingredient> getSerializer() {
		return Serializer.INSTANCE;
	}

	@Override
	public JsonElement serialize()
	{
		JsonObject json = new JsonObject();
		json.addProperty("type", CraftingHelper.getID(Serializer.INSTANCE).toString());
		json.addProperty("item", stack.getItem().getRegistryName().toString());
		json.addProperty("count", stack.getCount());
		if (stack.hasTag())
			json.addProperty("nbt", stack.getTag().toString());
		return json;
	}
	
	public static CountIngredient deserialize(JsonElement element) {
		if(element == null) throw new JsonSyntaxException("Json cannot be null");
		else if(!element.isJsonObject()) throw new JsonSyntaxException("Json must be an object");
		else {
			return (CountIngredient) Serializer.INSTANCE.parse((JsonObject) element);
		}
	}
	
	public int getCount() {
		return this.stack.getCount();
	}
	
	public static CountIngredient read(PacketBuffer buffer) {
	      int i = buffer.readVarInt();
	      return new CountIngredient(buffer.readItemStack());
	}

	public static class Serializer implements IIngredientSerializer<CountIngredient>
	{
		public static final Serializer INSTANCE = new Serializer();

		@Override
		public CountIngredient parse(PacketBuffer buffer) {
			return new CountIngredient(buffer.readItemStack());
		}

		@Override
		public CountIngredient parse(JsonObject json) {
			return new CountIngredient(CraftingHelper.getItemStack(json, true));
		}

		@Override
		public void write(PacketBuffer buffer, CountIngredient ingredient) {
			buffer.writeItemStack(ingredient.stack);
		}
	}

}
