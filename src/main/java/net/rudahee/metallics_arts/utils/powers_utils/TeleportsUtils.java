package net.rudahee.metallics_arts.utils.powers_utils;


import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.util.ITeleporter;

import java.util.function.Function;


public class TeleportsUtils {

    public static void teleport(Player player, Level world, ResourceKey<Level> dimension, BlockPos pos) {
        if (!world.isClientSide) {
            if (player != null) {
                if (player.isPassenger()) {
                    player.stopRiding();
                }
                if (player.getLevel().dimension() != dimension) {
                    //change dimension
                    player = (Player) player.changeDimension(world.getServer().getLevel(dimension), new ITeleporter() {
                        @Override
                        public Entity placeEntity(Entity entity, ServerLevel currentWorld, ServerLevel destWorld, float yaw, Function<Boolean, Entity> repositionEntity) {
                            Entity repositionedEntity = repositionEntity.apply(false);
                            repositionedEntity.teleportTo(pos.getX(), pos.getY() + 1, pos.getZ());
                            return repositionedEntity;
                        }
                    });
                }
                player.teleportToWithTicket(pos.getX(), pos.getY() + 1.5, pos.getZ());
                player.fallDistance = 0.0F;
            }
        }
    }

    public static void multiTeleport (Player player, AABB axisAlignedBB, Level world, ResourceKey<Level> dimension, BlockPos pos) {
        world.getEntitiesOfClass(Player.class, axisAlignedBB).forEach(entity -> {
            teleport(entity,world,dimension,pos);
        });
        teleport(player,world,dimension,pos);
    }

}
