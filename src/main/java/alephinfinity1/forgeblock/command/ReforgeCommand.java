package alephinfinity1.forgeblock.command;

import java.util.Arrays;
import java.util.Collection;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;

import alephinfinity1.forgeblock.misc.reforge.IReforgeableItem;
import alephinfinity1.forgeblock.misc.reforge.Reforge;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

public class ReforgeCommand {
	
	public static void register(CommandDispatcher<CommandSource> dispatcher) {
		dispatcher.register(Commands.literal("reforge").requires((commander) -> {
			return commander.hasPermissionLevel(2);
		}).executes((commandSource) -> {
			return reforgeCommand(commandSource.getSource(), Arrays.asList(commandSource.getSource().getEntity()), commandSource.getSource().asPlayer().getHeldItemMainhand());
		}).then(Commands.argument("reforge", StringArgumentType.string())).executes((commandSource2) -> {
			return reforgeCommand(commandSource2.getSource(), Arrays.asList(commandSource2.getSource().getEntity()), commandSource2.getSource().asPlayer().getHeldItemMainhand(), StringArgumentType.getString(commandSource2, "reforge"));
		}));
	}
	
	public static int reforgeCommand(CommandSource source, Collection<? extends Entity> targets, ItemStack stack) {
		((IReforgeableItem) stack.getItem()).setReforge(Reforge.getRandomReforge(stack), stack);
		return 1;
	}
	
	public static int reforgeCommand(CommandSource source, Collection<? extends Entity> targets, ItemStack stack, String reforgeName) {
		((IReforgeableItem) stack.getItem()).setReforge(Reforge.findReforgeByID(reforgeName), stack);
		return 1;
	}

}
