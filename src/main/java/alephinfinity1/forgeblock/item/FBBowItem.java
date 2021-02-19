package alephinfinity1.forgeblock.item;

import alephinfinity1.forgeblock.attribute.FBAttributes;
import alephinfinity1.forgeblock.attribute.ModifierHelper;
import alephinfinity1.forgeblock.init.ModRegistries;
import alephinfinity1.forgeblock.misc.stats_modifier.capability.IItemModifiers;
import alephinfinity1.forgeblock.misc.stats_modifier.capability.ItemModifiersProvider;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;

import alephinfinity1.forgeblock.misc.FBItemType;
import alephinfinity1.forgeblock.misc.reforge.IReforgeableItem;
import alephinfinity1.forgeblock.misc.reforge.Reforge;
import alephinfinity1.forgeblock.misc.tier.FBTier;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.UUID;

import static alephinfinity1.forgeblock.item.FBSwordItem.SWORD_REFORGE_MODIFIER;

public class FBBowItem extends BowItem implements IReforgeableItem, IFBTieredItem {
    private final FBTier tier;
    private final Multimap<String, AttributeModifier> attributes;
    private final double drawSpeed;

    protected static final UUID BOW_STRENGTH_MODIFIER = UUID.fromString("f1c2a376-481b-43d7-a257-7a2f38126500");
    protected static final UUID BOW_CRIT_CHANCE_MODIFIER = UUID.fromString("53305ff6-a0dd-4039-a2ad-653c74786f7d");
    protected static final UUID BOW_CRIT_DAMAGE_MODIFIER = UUID.fromString("bfa8ab47-7aeb-4930-a3de-f45e0cca76a6");

    protected static final UUID BOW_REFORGE_MODIFIER = UUID.fromString("71b440d6-9a6a-49ad-b3c3-4a4fb03a8afb");

    public FBBowItem(Properties props, FBTier tier, double attackDamageIn, double strengthIn, double critChanceIn, double critDamageIn, double drawSpeedIn) {
        super(props);
        this.tier = tier;
        ImmutableMultimap.Builder<String, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Attack damage modifier", attackDamageIn, AttributeModifier.Operation.ADDITION));
        builder.put(FBAttributes.STRENGTH.getName(), new AttributeModifier(BOW_STRENGTH_MODIFIER, "Strength modifier", strengthIn, AttributeModifier.Operation.ADDITION));
        builder.put(FBAttributes.CRIT_CHANCE.getName(), new AttributeModifier(BOW_CRIT_CHANCE_MODIFIER, "Crit chance modifier", critChanceIn, AttributeModifier.Operation.ADDITION));
        builder.put(FBAttributes.CRIT_DAMAGE.getName(), new AttributeModifier(BOW_CRIT_DAMAGE_MODIFIER, "Crit damage modifier", critDamageIn, AttributeModifier.Operation.ADDITION));
        attributes = builder.build();
        this.drawSpeed = drawSpeedIn;
    }

    @Override
    public FBItemType getFBItemType() {
        return FBItemType.BOW;
    }

    @Override
    public FBTier getFBTier() {
        return this.tier;
    }

    @Override
    public FBTier getStackTier(ItemStack stack) {
        if(stack.getTag() != null) {
            IItemModifiers itemMod = stack.getCapability(ItemModifiersProvider.ITEM_MODIFIERS_CAPABILITY).orElse(null);
            if(itemMod != null) {
                return FBTier.changeTier(tier, itemMod.getRarity(stack));
            }
            return tier;
        } else {
            return tier;
        }
    }

    @Override
    public Reforge getReforge(ItemStack stack) {
        if(stack.getTag() == null) return null;
        String reforgeName = stack.getTag().getString("Reforge");
        if(reforgeName.isEmpty()) return null;
        else return ModRegistries.REFORGE.getValue(new ResourceLocation(reforgeName));
    }

    @Override
    public void setReforge(Reforge reforge, ItemStack stack) {
        stack.getOrCreateTag().putString("Reforge", ModRegistries.REFORGE.getKey(reforge).toString());
    }

    @Override
    public Multimap<String, AttributeModifier> getReforgeModifiers(ItemStack stack) {
        if(getReforge(stack) == null) return ModifierHelper.emptyModifier();
        else { 
            Reforge reforge = getReforge(stack);
            return reforge.getModifierMapByTier(getStackTier(stack), BOW_REFORGE_MODIFIER);
        }
    }

}
