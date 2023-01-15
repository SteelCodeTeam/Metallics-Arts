package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.cognitive_metals;

import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.AbstractFechuchemicHelper;

import java.util.function.Supplier;

public class ZincFecuchemicHelper extends AbstractFechuchemicHelper {
    @Override
    public void tappingPower(Player player) {}

    @Override
    public void storagePower(Player player) {}

    public static Supplier<? extends ZincFecuchemicHelper> getInstance() {
        return ZincFecuchemicHelper::new;
    }
}
