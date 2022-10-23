package net.rudahee.metallics_arts.modules.powers.helpers;


import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;

public class LerasiumAndEttmetalHelpers {

    public static void ettmetalExplotion(Level world, Player player) {
        player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(playerCapability ->{
            if (playerCapability.isBurning(MetalsNBTData.DURALUMIN)) {
                world.explode(player,player.position().x,player.position().y,player.position().z,((float) playerCapability.getAllomanticAmount(MetalsNBTData.ETTMETAL))/1600, Explosion.BlockInteraction.BREAK);
                player.setHealth((player.getHealth() - ((float) playerCapability.getAllomanticAmount(MetalsNBTData.ETTMETAL)/800)));
            }else {
                world.explode(player,player.position().x,player.position().y,player.position().z,((float) playerCapability.getAllomanticAmount(MetalsNBTData.ETTMETAL)) /1600,Explosion.BlockInteraction.NONE);
                player.setHealth((player.getHealth() - ((float) playerCapability.getAllomanticAmount(MetalsNBTData.ETTMETAL)/1600)));
            }
            playerCapability.drainMetals(MetalsNBTData.ETTMETAL,MetalsNBTData.DURALUMIN);
        });
    }

}
