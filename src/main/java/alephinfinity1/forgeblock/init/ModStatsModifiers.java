package alephinfinity1.forgeblock.init;

import alephinfinity1.forgeblock.ForgeBlock;
import alephinfinity1.forgeblock.misc.stats_modifier.AbstractStatsModifier;
import alephinfinity1.forgeblock.misc.stats_modifier.HotPotatoBookModifier;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

public class ModStatsModifiers {
	
	public static final DeferredRegister<AbstractStatsModifier> STATS_MODIFIERS = DeferredRegister.create(AbstractStatsModifier.class, ForgeBlock.MOD_ID);
	
	public static final RegistryObject<AbstractStatsModifier> HOT_POTATO_BOOK = STATS_MODIFIERS.register("hot_potato_book", () -> new HotPotatoBookModifier());

}
