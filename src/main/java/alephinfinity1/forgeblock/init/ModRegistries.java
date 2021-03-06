package alephinfinity1.forgeblock.init;

import alephinfinity1.forgeblock.ForgeBlock;
import alephinfinity1.forgeblock.misc.collections.FBCollection;
import alephinfinity1.forgeblock.misc.reforge.Reforge;
import alephinfinity1.forgeblock.misc.capability.stats_modifier.AbstractStatsModifier;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModRegistries {
	
	public static IForgeRegistry<Reforge> REFORGE = null;
	public static IForgeRegistry<AbstractStatsModifier> STATS_MODIFIER = null;
	public static IForgeRegistry<FBCollection> COLLECTION = null;
	
	@SubscribeEvent
	public static void registerCustomRegistries(RegistryEvent.NewRegistry event) {
		RegistryBuilder<Reforge> reforge = new RegistryBuilder<Reforge>()
				.setName(new ResourceLocation(ForgeBlock.MOD_ID, "reforge"))
				.setType(Reforge.class);
		reforge.create();
		REFORGE = GameRegistry.findRegistry(Reforge.class);
		
		RegistryBuilder<AbstractStatsModifier> statsModifier = new RegistryBuilder<AbstractStatsModifier>()
				.setName(new ResourceLocation(ForgeBlock.MOD_ID, "stats_modifier"))
				.setType(AbstractStatsModifier.class);
		statsModifier.create();
		STATS_MODIFIER = GameRegistry.findRegistry(AbstractStatsModifier.class);
		
		RegistryBuilder<FBCollection> collection = new RegistryBuilder<FBCollection>()
				.setName(new ResourceLocation(ForgeBlock.MOD_ID, "collection"))
				.setType(FBCollection.class);
		collection.create();
		COLLECTION = GameRegistry.findRegistry(FBCollection.class);
	}

}
