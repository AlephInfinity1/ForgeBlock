package alephinfinity1.forgeblock.command;

import java.util.Collection;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;

import alephinfinity1.forgeblock.command.argument.SkillArgument;
import alephinfinity1.forgeblock.misc.capability.skills.ISkills;
import alephinfinity1.forgeblock.misc.capability.skills.SkillType;
import alephinfinity1.forgeblock.misc.capability.skills.SkillsProvider;
import alephinfinity1.forgeblock.network.FBPacketHandler;
import alephinfinity1.forgeblock.network.SkillUpdatePacket;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.network.PacketDistributor;

public class SkillCommand {
	public static final SimpleCommandExceptionType NO_SKILL_CAP = new SimpleCommandExceptionType(new TranslationTextComponent("commands.forgeblock.skill.noSkillCap")); //Thrown if player does not have skill capacity.
	public static final SimpleCommandExceptionType INVALID_SKILL = new SimpleCommandExceptionType(new TranslationTextComponent("commands.forgeblock.skill.invalidSkillType"));
	public static final SimpleCommandExceptionType INVALID_LEVEL = new SimpleCommandExceptionType(new TranslationTextComponent("commands.forgeblock.skill.set.invalidLevel"));
	public static final SimpleCommandExceptionType INVALID_POINTS = new SimpleCommandExceptionType(new TranslationTextComponent("commands.forgeblock.skill.set.invalidPoints")); //Thrown if set points is larger than maximum points available for level

	public static void register(CommandDispatcher<CommandSource> dispatcher) {
		dispatcher.register(Commands.literal("skill").requires((commandSource) -> commandSource.hasPermissionLevel(2))
				.then(Commands.literal("add")
						.then(Commands.argument("targets", EntityArgument.players())
								.then(Commands.argument("skillType", SkillArgument.skill())
										.then(Commands.argument("amount", IntegerArgumentType.integer(0))
												.then(Commands.literal("levels")
														.executes((commandSource) -> addSkillExperience(commandSource.getSource(), EntityArgument.getPlayers(commandSource, "targets"), SkillArgument.getSkill(commandSource, "skillType"), IntegerArgumentType.getInteger(commandSource, "amount"), true)))
												.then(Commands.literal("points")
														.executes((commandSource) -> addSkillExperience(commandSource.getSource(), EntityArgument.getPlayers(commandSource, "targets"), SkillArgument.getSkill(commandSource, "skillType"), IntegerArgumentType.getInteger(commandSource, "amount"), false)))
												)
										)
								)
						)
				.then(Commands.literal("set")
						.then(Commands.argument("targets", EntityArgument.players())
								.then(Commands.argument("skillType", SkillArgument.skill())
										.then(Commands.argument("amount", IntegerArgumentType.integer(0))
												.then(Commands.literal("levels")
														.executes((commandSource) -> setSkillExperience(commandSource.getSource(), EntityArgument.getPlayers(commandSource, "targets"), SkillArgument.getSkill(commandSource, "skillType"), IntegerArgumentType.getInteger(commandSource, "amount"), true)))
												.then(Commands.literal("points")
														.executes((commandSource) -> setSkillExperience(commandSource.getSource(), EntityArgument.getPlayers(commandSource, "targets"), SkillArgument.getSkill(commandSource, "skillType"), IntegerArgumentType.getInteger(commandSource, "amount"), false)))
												)
										)
								)
						)
				.then(Commands.literal("query")
						.then(Commands.argument("targets", EntityArgument.player())
								.then(Commands.argument("skillType", SkillArgument.skill())
										.then(Commands.literal("levels")
												.executes((commandSource) -> querySkillExperience(commandSource.getSource(), EntityArgument.getPlayer(commandSource, "targets"), SkillArgument.getSkill(commandSource, "skillType"), true)))
										.then(Commands.literal("points")
												.executes((commandSource) -> querySkillExperience(commandSource.getSource(), EntityArgument.getPlayer(commandSource, "targets"), SkillArgument.getSkill(commandSource, "skillType"), false)))
										)
								)
						)
				);
	}
	
