package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.hybrid_metals;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.AbstractFechuchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.server_events.OnWorldTickEvent;
import net.rudahee.metallics_arts.modules.logic.server.server_events.on_world_tick.OnTickUtils;

import java.util.function.Supplier;

/**
 * Helper class containing the methods and implementations for using feruchemical Gold.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class GoldFecuchemicHelper extends AbstractFechuchemicHelper {
    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, for the power of the Gold: increases the current life of the player.
     *
     * @param player to whom the effect will be applied.
     *
     * @see AbstractFechuchemicHelper#tappingPower(Player)
     */
    @Override
    public void tappingPower(Player player) {
        if (OnTickUtils.activationEvery30Tick(OnWorldTickEvent.getActualTick())) {
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
        if (OnTickUtils.activationEvery30Tick(OnWorldTickEvent.getActualTick())) {
            if (!player.isCreative()){
                player.hurt(DamageSource.GENERIC, 1);
            }
        }
    }

    //todo
    public static Supplier<? extends GoldFecuchemicHelper> getInstance() {
        return GoldFecuchemicHelper::new;
    }
}
