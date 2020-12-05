package alephinfinity1.forgeblock.item;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import alephinfinity1.forgeblock.entity.LapisZombieEntity;
import alephinfinity1.forgeblock.misc.itemreqs.IRequirementPredicate;
import alephinfinity1.forgeblock.misc.itemreqs.SkillRequirementPredicate;
import alephinfinity1.forgeblock.misc.skills.SkillType;
import alephinfinity1.forgeblock.misc.tier.FBTier;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class LapisArmorItem extends FBArmorItem implements IRequirementItem {
	
	private static final UUID LAPIS_HEALTH_BOOST = UUID.fromString("654d36d0-50ea-4d7d-9a93-1c0b7e9ddaea");

	public LapisArmorItem(EquipmentSlotType slot, String name, Properties props, FBTier tier, double defenseIn,
			double healthIn) {
		super(slot, name, props, tier, defenseIn, healthIn);
	}
	
	@Override
	public List<ITextComponent> additionalInformation() {
		List<ITextComponent> list = new ArrayList<>();
		list.add(new StringTextComponent(new TranslationTextComponent("text.forgeblock.armor_desc.lapis_0").getString()));
		list.add(new StringTextComponent(new TranslationTextComponent("text.forgeblock.armor_desc.lapis_1").getString()));
		list.add(new StringTextComponent(new TranslationTextComponent("text.forgeblock.armor_desc.lapis_2").getString()));
		list.add(new StringTextComponent(new TranslationTextComponent("text.forgeblock.armor_desc.lapis_3").getString()));
		list.add(new StringTextComponent(new TranslationTextComponent("text.forgeblock.armor_desc.lapis_4").getString()));
		list.add(new StringTextComponent(new TranslationTextComponent("text.forgeblock.armor_desc.lapis_5").getString()));
		list.add(new StringTextComponent(new TranslationTextComponent("text.forgeblock.armor_desc.lapis_6").getString()));
		list.add(new StringTextComponent(""));
		return list;
	}
	
	@SubscribeEvent
	public static void onLivingUpdate(LivingUpdateEvent event) {
		LivingEntity living = event.getEntityLiving();
		Iterable<ItemStack> armor = living.getArmorInventoryList();
		for(ItemStack stack : armor) {
			if(!(stack.getItem() instanceof LapisArmorItem) || living instanceof LapisZombieEntity) { //If lapis zombie, do not give health boost
				if(living.getAttribute(SharedMonsterAttributes.MAX_HEALTH).getModifier(LAPIS_HEALTH_BOOST) != null) {
					living.getAttribute(SharedMonsterAttributes.MAX_HEALTH).removeModifier(LAPIS_HEALTH_BOOST);
				}
				return;
			}
		}
		if(living.getAttribute(SharedMonsterAttributes.MAX_HEALTH).getModifier(LAPIS_HEALTH_BOOST) == null) {
			living.getAttribute(SharedMonsterAttributes.MAX_HEALTH).applyModifier(new AttributeModifier(LAPIS_HEALTH_BOOST, "Lapis armor health boost", 60.0D, Operation.ADDITION));
		}
	}
	
	//Multiplies the ore XP drop. Priority set to low to allow accurate multiplication.
	@SubscribeEvent(priority = EventPriority.LOW)
	public static void onPlayerBreakBlock(BlockEvent.BreakEvent event) {
		PlayerEntity player = event.getPlayer();
		Iterable<ItemStack> armor = player.getArmorInventoryList();
		int piecesCount = 0;
		for(ItemStack stack : armor) {
			if(stack.getItem() instanceof LapisArmorItem) ++piecesCount;
		}
		event.setExpToDrop((int) (event.getExpToDrop() * (1 + 0.5 * piecesCount)));
	}

	@Override
	public IRequirementPredicate[] getRequirements(ItemStack stack) {
		return new IRequirementPredicate[] {SkillRequirementPredicate.skillRequirement(SkillType.MINING, 5)};
	}

}
