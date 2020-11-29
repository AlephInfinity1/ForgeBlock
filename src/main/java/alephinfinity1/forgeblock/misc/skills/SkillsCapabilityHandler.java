package alephinfinity1.forgeblock.misc.skills;

import java.util.function.Function;

import alephinfinity1.forgeblock.ForgeBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerRespawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class SkillsCapabilityHandler {
	
	/*
	 * Get the magnitude of attribute modifier. Used for farming and fishing.
	 */
	public static final Function<Integer, Double> ATTRIBUTE_MODIFIER_AMOUNT_1 = (level) -> {
		if (level <= 14) {
			return Double.valueOf(level * 2);
		} else if (level >= 15 && level <= 19) {
			return Double.valueOf(level * 3 - 14);
		} else if (level >= 20 && level <= 25) {
			return Double.valueOf(level * 4 - 33);
		} else {
			return Double.valueOf(level * 5 - 58);
		}
	};
	
	/*
	 * Get the magnitude of attribute modifier. Used for mining, foraging, enchanting, and alchemy.
	 */
	public static final Function<Integer, Double> ATTRIBUTE_MODIFIER_AMOUNT_2 = (level) -> level <= 14 ? Double.valueOf(level) : Double.valueOf(level * 2 - 14);
	
	/*
	 * Get the magnitude of attribute modifier. Used for combat.
	 */
	public static final Function<Integer, Double> ATTRIBUTE_MODIFIER_AMOUNT_COMBAT = (level) -> Double.valueOf(level * 0.5);
	
	/*
	 * Get the magnitude of attribute modifier. Used for taming. (Purely for semantic reasons lol)
	 */
	public static final Function<Integer, Double> ATTRIBUTE_MODIFIER_AMOUNT_TAMING = (level) -> Double.valueOf(level);
	
	public static final ResourceLocation SKILLS_CAPABILITY = new ResourceLocation(ForgeBlock.MOD_ID, "skills");
	
	@SubscribeEvent
	public static void onCapabilityAttach(AttachCapabilitiesEvent<Entity> event) {
		if(event.getObject() instanceof PlayerEntity) {
			event.addCapability(SKILLS_CAPABILITY, new SkillsProvider());
		}
	}
	
	@SubscribeEvent
	public static void onPlayerClone(PlayerEvent.Clone event) {
		ISkills oldSkills = event.getOriginal().getCapability(SkillsProvider.SKILLS_CAPABILITY).orElseThrow(NullPointerException::new);
		ISkills newSkills = event.getPlayer().getCapability(SkillsProvider.SKILLS_CAPABILITY).orElseThrow(NullPointerException::new);
		newSkills.set(oldSkills);
		SkillsEventHandler.updateAllSkills(event.getPlayer(), event.getPlayer().getCapability(SkillsProvider.SKILLS_CAPABILITY).orElseThrow(NullPointerException::new), false);
	}
	
	@SubscribeEvent
	public static void onPlayerRespawn(PlayerRespawnEvent event) {
		SkillsEventHandler.updateAllSkills(event.getPlayer(), event.getPlayer().getCapability(SkillsProvider.SKILLS_CAPABILITY).orElseThrow(NullPointerException::new), false);
	}

}
