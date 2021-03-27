package alephinfinity1.forgeblock.mixin;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Inventory.class)
public interface AccessorInventory {
    @Accessor
    NonNullList<ItemStack> getInventoryContents();
}
