package alephinfinity1.forgeblock.mixin;

import alephinfinity1.forgeblock.attribute.FBAttributes;
import alephinfinity1.forgeblock.init.ModEnchantments;
import alephinfinity1.forgeblock.misc.FBCreatureAttributes;
import alephinfinity1.forgeblock.misc.RNGHelper;
import alephinfinity1.forgeblock.misc.skills.SkillsHelper;
import alephinfinity1.forgeblock.network.DamageParticlePacket;
import alephinfinity1.forgeblock.network.FBPacketHandler;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.boss.dragon.EnderDragonPartEntity;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.network.play.server.SEntityVelocityPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stats;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.PacketDistributor;
import org.lwjgl.opengl.NVBindlessMultiDrawIndirectCount;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(PlayerEntity.class)
public abstract class MixinPlayerEntity {

    @Inject(at = @At("RETURN"), method = "registerAttributes()V", cancellable = true)
    public void registerAttributes(CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;

        player.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0F);
        player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0F);

        player.getAttributes().registerAttribute(FBAttributes.STRENGTH);
        player.getAttributes().registerAttribute(FBAttributes.DEFENSE);
        player.getAttributes().registerAttribute(FBAttributes.TRUE_DEFENSE);
        player.getAttributes().registerAttribute(FBAttributes.CRIT_CHANCE);
        player.getAttributes().registerAttribute(FBAttributes.CRIT_DAMAGE);
        player.getAttributes().registerAttribute(FBAttributes.BONUS_ATTACK_SPEED);
        player.getAttributes().registerAttribute(FBAttributes.INTELLIGENCE);
        player.getAttributes().registerAttribute(FBAttributes.FEROCITY);
        for(IAttribute attribute : FBAttributes.ADDITIONAL_ATTRIBUTES) {
            player.getAttributes().registerAttribute(attribute);
        }
        for(IAttribute attribute : FBAttributes.RAW_ATTRIBUTES) {
            player.getAttributes().registerAttribute(attribute);
        }

        //Player-only attributes
        player.getAttributes().registerAttribute(FBAttributes.SEA_CREATURE_CHANCE);
        player.getAttributes().registerAttribute(FBAttributes.MAGIC_FIND);
        player.getAttributes().registerAttribute(FBAttributes.PET_LUCK);
        for(IAttribute attribute : FBAttributes.SKILL_XP_BOOSTS) {
            player.getAttributes().registerAttribute(attribute);
        }
        for(IAttribute attribute : FBAttributes.SLAYER_LUCKS) {
            player.getAttributes().registerAttribute(attribute);
        }
    }

    /**
     * @author AlephInfinity
     * @reason Replaces the old DamageHandler so it only applies to player actions and averts a god object.
     * Still not compatible with other mods though.
     */
    @Overwrite
    //TODO Add handling for custom enchantments
    public void attackTargetEntityWithCurrentItem(Entity targetEntity) {
        if (!net.minecraftforge.common.ForgeHooks.onPlayerAttackTarget(((PlayerEntity) (Object)this), targetEntity)) return;
        if (targetEntity.canBeAttackedWithItem()) {
            if (!targetEntity.hitByEntity(((PlayerEntity)(Object) this))) {
                float damage = (float)((PlayerEntity)(Object) this).getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue();
                float strength = (float)((PlayerEntity)(Object) this).getAttribute(FBAttributes.STRENGTH).getValue();
                float critChance = (float)((PlayerEntity)(Object) this).getAttribute(FBAttributes.CRIT_CHANCE).getValue();
                float critDamage = (float)((PlayerEntity)(Object) this).getAttribute(FBAttributes.STRENGTH).getValue();
                float f = (float) ((damage + Math.floor(strength / 5.0D)) * (1 + strength / 100.0D));
                float f1;

                //Enchantment effects
                ItemStack weapon = ((PlayerEntity)(Object) this).getHeldItemMainhand();
                double enchMultiplier = 0.0D;
                enchMultiplier += 0.05 * EnchantmentHelper.getEnchantmentLevel(ModEnchantments.SHARPNESS.get(), weapon);

                if (targetEntity instanceof LivingEntity) {
                    //a: sharpness & variants
                    LivingEntity victim = (LivingEntity) targetEntity;
                    if (victim.getCreatureAttribute().equals(CreatureAttribute.ARTHROPOD)) {
                        enchMultiplier += 0.08 * EnchantmentHelper.getEnchantmentLevel(Enchantments.BANE_OF_ARTHROPODS, weapon);
                    } else if (victim.getCreatureAttribute().equals(CreatureAttribute.UNDEAD)) {
                        enchMultiplier += 0.08 * EnchantmentHelper.getEnchantmentLevel(Enchantments.SMITE, weapon);
                    } else if (victim.getCreatureAttribute().equals(FBCreatureAttributes.CUBE) || victim instanceof CreeperEntity || victim instanceof SlimeEntity || victim instanceof MagmaCubeEntity) {
                        enchMultiplier += 0.1 * EnchantmentHelper.getEnchantmentLevel(ModEnchantments.CUBISM.get(), weapon);
                    } else if (victim.getCreatureAttribute().equals(FBCreatureAttributes.ENDER) || victim instanceof EndermanEntity || victim instanceof EndermiteEntity) {
                        enchMultiplier += 0.12 * EnchantmentHelper.getEnchantmentLevel(ModEnchantments.ENDER_SLAYER.get(), weapon);
                    }

                    int executeLevel = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.EXECUTE.get(), weapon);
                    float victimHealth = victim.getHealth();
                    float victimMaxHealth = victim.getMaxHealth();
                    enchMultiplier += 0.2 * executeLevel * (1 - victimHealth / victimMaxHealth);

                    if(victim.getCombatTracker().getBestAttacker() == null)
                        enchMultiplier += 0.2 * EnchantmentHelper.getEnchantmentLevel(ModEnchantments.FIRST_STRIKE.get(), weapon);

                    //d: giant killer
                    float damagerHealth = ((PlayerEntity) (Object) this).getHealth();
                    if(victimHealth > damagerHealth) {
                        double healthDiffPercentage = (victimHealth - damagerHealth) / damagerHealth * 100.0D;
                        int giantKillerLevel = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.GIANT_KILLER.get(), weapon);
                        double giantKillerMultiplier = healthDiffPercentage * giantKillerLevel * 0.001;
                        if(giantKillerMultiplier > 0.25) giantKillerMultiplier = 0.25;
                        enchMultiplier += giantKillerMultiplier;
                    }
                }

                double skillMultiplier = 0.0D;
                PlayerEntity player = (PlayerEntity) (Object) this;
                skillMultiplier = 0.04D * SkillsHelper.getCombatLevelOrElse(player, 0);

                f *= (1.0D + enchMultiplier + skillMultiplier);

                /* Use custom enchantments handling instead
                if (targetEntity instanceof LivingEntity) {
                    f1 = EnchantmentHelper.getModifierForCreature(((PlayerEntity)(Object) this).getHeldItemMainhand(), ((LivingEntity)targetEntity).getCreatureAttribute());
                } else {
                    f1 = EnchantmentHelper.getModifierForCreature(((PlayerEntity)(Object) this).getHeldItemMainhand(), CreatureAttribute.UNDEFINED);
                }
                */

                /* no more attack cooldown
                float f2 = ((PlayerEntity)(Object) this).getCooledAttackStrength(0.5F);
                f = f * (0.2F + f2 * f2 * 0.8F);
                f1 = f1 * f2;
                ((PlayerEntity)(Object) this).resetCooldown();
                */
                if (f > 0.0F) {
                    boolean flag = true; //f2 > 0.9F;
                    boolean flag1 = false;
                    int i = 0;
                    i = i + EnchantmentHelper.getKnockbackModifier(((PlayerEntity)(Object) this));
                    if (((PlayerEntity)(Object) this).isSprinting() && flag) {
                        ((PlayerEntity)(Object) this).world.playSound(null, ((PlayerEntity)(Object) this).getPosX(), ((PlayerEntity)(Object) this).getPosY(), ((PlayerEntity)(Object) this).getPosZ(), SoundEvents.ENTITY_PLAYER_ATTACK_KNOCKBACK, ((PlayerEntity)(Object) this).getSoundCategory(), 1.0F, 1.0F);
                        ++i;
                        flag1 = true;
                    }

                    boolean flag2 = flag && ((PlayerEntity)(Object) this).fallDistance > 0.0F && !((PlayerEntity)(Object) this).onGround && !((PlayerEntity)(Object) this).isOnLadder() && !((PlayerEntity)(Object) this).isInWater() && !((PlayerEntity)(Object) this).isPotionActive(Effects.BLINDNESS) && !((PlayerEntity)(Object) this).isPassenger() && targetEntity instanceof LivingEntity;
                    flag2 = RNGHelper.randomChance(critChance / 100.0D, new Random()); //flag2 && !((PlayerEntity)(Object) this).isSprinting();
                    net.minecraftforge.event.entity.player.CriticalHitEvent hitResult = net.minecraftforge.common.ForgeHooks.getCriticalHit(((PlayerEntity)(Object) this), targetEntity, flag2, flag2 ? (float) (1.0F + critDamage / 100.0D) : 1.0F);
                    boolean isCrit = flag2;
                    flag2 = hitResult != null;
                    if (flag2) {
                        f *= hitResult.getDamageModifier();
                    }

                    boolean flag3 = false;
                    double d0 = ((PlayerEntity)(Object) this).distanceWalkedModified - ((PlayerEntity)(Object) this).prevDistanceWalkedModified;
                    if (flag && !flag2 && !flag1 && ((PlayerEntity)(Object) this).onGround && d0 < (double)((PlayerEntity)(Object) this).getAIMoveSpeed()) {
                        ItemStack itemstack = ((PlayerEntity)(Object) this).getHeldItem(Hand.MAIN_HAND);
                        if (itemstack.getItem() instanceof SwordItem) {
                            flag3 = true;
                        }
                    }

                    float f4 = 0.0F;
                    boolean flag4 = false;
                    int j = EnchantmentHelper.getFireAspectModifier(((PlayerEntity)(Object) this));
                    if (targetEntity instanceof LivingEntity) {
                        f4 = ((LivingEntity)targetEntity).getHealth();
                        if (j > 0 && !targetEntity.isBurning()) {
                            flag4 = true;
                            targetEntity.setFire(1);
                        }
                    }

                    Vec3d vec3d = targetEntity.getMotion();
                    //f = 19;
                    boolean flag5 = targetEntity.attackEntityFrom(DamageSource.causePlayerDamage(((PlayerEntity)(Object) this)), f);
                    if (flag5) {
                        if (flag2) {
                            FBPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) (Object) this), new DamageParticlePacket(f, "crit", targetEntity.getPositionVec().add(0, targetEntity.getHeight() / 2.0, 0)));
                        } else {
                            FBPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) (Object) this), new DamageParticlePacket(f, "normal", targetEntity.getPositionVec().add(0, targetEntity.getHeight() / 2.0, 0)));
                        }
                        if (i > 0) {
                            if (targetEntity instanceof LivingEntity) {
                                ((LivingEntity)targetEntity).knockBack(((PlayerEntity)(Object) this), (float)i * 0.5F, (double) MathHelper.sin(((PlayerEntity)(Object) this).rotationYaw * ((float)Math.PI / 180F)), (double)(-MathHelper.cos(((PlayerEntity)(Object) this).rotationYaw * ((float)Math.PI / 180F))));
                            } else {
                                targetEntity.addVelocity((-MathHelper.sin(((PlayerEntity)(Object) this).rotationYaw * ((float)Math.PI / 180F)) * (float)i * 0.5F), 0.1D, (double)(MathHelper.cos(((PlayerEntity)(Object) this).rotationYaw * ((float)Math.PI / 180F)) * (float)i * 0.5F));
                            }

                            ((PlayerEntity)(Object) this).setMotion(((PlayerEntity)(Object) this).getMotion().mul(0.6D, 1.0D, 0.6D));
                            ((PlayerEntity)(Object) this).setSprinting(false);
                        }

                        if (flag3) {
                            float f3 = /*1.0F +*/ EnchantmentHelper.getSweepingDamageRatio(((PlayerEntity)(Object) this)) * f;

                            for(LivingEntity livingentity : ((PlayerEntity)(Object) this).world.getEntitiesWithinAABB(LivingEntity.class, targetEntity.getBoundingBox().grow(1.0D, 0.25D, 1.0D))) {
                                if (livingentity != ((Object) this) && livingentity != targetEntity && !((PlayerEntity)(Object) this).isOnSameTeam(livingentity) && (!(livingentity instanceof ArmorStandEntity) || !((ArmorStandEntity)livingentity).hasMarker()) && ((PlayerEntity)(Object) this).getDistanceSq(livingentity) < 9.0D) {
                                    livingentity.knockBack(((PlayerEntity)(Object) this), 0.4F, (double)MathHelper.sin(((PlayerEntity)(Object) this).rotationYaw * ((float)Math.PI / 180F)), (double)(-MathHelper.cos(((PlayerEntity)(Object) this).rotationYaw * ((float)Math.PI / 180F))));
                                    livingentity.attackEntityFrom(DamageSource.causePlayerDamage(((PlayerEntity)(Object) this)), f3);
                                }
                            }

                            ((PlayerEntity)(Object) this).world.playSound(null, ((PlayerEntity)(Object) this).getPosX(), ((PlayerEntity)(Object) this).getPosY(), ((PlayerEntity)(Object) this).getPosZ(), SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, ((PlayerEntity)(Object) this).getSoundCategory(), 1.0F, 1.0F);
                            ((PlayerEntity)(Object) this).spawnSweepParticles();
                        }

                        if (targetEntity instanceof ServerPlayerEntity && targetEntity.velocityChanged) {
                            ((ServerPlayerEntity)targetEntity).connection.sendPacket(new SEntityVelocityPacket(targetEntity));
                            targetEntity.velocityChanged = false;
                            targetEntity.setMotion(vec3d);
                        }

                        if (flag2) {
                            ((PlayerEntity)(Object) this).world.playSound(null, ((PlayerEntity)(Object) this).getPosX(), ((PlayerEntity)(Object) this).getPosY(), ((PlayerEntity)(Object) this).getPosZ(), SoundEvents.ENTITY_PLAYER_ATTACK_CRIT, ((PlayerEntity)(Object) this).getSoundCategory(), 1.0F, 1.0F);
                            ((PlayerEntity)(Object) this).onCriticalHit(targetEntity);
                        }

                        if (!flag2 && !flag3) {
                            if (flag) {
                                ((PlayerEntity)(Object) this).world.playSound(null, ((PlayerEntity)(Object) this).getPosX(), ((PlayerEntity)(Object) this).getPosY(), ((PlayerEntity)(Object) this).getPosZ(), SoundEvents.ENTITY_PLAYER_ATTACK_STRONG, ((PlayerEntity)(Object) this).getSoundCategory(), 1.0F, 1.0F);
                            } else {
                                ((PlayerEntity)(Object) this).world.playSound(null, ((PlayerEntity)(Object) this).getPosX(), ((PlayerEntity)(Object) this).getPosY(), ((PlayerEntity)(Object) this).getPosZ(), SoundEvents.ENTITY_PLAYER_ATTACK_WEAK, ((PlayerEntity)(Object) this).getSoundCategory(), 1.0F, 1.0F);
                            }
                        }

                        ((PlayerEntity)(Object) this).setLastAttackedEntity(targetEntity);
                        if (targetEntity instanceof LivingEntity) {
                            EnchantmentHelper.applyThornEnchantments((LivingEntity)targetEntity, ((PlayerEntity)(Object) this));
                        }

                        EnchantmentHelper.applyArthropodEnchantments(((PlayerEntity)(Object) this), targetEntity);
                        ItemStack itemstack1 = ((PlayerEntity)(Object) this).getHeldItemMainhand();
                        Entity entity = targetEntity;
                        if (targetEntity instanceof EnderDragonPartEntity) {
                            entity = ((EnderDragonPartEntity)targetEntity).dragon;
                        }

                        if (!((PlayerEntity)(Object) this).world.isRemote && !itemstack1.isEmpty() && entity instanceof LivingEntity) {
                            ItemStack copy = itemstack1.copy();
                            itemstack1.hitEntity((LivingEntity)entity, ((PlayerEntity)(Object) this));
                            if (itemstack1.isEmpty()) {
                                net.minecraftforge.event.ForgeEventFactory.onPlayerDestroyItem(((PlayerEntity)(Object) this), copy, Hand.MAIN_HAND);
                                ((PlayerEntity)(Object) this).setHeldItem(Hand.MAIN_HAND, ItemStack.EMPTY);
                            }
                        }

                        if (targetEntity instanceof LivingEntity) {
                            float f5 = f4 - ((LivingEntity)targetEntity).getHealth();
                            ((PlayerEntity)(Object) this).addStat(Stats.DAMAGE_DEALT, Math.round(f5 * 10.0F));
                            if (j > 0) {
                                targetEntity.setFire(j * 4);
                            }

                            if (((PlayerEntity)(Object) this).world instanceof ServerWorld && f5 > 2.0F) {
                                int k = (int)((double)f5 * 0.5D);
                                ((ServerWorld)((PlayerEntity)(Object) this).world).spawnParticle(ParticleTypes.DAMAGE_INDICATOR, targetEntity.getPosX(), targetEntity.getPosYHeight(0.5D), targetEntity.getPosZ(), k, 0.1D, 0.0D, 0.1D, 0.2D);
                            }
                        }

                        ((PlayerEntity)(Object) this).addExhaustion(0.1F);
                    } else {
                        ((PlayerEntity)(Object) this).world.playSound(null, ((PlayerEntity)(Object) this).getPosX(), ((PlayerEntity)(Object) this).getPosY(), ((PlayerEntity)(Object) this).getPosZ(), SoundEvents.ENTITY_PLAYER_ATTACK_NODAMAGE, ((PlayerEntity)(Object) this).getSoundCategory(), 1.0F, 1.0F);
                        if (flag4) {
                            targetEntity.extinguish();
                        }
                    }
                }

            }
        }
    }

    @Inject(at = @At("RETURN"), method = "xpBarCap()I", cancellable = true)
    public void xpBarCap(CallbackInfoReturnable<Integer> cir) {
        cir.cancel();
        int level = ((PlayerEntity) (Object) this).experienceLevel;
        switch (level % 100) {
        case 0:
            cir.setReturnValue(500);
            break;
        case 1:
            cir.setReturnValue(1000);
            break;
        case 2:
            cir.setReturnValue(2000);
            break;
        case 3:
            cir.setReturnValue(3500);
            break;
        default:
            cir.setReturnValue(5000);
            break;
        }
    }

}
