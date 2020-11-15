package alephinfinity1.forgeblock.misc.event;

import alephinfinity1.forgeblock.misc.skills.SkillType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Cancelable;

/**
 * An event to be fired when a player levels up a ForgeBlock skill.
 * The new level is modifiable, while the previous one isn't.
 * This event is neither {@link Cancelable} nor does it has a result.
 */
public class SkillLevelUpEvent extends PlayerEvent {
	
	/**
	 * The type of skill the level up occured in.
	 */
	private SkillType type;
	
	/**
	 * The previous level of the player. Immutable.
	 */
	private final int prevLevel;
	
	/**
	 * The current level of the player. Modifiable.
	 */
	private int newLevel;
	
	/**
	 * The original new level of the player. Immutable.
	 */
	private final int originalNewLevel;
	
	@Override
	public boolean isCancelable() {
		return false;
	}
	
	@Override
	public boolean hasResult() {
		return false;
	}
	
	public SkillLevelUpEvent(PlayerEntity player, SkillType type, int prevLevel, int newLevel) {
		super(player);
		this.type = type;
		this.prevLevel = prevLevel;
		this.newLevel = newLevel;
		this.originalNewLevel = newLevel;
	}
	
	public SkillType getType() {
		return this.type;
	}
	
	public int getPrevLevel() {
		return this.prevLevel;
	}
	
	public int getNewLevel() {
		return this.newLevel;
	}
	
	public int getOriginalNewLevel() {
		return this.originalNewLevel;
	}
	
	public void setType(SkillType type) {
		this.type = type;
	}
	
	public void setNewLevel(int newLevel) {
		this.newLevel = newLevel;
	}

}
