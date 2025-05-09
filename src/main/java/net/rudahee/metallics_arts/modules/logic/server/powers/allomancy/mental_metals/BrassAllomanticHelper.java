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
import net.rudahee.metallics_arts.modules.logic.server.server_events.on_world_tick.AllomaticTick;
import net.rudahee.metallics_arts.setup.network.packets.ChangeEmotionPacket;
import net.rudahee.metallics_arts.utils.CapabilityUtils;

import java.util.function.Predicate;

/**
 * Esta clase es la encargada de manejar los poderes alomanticos del bronze. El bronze "contenta" a las entidades del juego
 * Hay 3 metodos principales donde se controlan los distintos modificadores que se pueden dar (con el lerasium y el duralumin)
 * para que esta clase haga su funcion, el jugador debe hacer click izquierda en la entidad.
 *
 * @author SteelCode Team
 * @since 1.5.1
 *
 * @see AllomaticTick
 * @see ChangeEmotionPacket
 */
public class BrassAllomanticHelper {
    private static final Predicate<Goal> isAggroGoal = (goal) -> goal instanceof SwellGoal || goal instanceof MeleeAttackGoal ||
            goal instanceof TargetGoal || goal instanceof PanicGoal || goal.getClass().getName().contains("Fireball") ||
            goal.getClass().getName().contains("Attack") || goal.getClass().getName().contains("Anger") || goal instanceof AvoidEntityGoal;

    /**
     * Metodo base para contentar a una entidad, sin usar modificadores, esta clase hace a la entidad objetivo no agresiva
     * y cambia su target de ataque a cualquier otro. Si la entidad no puede ser agresiva, esta solo saldra huyendo del personaje.
     * si la entidad es agresiva, entonces se te quedará mirando fijamente. Tambien puedes tamear a las entidades que lo permitan.
     *
     * @param target entity que va a ser alegrada
     * @param source jugador que alegra a la entidad
     */
    public static void happyEntities(Mob target, Player source) {
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

    /**
     * Metodo base para contentar a una entidad, con la mejora del duraluminio/nicrosil, esta clase hace a la entidad objetivo no agresiva
     * y cambia su target de ataque a cualquier otro. Si la entidad no puede ser agresiva, esta solo saldra huyendo del personaje.
     * si la entidad es agresiva, entonces se te quedará mirando fijamente. Tambien puedes tamear a las entidades que lo permitan.
     * Ademas, al estar enhanced puedes destransformar zombies aldeanos y curarlos, asi como bajar el precio de los intercambios de los
     * aldeanos.
     *
     * @param target entity que va a ser alegrada
     * @param source jugador que alegra a la entidad
     */
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

    /**
     * Metodo base para usar los dos metodos de esta clase, en forma de burbuja, en vez de hacer click a una entidad.
     * Es recomendable la lectura de los otros dos metodos para saber como funcionan.
     *
     * @param source jugador que alegra a la entidad
     * @param level mundo donde se generará la burbuja donde se alegraran las entidades (esto incluye la dimension)
     * @param enhanced boolean que comprueba si el poder se aplica con los efectos normales o los mejorados.
     *
     * @see BrassAllomanticHelper#happyEntities
     * @see BrassAllomanticHelper#happyEntitiesWithLerasium
     */
    public static void happyEntitiesWithLerasium(Player source, Level level, boolean enhanced) {

        level.getEntitiesOfClass(Mob.class,
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
