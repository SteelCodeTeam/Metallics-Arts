package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.players.IInvestedPlayerData;

import java.util.function.Supplier;

public class TinFeruchemicHelper extends AbstractFechuchemicHelper{

    @Override
    public void decantPower(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 320, 1, true, false, false));
    }

    @Override
    public void storagePower(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 60, 1, true, false, false));
    }

    public static Supplier<? extends TinFeruchemicHelper> getInstance() {
        return TinFeruchemicHelper::new;
    }
}
