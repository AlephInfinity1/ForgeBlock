package alephinfinity1.forgeblock.init;

import alephinfinity1.forgeblock.ForgeBlock;
import alephinfinity1.forgeblock.container.FBRepairContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainerTypes {
	
	public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, ForgeBlock.MOD_ID);
	
	public static final RegistryObject<ContainerType<FBRepairContainer>> FB_ANVIL = CONTAINERS.register("anvil", () -> new ContainerType<FBRepairContainer>(FBRepairContainer::new));

}
