package alephinfinity1.forgeblock.client.particles;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.registries.ForgeRegistries;

public class StringParticleData implements IParticleData {
	
	private ParticleType<StringParticleData> type;
	private String str;
	
	public static final IParticleData.IDeserializer<StringParticleData> DESERIALIZER = new IParticleData.IDeserializer<StringParticleData>() {
		public StringParticleData deserialize(ParticleType<StringParticleData> particleTypeIn, StringReader reader) throws CommandSyntaxException {
			reader.expect(' ');
			return new StringParticleData(particleTypeIn, reader.readString());
		}

		public StringParticleData read(ParticleType<StringParticleData> particleTypeIn, PacketBuffer buffer) {
			return new StringParticleData(particleTypeIn, buffer.readString());
		}
	};
	
	public StringParticleData(ParticleType<StringParticleData> type, String str) {
		this.type = type;
		this.str = str;
	}

	@Override
	public ParticleType<?> getType() {
		return this.type;
	}

	@Override
	public void write(PacketBuffer buffer) {
		buffer.writeString(this.str);
	}

	@Override
	public String getParameters() {
		return ForgeRegistries.PARTICLE_TYPES.getKey(this.type) + " " + this.str;
	}
	
	public String getString() {
		return this.str;
	}

}
