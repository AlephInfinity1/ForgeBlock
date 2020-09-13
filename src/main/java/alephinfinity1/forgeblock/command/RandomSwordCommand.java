package alephinfinity1.forgeblock.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import alephinfinity1.forgeblock.item.CustomSwordItem;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

public class RandomSwordCommand {

	public static void register(CommandDispatcher<CommandSource> dispatcher) {
		dispatcher.register(Commands.literal("randomsword").executes((commandSource) -> {
			return randomSwordCommand(commandSource.getSource());
		}).then(Commands.argument("count", IntegerArgumentType.integer(1)).executes((commandSource) -> {
			return randomSwordCommand(commandSource.getSource(), IntegerArgumentType.getInteger(commandSource, "count"));
		}).then(Commands.argument("quality", IntegerArgumentType.integer()).executes((commandSource) -> {
			return randomSwordCommand(commandSource.getSource(), IntegerArgumentType.getInteger(commandSource, "count"), IntegerArgumentType.getInteger(commandSource, "quality"));
		}))));
	}
	
	public static int randomSwordCommand(CommandSource source) throws CommandSyntaxException {
		Entity entity = source.assertIsEntity();
		if(entity instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) entity;
			player.addItemStackToInventory(CustomSwordItem.randomSword());
			return 1;
		}
		return 0;
	}
	
	public static int randomSwordCommand(CommandSource source, int count) throws CommandSyntaxException {
		Entity entity = source.assertIsEntity();
		if(entity instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) entity;
			for(int i = 0; i < count; i++)
				player.addItemStackToInventory(CustomSwordItem.randomSword());
			return count;
		}
		return 0;
	}
	
	public static int randomSwordCommand(CommandSource source, int count, int quality) throws CommandSyntaxException {
		Entity entity = source.assertIsEntity();
		if(entity instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) entity;
			for(int i = 0; i < count; i++)
				player.addItemStackToInventory(CustomSwordItem.randomSword(quality));
			return count;
		}
		return 0;
	}
}
