package alephinfinity1.forgeblock.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.util.FoodStats;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(PlayerEntity.class)
public interface AccessorPlayerEntity {
    @Accessor
    FoodStats getFoodStats();

    @Accessor
    void setFoodStats(FoodStats fs);

    @Accessor
    PlayerContainer getContainer();

    @Accessor
    void setContainer(PlayerContainer pc);
}
