package alephinfinity1.forgeblock.misc.skills;

import java.text.DecimalFormat;

import alephinfinity1.forgeblock.entity.IFBEntity;
import alephinfinity1.forgeblock.network.FBPacketHandler;
import alephinfinity1.forgeblock.network.SkillUpdatePacket;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.PacketDistributor;

@Mod.EventBusSubscriber
public class SkillsEventHandler {

	@SubscribeEvent
	public static void onPlayerLoggedIn(PlayerLoggedInEvent event) {
		PlayerEntity player = event.getPlayer();
		ISkills skills = player.getCapability(SkillsProvider.SKILLS_CAPABILITY).orElseThrow(NullPointerException::new);
		for(SkillType type : SkillType.values())
			FBPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new SkillUpdatePacket(skills.getCompoundNBTFor(type)));
	}
	
	/*
	 * Debug only for now. Will be replaced with actual code.
	 */
	@SubscribeEvent
	public static void onEntityDeath(LivingDeathEvent event) {
		
		//final DecimalFormat format = new DecimalFormat(",###.#");
		
		if(event.getSource().getDamageType().equals("player")) {
			Entity e = event.getSource().getTrueSource();
			if(!(e instanceof PlayerEntity)) return;
			PlayerEntity player = (PlayerEntity) e;
			ISkills skills = player.getCapability(SkillsProvider.SKILLS_CAPABILITY).orElseThrow(NullPointerException::new);
			if(event.getEntity() instanceof IFBEntity)
				skills.addXP(SkillType.COMBAT, ((IFBEntity) event.getEntity()).getCombatXP());
			
			/*
			player.sendMessage(new StringTextComponent("Killed an entity!"));
			player.sendMessage(new StringTextComponent("+1M Combat XP (" + format.format(skills.getAbsoluteProgress(SkillType.COMBAT)) + "/" + format.format(skills.getXPNeededToLevelUp(SkillType.COMBAT)) + ")"));
			player.sendMessage(new StringTextComponent("Combat Level: " + Integer.toString(skills.getLevel(SkillType.COMBAT))));
			*/
			FBPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new SkillUpdatePacket(skills.getCompoundNBTFor(SkillType.COMBAT)));
			//player.sendMessage(new StringTextComponent("Client data: " + Integer.toString(Minecraft.getInstance().player.getCapability(SkillsProvider.SKILLS_CAPABILITY).orElseThrow(NullPointerException::new).getLevel(SkillType.COMBAT)) + " " + Double.toString(Minecraft.getInstance().player.getCapability(SkillsProvider.SKILLS_CAPABILITY).orElseThrow(NullPointerException::new).getAbsoluteProgress(SkillType.COMBAT))));
		}
	}
	
	@SubscribeEvent
	public static void onPlayerTick(PlayerTickEvent event) {
		if(event.phase == Phase.END && !event.player.getEntityWorld().isRemote) {
			PlayerEntity player = event.player;
			ISkills skills = player.getCapability(SkillsProvider.SKILLS_CAPABILITY).orElseThrow(NullPointerException::new);
			player.getAttributes().applyAttributeModifiers(skills.getAttributeModifiers());
		}
	}
	
	public static void notifyPlayer(ClientPlayerEntity player, SkillType type, ISkills old, ISkills neu) {
		double xpDiff = calculateXPDiff(type, old, neu);
		if(MathHelper.epsilonEquals(xpDiff, 0.0D)) return;
		StringBuffer buf = new StringBuffer();
		buf.append(TextFormatting.DARK_AQUA.toString());
		buf.append("+");
		buf.append(new DecimalFormat(",###.#").format(xpDiff));
		buf.append(" ");
		buf.append(type.getDisplayName().getString());
		buf.append(" (");
		buf.append(new DecimalFormat(",###.#").format(neu.getAbsoluteProgress(type)));
		buf.append("/");
		buf.append(new DecimalFormat(",###.#").format(neu.getXPNeededToLevelUp(type)));
		buf.append(")");
		ITextComponent comp = new StringTextComponent(buf.toString().replaceAll("\u00A0", ","));
		player.sendStatusMessage(comp, true);
		if(old.getLevel(type) < neu.getLevel(type)) {
			notifyPlayerLevelUp(player, type, old.getLevel(type), neu.getLevel(type));
		}
	}
	
	public static void notifyPlayerLevelUp(ClientPlayerEntity player, SkillType type, int levelOld, int levelNew) {
		ITextComponent border = new StringTextComponent("\u00A73--------------------------------");
		ITextComponent line1 = new TranslationTextComponent("skills.forgeblock.levelUpMessage_1", type.getDisplayName().getString(), levelOld, levelNew);
		ITextComponent line2 = new TranslationTextComponent("skills.forgeblock.levelUpMessage_2");
		ITextComponent line3 = new TranslationTextComponent("skills.forgeblock.levelUpMessage_3");
		
		player.sendMessage(border);
		player.sendMessage(line1);
		player.sendMessage(line2);
		player.sendMessage(line3);
		player.sendMessage(border);
		player.playSound(SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.PLAYERS, 1.0f, 1.0f);
	}
	
	public static double calculateXPDiff(SkillType type, ISkills old, ISkills neu) {
		double oldValue = 0;
		for(int i = 0; i < old.getLevel(type); i++) {
			oldValue += type.getXPForLevel(i);
		}
		oldValue += old.getAbsoluteProgress(type);
		
		double newValue = 0;
		for(int i = 0; i < neu.getLevel(type); i++) {
			newValue += type.getXPForLevel(i);
		}
		newValue += neu.getAbsoluteProgress(type);
		
		return newValue - oldValue;
	}
	
	public static void updateAllSkills(PlayerEntity player, ISkills skills, boolean notifyPlayer) {
		for(SkillType type : SkillType.values()) {
			FBPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new SkillUpdatePacket(skills.getCompoundNBTFor(type), notifyPlayer));
		}
	}
	
	@SubscribeEvent
	public static void onPlayerLogin(PlayerLoggedInEvent event) {
		updateAllSkills(event.getPlayer(), event.getPlayer().getCapability(SkillsProvider.SKILLS_CAPABILITY).orElseThrow(NullPointerException::new), false);
	}
}
