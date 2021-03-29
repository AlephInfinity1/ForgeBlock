package alephinfinity1.forgeblock.command;

import alephinfinity1.forgeblock.misc.capability.accessories.AccessoriesProvider;
import alephinfinity1.forgeblock.misc.capability.accessories.IAccessoriesData;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.util.text.StringTextComponent;

public class AccCommand {
    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("acc").executes(commandContext -> run(commandContext.getSource())));
    }

    public static int run(CommandSource source) throws CommandSyntaxException {
        Entity entity = source.assertIsEntity();
        if (entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entity;
            IAccessoriesData accsData = player.getCapability(AccessoriesProvider.ACCESSORIES_CAPABILITY).orElse(null);
            if (accsData == null) {
                return 0;
            }
            INamedContainerProvider provider = new SimpleNamedContainerProvider((id, playerInv, p) -> new ChestContainer(ContainerType.GENERIC_9X5, id, playerInv, accsData, 5), new StringTextComponent(""));
            player.openContainer(provider);
            return 1;
        }
        return 0;
    }
}
