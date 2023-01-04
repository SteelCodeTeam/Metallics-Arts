package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy;

import com.google.errorprone.annotations.SuppressPackageLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.data.players.IInvestedPlayerData;

import java.util.function.Supplier;

public class PewterFeruchemicHelper extends AbstractFechuchemicHelper{

    public void decantPower(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.JUMP, 5, 1, true, true));
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 5, 1, true, true));
    }

    @Override
    public void storagePower(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 5, 1, true, false));
        player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 5, 1, true, false));
    }

    public static Supplier<? extends PewterFeruchemicHelper> getInstance() {
        return PewterFeruchemicHelper::new;
    }
}
