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
	private Style style;
	
	public static final IParticleData.IDeserializer<StringParticleData> DESERIALIZER = new IParticleData.IDeserializer<StringParticleData>() {
		public StringParticleData deserialize(ParticleType<StringParticleData> particleTypeIn, StringReader reader) throws CommandSyntaxException {
			reader.expect(' ');
			String str = reader.readStringUntil(' ');
			reader.expect(' ');
			Style style = Style.parse(reader.readString());
			return new StringParticleData(particleTypeIn, str, style);
		}

		public StringParticleData read(ParticleType<StringParticleData> particleTypeIn, PacketBuffer buffer) {
			return new StringParticleData(particleTypeIn, buffer.readString(), Style.parse(buffer.readString()));
		}
	};
	
	public StringParticleData(ParticleType<StringParticleData> type, String str) {
		this.type = type;
		this.str = str;
		this.style = Style.NORMAL;
	}
	
	public StringParticleData(ParticleType<StringParticleData> type, String str, Style style) {
		this.type = type;
		this.str = str;
		this.style = style;
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
		return ForgeRegistries.PARTICLE_TYPES.getKey(this.type) + " " + this.str + " " + this.style.toString();
	}
	
	public String getString() {
		return this.str;
	}
	
	public Style getStyle() {
		return this.style;
	}
	
	public static enum Style {
		NORMAL,
		CRIT,
		FALL,
		DROWNING,
		FIRE,
		LAVA,
		TRUE;
		
		public static Style parse(String str) {
			if(str.equals("normal")) return NORMAL;
			else if(str.equals("crit")) return CRIT;
			else if(str.equals("fall")) return FALL;
			else if(str.equals("drown")) return DROWNING;
			else if(str.equals("inFire")) return FIRE;
			else if(str.equals("onFire")) return FIRE;
			else if(str.equals("lava")) return LAVA;
			else if(str.equals("true")) return TRUE;
			else return NORMAL;
		}
		
		@Override
		public String toString() {
			switch(this) {
			case NORMAL:
				return "normal";
			case CRIT:
				return "crit";
			case FALL:
				return "fall";
			case DROWNING:
				return "drown";
			case FIRE:
				return "inFire";
			case LAVA:
				return "lava";
			case TRUE:
				return "true";
			default:
				return "unknown";
			}
		}
	}

}
