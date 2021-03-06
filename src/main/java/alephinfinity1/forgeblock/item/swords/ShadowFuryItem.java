package alephinfinity1.forgeblock.item.swords;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import alephinfinity1.forgeblock.item.IAbilityItem;
import alephinfinity1.forgeblock.misc.ability.AbilityResultType;
import com.google.common.collect.Multimap;

import alephinfinity1.forgeblock.ForgeBlock;
import alephinfinity1.forgeblock.init.ModEffects;
import alephinfinity1.forgeblock.misc.TickHandler;
import alephinfinity1.forgeblock.misc.event.FBEventHooks;
import alephinfinity1.forgeblock.misc.event.PlayerCastSpellEvent;
import alephinfinity1.forgeblock.misc.capability.mana.IMana;
import alephinfinity1.forgeblock.misc.capability.mana.ManaProvider;
import alephinfinity1.forgeblock.misc.tier.FBTier;
import alephinfinity1.forgeblock.network.FBPacketHandler;
import alephinfinity1.forgeblock.network.ManaUpdatePacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.Hand;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.PacketDistributor;

public class ShadowFuryItem extends FBSwordItem implements IAbilityItem {
	
	public static final UUID SHADOW_FURY_MODIFIER = UUID.fromString("aeb3f44b-e157-49fa-95b1-520951cf855b");

	public ShadowFuryItem(Properties props, FBTier tier, double attackDamageIn, double strengthIn, double critChanceIn,
			double critDamageIn) {
		super(props, tier, attackDamageIn, strengthIn, critChanceIn, critDamageIn);	
	}
	
	public ShadowFuryItem(Properties props, FBTier tier, Multimap<String, AttributeModifier> modifiers) {
		super(props, tier, modifiers);
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
		PlayerCastSpellEvent event = FBEventHooks.onPlayerCastSpell(playerIn, stack, this.getAbilityCost(stack, playerIn));
		if(playerIn.getCapability(ManaProvider.MANA_CAPABILITY).orElseThrow(() -> new NullPointerException()).consume(event.getManaConsumed()) && !event.isCanceled()) {
			AbilityResultType flag = activateAbility(worldIn, playerIn, stack);
			if(flag.equals(AbilityResultType.SUCCESS)) {
				IMana mana = playerIn.getCapability(ManaProvider.MANA_CAPABILITY).orElseThrow(NullPointerException::new);
				FBPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) playerIn), new ManaUpdatePacket(mana.getMana()));
				playerIn.sendStatusMessage(new StringTextComponent(new TranslationTextComponent(this.getUnlocalizedUseAbilityName()).getString() + TextFormatting.AQUA.toString() + " (" + new DecimalFormat("#").format(event.getManaConsumed()) + " " + new TranslationTextComponent("misc.forgeblock.mana").getString() + ")"), true);
				return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
			} else {
				playerIn.sendStatusMessage(new TranslationTextComponent("text.forgeblock.noTarget"), true);
				return ActionResult.resultFail(playerIn.getHeldItem(handIn));
			}
		}
		playerIn.sendStatusMessage(new StringTextComponent(new TranslationTextComponent("text.forgeblock.notEnoughMana").getString()), true);
		return ActionResult.resultFail(playerIn.getHeldItem(handIn));
	}

	@Override
	public List<ITextComponent> abilityDescription(ItemStack stack) {
		List<ITextComponent> list = new ArrayList<>(); 
		list.add(new TranslationTextComponent("text.forgeblock.sword_desc.shadowfury_0"));
		list.add(new TranslationTextComponent("text.forgeblock.sword_desc.shadowfury_1"));
		list.add(new TranslationTextComponent("text.forgeblock.sword_desc.shadowfury_2"));
		list.add(new TranslationTextComponent("text.forgeblock.sword_desc.shadowfury_3"));
		list.add(new TranslationTextComponent("text.forgeblock.sword_desc.shadowfury_4"));
		list.add(new StringTextComponent(new TranslationTextComponent("text.forgeblock.cooldown", new DecimalFormat("#.##").format(this.getCooldown(stack, ForgeBlock.MINECRAFT.player) / 20.0d)).getString()));
		return list;
	}

	@Override
	public AbilityResultType activateAbility(World world, PlayerEntity player, ItemStack stack) {
		AxisAlignedBB bound = new AxisAlignedBB(player.getPositionVec().add(-12.0d, -2.0d, -12.0d), player.getPositionVec().add(12.0d, 2.0d, 12.0d));
		List<Entity> list = world.getEntitiesInAABBexcluding(player, bound, EntityPredicates.NOT_SPECTATING);
		List<Entity> accepted = list.stream().filter((entity) -> entity.isAlive() && entity instanceof LivingEntity && entity.getDistanceSq(player) <= 144.0f).collect(Collectors.toList()); //Only alive living entities should be targetted by this ability.
		
		//If no entities are in acceptable range, the ability fails.
		if(accepted.isEmpty()) return AbilityResultType.NO_TARGET;
		
		//Shuffle the list of accepted entities, and select 5 of them to cast the attack on (randomly).
		Collections.shuffle(accepted);
		player.addPotionEffect(new EffectInstance(ModEffects.IMMUNITY, 10 * ((accepted.size() > 5) ? 5 : accepted.size()), 0, true, true));
		for(int i = 0; i < (accepted.size() > 5 ? 5 : accepted.size()); i++) {
			int ticksAfter = 10 * i + 5;
			Tuple<LivingEntity, LivingEntity> targets = new Tuple<>(player, (LivingEntity) accepted.get(i));
			TickHandler.shadowFuryTarget.put(targets, TickHandler.serverTicksElapsed + ticksAfter);
		}
		
		player.getCooldownTracker().setCooldown(this, getCooldown(stack, player));	
		return AbilityResultType.SUCCESS;
	}

	//This isn't no-op, the ability costs 0.
	@Override
	public double getAbilityCost(ItemStack stack, PlayerEntity player) {
		if(Objects.isNull(player)) return this.getAbilityCost(stack);
		return 0;
	}

	@Override
	public int getCooldown(ItemStack stack) {
		return 300;
	}

	@Override
	public int getCooldown(ItemStack stack, PlayerEntity player) {
		if(Objects.isNull(player)) return this.getCooldown(stack);
		return 300;
	}

	@Override
	public double getAbilityCost(ItemStack stack) {
		return 0;
	}

	@Override
	public String getUnlocalizedUseAbilityName() {
		return "text.forgeblock.useAbility.shadowfury";
	}

}
