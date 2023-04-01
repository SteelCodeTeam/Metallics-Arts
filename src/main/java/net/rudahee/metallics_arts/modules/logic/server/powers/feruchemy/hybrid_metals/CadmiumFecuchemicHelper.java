package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.hybrid_metals;

import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.AbstractFechuchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.server_events.OnWorldTickEvent;
import net.rudahee.metallics_arts.modules.logic.server.server_events.on_world_tick.OnTickUtils;
import net.rudahee.metallics_arts.modules.test.ModEffects;

import java.util.function.Supplier;

/**
 * Helper class containing the methods and implementations for using feruchemical Cadmium.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class CadmiumFecuchemicHelper extends AbstractFechuchemicHelper {

    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, for the power of the Cadmium: Water breathing will be applied to the target player.
     *
     * @param player to whom the effect will be applied.
     *
     * @see AbstractFechuchemicHelper#tapPower(Player)
     */
    @Override
    public void tapPower(Player player) {
        if (player.isEyeInFluid(FluidTags.WATER)) {
            if (OnTickUtils.activationEvery30Tick(OnWorldTickEvent.getActualTick())) {
                if (player.isEyeInFluid(FluidTags.WATER)) {
                    player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 40, 10, true, false));
                }
            }
        }
        player.addEffect(new MobEffectInstance(ModEffects.POWER_EFFECTS.get("feruchemical_cadmium_tap").get(), 10, 0, true, true));
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
        player.addEffect(new MobEffectInstance(ModEffects.POWER_EFFECTS.get("feruchemical_cadmium_storage").get(), 10, 0, true, true));
    }

    /**
     * Returns an instance of CadmiumFecuchemicHelper using a factory method pattern.
     * This method allows you to create instances of CadmiumFecuchemicHelper with a consistent interface.
     *
     * @return a Supplier that returns a new instance of CadmiumFecuchemicHelper when called
     */
    public static Supplier<? extends CadmiumFecuchemicHelper> getInstance() {
        return CadmiumFecuchemicHelper::new;
    }
}
