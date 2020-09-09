package alephinfinity1.forgeblock.misc;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.FoodStats;

/*
 * Overrides the vanilla FoodStats.
 * Does nothing.
 */
public class FBFoodStats extends FoodStats {

	@Override
	public void tick(PlayerEntity player) {
		//Do nothing
	}
}
