package net.rudahee.metallics_arts.modules.powers.helpers;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.ITeleporter;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;

import java.util.function.Function;

public class GoldAndElectrumHelpers {

    public static void teleport(PlayerEntity player, World world, RegistryKey<World> dimension, BlockPos pos) {
        if (!world.isClientSide) {
            if (player != null) {
                if (player.isPassenger()) {
                    player.stopRiding();
                }

                if (player.level.dimension() != dimension) {
                    //change dimension
                    player = (PlayerEntity) player.changeDimension(world.getServer().getLevel(dimension), new ITeleporter() {
                        @Override
                        public Entity placeEntity(Entity entity, ServerWorld currentWorld, ServerWorld destWorld, float yaw, Function<Boolean, Entity> repositionEntity) {
                            Entity repositionedEntity = repositionEntity.apply(false);
                            repositionedEntity.teleportTo(pos.getX(), pos.getY(), pos.getZ());
                            return repositionedEntity;
                        }
                    });
                }
                player.teleportToWithTicket(pos.getX(), pos.getY() + 1.5, pos.getZ());
                player.fallDistance = 0.0F;
            }
        }
    }

    public static RegistryKey<World> getRegistryKeyFromString(String dim) {
        if (dim == World.OVERWORLD.getRegistryName().getNamespace()){
            return World.OVERWORLD;
        } else if (dim == World.NETHER.getRegistryName().getNamespace()) {
            return World.NETHER;
        } else if (dim == World.END.getRegistryName().getNamespace()) {
            return World.END;
        } else {
            return World.OVERWORLD;
        }
    }


    public static void addHealth(PlayerEntity player,int qtyHealth){
        player.setHealth(player.getHealth()+qtyHealth);
    }

    public static void removeHealth(PlayerEntity player, int qtyHealth){
        if (!player.isCreative()){
            if (player.getHealth()==1){
                player.kill();
            } else {
                player.setHealth(player.getHealth()-qtyHealth);
            }
        }


    }

    public static void removeHearts(PlayerEntity player, int qtyHearth){
        player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(qtyHearth);
    }

    public static void restoreHearts(PlayerEntity player){
        player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(20);
    }

    public static void addHearts(PlayerEntity player, int qtyHearth) {
        player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(qtyHearth);
    }
}
