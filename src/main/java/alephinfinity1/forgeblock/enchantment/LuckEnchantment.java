package alephinfinity1.forgeblock.enchantment;

import java.util.Random;

import alephinfinity1.forgeblock.init.ModEnchantments;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class LuckEnchantment extends Enchantment {

	protected LuckEnchantment(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType[] slots) {
		super(rarityIn, typeIn, slots);
	}
	
	public LuckEnchantment() {
		super(Rarity.UNCOMMON, EnchantmentType.WEAPON, new EquipmentSlotType[] {EquipmentSlotType.MAINHAND});
	}
	
	@Override
	public int getMaxLevel() {
		return 5;
	}
	
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public static void onPlayerKillEntity(LivingDropsEvent event) {
		if(event.getSource().getTrueSource() == null) return;
		if(!(event.getSource().getTrueSource() instanceof PlayerEntity)) return;
		PlayerEntity player = (PlayerEntity) event.getSource().getTrueSource();
		int luckLevel = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.LUCK.get(), player.getHeldItemMainhand());
		if(luckLevel == 0) return;
		LivingEntity victim = event.getEntityLiving();
		Iterable<ItemStack> armor = victim.getArmorInventoryList();
		Random rng = new Random();
		for(ItemStack piece : armor) {
			
		}
	}

}
