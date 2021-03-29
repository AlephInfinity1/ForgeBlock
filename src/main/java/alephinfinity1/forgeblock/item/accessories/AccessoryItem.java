package alephinfinity1.forgeblock.item.accessories;

import alephinfinity1.forgeblock.attribute.ModifierHelper;
import alephinfinity1.forgeblock.item.IFBTieredItem;
import alephinfinity1.forgeblock.misc.FBItemType;
import alephinfinity1.forgeblock.misc.capability.accessories.AccessoriesProvider;
import alephinfinity1.forgeblock.misc.capability.accessories.IAccessoriesData;
import alephinfinity1.forgeblock.misc.capability.stats_modifier.capability.IItemModifiers;
import alephinfinity1.forgeblock.misc.capability.stats_modifier.capability.ItemModifiersProvider;
import alephinfinity1.forgeblock.misc.reforge.IReforgeableItem;
import alephinfinity1.forgeblock.misc.reforge.Reforge;
import alephinfinity1.forgeblock.misc.tier.FBTier;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.UUID;

public class AccessoryItem extends Item implements IAccessoryItem, IReforgeableItem, IFBTieredItem {
    protected final FBTier tier;
    protected final Multimap<String, AttributeModifier> attributes;

    /**
     * Create a new AccessoryItem without any attributes
     * @param properties The item properties
     * @param tier The tier of this accessory
     */
    public AccessoryItem(Properties properties, FBTier tier) {
        super(properties);
        this.tier = tier;
        this.attributes = ImmutableMultimap.of();
    }

    /**
     * Create a new AccessoryItem with attributes
     * @param properties The item properties
     * @param tier The tier of this accessory
     */
    public AccessoryItem(Properties properties, FBTier tier, Multimap<String, AttributeModifier> attributes) {
        super(properties);
        this.tier = tier;
        this.attributes = attributes;
    }

    public boolean hasAccessory(PlayerEntity player) {
        IAccessoriesData accessoriesData = player.getCapability(AccessoriesProvider.ACCESSORIES_CAPABILITY).orElse(null);
        return accessoriesData != null && accessoriesData.isPresent(this);
    }

    @Override
    public FBItemType getFBItemType() {
        return FBItemType.ACCESSORY;
    }

    @Override
    public FBTier getFBTier() {
        return this.tier;
    }

    @Override
    public FBTier getStackTier(ItemStack stack) {
        if (stack.getTag() != null) {
            IItemModifiers itemMod = stack.getCapability(ItemModifiersProvider.ITEM_MODIFIERS_CAPABILITY).orElse(null);
            if(itemMod != null) {
                return FBTier.changeTier(tier, itemMod.getRarity(stack));
            }
        }
        return tier;
    }

    @Override
    public Multimap<String, AttributeModifier> getReforgeModifiers(ItemStack stack) {
        if (getReforge(stack) == null) {
            return ModifierHelper.emptyModifier();
        } else {
            Reforge reforge = getReforge(stack);
            return reforge.getModifierMapByTier(getStackTier(stack), UUID.randomUUID());
        }
    }
}
