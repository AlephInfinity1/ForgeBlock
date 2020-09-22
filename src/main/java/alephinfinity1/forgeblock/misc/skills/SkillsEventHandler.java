package alephinfinity1.forgeblock.misc.skills;

import java.text.DecimalFormat;

import alephinfinity1.forgeblock.network.FBPacketHandler;
import alephinfinity1.forgeblock.network.SkillUpdatePacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
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
		FBPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new SkillUpdatePacket(skills.getCompoundNBTFor(SkillType.COMBAT)));
		player.sendMessage(new StringTextComponent(Integer.toString(skills.getLevel(SkillType.ALCHEMY))));
	}
	
	/*
	 * Debug only for now. Will be replaced with actual code.
	 */
	//@SubscribeEvent
	public static void onEntityDeath(LivingDeathEvent event) {
		
		final DecimalFormat format = new DecimalFormat(",###.#");
		
		if(event.getSource().getDamageType().equals("player")) {
			Entity e = event.getSource().getTrueSource();
			if(!(e instanceof PlayerEntity)) return;
			PlayerEntity player = (PlayerEntity) e;
			ISkills skills = player.getCapability(SkillsProvider.SKILLS_CAPABILITY).orElseThrow(NullPointerException::new);
			skills.addXP(SkillType.COMBAT, 1000000.0D);
			player.sendMessage(new StringTextComponent("Killed an entity!"));
			player.sendMessage(new StringTextComponent("+1M Combat XP (" + format.format(skills.getAbsoluteProgress(SkillType.COMBAT)) + "/" + format.format(skills.getXPNeededToLevelUp(SkillType.COMBAT)) + ")"));
			player.sendMessage(new StringTextComponent("Combat Level: " + Integer.toString(skills.getLevel(SkillType.COMBAT))));
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
}
