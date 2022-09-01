package net.rudahee.metallics_arts.modules.powers.helpers;


import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.village.ReputationEventType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Illusioner;
import net.minecraft.world.entity.monster.Pillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.Collection;
import java.util.function.Predicate;

public class ZincAndBrassHelpers {


    public static void drawSaturatedScreen(Player entityLiving) {

    }

    public static void angryEntities(Mob target, Player source) {
        target.targetSelector.enableControlFlag(Goal.Flag.TARGET);

        target.setTarget(source);
        target.setLastHurtByMob(source);
        target.setAggressive(true);

        if (target instanceof TamableAnimal) {
            TamableAnimal tameable = (TamableAnimal) target;
          tameable.setTame(false);
          tameable.resetLove();
        } else if (target instanceof Creeper) {
            target.getMoveControl().setWantedPosition(source.position().x-0.5F, source.position().y, source.position().z-0.5F, 1.3f);
            target.goalSelector.addGoal(2, new CreeperSwellGoal((Creeper) target));
        } else if (target instanceof Rabbit) {
            target.goalSelector.addGoal(1, new MeleeAttackGoal(target, 1.4f, true));
        } else if (target instanceof AbstractSkeleton) {
            target.goalSelector.addGoal(1, new RangedBowAttackGoal<>((AbstractSkeleton) target, 1.3D, 10, 32.0F));
        } else if (target instanceof Illusioner) {
            target.goalSelector.addGoal(1, new RangedBowAttackGoal<>((Illusioner) target, 0.9D, 10, 64.0F));
        } else if (target instanceof Pillager) {
            target.goalSelector.addGoal(2, new RangedCrossbowAttackGoal<>((Pillager) target, 2.0D, 64.0F));
        } else if (target instanceof IronGolem) {
            target.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 60, 1, true, true));
            target.addEffect(new MobEffectInstance(MobEffects.GLOWING, 4, 1, true, true));
            target.goalSelector.addGoal(1, new MeleeAttackGoal(IronGolem.class, 1.4D, true));

        } else if (target instanceof Villager) {
            Villager villager = (Villager) target;
            villager.onReputationEventFrom(ReputationEventType.VILLAGER_KILLED, source);
            villager.setUnhappyCounter(50);
            villager.goalSelector.addGoal(1, new PanicGoal(villager, 1.3D));
            villager.goalSelector.addGoal(2, new AvoidEntityGoal<>(villager, Player.class, 64F, 1.0D, 1.6D));
        }
    }

    public static void angryEntitiesEnhanced(Mob target, Player source) {
        target.targetSelector.enableControlFlag(Goal.Flag.TARGET);

        target.setTarget(source);
        target.setLastHurtByMob(source);
        target.setAggressive(true);

        target.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100, 2, true, true, true));
        target.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 2, true, true, true));


        if (target instanceof TamableAnimal) {
            TamableAnimal tameable = (TamableAnimal) target;
            tameable.setTame(false);
            tameable.resetLove();
        } else if (target instanceof Creeper) {
            target.getMoveControl().setWantedPosition(source.position().x-0.5F, source.position().y, source.position().z-0.5F, 1.3f);
            target.goalSelector.addGoal(2, new CreeperSwellGoal((Creeper) target));
        } else if (target instanceof Rabbit) {
            target.goalSelector.addGoal(1, new MeleeAttackGoal(target, 1.6f, true));
        } else if (target instanceof AbstractSkeleton) {
            target.goalSelector.addGoal(1, new RangedBowAttackGoal<>((AbstractSkeleton) target, 1.6D, 15, 48.0F));
        } else if (target instanceof Illusioner) {
            target.goalSelector.addGoal(1, new RangedBowAttackGoal<>((Illusioner) target, 1.3D, 14, 64.0F));
        } else if (target instanceof Pillager) {
            target.goalSelector.addGoal(2, new RangedCrossbowAttackGoal<>((Pillager) target, 2.0D, 64.0F));
        } else if (target instanceof IronGolem) {
            target.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 80, 2, true, true));
            target.addEffect(new MobEffectInstance(MobEffects.GLOWING, 6, 1, true, true));
            target.goalSelector.addGoal(1, new MeleeAttackGoal(target, 1.8D, true));

        } else if (target instanceof Villager) {
            Villager villager = (Villager) target;
            villager.onReputationEventFrom(ReputationEventType.VILLAGER_KILLED, source);
            villager.setUnhappyCounter(100);
            villager.goalSelector.addGoal(1, new PanicGoal(villager, 1.6D));
            villager.goalSelector.addGoal(2, new AvoidEntityGoal<>(villager, Player.class, 64F, 1.3D, 1.7D));
        }
    }

    public static void angryEntitiesWithLerasium(Player source, Level world, AABB axisAlignedBB, boolean duralumin) {
        world.getEntitiesOfClass(Mob.class, axisAlignedBB).forEach(target -> {
            if (!(target instanceof WitherBoss)) {
                target.targetSelector.enableControlFlag(Goal.Flag.TARGET);

                target.setTarget(source);
                target.setLastHurtByMob(source);
                target.setAggressive(true);

                if (duralumin){
                    target.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100, 4, true, true, true));
                    target.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 4, true, true, true));
                } else {
                    target.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100, 2, true, true, true));
                    target.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 2, true, true, true));
                }

                if (target instanceof TamableAnimal) {
                    TamableAnimal tameable = (TamableAnimal) target;
                    tameable.setTame(false);
                    tameable.resetLove();
                } else if (target instanceof Creeper) {
                    target.getMoveControl().setWantedPosition(source.position().x-0.5F, source.position().y, source.position().z-0.5F, 1.3f);
                    target.goalSelector.addGoal(2, new CreeperSwellGoal((Creeper) target));
                } else if (target instanceof Rabbit) {
                    target.goalSelector.addGoal(1, new MeleeAttackGoal(target, 1.6f, true));
                } else if (target instanceof AbstractSkeleton) {
                    target.goalSelector.addGoal(1, new RangedBowAttackGoal<>((AbstractSkeleton) target, 1.6D, 15, 48.0F));
                } else if (target instanceof Illusioner) {
                    target.goalSelector.addGoal(1, new RangedBowAttackGoal<>((Illusioner) target, 1.3D, 14, 64.0F));
                } else if (target instanceof Pillager) {
                    target.goalSelector.addGoal(2, new RangedCrossbowAttackGoal<>((Pillager) target, 2.0D, 64.0F));
                } else if (target instanceof IronGolem) {
                    target.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 80, 2, true, true));
                    target.addEffect(new MobEffectInstance(MobEffects.GLOWING, 6, 1, true, true));
                    target.goalSelector.addGoal(1, new MeleeAttackGoal(target, 1.8D, true));

                } else if (target instanceof Villager) {
                    Villager villager = (Villager) target;
                    villager.onReputationEventFrom(ReputationEventType.VILLAGER_KILLED, source);
                    villager.setUnhappyCounter(100);
                    villager.goalSelector.addGoal(1, new PanicGoal(villager, 1.6D));
                    villager.goalSelector.addGoal(2, new AvoidEntityGoal<>(villager, Player.class, 64F, 1.3D, 1.7D));
                }
            }
        });
    }

    private static final Predicate<Goal> isAggroGoal = (goal) -> goal instanceof CreeperSwellGoal || goal instanceof MeleeAttackGoal ||
            goal instanceof TargetGoal || goal instanceof PanicGoal || goal.getClass().getName().contains("Fireball") ||
            goal.getClass().getName().contains("Attack") || goal.getClass().getName().contains("Anger") || goal instanceof AvoidEntityGoal;

    public static void happyEntities(Mob target, Player source) {
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
        target.goalSelector.addGoal(2, new LookAtPlayerGoal(target, source.getClass(), 6.0F));


        if (target instanceof TamableAnimal) {
            if (Math.random() > 0.9) {
                ((TamableAnimal) target).tame(source);
            }
        } else if (target instanceof AbstractHorse) {
            if (Math.random() > 0.9) {
                ((AbstractHorse) target).tameWithName(source);
            }
        } else if (target instanceof Sheep) {
            target.goalSelector.addGoal(1, new EatBlockGoal(target));
        } else if (target instanceof Villager) {
            Villager villager = (Villager) target;
            villager.onReputationEventFrom(ReputationEventType.ZOMBIE_VILLAGER_CURED, source);
            villager.goalSelector.getRunningGoals().filter(isAggroGoal).forEach(goal -> {
                villager.goalSelector.removeGoal(goal);
            });
            villager.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 60, 2, true, true));
        }
    }

    public static void happyEntitiesEnhanced(Mob target, Player source) {
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
        target.goalSelector.addGoal(2, new LookAtPlayerGoal(target, Player.class, 12.0F));


        if (target instanceof TamableAnimal) {
            ((TamableAnimal) target).tame(source);

        } else if (target instanceof AbstractHorse) {
            ((AbstractHorse) target).tameWithName(source);
        } else if (target instanceof Sheep) {
            target.goalSelector.addGoal(1, new EatBlockGoal(target));
        } else if (target instanceof Villager) {
            Villager villager = (Villager) target;
            villager.onReputationEventFrom(ReputationEventType.ZOMBIE_VILLAGER_CURED, source);
            villager.goalSelector.getRunningGoals().filter(isAggroGoal).forEach(goal -> {
                villager.goalSelector.removeGoal(goal);
            });
            villager.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 80, 2, true, true));
        }
    }

    public static void happyEntitiesWithLerasium(Player source, Level world, AABB axisAlignedBB,boolean duralumin){
        world.getEntitiesOfClass(Mob.class, axisAlignedBB).forEach(target -> {
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
            target.goalSelector.addGoal(2, new LookAtPlayerGoal(target, Player.class, 12.0F));

            if (target instanceof TamableAnimal) {
                ((TamableAnimal) target).tame(source);

            } else if (target instanceof AbstractHorse) {
                ((AbstractHorse) target).tameWithName(source);
            } else if (target instanceof Sheep) {
                target.goalSelector.addGoal(1, new EatBlockGoal(target));
            } else if (target instanceof Villager) {
                Villager villager = (Villager) target;
                villager.onReputationEventFrom(ReputationEventType.ZOMBIE_VILLAGER_CURED, source);
                villager.goalSelector.getRunningGoals().filter(isAggroGoal).forEach(goal -> {
                    villager.goalSelector.removeGoal(goal);
                });
                villager.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 80, 2, true, true));
            }
        });
    }

    public static void addFireAspectToPlayer(LivingEntity livingEntity, int secondsFire){
        livingEntity.setSecondsOnFire(secondsFire);
    }

    public static void addLootToEnemy(LivingEntity livingEntity, double percentaje) {
        if(!(livingEntity instanceof Player)){
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
        if(!(livingEntity instanceof Player)){
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
