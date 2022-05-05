package net.rudahee.metallics_arts.modules.powers.helpers;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class CopperAndBronzeHelpers {
    public static void BronzeAiEntityManipulation(AxisAlignedBB axisAlignedBB, PlayerEntity player, World world) {
        world.getEntitiesOfClass(MobEntity.class, axisAlignedBB).forEach(entity -> {
            entity.goalSelector.removeGoal(entity.goalSelector.getRunningGoals().findFirst().orElse(null));
            entity.getLookControl().setLookAt(player.position().x, player.position().y, player.position().z);
            entity.getMoveControl().setWantedPosition(player.position().x-0.5F, player.position().y, player.position().z-0.5F, 1.2f);
        });
    }

    public static void CopperAiEntityManipulation(AxisAlignedBB axisAlignedBB, PlayerEntity player, World world) {
        world.getEntitiesOfClass(MobEntity.class, axisAlignedBB).forEach(entity -> {
            if (!(entity instanceof WitherEntity) && !(entity instanceof EnderDragonEntity)) {
                entity.goalSelector.removeGoal(entity.goalSelector.getRunningGoals().findFirst().orElse(null));
                entity.goalSelector.addGoal(1, new LookRandomlyGoal(entity));
                if (entity.getTarget() == player) {
                    entity.setAggressive(false);
                    entity.setTarget(null);
                }
            }
        });
    }
}
