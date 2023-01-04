package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy;

import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.modules.logic.server.ServerEventHandler;
import net.rudahee.metallics_arts.utils.event_utils.OnWorldTickEvent;

import java.util.function.Supplier;

public class BendalloyFecuchemicHelper extends AbstractFechuchemicHelper{

    //REMOVE FOOD
    @Override
    public void decantPower(Player player) {
        if (OnWorldTickEvent.activationEvery30Tick(ServerEventHandler.getActualTick())) {
            if (player.getFoodData().getFoodLevel()<20){
                player.getFoodData().setFoodLevel(player.getFoodData().getFoodLevel()+1);
            }
        }
    }

    //GIVE FOOD
    @Override
    public void storagePower(Player player) {
        if (OnWorldTickEvent.activationEvery30Tick(ServerEventHandler.getActualTick())) {
            if (!player.isCreative()){
                if (player.getFoodData().getFoodLevel()>0){
                    player.getFoodData().setFoodLevel(player.getFoodData().getFoodLevel()-1);
                }
            }
        }
    }

    public static Supplier<? extends BendalloyFecuchemicHelper> getInstance() {
        return BendalloyFecuchemicHelper::new;
    }
}
