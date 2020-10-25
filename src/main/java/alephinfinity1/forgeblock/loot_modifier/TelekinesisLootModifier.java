package alephinfinity1.forgeblock.loot_modifier;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameters;
import net.minecraft.world.storage.loot.conditions.ILootCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;

public class TelekinesisLootModifier extends LootModifier {

	protected TelekinesisLootModifier(ILootCondition[] conditionsIn) {
		super(conditionsIn);
	}

	@Override
	protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
		List<ItemStack> modified = new ArrayList<>();
		Entity entity = context.get(LootParameters.THIS_ENTITY);
		if(entity != null)
			generatedLoot.forEach((itemstack) -> {
				ItemStack _new = itemstack.copy();
				CompoundNBT nbt = _new.getOrCreateTag();
				nbt.putUniqueId("MinedBy", entity.getUniqueID());
				modified.add(_new);
			});
		return modified;
	}
	
	public static class Serializer extends GlobalLootModifierSerializer<TelekinesisLootModifier> {

		@Override
		public TelekinesisLootModifier read(ResourceLocation location, JsonObject object,
				ILootCondition[] ailootcondition) {
			return new TelekinesisLootModifier(ailootcondition);
		}
		
	}

}
