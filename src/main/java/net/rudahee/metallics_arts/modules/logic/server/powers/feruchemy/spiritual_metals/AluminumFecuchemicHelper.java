package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.spiritual_metals;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.AbstractFechuchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.god_metals.AtiumFecuchemicHelper;
import net.rudahee.metallics_arts.modules.effects.ModEffects;

import java.util.function.Supplier;

/**
 * Helper class containing the methods and implementations for using feruchemical Aluminum.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class AluminumFecuchemicHelper extends AbstractFechuchemicHelper {

    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * This method is not used, because the power logic is applied in the discharge methods of this class.
     *
     * @param player to whom the effect will be applied.
     *
     * @see AbstractFechuchemicHelper#tapPower(Player)
     */
    @Override
    public void tapPower(Player player) {
        ModEffects.giveFeruchemicalTapEffect(player, MetalTagEnum.ALUMINUM);
    }

    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * This method is not used, because the power logic is applied in the charge methods of this class.
     *
     * @param player to whom the effect will be applied.
     *
     * @see AbstractFechuchemicHelper#storagePower(Player)
     */
    @Override
    public void storagePower(Player player) {
        ModEffects.giveFeruchemicalStorageEffect(player,MetalTagEnum.ALUMINUM);
    }

    /**
     * Returns an instance of AluminumFecuchemicHelper using a factory method pattern.
     * This method allows you to create instances of AluminumFecuchemicHelper with a consistent interface.
     *
     * @return a Supplier that returns a new instance of AluminumFecuchemicHelper when called
     */
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
        tapPower(player);
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
        storagePower(player);
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
