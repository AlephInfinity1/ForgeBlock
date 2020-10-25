package alephinfinity1.forgeblock.misc.reforge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;
import java.util.function.Function;

import com.google.common.collect.Multimap;

import alephinfinity1.forgeblock.attribute.ModifierHelper;
import alephinfinity1.forgeblock.init.ModRegistries;
import alephinfinity1.forgeblock.item.IFBItem;
import alephinfinity1.forgeblock.misc.FBItemType;
import alephinfinity1.forgeblock.misc.tier.FBTier;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.registries.ForgeRegistryEntry;

//See https://hypixel-skyblock.fandom.com/wiki/Reforging
public class Reforge extends ForgeRegistryEntry<Reforge> {
	
	private FBItemType[] types;
	//private String id; Use registry ID instead
	private boolean isSpecial;
	public Function<UUID, Multimap<String, AttributeModifier>> commonModifiers;
	public Function<UUID, Multimap<String, AttributeModifier>> uncommonModifiers;
	public Function<UUID, Multimap<String, AttributeModifier>> rareModifiers;
	public Function<UUID, Multimap<String, AttributeModifier>> epicModifiers;
	public Function<UUID, Multimap<String, AttributeModifier>> legendaryModifiers;
	public Function<UUID, Multimap<String, AttributeModifier>> mythicModifiers;
	public Function<UUID, Multimap<String, AttributeModifier>> supremeModifiers;
	//public String translationKey; Use registry ID instead
	
	public Reforge(FBItemType[] types, boolean isSpecial, Function<UUID, Multimap<String, AttributeModifier>> commonModifiers, Function<UUID, Multimap<String, AttributeModifier>> uncommonModifiers, Function<UUID, Multimap<String, AttributeModifier>> rareModifiers, Function<UUID, Multimap<String, AttributeModifier>> epicModifiers, Function<UUID, Multimap<String, AttributeModifier>> legendaryModifiers, Function<UUID, Multimap<String, AttributeModifier>> mythicModifiers, Function<UUID, Multimap<String, AttributeModifier>> supremeModifiers) {
		this.types = types;
		//this.id = id;
		//this.translationKey = translationKey;
		this.isSpecial = isSpecial;
		this.commonModifiers = commonModifiers;
		this.uncommonModifiers = uncommonModifiers;
		this.rareModifiers = rareModifiers;
		this.epicModifiers = epicModifiers;
		this.legendaryModifiers = legendaryModifiers;
		this.mythicModifiers = mythicModifiers;
		this.supremeModifiers = supremeModifiers;
	}
	
	public Multimap<String, AttributeModifier> getModifierMapByTier(FBTier tier, UUID uuid) {
		switch(tier) {
		case COMMON:
			return commonModifiers.apply(uuid);
		case UNCOMMON:
			return uncommonModifiers.apply(uuid);
		case RARE:
			return rareModifiers.apply(uuid);
		case EPIC:
			return epicModifiers.apply(uuid);
		case LEGENDARY:
			return legendaryModifiers.apply(uuid);
		case MYTHIC:
			return mythicModifiers.apply(uuid);
		case SUPREME:
			return supremeModifiers.apply(uuid);
		default:
			return ModifierHelper.emptyModifier();
		}
	}
	
	public boolean isSpecial() {
		return this.isSpecial;
	}
	
	public FBItemType[] getTypes() {
		return this.types;
	}
	
	public String getDisplayName() {
		return (new TranslationTextComponent("reforge." + this.getRegistryName().getNamespace() + "." + this.getRegistryName().getPath())).getString();
	}
	
	public static Reforge getRandomReforge(ItemStack stack) {
		FBItemType type = ((IFBItem) stack.getItem()).getFBItemType();
		java.util.List<Reforge> applicable = new ArrayList<>();
		for(Reforge reforge : ModRegistries.REFORGE.getValues()) {
			if((Arrays.asList(reforge.getTypes())).contains(type) && !reforge.isSpecial() && !reforge.equals(((IReforgeableItem) stack.getItem()).getReforge(stack))) {
				applicable.add(reforge);
			}
		}
		
		return applicable.get((new Random()).nextInt(applicable.size()));
	}
	
}
