package alephinfinity1.forgeblock.init;

import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import alephinfinity1.forgeblock.ForgeBlock;
import alephinfinity1.forgeblock.misc.collections.CollectionRewards;
import alephinfinity1.forgeblock.misc.collections.CollectionType;
import alephinfinity1.forgeblock.misc.collections.FBCollection;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

public class ModCollections {
	
	public static final DeferredRegister<FBCollection> COLLECTIONS = DeferredRegister.create(FBCollection.class, ForgeBlock.MOD_ID);
	
	public static final RegistryObject<FBCollection> COBBLESTONE_COLLECTION = COLLECTIONS.register("cobblestone", () -> new FBCollection(
			CollectionType.MINING,
			Stream.of(
					new AbstractMap.SimpleEntry<Integer, CollectionRewards>(1000, 
							new CollectionRewards(new ResourceLocation("forgeblock:enchanted_cobblestone")))
					).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)),
			Stream.of(
					new AbstractMap.SimpleEntry<Item, Integer>(Items.COBBLESTONE, 1),
					new AbstractMap.SimpleEntry<Item, Integer>(ModItems.ENCHANTED_COBBLESTONE.get(), 160),
					new AbstractMap.SimpleEntry<Item, Integer>(ModItems.ENCHANTED_STONE.get(), 25600)
					).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
			));
}
