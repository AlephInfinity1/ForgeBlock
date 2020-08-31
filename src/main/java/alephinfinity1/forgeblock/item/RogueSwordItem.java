package alephinfinity1.forgeblock.item;

import alephinfinity1.forgeblock.init.ModEffects;
import alephinfinity1.forgeblock.misc.tier.FBTier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class RogueSwordItem extends FBSwordItem {

	public RogueSwordItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties p_i48460_4_) {
		super(tier, attackDamageIn, attackSpeedIn, p_i48460_4_);
	}
	
	public RogueSwordItem(Properties props, FBTier tier, double attackDamageIn, double strengthIn, double critChanceIn, double critDamageIn) {
		super(props, tier, attackDamageIn, strengthIn, critChanceIn, critDamageIn);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		playerIn.addPotionEffect(new EffectInstance(Effects.SPEED, 600, 0));
		return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
	}

}
