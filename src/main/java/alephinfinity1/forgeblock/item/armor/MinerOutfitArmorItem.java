package alephinfinity1.forgeblock.item.armor;

import java.util.ArrayList;
import java.util.List;

import alephinfinity1.forgeblock.item.IRequirementItem;
import alephinfinity1.forgeblock.misc.itemreqs.IRequirementPredicate;
import alephinfinity1.forgeblock.misc.itemreqs.SkillRequirementPredicate;
import alephinfinity1.forgeblock.misc.capability.skills.SkillType;
import alephinfinity1.forgeblock.misc.tier.FBTier;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class MinerOutfitArmorItem extends FBArmorItem implements IRequirementItem {

	public MinerOutfitArmorItem(EquipmentSlotType slot, String name, Properties props, FBTier tier, double defenseIn,
			double healthIn) {
		super(slot, name, props, tier, defenseIn, healthIn);
	}
	
	@Override
	public List<ITextComponent> additionalInformation() {
		List<ITextComponent> list = new ArrayList<>();
		list.add(new StringTextComponent(new TranslationTextComponent("text.forgeblock.armor_desc.miner_outfit_0").getString()));
		list.add(new StringTextComponent(new TranslationTextComponent("text.forgeblock.armor_desc.miner_outfit_1").getString()));
		list.add(new StringTextComponent(new TranslationTextComponent("text.forgeblock.armor_desc.miner_outfit_2").getString()));
		list.add(new StringTextComponent(""));
		return list;
	}
	
	@SubscribeEvent
	public static void onLivingUpdate(LivingUpdateEvent event) {
		LivingEntity living = event.getEntityLiving();
		Iterable<ItemStack> armor = living.getArmorInventoryList();
		for(ItemStack stack : armor) {
			if(!(stack.getItem() instanceof MinerOutfitArmorItem)) return;
		}
		EffectInstance haste = new EffectInstance(Effects.HASTE, 100, 1, true, true);
		living.addPotionEffect(haste);
	}

	@Override
	public IRequirementPredicate[] getRequirements(ItemStack stack) {
		return new IRequirementPredicate[] {SkillRequirementPredicate.skillRequirement(SkillType.MINING, 5)};
	}

}
