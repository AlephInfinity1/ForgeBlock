package alephinfinity1.forgeblock.enchantment;

import java.util.UUID;

import alephinfinity1.forgeblock.init.ModEnchantments;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class GrowthEnchantment extends Enchantment {

	public GrowthEnchantment(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType[] slots) {
		super(rarityIn, typeIn, slots);
	}
	
	public GrowthEnchantment() {
		super(Rarity.RARE, EnchantmentType.ARMOR, new EquipmentSlotType[] {EquipmentSlotType.HEAD, EquipmentSlotType.CHEST, EquipmentSlotType.LEGS, EquipmentSlotType.FEET});
	}
	
	@SubscribeEvent
	public static void onLivingUpdate(LivingUpdateEvent event) {
		LivingEntity living = event.getEntityLiving();
		Iterable<ItemStack> armorItems = living.getArmorInventoryList();
		int enchLevel = 0;
		for(ItemStack stack : armorItems) {
			enchLevel += EnchantmentHelper.getEnchantmentLevel(ModEnchantments.GROWTH.get(), stack);
		}
		AttributeModifier growthModifier = new AttributeModifier(UUID.fromString("1422aa8d-d317-4f6d-8152-56a14eb61f69"), "Growth modifier", enchLevel * 15.0D, Operation.ADDITION);
		if(living.getAttribute(SharedMonsterAttributes.MAX_HEALTH).getModifier(UUID.fromString("1422aa8d-d317-4f6d-8152-56a14eb61f69")) != null) living.getAttribute(SharedMonsterAttributes.MAX_HEALTH).removeModifier(UUID.fromString("1422aa8d-d317-4f6d-8152-56a14eb61f69"));
		living.getAttribute(SharedMonsterAttributes.MAX_HEALTH).applyModifier(growthModifier);
	}
	
	@Override
	public int getMaxLevel() {
		return 5;
	}

}
