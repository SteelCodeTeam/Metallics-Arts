package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.cognitive_metals;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.AbstractFechuchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.server_events.OnDamageEvent;
import net.rudahee.metallics_arts.modules.test.ModEffects;

import java.util.function.Supplier;

/**
 * Helper class containing the methods and implementations for using feruchemical Zinc.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class ZincFecuchemicHelper extends AbstractFechuchemicHelper {

    /**
     * Applies the Allomantic Zinc power effect to the player when tapping the power.
     * This method is called when a player taps the power, adding the effect to the player.
     *
     * @param player the Player to whom the effect is applied
     */
    @Override
    public void tapPower(Player player) {player.addEffect(new MobEffectInstance(ModEffects.POWER_EFFECTS.get("allomantic_zinc").get(), 1, 0, true, true));}

    /**
     * Applies the Allomantic Zinc power effect to the player when storing the power.
     * This method is called when a player stores the power, adding the effect to the player.
     *
     * @param player the Player to whom the effect is applied
     */
    @Override
    public void storagePower(Player player) {player.addEffect(new MobEffectInstance(ModEffects.POWER_EFFECTS.get("allomantic_zinc").get(), 1, 0, true, true));}

    /**
     * Returns an instance of ZincFecuchemicHelper using a factory method pattern.
     * This method allows you to create instances of ZincFecuchemicHelper with a consistent interface.
     *
     * @return a Supplier that returns a new instance of ZincFecuchemicHelper when called
     */
    public static Supplier<? extends ZincFecuchemicHelper> getInstance() {
        return ZincFecuchemicHelper::new;
    }
}
