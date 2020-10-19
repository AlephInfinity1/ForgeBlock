package alephinfinity1.forgeblock.command;

import java.util.Arrays;
import java.util.Collection;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;

import alephinfinity1.forgeblock.misc.reforge.IReforgeableItem;
import alephinfinity1.forgeblock.misc.reforge.Reforge;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TranslationTextComponent;

public class ReforgeCommand {
	
	private static final SimpleCommandExceptionType INVALID_REFORGE = new SimpleCommandExceptionType(new TranslationTextComponent("commands.forgeblock.reforge.invalidReforge"));
	private static final DynamicCommandExceptionType UNREFORGEABLE_ITEM = new DynamicCommandExceptionType((stack) -> {
		return new TranslationTextComponent("commands.forgeblock.reforge.nonreforgeable", ((ItemStack) stack).getDisplayName());
	});
	
	public static void register(CommandDispatcher<CommandSource> dispatcher) {
		dispatcher.register(Commands.literal("reforge").requires((commander) -> {
			return commander.hasPermissionLevel(2);
		}).executes((commandSource) -> {
			return reforgeCommand(commandSource.getSource(), Arrays.asList(commandSource.getSource().getEntity()), commandSource.getSource().asPlayer().getHeldItemMainhand());
		}).then(Commands.argument("reforge", StringArgumentType.string()).executes((commandSource2) -> {
			return reforgeCommand(commandSource2.getSource(), Arrays.asList(commandSource2.getSource().getEntity()), commandSource2.getSource().asPlayer().getHeldItemMainhand(), StringArgumentType.getString(commandSource2, "reforge"));
		})));
	}
	
	public static int reforgeCommand(CommandSource source, Collection<? extends Entity> targets, ItemStack stack) throws CommandSyntaxException {
		if(!(stack.getItem() instanceof IReforgeableItem)) {
			throw UNREFORGEABLE_ITEM.create(stack);
		}
		((IReforgeableItem) stack.getItem()).setReforge(Reforge.getRandomReforge(stack), stack);
		source.sendFeedback(new TranslationTextComponent("commands.forgeblock.reforge.randomSuccess", source.getEntity().getDisplayName(), stack.getDisplayName()), true);
		return 1;
	}
	
	public static int reforgeCommand(CommandSource source, Collection<? extends Entity> targets, ItemStack stack, String reforgeName) throws CommandSyntaxException {
		if(!(stack.getItem() instanceof IReforgeableItem)) {
			throw UNREFORGEABLE_ITEM.create(stack);
		}
		Reforge reforge = null;
		try {
			reforge = Reforge.findReforgeByID(reforgeName);
		} catch(IllegalArgumentException e) {
			throw INVALID_REFORGE.create();
		}
		
		((IReforgeableItem) stack.getItem()).setReforge(reforge, stack);
		source.sendFeedback(new TranslationTextComponent("commands.forgeblock.reforge.success", source.getEntity().getDisplayName(), stack.getDisplayName(), reforge.getDisplayName()), true);
		return 1;
	}

}
