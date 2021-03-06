package alephinfinity1.forgeblock.misc.capability.skills;

import java.text.DecimalFormat;
import java.util.Objects;
import java.util.UUID;

import alephinfinity1.forgeblock.attribute.FBAttributes;
import alephinfinity1.forgeblock.entity.IFBEntity;
import alephinfinity1.forgeblock.misc.TextFormatHelper;
import alephinfinity1.forgeblock.misc.capability.coins.CoinsProvider;
import alephinfinity1.forgeblock.misc.capability.coins.ICoins;
import alephinfinity1.forgeblock.misc.event.FBEventHooks;
import alephinfinity1.forgeblock.misc.event.SkillLevelUpEvent;
import alephinfinity1.forgeblock.misc.event.SkillXPGainEvent;
import alephinfinity1.forgeblock.network.CoinsUpdatePacket;
import alephinfinity1.forgeblock.network.FBPacketHandler;
import alephinfinity1.forgeblock.network.SkillUpdatePacket;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.EnchantmentContainer;
import net.minecraft.inventory.container.RepairContainer;
import net.minecraft.util.EntityDamageSource;
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
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.PacketDistributor;

@Mod.EventBusSubscriber
public class SkillsEventHandler {

	@SubscribeEvent
	public static void onPlayerLoggedIn(PlayerLoggedInEvent event) {
		if (event.getPlayer() instanceof ClientPlayerEntity) return;
		SkillsHelper.updateAllSkills((ServerPlayerEntity) event.getPlayer(), false);
	}
	
