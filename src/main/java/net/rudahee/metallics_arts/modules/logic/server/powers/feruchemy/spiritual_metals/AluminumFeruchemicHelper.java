package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.spiritual_metals;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;

import java.util.function.Supplier;

/**
 * Helper class containing the methods and implementations for using feruchemical Aluminum.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class AluminumFeruchemicHelper {

    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * This method is not used, because the power logic is applied in the discharge methods of this class.
     *
     * @param player to whom the effect will be applied.
     *
     */
    public static void tapPower(Player player) {
    }

    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * This method is not used, because the power logic is applied in the charge methods of this class.
     *
     * @param player to whom the effect will be applied.
     *
     */
    public static void storagePower(Player player) {
    }

    /**
     * Returns an instance of AluminumFecuchemicHelper using a factory method pattern.
     * This method allows you to create instances of AluminumFecuchemicHelper with a consistent interface.
     *
     * @return a Supplier that returns a new instance of AluminumFecuchemicHelper when called
     */
    public static Supplier<? extends AluminumFeruchemicHelper> getInstance() {
        return AluminumFeruchemicHelper::new;
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
