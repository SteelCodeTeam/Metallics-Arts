package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.spiritual_metals;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.AbstractFechuchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.server_events.OnWorldTickEvent;
import net.rudahee.metallics_arts.modules.logic.server.server_events.on_world_tick.OnTickUtils;
import net.rudahee.metallics_arts.modules.effects.ModEffects;
import net.rudahee.metallics_arts.utils.MathUtils;

import java.util.Random;
import java.util.function.Supplier;

/**
 * Helper class containing the methods and implementations for using feruchemical Chromium.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class  ChromiumFecuchemicHelper extends AbstractFechuchemicHelper {

    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, for the power of the Chromium: Luck will be applied to the target player.
     *
     * @param player to whom the effect will be applied.
     *
     * @see AbstractFechuchemicHelper#tapPower(Player)
     */
    @Override
    public void tapPower(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.LUCK,40,15,false, false));
        ModEffects.giveFeruchemicalTapEffect(player, MetalTagEnum.CHROMIUM);
    }

    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, for the power of the Chromium: Bad luck will be applied to the target player and will be given random negative effects.
     *
     * @param player to whom the effect will be applied.
     *
     * @see AbstractFechuchemicHelper#tapPower(Player)
     */
    @Override
    public void storagePower(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.UNLUCK,40,15,false, false));
        if (MathUtils.isDivisibleBy30(OnWorldTickEvent.getActualTick())){
            if (Math.random()<0.5) {
                Random a = new Random();
                switch (a.nextInt(9)) {
                    case 0 -> player.addEffect(new MobEffectInstance(MobEffects.WITHER, 40, 2, false, false));
                    case 1 -> player.addEffect(new MobEffectInstance(MobEffects.POISON, 45, 2, false, false));
                    case 2 -> player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 50, 2, false, false));
                    case 3 -> player.addEffect(new MobEffectInstance(MobEffects.HUNGER, 50, 2, false, false));
                    case 4 -> player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 45, 2, false, false));
                    case 5 -> player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 180, 4, false, false));
                    case 6 -> player.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 40, 1, false, false));
                    case 7 -> player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 50, 2, false, false));
                    case 8 -> player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 50, 2, false, false));
                }
            }
        }
        ModEffects.giveFeruchemicalStorageEffect(player,MetalTagEnum.CHROMIUM);
    }

    /**
     * Returns an instance of ChromiumFecuchemicHelper using a factory method pattern.
     * This method allows you to create instances of ChromiumFecuchemicHelper with a consistent interface.
     *
     * @return a Supplier that returns a new instance of ChromiumFecuchemicHelper when called
     */
    public static Supplier<? extends ChromiumFecuchemicHelper> getInstance() {
        return ChromiumFecuchemicHelper::new;
    }
}
