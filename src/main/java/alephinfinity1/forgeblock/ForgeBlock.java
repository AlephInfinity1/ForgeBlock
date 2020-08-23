package alephinfinity1.forgeblock;

import alephinfinity1.forgeblock.attribute.AttributeHelper;
import alephinfinity1.forgeblock.init.ModItems;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(value = ForgeBlock.MOD_ID)
public class ForgeBlock {

	public static final String MOD_ID = "forgeblock";
	
	public ForgeBlock() {
		ModItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::removeLimits);
	}
	
	private void removeLimits(FMLCommonSetupEvent event) {
		AttributeHelper.removeLimits();
	}
	
}
