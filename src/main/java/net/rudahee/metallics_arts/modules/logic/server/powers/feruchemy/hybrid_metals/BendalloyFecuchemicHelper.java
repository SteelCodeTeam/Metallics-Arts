package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.hybrid_metals;

import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.AbstractFechuchemicHelper;
import net.rudahee.metallics_arts.utils.event_utils.OnWorldTick;
import net.rudahee.metallics_arts.utils.event_utils.on_world_tick.OnTickUtils;

import java.util.function.Supplier;

public class BendalloyFecuchemicHelper extends AbstractFechuchemicHelper {

    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, for the power of the Bendalloy: Increases the target player's amount of food.
     *
     * @param player to whom the effect will be applied.
     *
     * @see AbstractFechuchemicHelper#tappingPower(Player)
     */
    @Override
    public void tappingPower(Player player) {
        if (OnTickUtils.activationEvery30Tick(OnWorldTick.getActualTick())) {
            if (player.getFoodData().getFoodLevel()<20){
                player.getFoodData().setFoodLevel(player.getFoodData().getFoodLevel()+1);
            }
        }
    }

    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, for the power of the Bendalloy: Decreases the target player's amount of food.
     *
     * @param player to whom the effect will be applied.
     *
     * @see AbstractFechuchemicHelper#storagePower(Player)
     */
    @Override
    public void storagePower(Player player) {
        if (OnTickUtils.activationEvery30Tick(OnWorldTick.getActualTick())) {
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
