package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy;

import net.minecraft.world.entity.player.Player;

import java.util.function.Supplier;

public class ZincFecuchemicHelper extends AbstractFechuchemicHelper{
    @Override
    public void decantPower(Player player) {}

    @Override
    public void storagePower(Player player) {}

    public static Supplier<? extends ZincFecuchemicHelper> getInstance() {
        return ZincFecuchemicHelper::new;
    }
}
