package alephinfinity1.forgeblock.init;

import alephinfinity1.forgeblock.ForgeBlock;
import alephinfinity1.forgeblock.attribute.FBAttributes;
import alephinfinity1.forgeblock.effect.EnderWarpEffect;
import alephinfinity1.forgeblock.effect.FBAbsorptionEffect;
import alephinfinity1.forgeblock.effect.PoisonEffect;
import alephinfinity1.forgeblock.effect.RegenerationEffect;
import alephinfinity1.forgeblock.effect.SpeedEffect;
import alephinfinity1.forgeblock.effect.StrengthEffect;
import alephinfinity1.forgeblock.effect.VenomousEffect;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEffects {

	public static final DeferredRegister<Effect> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, ForgeBlock.MOD_ID);
	public static final DeferredRegister<Effect> OVERRIDE = DeferredRegister.create(ForgeRegistries.POTIONS, ForgeBlock.MINECRAFT_ID);
	
	public static final Effect ENDER_WARP = new EnderWarpEffect(EffectType.BENEFICIAL, 8171462).addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "866AD3A9-62BD-40DA-A330-F7F420EB8F66", (double) 0.05F, AttributeModifier.Operation.ADDITION);
	public static final Effect STRENGTH = new StrengthEffect(EffectType.BENEFICIAL, 9643043).addAttributesModifier(FBAttributes.STRENGTH, "9d8fca99-d0e2-472f-bb15-3d375d1ed78d", (double) 1.0F, AttributeModifier.Operation.ADDITION);
	public static final Effect ABSORPTION = new FBAbsorptionEffect(EffectType.BENEFICIAL, 2445989);
	public static final Effect REGENERATION = new RegenerationEffect(EffectType.BENEFICIAL, 13458603).addAttributesModifier(FBAttributes.HEALTH_REGEN, "b5941ec6-c55a-4f9e-938d-1463741b5186", (double) 1.0F, AttributeModifier.Operation.ADDITION);
	public static final Effect POISON = new PoisonEffect(EffectType.HARMFUL, 0x4E9331);
	public static final Effect VENOMOUS = new VenomousEffect(EffectType.HARMFUL, 0x430A09).addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "fb494452-6e5e-4c6c-9904-111cfd6c7f86", (double) 1.0F, AttributeModifier.Operation.MULTIPLY_BASE);
	public static final Effect SPEED = new SpeedEffect(EffectType.BENEFICIAL, 0x7CAFC6).addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "9e56fb33-df24-4d3f-875e-eb689db9f1eb", (double) 0.005F, AttributeModifier.Operation.ADDITION);
	public static final Effect SLOWNESS = new SpeedEffect(EffectType.HARMFUL, 0x5A6C81).addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "9e56fb33-df24-4d3f-875e-eb689db9f1eb", (double) -0.005F, AttributeModifier.Operation.ADDITION);
	
	public static final RegistryObject<Effect> ENDER_WARP_OBJECT = POTIONS.register("ender_warp", () -> ENDER_WARP);
	public static final RegistryObject<Effect> STRENGTH_OBJECT = OVERRIDE.register("strength", () -> STRENGTH);
	public static final RegistryObject<Effect> ABSORPTION_OBJECT = OVERRIDE.register("absorption", () -> ABSORPTION);
	public static final RegistryObject<Effect> REGENERATION_OBJECT = OVERRIDE.register("regeneration", () -> REGENERATION);
	public static final RegistryObject<Effect> POISON_OBJECT = OVERRIDE.register("poison", () -> POISON);
	public static final RegistryObject<Effect> VENOMOUS_OBJECT = POTIONS.register("venomous", () -> VENOMOUS);
	public static final RegistryObject<Effect> SPEED_OBJECT = OVERRIDE.register("speed", () -> SPEED);
	public static final RegistryObject<Effect> SLOWNESS_OBJECT = OVERRIDE.register("slowness", () -> SLOWNESS);

}
