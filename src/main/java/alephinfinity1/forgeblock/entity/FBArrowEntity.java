package alephinfinity1.forgeblock.entity;

import alephinfinity1.forgeblock.misc.AttributeHelper;
import alephinfinity1.forgeblock.misc.RNGHelper;
import alephinfinity1.forgeblock.misc.skills.SkillsHelper;
import alephinfinity1.forgeblock.mixin.AccessorAbstractArrowEntity;
import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SChangeGameStatePacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.Arrays;
import java.util.Random;

public class FBArrowEntity extends AbstractArrowEntity {
    private double damage; //Note: different from the damage in AbstractArrowEntity, this refers to the FB player stat
    private double strength;
    private double critChance;
    private double critDamage;
    private double multiplier = 1.0D;

    public FBArrowEntity(EntityType<? extends AbstractArrowEntity> type, World worldIn) {
        super(type, worldIn);
    }

    protected FBArrowEntity(EntityType<? extends AbstractArrowEntity> type, double x, double y, double z, World worldIn) {
        super(type, x, y, z, worldIn);
    }

    public FBArrowEntity(EntityType<? extends AbstractArrowEntity> type, LivingEntity shooter, World worldIn) {
        super(type, shooter, worldIn);
    }

    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(Items.ARROW);
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }

    public void setCritChance(double critChance) {
        this.critChance = critChance;
    }

    public void setCritDamage(double critDamage) {
        this.critDamage = critDamage;
    }

    public void setMultiplier(double multiplier) {
        this.multiplier = multiplier;
    }

    @Override
    public double getDamage() {
        return damage;
    }

    public double getStrength() {
        return strength;
    }

    public double getCritChance() {
        return critChance;
    }

    public double getCritDamage() {
        return critDamage;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public void setStats(LivingEntity livingEntity) {
        setDamage(AttributeHelper.getDamageOrDefault(livingEntity, 0));
        setStrength(AttributeHelper.getStrengthOrDefault(livingEntity, 0));
        setCritChance(AttributeHelper.getCritChanceOrDefault(livingEntity, 0));
        setCritDamage(AttributeHelper.getCritDamageOrDefault(livingEntity, 0));
        if (livingEntity instanceof PlayerEntity) {
            setMultiplier(1.0 + SkillsHelper.getCombatLevelOrElse((PlayerEntity) livingEntity, 0) * 0.04);
        }
    }

    protected void onEntityHit(EntityRayTraceResult p_213868_1_) {
        Entity entity = p_213868_1_.getEntity();
        float f = (float)this.getMotion().length();
        double i = (damage + Math.floor(strength / 5.0)) * (1.0 + strength / 100.0);
        if (this.getPierceLevel() > 0) {
            if (((AccessorAbstractArrowEntity) this).getPiercedEntities() == null) {
                ((AccessorAbstractArrowEntity) this).setPiercedEntities(new IntOpenHashSet(5));
            }

            if (((AccessorAbstractArrowEntity) this).getHitEntities() == null) {
                ((AccessorAbstractArrowEntity) this).setHitEntities(Lists.newArrayListWithCapacity(5));
            }

            if (((AccessorAbstractArrowEntity) this).getPiercedEntities().size() >= this.getPierceLevel() + 1) {
                this.remove();
                return;
            }

            ((AccessorAbstractArrowEntity) this).getPiercedEntities().add(entity.getEntityId());
        }

        boolean crit = RNGHelper.randomChance(critChance * f / 300.0, new Random());
        if (crit) {
            i *= (1.0 + critDamage / 100.0);
        }

        Entity entity1 = this.getShooter();
        DamageSource damagesource;
        if (entity1 == null) {
            damagesource = DamageSource.causeArrowDamage(this, this);
        } else {
            damagesource = DamageSource.causeArrowDamage(this, entity1);
            if (entity1 instanceof LivingEntity) {
                ((LivingEntity)entity1).setLastAttackedEntity(entity);
            }
        }

        boolean flag = entity.getType() == EntityType.ENDERMAN;
        int j = entity.getFireTimer();
        if (this.isBurning() && !flag) {
            entity.setFire(5);
        }

        if (entity.attackEntityFrom(damagesource, (float) (i * multiplier))) {
            if (flag) {
                return;
            }

            if (entity instanceof LivingEntity) {
                LivingEntity livingentity = (LivingEntity)entity;
                if (!this.world.isRemote && this.getPierceLevel() <= 0) {
                    livingentity.setArrowCountInEntity(livingentity.getArrowCountInEntity() + 1);
                }

                if (((AccessorAbstractArrowEntity) this).getKnockbackStrength() > 0) {
                    Vec3d vec3d = this.getMotion().mul(1.0D, 0.0D, 1.0D).normalize().scale((double)((AccessorAbstractArrowEntity) this).getKnockbackStrength() * 0.6D);
                    if (vec3d.lengthSquared() > 0.0D) {
                        livingentity.addVelocity(vec3d.x, 0.1D, vec3d.z);
                    }
                }

                if (!this.world.isRemote && entity1 instanceof LivingEntity) {
                    EnchantmentHelper.applyThornEnchantments(livingentity, entity1);
                    EnchantmentHelper.applyArthropodEnchantments((LivingEntity)entity1, livingentity);
                }

                this.arrowHit(livingentity);
                if (entity1 != null && livingentity != entity1 && livingentity instanceof PlayerEntity && entity1 instanceof ServerPlayerEntity) {
                    ((ServerPlayerEntity)entity1).connection.sendPacket(new SChangeGameStatePacket(6, 0.0F));
                }

                if (!entity.isAlive() && ((AccessorAbstractArrowEntity) this).getHitEntities() != null) {
                    ((AccessorAbstractArrowEntity) this).getHitEntities().add(livingentity);
                }

                if (!this.world.isRemote && entity1 instanceof ServerPlayerEntity) {
                    ServerPlayerEntity serverplayerentity = (ServerPlayerEntity)entity1;
                    if (((AccessorAbstractArrowEntity) this).getHitEntities() != null && this.getShotFromCrossbow()) {
                        CriteriaTriggers.KILLED_BY_CROSSBOW.trigger(serverplayerentity, ((AccessorAbstractArrowEntity) this).getHitEntities(), ((AccessorAbstractArrowEntity) this).getHitEntities().size());
                    } else if (!entity.isAlive() && this.getShotFromCrossbow()) {
                        CriteriaTriggers.KILLED_BY_CROSSBOW.trigger(serverplayerentity, Arrays.asList(entity), 0);
                    }
                }
            }

            this.playSound(((AccessorAbstractArrowEntity) this).getHitSound(), 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
            if (this.getPierceLevel() <= 0) {
                this.remove();
            }
        } else {
            entity.setFireTimer(j);
            this.setMotion(this.getMotion().scale(-0.1D));
            this.rotationYaw += 180.0F;
            this.prevRotationYaw += 180.0F;
            ((AccessorAbstractArrowEntity) this).setTicksInAir(0);
            if (!this.world.isRemote && this.getMotion().lengthSquared() < 1.0E-7D) {
                if (this.pickupStatus == AbstractArrowEntity.PickupStatus.ALLOWED) {
                    this.entityDropItem(this.getArrowStack(), 0.1F);
                }

                this.remove();
            }
        }
    }

    @Override
    public void writeAdditional(CompoundNBT compoundNBT) {
        super.writeAdditional(compoundNBT);
        compoundNBT.putDouble("damage", getDamage());
        compoundNBT.putDouble("strength", getStrength());
        compoundNBT.putDouble("critChance", getCritChance());
        compoundNBT.putDouble("critDamage", getCritDamage());
    }

    @Override
    public void readAdditional(CompoundNBT compoundNBT) {
        super.readAdditional(compoundNBT);
        if (compoundNBT.contains("damage")) {
            this.damage = compoundNBT.getDouble("damage");
        }
        if (compoundNBT.contains("strength")) {
            this.strength = compoundNBT.getDouble("strength");
        }
        if (compoundNBT.contains("critChance")) {
            this.critChance = compoundNBT.getDouble("critChance");
        }
        if (compoundNBT.contains("critDamage")) {
            this.critDamage = compoundNBT.getDouble("critDamage");
        }
    }
}
