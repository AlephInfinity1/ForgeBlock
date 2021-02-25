package alephinfinity1.forgeblock.item;

import alephinfinity1.forgeblock.attribute.FBAttributes;
import alephinfinity1.forgeblock.attribute.ModifierHelper;
import alephinfinity1.forgeblock.init.ModRegistries;
import alephinfinity1.forgeblock.misc.TextFormatHelper;
import alephinfinity1.forgeblock.misc.stats_modifier.capability.IItemModifiers;
import alephinfinity1.forgeblock.misc.stats_modifier.capability.ItemModifiersProvider;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;

import alephinfinity1.forgeblock.misc.FBItemType;
import alephinfinity1.forgeblock.misc.reforge.IReforgeableItem;
import alephinfinity1.forgeblock.misc.reforge.Reforge;
import alephinfinity1.forgeblock.misc.tier.FBTier;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.stats.Stats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
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

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
        if (entityLiving instanceof PlayerEntity) {
            PlayerEntity playerentity = (PlayerEntity)entityLiving;
            boolean flag = playerentity.abilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
            ItemStack itemstack = playerentity.findAmmo(stack);

            int i = this.getUseDuration(stack) - timeLeft;
            i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, playerentity, i, !itemstack.isEmpty() || flag);
            if (i < 0) return;

            if (!itemstack.isEmpty() || flag) {
                if (itemstack.isEmpty()) {
                    itemstack = new ItemStack(Items.ARROW);
                }

                float f = getArrowVelocity(i);
                if (!((double)f < 0.1D)) {
                    boolean flag1 = playerentity.abilities.isCreativeMode || (itemstack.getItem() instanceof ArrowItem && ((ArrowItem)itemstack.getItem()).isInfinite(itemstack, stack, playerentity));
                    if (!worldIn.isRemote) {
                        ArrowItem arrowitem = (ArrowItem)(itemstack.getItem() instanceof ArrowItem ? itemstack.getItem() : Items.ARROW);
                        AbstractArrowEntity abstractarrowentity = arrowitem.createArrow(worldIn, itemstack, playerentity);
                        abstractarrowentity = customeArrow(abstractarrowentity);
                        abstractarrowentity.shoot(playerentity, playerentity.rotationPitch, playerentity.rotationYaw, 0.0F, f * 3.0F, 1.0F);
                        if (f == 1.0F) {
                            abstractarrowentity.setIsCritical(true);
                        }

                        int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);
                        if (j > 0) {
                            abstractarrowentity.setDamage(abstractarrowentity.getDamage() + (double)j * 0.5D + 0.5D);
                        }

                        int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);
                        if (k > 0) {
                            abstractarrowentity.setKnockbackStrength(k);
                        }

                        if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0) {
                            abstractarrowentity.setFire(100);
                        }

                        stack.damageItem(1, playerentity, (p_220009_1_) -> {
                            p_220009_1_.sendBreakAnimation(playerentity.getActiveHand());
                        });
                        if (flag1 || playerentity.abilities.isCreativeMode && (itemstack.getItem() == Items.SPECTRAL_ARROW || itemstack.getItem() == Items.TIPPED_ARROW)) {
                            abstractarrowentity.pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
                        }

                        worldIn.addEntity(abstractarrowentity);
                    }

                    worldIn.playSound((PlayerEntity)null, playerentity.getPosX(), playerentity.getPosY(), playerentity.getPosZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                    if (!flag1 && !playerentity.abilities.isCreativeMode) {
                        itemstack.shrink(1);
                        if (itemstack.isEmpty()) {
                            playerentity.inventory.deleteStack(itemstack);
                        }
                    }

                    playerentity.addStat(Stats.ITEM_USED.get(this));
                }
            }
        }
    }

    public List<ITextComponent> additionalInformation() {return new ArrayList<>();}
    public List<ITextComponent> additionalInformation(ItemStack stack) {return new ArrayList<>();}
    public List<ITextComponent> requirementsInformation(ItemStack stack) {return new ArrayList<>();}

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        FBTier tier = this.getStackTier(stack);
        tooltip.add(new StringTextComponent(tier.color.toString() + tooltip.get(0).getString()));
        tooltip.remove(0);

        List<ITextComponent> additional = this.additionalInformation();
        List<ITextComponent> additional1 = this.additionalInformation(stack);

        tooltip.add(new TranslationTextComponent("misc.forgeblock.drawSpeed", new DecimalFormat(",###.0").format(drawSpeed)));

        tooltip.addAll(TextFormatHelper.formatModifierMap(attributes, this.getReforge(stack), tier, stack.getCapability(ItemModifiersProvider.ITEM_MODIFIERS_CAPABILITY).orElse(null), stack));

        tooltip.add(new StringTextComponent(""));

        //Insert enchantments here
        tooltip.addAll(TextFormatHelper.formatEnchantments(stack));

        //Insert item ability description here (unused for some swords)
        tooltip.addAll(additional);
        tooltip.addAll(additional1);
        tooltip.addAll(this.requirementsInformation(stack));

        //If this item is reforgeable but not reforged
        if(this.getReforge(stack) == null) tooltip.add(new StringTextComponent(new TranslationTextComponent("text.forgeblock.reforgeable").getString()));

        boolean recombobulated = false;
        if(stack.getTag() != null) recombobulated = this.isRecombobulated(stack);
        String color = tier.color.toString();
        String bold = TextFormatting.BOLD.toString();
        String obfuscated = TextFormatting.OBFUSCATED.toString();
        String reset = TextFormatting.RESET.toString();
        if(!recombobulated) tooltip.add(new StringTextComponent(color + bold + tier.name.getString() + " " + this.getFBItemType().getDisplayName()));
        else tooltip.add(new StringTextComponent(color + bold + obfuscated + "n " + reset + color + bold + tier.name.getString() + " " + this.getFBItemType().getDisplayName() + obfuscated + " n"));
    }
}
