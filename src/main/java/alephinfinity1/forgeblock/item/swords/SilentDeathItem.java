package alephinfinity1.forgeblock.item.swords;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import alephinfinity1.forgeblock.item.IAbilityItem;
import alephinfinity1.forgeblock.item.IQualityItem;
import alephinfinity1.forgeblock.misc.ability.AbilityResultType;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import com.google.common.collect.Multimap;
import com.google.common.util.concurrent.AtomicDouble;

import alephinfinity1.forgeblock.attribute.FBAttributes;
import alephinfinity1.forgeblock.init.ModEnchantments;
import alephinfinity1.forgeblock.misc.capability.stats_modifier.capability.IItemModifiers;
import alephinfinity1.forgeblock.misc.capability.stats_modifier.capability.ItemModifiersProvider;
import alephinfinity1.forgeblock.misc.tier.FBTier;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public class SilentDeathItem extends FBSwordItem implements IAbilityItem, IQualityItem {

    public SilentDeathItem(Properties props, FBTier tier, double attackDamageIn, double strengthIn, double critChanceIn,
                           double critDamageIn) {
        super(props, tier, attackDamageIn, strengthIn, critChanceIn, critDamageIn);
    }

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot, ItemStack stack) {
        if (equipmentSlot != EquipmentSlotType.MAINHAND) return super.getAttributeModifiers(equipmentSlot);
        Builder<String, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.putAll(this.getAttributes(stack));
        builder.putAll(this.getReforgeModifiers(stack));

        int criticalLevel = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.CRITICAL.get(), stack);
        if (criticalLevel != 0)
            builder.put(FBAttributes.CRIT_DAMAGE.getName(), new AttributeModifier(CRITICAL_ENCHANTMENT_MODIFIER, "Crit enchant modifier", 10.0D * criticalLevel, Operation.ADDITION));

        int oneForAllLevel = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.ONE_FOR_ALL.get(), stack);
        if (oneForAllLevel != 0) {
            AtomicDouble weaponDamage = new AtomicDouble(0.0D);
            builder.build().forEach((attrName, modifier) -> {
                if (attrName.equals(SharedMonsterAttributes.ATTACK_DAMAGE.getName())
                        && modifier.getOperation().equals(Operation.ADDITION)) {
                    weaponDamage.getAndAdd(modifier.getAmount());
                }
            });
            builder.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ONE_FOR_ALL_MODIFIER, "1FA enchant modifier", weaponDamage.get() * 2.1, Operation.ADDITION));
        }

        IItemModifiers itemMod = stack.getCapability(ItemModifiersProvider.ITEM_MODIFIERS_CAPABILITY).orElse(null);
        if (itemMod != null) {
            builder.putAll(itemMod.getModifiers(stack));
        }

        return builder.build();
    }

    public Multimap<String, AttributeModifier> getAttributes(ItemStack stack) {
        ImmutableMultimap.Builder<String, AttributeModifier> newAttributes = ImmutableMultimap.builder();
        this.attributes.forEach((attrName, modifier) -> {
            if (modifier.getOperation().equals(Operation.ADDITION)) {
                newAttributes.put(attrName, new AttributeModifier(modifier.getID(),
                        modifier.getName(),
                        modifier.getAmount() * (1.0d + this.getQuality(stack) / 100.0d),
                        modifier.getOperation()));
            } else {
                newAttributes.put(attrName, modifier);
            }
        });
        return newAttributes.build();
    }

    @Override
    public int getQuality(ItemStack stack) {
        CompoundNBT tag = stack.getTag();
        if (Objects.isNull(tag)) {
            return 0;
        } else {
            return tag.getShort("Quality");
        }
    }

    @Override
    public void setQuality(ItemStack stack, int quality) {
        stack.getOrCreateTag().putShort("Quality", (short) quality);
    }

    @Override
    public int getFloor(ItemStack stack) {
        CompoundNBT tag = stack.getTag();
        if (Objects.isNull(tag)) {
            return 0;
        } else {
            return tag.getShort("Floor");
        }
    }

    @Override
    public void setFloor(ItemStack stack, int floor) {
        stack.getOrCreateTag().putShort("Floor", (short) floor);
    }

    @Override
    public List<ITextComponent> abilityDescription(ItemStack stack) {

        return new ArrayList<ITextComponent>();
    }

    @Override
    public AbilityResultType activateAbility(World world, PlayerEntity player, ItemStack stack) {
        return AbilityResultType.SUCCESS;
    }

    @Override
    public double getAbilityCost(ItemStack stack) {
        return 0;
    }

    @Override
    public double getAbilityCost(ItemStack stack, PlayerEntity player) {
        return 0;
    }

    @Override
    public int getCooldown(ItemStack stack) {
        return 0;
    }

    @Override
    public int getCooldown(ItemStack stack, PlayerEntity player) {
        return 0;
    }

    @Override
    public String getUnlocalizedUseAbilityName() {
        return "hello";
    }

    @Override
    public FBTier getStackTier(ItemStack stack) {
        if (this.getQuality(stack) >= MAX_QUALITY) {
            return FBTier.changeTier(super.getStackTier(stack), 1);        /* If maximum quality, bump rarity by 1 */
        } else {
            return super.getStackTier(stack);
        }
    }

}
