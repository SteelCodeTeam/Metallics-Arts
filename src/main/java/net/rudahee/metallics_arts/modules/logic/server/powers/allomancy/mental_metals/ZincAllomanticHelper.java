package net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.mental_metals;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.village.ReputationEventType;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Illusioner;
import net.minecraft.world.entity.monster.Pillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.rudahee.metallics_arts.utils.CapabilityUtils;

public class ZincAllomanticHelper {
    public static void angryEntities(Mob target, Player source) {
        target.targetSelector.enableControlFlag(Goal.Flag.TARGET);

        target.setTarget(source);
        target.setLastHurtByMob(source);
        target.setAggressive(true);

        if (target instanceof TamableAnimal tameable) {
            tameable.setTame(false);
            tameable.resetLove();
        } else if (target instanceof Creeper) {
            target.getMoveControl().setWantedPosition(source.position().x-0.5F, source.position().y, source.position().z-0.5F, 1.3f);
            target.goalSelector.addGoal(2, new SwellGoal((Creeper) target));
        } else if (target instanceof Rabbit) {
            target.goalSelector.addGoal(1, new MeleeAttackGoal((Rabbit) target, 1.4f, true));
        } else if (target instanceof AbstractSkeleton) {
            target.goalSelector.addGoal(1, new RangedBowAttackGoal<>((AbstractSkeleton) target, 1.3D, 10, 32.0F));
        } else if (target instanceof Illusioner) {
            target.goalSelector.addGoal(1, new RangedBowAttackGoal<>((Illusioner) target, 0.9D, 10, 64.0F));
        } else if (target instanceof Pillager) {
            target.goalSelector.addGoal(2, new RangedCrossbowAttackGoal<>((Pillager) target, 2.0D, 64.0F));
        } else if (target instanceof IronGolem) {
            target.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 60, 1, true, true));
            target.addEffect(new MobEffectInstance(MobEffects.GLOWING, 4, 1, true, true));
            target.goalSelector.addGoal(1, new MeleeAttackGoal((IronGolem) target, 1.4D, true));

        } else if (target instanceof Villager villager) {
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

        if (target instanceof TamableAnimal tameable) {
            tameable.setTame(false);
            tameable.resetLove();
        } else if (target instanceof Creeper) {
            target.getMoveControl().setWantedPosition(source.position().x-0.5F, source.position().y, source.position().z-0.5F, 1.3f);
            target.goalSelector.addGoal(2, new SwellGoal((Creeper) target));
        } else if (target instanceof Rabbit) {
            target.goalSelector.addGoal(1, new MeleeAttackGoal((Rabbit) target, 1.4f, true));
        } else if (target instanceof AbstractSkeleton) {
            target.goalSelector.addGoal(1, new RangedBowAttackGoal<>((AbstractSkeleton) target, 1.6D, 15, 48.0F));
        } else if (target instanceof Illusioner) {
            target.goalSelector.addGoal(1, new RangedBowAttackGoal<>((Illusioner) target, 1.3D, 14, 64.0F));
        } else if (target instanceof Pillager) {
            target.goalSelector.addGoal(2, new RangedCrossbowAttackGoal<>((Pillager) target, 2.0D, 64.0F));
        } else if (target instanceof IronGolem) {
            target.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 80, 2, true, true));
            target.addEffect(new MobEffectInstance(MobEffects.GLOWING, 6, 1, true, true));
            target.goalSelector.addGoal(1, new MeleeAttackGoal((IronGolem) target, 1.8D, true));

        } else if (target instanceof Villager villager) {
            villager.onReputationEventFrom(ReputationEventType.VILLAGER_KILLED, source);
            villager.setUnhappyCounter(100);
            villager.goalSelector.addGoal(1, new PanicGoal(villager, 1.6D));
            villager.goalSelector.addGoal(2, new AvoidEntityGoal<>(villager, Player.class, 64F, 1.3D, 1.7D));
        }
    }

    public static void angryEntitiesWithLerasium(Player source, Level world, boolean enhanced) {

        world.getEntitiesOfClass(Mob.class,
            (enhanced) ? CapabilityUtils.getBubble(source, 12) : CapabilityUtils.getBubble(source, 8)).forEach(target -> {

                if (!(target instanceof WitherBoss)) {
                target.targetSelector.enableControlFlag(Goal.Flag.TARGET);

                target.setTarget(source);
                target.setLastHurtByMob(source);
                target.setAggressive(true);

                if (enhanced){
                    target.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100, 4, true, true, true));
                    target.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 4, true, true, true));
                } else {
                    target.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100, 2, true, true, true));
                    target.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 2, true, true, true));
                }

                if (target instanceof TamableAnimal tameable) {
                    tameable.setTame(false);
                    tameable.resetLove();
                } else if (target instanceof Creeper) {
                    target.getMoveControl().setWantedPosition(source.position().x-0.5F, source.position().y, source.position().z-0.5F, 1.3f);
                    target.goalSelector.addGoal(2, new SwellGoal((Creeper) target));
                } else if (target instanceof Rabbit) {
                    target.goalSelector.addGoal(1, new MeleeAttackGoal((Rabbit) target, 1.6f, true));
                } else if (target instanceof AbstractSkeleton) {
                    target.goalSelector.addGoal(1, new RangedBowAttackGoal<>((AbstractSkeleton) target, 1.6D, 15, 48.0F));
                } else if (target instanceof Illusioner) {
                    target.goalSelector.addGoal(1, new RangedBowAttackGoal<>((Illusioner) target, 1.3D, 14, 64.0F));
                } else if (target instanceof Pillager) {
                    target.goalSelector.addGoal(2, new RangedCrossbowAttackGoal<>((Pillager) target, 2.0D, 64.0F));
                } else if (target instanceof IronGolem) {
                    target.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 80, 2, true, true));
                    target.addEffect(new MobEffectInstance(MobEffects.GLOWING, 6, 1, true, true));
                    target.goalSelector.addGoal(1, new MeleeAttackGoal((IronGolem) target, 1.8D, true));

                } else if (target instanceof Villager villager) {
                    villager.onReputationEventFrom(ReputationEventType.VILLAGER_KILLED, source);
                    villager.setUnhappyCounter(100);
                    villager.goalSelector.addGoal(1, new PanicGoal(villager, 1.6D));
                    villager.goalSelector.addGoal(2, new AvoidEntityGoal<>(villager, Player.class, 64F, 1.3D, 1.7D));
                }
            }
        });
    }
}
