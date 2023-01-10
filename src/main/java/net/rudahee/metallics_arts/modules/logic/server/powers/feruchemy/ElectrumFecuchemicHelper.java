package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.data.players.IInvestedPlayerData;

import java.util.function.Supplier;

public class ElectrumFecuchemicHelper extends AbstractFechuchemicHelper{

    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, for the power of the Electrum: modify the maximum life to add 5 extra hearts to the target player.
     *
     * @param player to whom the effect will be applied.
     *
     * @see AbstractFechuchemicHelper#decantPower(Player)
     */
    @Override
    public void decantPower(Player player) {
        player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(30);
    }

    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, for the power of the Electrum: modify the max health so that the target player only has 5 hearts.
     *
     * @param player to whom the effect will be applied.
     *
     * @see AbstractFechuchemicHelper#storagePower(Player)
     */
    @Override
    public void storagePower(Player player) {
        if (player.getHealth()>10) {
            player.setHealth(10);
        }
        player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(10);
    }
    /**
     * This is a unique method in the helpers, which is used to restore the max health to the base value (10 hearts), when the electrum powers stop working.
     *
     * @param player to whom the effect will be applied.
     * @param playerCapability capabilities (data) to the player.
     * @return CompoundTag metalmind information update.
     */
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