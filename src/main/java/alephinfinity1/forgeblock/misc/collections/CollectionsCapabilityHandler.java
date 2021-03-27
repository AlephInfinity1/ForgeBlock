package alephinfinity1.forgeblock.misc.collections;

import alephinfinity1.forgeblock.ForgeBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class CollectionsCapabilityHandler {
	
	public static final ResourceLocation COLLECTIONS_CAPABILITY = new ResourceLocation(ForgeBlock.MOD_ID, "collections");
	
	//@SubscribeEvent
	public static void onCapabilityAttach(AttachCapabilitiesEvent<Entity> event) {
		if(event.getObject() instanceof PlayerEntity) {
			event.addCapability(COLLECTIONS_CAPABILITY, new CollectionsDataProvider());
		}
	}

}
