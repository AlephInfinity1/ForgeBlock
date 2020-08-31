package alephinfinity1.forgeblock.init;

import alephinfinity1.forgeblock.ForgeBlock;
import net.minecraft.potion.Potion;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModPotions {

	public static final DeferredRegister<Potion> POTION_TYPES = DeferredRegister.create(ForgeRegistries.POTION_TYPES, ForgeBlock.MOD_ID);
	public static final DeferredRegister<Potion> OVERRIDE = DeferredRegister.create(ForgeRegistries.POTION_TYPES, ForgeBlock.MINECRAFT_ID);

}
