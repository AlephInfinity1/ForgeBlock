package alephinfinity1.forgeblock.misc.capability.stats_modifier;

import java.util.UUID;

import alephinfinity1.forgeblock.attribute.ModifierHelper;
import alephinfinity1.forgeblock.item.IFBItem;
import alephinfinity1.forgeblock.misc.FBItemType;
import net.minecraft.util.text.TextFormatting;

public class HotPotatoBookModifier extends AbstractStatsModifier {
	
	private static final UUID OFFENSIVE_UUID = UUID.fromString("cd3be0c4-149e-40b4-bef6-30e02e742892");
	private static final UUID HELMET_UUID = UUID.fromString("4707e16f-97da-4973-9b08-ac2455ce707c");
	private static final UUID CHESTPLATE_UUID = UUID.fromString("73cd7cfd-14d9-4e6c-95a2-8933356fd1b3");
	private static final UUID LEGGINGS_UUID = UUID.fromString("d6741996-8a9f-442c-96bb-942a0cbc79d2");
	private static final UUID BOOTS_UUID = UUID.fromString("c45a525d-4327-499e-b9c7-f807aec7f521");	
	
	public HotPotatoBookModifier() {
		super(
				null,
				(stack, nbt) -> 0,
				TextFormatting.YELLOW,
				(stack) -> {
					if(!(stack.getItem() instanceof IFBItem)) return false;
					else {
						IFBItem item = (IFBItem) stack.getItem();
						FBItemType[] acceptableTypes = new FBItemType[] {
								FBItemType.SWORD,
								FBItemType.BOW,
								FBItemType.PICKAXE,
								FBItemType.HELMET,
								FBItemType.CHESTPLATE,
								FBItemType.LEGGINGS,
								FBItemType.BOOTS
						};
						for(FBItemType type : acceptableTypes) {
							if(item.getFBItemType().equals(type)) return true;
						}
						return false;
					}
				}
				);
		this.attributeModifiers = (stack, nbt) -> {
					if(!(stack.getItem() instanceof IFBItem)) return ModifierHelper.emptyModifier();
					else {
						IFBItem item = (IFBItem) stack.getItem();
						int amount = nbt.getShort("Amount") + nbt.getShort("FumingAmount");
						if(item.getFBItemType().equals(FBItemType.SWORD)
								|| item.getFBItemType().equals(FBItemType.BOW)
								|| item.getFBItemType().equals(FBItemType.PICKAXE)) { //Offensive items
							return ModifierHelper.modifierMapFromDoubles(2 * amount, 2 * amount, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, OFFENSIVE_UUID);
						} else { //Defensive items
							FBItemType type = item.getFBItemType();
							if(type.equals(FBItemType.HELMET)) return ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 4 * amount, 2 * amount, 0, 0, 0, 0, 0, HELMET_UUID);
							else if(type.equals(FBItemType.CHESTPLATE)) return ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 4 * amount, 2 * amount, 0, 0, 0, 0, 0, CHESTPLATE_UUID);
							else if(type.equals(FBItemType.LEGGINGS)) return ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 4 * amount, 2 * amount, 0, 0, 0, 0, 0, LEGGINGS_UUID);
							else if(type.equals(FBItemType.BOOTS)) return ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 4 * amount, 2 * amount, 0, 0, 0, 0, 0, BOOTS_UUID);
							else return ModifierHelper.emptyModifier();
						}
					}
				};
	}

}
