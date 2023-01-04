package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.modules.logic.server.ServerEventHandler;
import net.rudahee.metallics_arts.utils.CapabilityUtils;
import net.rudahee.metallics_arts.utils.event_utils.OnWorldTickEvent;

import java.util.function.Supplier;

public class CadmiumFecuchemicHelper extends AbstractFechuchemicHelper{


    //WEATHER BREATHING
    @Override
    public void decantPower(Player player) {
        if (player.isEyeInFluid(FluidTags.WATER)) {
            if (OnWorldTickEvent.activationEvery30Tick(ServerEventHandler.getActualTick())) {
                if (player.isEyeInFluid(FluidTags.WATER)) {
                    player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 40, 10, true, false));
                }
            }
        }
    }

    //REMOVE OXYGEN
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
