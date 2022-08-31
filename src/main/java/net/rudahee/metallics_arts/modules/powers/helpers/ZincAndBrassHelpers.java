package net.rudahee.metallics_arts.modules.powers.helpers;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.merchant.IReputationType;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.AbstractSkeletonEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.IllusionerEntity;
import net.minecraft.entity.monster.PillagerEntity;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.Collection;
import java.util.function.Predicate;

public class ZincAndBrassHelpers {


    public static void drawSaturatedScreen(PlayerEntity entityLiving) {

    }

    public static void angryEntities(CreatureEntity target, PlayerEntity source) {
        target.targetSelector.enableControlFlag(Goal.Flag.TARGET);

        target.setTarget(source);
        target.setLastHurtByMob(source);
        target.setAggressive(true);

        if (target instanceof TameableEntity) {
          TameableEntity tameable = (TameableEntity) target;
          tameable.setTame(false);
          tameable.resetLove();
        } else if (target instanceof CreeperEntity) {
            target.getMoveControl().setWantedPosition(source.position().x-0.5F, source.position().y, source.position().z-0.5F, 1.3f);
            target.goalSelector.addGoal(2, new CreeperSwellGoal((CreeperEntity) target));
        } else if (target instanceof RabbitEntity) {
            target.goalSelector.addGoal(1, new MeleeAttackGoal(target, 1.4f, true));
        } else if (target instanceof AbstractSkeletonEntity) {
            target.goalSelector.addGoal(1, new RangedBowAttackGoal<>((AbstractSkeletonEntity) target, 1.3D, 10, 32.0F));
        } else if (target instanceof IllusionerEntity) {
            target.goalSelector.addGoal(1, new RangedBowAttackGoal<>((IllusionerEntity) target, 0.9D, 10, 64.0F));
        } else if (target instanceof PillagerEntity) {
            target.goalSelector.addGoal(2, new RangedCrossbowAttackGoal<>((PillagerEntity) target, 2.0D, 64.0F));
        } else if (target instanceof GolemEntity) {
            target.addEffect(new EffectInstance(Effects.REGENERATION, 60, 1, true, true));
            target.addEffect(new EffectInstance(Effects.GLOWING, 4, 1, true, true));
            target.goalSelector.addGoal(1, new MeleeAttackGoal(target, 1.4D, true));

        } else if (target instanceof VillagerEntity) {
            VillagerEntity villager = (VillagerEntity) target;
            villager.onReputationEventFrom(IReputationType.VILLAGER_KILLED, source);
            villager.setUnhappyCounter(50);
            villager.goalSelector.addGoal(1, new PanicGoal(villager, 1.3D));
            villager.goalSelector.addGoal(2, new AvoidEntityGoal<>(villager, PlayerEntity.class, 64F, 1.0D, 1.6D));
        }
    }

