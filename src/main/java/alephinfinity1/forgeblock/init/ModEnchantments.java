package alephinfinity1.forgeblock.init;

import alephinfinity1.forgeblock.ForgeBlock;
import alephinfinity1.forgeblock.enchantment.CriticalEnchantment;
import alephinfinity1.forgeblock.enchantment.CubismEnchantment;
import alephinfinity1.forgeblock.enchantment.EnderSlayerEnchantment;
import alephinfinity1.forgeblock.enchantment.ExecuteEnchantment;
import alephinfinity1.forgeblock.enchantment.ExperienceEnchantment;
import alephinfinity1.forgeblock.enchantment.FirstStrikeEnchantment;
import alephinfinity1.forgeblock.enchantment.GiantKillerEnchantment;
import alephinfinity1.forgeblock.enchantment.GrowthEnchantment;
import alephinfinity1.forgeblock.enchantment.LifeStealEnchantment;
import alephinfinity1.forgeblock.enchantment.LuckEnchantment;
import alephinfinity1.forgeblock.enchantment.OneForAllEnchantment;
import alephinfinity1.forgeblock.enchantment.ProtectionEnchantment;
import alephinfinity1.forgeblock.enchantment.ScavengerEnchantment;
import alephinfinity1.forgeblock.enchantment.SharpnessEnchantment;
import alephinfinity1.forgeblock.enchantment.SmeltingTouchEnchantment;
import alephinfinity1.forgeblock.enchantment.SyphonEnchantment;
import alephinfinity1.forgeblock.enchantment.TelekinesisEnchantment;
import alephinfinity1.forgeblock.enchantment.UltimateWiseEnchantment;
import alephinfinity1.forgeblock.enchantment.VampirismEnchantment;
import alephinfinity1.forgeblock.enchantment.VenomousEnchantment;
import alephinfinity1.forgeblock.enchantment.ViciousEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEnchantments {

	public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, ForgeBlock.MOD_ID);
	public static final DeferredRegister<Enchantment> OVERRIDE = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, ForgeBlock.MINECRAFT_ID);
	
	public static final RegistryObject<Enchantment> SHARPNESS = OVERRIDE.register("sharpness", () -> new SharpnessEnchantment());
	public static final RegistryObject<Enchantment> GROWTH = ENCHANTMENTS.register("growth", () -> new GrowthEnchantment());
	public static final RegistryObject<Enchantment> TELEKINESIS = ENCHANTMENTS.register("telekinesis", () -> new TelekinesisEnchantment());
	public static final RegistryObject<Enchantment> LIFE_STEAL = ENCHANTMENTS.register("life_steal", () -> new LifeStealEnchantment());
	public static final RegistryObject<Enchantment> CRITICAL = ENCHANTMENTS.register("critical", () -> new CriticalEnchantment());
	public static final RegistryObject<Enchantment> CUBISM = ENCHANTMENTS.register("cubism", () -> new CubismEnchantment());
	public static final RegistryObject<Enchantment> ENDER_SLAYER = ENCHANTMENTS.register("ender_slayer", () -> new EnderSlayerEnchantment());
	public static final RegistryObject<Enchantment> EXECUTE = ENCHANTMENTS.register("execute", () -> new ExecuteEnchantment());
	public static final RegistryObject<Enchantment> FIRST_STRIKE = ENCHANTMENTS.register("first_strike", () -> new FirstStrikeEnchantment());
	public static final RegistryObject<Enchantment> VENOMOUS = ENCHANTMENTS.register("venomous", () -> new VenomousEnchantment());
	public static final RegistryObject<Enchantment> GIANT_KILLER = ENCHANTMENTS.register("giant_killer", () -> new GiantKillerEnchantment());
	public static final RegistryObject<Enchantment> LUCK = ENCHANTMENTS.register("luck", () -> new LuckEnchantment());
	public static final RegistryObject<Enchantment> PROTECTION = OVERRIDE.register("protection", () -> new ProtectionEnchantment());
	public static final RegistryObject<Enchantment> SMELTING_TOUCH = ENCHANTMENTS.register("smelting_touch", () -> new SmeltingTouchEnchantment());
	public static final RegistryObject<Enchantment> EXPERIENCE = ENCHANTMENTS.register("experience", () -> new ExperienceEnchantment());
	public static final RegistryObject<Enchantment> SCAVENGER = ENCHANTMENTS.register("scavenger", () -> new ScavengerEnchantment());
	public static final RegistryObject<Enchantment> VAMPIRISM = ENCHANTMENTS.register("vampirism", () -> new VampirismEnchantment());
	public static final RegistryObject<Enchantment> VICIOUS = ENCHANTMENTS.register("vicious", () -> new ViciousEnchantment());
	public static final RegistryObject<Enchantment> SYPHON = ENCHANTMENTS.register("syphon", () -> new SyphonEnchantment());
	
	public static final RegistryObject<Enchantment> ULTIMATE_WISE = ENCHANTMENTS.register("ultimate_wise", () -> new UltimateWiseEnchantment());
	public static final RegistryObject<Enchantment> ONE_FOR_ALL = ENCHANTMENTS.register("one_for_all", () -> new OneForAllEnchantment());

}
