package alephinfinity1.forgeblock.loot.functions;

import java.lang.reflect.Type;
import java.text.DecimalFormat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import alephinfinity1.forgeblock.attribute.FBAttributes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootFunction;
import net.minecraft.world.storage.loot.LootParameters;
import net.minecraft.world.storage.loot.conditions.ILootCondition;

public class NotifyKiller extends LootFunction {
	
	private static final Logger LOGGER = LogManager.getLogger();
	private final NotifyType type;
	private final boolean useMF;
	private final boolean usePL;
	
	public static class NotifyType {
		
		public static final NotifyType RARE = new NotifyType("misc.forgeblock.rareDrop");           //RARE DROP!
		public static final NotifyType EPIC = new NotifyType("misc.forgeblock.epicDrop");           //VERY RARE DROP!
		public static final NotifyType LEGENDARY = new NotifyType("misc.forgeblock.legendaryDrop"); //CRAZY RARE DROP!
		public static final NotifyType PET = new NotifyType("misc.forgeblock.petDrop");             //PET DROP!
		
		public String translationKey;
		
		private NotifyType(String translationKey) {
			this.translationKey = translationKey;
		}
		
		public static NotifyType parse(String str) {
			if(str.equals("rare")) {
				return RARE;
			} else if(str.equals("epic") || str.equals("very_rare")) {
				return EPIC;
			} else if(str.equals("legendary") || str.equals("crazy_rare")) {
				return LEGENDARY;
			} else if(str.equals("pet")) {
				return PET;
			} else return RARE;
		}
		
		@Override
		public String toString() {
			if(this.equals(RARE)) return "rare";
			else if(this.equals(EPIC)) return "epic";
			else if(this.equals(LEGENDARY)) return "legendary";
			else if(this.equals(PET)) return "pet";
			else return super.toString();
		}
		
		public static class Serializer implements JsonDeserializer<NotifyType>, JsonSerializer<NotifyType> {

			@Override
			public JsonElement serialize(NotifyType src, Type typeOfSrc, JsonSerializationContext context) {
				return new JsonPrimitive(src.toString());
			}

			@Override
			public NotifyType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
					throws JsonParseException {
				if(json.isJsonPrimitive()) return NotifyType.parse(json.getAsString());
				else throw new JsonParseException("Json must be a string");
			}
			
		}
	}

	protected NotifyKiller(ILootCondition[] conditionsIn, NotifyType type, boolean useMF, boolean usePL) {
		super(conditionsIn);
		this.type = type;
		this.useMF = useMF;
		this.usePL = usePL;
	}

	@Override
	protected ItemStack doApply(ItemStack stack, LootContext context) {
		Entity killer = context.get(LootParameters.KILLER_ENTITY);
		if(!(killer instanceof PlayerEntity)) LOGGER.trace("Killer is not a player.");
		else {
			PlayerEntity player = (PlayerEntity) killer;
			ITextComponent message = new TranslationTextComponent(this.type.translationKey, stack.getDisplayName().getString());
			if(this.useMF) {
				String mf = new DecimalFormat(",###.#").format(player.getAttribute(FBAttributes.MAGIC_FIND).getValue()).replaceAll("\u00A0", ",");
				message.appendSibling(new TranslationTextComponent("misc.forgeblock.magicFindBonus", mf));
			}
			if(this.usePL) {
				String pl = new DecimalFormat(",###.#").format(player.getAttribute(FBAttributes.PET_LUCK).getValue()).replaceAll("\u00A0", ",");
				message.appendSibling(new TranslationTextComponent("misc.forgeblock.petLuckBonus", pl));
			}
			player.sendMessage(message);
		}
		return stack;
	}
	
	public static class Serializer extends LootFunction.Serializer<NotifyKiller> {

		private Serializer(ResourceLocation p_i50228_1_, Class<NotifyKiller> p_i50228_2_) {
			super(p_i50228_1_, p_i50228_2_);
		}
		
		public Serializer() {
			super(new ResourceLocation("forgeblock", "notify_killer"), NotifyKiller.class);
		}

		@Override
		public NotifyKiller deserialize(JsonObject object, JsonDeserializationContext deserializationContext,
				ILootCondition[] conditionsIn) {
			return new NotifyKiller(conditionsIn, 
					NotifyType.parse(object.get("type").getAsString()),
					object.get("use_magic_find").getAsBoolean(),
					object.get("use_pet_luck").getAsBoolean());
		}
		
	}

}
