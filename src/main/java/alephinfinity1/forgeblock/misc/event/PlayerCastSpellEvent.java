package alephinfinity1.forgeblock.misc.event;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Cancelable;

/**
 * Fired whenever a player casts a spell.
 * This event is {@link Cancelable}
 * This event does not have a result.
 */
@Cancelable
public class PlayerCastSpellEvent extends PlayerEvent {
	
	private ItemStack stack;
	private double mana;
	private final double originalMana;

	public PlayerCastSpellEvent(PlayerEntity player, ItemStack stack, double manaConsumed) {
		super(player);
		this.stack = stack;
		this.mana = manaConsumed;
		this.originalMana = manaConsumed;
	}
	
	@Override
	public boolean isCancelable() {
		return true;
	}
	
	@Override
	public boolean hasResult() {
		return false;
	}
	
	public ItemStack getItemStack() {
		return stack;
	}
	
	public double getManaConsumed() {
		return mana;
	}
	
	public double getOriginalMana() {
		return originalMana;
	}
	
	public void setItemStack(ItemStack stack) {
		this.stack = stack;
	}
	
	public void setManaConsumed(double mana) {
		this.mana = mana;
	}

}
