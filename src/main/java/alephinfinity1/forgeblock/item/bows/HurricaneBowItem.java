package alephinfinity1.forgeblock.item.bows;

import alephinfinity1.forgeblock.entity.FBArrowEntity;
import alephinfinity1.forgeblock.init.ModEnchantments;
import alephinfinity1.forgeblock.item.IRequirementItem;
import alephinfinity1.forgeblock.misc.RNGHelper;
import alephinfinity1.forgeblock.misc.itemreqs.IRequirementPredicate;
import alephinfinity1.forgeblock.misc.itemreqs.SkillRequirementPredicate;
import alephinfinity1.forgeblock.misc.tier.FBTier;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.*;
import net.minecraft.stats.Stats;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HurricaneBowItem extends FBBowItem implements IRequirementItem {
    private static final int[] ARROW_THRESHOLD = {20, 50, 100, 250};

    public HurricaneBowItem(Properties props, FBTier tier, double attackDamageIn, double strengthIn, double critChanceIn, double critDamageIn, float drawSpeedIn, float velocityMul, float accuracy) {
        super(props, tier, attackDamageIn, strengthIn, critChanceIn, critDamageIn, drawSpeedIn, velocityMul, accuracy);
    }

    public HurricaneBowItem(Properties group, FBTier tier, int attackDamageIn, int strengthIn, int critChanceIn, int critDamageIn) {
        super(group, tier, attackDamageIn, strengthIn, critChanceIn, critDamageIn);
    }

    /**
     * Set the number of kills on the instance.
     * @param instance The Hurricane Bow instance.
     * @param kills The number of kills.
     */
    public static void setKills(ItemStack instance, int kills) {
        if (instance != null && instance.getItem() instanceof HurricaneBowItem) {
            instance.getOrCreateTag().putInt("Kills", kills);
        }
    }

    /**
     * Increase the number of kills on the instance by 1.
     * @param instance The Hurricane Bow instance.
     */
    public static void incrementKills(ItemStack instance) {
        if (instance != null && instance.getItem() instanceof HurricaneBowItem) {
            setKills(instance, getKills(instance) + 1);
        }
    }

    /**
     * Get the number of kills on the instance.
     * @param instance The Hurricane Bow instance.
     * @return The number of kills on the instance.
     */
    public static int getKills(ItemStack instance) {
        if (instance != null && instance.getItem() instanceof HurricaneBowItem && instance.getTag() != null) {
            return instance.getTag().getInt("Kills");
        } else {
            return 0;
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

                float f = getArrowVelocity(i, drawSpeed, velocityMul);
                if (!((double)f < 0.1D)) {
                    boolean flag1 = playerentity.abilities.isCreativeMode || RNGHelper.fractionalChance(EnchantmentHelper.getEnchantmentLevel(ModEnchantments.INFINITE_QUIVER.get(), stack), 10, new Random());
                    if (!worldIn.isRemote) {
                        ArrowItem arrowitem = (ArrowItem)(itemstack.getItem() instanceof ArrowItem ? itemstack.getItem() : Items.ARROW);

                        AbstractArrowEntity abstractarrowentity = arrowitem.createArrow(worldIn, itemstack, playerentity);
                        abstractarrowentity = customeArrow(abstractarrowentity);
                        ((FBArrowEntity) abstractarrowentity).setStats(entityLiving);

                        //-15 deg
                        AbstractArrowEntity extraArrow1 = arrowitem.createArrow(worldIn, itemstack, playerentity);
                        extraArrow1 = customeArrow(extraArrow1);
                        ((FBArrowEntity) extraArrow1).setStats(entityLiving);

                        //15 deg
                        AbstractArrowEntity extraArrow2 = arrowitem.createArrow(worldIn, itemstack, playerentity);
                        extraArrow2 = customeArrow(extraArrow2);
                        ((FBArrowEntity) extraArrow2).setStats(entityLiving);

                        //-30 deg
                        AbstractArrowEntity extraArrow3 = arrowitem.createArrow(worldIn, itemstack, playerentity);
                        extraArrow3 = customeArrow(extraArrow3);
                        ((FBArrowEntity) extraArrow3).setStats(entityLiving);

                        //30 deg
                        AbstractArrowEntity extraArrow4 = arrowitem.createArrow(worldIn, itemstack, playerentity);
                        extraArrow4 = customeArrow(extraArrow4);
                        ((FBArrowEntity) extraArrow4).setStats(entityLiving);

                        ((FBArrowEntity) abstractarrowentity).setHurricane(stack);
                        ((FBArrowEntity) extraArrow1).setHurricane(stack);
                        ((FBArrowEntity) extraArrow2).setHurricane(stack);
                        ((FBArrowEntity) extraArrow3).setHurricane(stack);
                        ((FBArrowEntity) extraArrow4).setHurricane(stack);

                        abstractarrowentity.shoot(playerentity, playerentity.rotationPitch, playerentity.rotationYaw, 0.0F, f * 3.0F, 1.0F / accuracy);
                        extraArrow1.shoot(playerentity, playerentity.rotationPitch, playerentity.rotationYaw - 10.0f, 0.0F, f * 3.0F, 1.0F / accuracy);
                        extraArrow2.shoot(playerentity, playerentity.rotationPitch, playerentity.rotationYaw + 10.0f, 0.0F, f * 3.0F, 1.0F / accuracy);
                        extraArrow3.shoot(playerentity, playerentity.rotationPitch, playerentity.rotationYaw - 20.0f, 0.0F, f * 3.0F, 1.0F / accuracy);
                        extraArrow4.shoot(playerentity, playerentity.rotationPitch, playerentity.rotationYaw + 20.0f, 0.0F, f * 3.0F, 1.0F / accuracy);

                        if (f >= this.velocityMul) {
                            abstractarrowentity.setIsCritical(true);
                            extraArrow1.setIsCritical(true);
                            extraArrow2.setIsCritical(true);
                            extraArrow3.setIsCritical(true);
                            extraArrow4.setIsCritical(true);
                        }

                        int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);
                        if (j > 0) {
                            ((FBArrowEntity) abstractarrowentity).setMultiplier(
                                    ((FBArrowEntity) abstractarrowentity).getMultiplier() + 0.08 * j
                            );
                            ((FBArrowEntity) extraArrow1).setMultiplier(
                                    ((FBArrowEntity) extraArrow1).getMultiplier() + 0.08 * j
                            );
                            ((FBArrowEntity) extraArrow2).setMultiplier(
                                    ((FBArrowEntity) extraArrow2).getMultiplier() + 0.08 * j
                            );
                            ((FBArrowEntity) extraArrow3).setMultiplier(
                                    ((FBArrowEntity) extraArrow3).getMultiplier() + 0.08 * j
                            );
                            ((FBArrowEntity) extraArrow4).setMultiplier(
                                    ((FBArrowEntity) extraArrow4).getMultiplier() + 0.08 * j
                            );
                        }

                        int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);
                        if (k > 0) {
                            abstractarrowentity.setKnockbackStrength(k);
                            extraArrow1.setKnockbackStrength(k);
                            extraArrow2.setKnockbackStrength(k);
                            extraArrow3.setKnockbackStrength(k);
                            extraArrow4.setKnockbackStrength(k);
                        }

                        int l = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.AIMING.get(), stack);
                        if (l > 0) {
                            ((FBArrowEntity) abstractarrowentity).setAimingLvl(l);
                            //Extra arrows not affected by aiming
                        }

                        int m = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.SNIPE.get(), stack);
                        if (m > 0) {
                            ((FBArrowEntity) abstractarrowentity).setSnipeLvl(m);
                            ((FBArrowEntity) extraArrow1).setSnipeLvl(m);
                            ((FBArrowEntity) extraArrow2).setSnipeLvl(m);
                            ((FBArrowEntity) extraArrow3).setSnipeLvl(m);
                            ((FBArrowEntity) extraArrow4).setSnipeLvl(m);
                        }

                        int n = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.CUBISM.get(), stack);
                        if (n > 0) {
                            ((FBArrowEntity) abstractarrowentity).setCubismMultiplier(n * 0.10);
                            ((FBArrowEntity) extraArrow1).setCubismMultiplier(n * 0.10);
                            ((FBArrowEntity) extraArrow2).setCubismMultiplier(n * 0.10);
                            ((FBArrowEntity) extraArrow3).setCubismMultiplier(n * 0.10);
                            ((FBArrowEntity) extraArrow4).setCubismMultiplier(n * 0.10);
                        }

                        if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0) {
                            abstractarrowentity.setFire(100);
                            extraArrow1.setFire(100);
                            extraArrow2.setFire(100);
                            extraArrow3.setFire(100);
                            extraArrow4.setFire(100);
                        }

                        if (flag1 || playerentity.abilities.isCreativeMode && (itemstack.getItem() == Items.SPECTRAL_ARROW || itemstack.getItem() == Items.TIPPED_ARROW)) {
                            abstractarrowentity.pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
                            extraArrow1.pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
                            extraArrow2.pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
                            extraArrow3.pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
                            extraArrow4.pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
                        }

                        worldIn.addEntity(abstractarrowentity);
                        if (getKills(stack) >= ARROW_THRESHOLD[0]) {
                            worldIn.addEntity(extraArrow1);
                        }
                        if (getKills(stack) >= ARROW_THRESHOLD[1]) {
                            worldIn.addEntity(extraArrow2);
                        }
                        if (getKills(stack) >= ARROW_THRESHOLD[2]) {
                            worldIn.addEntity(extraArrow3);
                        }
                        if (getKills(stack) >= ARROW_THRESHOLD[3]) {
                            worldIn.addEntity(extraArrow4);
                        }
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

    @Override
    public List<ITextComponent> additionalInformation() {
        List<ITextComponent> list = new ArrayList<>();
        list.add(new TranslationTextComponent("text.forgeblock.bow_desc.hurricane_0"));
        list.add(new TranslationTextComponent("text.forgeblock.bow_desc.hurricane_1"));
        list.add(new TranslationTextComponent("text.forgeblock.bow_desc.hurricane_2"));
        list.add(new TranslationTextComponent("text.forgeblock.bow_desc.hurricane_3"));
        list.add(new TranslationTextComponent("text.forgeblock.bow_desc.hurricane_4"));
        list.add(new StringTextComponent(""));
        return list;
    }

    @Override
    public List<ITextComponent> additionalInformation(ItemStack stack) {
        List<ITextComponent> list = new ArrayList<>();
        int kills = getKills(stack);
        if (kills >= 250) {
            list.add(new TranslationTextComponent("text.forgeblock.bow_desc.hurricane_maxed", getKills(stack)));
        } else {
            list.add(new TranslationTextComponent("text.forgeblock.bow_desc.hurricane_kills", getKills(stack)));
        }
        list.add(new StringTextComponent(""));
        return list;
    }

    //1 default version, 1 maxed version
    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
        if (this.isInGroup(group)) {
            items.add(new ItemStack(this));
            ItemStack maxed = new ItemStack(this);
            maxed.getOrCreateTag().putInt("Kills", 250);
            items.add(maxed);
        }
    }

    @Override
    public IRequirementPredicate[] getRequirements(ItemStack stack) {
        return new IRequirementPredicate[] {SkillRequirementPredicate.combatRequirement(12)};
    }
}
