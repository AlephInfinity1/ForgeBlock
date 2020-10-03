package alephinfinity1.forgeblock.enchantment;

import java.util.ArrayList;
import java.util.Collection;

import alephinfinity1.forgeblock.init.ModEnchantments;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BowItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolItem;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
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

}
