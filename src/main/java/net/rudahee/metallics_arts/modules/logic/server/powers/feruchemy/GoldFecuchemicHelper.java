package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.modules.logic.server.ServerEventHandler;
import net.rudahee.metallics_arts.utils.event_utils.OnWorldTick;
import net.rudahee.metallics_arts.utils.event_utils.on_world_tick.OnTickUtils;

import java.util.function.Supplier;

public class GoldFecuchemicHelper extends AbstractFechuchemicHelper{
    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, for the power of the Gold: increases the current life of the player.
     *
     * @param player to whom the effect will be applied.
     *
     * @see AbstractFechuchemicHelper#decantPower(Player)
     */
    @Override
    public void decantPower(Player player) {
        if (OnTickUtils.activationEvery30Tick(OnWorldTick.getActualTick())) {
            player.setHealth(player.getHealth()+1);
        }
    }
    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, for the power of the Gold: decreases the current life of the player.
     *
     * @param player to whom the effect will be applied.
     *
     * @see AbstractFechuchemicHelper#storagePower(Player) 
     */
    @Override
    public void storagePower(Player player) {
        if (OnTickUtils.activationEvery30Tick(OnWorldTick.getActualTick())) {
            if (!player.isCreative()){
                player.hurt(DamageSource.GENERIC, 1);
            }
        }
    }

    public static Supplier<? extends GoldFecuchemicHelper> getInstance() {
        return GoldFecuchemicHelper::new;
    }
}
