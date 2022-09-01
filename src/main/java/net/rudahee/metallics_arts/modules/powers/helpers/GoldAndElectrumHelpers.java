package net.rudahee.metallics_arts.modules.powers.helpers;


import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.util.ITeleporter;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;

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
        if (dim == Level.OVERWORLD.registry().getNamespace()){
            return Level.OVERWORLD;
        } else if (dim == Level.NETHER.registry().getNamespace()) {
            return Level.NETHER;
        } else if (dim == Level.END.registry().getNamespace()) {
            return Level.END;
        } else {
            return Level.OVERWORLD;
        }
    }

    public static void addHealth(Player player,int qtyHealth){
        player.setHealth(player.getHealth()+qtyHealth);
    }

    public static void removeHealth(Player player, int qtyHealth){
        if (!player.isCreative()){
            player.hurt(DamageSource.GENERIC, qtyHealth);
        }
    }

    public static void removeHearts(Player player, int qtyHearth){
        player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(qtyHearth);
    }

    public static void restoreHearts(Player player){
        player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(20);
    }

    public static void addHearts(Player player, int qtyHearth) {
        player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(qtyHearth);
    }

    private static BlockPos block = null;
    private static String dimension = null;

    public static void takeDeathPosToObjetive(Player playerEntity) {
        playerEntity.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(cap ->{
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