    public static void angryEntitiesEnhanced(CreatureEntity target, PlayerEntity source) {
        target.targetSelector.enableControlFlag(Goal.Flag.TARGET);

        target.setTarget(source);
        target.setLastHurtByMob(source);
        target.setAggressive(true);

        target.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, 100, 2, true, true, true));
        target.addEffect(new EffectInstance(Effects.DAMAGE_RESISTANCE, 100, 2, true, true, true));


        if (target instanceof TameableEntity) {
            TameableEntity tameable = (TameableEntity) target;
            tameable.setTame(false);
            tameable.resetLove();
        } else if (target instanceof CreeperEntity) {
            target.getMoveControl().setWantedPosition(source.position().x-0.5F, source.position().y, source.position().z-0.5F, 1.3f);
            target.goalSelector.addGoal(2, new CreeperSwellGoal((CreeperEntity) target));
        } else if (target instanceof RabbitEntity) {
            target.goalSelector.addGoal(1, new MeleeAttackGoal(target, 1.6f, true));
        } else if (target instanceof AbstractSkeletonEntity) {
            target.goalSelector.addGoal(1, new RangedBowAttackGoal<>((AbstractSkeletonEntity) target, 1.6D, 15, 48.0F));
        } else if (target instanceof IllusionerEntity) {
            target.goalSelector.addGoal(1, new RangedBowAttackGoal<>((IllusionerEntity) target, 1.3D, 14, 64.0F));
        } else if (target instanceof PillagerEntity) {
            target.goalSelector.addGoal(2, new RangedCrossbowAttackGoal<>((PillagerEntity) target, 2.0D, 64.0F));
        } else if (target instanceof GolemEntity) {
            target.addEffect(new EffectInstance(Effects.REGENERATION, 80, 2, true, true));
            target.addEffect(new EffectInstance(Effects.GLOWING, 6, 1, true, true));
            target.goalSelector.addGoal(1, new MeleeAttackGoal(target, 1.8D, true));

        } else if (target instanceof VillagerEntity) {
            VillagerEntity villager = (VillagerEntity) target;
            villager.onReputationEventFrom(IReputationType.VILLAGER_KILLED, source);
            villager.setUnhappyCounter(100);
            villager.goalSelector.addGoal(1, new PanicGoal(villager, 1.6D));
            villager.goalSelector.addGoal(2, new AvoidEntityGoal<>(villager, PlayerEntity.class, 64F, 1.3D, 1.7D));
        }
    }

    public static void angryEntitiesWithLerasium(PlayerEntity source, World world, AxisAlignedBB axisAlignedBB,boolean duralumin) {
        world.getEntitiesOfClass(CreatureEntity.class, axisAlignedBB).forEach(target -> {
            if (!(target instanceof WitherEntity)) {
                target.targetSelector.enableControlFlag(Goal.Flag.TARGET);

                target.setTarget(source);
                target.setLastHurtByMob(source);
                target.setAggressive(true);

                if (duralumin){
                    target.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, 100, 4, true, true, true));
                    target.addEffect(new EffectInstance(Effects.DAMAGE_RESISTANCE, 100, 4, true, true, true));
                } else {
                    target.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, 100, 2, true, true, true));
                    target.addEffect(new EffectInstance(Effects.DAMAGE_RESISTANCE, 100, 2, true, true, true));
                }

                if (target instanceof TameableEntity) {
                    TameableEntity tameable = (TameableEntity) target;
                    tameable.setTame(false);
                    tameable.resetLove();
                } else if (target instanceof CreeperEntity) {
                    target.getMoveControl().setWantedPosition(source.position().x-0.5F, source.position().y, source.position().z-0.5F, 1.3f);
                    target.goalSelector.addGoal(2, new CreeperSwellGoal((CreeperEntity) target));
                } else if (target instanceof RabbitEntity) {
                    target.goalSelector.addGoal(1, new MeleeAttackGoal(target, 1.6f, true));
                } else if (target instanceof AbstractSkeletonEntity) {
                    target.goalSelector.addGoal(1, new RangedBowAttackGoal<>((AbstractSkeletonEntity) target, 1.6D, 15, 48.0F));
                } else if (target instanceof IllusionerEntity) {
                    target.goalSelector.addGoal(1, new RangedBowAttackGoal<>((IllusionerEntity) target, 1.3D, 14, 64.0F));
                } else if (target instanceof PillagerEntity) {
                    target.goalSelector.addGoal(2, new RangedCrossbowAttackGoal<>((PillagerEntity) target, 2.0D, 64.0F));
                } else if (target instanceof GolemEntity) {
                    target.addEffect(new EffectInstance(Effects.REGENERATION, 80, 2, true, true));
                    target.addEffect(new EffectInstance(Effects.GLOWING, 6, 1, true, true));
                    target.goalSelector.addGoal(1, new MeleeAttackGoal(target, 1.8D, true));

                } else if (target instanceof VillagerEntity) {
                    VillagerEntity villager = (VillagerEntity) target;
                    villager.onReputationEventFrom(IReputationType.VILLAGER_KILLED, source);
                    villager.setUnhappyCounter(100);
                    villager.goalSelector.addGoal(1, new PanicGoal(villager, 1.6D));
                    villager.goalSelector.addGoal(2, new AvoidEntityGoal<>(villager, PlayerEntity.class, 64F, 1.3D, 1.7D));
                }
            }
        });
    }

    private static final Predicate<Goal> isAggroGoal = (goal) -> goal instanceof CreeperSwellGoal || goal instanceof MeleeAttackGoal ||
            goal instanceof TargetGoal || goal instanceof PanicGoal || goal.getClass().getName().contains("Fireball") ||
            goal.getClass().getName().contains("Attack") || goal.getClass().getName().contains("Anger") || goal instanceof AvoidEntityGoal;

    public static void happyEntities(CreatureEntity target, PlayerEntity source) {
        target.targetSelector.enableControlFlag(Goal.Flag.TARGET);

        target.setAggressive(false);
        target.goalSelector.getRunningGoals().filter(isAggroGoal).forEach(PrioritizedGoal::stop);
        target.targetSelector.getRunningGoals().filter(isAggroGoal).forEach(PrioritizedGoal::stop);

        target.setTarget(null);
        target.setLastHurtByMob(null);
        //Disable targeting as a whole
        target.targetSelector.disableControlFlag(Goal.Flag.TARGET);
        target.setAggressive(false);
        //Add new goals
        target.goalSelector.addGoal(2, new LookAtGoal(target, PlayerEntity.class, 6.0F));


        if (target instanceof TameableEntity) {
            if (Math.random() > 0.9) {
                ((TameableEntity) target).tame(source);
            }
        } else if (target instanceof AbstractHorseEntity) {
            if (Math.random() > 0.9) {
                ((AbstractHorseEntity) target).tameWithName(source);
            }
        } else if (target instanceof SheepEntity) {
            target.goalSelector.addGoal(1, new EatGrassGoal(target));
        } else if (target instanceof VillagerEntity) {
            VillagerEntity villager = (VillagerEntity) target;
            villager.onReputationEventFrom(IReputationType.ZOMBIE_VILLAGER_CURED, source);
            villager.goalSelector.getRunningGoals().filter(isAggroGoal).forEach(goal -> {
                villager.goalSelector.removeGoal(goal);
            });
            villager.addEffect(new EffectInstance(Effects.REGENERATION, 60, 2, true, true));
        }
    }

    public static void happyEntitiesEnhanced(CreatureEntity target, PlayerEntity source) {
        target.targetSelector.enableControlFlag(Goal.Flag.TARGET);

        target.setAggressive(false);
        target.goalSelector.getRunningGoals().filter(isAggroGoal).forEach(PrioritizedGoal::stop);
        target.targetSelector.getRunningGoals().filter(isAggroGoal).forEach(PrioritizedGoal::stop);

        target.setTarget(null);
        target.setLastHurtByMob(null);
        //Disable targeting as a whole
        target.targetSelector.disableControlFlag(Goal.Flag.TARGET);
        target.setAggressive(false);
        //Add new goals
        target.goalSelector.addGoal(2, new LookAtGoal(target, PlayerEntity.class, 12.0F));


        if (target instanceof TameableEntity) {
            ((TameableEntity) target).tame(source);

        } else if (target instanceof AbstractHorseEntity) {
            ((AbstractHorseEntity) target).tameWithName(source);
        } else if (target instanceof SheepEntity) {
            target.goalSelector.addGoal(1, new EatGrassGoal(target));
        } else if (target instanceof VillagerEntity) {
            VillagerEntity villager = (VillagerEntity) target;
            villager.onReputationEventFrom(IReputationType.ZOMBIE_VILLAGER_CURED, source);
            villager.goalSelector.getRunningGoals().filter(isAggroGoal).forEach(goal -> {
                villager.goalSelector.removeGoal(goal);
            });
            villager.addEffect(new EffectInstance(Effects.REGENERATION, 80, 2, true, true));
        }
    }

    public static void happyEntitiesWithLerasium(PlayerEntity source, World world, AxisAlignedBB axisAlignedBB,boolean duralumin){
        world.getEntitiesOfClass(CreatureEntity.class, axisAlignedBB).forEach(target -> {
            target.targetSelector.enableControlFlag(Goal.Flag.TARGET);

            target.setAggressive(false);
            target.goalSelector.getRunningGoals().filter(isAggroGoal).forEach(PrioritizedGoal::stop);
            target.targetSelector.getRunningGoals().filter(isAggroGoal).forEach(PrioritizedGoal::stop);

            target.setTarget(null);
            target.setLastHurtByMob(null);
            //Disable targeting as a whole
            target.targetSelector.disableControlFlag(Goal.Flag.TARGET);
            target.setAggressive(false);
            //Add new goals
            target.goalSelector.addGoal(2, new LookAtGoal(target, PlayerEntity.class, 12.0F));

            if (target instanceof TameableEntity) {
                ((TameableEntity) target).tame(source);

            } else if (target instanceof AbstractHorseEntity) {
                ((AbstractHorseEntity) target).tameWithName(source);
            } else if (target instanceof SheepEntity) {
                target.goalSelector.addGoal(1, new EatGrassGoal(target));
            } else if (target instanceof VillagerEntity) {
                VillagerEntity villager = (VillagerEntity) target;
                villager.onReputationEventFrom(IReputationType.ZOMBIE_VILLAGER_CURED, source);
                villager.goalSelector.getRunningGoals().filter(isAggroGoal).forEach(goal -> {
                    villager.goalSelector.removeGoal(goal);
                });
                villager.addEffect(new EffectInstance(Effects.REGENERATION, 80, 2, true, true));
            }
        });
    }

    public static void addFireAspectToPlayer(LivingEntity livingEntity,int secondsFire){
        livingEntity.setSecondsOnFire(secondsFire);
    }

    public static void addLootToEnemy(LivingEntity livingEntity, double percentaje) {
        if(!(livingEntity instanceof PlayerEntity)){
            if (!livingEntity.isAlive()){
                if(Math.random()<percentaje){
                    Collection<ItemEntity> drops = livingEntity.captureDrops();
                    drops.addAll(drops);
                }
            }
        }else {
            //si matas a un pj
        }
    }

    public static void removeLootToEnemy(LivingEntity livingEntity, double percentaje) {
        if(!(livingEntity instanceof PlayerEntity)){
            if (!livingEntity.isAlive()){
                if(Math.random()<percentaje){
                    //evitar drops
                    Collection<ItemEntity> drops = livingEntity.captureDrops();
                    drops.removeAll(drops);
                }
            }
        }else {
            //si matas a un pj
        }
    }
}
