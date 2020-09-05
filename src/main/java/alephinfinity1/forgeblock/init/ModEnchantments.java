package alephinfinity1.forgeblock.init;

import alephinfinity1.forgeblock.ForgeBlock;
import alephinfinity1.forgeblock.enchantment.GrowthEnchantment;
import alephinfinity1.forgeblock.enchantment.TelekinesisEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEnchantments {

	public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, ForgeBlock.MOD_ID);
	public static final DeferredRegister<Enchantment> OVERRIDE = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, ForgeBlock.MINECRAFT_ID);
	
	public static final RegistryObject<Enchantment> GROWTH = ENCHANTMENTS.register("growth", () -> new GrowthEnchantment());
	public static final RegistryObject<Enchantment> TELEKINESIS = ENCHANTMENTS.register("telekinesis", () -> new TelekinesisEnchantment());

}
