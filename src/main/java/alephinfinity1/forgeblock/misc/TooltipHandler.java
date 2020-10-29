package alephinfinity1.forgeblock.misc;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import com.google.common.collect.Multimap;

import alephinfinity1.forgeblock.item.IFBItem;
import alephinfinity1.forgeblock.misc.tier.FBTier;
import alephinfinity1.forgeblock.misc.tier.TierHelper;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class TooltipHandler {

	/*
	private static DecimalFormat createAttributeModifierDecimalFormat() {
		DecimalFormat decimalformat = new DecimalFormat("#.##");
		decimalformat.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.ROOT));
		return decimalformat;
	}
	*/

	
	
	@SubscribeEvent
	public static void modifyTooltip(ItemTooltipEvent event) {
		if(!event.getFlags().isAdvanced()) {
			List<ITextComponent> tooltip = event.getToolTip();
			PlayerEntity playerIn = event.getPlayer();
			UUID ATTACK_DAMAGE_MODIFIER = UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");
			UUID ATTACK_SPEED_MODIFIER = UUID.fromString("FA233E1C-4180-4865-B01B-BCCE9785ACA3");
			ItemStack stack = event.getItemStack();
			//DecimalFormat DECIMALFORMAT = createAttributeModifierDecimalFormat();
	
			
			if(event.getItemStack().getItem() instanceof IFBItem) {
				for(EquipmentSlotType equipmentslottype : EquipmentSlotType.values()) {
					Multimap<String, AttributeModifier> multimap = stack.getAttributeModifiers(equipmentslottype);
					if (!multimap.isEmpty()) {
						tooltip.remove(tooltip.size() - 1);
						tooltip.remove(tooltip.size() - 1);
	
						for(Entry<String, AttributeModifier> entry : multimap.entries()) {
							AttributeModifier attributemodifier = entry.getValue();
							double d0 = attributemodifier.getAmount();
							boolean flag = false;
							if (playerIn != null) {
								if (attributemodifier.getID() == ATTACK_DAMAGE_MODIFIER) {
									d0 = d0 + playerIn.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getBaseValue();
									d0 = d0 + (double)EnchantmentHelper.getModifierForCreature(stack, CreatureAttribute.UNDEFINED);
									flag = true;
								} else if (attributemodifier.getID() == ATTACK_SPEED_MODIFIER) {
									d0 += playerIn.getAttribute(SharedMonsterAttributes.ATTACK_SPEED).getBaseValue();
									flag = true;
								}
							}
	
							double d1;
							if (attributemodifier.getOperation() != AttributeModifier.Operation.MULTIPLY_BASE && attributemodifier.getOperation() != AttributeModifier.Operation.MULTIPLY_TOTAL) {
								d1 = d0;
							} else {
								d1 = d0 * 100.0D;
							}
	
							if (flag) {
								tooltip.remove(tooltip.size() - 1);
							} else if (d0 > 0.0D) {
								tooltip.remove(tooltip.size() - 1);
							} else if (d0 < 0.0D) {
								d1 = d1 * -1.0D;
								tooltip.remove(tooltip.size() - 1);
							}
						}
					}
				}
			}
	
	
	
			//Remove enchantment tooltips in favour of FB tooltips
			if(event.getItemStack().isEnchanted()) {
				Map<Enchantment, Integer> enchants = EnchantmentHelper.getEnchantments(stack);
				Set<Map.Entry<Enchantment, Integer>> set = enchants.entrySet();
				for(Map.Entry<Enchantment, Integer> entry : set) {
					ITextComponent text = entry.getKey().getDisplayName(entry.getValue());
					tooltip.remove(text);
				}
			}
	
			//Add common tier to all non-FB items
			if(!(event.getItemStack().getItem() instanceof IFBItem)) {
				FBTier tier = TierHelper.getItemTier(event.getItemStack());
				boolean recombobulated = false;
				if(event.getItemStack().getTag() != null) {
					if(event.getItemStack().getTag().getByte("Recombobulated") != 0) recombobulated = true;
				}
				String color = tier.color.toString();
				String bold = TextFormatting.BOLD.toString();
				String obfuscated = TextFormatting.OBFUSCATED.toString();
				String reset = TextFormatting.RESET.toString();
				if(!recombobulated) tooltip.add(new StringTextComponent(color + bold + tier.name.getString()));
				else tooltip.add(new StringTextComponent(color + bold + obfuscated + "n " + reset + color + bold + tier.name.getString() + obfuscated + " n"));
			}
			
			/*
			 * A temporary fix for CustomSwordItem having 1 extra newline at end
			 */
			if(tooltip.get(tooltip.size() - 1).equals(new StringTextComponent(""))) {
				tooltip.remove(tooltip.size() - 1);
			}
		}
	}
	

}
