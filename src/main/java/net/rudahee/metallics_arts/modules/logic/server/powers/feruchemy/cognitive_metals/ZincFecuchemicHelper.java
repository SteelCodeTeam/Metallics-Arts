package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.cognitive_metals;

import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.AbstractFechuchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.server_events.OnDamageEvent;

import java.util.function.Supplier;

/**
 * Helper class containing the methods and implementations for using feruchemical Zinc.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class ZincFecuchemicHelper extends AbstractFechuchemicHelper {
    @Override
    public void tappingPower(Player player) {}

    @Override
    public void storagePower(Player player) {}

    public static Supplier<? extends ZincFecuchemicHelper> getInstance() {
        return ZincFecuchemicHelper::new;
    }
}
