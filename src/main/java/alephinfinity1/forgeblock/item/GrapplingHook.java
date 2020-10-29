package alephinfinity1.forgeblock.item;

import alephinfinity1.forgeblock.misc.FBItemType;
import alephinfinity1.forgeblock.misc.tier.FBTier;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public class GrapplingHook implements IFBTieredItem {

  @Override
  public FBTier getFBTier() {
    return FBTier.UNCOMMON;
  }

  @Override
  public FBTier getStackTier(ItemStack stack) {
    return FBTier.valueOf(1);
  }

  @Override
  public Rarity getRarity(ItemStack stack) {
    return Rarity.UNCOMMON;
  }

  @Override
  public void addInformation(ItemStack stack, @Nullable World worldIn,
      List<ITextComponent> tooltip, ITooltipFlag flagIn) {

  }

  @Override
  public FBItemType getFBItemType() {
    return FBItemType.GENERAL;
  }
}
