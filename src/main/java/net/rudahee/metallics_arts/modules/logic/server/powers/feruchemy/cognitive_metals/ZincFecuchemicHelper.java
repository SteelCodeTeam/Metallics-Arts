package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.cognitive_metals;

import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.AbstractFechuchemicHelper;
import net.rudahee.metallics_arts.modules.effects.ModEffects;

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
    public void tapPower(Player player) {
        ModEffects.giveFeruchemicalTapEffect(player, MetalTagEnum.ZINC);
    }

    /**
     * Applies the Allomantic Zinc power effect to the player when storing the power.
     * This method is called when a player stores the power, adding the effect to the player.
     *
     * @param player the Player to whom the effect is applied
     */
    @Override
    public void storagePower(Player player) {
        ModEffects.giveFeruchemicalStorageEffect(player,MetalTagEnum.ZINC);
    }

}
