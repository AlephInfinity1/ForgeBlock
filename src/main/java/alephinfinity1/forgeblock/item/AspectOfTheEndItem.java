package alephinfinity1.forgeblock.item;

import java.util.ArrayList;
import java.util.List;

import alephinfinity1.forgeblock.init.ModEffects;
import alephinfinity1.forgeblock.misc.tier.FBTier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class AspectOfTheEndItem extends FBSwordItem {
	}
	
	public AspectOfTheEndItem(Properties props, FBTier tier, double attackDamageIn, double strengthIn, double critChanceIn, double critDamageIn) {
		super(props, tier, attackDamageIn, strengthIn, critChanceIn, critDamageIn);
	}
	
	@Override
	public List<ITextComponent> additionalInformation() {
		List<ITextComponent> list = new ArrayList<>();
		list.add(new StringTextComponent(new TranslationTextComponent("text.forgeblock.sword_desc.aote_0").getString()));
		list.add(new StringTextComponent(new TranslationTextComponent("text.forgeblock.sword_desc.aote_1").getString()));
		list.add(new StringTextComponent(new TranslationTextComponent("text.forgeblock.sword_desc.aote_2").getString()));
		list.add(new StringTextComponent(new TranslationTextComponent("text.forgeblock.sword_desc.aote_3").getString()));
		list.add(new StringTextComponent(new TranslationTextComponent("text.forgeblock.sword_desc.aote_4").getString()));
		list.add(new StringTextComponent(new TranslationTextComponent("text.forgeblock.sword_desc.aote_5").getString()));
		return list;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		Vec3d direction = playerIn.getLookVec();
		ItemStack stack = playerIn.getHeldItem(handIn);
		int teleportDistance = 8;
		if(stack.getTag() != null) {
			if(stack.getTag().getInt("Teleport") > 0) teleportDistance = stack.getTag().getInt("Teleport");
		}
		int i;
		for(i = 0; i <= teleportDistance; i++) {
			if(!worldIn.isAirBlock(new BlockPos(playerIn.getPosX() + direction.x * i, playerIn.getPosYEye() + direction.y * i, playerIn.getPosZ() + direction.z * i))) {
				//Testing purposes only
				//worldIn.setBlockState(new BlockPos(playerIn.getPosX() + direction.x * i, playerIn.getPosY() + direction.y * i, playerIn.getPosZ() + direction.z * i), Blocks.MAGENTA_WOOL.getDefaultState());
				//playerIn.setPosition(playerIn.getPosX() + direction.x * i, playerIn.getPosY() + direction.y * i, playerIn.getPosZ() + direction.z * i);
				i--;
				break;
			}
		}
		playerIn.setPosition(playerIn.getPosX() + direction.x * i, playerIn.getPosYEye() + direction.y * i, playerIn.getPosZ() + direction.z * i);
		playerIn.setVelocity(0.0d, 0.0d, 0.0d);
		playerIn.addPotionEffect(new EffectInstance(ModEffects.ENDER_WARP_OBJECT.get(), 60, 0));
		return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
	}

}
