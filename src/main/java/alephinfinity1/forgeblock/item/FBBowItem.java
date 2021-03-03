package alephinfinity1.forgeblock.item;

import alephinfinity1.forgeblock.attribute.FBAttributes;
import alephinfinity1.forgeblock.attribute.ModifierHelper;
import alephinfinity1.forgeblock.entity.FBArrowEntity;
import alephinfinity1.forgeblock.init.ModEnchantments;
import alephinfinity1.forgeblock.init.ModEntities;
import alephinfinity1.forgeblock.init.ModRegistries;
import alephinfinity1.forgeblock.misc.RNGHelper;
import alephinfinity1.forgeblock.misc.TextFormatHelper;
import alephinfinity1.forgeblock.misc.stats_modifier.capability.IItemModifiers;
import alephinfinity1.forgeblock.misc.stats_modifier.capability.ItemModifiersProvider;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;

import alephinfinity1.forgeblock.misc.FBItemType;
import alephinfinity1.forgeblock.misc.reforge.IReforgeableItem;
import alephinfinity1.forgeblock.misc.reforge.Reforge;
import alephinfinity1.forgeblock.misc.tier.FBTier;
import com.google.common.util.concurrent.AtomicDouble;
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
import java.util.Random;
import java.util.UUID;

import static alephinfinity1.forgeblock.item.FBSwordItem.SWORD_REFORGE_MODIFIER;

public class FBBowItem extends BowItem implements IReforgeableItem, IFBTieredItem {
    private final FBTier tier;
    private final Multimap<String, AttributeModifier> attributes;
    private final float drawSpeed;
    private final float velocityMul;
    private final float accuracy;

    protected static final UUID BOW_STRENGTH_MODIFIER = UUID.fromString("f1c2a376-481b-43d7-a257-7a2f38126500");
    protected static final UUID BOW_CRIT_CHANCE_MODIFIER = UUID.fromString("53305ff6-a0dd-4039-a2ad-653c74786f7d");
    protected static final UUID BOW_CRIT_DAMAGE_MODIFIER = UUID.fromString("bfa8ab47-7aeb-4930-a3de-f45e0cca76a6");

    protected static final UUID BOW_REFORGE_MODIFIER = UUID.fromString("71b440d6-9a6a-49ad-b3c3-4a4fb03a8afb");

    public FBBowItem(Properties props, FBTier tier, double attackDamageIn, double strengthIn, double critChanceIn, double critDamageIn, float drawSpeedIn, float velocityMul, float accuracy) {
        super(props);
        //Modify bow charge animation
        this.addPropertyOverride(new ResourceLocation("pull"), (p_210310_0_, p_210310_1_, p_210310_2_) -> {
            if (p_210310_2_ == null) {
                return 0.0F;
            } else {
                return !(p_210310_2_.getActiveItemStack().getItem() instanceof BowItem) ? 0.0F : (float)(p_210310_0_.getUseDuration() - p_210310_2_.getItemInUseCount()) * drawSpeedIn / 20.0F;
            }
        });

        this.tier = tier;
        ImmutableMultimap.Builder<String, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Attack damage modifier", attackDamageIn, AttributeModifier.Operation.ADDITION));
        builder.put(FBAttributes.STRENGTH.getName(), new AttributeModifier(BOW_STRENGTH_MODIFIER, "Strength modifier", strengthIn, AttributeModifier.Operation.ADDITION));
        builder.put(FBAttributes.CRIT_CHANCE.getName(), new AttributeModifier(BOW_CRIT_CHANCE_MODIFIER, "Crit chance modifier", critChanceIn, AttributeModifier.Operation.ADDITION));
        builder.put(FBAttributes.CRIT_DAMAGE.getName(), new AttributeModifier(BOW_CRIT_DAMAGE_MODIFIER, "Crit damage modifier", critDamageIn, AttributeModifier.Operation.ADDITION));
        attributes = builder.build();
        this.drawSpeed = drawSpeedIn;
        this.velocityMul = velocityMul;
        this.accuracy = accuracy;
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
        if (stack.getTag() != null) {
            IItemModifiers itemMod = stack.getCapability(ItemModifiersProvider.ITEM_MODIFIERS_CAPABILITY).orElse(null);
            if (itemMod != null) {
                return FBTier.changeTier(tier, itemMod.getRarity(stack));
            }
            return tier;
        } else {
            return tier;
        }
    }

    @Override
    public Reforge getReforge(ItemStack stack) {
        if (stack.getTag() == null) return null;
        String reforgeName = stack.getTag().getString("Reforge");
        if (reforgeName.isEmpty()) return null;
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

    /**
     * Gets the velocity of the arrow loose
     * @param charge The time, in ticks, the bow is charged.
     * @param drawSpeed The draw speed attribute of the bow.
     * @param velocityMul The velocity multiplier attribute of the bow.
     * @return The velocity of the arrow loose.
     */
    public static float getArrowVelocity(int charge, float drawSpeed, float velocityMul) {
        float f = (float) charge * drawSpeed / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }
        f *= velocityMul;

        return f;
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

                float f = getArrowVelocity(i, drawSpeed, velocityMul);
                if (!((double)f < 0.1D)) {
                    boolean flag1 = playerentity.abilities.isCreativeMode || RNGHelper.fractionalChance(EnchantmentHelper.getEnchantmentLevel(ModEnchantments.INFINITE_QUIVER.get(), stack), 10, new Random());
                    if (!worldIn.isRemote) {
                        ArrowItem arrowitem = (ArrowItem)(itemstack.getItem() instanceof ArrowItem ? itemstack.getItem() : Items.ARROW);
                        AbstractArrowEntity abstractarrowentity = arrowitem.createArrow(worldIn, itemstack, playerentity);
                        abstractarrowentity = customeArrow(abstractarrowentity);
                        ((FBArrowEntity) abstractarrowentity).setStats(entityLiving);
                        abstractarrowentity.shoot(playerentity, playerentity.rotationPitch, playerentity.rotationYaw, 0.0F, f * 3.0F, 1.0F / accuracy);
                        if (f >= this.velocityMul) {
                            abstractarrowentity.setIsCritical(true);
                        }

                        int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);
                        if (j > 0) {
                            ((FBArrowEntity) abstractarrowentity).setMultiplier(
                                    ((FBArrowEntity) abstractarrowentity).getMultiplier() + 0.08 * j
                            );
                        }

                        int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);
                        if (k > 0) {
                            abstractarrowentity.setKnockbackStrength(k);
                        }

                        int l = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.AIMING.get(), stack);
                        if (l > 0) {
                            ((FBArrowEntity) abstractarrowentity).setAimingLvl(l);
                        }

