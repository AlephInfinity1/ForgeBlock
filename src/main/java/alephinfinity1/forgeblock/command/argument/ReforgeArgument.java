package alephinfinity1.forgeblock.command.argument;

import java.util.concurrent.CompletableFuture;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;

import alephinfinity1.forgeblock.init.ModRegistries;
import alephinfinity1.forgeblock.misc.reforge.Reforge;
import net.minecraft.command.CommandSource;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

public class ReforgeArgument implements ArgumentType<Reforge> {

	public static final DynamicCommandExceptionType REFORGE_UNKNOWN = new DynamicCommandExceptionType((p_208662_0_) -> {
		return new TranslationTextComponent("reforge.unknown", p_208662_0_);
	});
	
	public static ReforgeArgument reforge() {
		return new ReforgeArgument();
	}
	
	public static Reforge getReforge(CommandContext<CommandSource> context, String name) {
		return context.getArgument(name, Reforge.class);
	}

	@Override
	public Reforge parse(StringReader reader) throws CommandSyntaxException {
		ResourceLocation rl = ResourceLocation.read(reader);
		if(ModRegistries.REFORGE.getValue(rl) == null) {
			throw REFORGE_UNKNOWN.create(rl);
		} else {
			return ModRegistries.REFORGE.getValue(rl);
		}
	}

	@Override
	public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> p_listSuggestions_1_, SuggestionsBuilder p_listSuggestions_2_) {
		return ISuggestionProvider.suggestIterable(ModRegistries.REFORGE.getKeys(), p_listSuggestions_2_);
	}

}
