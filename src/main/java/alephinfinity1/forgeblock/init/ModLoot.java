package alephinfinity1.forgeblock.init;

import alephinfinity1.forgeblock.loot.conditions.RandomChanceWithModifiers;
import alephinfinity1.forgeblock.loot.functions.NotifyKiller;
import net.minecraft.world.storage.loot.conditions.LootConditionManager;
import net.minecraft.world.storage.loot.functions.LootFunctionManager;

public class ModLoot {

	public static void init() {
		LootConditionManager.registerCondition(new RandomChanceWithModifiers.Serializer());
		LootFunctionManager.registerFunction(new NotifyKiller.Serializer());
	}
	
}
