package net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.mental_metals;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.rudahee.metallics_arts.modules.logic.server.server_events.on_world_tick.AllomaticTick;
import net.rudahee.metallics_arts.utils.CapabilityUtils;

import java.util.Objects;

/**
 * Helper class for handling the effects of burning Bronze in an Allomantic system.
 *
 * @author SteelCode Team
 * @since 1.5.1
 *
 * @see AllomaticTick
 */
public class BronzeAllomanticHelper {

    /**
     * Manipulates AI entities based on the player's Bronze Allomantic abilities.
     *
     * @param player   The player burning Bronze.
     * @param world    The level in which the player is located.
     * @param enhanced A boolean flag indicating if the player's abilities are enhanced.
     * @param lerasium A boolean flag indicating if the player is burning Lerasium.
     */
    public static void BronzeAiEntityManipulation(Player player, Level world, boolean enhanced, boolean lerasium) {
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
            if (entity == null) {
                return;
            }

            entity.goalSelector.removeGoal(Objects.requireNonNull(entity.goalSelector.getRunningGoals().findFirst().orElse(null)));
            entity.getLookControl().setLookAt(player.position().x, player.position().y, player.position().z);
            entity.getMoveControl().setWantedPosition(player.position().x-0.5F, player.position().y, player.position().z-0.5F, 1.2f);
        });
    }

}
