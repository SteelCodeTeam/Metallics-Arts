package net.rudahee.metallics_arts.modules.powers.helpers.metal_helpers;


import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.rudahee.metallics_arts.modules.tags_player.InvestedCapability;
import net.rudahee.metallics_arts.data.enums.implementations.MetalsNBTData;

public class LerasiumAndEttmetalHelpers {

    public static void ettmetalExplotion(Level world, Player player) {
        player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(playerCapability ->{
            if (playerCapability.getEnhanced()) {
                //max reserve = 100/20 = 5
                //min reserve = 10/20 = 0*5
                world.explode(player,player.position().x,player.position().y,player.position().z,((float) playerCapability.getAllomanticAmount(MetalsNBTData.ETTMETAL))/20, Explosion.BlockInteraction.BREAK);
                player.setHealth((player.getHealth() - ((float) playerCapability.getAllomanticAmount(MetalsNBTData.ETTMETAL)/5)));
                playerCapability.drainMetals(MetalsNBTData.DURALUMIN);
            }else {
                world.explode(player,player.position().x,player.position().y,player.position().z,((float) playerCapability.getAllomanticAmount(MetalsNBTData.ETTMETAL))/20,Explosion.BlockInteraction.NONE);
                player.setHealth((player.getHealth() - ((float) playerCapability.getAllomanticAmount(MetalsNBTData.ETTMETAL)/10)));
            }
            playerCapability.drainMetals(MetalsNBTData.ETTMETAL);
        });
    }

}
