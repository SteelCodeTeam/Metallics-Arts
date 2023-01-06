package net.rudahee.metallics_arts.modules.logic.server.powers.allomancy;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

public class CopperAllomanticHelper {
    public static void CopperAiEntityManipulation(AABB axisAlignedBB, Player player, Level world) {
        world.getEntitiesOfClass(Mob.class, axisAlignedBB).forEach(entity -> {
            if (!(entity instanceof WitherBoss) && !(entity instanceof EnderDragon)) {
                entity.goalSelector.removeGoal(entity.goalSelector.getRunningGoals().findFirst().orElse(null));
                entity.goalSelector.addGoal(1, new LookAtPlayerGoal(entity, Player.class, 1.0f));
                if (entity.getTarget() == player) {
                    entity.setAggressive(false);
                    entity.setTarget(null);
                }
            }
        });
    }

    public static void CopperEnhancedAiEntityManipulation(AABB axisAlignedBB, Player player, Level world) {
        world.getEntitiesOfClass(Mob.class, axisAlignedBB).forEach(entity -> {
            if (!(entity instanceof WitherBoss) && !(entity instanceof EnderDragon)) {
                entity.goalSelector.removeGoal(entity.goalSelector.getRunningGoals().findFirst().orElse(null));
                entity.goalSelector.addGoal(1, new LookAtPlayerGoal(entity, Player.class, 3.0f));
                if (entity.getTarget() == player) {
                    entity.setAggressive(false);
                    entity.setTarget(null);
                }
            }
            entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 2, true, true, true));
            entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 2, true, true, true));
        });
    }
}
