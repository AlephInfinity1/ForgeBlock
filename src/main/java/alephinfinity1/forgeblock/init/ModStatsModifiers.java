package alephinfinity1.forgeblock.init;

import alephinfinity1.forgeblock.ForgeBlock;
import alephinfinity1.forgeblock.misc.capability.stats_modifier.AbstractStatsModifier;
import alephinfinity1.forgeblock.misc.capability.stats_modifier.HotPotatoBookModifier;
import alephinfinity1.forgeblock.misc.capability.stats_modifier.RecombobulatorModifier;
import alephinfinity1.forgeblock.misc.capability.stats_modifier.WoodSingularityModifier;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

public class ModStatsModifiers {
	
	public static final DeferredRegister<AbstractStatsModifier> STATS_MODIFIERS = DeferredRegister.create(AbstractStatsModifier.class, ForgeBlock.MOD_ID);
	
	public static final RegistryObject<AbstractStatsModifier> HOT_POTATO_BOOK = STATS_MODIFIERS.register("hot_potato_book", () -> new HotPotatoBookModifier());
	public static final RegistryObject<AbstractStatsModifier> WOOD_SINGULARITY = STATS_MODIFIERS.register("wood_singularity", () -> new WoodSingularityModifier());
	public static final RegistryObject<AbstractStatsModifier> RECOMBOBULATOR = STATS_MODIFIERS.register("recombobulator", () -> new RecombobulatorModifier());

}
