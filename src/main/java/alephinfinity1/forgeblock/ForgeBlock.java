package alephinfinity1.forgeblock;

import alephinfinity1.forgeblock.misc.capability.accessories.AccessoriesData;
import alephinfinity1.forgeblock.misc.capability.accessories.AccessoriesStorage;
import alephinfinity1.forgeblock.misc.capability.accessories.IAccessoriesData;
import alephinfinity1.forgeblock.misc.capability.coins.Coins;
import alephinfinity1.forgeblock.misc.capability.skills.Skills;
import alephinfinity1.forgeblock.misc.capability.stats_modifier.capability.ItemModifiers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import alephinfinity1.forgeblock.attribute.AttributeLimitsRemover;
import alephinfinity1.forgeblock.client.ClientEventBusSubscriber;
import alephinfinity1.forgeblock.config.FBModConfig;
import alephinfinity1.forgeblock.discordRPC.DiscordRpc;
import alephinfinity1.forgeblock.init.ModBlocks;
import alephinfinity1.forgeblock.init.ModCollections;
import alephinfinity1.forgeblock.init.ModContainerTypes;
import alephinfinity1.forgeblock.init.ModEffects;
import alephinfinity1.forgeblock.init.ModEnchantments;
import alephinfinity1.forgeblock.init.ModEntities;
import alephinfinity1.forgeblock.init.ModItems;
import alephinfinity1.forgeblock.init.ModLoot;
import alephinfinity1.forgeblock.init.ModLootModifiers;
import alephinfinity1.forgeblock.init.ModParticles;
import alephinfinity1.forgeblock.init.ModPotions;
import alephinfinity1.forgeblock.init.ModRecipes;
import alephinfinity1.forgeblock.init.ModReforges;
import alephinfinity1.forgeblock.init.ModStatsModifiers;
import alephinfinity1.forgeblock.init.ModTileEntityTypes;
import alephinfinity1.forgeblock.misc.capability.coins.CoinsFactory;
import alephinfinity1.forgeblock.misc.capability.coins.CoinsStorage;
import alephinfinity1.forgeblock.misc.capability.coins.ICoins;
import alephinfinity1.forgeblock.misc.collections.CollectionsDataFactory;
import alephinfinity1.forgeblock.misc.collections.CollectionsDataStorage;
import alephinfinity1.forgeblock.misc.collections.ICollectionsData;
import alephinfinity1.forgeblock.misc.capability.mana.IMana;
import alephinfinity1.forgeblock.misc.capability.mana.Mana;
import alephinfinity1.forgeblock.misc.capability.mana.ManaStorage;
import alephinfinity1.forgeblock.misc.capability.skills.ISkills;
import alephinfinity1.forgeblock.misc.capability.skills.SkillsFactory;
import alephinfinity1.forgeblock.misc.capability.skills.SkillsStorage;
import alephinfinity1.forgeblock.misc.capability.stats_modifier.capability.IItemModifiers;
import alephinfinity1.forgeblock.misc.capability.stats_modifier.capability.ItemModifiersFactory;
import alephinfinity1.forgeblock.misc.capability.stats_modifier.capability.ItemModifiersStorage;
import alephinfinity1.forgeblock.network.FBPacketHandler;
import alephinfinity1.forgeblock.recipe.brewing.BrewingRecipeRegistrar;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.fml.loading.FMLPaths;
import org.spongepowered.asm.launch.MixinBootstrap;

@Mod(value = ForgeBlock.MOD_ID)
@Mod.EventBusSubscriber
public class ForgeBlock {

	public static final String MOD_ID = "forgeblock";
	public static final String MINECRAFT_ID = "minecraft";

	public static final String VERSION = "0.0.162";
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

	//TODO: this doesnt support servers, crashes them as its a client side class only.
	public static final Minecraft MINECRAFT = Minecraft.getInstance();

	public ForgeBlock() {
		ModItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		ModItems.OVERRIDE.register(FMLJavaModLoadingContext.get().getModEventBus());
		ModBlocks.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
		ModBlocks.OVERRIDE.register(FMLJavaModLoadingContext.get().getModEventBus());
		ModEffects.POTIONS.register(FMLJavaModLoadingContext.get().getModEventBus());
		ModEffects.OVERRIDE.register(FMLJavaModLoadingContext.get().getModEventBus());
		ModPotions.POTION_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
		ModPotions.OVERRIDE.register(FMLJavaModLoadingContext.get().getModEventBus());
		ModEnchantments.ENCHANTMENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
		ModEnchantments.OVERRIDE.register(FMLJavaModLoadingContext.get().getModEventBus());
		ModEntities.ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
		ModEntities.OVERRIDE.register(FMLJavaModLoadingContext.get().getModEventBus());
		ModContainerTypes.CONTAINERS.register(FMLJavaModLoadingContext.get().getModEventBus());
		ModRecipes.RECIPE_SERIALIZERS.register(FMLJavaModLoadingContext.get().getModEventBus());
		ModParticles.PARTICLE_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
		ModLootModifiers.LOOT_MODIFIER_SERIALIZERS
		.register(FMLJavaModLoadingContext.get().getModEventBus());
		ModReforges.REFORGES.register(FMLJavaModLoadingContext.get().getModEventBus());
		ModStatsModifiers.STATS_MODIFIERS
		.register(FMLJavaModLoadingContext.get().getModEventBus());
		ModCollections.COLLECTIONS.register(FMLJavaModLoadingContext.get().getModEventBus());
		ModTileEntityTypes.TILE_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
		ModTileEntityTypes.OVERRIDE.register(FMLJavaModLoadingContext.get().getModEventBus());
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus()
		.addListener(ClientEventBusSubscriber::onClientSetup);

		ModLoot.init();

		FBPacketHandler.register();

		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, FBModConfig.CLIENT_CONFIG);
		FBModConfig.loadConfig(FBModConfig.CLIENT_CONFIG,
				FMLPaths.CONFIGDIR.get().resolve("forgeblock-client.toml"));

		MixinBootstrap.init();
	}

	private void setup(FMLCommonSetupEvent event) {
		AttributeLimitsRemover.removeLimits();
		CapabilityManager.INSTANCE.register(IMana.class, new ManaStorage(), Mana::new);
		CapabilityManager.INSTANCE.register(ISkills.class, new SkillsStorage(), Skills::new);
		CapabilityManager.INSTANCE.register(ICoins.class, new CoinsStorage(), Coins::new);
		CapabilityManager.INSTANCE.register(IItemModifiers.class, new ItemModifiersStorage(), ItemModifiers::new);
		//CapabilityManager.INSTANCE.register(ICollectionsData.class, new CollectionsDataStorage(), new CollectionsDataFactory());
		CapabilityManager.INSTANCE.register(IAccessoriesData.class, new AccessoriesStorage(), AccessoriesData::new);
		BrewingRecipeRegistrar.registerRecipes();

		//Checks if this a client, if so start discordRCP, if not just do nothing.
		if (FMLEnvironment.dist.isClient()) {
			DiscordRpc.getInstance().startRpc();
		}
	}

}