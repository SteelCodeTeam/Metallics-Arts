package net.rudahee.metallics_arts.modules.custom_goals;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.setup.network.packets.BossPushEntityPacket;

import java.util.List;

public class PushBossGoal extends Goal {

    private final Mob inquisitor;
    private final Class<? extends Player> playerClazz;
    private final int MAX_DISTANCE;
    private long lastCanUseCheck;



    public PushBossGoal(Mob inquisitor, Class<? extends Player> playerClazz) {
        this.inquisitor = inquisitor;
        this.playerClazz = playerClazz;
        this.MAX_DISTANCE = 8;
    }
    

    @Override
    public boolean canUse() {

        long gameTime = this.inquisitor.level.getGameTime();

        if (gameTime - this.lastCanUseCheck < 100L) {
            return false;
        } else {

            this.lastCanUseCheck = gameTime;

            List<? extends Player> players = findTargets();


            players.forEach(player -> {
                if (Math.sqrt(inquisitor.distanceToSqr(player)) < MAX_DISTANCE) {
                    ModNetwork.sendToServer(new BossPushEntityPacket(inquisitor.getUUID(), player.getUUID()));
                }
            });
        }

        return true;
    }

    private List<? extends Player> findTargets() {
        List<? extends Player> players = null;
        TargetingConditions targetConditions = TargetingConditions.forCombat().selector( (entity) -> entity instanceof Player).range(MAX_DISTANCE);
        AABB aabb = this.inquisitor.getBoundingBox().inflate(MAX_DISTANCE, MAX_DISTANCE, MAX_DISTANCE);
        players = this.inquisitor.level.getNearbyEntities(playerClazz, targetConditions, inquisitor, aabb);

        return players;
    }
}
