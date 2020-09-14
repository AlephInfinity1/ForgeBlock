package alephinfinity1.forgeblock.item;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import alephinfinity1.forgeblock.attribute.FBAttributes;
import alephinfinity1.forgeblock.init.ModEffects;
import alephinfinity1.forgeblock.init.ModEnchantments;
import alephinfinity1.forgeblock.misc.mana.ManaProvider;
import alephinfinity1.forgeblock.misc.tier.FBTier;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class AspectOfTheEndItem extends FBSwordItem implements IAbilityItem {
	
	public AspectOfTheEndItem(Properties props, FBTier tier, double attackDamageIn, double strengthIn, double critChanceIn, double critDamageIn) {
		super(props, tier, attackDamageIn, strengthIn, critChanceIn, critDamageIn);
	}
	
	@Override
	public List<ITextComponent> additionalInformation(ItemStack stack) {
		List<ITextComponent> list = new ArrayList<>();
		list.addAll(this.abilityDescription(stack));
		list.add(new StringTextComponent(""));
		return list;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		if(worldIn.isRemote) return ActionResult.resultPass(playerIn.getHeldItem(handIn));
		ItemStack stack = playerIn.getHeldItem(handIn);
		if(playerIn.getCapability(ManaProvider.MANA_CAPABILITY).orElseThrow(() -> new NullPointerException()).consume(this.getAbilityCost(stack, playerIn))) {
			activateAbility(worldIn, playerIn, stack);
			playerIn.sendStatusMessage(new StringTextComponent(new TranslationTextComponent("text.forgeblock.useAbility.aote").getString() + TextFormatting.AQUA.toString() + " (" + new DecimalFormat("#").format(this.getAbilityCost(stack, playerIn)) + " " + new TranslationTextComponent("misc.forgeblock.mana").getString() + ")"), true);
			return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
		}
		playerIn.sendStatusMessage(new StringTextComponent(new TranslationTextComponent("text.forgeblock.notEnoughMana").getString()), true);
		return ActionResult.resultFail(playerIn.getHeldItem(handIn));
	}

	@Override
	public List<ITextComponent> abilityDescription(ItemStack stack) {
		List<ITextComponent> list = new ArrayList<>();
		list.add(new StringTextComponent(new TranslationTextComponent("text.forgeblock.sword_desc.aote_0").getString()));
		list.add(new StringTextComponent(new TranslationTextComponent("text.forgeblock.sword_desc.aote_1").getString()));
		list.add(new StringTextComponent(new TranslationTextComponent("text.forgeblock.sword_desc.aote_2").getString()));
		list.add(new StringTextComponent(new TranslationTextComponent("text.forgeblock.sword_desc.aote_3").getString()));
		list.add(new StringTextComponent(new TranslationTextComponent("text.forgeblock.mana_cost").getString() + TextFormatting.DARK_AQUA.toString() + new DecimalFormat("#").format(this.getAbilityCost(stack))));
		return list;
	}

	@Override
	public boolean activateAbility(World world, PlayerEntity player, ItemStack stack) {
		Vec3d direction = player.getLookVec();
		
		int teleportDistance = 8;
		if(stack.getTag() != null) {
			if(stack.getTag().getInt("Teleport") > 0) teleportDistance = stack.getTag().getInt("Teleport");
		}
		int i;
		for(i = 1; i <= teleportDistance; i++) {
			if(!world.isAirBlock(new BlockPos(player.getPosX() + direction.x * i, player.getPosYEye() + direction.y * i, player.getPosZ() + direction.z * i))) {
				//Testing purposes only
				//worldIn.setBlockState(new BlockPos(playerIn.getPosX() + direction.x * i, playerIn.getPosY() + direction.y * i, playerIn.getPosZ() + direction.z * i), Blocks.MAGENTA_WOOL.getDefaultState());
				//playerIn.setPosition(playerIn.getPosX() + direction.x * i, playerIn.getPosY() + direction.y * i, playerIn.getPosZ() + direction.z * i);
				--i;
				break;
			}
		}
		player.setPositionAndUpdate(player.getPosX() + direction.x * i, player.getPosYEye() + direction.y * i, player.getPosZ() + direction.z * i);
		player.setVelocity(0.0d, 0.0d, 0.0d);
		player.addPotionEffect(new EffectInstance(ModEffects.ENDER_WARP_OBJECT.get(), 60, 0));
		player.playSound(SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.PLAYERS, 1.0f, 1.0f);
		return true;
	}

	@Override
	public double getAbilityCost(ItemStack stack, PlayerEntity player) {
		return player.isCreative() ? 0 : (50.0D / (100.0D + player.getAttribute(FBAttributes.MANA_EFFICIENCY).getValue()) * 100.0D) * (1 - 0.1 * EnchantmentHelper.getEnchantmentLevel(ModEnchantments.ULTIMATE_WISE.get(), stack));
	}
	
	public double getAbilityCost(ItemStack stack) {
		return 50.0D * (1 - 0.1 * EnchantmentHelper.getEnchantmentLevel(ModEnchantments.ULTIMATE_WISE.get(), stack));
	}

}
