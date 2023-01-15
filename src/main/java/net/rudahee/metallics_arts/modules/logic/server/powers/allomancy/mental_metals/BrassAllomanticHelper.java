package net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.mental_metals;


import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.village.ReputationEventType;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.rudahee.metallics_arts.utils.CapabilityUtils;

import java.util.function.Predicate;

public class BrassAllomanticHelper {


    private static final Predicate<Goal> isAggroGoal = (goal) -> goal instanceof SwellGoal || goal instanceof MeleeAttackGoal ||
            goal instanceof TargetGoal || goal instanceof PanicGoal || goal.getClass().getName().contains("Fireball") ||
            goal.getClass().getName().contains("Attack") || goal.getClass().getName().contains("Anger") || goal instanceof AvoidEntityGoal;

    public static void happyEntities(Mob target, Player source) {
        target.targetSelector.enableControlFlag(Goal.Flag.TARGET);

        target.setAggressive(false);

        target.goalSelector.getRunningGoals().filter(isAggroGoal).forEach(WrappedGoal::stop );
        target.targetSelector.getRunningGoals().filter(isAggroGoal).forEach(WrappedGoal::stop);

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
        } else if (target instanceof Villager villager) {
            villager.onReputationEventFrom(ReputationEventType.ZOMBIE_VILLAGER_CURED, source);
            villager.goalSelector.getRunningGoals().filter(isAggroGoal).forEach(villager.goalSelector::removeGoal);
            villager.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 60, 2, true, true));
        }
    }

    public static void happyEntitiesEnhanced(Mob target, Player source) {
        target.targetSelector.enableControlFlag(Goal.Flag.TARGET);

        target.setAggressive(false);
        target.goalSelector.getRunningGoals().filter(isAggroGoal).forEach(WrappedGoal::stop);
        target.targetSelector.getRunningGoals().filter(isAggroGoal).forEach(WrappedGoal::stop);

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
        } else if (target instanceof Villager villager) {
            villager.onReputationEventFrom(ReputationEventType.ZOMBIE_VILLAGER_CURED, source);
            villager.goalSelector.getRunningGoals().filter(isAggroGoal).forEach(villager.goalSelector::removeGoal);
            villager.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 80, 2, true, true));
        }
    }

    public static void happyEntitiesWithLerasium(Player source, Level world, boolean enhanced) {

        world.getEntitiesOfClass(Mob.class,
                (enhanced) ? CapabilityUtils.getBubble(source, 12) : CapabilityUtils.getBubble(source, 8)).forEach(target -> {
            target.targetSelector.enableControlFlag(Goal.Flag.TARGET);

            target.setAggressive(false);
            target.goalSelector.getRunningGoals().filter(isAggroGoal).forEach(WrappedGoal::stop);
            target.targetSelector.getRunningGoals().filter(isAggroGoal).forEach(WrappedGoal::stop);

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
            } else if (target instanceof Villager villager) {
                villager.onReputationEventFrom(ReputationEventType.ZOMBIE_VILLAGER_CURED, source);
                villager.goalSelector.getRunningGoals().filter(isAggroGoal).forEach(villager.goalSelector::removeGoal);
                villager.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 80, 2, true, true));
            }
        });
    }



}
