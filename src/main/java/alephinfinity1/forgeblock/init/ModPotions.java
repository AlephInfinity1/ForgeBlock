package alephinfinity1.forgeblock.init;

import alephinfinity1.forgeblock.ForgeBlock;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModPotions {

	public static final DeferredRegister<Potion> POTION_TYPES = DeferredRegister.create(ForgeRegistries.POTION_TYPES, ForgeBlock.MOD_ID);
	public static final DeferredRegister<Potion> OVERRIDE = DeferredRegister.create(ForgeRegistries.POTION_TYPES, ForgeBlock.MINECRAFT_ID);
	 
	public static final Potion STRENGTH_1 = new Potion("strength_1", new EffectInstance(Effects.STRENGTH, 3600, 0));
	public static final Potion STRENGTH_3 = new Potion("strength_3", new EffectInstance(Effects.STRENGTH, 3600, 2));
	public static final Potion STRENGTH_5 = new Potion("strength_5", new EffectInstance(Effects.STRENGTH, 3600, 4));
	public static final Potion ABSORPTION_1 = new Potion("absorption_1", new EffectInstance(Effects.ABSORPTION, 3600, 0));
	public static final Potion ABSORPTION_3 = new Potion("absorption_3", new EffectInstance(Effects.ABSORPTION, 3600, 2));
	public static final Potion ABSORPTION_5 = new Potion("absorption_5", new EffectInstance(Effects.ABSORPTION, 3600, 4));
	public static final Potion REGENERATION_1 = new Potion("regeneration_1", new EffectInstance(Effects.REGENERATION, 900, 0));
	public static final Potion REGENERATION_5 = new Potion("regeneration_5", new EffectInstance(Effects.REGENERATION, 900, 4));
	public static final Potion SPEED_1 = new Potion("speed_1", new EffectInstance(Effects.SPEED, 3600, 0));
	public static final Potion SPEED_3 = new Potion("speed_3", new EffectInstance(Effects.SPEED, 3600, 2));
	public static final Potion SPEED_5 = new Potion("speed_5", new EffectInstance(Effects.SPEED, 3600, 4));
	public static final Potion SLOWNESS_1 = new Potion("slowness_1", new EffectInstance(Effects.SLOWNESS, 1800, 0));
	public static final Potion SLOWNESS_3 = new Potion("slowness_3", new EffectInstance(Effects.SLOWNESS, 1800, 2));
	public static final Potion SLOWNESS_5 = new Potion("slowness_5", new EffectInstance(Effects.SLOWNESS, 1800, 4));

	public static final RegistryObject<Potion> STRENGTH_1_OBJECT = POTION_TYPES.register("strength_1", () -> STRENGTH_1);
	public static final RegistryObject<Potion> STRENGTH_3_OBJECT = POTION_TYPES.register("strength_3", () -> STRENGTH_3);
	public static final RegistryObject<Potion> STRENGTH_5_OBJECT = POTION_TYPES.register("strength_5", () -> STRENGTH_5);
	public static final RegistryObject<Potion> ABSORPTION_1_OBJECT = POTION_TYPES.register("absorption_1", () -> ABSORPTION_1);
	public static final RegistryObject<Potion> ABSORPTION_3_OBJECT = POTION_TYPES.register("absorption_3", () -> ABSORPTION_3);
	public static final RegistryObject<Potion> ABSORPTION_5_OBJECT = POTION_TYPES.register("absorption_5", () -> ABSORPTION_5);
	public static final RegistryObject<Potion> REGENERATION_1_OBJECT = POTION_TYPES.register("regeneration_1", () -> REGENERATION_1);
	public static final RegistryObject<Potion> REGENERATION_5_OBJECT = POTION_TYPES.register("regeneration_5", () -> REGENERATION_5);
	public static final RegistryObject<Potion> SPEED_1_OBJECT = POTION_TYPES.register("speed_1", () -> SPEED_1);
	public static final RegistryObject<Potion> SPEED_3_OBJECT = POTION_TYPES.register("speed_3", () -> SPEED_3);
	public static final RegistryObject<Potion> SPEED_5_OBJECT = POTION_TYPES.register("speed_5", () -> SPEED_5);
	public static final RegistryObject<Potion> SLOWNESS_1_OBJECT = POTION_TYPES.register("slowness_1", () -> SLOWNESS_1);
	public static final RegistryObject<Potion> SLOWNESS_3_OBJECT = POTION_TYPES.register("slowness_3", () -> SLOWNESS_3);
	public static final RegistryObject<Potion> SLOWNESS_5_OBJECT = POTION_TYPES.register("slowness_5", () -> SLOWNESS_5);

}
