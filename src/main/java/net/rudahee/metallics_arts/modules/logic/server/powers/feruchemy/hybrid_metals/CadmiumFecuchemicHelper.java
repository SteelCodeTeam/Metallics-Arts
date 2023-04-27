package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.hybrid_metals;

import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.AbstractFechuchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.server_events.OnWorldTickEvent;
import net.rudahee.metallics_arts.modules.logic.server.server_events.on_world_tick.OnTickUtils;
import net.rudahee.metallics_arts.modules.effects.ModEffects;
import net.rudahee.metallics_arts.utils.MathUtils;

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
            if (MathUtils.isDivisibleBy30(OnWorldTickEvent.getActualTick())) {
                if (player.isEyeInFluid(FluidTags.WATER)) {
                    player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 120, 10, false, false));
                }
            }
        }
        ModEffects.giveFeruchemicalTapEffect(player, MetalTagEnum.CADMIUM);
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
        ModEffects.giveFeruchemicalStorageEffect(player,MetalTagEnum.CADMIUM);
    }

}
