package alephinfinity1.forgeblock.item;

import java.util.ArrayList;
import java.util.List;

import alephinfinity1.forgeblock.misc.tier.FBTier;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class SwordOfTheStarsItem extends FBSwordItem {

	public SwordOfTheStarsItem(Properties props, FBTier tier, double attackDamageIn, double strengthIn,
			double critChanceIn, double critDamageIn) {
		super(props, tier, attackDamageIn, strengthIn, critChanceIn, critDamageIn);
	}
	
	@Override
	public List<ITextComponent> additionalInformation() {
		List<ITextComponent> list = new ArrayList<>();
		list.add(new StringTextComponent(new TranslationTextComponent("text.forgeblock.sword_desc.sots_0").getString()));
		list.add(new StringTextComponent(new TranslationTextComponent("text.forgeblock.sword_desc.sots_1").getString()));
		list.add(new StringTextComponent(new TranslationTextComponent("text.forgeblock.sword_desc.sots_2").getString()));
		list.add(new StringTextComponent(""));
		return list;
	}
	
	/*
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		if(worldIn.isRemote) {
			Minecraft.getInstance().displayGuiScreen(new GuiMenu(new StringTextComponent("GUI Test")));
		}
		
		return ActionResult.resultSuccess(playerIn.getHeldItemMainhand());
	}
	*/

}
