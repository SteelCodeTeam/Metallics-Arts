package net.rudahee.metallics_arts.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;

public class EntityUtils {

    public static double distance(ServerPlayer player, LivingEntity entity){

        BlockPos playerPos= player.blockPosition();
        BlockPos entityPos= entity.blockPosition();


        return Math.sqrt(Math.pow(playerPos.getX()-entityPos.getX(),2)+
                Math.pow(playerPos.getY()-entityPos.getY(),2)+
                Math.pow(playerPos.getZ()-entityPos.getZ(),2));


    }

}
