package alephinfinity1.forgeblock.item.armor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import alephinfinity1.forgeblock.item.IRequirementItem;
import com.google.common.collect.Multimap;

import alephinfinity1.forgeblock.misc.event.PlayerCastSpellEvent;
import alephinfinity1.forgeblock.misc.itemreqs.IRequirementPredicate;
import alephinfinity1.forgeblock.misc.itemreqs.SkillRequirementPredicate;
import alephinfinity1.forgeblock.misc.tier.FBTier;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class WiseDragonArmorItem extends FBArmorItem implements IRequirementItem {
	
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
		list.add(new StringTextComponent(""));
		return list;
	}
	
	@SubscribeEvent
	public static void onPlayerCastSpell(PlayerCastSpellEvent event) {
		PlayerEntity player = event.getPlayer();
		Iterable<ItemStack> armor = player.getArmorInventoryList();
		for(ItemStack stack : armor) {
			if(!(stack.getItem() instanceof WiseDragonArmorItem)) return;
		}
		event.setManaConsumed(event.getManaConsumed() * 2.0D / 3.0D);
	}
	
	@Override
	public IRequirementPredicate[] getRequirements(ItemStack stack) {
		return new IRequirementPredicate[] {SkillRequirementPredicate.combatRequirement(16)};
	}

}
