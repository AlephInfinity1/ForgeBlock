package alephinfinity1.forgeblock.enchantment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import alephinfinity1.forgeblock.init.ModEnchantments;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolItem;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class TelekinesisEnchantment extends Enchantment {

	protected TelekinesisEnchantment(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType[] slots) {
		super(rarityIn, typeIn, slots);
	}
	
	public TelekinesisEnchantment() {
		super(Rarity.COMMON, EnchantmentType.create("TOOLS_AND_WEAPON", (item) -> (item instanceof SwordItem || item instanceof ToolItem || item instanceof BowItem)), new EquipmentSlotType[] {EquipmentSlotType.HEAD, EquipmentSlotType.CHEST, EquipmentSlotType.LEGS, EquipmentSlotType.FEET, EquipmentSlotType.MAINHAND, EquipmentSlotType.OFFHAND});
	}
	
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void onPlayerKillEntity(LivingDropsEvent event) {
		if(event.getSource().getTrueSource() == null) return;
		if(!(event.getSource().getTrueSource() instanceof PlayerEntity)) return;
		PlayerEntity player = (PlayerEntity) event.getSource().getTrueSource();
		if(EnchantmentHelper.getEnchantmentLevel(ModEnchantments.TELEKINESIS.get(), player.getHeldItemMainhand()) == 0) return;
		Collection<ItemEntity> drops = event.getDrops();
		Collection<ItemEntity> unfitDrops = new ArrayList<>();
		for(ItemEntity drop : drops) {
			boolean fit = player.addItemStackToInventory(drop.getItem());
			if(!fit) unfitDrops.add(drop);
		}
		drops.clear();
		drops.addAll(unfitDrops);
	}
	
	/*
	 * Adds experience drops directly to player.
	 * Set to lowest priority to allow other XP modifiers to take place first.
	 */
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void onPlayerKillEntityExp(LivingExperienceDropEvent event) {
		PlayerEntity player = event.getAttackingPlayer();
		if(player == null) return;
		if(EnchantmentHelper.getEnchantmentLevel(ModEnchantments.TELEKINESIS.get(), player.getHeldItemMainhand()) == 0) return;
		player.giveExperiencePoints(event.getDroppedExperience());
		event.setCanceled(true);
	}
	
	/*
	 * Teleports block drop XP to player
	 */
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void onPlayerBreakBlockExp(BlockEvent.BreakEvent event) {
		PlayerEntity player = event.getPlayer();
		if(player == null) return;
		if(EnchantmentHelper.getEnchantmentLevel(ModEnchantments.TELEKINESIS.get(), player.getHeldItemMainhand()) == 0) return;
		player.giveExperiencePoints(event.getExpToDrop());
		event.setExpToDrop(0);
	}
	
	/*
	 * Teleports block drops to player inventory
	 * See TelekinesisLootModifier for more
	 */
	@SubscribeEvent
	public static void onEntityJoinWorld(EntityJoinWorldEvent event) {
		if(!(event.getEntity() instanceof ItemEntity)) return;
		ItemEntity item = (ItemEntity) event.getEntity();
		ItemStack stack = item.getItem();
		if(stack.getTag() == null) return;
		UUID uuid = stack.getTag().getUniqueId("MinedBy");
		//If uuid does not exist, return. (new UUID(0, 0) is the empty UUID returned if getUniqueId cannot find anything.)
		if(uuid.equals(new UUID(0, 0))) return;
		//If uuid does exist, the item entity is created by telekinesis. (See TelekinesisLootModifier)
		
		World world = event.getWorld();
		PlayerEntity player = world.getPlayerByUuid(uuid);
		if(player.addItemStackToInventory(stack)) { //If stack successfully added, remove item entity from world.
			item.remove();
		}
	}

}