	@SuppressWarnings("deprecation")
	private static int addSkillExperience(CommandSource source, Collection<? extends ServerPlayerEntity> targets, SkillType skillType, int amount, boolean levels) throws CommandSyntaxException {
		
		int i = 0; //Success count
		if(levels) {
			for(ServerPlayerEntity player : targets) {
				ISkills skills = player.getCapability(SkillsProvider.SKILLS_CAPABILITY).orElseThrow(NO_SKILL_CAP::create);
				skills.setLevel(skillType, MathHelper.clamp(skills.getLevel(skillType) + amount, 0, skillType.getMaxLevel()));
				FBPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new SkillUpdatePacket(skills.getCompoundNBTFor(skillType), false));
				++i;
			}
		} else {
			for(ServerPlayerEntity player : targets) {
				ISkills skills = player.getCapability(SkillsProvider.SKILLS_CAPABILITY).orElseThrow(NO_SKILL_CAP::create);
				skills.addXP(skillType, amount); //Use of addXP accepted here, since event shouldn't be updated.
				FBPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new SkillUpdatePacket(skills.getCompoundNBTFor(skillType), false));
				++i;
			}
		}
		
		if(targets.size() == 1) {
			if(levels)
				source.sendFeedback(new TranslationTextComponent("commands.forgeblock.skill.add.levels.single", targets.iterator().next().getDisplayName(), amount, skillType.getDisplayName().getString()), true);
			else
				source.sendFeedback(new TranslationTextComponent("commands.forgeblock.skill.add.points.single", targets.iterator().next().getDisplayName(), amount, skillType.getDisplayName().getString()), true);
		} else {
			if(levels)
				source.sendFeedback(new TranslationTextComponent("commands.forgeblock.skill.add.levels.multi", targets.size(), amount, skillType.getDisplayName().getString()), true);
			else
				source.sendFeedback(new TranslationTextComponent("commands.forgeblock.skill.add.points.multi", targets.size(), amount, skillType.getDisplayName().getString()), true);
		}
		return i;
	}
	
	private static int setSkillExperience(CommandSource source, Collection<? extends ServerPlayerEntity> targets, SkillType skillType, int amount, boolean levels) throws CommandSyntaxException {
		
		int i = 0; //Success count
		if(levels) {
			if(amount < 0 || amount > skillType.getMaxLevel()) throw INVALID_LEVEL.create();
			for(ServerPlayerEntity player : targets) {
				ISkills skills = player.getCapability(SkillsProvider.SKILLS_CAPABILITY).orElseThrow(NO_SKILL_CAP::create);
				skills.setLevel(skillType, amount);
				FBPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new SkillUpdatePacket(skills.getCompoundNBTFor(skillType), false));
				++i;
			}
		} else {
			for(ServerPlayerEntity player : targets) {
				ISkills skills = player.getCapability(SkillsProvider.SKILLS_CAPABILITY).orElseThrow(NO_SKILL_CAP::create);
				if(amount > skills.getXPNeededToLevelUp(skillType) || amount < 0) throw INVALID_POINTS.create();
				skills.setProgress(skillType, amount);
				FBPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new SkillUpdatePacket(skills.getCompoundNBTFor(skillType), false));
				++i;
			}
		}
		
		if(targets.size() == 1) {
			if(levels)
				source.sendFeedback(new TranslationTextComponent("commands.forgeblock.skill.set.levels.single", targets.iterator().next().getDisplayName(), amount, skillType.getDisplayName().getString()), true);
			else
				source.sendFeedback(new TranslationTextComponent("commands.forgeblock.skill.set.points.single", targets.iterator().next().getDisplayName(), amount, skillType.getDisplayName().getString()), true);
		} else {
			if(levels)
				source.sendFeedback(new TranslationTextComponent("commands.forgeblock.skill.set.levels.multi", targets.size(), amount, skillType.getDisplayName().getString()), true);
			else
				source.sendFeedback(new TranslationTextComponent("commands.forgeblock.skill.set.points.multi", targets.size(), amount, skillType.getDisplayName().getString()), true);
		}
		return i;
	}
	
	private static int querySkillExperience(CommandSource source, ServerPlayerEntity target, SkillType skillType, boolean levels) throws CommandSyntaxException {
		
		ISkills skills = target.getCapability(SkillsProvider.SKILLS_CAPABILITY).orElseThrow(NO_SKILL_CAP::create);
		
		int i;
		if(levels) {
			i = skills.getLevel(skillType);
		} else {
			i = (int) skills.getAbsoluteProgress(skillType);
		}
		
		if(levels) source.sendFeedback(new TranslationTextComponent("commands.forgeblock.skill.query.levels", target.getDisplayName().getString(), i, skillType.getDisplayName().getString()), true);
		else source.sendFeedback(new TranslationTextComponent("commands.forgeblock.skill.query.points", target.getDisplayName().getString(), i, skillType.getDisplayName().getString()), true);
		
		return i;
	}
}
