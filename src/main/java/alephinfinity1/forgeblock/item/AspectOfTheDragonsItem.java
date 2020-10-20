package alephinfinity1.forgeblock.item;

import static alephinfinity1.forgeblock.ForgeBlock.MINECRAFT;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import alephinfinity1.forgeblock.attribute.FBAttributes;
import alephinfinity1.forgeblock.init.ModEnchantments;
import alephinfinity1.forgeblock.misc.mana.IMana;
import alephinfinity1.forgeblock.misc.mana.ManaProvider;
import alephinfinity1.forgeblock.misc.tier.FBTier;
import alephinfinity1.forgeblock.network.FBPacketHandler;
import alephinfinity1.forgeblock.network.ManaUpdatePacket;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.PacketDistributor;

public class AspectOfTheDragonsItem extends FBSwordItem implements IAbilityItem {

	public AspectOfTheDragonsItem(Properties props, FBTier tier, double attackDamageIn, double strengthIn,
			double critChanceIn, double critDamageIn) {
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
			IMana mana = playerIn.getCapability(ManaProvider.MANA_CAPABILITY).orElseThrow(NullPointerException::new);
			FBPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) playerIn), new ManaUpdatePacket(mana.getMana()));
			playerIn.sendStatusMessage(new StringTextComponent(new TranslationTextComponent("text.forgeblock.useAbility.aotd").getString() + TextFormatting.AQUA.toString() + " (" + new DecimalFormat("#").format(this.getAbilityCost(stack, playerIn)) + " " + new TranslationTextComponent("misc.forgeblock.mana").getString() + ")"), true);
			return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
		}
		playerIn.sendStatusMessage(new StringTextComponent(new TranslationTextComponent("text.forgeblock.notEnoughMana").getString()), true);
		return ActionResult.resultFail(playerIn.getHeldItem(handIn));
	}

	@Override
	public List<ITextComponent> abilityDescription(ItemStack stack) {
		List<ITextComponent> list = new ArrayList<>();
		list.add(new StringTextComponent(new TranslationTextComponent("text.forgeblock.sword_desc.aotd_0").getString()));
		list.add(new StringTextComponent(new TranslationTextComponent("text.forgeblock.sword_desc.aotd_1").getString()));
		list.add(new StringTextComponent(new TranslationTextComponent("text.forgeblock.sword_desc.aotd_2", new DecimalFormat(",###").format(this.getAbilityDamage(stack, MINECRAFT.player)).replaceAll("\u00A0", ",")).getString()));
		list.add(new StringTextComponent(new TranslationTextComponent("text.forgeblock.sword_desc.aotd_3").getString()));
		list.add(new StringTextComponent(new TranslationTextComponent("text.forgeblock.mana_cost", new DecimalFormat("#").format(this.getAbilityCost(stack, MINECRAFT.player))).getString()));
		list.add(new StringTextComponent(new TranslationTextComponent("text.forgeblock.cooldown", new DecimalFormat("#.##").format(this.getCooldown(stack, MINECRAFT.player) / 20.0d)).getString()));
		return list;
	}

	@Override
	public boolean activateAbility(World world, PlayerEntity player, ItemStack stack) {
		AxisAlignedBB bound = new AxisAlignedBB(player.getPositionVector().add(player.getLookVec().rotateYaw(90.0f).rotatePitch(45.0f).scale(2).add(0, -5, 0)), player.getPositionVector().add(player.getLookVec().scale(8.0)).add(player.getLookVec().rotateYaw(90.0f).rotatePitch(45.0f).scale(-2).add(0, 5, 0)));
		
		/*
		world.setBlockState(new BlockPos(player.getPositionVector().add(player.getLookVec().rotateYaw(90.0f).rotatePitch(45.0f).scale(2))), Blocks.MAGENTA_WOOL.getDefaultState());
		world.setBlockState(new BlockPos(player.getPositionVector().add(player.getLookVec().scale(8.0)).add(player.getLookVec().rotateYaw(90.0f).rotatePitch(45.0f).scale(-2))), Blocks.MAGENTA_WOOL.getDefaultState());
		*/
		
		List<Entity> list = world.getEntitiesInAABBexcluding(player, bound, EntityPredicates.NOT_SPECTATING);
		for(Entity entity : list) {
			if(entity instanceof LivingEntity) {
				LivingEntity living = (LivingEntity) entity;
				living.attackEntityFrom(DamageSource.causeIndirectMagicDamage(player, player), this.getAbilityDamage(stack, player));
				living.setVelocity(player.getLookVec().getX() * 4.0, player.getLookVec().getY() * 1.5, player.getLookVec().getZ() * 4.0);
			}
		}
				
		player.playSound(SoundEvents.ENTITY_ENDER_DRAGON_GROWL, SoundCategory.PLAYERS, 1.0f, 1.0f);
		player.getCooldownTracker().setCooldown(this, getCooldown(stack, player));
		return true;
	}

	@Override
	public double getAbilityCost(ItemStack stack, PlayerEntity player) {
		if(player == null) return getAbilityCost(stack);
		return player.isCreative() ? 0 : (100.0D / (100.0D + player.getAttribute(FBAttributes.MANA_EFFICIENCY).getValue()) * 100.0D) * (1 - 0.1 * EnchantmentHelper.getEnchantmentLevel(ModEnchantments.ULTIMATE_WISE.get(), stack));
	}
	
	public double getAbilityCost(ItemStack stack) {
		return 100.0D * (1 - 0.1 * EnchantmentHelper.getEnchantmentLevel(ModEnchantments.ULTIMATE_WISE.get(), stack));
	}
	
	public float getAbilityDamage(ItemStack stack) {
		return 12000;
	}
	
	public float getAbilityDamage(ItemStack stack, PlayerEntity player) {
		if(player == null) return getAbilityDamage(stack);
		return (float) (12000 * (1 + 0.001 * player.getAttribute(FBAttributes.INTELLIGENCE).getValue()));
	}

	@Override
	public int getCooldown(ItemStack stack) {
		return 10;
	}

	@Override
	public int getCooldown(ItemStack stack, PlayerEntity player) {
		if(player == null) return getCooldown(stack);
		return (int) (getCooldown(stack) * (1 - player.getAttribute(FBAttributes.COOLDOWN_REDUCTION).getValue() / 100.0d) - player.getAttribute(FBAttributes.RAW_COOLDOWN_REDUCTION).getValue());
	}

}
