package net.rudahee.metallics_arts.modules.powers.helpers;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

public class CopperAndBronzeHelpers {

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

    public static void DontSpawnPhantoms(Player player, AABB axisAlignedBB, Level world) {
        world.getEntitiesOfClass(Phantom.class, axisAlignedBB).forEach(entity -> {
            if (entity instanceof Phantom) {
                if (entity.getTarget() == player) {
                    entity.setAggressive(false);
                    entity.setTarget(null);
                }
            }
        });
    }

    public static void SpawnPhamtonsWithFireResistance(Player player, Level world) {

        Phantom phantom = new Phantom(EntityType.PHANTOM, world);
        phantom.setPos(player.position().x,player.position().y + 4, player.position().z);
        phantom.setTarget(player);
        phantom.setAggressive(true);
        phantom.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 240, 2, true, true, true));
        world.addFreshEntity(phantom);
    }



}
