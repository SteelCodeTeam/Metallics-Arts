package net.rudahee.metallics_arts.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;

public class MistUtils {

    public static boolean mist(Player player, int min, int max){
        return player.getLevel().getLevelData().getDayTime()>=min && player.getLevel().getLevelData().getDayTime()<max;
    }

}
