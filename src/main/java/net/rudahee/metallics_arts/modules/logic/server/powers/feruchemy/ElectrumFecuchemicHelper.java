package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.data.players.IInvestedPlayerData;

import java.util.function.Supplier;

public class ElectrumFecuchemicHelper extends AbstractFechuchemicHelper{

    @Override
    public void decantPower(Player player) {
        player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(30);
    }

    @Override
    public void storagePower(Player player) {
        if (player.getHealth()>10) {
            player.setHealth(10);
        }
        player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(10);
    }

    public static void restoreHearts(Player player, IInvestedPlayerData playerCapability ){
        if (player.getHealth()>20) {
            player.setHealth(20);
        }
        player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(20);
        playerCapability.setModifiedHealth(false);

    }

    public static Supplier<? extends ElectrumFecuchemicHelper> getInstance() {
        return ElectrumFecuchemicHelper::new;
    }
}
