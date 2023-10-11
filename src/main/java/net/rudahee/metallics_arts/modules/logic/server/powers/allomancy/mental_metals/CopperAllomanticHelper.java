package net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.mental_metals;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.rudahee.metallics_arts.modules.logic.server.server_events.on_world_tick.AllomaticTick;
import net.rudahee.metallics_arts.utils.CapabilityUtils;

import java.util.Objects;
import java.util.Optional;

/**
 * Helper class that contains the methods to use the allomantic Copper
 *
 * @author SteelCode Team
 * @since 1.5.1
 *
 * @see AllomaticTick
 */
public class CopperAllomanticHelper {

    /**
     * Manipulates the AI of entities within a certain radius of the player based on the given conditions.
     *
     * @param player   the Player object representing the player in the game world
     * @param world    the Level object representing the game world
     * @param enhanced a boolean indicating if the enhanced mode is active
     * @param lerasium a boolean indicating if lerasium is present
     */
    public static void CopperAiEntityManipulation(Player player, Level world, boolean enhanced, boolean lerasium, boolean compounding) {
        int radius = CapabilityUtils.getRadius(enhanced, lerasium, compounding);

        world.getEntitiesOfClass(Mob.class, CapabilityUtils.getBubble(player, radius)).forEach(entity -> {
            if (entity == null) {
                return;
            }

            if (!(entity instanceof WitherBoss) && !(entity instanceof EnderDragon)) {

                if (entity.goalSelector.getRunningGoals().findAny().isPresent()) {
                    Optional<WrappedGoal> goal = entity.goalSelector.getRunningGoals().findFirst();
                    goal.ifPresent(wrappedGoal -> entity.goalSelector.removeGoal(Objects.requireNonNull(wrappedGoal)));
                }

                entity.goalSelector.addGoal(1, new LookAtPlayerGoal(entity, Player.class, 1.0f));
                if (entity.getTarget() == player) {
                    entity.setAggressive(false);
                    entity.setTarget(null);
                }
            }
        });
    }
}
