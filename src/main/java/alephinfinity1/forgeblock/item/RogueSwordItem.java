package alephinfinity1.forgeblock.item;

import java.util.UUID;

import alephinfinity1.forgeblock.misc.TickHandler;
import alephinfinity1.forgeblock.misc.tier.FBTier;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Tuple;
import net.minecraft.world.World;

public class RogueSwordItem extends FBSwordItem {	
	
	public RogueSwordItem(Properties props, FBTier tier, double attackDamageIn, double strengthIn, double critChanceIn, double critDamageIn) {
		super(props, tier, attackDamageIn, strengthIn, critChanceIn, critDamageIn);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		UUID uuid = UUID.randomUUID();
		playerIn.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).applyModifier(new AttributeModifier(uuid, "Rogue Sword Speed Boost", 0.02, Operation.ADDITION));
		TickHandler.attModifierExpiry.put(new Tuple<LivingEntity, UUID>(playerIn, uuid), TickHandler.ticksElapsed + 600);
		return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
	}

}
