package alephinfinity1.forgeblock.init;

import alephinfinity1.forgeblock.ForgeBlock;
import alephinfinity1.forgeblock.effect.EnderWarpEffect;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEffects {

	public static final DeferredRegister<Effect> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, ForgeBlock.MOD_ID);
	
	public static final Effect ENDER_WARP = new EnderWarpEffect(EffectType.BENEFICIAL, 8171462).addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "91AEAA56-376B-4498-935B-2F7F68070635", (double)0.5F, AttributeModifier.Operation.MULTIPLY_BASE);
	
	public static final RegistryObject<Effect> ENDER_WARP_OBJECT = POTIONS.register("ender_warp", () -> ENDER_WARP);

}
