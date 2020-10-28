package alephinfinity1.forgeblock.enchantment;

import java.util.Random;

import alephinfinity1.forgeblock.init.ModEnchantments;
import alephinfinity1.forgeblock.misc.RNGHelper;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BowItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolItem;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ExperienceEnchantment extends Enchantment {

	protected ExperienceEnchantment(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType[] slots) {
		super(rarityIn, typeIn, slots);
	}
	
	public ExperienceEnchantment() {
		super(Rarity.UNCOMMON, EnchantmentType.create("TOOLS_AND_WEAPON", (item) -> (item instanceof SwordItem || item instanceof ToolItem || item instanceof BowItem)), new EquipmentSlotType[] {EquipmentSlotType.HEAD, EquipmentSlotType.CHEST, EquipmentSlotType.LEGS, EquipmentSlotType.FEET, EquipmentSlotType.MAINHAND, EquipmentSlotType.OFFHAND});
	}
	
	@SubscribeEvent
	public static void onPlayerKillEntityExp(LivingExperienceDropEvent event) {
		PlayerEntity player = event.getAttackingPlayer();
		if(player == null) return;
		double xpEnchBonus = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.EXPERIENCE.get(), player.getHeldItemMainhand()) * 0.125;
		double exactXp = event.getDroppedExperience() * (1.0 + xpEnchBonus);
		double partial = exactXp % 1.0D;
		if(RNGHelper.randomChance(partial, new Random()))
			event.setDroppedExperience((int) exactXp + 1);
		else
			event.setDroppedExperience((int) exactXp);
	}
	
	public static void onPlayerBreakBlockExp(BlockEvent.BreakEvent event) {
		PlayerEntity player = event.getPlayer();
		if(player == null) return;
		double xpEnchBonus = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.EXPERIENCE.get(), player.getHeldItemMainhand()) * 0.125;
		double exactXp = event.getExpToDrop() * (1.0 + xpEnchBonus);
		double partial = exactXp % 1.0D;
		if(RNGHelper.randomChance(partial, new Random()))
			event.setExpToDrop((int) exactXp + 1);
		else
			event.setExpToDrop((int) exactXp);
	}
	
	@Override
	public int getMaxLevel() {
		return 3;
	}

}
