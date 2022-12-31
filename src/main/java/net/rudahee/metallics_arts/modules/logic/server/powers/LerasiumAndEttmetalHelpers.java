package net.rudahee.metallics_arts.modules.logic.server.powers;


import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;

public class LerasiumAndEttmetalHelpers {

    public static void ettmetalExplotion(Level world, Player player) {
        player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(playerCapability ->{
            if (playerCapability.getEnhanced()) {
                //max reserve = 100/20 = 5
                //min reserve = 10/20 = 0*5
                world.explode(player,player.position().x,player.position().y,player.position().z,((float) playerCapability.getAllomanticAmount(MetalTagEnum.ETTMETAL))/20, Explosion.BlockInteraction.BREAK);
                player.setHealth((player.getHealth() - ((float) playerCapability.getAllomanticAmount(MetalTagEnum.ETTMETAL)/5)));
                playerCapability.drainMetals(MetalTagEnum.DURALUMIN);
            }else {
                world.explode(player,player.position().x,player.position().y,player.position().z,((float) playerCapability.getAllomanticAmount(MetalTagEnum.ETTMETAL))/20,Explosion.BlockInteraction.NONE);
                player.setHealth((player.getHealth() - ((float) playerCapability.getAllomanticAmount(MetalTagEnum.ETTMETAL)/10)));
            }
            playerCapability.drainMetals(MetalTagEnum.ETTMETAL);
        });
    }

}