	/*
	 * Debug only for now. Will be replaced with actual code.
	 */
	@SubscribeEvent
	public static void onEntityDeath(LivingDeathEvent event) {
		
		//final DecimalFormat format = new DecimalFormat(",###.#");
		
		if(event.getSource() instanceof EntityDamageSource && event.getSource() != null) {
			if(((EntityDamageSource) event.getSource()).getTrueSource() instanceof PlayerEntity) {
				Entity e = event.getSource().getTrueSource();
				if(!(e instanceof PlayerEntity)) return;
				PlayerEntity player = (PlayerEntity) e;
				ISkills skills = player.getCapability(SkillsProvider.SKILLS_CAPABILITY).orElse(null);
				if(Objects.isNull(skills)) return;
				if(event.getEntity() instanceof IFBEntity)
					SkillsHelper.addXP(player, SkillType.COMBAT, 
							FBEventHooks.onPlayerSkillXPGain(player, SkillType.COMBAT, ((IFBEntity) event.getEntity()).getCombatXP()));
				
				FBPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new SkillUpdatePacket(skills.getCompoundNBTFor(SkillType.COMBAT)));
			}
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
	
	public static void notifyPlayer(ClientPlayerEntity player, SkillType type, ISkills old, ISkills _new) {
		double xpDiff = calculateXPDiff(type, old, _new);
		if(MathHelper.epsilonEquals(xpDiff, 0.0D)) return;
		StringBuffer buf = new StringBuffer();
		buf.append(TextFormatting.DARK_AQUA.toString());
		if(Math.signum(xpDiff) > 0)
			buf.append("+");
		buf.append(new DecimalFormat(",###.#").format(xpDiff));
		buf.append(" ");
		buf.append(type.getDisplayName().getString());
		buf.append(" (");
		buf.append(new DecimalFormat(",###.#").format(_new.getAbsoluteProgress(type)));
		buf.append("/");
		buf.append(new DecimalFormat(",###.#").format(_new.getXPNeededToLevelUp(type)));
		buf.append(")");
		ITextComponent comp = new StringTextComponent(buf.toString().replaceAll("\u00A0", ","));
		player.sendStatusMessage(comp, true);
		if(old.getLevel(type) < _new.getLevel(type)) {
			notifyPlayerLevelUp(player, type, old.getLevel(type), _new.getLevel(type));
		}
	}
	
	public static void notifyPlayerLevelUp(ClientPlayerEntity cplayer, SkillType type, int levelOld, int levelNew) {
		final UUID DUMMY_UUID = UUID.fromString("0afce2d8-e6e2-475f-b628-4704bdb7331c"); //Used for attribute modifier, to pass to TextFormatHelper#formatSpecialModifier()
		final String INDENT = "    "; //4 spaces here, nothing special
		
		ITextComponent border = new StringTextComponent("\u00A73--------------------------------");
		ITextComponent line1 = new TranslationTextComponent("skills.forgeblock.levelUpMessage_1", type.getDisplayName().getString(), levelOld, levelNew);
		ITextComponent line2 = new TranslationTextComponent("skills.forgeblock.levelUpMessage_2");
		
		ITextComponent line3 = new StringTextComponent(INDENT).appendSibling(type.getAbilityName(levelNew));
		
		double coinsReward = 0.0D;
		for (int i = levelOld + 1; i <= levelNew; i++) {
			coinsReward += type.getCoinsRewardUponReachingLevel(i);
		}
		
		ITextComponent line5 = new TranslationTextComponent("skills.forgeblock.levelUpMessageCoins", new DecimalFormat(",###.#").format(coinsReward));
		
		double modifierAmount = type.getAttributeModifierAmount(levelNew) - type.getAttributeModifierAmount(levelOld);
		
		ITextComponent line4 = new StringTextComponent(INDENT).appendSibling(TextFormatHelper.formatSpecialModifier(type.getAttribute().getName(), new AttributeModifier(DUMMY_UUID, "Dummy string", modifierAmount, type.getOperation())));
		
		cplayer.sendMessage(border);
		cplayer.sendMessage(line1);
		cplayer.sendMessage(line2);
		cplayer.sendMessage(line3);
		cplayer.sendMessage(line4);
		cplayer.sendMessage(line5);
		cplayer.sendMessage(border);
		cplayer.playSound(SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.PLAYERS, 1.0f, 1.0f);
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
	
	@SubscribeEvent
	public static void onPlayerGainXP(SkillXPGainEvent event) {
		PlayerEntity player = event.getPlayer();
		double farmingBoost = player.getAttribute(FBAttributes.FARMING_XP_BOOST).getValue();
		double miningBoost = player.getAttribute(FBAttributes.MINING_XP_BOOST).getValue();
		double combatBoost = player.getAttribute(FBAttributes.COMBAT_XP_BOOST).getValue();
		double foragingBoost = player.getAttribute(FBAttributes.FORAGING_XP_BOOST).getValue();
		double fishingBoost = player.getAttribute(FBAttributes.FISHING_XP_BOOST).getValue();
		double enchantingBoost = player.getAttribute(FBAttributes.ENCHANTING_XP_BOOST).getValue();
		double alchemyBoost = player.getAttribute(FBAttributes.ALCHEMY_XP_BOOST).getValue();
		double tamingBoost = player.getAttribute(FBAttributes.TAMING_XP_BOOST).getValue();
		double allBoost = player.getAttribute(FBAttributes.ALL_SKILLS_XP_BOOST).getValue();
		
		double multiplier = 100.0D;
		
		switch(event.getType()) {
		case FARMING:
			multiplier += (farmingBoost + allBoost);
			break;
		case MINING:
			multiplier += (miningBoost + allBoost);
			break;
		case COMBAT:
			multiplier += (combatBoost + allBoost);
			break;
		case FORAGING:
			multiplier += (foragingBoost + allBoost);
			break;
		case FISHING:
			multiplier += (fishingBoost + allBoost);
			break;
		case ENCHANTING:
			multiplier += (enchantingBoost + allBoost);
			break;
		case ALCHEMY:
			multiplier += (alchemyBoost + allBoost);
			break;
		case TAMING:
			multiplier += (tamingBoost + allBoost);
			break;
		default:
			break;
		}
		
		if(multiplier < 0.0D) multiplier = 0.0D;
		
		event.setAmount(event.getAmount() * multiplier / 100.0D);
	}
	
	@SubscribeEvent
	public static void onPlayerLevelUp(SkillLevelUpEvent event) {
		PlayerEntity player = event.getPlayer();
		ICoins coins = player.getCapability(CoinsProvider.COINS_CAPABILITY).orElse(null);
		if(Objects.isNull(coins)) return;
		for(int i = event.getPrevLevel() + 1; i <= event.getNewLevel(); i++) {
			coins.add(event.getType().getCoinsRewardUponReachingLevel(i));
		}
		FBPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) event.getPlayer()), new CoinsUpdatePacket(coins.getCoins()));
	}
	
	@SubscribeEvent
	public static void onPlayerUseXP(PlayerXpEvent.LevelChange event) {
		//Check if the player's level change was due to spending
		//If yes, increase the player's Enchanting skill XP.
		PlayerEntity player = event.getPlayer();
		if (player instanceof ClientPlayerEntity) return;
		if (player.openContainer instanceof RepairContainer || player.openContainer instanceof EnchantmentContainer) {
			SkillsHelper.addXP(player, SkillType.ENCHANTING, 
					FBEventHooks.onPlayerSkillXPGain(player, SkillType.ENCHANTING, Math.pow(-event.getLevels(), 1.5) * 3.5));
			SkillsHelper.updateSkill((ServerPlayerEntity) player, SkillType.ENCHANTING);
		}
	}
	
	//Conjuring skill: additional XP gain when picking up orbs.
	@SubscribeEvent
	public static void onPlayerPickUpXP(PlayerXpEvent.PickupXp event) {
		PlayerEntity player = event.getPlayer();
		int enchantingLvl = SkillsHelper.getEnchantingLevelOrElse(player, 0);
		float multiplier = 0.04f * enchantingLvl;
		float bonus = event.getOrb().getXpValue() * multiplier;
		player.giveExperiencePoints(MathHelper.fastFloor(bonus));
		if (MathHelper.frac((double) bonus) != 0) {
			if (MathHelper.frac((double) bonus) > Math.random()) {
				player.giveExperiencePoints(1);
			}
		}
	}
}
