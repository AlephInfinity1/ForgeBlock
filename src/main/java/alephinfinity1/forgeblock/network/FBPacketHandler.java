package alephinfinity1.forgeblock.network;

import alephinfinity1.forgeblock.ForgeBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class FBPacketHandler {
	private static final String PROTOCOL_VERSION = "1";
	public static SimpleChannel INSTANCE;
	
	private static int id = 0;
	
	private static int id() {
		return id++;
	}
	
	public static void register() {
		INSTANCE = NetworkRegistry.newSimpleChannel(
			new ResourceLocation(ForgeBlock.MOD_ID, "main"),
		    () -> PROTOCOL_VERSION,
		    PROTOCOL_VERSION::equals,
		    PROTOCOL_VERSION::equals
		);
		
		INSTANCE.registerMessage(id(), ManaUpdatePacket.class, ManaUpdatePacket::encode, ManaUpdatePacket::new, ManaUpdatePacket::handle);
		
	}
}
