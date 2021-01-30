package alephinfinity1.forgeblock.item;

import alephinfinity1.forgeblock.attribute.FBAttributes;
import alephinfinity1.forgeblock.misc.tier.FBTier;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class TestArmorItem extends FBArmorItem {
    private static final Multimap<String, AttributeModifier> attributes;
    static {
        ImmutableMultimap.Builder<String, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(FBAttributes.INTELLIGENCE.toString(), new AttributeModifier("Hello world", 75, AttributeModifier.Operation.ADDITION));
        builder.put(FBAttributes.INTELLIGENCE.toString(), new AttributeModifier("Hello world", -75, AttributeModifier.Operation.ADDITION));
        attributes = builder.build();
    }

    public TestArmorItem(EquipmentSlotType slot, String name, Properties props, FBTier tier) {
        super(slot, name, props, tier, 3, 4);
    }

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot, ItemStack stack) {
        return attributes;
    }
}
