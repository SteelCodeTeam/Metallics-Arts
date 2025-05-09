package net.rudahee.metallics_arts.modules.custom_goals;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.physical_metals.IronAndSteelHelpers;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.setup.network.packets.BossPullEntityPacket;

import java.util.List;

public class PullBossGoal extends Goal {

    private final Mob inquisitor;
    private final Class<? extends Player> playerClazz;
    private final int MAX_DISTANCE;
    private final int PHASE;
    private long lastCanUseCheck;



    public PullBossGoal(Mob inquisitor, Class<? extends Player> playerClazz, int phase) {
        this.inquisitor = inquisitor;
        this.playerClazz = playerClazz;
        this.PHASE = phase;
        if (phase == 0) {
            MAX_DISTANCE = 24;
        } else {
            MAX_DISTANCE = 32;
        }
    }
    

    @Override
    public boolean canUse() {

        long gameTime = this.inquisitor.level.getGameTime();
        if (gameTime - this.lastCanUseCheck < 100L) {
            return false;
        } else {
            this.lastCanUseCheck = gameTime;

            if (PHASE == 0) {

                Player player = findTarget();

                if (player == null) {
                    return false;
                }

                if (Math.sqrt(inquisitor.distanceToSqr(player)) > MAX_DISTANCE) {
                    return false;
                }

                ModNetwork.sendToServer(new BossPullEntityPacket(inquisitor.getUUID(), player.getUUID(), IronAndSteelHelpers.PULL, 0));
            } else if (PHASE == 1) {
                List<? extends Player> players = findTargets();


                players.forEach(player -> {
                    if (Math.sqrt(inquisitor.distanceToSqr(player)) < MAX_DISTANCE) {
                        ModNetwork.sendToServer(new BossPullEntityPacket(inquisitor.getUUID(), player.getUUID(), IronAndSteelHelpers.PULL, 1));
                    }
                });
            }
            return true;
        }
    }

    private List<? extends Player> findTargets() {
        List<? extends Player> players = null;
        TargetingConditions targetConditions = TargetingConditions.forCombat().selector( (entity) -> entity instanceof Player).range(MAX_DISTANCE);
        AABB aabb = this.inquisitor.getBoundingBox().inflate(MAX_DISTANCE, MAX_DISTANCE, MAX_DISTANCE);
        players = this.inquisitor.level.getNearbyEntities(playerClazz, targetConditions, inquisitor, aabb);

        return players;
    }

    protected Player findTarget() {
        Player player = null;
        TargetingConditions targetConditions = TargetingConditions.forCombat().selector( (entity) -> entity instanceof Player).range(MAX_DISTANCE);

        player = this.inquisitor.level.getNearestPlayer(targetConditions, this.inquisitor, this.inquisitor.getX(), this.inquisitor.getEyeY(), this.inquisitor.getZ());

        return player;

    }
}
