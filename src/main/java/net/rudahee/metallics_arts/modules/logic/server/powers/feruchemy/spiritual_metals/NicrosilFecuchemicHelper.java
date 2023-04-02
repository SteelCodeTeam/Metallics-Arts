package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.spiritual_metals;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.AbstractFechuchemicHelper;
import net.rudahee.metallics_arts.modules.effects.ModEffects;

import java.util.function.Supplier;

/**
 * Helper class containing the methods and implementations for using feruchemical Nicrosil.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class NicrosilFecuchemicHelper extends AbstractFechuchemicHelper {

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
        ModEffects.giveFeruchemicalTapEffect(player,MetalTagEnum.BRASS);
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
        ModEffects.giveFeruchemicalStorageEffect(player,MetalTagEnum.NICROSIL);
    }

    /**
     * Returns an instance of NicrosilFecuchemicHelper using a factory method pattern.
     * This method allows you to create instances of NicrosilFecuchemicHelper with a consistent interface.
     *
     * @return a Supplier that returns a new instance of NicrosilFecuchemicHelper when called
     */
    public static Supplier<? extends NicrosilFecuchemicHelper> getInstance() {
        return NicrosilFecuchemicHelper::new;
    }

    /**
     * Redefine of the method of the AbstractFechuchemicHelper class.
     * <p>
     * In this specific case, the Nicrosil reserve is discharged based on the amount of other metals being tapped at the same time as the Nicrosil.
     *
     * @param compoundTag the inside information of the metalmind.
     * @param player with the mindmetal equipped.
     * @param playerCapability capabilities (data) of the player.
     * @param metalReserve present value of the metal reserve.
     * @param metalKey metal key to be modified.
     * @param nicConsume control value of whether it is necessary to store charge or not.
     * @return CompoundTag metalmind information update.
     *
     * @see AbstractFechuchemicHelper#tapPower(Player)
     */
    @Override
    public CompoundTag calculateDischarge(CompoundTag compoundTag, Player player, IInvestedPlayerData playerCapability, int metalReserve, String metalKey, boolean nicConsume) {
        int value = playerCapability.cantMetalsTapping();
        if (nicConsume) {
            value = 0;
        }
        compoundTag.putInt(metalKey, metalReserve - value);
        tapPower(player);
        return compoundTag;
    }

    /**
     * Redefine of the method of the AbstractFechuchemicHelper class.
     * <p>
     * In this specific case, the Nicrosil reserve is charged based on the amount of other metals being stored at the same time as the Nicrosil.
     *
     * @param compoundTag the inside information of the metalmind.
     * @param player with the mindmetal equipped.
     * @param playerCapability capabilities (data) of the player.
     * @param metalReserve present value of the metal reserve.
     * @param metalKey metal key to be modified.
     * @param nicConsume control value of whether it is necessary to store charge or not.
     * @return CompoundTag metalmind information update.
     *
     * @see AbstractFechuchemicHelper#tapPower(Player)
     */
    @Override
    public CompoundTag calculateCharge(CompoundTag compoundTag, Player player, IInvestedPlayerData playerCapability, int metalReserve, String metalKey, boolean nicConsume) {
        int value = playerCapability.cantMetalsStoring();
        if (playerCapability.isStoring(MetalTagEnum.BRASS) && !player.isOnFire()){
            value = value - 1;
        }
        if (nicConsume) {
            value = 0;
        }
        compoundTag.putInt(metalKey, metalReserve + value);
        storagePower(player);
        return compoundTag;
    }
}
