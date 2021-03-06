package alephinfinity1.forgeblock.command;

import alephinfinity1.forgeblock.misc.capability.coins.CoinsProvider;
import alephinfinity1.forgeblock.misc.capability.coins.ICoins;
import alephinfinity1.forgeblock.network.CoinsUpdatePacket;
import alephinfinity1.forgeblock.network.FBPacketHandler;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.network.PacketDistributor;

import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Objects;

public class CoinsCommand {
    public static final SimpleCommandExceptionType NO_COINS_CAP = new SimpleCommandExceptionType(new TranslationTextComponent("commands.forgeblock.coins.noCoinsCap")); //Thrown if player does not have coins capacity.

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("coins").requires(commandSource -> commandSource.hasPermissionLevel(2))
                .then(Commands.literal("add")
                        .then(Commands.argument("targets", EntityArgument.players())
                                .then(Commands.argument("amount", IntegerArgumentType.integer(0))
                                        .executes((commandSource) -> addCoins(commandSource.getSource(), EntityArgument.getPlayers(commandSource, "targets"), IntegerArgumentType.getInteger(commandSource, "amount")))
                                )
                        )
                )
                .then(Commands.literal("set")
                        .then(Commands.argument("targets", EntityArgument.players())
                                .then(Commands.argument("amount", IntegerArgumentType.integer(0))
                                        .executes((commandSource) -> setCoins(commandSource.getSource(), EntityArgument.getPlayers(commandSource, "targets"), IntegerArgumentType.getInteger(commandSource, "amount")))
                                )
                        )
                )
                .then(Commands.literal("query")
                        .then(Commands.argument("targets", EntityArgument.player())
                                .executes((commandSource) -> queryCoins(commandSource.getSource(), EntityArgument.getPlayer(commandSource, "targets")))
                        )
                )
        );
    }

    private static int addCoins(CommandSource source, Collection<ServerPlayerEntity> targets, int amount) {
        int success = 0; //Success count
        for (ServerPlayerEntity player : targets) {
            ICoins coins = player.getCapability(CoinsProvider.COINS_CAPABILITY).orElse(null);
            if (Objects.isNull(coins)) {
                continue;
            }
            coins.add(amount);
            FBPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new CoinsUpdatePacket(coins.getCoins()));
            success++;
        }

        if(targets.size() == 1) {
            source.sendFeedback(new TranslationTextComponent("commands.forgeblock.coins.add.single", targets.iterator().next().getDisplayName(), amount), true);
        } else {
            source.sendFeedback(new TranslationTextComponent("commands.forgeblock.coins.add.multi", targets.size(), amount), true);
        }
        return success;
    }

    private static int setCoins(CommandSource source, Collection<ServerPlayerEntity> targets, int amount) {
        int success = 0; //Success count
        for (ServerPlayerEntity player : targets) {
            ICoins coins = player.getCapability(CoinsProvider.COINS_CAPABILITY).orElse(null);
            if (Objects.isNull(coins)) {
                continue;
            }
            coins.set(amount);
            FBPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new CoinsUpdatePacket(coins.getCoins()));
            success++;
        }

        if(targets.size() == 1) {
            source.sendFeedback(new TranslationTextComponent("commands.forgeblock.coins.set.single", targets.iterator().next().getDisplayName(), amount), true);
        } else {
            source.sendFeedback(new TranslationTextComponent("commands.forgeblock.coins.set.multi", targets.size(), new DecimalFormat("#.#").format(amount)), true);
        }
        return success;
    }

    private static int queryCoins(CommandSource source, ServerPlayerEntity target) throws CommandSyntaxException {
        ICoins coins = target.getCapability(CoinsProvider.COINS_CAPABILITY).orElseThrow(NO_COINS_CAP::create);
        double amount = coins.getCoins();
        source.sendFeedback(new TranslationTextComponent("commands.forgeblock.coins.query", target.getDisplayName().getString(), amount), true);
        return (int) amount;
    }
}
