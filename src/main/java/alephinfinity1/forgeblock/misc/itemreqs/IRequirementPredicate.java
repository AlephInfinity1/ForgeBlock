package alephinfinity1.forgeblock.misc.itemreqs;

import java.util.List;
import java.util.function.Predicate;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;

/**
 * A specialisation of predicates that targets players.
 */
public interface IRequirementPredicate extends Predicate<PlayerEntity> {

	public List<ITextComponent> getDisplay(boolean isMet);
	
	public default List<ITextComponent> getDisplay(PlayerEntity player) {
		return this.getDisplay(this.test(player));
	}
	
}
