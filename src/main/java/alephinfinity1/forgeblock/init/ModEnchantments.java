package alephinfinity1.forgeblock.init;

import alephinfinity1.forgeblock.ForgeBlock;
import alephinfinity1.forgeblock.enchantment.*;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.PowerEnchantment;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEnchantments {

	public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, ForgeBlock.MOD_ID);
	public static final DeferredRegister<Enchantment> OVERRIDE = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, ForgeBlock.MINECRAFT_ID);
	
	public static final RegistryObject<Enchantment> SHARPNESS = OVERRIDE.register("sharpness", SharpnessEnchantment::new);
	public static final RegistryObject<Enchantment> GROWTH = ENCHANTMENTS.register("growth", GrowthEnchantment::new);
	public static final RegistryObject<Enchantment> TELEKINESIS = ENCHANTMENTS.register("telekinesis", TelekinesisEnchantment::new);
	public static final RegistryObject<Enchantment> LIFE_STEAL = ENCHANTMENTS.register("life_steal", LifeStealEnchantment::new);
	public static final RegistryObject<Enchantment> CRITICAL = ENCHANTMENTS.register("critical", CriticalEnchantment::new);
	public static final RegistryObject<Enchantment> CUBISM = ENCHANTMENTS.register("cubism", CubismEnchantment::new);
	public static final RegistryObject<Enchantment> ENDER_SLAYER = ENCHANTMENTS.register("ender_slayer", EnderSlayerEnchantment::new);
	public static final RegistryObject<Enchantment> EXECUTE = ENCHANTMENTS.register("execute", ExecuteEnchantment::new);
	public static final RegistryObject<Enchantment> FIRST_STRIKE = ENCHANTMENTS.register("first_strike", FirstStrikeEnchantment::new);
	public static final RegistryObject<Enchantment> VENOMOUS = ENCHANTMENTS.register("venomous", VenomousEnchantment::new);
	public static final RegistryObject<Enchantment> GIANT_KILLER = ENCHANTMENTS.register("giant_killer", GiantKillerEnchantment::new);
	public static final RegistryObject<Enchantment> LUCK = ENCHANTMENTS.register("luck", LuckEnchantment::new);
	public static final RegistryObject<Enchantment> PROTECTION = OVERRIDE.register("protection", ProtectionEnchantment::new);
	public static final RegistryObject<Enchantment> SMELTING_TOUCH = ENCHANTMENTS.register("smelting_touch", SmeltingTouchEnchantment::new);
	public static final RegistryObject<Enchantment> EXPERIENCE = ENCHANTMENTS.register("experience", ExperienceEnchantment::new);
	public static final RegistryObject<Enchantment> SCAVENGER = ENCHANTMENTS.register("scavenger", ScavengerEnchantment::new);
	public static final RegistryObject<Enchantment> VAMPIRISM = ENCHANTMENTS.register("vampirism", VampirismEnchantment::new);
	public static final RegistryObject<Enchantment> VICIOUS = ENCHANTMENTS.register("vicious", ViciousEnchantment::new);
	public static final RegistryObject<Enchantment> SYPHON = ENCHANTMENTS.register("syphon", SyphonEnchantment::new);

	//No need to override Power since the code that handles it is in BowItem, which has already been overridden.
	public static final RegistryObject<Enchantment> AIMING = ENCHANTMENTS.register("aiming", AimingEnchantment::new);
	public static final RegistryObject<Enchantment> SNIPE = ENCHANTMENTS.register("snipe", SnipeEnchantment::new);
	public static final RegistryObject<Enchantment> INFINITE_QUIVER = ENCHANTMENTS.register("infinite_quiver", InfiniteQuiverEnchantment::new);

	public static final RegistryObject<Enchantment> ULTIMATE_WISE = ENCHANTMENTS.register("ultimate_wise", UltimateWiseEnchantment::new);
	public static final RegistryObject<Enchantment> ONE_FOR_ALL = ENCHANTMENTS.register("one_for_all", OneForAllEnchantment::new);

}
