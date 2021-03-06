package alephinfinity1.forgeblock.misc.event;

import alephinfinity1.forgeblock.misc.capability.skills.SkillType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

public class FBEventHooks {

	public static double onPlayerSkillXPGain(PlayerEntity player, SkillType type, double amount) {
		SkillXPGainEvent event = new SkillXPGainEvent(player, type, amount);
		boolean canceled = MinecraftForge.EVENT_BUS.post(event);
		if(canceled) return 0.0D;
		else return event.getAmount();
	}
	
	public static PlayerCastSpellEvent onPlayerCastSpell(PlayerEntity player, ItemStack stack, double mana) {
		PlayerCastSpellEvent event = new PlayerCastSpellEvent(player, stack, mana);
		@SuppressWarnings("unused")
		boolean canceled = MinecraftForge.EVENT_BUS.post(event);
		return event;
	}
	
	public static int onPlayerSkillLevelUp(PlayerEntity player, SkillType type, int prevLevel, int newLevel) {
		SkillLevelUpEvent event = new SkillLevelUpEvent(player, type, prevLevel, newLevel);
		MinecraftForge.EVENT_BUS.post(event);
		return event.getNewLevel();
	}
}
