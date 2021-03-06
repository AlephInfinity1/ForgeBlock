package alephinfinity1.forgeblock.item.swords;

import java.util.ArrayList;
import java.util.List;

import alephinfinity1.forgeblock.misc.tier.FBTier;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class SpiderSwordItem extends FBSwordItem {

	public SpiderSwordItem(Properties props, FBTier tier, double attackDamageIn, double strengthIn, double critChanceIn,
			double critDamageIn) {
		super(props, tier, attackDamageIn, strengthIn, critChanceIn, critDamageIn);
	}
	
	@Override
	public List<ITextComponent> additionalInformation() {
		List<ITextComponent> list = new ArrayList<>();
		list.add(new TranslationTextComponent("text.forgeblock.sword_desc.spider_0"));
		list.add(new TranslationTextComponent("text.forgeblock.sword_desc.spider_1"));
		list.add(new TranslationTextComponent("text.forgeblock.sword_desc.spider_2"));
		list.add(new StringTextComponent(""));
		return list;
	}

}
