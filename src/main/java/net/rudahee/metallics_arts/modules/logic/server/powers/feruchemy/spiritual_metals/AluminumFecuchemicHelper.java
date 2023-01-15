package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.spiritual_metals;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.AbstractFechuchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.god_metals.AtiumFecuchemicHelper;

import java.util.function.Supplier;

public class AluminumFecuchemicHelper extends AbstractFechuchemicHelper {
    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * This method is not used, because the power logic is applied in the discharge methods of this class.
     *
     * @param player to whom the effect will be applied.
     *
     * @see AtiumFecuchemicHelper#calculateDischarge(CompoundTag, Player, IInvestedPlayerData, int, String, boolean)
     */
    @Override
    public void tappingPower(Player player) {}

    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * This method is not used, because the power logic is applied in the charge methods of this class.
     *
     * @param player to whom the effect will be applied.
     *
     * @see AtiumFecuchemicHelper#calculateCharge(CompoundTag, Player, IInvestedPlayerData, int, String, boolean)
     */
    @Override
    public void storagePower(Player player) {}

    public static Supplier<? extends AluminumFecuchemicHelper> getInstance() {
        return AluminumFecuchemicHelper::new;
    }

    /**
     * Redefine of the method of the AbstractFechuchemicHelper class.
     * In this specific case, because the logic of the charge is proper of the Aluminum.
     *
     * @param compoundTag the inside information of the metalmind.
     * @param player with the mindmetal equipped.
     * @param playerCapability capabilities (data) of the player.
     * @param metalReserve present value of the metal reserve.
     * @param metalKey metal key to be modified.
     * @param nicConsume control value of whether it is necessary to store charge or not.
     * @return CompoundTag metalmind information update.
     *
     * @see AtiumFecuchemicHelper#calculateDischarge(CompoundTag, Player, IInvestedPlayerData, int, String, boolean)
     */
    @Override
    public CompoundTag calculateDischarge(CompoundTag compoundTag, Player player, IInvestedPlayerData playerCapability, int metalReserve, String metalKey, boolean nicConsume) {
        compoundTag.putInt(metalKey, 1);
        return compoundTag;
    }
    /**
     * Redefine of the method of the AbstractFechuchemicHelper class.
     * In this specific case, because the logic of the charge is proper of the Aluminum.
     *
     * @param compoundTag the inside information of the metalmind.
     * @param player with the mindmetal equipped.
     * @param playerCapability capabilities (data) of the player.
     * @param metalReserve present value of the metal reserve.
     * @param metalKey metal key to be modified.
     * @param nicConsume control value of whether it is necessary to store charge or not.
     * @return CompoundTag metalmind information update.
     *
     * @see AtiumFecuchemicHelper#calculateCharge(CompoundTag, Player, IInvestedPlayerData, int, String, boolean)
     */

    @Override
    public CompoundTag calculateCharge(CompoundTag compoundTag, Player player, IInvestedPlayerData playerCapability, int metalReserve, String metalKey, boolean nicConsume) {
        compoundTag.putInt(metalKey, 2);
        return compoundTag;
    }
    /**
     * This is a unique method in the helpers, which is responsible for modifying the internal reserve of the metalmind, to show that the power is off.
     *
     * @param compoundTag the inside information of the metalmind.
     * @param metalKey metal key to be modified.
     * @return CompoundTag metalmind information update.
     */
    public static CompoundTag turnOffPower(CompoundTag compoundTag, String metalKey) {
        compoundTag.putInt(metalKey, 3);
        return compoundTag;
    }
}
