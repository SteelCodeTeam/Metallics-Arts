package net.rudahee.metallics_arts.modules.powers.helpers;

import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.ResetAngerGoal;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.monster.PhantomEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.Random;

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

    public static void DontSpawnPhantoms(PlayerEntity player, AxisAlignedBB axisAlignedBB, World world) {
        world.getEntitiesOfClass(PhantomEntity.class, axisAlignedBB).forEach(entity -> {
            if (entity instanceof PhantomEntity) {
                if (entity.getTarget() == player) {
                    entity.setAggressive(false);
                    entity.setTarget(null);
                }
            }
        });
    }

    public static void SpawnPhamtonsWithFireResistance(PlayerEntity player, World world) {
        // TODO
    }

    public static void saveExperience(PlayerEntity player, World world) {
        if (player.totalExperience > 0) {
            player.giveExperiencePoints(-7);
        }
        world.playLocalSound(player.getX(), player.getY(), player.getZ(), SoundEvents.EXPERIENCE_BOTTLE_THROW, SoundCategory.PLAYERS, 0.1F, (new Random().nextFloat() - new Random().nextFloat()) * 0.35F + 0.9F, false);
    }

    public static void generateExperience(PlayerEntity player, World world) {
        player.giveExperiencePoints(7);
        world.playLocalSound(player.getX(), player.getY(), player.getZ(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 0.1F, (new Random().nextFloat() - new Random().nextFloat()) * 0.35F + 0.9F, false);

    }

}
