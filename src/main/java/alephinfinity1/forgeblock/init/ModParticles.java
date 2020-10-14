package alephinfinity1.forgeblock.init;

import alephinfinity1.forgeblock.ForgeBlock;
import alephinfinity1.forgeblock.client.particles.StringParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModParticles {

	public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, ForgeBlock.MOD_ID);
	
	public static final RegistryObject<ParticleType<StringParticleData>> NUMERIC_DAMAGE = PARTICLE_TYPES.register("numeric_damage", () -> new ParticleType<StringParticleData>(false, StringParticleData.DESERIALIZER));
}
