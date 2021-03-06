package alephinfinity1.forgeblock.item.swords;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import alephinfinity1.forgeblock.ForgeBlock;
import alephinfinity1.forgeblock.attribute.FBAttributes;
import alephinfinity1.forgeblock.init.ModEffects;
import alephinfinity1.forgeblock.init.ModEnchantments;
import alephinfinity1.forgeblock.init.ModItems;
import alephinfinity1.forgeblock.item.armor.StrongDragonArmorItem;
import alephinfinity1.forgeblock.misc.ability.AbilityResultType;
import alephinfinity1.forgeblock.misc.capability.mana.IMana;
import alephinfinity1.forgeblock.misc.capability.mana.ManaProvider;
import alephinfinity1.forgeblock.misc.tier.FBTier;
import alephinfinity1.forgeblock.network.FBPacketHandler;
import alephinfinity1.forgeblock.network.ManaUpdatePacket;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
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
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.PacketDistributor;

@Mod.EventBusSubscriber
public class AspectOfTheEnderItem extends AspectOfTheEndItem {

	public AspectOfTheEnderItem(Item.Properties props, FBTier tier, double attackDamageIn, double strengthIn,
                                double critChanceIn, double critDamageIn) {
		super(props, tier, attackDamageIn, strengthIn, critChanceIn, critDamageIn);
	}
	
	@Override
	public List<ITextComponent> abilityDescription(ItemStack stack) {
		List<ITextComponent> list = new ArrayList<>();
		list.add(new StringTextComponent(new TranslationTextComponent("text.forgeblock.sword_desc.strongaote_0").getString()));
		list.add(new StringTextComponent(new TranslationTextComponent("text.forgeblock.sword_desc.strongaote_1").getString()));
		list.add(new StringTextComponent(new TranslationTextComponent("text.forgeblock.sword_desc.strongaote_2").getString()));
		list.add(new StringTextComponent(new TranslationTextComponent("text.forgeblock.sword_desc.strongaote_3").getString()));
		list.add(new StringTextComponent(new TranslationTextComponent("text.forgeblock.mana_cost", new DecimalFormat("#").format(this.getAbilityCost(stack, ForgeBlock.MINECRAFT.player))).getString()));
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
			playerIn.sendStatusMessage(new StringTextComponent(new TranslationTextComponent("text.forgeblock.useAbility.strongaote").getString() + TextFormatting.AQUA.toString() + " (" + new DecimalFormat("#").format(this.getAbilityCost(stack, playerIn)) + " " + new TranslationTextComponent("misc.forgeblock.mana").getString() + ")"), true);
			return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
		}
		playerIn.sendStatusMessage(new StringTextComponent(new TranslationTextComponent("text.forgeblock.notEnoughMana").getString()), true);
		return ActionResult.resultFail(playerIn.getHeldItem(handIn));
	}
	
	@Override
	public AbilityResultType activateAbility(World world, PlayerEntity player, ItemStack stack) {
		Vec3d direction = player.getLookVec();
		
		int teleportDistance = 10;
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
		player.addPotionEffect(new EffectInstance(ModEffects.ENDER_WARP_OBJECT.get(), 120, 0));
		player.playSound(SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.PLAYERS, 1.0f, 1.0f);
		return AbilityResultType.SUCCESS;
	}
	
	@Override
	public double getAbilityCost(ItemStack stack, PlayerEntity player) {
		if(player == null) return this.getAbilityCost(stack);
		else return player.isCreative() ? 0 : (40.0D / (100.0D + player.getAttribute(FBAttributes.MANA_EFFICIENCY).getValue()) * 100.0D) * (1 - 0.1 * EnchantmentHelper.getEnchantmentLevel(ModEnchantments.ULTIMATE_WISE.get(), stack));
	}
	
	@Override
	public double getAbilityCost(ItemStack stack) {
		return 40.0D * (1 - 0.1 * EnchantmentHelper.getEnchantmentLevel(ModEnchantments.ULTIMATE_WISE.get(), stack));
	}
	
	@SubscribeEvent
	public static void onLivingUpdate(LivingUpdateEvent event) {
		LivingEntity living = event.getEntityLiving();
		Iterable<ItemStack> armor = living.getArmorInventoryList();
		for(ItemStack item : armor) {
			if(!(item.getItem() instanceof StrongDragonArmorItem) && living.getHeldItemMainhand().getItem() instanceof AspectOfTheEnderItem) {
				ItemStack aoteItem = new ItemStack(ModItems.ASPECT_OF_THE_END.get());
				CompoundNBT tag = aoteItem.getOrCreateTag();
				tag.merge(living.getHeldItemMainhand().getOrCreateTag());
				living.setHeldItem(Hand.MAIN_HAND, aoteItem);
				return;
			}
		}
	}

}
