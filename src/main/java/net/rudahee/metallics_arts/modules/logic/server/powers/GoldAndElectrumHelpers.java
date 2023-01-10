package net.rudahee.metallics_arts.modules.logic.server.powers;


import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.util.ITeleporter;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;

import java.util.function.Function;


public class GoldAndElectrumHelpers {

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

    public static ResourceKey<Level> getRegistryKeyFromString(String dim) {
        if (dim.equals(Level.OVERWORLD.location().toString())){
            return Level.OVERWORLD;
        } else if (dim.equals(Level.NETHER.location().toString())) {
            return Level.NETHER;
        } else if (dim.equals(Level.END.location().toString())) {
            return Level.END;
        } else {
            return Level.OVERWORLD;
        }
    }
    private static BlockPos block = null;
    private static String dimension = null;

    public static void takeDeathPosToObjetive(Player playerEntity) {
        playerEntity.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(cap ->{
            if (cap.getDeathPos() != null && cap.getDeathDimension() != null) {
                block = new BlockPos(cap.getDeathPos()[0], cap.getDeathPos()[1], cap.getDeathPos()[2]);
                dimension = cap.getDeathDimension();
            }
        });
    }

    public static BlockPos getBlock() {
        return block;
    }

    public static void setBlock(BlockPos block) {
        GoldAndElectrumHelpers.block = block;
    }

    public static String getDimension() {
        return dimension;
    }

    public static void setDimension(String dimension) {
        GoldAndElectrumHelpers.dimension = dimension;
    }
}
