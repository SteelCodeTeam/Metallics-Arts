package net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.mental_metals;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.rudahee.metallics_arts.utils.CapabilityUtils;

import java.util.Objects;

public class CopperAllomanticHelper {
    public static void CopperAiEntityManipulation(Player player, Level world, boolean enhanced, boolean lerasium) {
        int radius;

        if (enhanced && lerasium) {
            radius = 16;
        } else if (enhanced) {
            radius = 14;
        } else if (lerasium) {
            radius = 12;
        } else {
            radius = 8;
        }

        world.getEntitiesOfClass(Mob.class, CapabilityUtils.getBubble(player, radius)).forEach(entity -> {
            if (!(entity instanceof WitherBoss) && !(entity instanceof EnderDragon)) {
                entity.goalSelector.removeGoal(Objects.requireNonNull(entity.goalSelector.getRunningGoals().findFirst().orElse(null)));
                entity.goalSelector.addGoal(1, new LookAtPlayerGoal(entity, Player.class, 1.0f));
                if (entity.getTarget() == player) {
                    entity.setAggressive(false);
                    entity.setTarget(null);
                }
            }
        });
    }
}
