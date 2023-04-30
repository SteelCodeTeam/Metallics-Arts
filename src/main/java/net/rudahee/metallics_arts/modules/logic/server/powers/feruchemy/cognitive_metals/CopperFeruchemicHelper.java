package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.cognitive_metals;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.effects.ModEffects;

/**
 * Helper class containing the methods and implementations for using feruchemical Copper.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class CopperFeruchemicHelper {
    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, for the power of the Copper: Increases the target player's amount experience.
     *
     * @param player to whom the effect will be applied.
     */
    public static void tapPower(Player player) {
        player.giveExperiencePoints(1);
    }

    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, for the power of the Copper: Decreases the target player's amount experience.
     *
     * @param player to whom the effect will be applied.
     */
    public static void storagePower(Player player) {
        player.giveExperiencePoints(-1);
    }




}
