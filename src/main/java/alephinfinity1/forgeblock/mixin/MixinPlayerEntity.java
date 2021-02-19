package alephinfinity1.forgeblock.mixin;

import alephinfinity1.forgeblock.attribute.FBAttributes;
import alephinfinity1.forgeblock.client.ClientEventBusSubscriber;
import alephinfinity1.forgeblock.init.ModEnchantments;
import alephinfinity1.forgeblock.misc.AttributeHelper;
import alephinfinity1.forgeblock.misc.FBCreatureAttributes;
import alephinfinity1.forgeblock.misc.FBFoodStats;
import alephinfinity1.forgeblock.misc.RNGHelper;
import alephinfinity1.forgeblock.misc.skills.SkillsHelper;
import alephinfinity1.forgeblock.network.DamageParticlePacket;
import alephinfinity1.forgeblock.network.FBPacketHandler;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.entity.player.ClientPlayerEntity;
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
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.PacketDistributor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(PlayerEntity.class)
public abstract class MixinPlayerEntity {

    @Inject(at = @At("RETURN"), method = "<init>")
    public void overrideFood(World worldIn, GameProfile gameProfileIn, CallbackInfo ci) {
        ((AccessorPlayerEntity) this).setFoodStats(new FBFoodStats());
    }

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
        PlayerEntity player = (PlayerEntity) (Object) this;
        if (player instanceof ClientPlayerEntity) return;
        if (!net.minecraftforge.common.ForgeHooks.onPlayerAttackTarget(((PlayerEntity) (Object)this), targetEntity)) return;
        if (targetEntity.canBeAttackedWithItem()) {
            if (!targetEntity.hitByEntity(player)) {
                float damage = (float) AttributeHelper.getDamageOrDefault(player, 0);
                float strength = (float) AttributeHelper.getStrengthOrDefault(player, 0);
                float critChance = (float) AttributeHelper.getCritChanceOrDefault(player, 0);
                float critDamage = (float) AttributeHelper.getCritDamageOrDefault(player, 0);
                float f = (float) ((damage + Math.floor(strength / 5.0D)) * (1 + strength / 100.0D));
                float f1;

                //Enchantment effects
                ItemStack weapon = player.getHeldItemMainhand();
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

                double skillMultiplier = 0.04D * SkillsHelper.getCombatLevelOrElse(player, 0);

                f *= (1.0D + enchMultiplier + skillMultiplier);

                /* Use custom enchantments handling instead
                if (targetEntity instanceof LivingEntity) {
                    f1 = EnchantmentHelper.getModifierForCreature(player.getHeldItemMainhand(), ((LivingEntity)targetEntity).getCreatureAttribute());
                } else {
                    f1 = EnchantmentHelper.getModifierForCreature(player.getHeldItemMainhand(), CreatureAttribute.UNDEFINED);
                }
                */

                /* no more attack cooldown
                float f2 = player.getCooledAttackStrength(0.5F);
                f = f * (0.2F + f2 * f2 * 0.8F);
                f1 = f1 * f2;
                player.resetCooldown();
                */
                if (f > 0.0F) {
                    boolean flag = true; //f2 > 0.9F;
                    boolean flag1 = false;
                    int i = 0;
                    i = i + EnchantmentHelper.getKnockbackModifier(player);
                    if (player.isSprinting() && flag) {
                        player.world.playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_PLAYER_ATTACK_KNOCKBACK, player.getSoundCategory(), 1.0F, 1.0F);
                        ++i;
                        flag1 = true;
                    }

                    boolean flag2 = flag && player.fallDistance > 0.0F && !player.onGround && !player.isOnLadder() && !player.isInWater() && !player.isPotionActive(Effects.BLINDNESS) && !player.isPassenger() && targetEntity instanceof LivingEntity;
                    flag2 = RNGHelper.randomChance(critChance / 100.0D, new Random()); //flag2 && !player.isSprinting();
                    net.minecraftforge.event.entity.player.CriticalHitEvent hitResult = net.minecraftforge.common.ForgeHooks.getCriticalHit(player, targetEntity, flag2, flag2 ? (float) (1.0F + critDamage / 100.0D) : 1.0F);
                    boolean isCrit = flag2;
                    flag2 = hitResult != null;
                    if (flag2) {
                        f *= hitResult.getDamageModifier();
                    }

                    boolean flag3 = false;
                    double d0 = player.distanceWalkedModified - player.prevDistanceWalkedModified;
                    if (flag && !flag2 && !flag1 && player.onGround && d0 < (double)player.getAIMoveSpeed()) {
                        ItemStack itemstack = player.getHeldItem(Hand.MAIN_HAND);
                        if (itemstack.getItem() instanceof SwordItem) {
                            flag3 = true;
                        }
                    }

                    float f4 = 0.0F;
                    boolean flag4 = false;
                    int j = EnchantmentHelper.getFireAspectModifier(player);
                    if (targetEntity instanceof LivingEntity) {
                        f4 = ((LivingEntity)targetEntity).getHealth();
                        if (j > 0 && !targetEntity.isBurning()) {
                            flag4 = true;
                            targetEntity.setFire(1);
                        }
                    }

                    Vec3d vec3d = targetEntity.getMotion();
                    //f = 19;
                    boolean flag5 = targetEntity.attackEntityFrom(DamageSource.causePlayerDamage(player), f);
                    if (flag5) {
                        if (flag2) {
                            FBPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) (Object) this), new DamageParticlePacket(f, "crit", targetEntity.getPositionVec().add(0, targetEntity.getHeight() / 2.0, 0)));
                        } else {
                            FBPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) (Object) this), new DamageParticlePacket(f, "normal", targetEntity.getPositionVec().add(0, targetEntity.getHeight() / 2.0, 0)));
                        }
                        if (i > 0) {
                            if (targetEntity instanceof LivingEntity) {
                                ((LivingEntity)targetEntity).knockBack(player, (float)i * 0.5F, MathHelper.sin(player.rotationYaw * ((float)Math.PI / 180F)), -MathHelper.cos(player.rotationYaw * ((float)Math.PI / 180F)));
                            } else {
                                targetEntity.addVelocity((-MathHelper.sin(player.rotationYaw * ((float)Math.PI / 180F)) * (float)i * 0.5F), 0.1D, MathHelper.cos(player.rotationYaw * ((float)Math.PI / 180F)) * (float)i * 0.5F);
                            }

                            player.setMotion(player.getMotion().mul(0.6D, 1.0D, 0.6D));
                            player.setSprinting(false);
                        }

                        if (flag3) {
                            float f3 = /*1.0F +*/ EnchantmentHelper.getSweepingDamageRatio(player) * f;

                            for(LivingEntity livingentity : player.world.getEntitiesWithinAABB(LivingEntity.class, targetEntity.getBoundingBox().grow(1.0D, 0.25D, 1.0D))) {
                                if (livingentity != ((Object) this) && livingentity != targetEntity && !player.isOnSameTeam(livingentity) && (!(livingentity instanceof ArmorStandEntity) || !((ArmorStandEntity)livingentity).hasMarker()) && player.getDistanceSq(livingentity) < 9.0D) {
                                    //livingentity.knockBack(player, 0.4F, (double)MathHelper.sin(player.rotationYaw * ((float)Math.PI / 180F)), (double)(-MathHelper.cos(player.rotationYaw * ((float)Math.PI / 180F))));
                                    //livingentity.attackEntityFrom(DamageSource.causePlayerDamage(player), f3);
                                }
                            }

                            player.world.playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, player.getSoundCategory(), 1.0F, 1.0F);
                            //player.spawnSweepParticles();
                        }

                        if (targetEntity instanceof ServerPlayerEntity && targetEntity.velocityChanged) {
                            ((ServerPlayerEntity)targetEntity).connection.sendPacket(new SEntityVelocityPacket(targetEntity));
                            targetEntity.velocityChanged = false;
                            targetEntity.setMotion(vec3d);
                        }

                        if (flag2) {
                            player.world.playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_PLAYER_ATTACK_CRIT, player.getSoundCategory(), 1.0F, 1.0F);
                            player.onCriticalHit(targetEntity);
                        }

                        if (!flag2 && !flag3) {
                            if (flag) {
                                player.world.playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_PLAYER_ATTACK_STRONG, player.getSoundCategory(), 1.0F, 1.0F);
                            } else {
                                player.world.playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_PLAYER_ATTACK_WEAK, player.getSoundCategory(), 1.0F, 1.0F);
                            }
                        }

                        player.setLastAttackedEntity(targetEntity);
                        if (targetEntity instanceof LivingEntity) {
                            EnchantmentHelper.applyThornEnchantments((LivingEntity)targetEntity, player);
                        }

                        EnchantmentHelper.applyArthropodEnchantments(player, targetEntity);
                        ItemStack itemstack1 = player.getHeldItemMainhand();
                        Entity entity = targetEntity;
                        if (targetEntity instanceof EnderDragonPartEntity) {
                            entity = ((EnderDragonPartEntity)targetEntity).dragon;
                        }

                        if (!player.world.isRemote && !itemstack1.isEmpty() && entity instanceof LivingEntity) {
                            ItemStack copy = itemstack1.copy();
                            itemstack1.hitEntity((LivingEntity)entity, player);
                            if (itemstack1.isEmpty()) {
                                net.minecraftforge.event.ForgeEventFactory.onPlayerDestroyItem(player, copy, Hand.MAIN_HAND);
                                player.setHeldItem(Hand.MAIN_HAND, ItemStack.EMPTY);
                            }
                        }

                        if (targetEntity instanceof LivingEntity) {
                            float f5 = f4 - ((LivingEntity)targetEntity).getHealth();
                            player.addStat(Stats.DAMAGE_DEALT, Math.round(f5 * 10.0F));
                            if (j > 0) {
                                targetEntity.setFire(j * 4);
                            }

                            if (player.world instanceof ServerWorld && f5 > 2.0F) {
                                int k = (int)((double)f5 * 0.5D);
                                ((ServerWorld)player.world).spawnParticle(ParticleTypes.DAMAGE_INDICATOR, targetEntity.getPosX(), targetEntity.getPosYHeight(0.5D), targetEntity.getPosZ(), k, 0.1D, 0.0D, 0.1D, 0.2D);
                            }
                        }

                        player.addExhaustion(0.1F);
                    } else {
                        player.world.playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_PLAYER_ATTACK_NODAMAGE, player.getSoundCategory(), 1.0F, 1.0F);
                        if (flag4) {
                            targetEntity.extinguish();
                        }
                    }
                }

            }
        }
    }

    @Inject(at = @At(value = "RETURN", ordinal = 2), method = "attackEntityFrom(Lnet/minecraft/util/DamageSource;F)Z", cancellable = true)
    public void attackEntityFrom(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        double defense = ((PlayerEntity) (Object) this).getAttribute(FBAttributes.DEFENSE).getValue();

    }

}
