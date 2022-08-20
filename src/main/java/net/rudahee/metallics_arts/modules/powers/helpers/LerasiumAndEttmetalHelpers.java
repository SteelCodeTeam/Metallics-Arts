package net.rudahee.metallics_arts.modules.powers.helpers;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.rudahee.metallics_arts.modules.data_player.IDefaultInvestedPlayerData;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import net.rudahee.metallics_arts.setup.enums.metals.Metal;

import java.util.ArrayList;

public class LerasiumAndEttmetalHelpers {

    public static void ettmetalExplotion(World world, PlayerEntity player) {
        player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(playerCapability ->{
            if (playerCapability.isBurning(MetalsNBTData.DURALUMIN)) {
                world.explode(player,player.position().x,player.position().y,player.position().z,((playerCapability.getAllomanticAmount(MetalsNBTData.ETTMETAL)+1)/1600),Explosion.Mode.BREAK);
                //player.setHealth(player.getHealth()-(((playerCapability.getAllomanticAmount(MetalsNBTData.ETTMETAL)+1)/1600)*2));
            }else {
                world.explode(player,player.position().x,player.position().y,player.position().z,((playerCapability.getAllomanticAmount(MetalsNBTData.ETTMETAL)+1)/1600),Explosion.Mode.NONE);
                //player.setHealth(player.getHealth()-(((playerCapability.getAllomanticAmount(MetalsNBTData.ETTMETAL)+1)/1600)*2));
            }
            playerCapability.drainMetals(MetalsNBTData.ETTMETAL);
        });
    }

    public static boolean saveAllomanticReserve(PlayerEntity player, IDefaultInvestedPlayerData playerCapability) {
        boolean itsDone = false;
        ArrayList<MetalsNBTData> metals = playerCapability.getAllomanticPowers();
        int firstQty = 0;
        int qtyToRemove = 0;
        boolean continueSaving = true;

        for (MetalsNBTData metal: metals) {
            continueSaving = true;
            while (continueSaving) {
                if (firstQty == 0) {
                    firstQty = playerCapability.getAllomanticAmount(metal);
                    qtyToRemove = Math.toIntExact(Math.round(firstQty * 0.1));
                }
                continueSaving = playerCapability.substractAllomanticMetalAmount(metal, qtyToRemove);
                if (!continueSaving || firstQty == 0) {
                    firstQty = 0;
                    qtyToRemove = 0;
                    continueSaving = false;
                } else {
                    playerCapability.setAmountLerasiumReserve(metal, playerCapability.getAmountLerasiumReserve(metal) + qtyToRemove);
                    itsDone = true;
                }
            }
        }

        return itsDone;
    }

    public static boolean loadAllomanticReserve(PlayerEntity player, IDefaultInvestedPlayerData playerCapability) {
        boolean itsDone = false;
        ArrayList<MetalsNBTData> metals = playerCapability.getAllomanticPowers();
        int firstQty = 0;
        int qtyToAdd = 0;
        boolean continueLoading = true;

        for (MetalsNBTData metal: metals) {
            continueLoading = true;
            while (continueLoading) {
                if (firstQty == 0) {
                    firstQty = playerCapability.getAmountLerasiumReserve(metal);
                    qtyToAdd = Math.toIntExact(Math.round(firstQty * 0.1));
                }

                continueLoading = playerCapability.addAllomanticMetalAmount(metal, qtyToAdd);

                if (!continueLoading || firstQty == 0) {
                    firstQty = 0;
                    qtyToAdd = 0;
                    continueLoading = false;
                } else {
                    playerCapability.setAmountLerasiumReserve(metal, playerCapability.getAmountLerasiumReserve(metal) - qtyToAdd);
                    itsDone = true;
                }
            }
        }
        return itsDone;
    }

}
