package alephinfinity1.forgeblock.command.argument;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;

import alephinfinity1.forgeblock.misc.capability.skills.SkillType;
import net.minecraft.command.CommandSource;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.util.text.TranslationTextComponent;

public class SkillArgument implements ArgumentType<SkillType> {
	
	public static final DynamicCommandExceptionType SKILL_UNKNOWN = new DynamicCommandExceptionType((p_208662_0_) -> new TranslationTextComponent("skill.unknown", p_208662_0_));
	
	public static SkillArgument skill() {
		return new SkillArgument();
	}
	
	public static SkillType getSkill(CommandContext<CommandSource> context, String name) {
		return context.getArgument(name, SkillType.class);
	}

	@Override
	public SkillType parse(StringReader reader) throws CommandSyntaxException {
		String str = reader.readUnquotedString();
		for(SkillType type : SkillType.values()) {
			if(str.equals(type.getID())) return type;
		}
		throw SKILL_UNKNOWN.create(str);
	}
	
	@Override
	public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> p_listSuggestions_1_, SuggestionsBuilder p_listSuggestions_2_) {
		return ISuggestionProvider.suggest(
				Arrays.asList(SkillType.values()).stream().map(SkillType::getID).collect(Collectors.toList()), 
				p_listSuggestions_2_);
	}

}
