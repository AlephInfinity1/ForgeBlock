package alephinfinity1.forgeblock.item;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.google.common.collect.Multimap;

import alephinfinity1.forgeblock.misc.tier.FBTier;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class YoungDragonArmorItem extends FBArmorItem {
	
	private static final UUID YOUNG_SPEED_BOOST = UUID.fromString("a4fd3d7d-ae27-47f1-b58b-1736d9eecc4e");
	
	public YoungDragonArmorItem(EquipmentSlotType slot, String name, Properties props, FBTier tier,
			Multimap<String, AttributeModifier> modifiers) {
		super(slot, name, props, tier, modifiers);
	}
	
	@Override
	public List<ITextComponent> additionalInformation() {
		List<ITextComponent> list = new ArrayList<>();
		list.add(new StringTextComponent(new TranslationTextComponent("text.forgeblock.armor_desc.young_0").getString()));
		list.add(new StringTextComponent(new TranslationTextComponent("text.forgeblock.armor_desc.young_1").getString()));
		list.add(new StringTextComponent(new TranslationTextComponent("text.forgeblock.armor_desc.young_2").getString()));
		list.add(new StringTextComponent(new TranslationTextComponent("text.forgeblock.armor_desc.young_3").getString()));
		list.add(new StringTextComponent(""));
		return list;
	}
	
	@SubscribeEvent
	public static void onLivingUpdate(LivingUpdateEvent event) {
		LivingEntity living = event.getEntityLiving();
		Iterable<ItemStack> armor = living.getArmorInventoryList();
		for(ItemStack stack : armor) {
			if(!(stack.getItem() instanceof YoungDragonArmorItem)) {
				if(living.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getModifier(YOUNG_SPEED_BOOST) != null) {
					living.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).removeModifier(YOUNG_SPEED_BOOST);
				}
				return;
			}
		}
		if(living.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getModifier(YOUNG_SPEED_BOOST) == null && living.getHealth() / living.getMaxHealth() > 0.5f) {
			living.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).applyModifier(new AttributeModifier(YOUNG_SPEED_BOOST, "Young armor speed boost", 0.07D, Operation.ADDITION));
		}
		if(living.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getModifier(YOUNG_SPEED_BOOST) != null && living.getHealth() / living.getMaxHealth() <= 0.5f) {
			living.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).removeModifier(YOUNG_SPEED_BOOST);
		}
	}
	

}
