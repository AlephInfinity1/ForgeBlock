package alephinfinity1.forgeblock.loot.conditions;

import java.util.Random;
import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

import alephinfinity1.forgeblock.attribute.FBAttributes;
import alephinfinity1.forgeblock.init.ModEnchantments;
import alephinfinity1.forgeblock.misc.RNGHelper;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameter;
import net.minecraft.world.storage.loot.LootParameters;
import net.minecraft.world.storage.loot.conditions.ILootCondition;

public class RandomChanceWithModifiers implements ILootCondition {
	private final float chance;
	private final float luckMultiplier;
	private final float MFMultiplier;
	private final float PLMultiplier;
	
	private RandomChanceWithModifiers(float chance, float luckMultiplier, float MFMultiplier, float PLMultiplier) {
		this.chance = chance;
		this.luckMultiplier = luckMultiplier;
		this.MFMultiplier = MFMultiplier;
		this.PLMultiplier = PLMultiplier;
	}

	@Override
	public Set<LootParameter<?>> getRequiredParameters() {
		return ImmutableSet.of(LootParameters.KILLER_ENTITY);
	}

	@Override
	public boolean test(LootContext t) {
		float chanceMultiplier = 0.0f;
		Entity entity = t.get(LootParameters.KILLER_ENTITY);
		if(entity instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) entity;
			int luck = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.LUCK.get(), player.getHeldItemMainhand());
			double mf = player.getAttribute(FBAttributes.MAGIC_FIND).getValue();
			double pl = player.getAttribute(FBAttributes.PET_LUCK).getValue();
			chanceMultiplier += luck * luckMultiplier;
			chanceMultiplier += mf * MFMultiplier;
			chanceMultiplier += pl * PLMultiplier;
		}
		return RNGHelper.randomChance(chance * (1 + chanceMultiplier), new Random());
	}

	public static ILootCondition.IBuilder builder(float chance, float luckMultiplier, float MFMultiplier, float PLMultiplier) {
		return () -> {
			return new RandomChanceWithModifiers(chance, luckMultiplier, MFMultiplier, PLMultiplier);
		};
	}
	
	public static class Serializer extends ILootCondition.AbstractSerializer<RandomChanceWithModifiers> {

		public Serializer() {
			super(new ResourceLocation("forgeblock", "random_chance_with_modifiers"), RandomChanceWithModifiers.class);
		}
		
		@Override
		public void serialize(JsonObject json, RandomChanceWithModifiers value, JsonSerializationContext context) {
			json.addProperty("chance", value.chance);
			json.addProperty("luck_multiplier", value.luckMultiplier);
			json.addProperty("mf_multiplier", value.MFMultiplier);
			json.addProperty("pl_multiplier", value.PLMultiplier);
		}

		@Override
		public RandomChanceWithModifiers deserialize(JsonObject json, JsonDeserializationContext context) {
			return new RandomChanceWithModifiers(JSONUtils.getFloat(json, "chance"),
					JSONUtils.getFloat(json, "luck_multiplier"),
					JSONUtils.getFloat(json, "mf_multiplier"),
					JSONUtils.getFloat(json, "pl_multiplier"));
		}
		
	}

}
