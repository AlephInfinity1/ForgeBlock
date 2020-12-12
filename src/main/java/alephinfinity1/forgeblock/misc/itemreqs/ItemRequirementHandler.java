package alephinfinity1.forgeblock.misc.itemreqs;

import alephinfinity1.forgeblock.item.IRequirementItem;
import alephinfinity1.forgeblock.misc.event.PlayerCastSpellEvent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.EquipmentSlotType.Group;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * A handler class dedicated to limit player actions if they are not allowed to use the item.
 */
@Mod.EventBusSubscriber
public class ItemRequirementHandler {

	/**
	 * Disable player attacks if player is not allowed to use the weapon.
	 * @param event
	 */
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void onPlayerAttack(AttackEntityEvent event) {
		PlayerEntity player = event.getPlayer();
		ItemStack stack = player.getHeldItemMainhand();
		if (stack.getItem() instanceof IRequirementItem) {
			if (!((IRequirementItem) stack.getItem()).canPlayerUseItem(player, stack)) {
				event.setCanceled(true);
				player.sendStatusMessage(new TranslationTextComponent("text.forgeblock.reqNotMet").applyTextStyle(TextFormatting.RED), true);
				return;
			}
		}
	}
	
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void onPlayerCastSpell(PlayerCastSpellEvent event) {
		PlayerEntity player = event.getPlayer();
		ItemStack stack = player.getHeldItemMainhand();
		if (stack.getItem() instanceof IRequirementItem) {
			if (!((IRequirementItem) stack.getItem()).canPlayerUseItem(player, stack)) {
				event.setCanceled(true);
				player.sendStatusMessage(new TranslationTextComponent("text.forgeblock.reqNotMet").applyTextStyle(TextFormatting.RED), true);
				return;
			}
		}
	}
	
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void onPlayerWearArmor(LivingEquipmentChangeEvent event) {
		LivingEntity living = event.getEntityLiving();
		if (living instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) living;
			ItemStack stack = event.getTo();
			if (stack.getItem() instanceof IRequirementItem) {
				//If player does not meet requirement
				if (!((IRequirementItem) stack.getItem()).canPlayerUseItem(player, stack)) {
					//Since the event is not cancelable, we have to take the armour off manually.
					EquipmentSlotType slot = event.getSlot();
					//Only unequip armour, do not unequip weapons.
					if (slot.getSlotType().equals(Group.ARMOR)) {
						ItemStack actualItem = player.inventory.armorInventory.get(slot.getIndex());
						if (!player.addItemStackToInventory(actualItem)) {
							player.dropItem(actualItem, false);
						}
						player.inventory.armorInventory.set(slot.getIndex(), ItemStack.EMPTY);
						player.sendStatusMessage(new TranslationTextComponent("text.forgeblock.reqNotMet").applyTextStyle(TextFormatting.RED), true);
					}
				}
			}
		}
	}
	
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void onPlayerLeftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
		PlayerEntity player = event.getPlayer();
		ItemStack stack = player.getHeldItemMainhand();
		if (stack.getItem() instanceof IRequirementItem) {
			if (!((IRequirementItem) stack.getItem()).canPlayerUseItem(player, stack)) {
				event.setCanceled(true);
				player.sendStatusMessage(new TranslationTextComponent("text.forgeblock.reqNotMet").applyTextStyle(TextFormatting.RED), true);
				return;
			}
		}
	}
	
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void onPlayerRightClickItem(PlayerInteractEvent.RightClickItem event) {
		PlayerEntity player = event.getPlayer();
		ItemStack stack = player.getHeldItemMainhand();
		if (stack.getItem() instanceof IRequirementItem) {
			if (!((IRequirementItem) stack.getItem()).canPlayerUseItem(player, stack)) {
				event.setCanceled(true);
				player.sendStatusMessage(new TranslationTextComponent("text.forgeblock.reqNotMet").applyTextStyle(TextFormatting.RED), true);
				return;
			}
		}
	}
	
}
