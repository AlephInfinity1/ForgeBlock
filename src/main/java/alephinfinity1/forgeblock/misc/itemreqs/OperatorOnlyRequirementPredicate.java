package alephinfinity1.forgeblock.misc.itemreqs;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

public class OperatorOnlyRequirementPredicate implements IRequirementPredicate {

	@Override
	public boolean test(PlayerEntity t) {	
		return t.hasPermissionLevel(2);
	}
	
	public static OperatorOnlyRequirementPredicate isOP() {
		return new OperatorOnlyRequirementPredicate();
	}

	@Override
	public List<ITextComponent> getDisplay(boolean isMet) {
		List<ITextComponent> list = new ArrayList<>();
		
		/*
		 * \u2714: tick symbol
		 * \u2716: cross symbol
		 */
		
		if (isMet) {
			list.add(new StringTextComponent("\u2714 ").applyTextStyle(TextFormatting.GREEN).appendSibling(new TranslationTextComponent("text.forgeblock.opReq").applyTextStyle(TextFormatting.DARK_GREEN)));
		} else {
			list.add(new StringTextComponent("\u2716 ").applyTextStyle(TextFormatting.RED).appendSibling(new TranslationTextComponent("text.forgeblock.opReq").applyTextStyle(TextFormatting.RED)));
		}
		return list;
	}

}
