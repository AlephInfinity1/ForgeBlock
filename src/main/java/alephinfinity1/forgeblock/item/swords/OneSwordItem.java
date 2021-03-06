package alephinfinity1.forgeblock.item.swords;

import java.util.UUID;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import com.google.common.collect.Multimap;

import alephinfinity1.forgeblock.attribute.FBAttributes;
import alephinfinity1.forgeblock.misc.tier.FBTier;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;

/*
 * Debug item. Used to test display.
 */
public class OneSwordItem extends FBSwordItem {
	
	private static final Multimap<String, AttributeModifier> MODIFIER_MAP;
	private static final UUID MODIFIER_UUID = UUID.fromString("284050e7-8f46-4b76-8550-dc6f9baaaf96");
	
	static {
		Builder<String, AttributeModifier> builder = ImmutableMultimap.builder();
		/*
		for(IAttribute attribute : FBAttributes.PRIMARY_ATTRIBUTES) {
			builder.put(attribute.getName(), new AttributeModifier(MODIFIER_UUID, "Debug", 1, Operation.ADDITION));
		}
		for(IAttribute attribute : FBAttributes.EXTRA_ATTRIBUTES) {
			builder.put(attribute.getName(), new AttributeModifier(MODIFIER_UUID, "Debug", 1, Operation.ADDITION));
		}
		for(IAttribute attribute : FBAttributes.SKILL_XP_BOOSTS) {
			builder.put(attribute.getName(), new AttributeModifier(MODIFIER_UUID, "Debug", 1, Operation.ADDITION));
		}
		for(IAttribute attribute : FBAttributes.SLAYER_LUCKS) {
			builder.put(attribute.getName(), new AttributeModifier(MODIFIER_UUID, "Debug", 1, Operation.ADDITION));
		}
		*/
		builder.put(FBAttributes.COMBAT_XP_BOOST.getName(), new AttributeModifier(MODIFIER_UUID, "Debug", 100, Operation.ADDITION));
		
		MODIFIER_MAP = builder.build();
	}

	public OneSwordItem(Properties props, FBTier tier, double attackDamageIn, double strengthIn, double critChanceIn,
			double critDamageIn) {
		super(props, tier, attackDamageIn, strengthIn, critChanceIn, critDamageIn);
	}
	
	public OneSwordItem(Properties props, FBTier tier, Multimap<String, AttributeModifier> attributes) {
		super(props, tier, attributes);
	}
	
	public OneSwordItem(Properties props, FBTier tier) {
		super(props, tier, MODIFIER_MAP);
	}

}
