package alephinfinity1.forgeblock.config;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;

import alephinfinity1.forgeblock.misc.AttributeDisplayType;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod.EventBusSubscriber
public class FBModConfig {
	
	public static final String CATEGORY_GENERAL = "General";
	public static final String CATEGORY_TIER = "Rarity";
	public static final String CATEGORY_ATTRIBUTES = "Stats/Attributes";

	public static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();
	
	public static ForgeConfigSpec CLIENT_CONFIG;
	
	//Tier colours.
	public static ForgeConfigSpec.EnumValue<TextFormatting> POOR_TIER_COLOR;
	public static ForgeConfigSpec.EnumValue<TextFormatting> COMMON_TIER_COLOR;
	public static ForgeConfigSpec.EnumValue<TextFormatting> UNCOMMON_TIER_COLOR;
	public static ForgeConfigSpec.EnumValue<TextFormatting> RARE_TIER_COLOR;
	public static ForgeConfigSpec.EnumValue<TextFormatting> EPIC_TIER_COLOR;
	public static ForgeConfigSpec.EnumValue<TextFormatting> LEGENDARY_TIER_COLOR;
	public static ForgeConfigSpec.EnumValue<TextFormatting> MYTHIC_TIER_COLOR;
	public static ForgeConfigSpec.EnumValue<TextFormatting> SUPREME_TIER_COLOR;
	public static ForgeConfigSpec.EnumValue<TextFormatting> SPECIAL_TIER_COLOR;
	public static ForgeConfigSpec.EnumValue<TextFormatting> VERY_SPECIAL_TIER_COLOR;
	
	//The way to display attributes.
	public static ForgeConfigSpec.EnumValue<AttributeDisplayType> ATTRIBUTE_DISPLAY_TYPE;
	
	//Whether high-level and rare enchants should be golden instead of blue.
	public static ForgeConfigSpec.BooleanValue ENABLE_GOLDEN_ENCHANTS;
	
	//Whether to display rarity glow.
	public static ForgeConfigSpec.BooleanValue RARITY_GLOW;
	
	//The opacity of the rarity glow. 0.0d for fully transparent and 1.0d for fully opaque.
	public static ForgeConfigSpec.DoubleValue RARITY_GLOW_OPACITY;
	
	static {
		CLIENT_BUILDER.comment("General config").push(CATEGORY_GENERAL);
		ENABLE_GOLDEN_ENCHANTS = CLIENT_BUILDER.comment("Enable golden enchants").define("goldenEnchants", true);
		CLIENT_BUILDER.pop();
		
		CLIENT_BUILDER.comment("Rarity config").push(CATEGORY_TIER);
		setupTierConfig();
		CLIENT_BUILDER.pop();
		
		CLIENT_BUILDER.comment("Stats config").push(CATEGORY_ATTRIBUTES);
		setupAttributesConfig();
		CLIENT_BUILDER.pop();
		
		CLIENT_CONFIG = CLIENT_BUILDER.build();
		
	}

	public static void setupTierConfig() {
		List<TextFormatting> colors = Arrays.asList(TextFormatting.AQUA, TextFormatting.BLACK, TextFormatting.BLUE, TextFormatting.DARK_AQUA, TextFormatting.DARK_BLUE, TextFormatting.DARK_GRAY, TextFormatting.DARK_GREEN, TextFormatting.DARK_PURPLE, TextFormatting.DARK_RED, TextFormatting.GOLD, TextFormatting.GRAY, TextFormatting.GREEN, TextFormatting.LIGHT_PURPLE, TextFormatting.RED, TextFormatting.WHITE, TextFormatting.YELLOW);

		POOR_TIER_COLOR = CLIENT_BUILDER.comment("Poor rarity color").defineEnum("poorColor", TextFormatting.GRAY, colors);
		COMMON_TIER_COLOR = CLIENT_BUILDER.comment("Common rarity color").defineEnum("commonColor", TextFormatting.WHITE, colors);
		UNCOMMON_TIER_COLOR = CLIENT_BUILDER.comment("Uncommon rarity color").defineEnum("uncommonColor", TextFormatting.GREEN, colors);
		RARE_TIER_COLOR = CLIENT_BUILDER.comment("Rare rarity color").defineEnum("rareColor", TextFormatting.BLUE, colors);
		EPIC_TIER_COLOR = CLIENT_BUILDER.comment("Epic rarity color").defineEnum("epicColor", TextFormatting.DARK_PURPLE, colors);
		LEGENDARY_TIER_COLOR = CLIENT_BUILDER.comment("Legendary rarity color").defineEnum("legendaryColor", TextFormatting.GOLD, colors);
		MYTHIC_TIER_COLOR = CLIENT_BUILDER.comment("Mythic rarity color").defineEnum("mythicColor", TextFormatting.LIGHT_PURPLE, colors);
		SUPREME_TIER_COLOR = CLIENT_BUILDER.comment("Supreme rarity color").defineEnum("supremeColor", TextFormatting.DARK_RED, colors);
		SPECIAL_TIER_COLOR = CLIENT_BUILDER.comment("Special rarity color").defineEnum("specialColor", TextFormatting.RED, colors);
		VERY_SPECIAL_TIER_COLOR = CLIENT_BUILDER.comment("Very special rarity color").defineEnum("verySpecialColor", TextFormatting.RED, colors);
		
		RARITY_GLOW = CLIENT_BUILDER.comment("Enable rarity glow").define("rarityGlow", true);
		RARITY_GLOW_OPACITY = CLIENT_BUILDER.comment("Rarity glow opacity").defineInRange("rarityGlowOpacity", 0.5d, 0.0d, 1.0d);

	}
	
	public static void setupAttributesConfig() {
		List<AttributeDisplayType> acceptableValues = Arrays.asList(AttributeDisplayType.TEXT, AttributeDisplayType.SHORT, AttributeDisplayType.BOTH);
		
		ATTRIBUTE_DISPLAY_TYPE = CLIENT_BUILDER.comment("Stat display type").defineEnum("attributesDisplayType", AttributeDisplayType.TEXT, acceptableValues);
	}
	
	public static void loadConfig(ForgeConfigSpec spec, Path path) {
		final CommentedFileConfig configData = CommentedFileConfig.builder(path).sync().autosave().writingMode(WritingMode.REPLACE).build();

		configData.load();
		spec.setConfig(configData);
	}
	
	@SubscribeEvent
    public static void onLoad(final ModConfig.Loading configEvent) {
    }
	
	@SubscribeEvent
    public static void onReload(final ModConfig.Reloading configEvent) {
    }

}
