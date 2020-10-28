package alephinfinity1.forgeblock.init;

import alephinfinity1.forgeblock.ForgeBlock;
import alephinfinity1.forgeblock.misc.reforge.Reforge;
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
	
	@SubscribeEvent
	public static void registerCustomRegistries(RegistryEvent.NewRegistry event) {
		RegistryBuilder<Reforge> reforge = new RegistryBuilder<Reforge>()
				.setName(new ResourceLocation(ForgeBlock.MOD_ID, "reforge"))
				.setType(Reforge.class);
		reforge.create();
		REFORGE = GameRegistry.findRegistry(Reforge.class);
	}

}
