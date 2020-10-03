package alephinfinity1.forgeblock.misc.drops;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.function.Function;

import javax.annotation.Nullable;

import alephinfinity1.forgeblock.attribute.FBAttributes;
import alephinfinity1.forgeblock.init.ModEnchantments;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class DropData {
	public static enum DropType {
		COMMON(false, false, false, false), //Most usual drops
		UNCOMMON(true, false, false, false), //Message sent, but not affected by magic find (e.g. foul flesh, toxic arrow poison)
		RARE(true, false, true, false), //Message sent and affected by magic find
		COMMON_ARMOR(false, true, false, false), //Affected by luck, no message
		UNCOMMON_ARMOR(true, true, false, false), //Affected by luck, send message
		RARE_ARMOR(true, true, true, false), //Affected by luck and magic find, with message
		PET(true, false, false, true); //Message sent and affected by pet luck
		
		public boolean message;
		public boolean isAffectedByArmorLuck;
		public boolean isAffectedByMF;
		public boolean isAffectedByPL;
		
		private DropType(boolean message, boolean isAffectedByArmorLuck, 
				boolean isAffectedByMF, boolean isAffectedByPL) {
			this.message = message;
			this.isAffectedByArmorLuck = isAffectedByArmorLuck;
			this.isAffectedByMF = isAffectedByMF;
			this.isAffectedByPL = isAffectedByPL;
		}
	}
	
	private ItemStack item;
	private DropType type;
	private float dropRate;
	private @Nullable Function<ItemStack, ItemStack> function;
	
	public DropData(ItemStack item, DropType type, float dropRate) {
		this.item = item;
		this.type = type;
		this.dropRate = dropRate;
		this.function = null;
	}
	
	public DropData(ItemStack item, DropType type, float dropRate, int amount) {
		this(item, type, dropRate);
		function = (stack) -> {
			stack.setCount(amount);
			return stack;
		};
	}
	
	public ItemStack drop(PlayerEntity player, boolean sendMessage) {
		int luckLevel = 0;
		if(player.getHeldItemMainhand() != null) {
			luckLevel = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.LUCK.get(), player.getHeldItemMainhand());
		}
		return this.drop(player, luckLevel, player.getAttribute(FBAttributes.MAGIC_FIND).getValue(), 
				player.getAttribute(FBAttributes.PET_LUCK).getValue(), sendMessage);
	}
	
	public ItemStack drop(PlayerEntity player, int luckLevel, double magicFind, double petLuck, boolean sendMessage) {
		float actualDropRate = this.dropRate;
		if(this.type.isAffectedByArmorLuck) actualDropRate *= (1 + 0.05 * luckLevel);
		if(this.type.isAffectedByMF) actualDropRate *= (1 + 0.01 * magicFind);
		if(this.type.isAffectedByPL) actualDropRate *= (1 + 0.01 * petLuck);
		Random rng = new Random();
		if(rng.nextFloat() < actualDropRate) {
			if(this.type.message && sendMessage)
				this.sendDropMessage(player, magicFind);
			if(function != null) {
				return function.apply(item);
			} else {
				return item;
			}
		} else return ItemStack.EMPTY;
	}
	
	public void sendDropMessage(PlayerEntity player, double magicFind) {
		ITextComponent text;
		if(magicFind > 0.001d && magicFind < 0.001d) {
			text = new StringTextComponent(new TranslationTextComponent("misc.forgeblock.rareDrop").getString() + " " + this.item.getDisplayName().getString() + " " + new TranslationTextComponent("misc.forgeblock.magicFindBonus", new DecimalFormat("#").format(magicFind)).getString());
		} else {
			text = new StringTextComponent(new TranslationTextComponent("misc.forgeblock.rareDrop").getString() + " " + this.item.getDisplayName().getString());
		}
		player.sendMessage(text);
	}
}
