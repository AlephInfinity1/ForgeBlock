package alephinfinity1.forgeblock.misc.event;

import alephinfinity1.forgeblock.misc.skills.SkillType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Cancelable;

/**
 * An event to be fired whenever a player gains Skill XP.
 * Only applicable when player gains Skill XP, vanilla XP will not be taken into consideration.
 * This event is {@link @Cancelable}
 * This event does not have a result.
 */
@Cancelable
public class SkillXPGainEvent extends PlayerEvent {

	private SkillType type;
	private double amount;
	private final double original;
	
	public SkillXPGainEvent(PlayerEntity player, SkillType type, double amount) {
		super(player);
		this.type = type;
		this.amount = amount;
		this.original = amount;
	}
	
	@Override
	public boolean isCancelable() {
		return true;
	}
	
	@Override
	public boolean hasResult() {
		return false;
	}
	
	public SkillType getType() {
		return type;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public double getOriginalAmount() {
		return original;
	}
	
	public void setType(SkillType type) {
		this.type = type;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
}
