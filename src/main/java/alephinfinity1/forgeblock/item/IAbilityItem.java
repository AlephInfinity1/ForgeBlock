package alephinfinity1.forgeblock.item;

import java.util.List;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;

/*
 * An interface for every item that has an ability.
 * All methods are ItemStack specific
 */
public interface IAbilityItem {
	
	public List<ITextComponent> abilityDescription(ItemStack stack);
	
	/*
	 * Returns whether the ability was successfully activated
	 */
	public boolean activateAbility(PlayerEntity player, ItemStack stack);
	
	public double getAbilityCost(ItemStack stack);
}
