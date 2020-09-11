package alephinfinity1.forgeblock.item;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.google.common.collect.Multimap;

import alephinfinity1.forgeblock.attribute.FBAttributes;
import alephinfinity1.forgeblock.misc.tier.FBTier;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class WiseDragonArmorItem extends FBArmorItem {
	
	public static final UUID WISE_MANA_EFFICIENCY_BOOST = UUID.fromString("0fc05101-3872-4de2-815d-0c2862277b7a");

	public WiseDragonArmorItem(EquipmentSlotType slot, String name, Properties props, FBTier tier,
			Multimap<String, AttributeModifier> modifiers) {
		super(slot, name, props, tier, modifiers);
	}

	@Override
	public List<ITextComponent> additionalInformation() {
		List<ITextComponent> list = new ArrayList<>();
		list.add(new StringTextComponent(new TranslationTextComponent("text.forgeblock.armor_desc.wise_0").getString()));
		list.add(new StringTextComponent(new TranslationTextComponent("text.forgeblock.armor_desc.wise_1").getString()));
		list.add(new StringTextComponent(new TranslationTextComponent("text.forgeblock.armor_desc.wise_2").getString()));
		return list;
	}
	
	@SubscribeEvent
	public static void onLivingUpdate(LivingUpdateEvent event) {
		LivingEntity living = event.getEntityLiving();
		if(!(living instanceof PlayerEntity)) return;
		Iterable<ItemStack> armor = living.getArmorInventoryList();
		for(ItemStack stack : armor) {
			if(!(stack.getItem() instanceof WiseDragonArmorItem)) {
				if(living.getAttribute(FBAttributes.MANA_EFFICIENCY).getModifier(WISE_MANA_EFFICIENCY_BOOST) != null) {
					living.getAttribute(FBAttributes.MANA_EFFICIENCY).removeModifier(WISE_MANA_EFFICIENCY_BOOST);
				}
				return;
			}
		}
		if(living.getAttribute(FBAttributes.MANA_EFFICIENCY).getModifier(WISE_MANA_EFFICIENCY_BOOST) == null) {
			living.getAttribute(FBAttributes.MANA_EFFICIENCY).applyModifier(new AttributeModifier(WISE_MANA_EFFICIENCY_BOOST, "Wise armor mana efficiency boost", 50.0D, Operation.ADDITION));
		}
	}

}
