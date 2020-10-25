package alephinfinity1.forgeblock.command;

import java.util.Arrays;
import java.util.Collection;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;

import alephinfinity1.forgeblock.command.argument.ReforgeArgument;
import alephinfinity1.forgeblock.misc.reforge.IReforgeableItem;
import alephinfinity1.forgeblock.misc.reforge.Reforge;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TranslationTextComponent;

public class ReforgeCommand {
	
	//private static final SimpleCommandExceptionType INVALID_REFORGE = new SimpleCommandExceptionType(new TranslationTextComponent("commands.forgeblock.reforge.invalidReforge"));
	private static final DynamicCommandExceptionType UNREFORGEABLE_ITEM = new DynamicCommandExceptionType((stack) -> {
		return new TranslationTextComponent("commands.forgeblock.reforge.nonreforgeable", ((ItemStack) stack).getDisplayName());
	});
	
	public static void register(CommandDispatcher<CommandSource> dispatcher) {
		dispatcher.register(Commands.literal("reforge").requires((commander) -> {
			return commander.hasPermissionLevel(2);
		}).executes((commandSource) -> {
			return reforgeCommand(commandSource.getSource(), Arrays.asList(commandSource.getSource().getEntity()));
		}).then(Commands.argument("targets", EntityArgument.entities())
				.executes((commandSource) -> {
					return reforgeCommand(commandSource.getSource(), EntityArgument.getEntities(commandSource, "targets"));
				})
				.then(Commands.argument("reforge", ReforgeArgument.reforge())
						.executes((commandSource) -> {
							return reforgeCommand(commandSource.getSource(), EntityArgument.getEntities(commandSource, "targets"), ReforgeArgument.getReforge(commandSource, "reforge"));
						}))
				)
		);
	}
	
	public static int reforgeCommand(CommandSource source, Collection<? extends Entity> targets) throws CommandSyntaxException {
		
		int i = 0; //Success count
		
		for(Entity entity : targets) {
			if(entity instanceof LivingEntity) {
				ItemStack stack = ((LivingEntity) entity).getHeldItemMainhand();
				if(stack.getItem() instanceof IReforgeableItem) {
					((IReforgeableItem) stack.getItem()).setReforge(Reforge.getRandomReforge(stack), stack);
					i++;
				} else {
					throw UNREFORGEABLE_ITEM.create(stack);
				}
			}
		}
		
		return i;
	}
	
	public static int reforgeCommand(CommandSource source, Collection<? extends Entity> targets, Reforge reforge) throws CommandSyntaxException {
		
		int i = 0; //Success count
		
		for(Entity entity : targets) {
			if(entity instanceof LivingEntity) {
				ItemStack stack = ((LivingEntity) entity).getHeldItemMainhand();
				if(stack.getItem() instanceof IReforgeableItem) {
					((IReforgeableItem) stack.getItem()).setReforge(reforge, stack);
					i++;
				} else {
					throw UNREFORGEABLE_ITEM.create(stack);
				}
			}
		}
		
		return i;
	}

}
