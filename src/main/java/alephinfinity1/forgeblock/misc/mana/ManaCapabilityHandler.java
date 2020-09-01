package alephinfinity1.forgeblock.misc.mana;

import alephinfinity1.forgeblock.ForgeBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ManaCapabilityHandler {

	public static final ResourceLocation MANA_CAPABILITY = new ResourceLocation(ForgeBlock.MOD_ID, "mana");
	
	@SubscribeEvent
	public static void onCapabilityAttach(AttachCapabilitiesEvent<Entity> event) {
		if(event.getObject() instanceof PlayerEntity) {
			event.addCapability(MANA_CAPABILITY, new ManaProvider());
		}
	}

}
