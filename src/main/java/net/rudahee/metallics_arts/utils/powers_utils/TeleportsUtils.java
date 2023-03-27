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

/**
 * A utility class that provides methods for handling teleportation logic in Minecraft.
 *
 * This class contains methods for teleporting players to specific positions within the same dimension or another dimension,
 * as well as teleporting multiple players within a specified area.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class TeleportsUtils {

    /**
     * Teleports a given player to a specified position within the same dimension or another dimension.
     *
     * This method should only be called on the server side. It handles teleportation logic, including stopping
     * the player from riding entities, changing dimensions (if necessary), and updating the player's position.
     *
     * @param player The Player object representing the player to teleport.
     * @param level The Level object representing the current level the player is in.
     * @param dimension The ResourceKey of the target dimension for teleportation.
     * @param pos The BlockPos object representing the target position for teleportation.
     */
    public static void teleport(Player player, Level level, ResourceKey<Level> dimension, BlockPos pos) {
        if (!level.isClientSide) {
            if (player != null) {
                if (player.isPassenger()) {
                    player.stopRiding();
                }
                if (player.getLevel().dimension() != dimension) {
                    //change dimension
                    player = (Player) player.changeDimension(level.getServer().getLevel(dimension), new ITeleporter() {
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

    /**
     * Teleports a player and all other players within a specified area to a target position within the same dimension or another dimension.
     *
     * This method should only be called on the server side. It utilizes the teleport method to handle teleportation logic for all players within
     * the specified AABB (axis-aligned bounding box).
     *
     * @param player The Player object representing the main player to teleport.
     * @param axisAlignedBB The AABB object representing the area in which other players should be teleported as well.
     * @param level The Level object representing the current level the players are in.
     * @param dimension The ResourceKey of the target dimension for teleportation.
     * @param pos The BlockPos object representing the target position for teleportation.
     */
    public static void multiTeleport (Player player, AABB axisAlignedBB, Level level, ResourceKey<Level> dimension, BlockPos pos) {
        level.getEntitiesOfClass(Player.class, axisAlignedBB).forEach(entity -> {
            teleport(entity, level, dimension, pos);
        });
        teleport(player, level, dimension, pos);
    }

}
