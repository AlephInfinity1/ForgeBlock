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
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.stats.Stats;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RunaansBowItem extends FBBowItem implements IRequirementItem {
    public RunaansBowItem(Properties props, FBTier tier, double attackDamageIn, double strengthIn, double critChanceIn, double critDamageIn, float drawSpeedIn, float velocityMul, float accuracy) {
        super(props, tier, attackDamageIn, strengthIn, critChanceIn, critDamageIn, drawSpeedIn, velocityMul, accuracy);
    }

    public RunaansBowItem(Properties group, FBTier tier, int attackDamageIn, int strengthIn, int critChanceIn, int critDamageIn) {
        super(group, tier, attackDamageIn, strengthIn, critChanceIn, critDamageIn);
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

                        AbstractArrowEntity extraArrow1 = arrowitem.createArrow(worldIn, itemstack, playerentity);
                        extraArrow1 = customeArrow(extraArrow1);
                        ((FBArrowEntity) extraArrow1).setStats(entityLiving);
                        ((FBArrowEntity) extraArrow1).setMultiplier(0.4); //Deals 40% of the damage

                        AbstractArrowEntity extraArrow2 = arrowitem.createArrow(worldIn, itemstack, playerentity);
                        extraArrow2 = customeArrow(extraArrow2);
                        ((FBArrowEntity) extraArrow2).setStats(entityLiving);
                        ((FBArrowEntity) extraArrow2).setMultiplier(0.4); //Deals 40% of the damage

                        abstractarrowentity.shoot(playerentity, playerentity.rotationPitch, playerentity.rotationYaw, 0.0F, f * 3.0F, 1.0F / accuracy);
                        extraArrow1.shoot(playerentity, playerentity.rotationPitch, playerentity.rotationYaw - 15.0f, 0.0F, f * 3.0F, 1.0F / accuracy); //Shot at 15 degree angle
                        extraArrow2.shoot(playerentity, playerentity.rotationPitch, playerentity.rotationYaw + 15.0f, 0.0F, f * 3.0F, 1.0F / accuracy);

                        if (f >= this.velocityMul) {
                            abstractarrowentity.setIsCritical(true);
                            extraArrow1.setIsCritical(true);
                            extraArrow2.setIsCritical(true);
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
                            ((FBArrowEntity) extraArrow1).setAimingLvl(l);
                            ((FBArrowEntity) extraArrow2).setAimingLvl(l);
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
                        worldIn.addEntity(extraArrow1);
                        worldIn.addEntity(extraArrow2);
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
        list.add(new TranslationTextComponent("text.forgeblock.bow_desc.runaan_0"));
        list.add(new TranslationTextComponent("text.forgeblock.bow_desc.runaan_1"));
        list.add(new TranslationTextComponent("text.forgeblock.bow_desc.runaan_2"));
        list.add(new TranslationTextComponent("text.forgeblock.bow_desc.runaan_3"));
        list.add(new StringTextComponent(""));
        return list;
    }

    @Override
    public IRequirementPredicate[] getRequirements(ItemStack stack) {
        return new IRequirementPredicate[] {SkillRequirementPredicate.combatRequirement(16)};
    }
}
