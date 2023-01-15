package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy;

import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.modules.logic.server.ServerEventHandler;
import net.rudahee.metallics_arts.utils.event_utils.OnWorldTick;
import net.rudahee.metallics_arts.utils.event_utils.on_world_tick.OnTickUtils;

import java.util.function.Supplier;

public class CadmiumFecuchemicHelper extends AbstractFechuchemicHelper{

    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, for the power of the Cadmium: Water breathing will be applied to the target player.
     *
     * @param player to whom the effect will be applied.
     *
     * @see AbstractFechuchemicHelper#decantPower(Player)
     */
    @Override
    public void decantPower(Player player) {
        if (player.isEyeInFluid(FluidTags.WATER)) {
            if (OnTickUtils.activationEvery30Tick(OnWorldTick.getActualTick())) {
                if (player.isEyeInFluid(FluidTags.WATER)) {
                    player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 40, 10, true, false));
                }
            }
        }
    }

    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, for the power of the Cadmium: Decreases the target player's amount of oxygen out of the water, and even faster underwater.
     *
     * @param player to whom the effect will be applied.
     *
     * @see AbstractFechuchemicHelper#storagePower(Player)
     */
    @Override
    public void storagePower(Player player) {
        if (!player.isCreative()){
            if (!player.isEyeInFluid(FluidTags.WATER)) {
                player.setAirSupply(player.getAirSupply()-5);
                if (player.getAirSupply()<=-29) {
                    player.hurt(DamageSource.DROWN,1);
                    player.setAirSupply(-9);
                }
            } else {
                player.setAirSupply(player.getAirSupply()-1);
            }
        }
    }

    public static Supplier<? extends CadmiumFecuchemicHelper> getInstance() {
        return CadmiumFecuchemicHelper::new;
    }
}
