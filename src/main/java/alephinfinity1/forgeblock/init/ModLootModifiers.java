package alephinfinity1.forgeblock.init;

import alephinfinity1.forgeblock.ForgeBlock;
import alephinfinity1.forgeblock.loot_modifier.SmeltingTouchLootModifier;
import alephinfinity1.forgeblock.loot_modifier.TelekinesisLootModifier;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModLootModifiers {
	
	public static final DeferredRegister<GlobalLootModifierSerializer<?>> LOOT_MODIFIER_SERIALIZERS = DeferredRegister.create(ForgeRegistries.LOOT_MODIFIER_SERIALIZERS, ForgeBlock.MOD_ID);
	
	public static final RegistryObject<GlobalLootModifierSerializer<TelekinesisLootModifier>> TELEKINESIS_MODIFIER = 
			LOOT_MODIFIER_SERIALIZERS.register("telekinesis", () -> new TelekinesisLootModifier.Serializer());
	public static final RegistryObject<GlobalLootModifierSerializer<SmeltingTouchLootModifier>> SMELTING_TOUCH_MODIFIER =
			LOOT_MODIFIER_SERIALIZERS.register("smelting_touch", () -> new SmeltingTouchLootModifier.Serializer());
	
}
