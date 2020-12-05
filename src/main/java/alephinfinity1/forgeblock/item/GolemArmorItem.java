package alephinfinity1.forgeblock.item;

import java.util.ArrayList;
import java.util.List;

import alephinfinity1.forgeblock.init.ModEffects;
import alephinfinity1.forgeblock.misc.itemreqs.IRequirementPredicate;
import alephinfinity1.forgeblock.misc.itemreqs.SkillRequirementPredicate;
import alephinfinity1.forgeblock.misc.skills.SkillType;
import alephinfinity1.forgeblock.misc.tier.FBTier;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class GolemArmorItem extends FBArmorItem implements IRequirementItem {

	public GolemArmorItem(EquipmentSlotType slot, String name, Properties props, FBTier tier, double defenseIn,
			double healthIn) {
		super(slot, name, props, tier, defenseIn, healthIn);
	}
	
	@Override
	public List<ITextComponent> additionalInformation() {
		List<ITextComponent> list = new ArrayList<>();
		list.add(new StringTextComponent(new TranslationTextComponent("text.forgeblock.armor_desc.golem_0").getString()));
		list.add(new StringTextComponent(new TranslationTextComponent("text.forgeblock.armor_desc.golem_1").getString()));
		list.add(new StringTextComponent(new TranslationTextComponent("text.forgeblock.armor_desc.golem_2").getString()));
		list.add(new StringTextComponent(new TranslationTextComponent("text.forgeblock.armor_desc.golem_3").getString()));
		list.add(new StringTextComponent(""));
		return list;
	}
	
	@SubscribeEvent
	public static void onLivingKill(LivingDeathEvent event) {
		if(event.getSource().getTrueSource() != null) {
			if(event.getSource().getTrueSource() instanceof LivingEntity) {
				LivingEntity living = (LivingEntity) event.getSource().getTrueSource();
				Iterable<ItemStack> armor = living.getArmorInventoryList();
				for(ItemStack stack : armor) {
					if(!(stack.getItem() instanceof GolemArmorItem)) return;
				}
				EffectInstance absorptionIn = new EffectInstance(ModEffects.ABSORPTION, 400, 2);
				living.addPotionEffect(absorptionIn);
			}
		}
	}

	@Override
	public IRequirementPredicate[] getRequirements(ItemStack stack) {
		return new IRequirementPredicate[] {SkillRequirementPredicate.combatRequirement(7), SkillRequirementPredicate.skillRequirement(SkillType.MINING, 7)};
	}

}
