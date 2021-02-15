package alephinfinity1.forgeblock.mixin;

import alephinfinity1.forgeblock.entity.IFBEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(MobEntity.class)
public class MixinMobEntity {
    /**
     * @author AlephInfinity
     * @reason Replaces the old DamageHandler while still adding the new combat mechanics. <br>Only works on custom mobs.
     */
    @Overwrite
    public boolean attackEntityAsMob(Entity entityIn) {
        if (this instanceof IFBEntity) {
            //TODO Custom mechanic not yet added.
            float f = (float) ((MobEntity) (Object) this).getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue();
            float f1 = (float) ((MobEntity) (Object) this).getAttribute(SharedMonsterAttributes.ATTACK_KNOCKBACK).getValue();
            if (entityIn instanceof LivingEntity) {
                f += EnchantmentHelper.getModifierForCreature(((MobEntity) (Object) this).getHeldItemMainhand(), ((LivingEntity) entityIn).getCreatureAttribute());
                f1 += (float) EnchantmentHelper.getKnockbackModifier(((MobEntity) (Object) this));
            }

            int i = EnchantmentHelper.getFireAspectModifier(((MobEntity) (Object) this));
            if (i > 0) {
                entityIn.setFire(i * 4);
            }

            boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(((MobEntity) (Object) this)), f);
            if (flag) {
                if (f1 > 0.0F && entityIn instanceof LivingEntity) {
                    ((LivingEntity) entityIn).knockBack(((MobEntity) (Object) this), f1 * 0.5F, (double) MathHelper.sin(((MobEntity) (Object) this).rotationYaw * ((float) Math.PI / 180F)), (double) (-MathHelper.cos(((MobEntity) (Object) this).rotationYaw * ((float) Math.PI / 180F))));
                    ((MobEntity) (Object) this).setMotion(((MobEntity) (Object) this).getMotion().mul(0.6D, 1.0D, 0.6D));
                }

                if (entityIn instanceof PlayerEntity) {
                    PlayerEntity playerentity = (PlayerEntity) entityIn;
                    ItemStack itemstack = ((MobEntity) (Object) this).getHeldItemMainhand();
                    ItemStack itemstack1 = playerentity.isHandActive() ? playerentity.getActiveItemStack() : ItemStack.EMPTY;
                    if (!itemstack.isEmpty() && !itemstack1.isEmpty() && itemstack.canDisableShield(itemstack1, playerentity, ((MobEntity) (Object) this)) && itemstack1.isShield(playerentity)) {
                        float f2 = 0.25F + (float) EnchantmentHelper.getEfficiencyModifier(((MobEntity) (Object) this)) * 0.05F;
                        if (((AccessorEntity) this).getRand().nextFloat() < f2) {
                            playerentity.getCooldownTracker().setCooldown(itemstack.getItem(), 100);
                            ((MobEntity) (Object) this).world.setEntityState(playerentity, (byte) 30);
                        }
                    }
                }

                ((AccessorEntity) this).callApplyEnchantments(((MobEntity) (Object) this), entityIn);
                ((MobEntity) (Object) this).setLastAttackedEntity(entityIn);
            }

            return flag;
        } else {
            float f = (float) ((MobEntity) (Object) this).getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue();
            float f1 = (float) ((MobEntity) (Object) this).getAttribute(SharedMonsterAttributes.ATTACK_KNOCKBACK).getValue();
            if (entityIn instanceof LivingEntity) {
                f += EnchantmentHelper.getModifierForCreature(((MobEntity) (Object) this).getHeldItemMainhand(), ((LivingEntity) entityIn).getCreatureAttribute());
                f1 += (float) EnchantmentHelper.getKnockbackModifier(((MobEntity) (Object) this));
            }

            int i = EnchantmentHelper.getFireAspectModifier(((MobEntity) (Object) this));
            if (i > 0) {
                entityIn.setFire(i * 4);
            }

            boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(((MobEntity) (Object) this)), f);
            if (flag) {
                if (f1 > 0.0F && entityIn instanceof LivingEntity) {
                    ((LivingEntity) entityIn).knockBack(((MobEntity) (Object) this), f1 * 0.5F, (double) MathHelper.sin(((MobEntity) (Object) this).rotationYaw * ((float) Math.PI / 180F)), (double) (-MathHelper.cos(((MobEntity) (Object) this).rotationYaw * ((float) Math.PI / 180F))));
                    ((MobEntity) (Object) this).setMotion(((MobEntity) (Object) this).getMotion().mul(0.6D, 1.0D, 0.6D));
                }

                if (entityIn instanceof PlayerEntity) {
                    PlayerEntity playerentity = (PlayerEntity) entityIn;
                    ItemStack itemstack = ((MobEntity) (Object) this).getHeldItemMainhand();
                    ItemStack itemstack1 = playerentity.isHandActive() ? playerentity.getActiveItemStack() : ItemStack.EMPTY;
                    if (!itemstack.isEmpty() && !itemstack1.isEmpty() && itemstack.canDisableShield(itemstack1, playerentity, ((MobEntity) (Object) this)) && itemstack1.isShield(playerentity)) {
                        float f2 = 0.25F + (float) EnchantmentHelper.getEfficiencyModifier(((MobEntity) (Object) this)) * 0.05F;
                        if (((AccessorEntity) this).getRand().nextFloat() < f2) {
                            playerentity.getCooldownTracker().setCooldown(itemstack.getItem(), 100);
                            ((MobEntity) (Object) this).world.setEntityState(playerentity, (byte) 30);
                        }
                    }
                }

                ((AccessorEntity) this).callApplyEnchantments(((MobEntity) (Object) this), entityIn);
                ((MobEntity) (Object) this).setLastAttackedEntity(entityIn);
            }

            return flag;
        }
    }
}
