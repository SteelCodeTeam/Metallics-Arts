package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.modules.logic.server.ServerEventHandler;
import net.rudahee.metallics_arts.utils.event_utils.OnWorldTickEvent;

import java.util.function.Supplier;

public class GoldFecuchemicHelper extends AbstractFechuchemicHelper{
    @Override
    public void decantPower(Player player) {
        if (OnWorldTickEvent.activationEvery30Tick(ServerEventHandler.getActualTick())) {
            player.setHealth(player.getHealth()+1);
        }
    }

    @Override
    public void storagePower(Player player) {
        if (OnWorldTickEvent.activationEvery30Tick(ServerEventHandler.getActualTick())) {
            if (!player.isCreative()){
                player.hurt(DamageSource.GENERIC, 1);
            }
        }
    }

    public static Supplier<? extends GoldFecuchemicHelper> getInstance() {
        return GoldFecuchemicHelper::new;
    }
}
