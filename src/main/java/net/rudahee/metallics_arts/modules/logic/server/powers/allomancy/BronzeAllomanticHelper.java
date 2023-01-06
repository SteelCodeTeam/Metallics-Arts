package net.rudahee.metallics_arts.modules.logic.server.powers.allomancy;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

public class BronzeAllomanticHelper {

    public static void BronzeAiEntityManipulation(AABB axisAlignedBB, Player player, Level world) {
        world.getEntitiesOfClass(Mob.class, axisAlignedBB).forEach(entity -> {
            entity.goalSelector.removeGoal(entity.goalSelector.getRunningGoals().findFirst().orElse(null));
            entity.getLookControl().setLookAt(player.position().x, player.position().y, player.position().z);
            entity.getMoveControl().setWantedPosition(player.position().x-0.5F, player.position().y, player.position().z-0.5F, 1.2f);
        });
    }

    public static void BronzeEnhancedAiEntityManipulation(AABB axisAlignedBB, Player player, Level world) {
        world.getEntitiesOfClass(Mob.class, axisAlignedBB).forEach(entity -> {
            entity.goalSelector.removeGoal(entity.goalSelector.getRunningGoals().findFirst().orElse(null));
            entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 2, true, true, true));
            entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100, 1, true, true, true));
            entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 1, true, true, true));

            entity.getLookControl().setLookAt(player.position().x, player.position().y, player.position().z);
            entity.getMoveControl().setWantedPosition(player.position().x-0.5F, player.position().y, player.position().z-0.5F, 1.6f);
        });
    }




}
