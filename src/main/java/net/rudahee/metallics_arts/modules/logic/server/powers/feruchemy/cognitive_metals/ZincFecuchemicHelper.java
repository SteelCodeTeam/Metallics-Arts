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
    @Override
    public void tapPower(Player player) {player.addEffect(new MobEffectInstance(ModEffects.POWER_EFFECTS.get("allomantic_zinc").get(), 1, 1, true, true));}

    @Override
    public void storagePower(Player player) {player.addEffect(new MobEffectInstance(ModEffects.POWER_EFFECTS.get("allomantic_zinc").get(), 1, 1, true, true));}

    public static Supplier<? extends ZincFecuchemicHelper> getInstance() {
        return ZincFecuchemicHelper::new;
    }
}