                        int m = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.SNIPE.get(), stack);
                        if (m > 0) {
                            ((FBArrowEntity) abstractarrowentity).setSnipeLvl(m);
                        }

                        int n = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.CUBISM.get(), stack);
                        if (n > 0) {
                            ((FBArrowEntity) abstractarrowentity).setCubismMultiplier(n * 0.10);
                        }

                        if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0) {
                            abstractarrowentity.setFire(100);
                        }

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

    public List<ITextComponent> additionalInformation() { return new ArrayList<>(); }
    public List<ITextComponent> additionalInformation(ItemStack stack) { return new ArrayList<>(); }
    public List<ITextComponent> requirementsInformation(ItemStack stack) { return new ArrayList<>(); }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        FBTier tier = this.getStackTier(stack);
        tooltip.add(new StringTextComponent(tier.color.toString() + tooltip.get(0).getString()));
        tooltip.remove(0);

        List<ITextComponent> additional = this.additionalInformation();
        List<ITextComponent> additional1 = this.additionalInformation(stack);

        tooltip.add(new TranslationTextComponent("misc.forgeblock.drawSpeed", new DecimalFormat(",##0.0").format(drawSpeed)));
        tooltip.add(new TranslationTextComponent("misc.forgeblock.velocityMul", new DecimalFormat(",##0.0").format(velocityMul)));
        tooltip.add(new TranslationTextComponent("misc.forgeblock.accuracy", new DecimalFormat(",##0.0").format(accuracy)));

        tooltip.addAll(TextFormatHelper.formatModifierMap(attributes, this.getReforge(stack), tier, stack.getCapability(ItemModifiersProvider.ITEM_MODIFIERS_CAPABILITY).orElse(null), stack));

        tooltip.add(new StringTextComponent(""));

        //Insert enchantments here
        tooltip.addAll(TextFormatHelper.formatEnchantments(stack));

        //Insert item ability description here (unused for some swords)
        tooltip.addAll(additional);
        tooltip.addAll(additional1);
        tooltip.addAll(this.requirementsInformation(stack));

        //If this item is reforgeable but not reforged
        if (this.getReforge(stack) == null) tooltip.add(new StringTextComponent(new TranslationTextComponent("text.forgeblock.reforgeable").getString()));

        boolean recombobulated = false;
        if(stack.getTag() != null) recombobulated = this.isRecombobulated(stack);
        String color = tier.color.toString();
        String bold = TextFormatting.BOLD.toString();
        String obfuscated = TextFormatting.OBFUSCATED.toString();
        String reset = TextFormatting.RESET.toString();
        if(!recombobulated) tooltip.add(new StringTextComponent(color + bold + tier.name.getString() + " " + this.getFBItemType().getDisplayName()));
        else tooltip.add(new StringTextComponent(color + bold + obfuscated + "n " + reset + color + bold + tier.name.getString() + " " + this.getFBItemType().getDisplayName() + obfuscated + " n"));
    }

    public float getDrawSpeed() {
        return drawSpeed;
    }

    public float getVelocityMul() {
        return velocityMul;
    }

    public float getAccuracy() {
        return accuracy;
    }

    public AbstractArrowEntity customeArrow(AbstractArrowEntity arrow) {
        return new FBArrowEntity(ModEntities.FB_ARROW_TYPE, (LivingEntity) arrow.getShooter(), arrow.getEntityWorld());
    }

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot, ItemStack stack) {
        if (equipmentSlot != EquipmentSlotType.MAINHAND) return super.getAttributeModifiers(equipmentSlot);
        ImmutableMultimap.Builder<String, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.putAll(this.attributes);
        builder.putAll(this.getReforgeModifiers(stack));

        IItemModifiers itemMod = stack.getCapability(ItemModifiersProvider.ITEM_MODIFIERS_CAPABILITY).orElse(null);
        if (itemMod != null) {
            builder.putAll(itemMod.getModifiers(stack));
        }

        return builder.build();
    }

    @Override
    public ITextComponent getDisplayName(ItemStack stack) {
        String reforgeName = "";
        if (this.getReforge(stack) != null) {
            reforgeName = this.getReforge(stack).getDisplayName();
        }
        FBTier tier = getStackTier(stack);
        String color = tier.color.toString();
        if (this.getReforge(stack) != null)
            return new StringTextComponent(color + reforgeName + " " + new TranslationTextComponent(this.getTranslationKey(stack)).getString());
        else
            return new StringTextComponent(color + new TranslationTextComponent(this.getTranslationKey(stack)).getString());
    }

}
