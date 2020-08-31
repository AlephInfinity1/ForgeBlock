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
	 
	public static final Potion STRENGTH_1_0_0 = new Potion("strength_1_0_0", new EffectInstance(Effects.STRENGTH, 3600, 0));
	public static final Potion STRENGTH_2_0_0 = new Potion("strength_2_0_0", new EffectInstance(Effects.STRENGTH, 3600, 1));
	public static final Potion STRENGTH_3_0_0 = new Potion("strength_3_0_0", new EffectInstance(Effects.STRENGTH, 3600, 2));
	public static final Potion STRENGTH_4_0_0 = new Potion("strength_4_0_0", new EffectInstance(Effects.STRENGTH, 3600, 3));
	public static final Potion STRENGTH_5_0_0 = new Potion("strength_5_0_0", new EffectInstance(Effects.STRENGTH, 3600, 4));
	public static final Potion STRENGTH_6_0_0 = new Potion("strength_6_0_0", new EffectInstance(Effects.STRENGTH, 3600, 5));
	public static final Potion STRENGTH_7_0_0 = new Potion("strength_7_0_0", new EffectInstance(Effects.STRENGTH, 3600, 6));
	public static final Potion STRENGTH_8_0_0 = new Potion("strength_8_0_0", new EffectInstance(Effects.STRENGTH, 3600, 7));

	public static final Potion STRENGTH_1_0_1 = new Potion("strength_1_0_1", new EffectInstance(Effects.STRENGTH, 9600, 0));
	public static final Potion STRENGTH_2_0_1 = new Potion("strength_2_0_1", new EffectInstance(Effects.STRENGTH, 9600, 1));
	public static final Potion STRENGTH_3_0_1 = new Potion("strength_3_0_1", new EffectInstance(Effects.STRENGTH, 9600, 2));
	public static final Potion STRENGTH_4_0_1 = new Potion("strength_4_0_1", new EffectInstance(Effects.STRENGTH, 9600, 3));
	public static final Potion STRENGTH_5_0_1 = new Potion("strength_5_0_1", new EffectInstance(Effects.STRENGTH, 9600, 4));
	public static final Potion STRENGTH_6_0_1 = new Potion("strength_6_0_1", new EffectInstance(Effects.STRENGTH, 9600, 5));
	public static final Potion STRENGTH_7_0_1 = new Potion("strength_7_0_1", new EffectInstance(Effects.STRENGTH, 9600, 6));
	public static final Potion STRENGTH_8_0_1 = new Potion("strength_8_0_1", new EffectInstance(Effects.STRENGTH, 9600, 7));

	public static final RegistryObject<Potion> STRENGTH_1_0_0_OBJECT = POTION_TYPES.register("strength_1_0_0", () -> STRENGTH_1_0_0);
	public static final RegistryObject<Potion> STRENGTH_2_0_0_OBJECT = POTION_TYPES.register("strength_2_0_0", () -> STRENGTH_2_0_0);
	public static final RegistryObject<Potion> STRENGTH_3_0_0_OBJECT = POTION_TYPES.register("strength_3_0_0", () -> STRENGTH_3_0_0);
	public static final RegistryObject<Potion> STRENGTH_4_0_0_OBJECT = POTION_TYPES.register("strength_4_0_0", () -> STRENGTH_4_0_0);
	public static final RegistryObject<Potion> STRENGTH_5_0_0_OBJECT = POTION_TYPES.register("strength_5_0_0", () -> STRENGTH_5_0_0);
	public static final RegistryObject<Potion> STRENGTH_6_0_0_OBJECT = POTION_TYPES.register("strength_6_0_0", () -> STRENGTH_6_0_0);
	public static final RegistryObject<Potion> STRENGTH_7_0_0_OBJECT = POTION_TYPES.register("strength_7_0_0", () -> STRENGTH_7_0_0);
	public static final RegistryObject<Potion> STRENGTH_8_0_0_OBJECT = POTION_TYPES.register("strength_8_0_0", () -> STRENGTH_8_0_0);

}
