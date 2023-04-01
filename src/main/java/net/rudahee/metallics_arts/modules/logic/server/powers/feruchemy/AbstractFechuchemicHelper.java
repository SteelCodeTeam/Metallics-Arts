package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;


/**
 * Abstract class that defines the structure of the feruchemical helpers.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public abstract class AbstractFechuchemicHelper {

    /**
     * An abstract method to be implemented by subclasses, representing the action taken when a player taps a power.
     * Each subclass should define the specific effect or action to be applied to the player when tapping the power.
     *
     * @param player the Player to whom the effect or action is applied
     */
    public abstract void tapPower(Player player);

    /**
     * An abstract method to be implemented by subclasses, representing the action taken when a player stores a power.
     * Each subclass should define the specific effect or action to be applied to the player when storing the power.
     *
     * @param player the Player to whom the effect or action is applied
     */
    public abstract void storagePower(Player player);

    //charge = storing
    //discharge = decanting
    /**
     * Basic implementation of discharge metalminds.
     *
     * @param compoundTag the inside information of the metalmind.
     * @param player with the mindmetal equipped.
     * @param playerCapability capabilities (data) of the player.
     * @param metalReserve present value of the metal reserve.
     * @param metalKey metal key to be modified.
     * @param nicConsume control value of whether it is necessary to store charge or not.
     * @return CompoundTag metalmind information update.
     */
    public CompoundTag calculateDischarge(CompoundTag compoundTag, Player player, IInvestedPlayerData playerCapability, int metalReserve, String metalKey, boolean nicConsume) {
        if (!playerCapability.isTapping(MetalTagEnum.NICROSIL) || !nicConsume) {
            compoundTag.putInt(metalKey, metalReserve - 1);
        }
        tapPower(player);
        return compoundTag;
    }

    /**
     * Basic implementation of charging metalminds.
     *
     * @param compoundTag the inside information of the metalmind.
     * @param player with the mindmetal equipped.
     * @param playerCapability capabilities (data) of the player.
     * @param metalReserve present value of the metal reserve.
     * @param metalKey metal key to be modified.
     * @param nicConsume control value of whether it is necessary to store charge or not.
     * @return CompoundTag metalmind information update.
     */
    public CompoundTag calculateCharge(CompoundTag compoundTag, Player player, IInvestedPlayerData playerCapability, int metalReserve, String metalKey, boolean nicConsume) {
        if (!playerCapability.isStoring(MetalTagEnum.NICROSIL) || !nicConsume) {
            compoundTag.putInt(metalKey, metalReserve + 1);
        }
        storagePower(player);
        return compoundTag;
    }
}
