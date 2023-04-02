package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.god_metals;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.AbstractFechuchemicHelper;
import net.rudahee.metallics_arts.modules.test.ModEffects;

import java.util.ArrayList;
import java.util.function.Supplier;

/**
 * Helper class containing the methods and implementations for using feruchemical Lerasium.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class LerasiumFecuchemicHelper extends AbstractFechuchemicHelper {
    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * This method is not used, because the power logic is applied in the discharge methods of this class.
     *
     * @param player to whom the effect will be applied.
     *
     * @see AtiumFecuchemicHelper#calculateDischarge(CompoundTag, Player, IInvestedPlayerData, int, String, boolean)
     */
    @Override
    public void tapPower(Player player) {
        ModEffects.giveFeruchemicalTapEffect(player,MetalTagEnum.BRASS);
    }
    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * This method is not used, because the power logic is applied in the discharge methods of this class.
     *
     * @param player to whom the effect will be applied.
     */
    @Override
    public void storagePower(Player player) {
        ModEffects.giveFeruchemicalStorageEffect(player,MetalTagEnum.LERASIUM);
    }

    /**
     * Returns an instance of LerasiumFecuchemicHelper using a factory method pattern.
     * This method allows you to create instances of LerasiumFecuchemicHelper with a consistent interface.
     *
     * @return a Supplier that returns a new instance of LerasiumFecuchemicHelper when called
     */
    public static Supplier<? extends LerasiumFecuchemicHelper> getInstance() {
        return LerasiumFecuchemicHelper::new;
    }

    /**
     * Redefine of the method of the AbstractFechuchemicHelper class.
     * In this specific case, removes the basic interaction of nicrosil.
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
        compoundTag.putInt(metalKey,0);
        tapPower(player);
        return loadAllomanticReserve(playerCapability, compoundTag);
    }

    /**
     * Redefine of the method of the AbstractFechuchemicHelper class.
     * In this specific case, removes the basic interaction of nicrosil.
     * @param compoundTag the inside information of the metalmind.
     * @param player with the mindmetal equipped.
     * @param playerCapability capabilities (data) of the player.
     * @param metalReserve present value of the metal reserve.
     * @param metalKey metal key to be modified.
     * @param nicConsume control value of whether it is necessary to store charge or not.
     * @return CompoundTag metalmind information update.
     *
     * @see AbstractFechuchemicHelper#storagePower(Player)
     */
    @Override
    public CompoundTag calculateCharge(CompoundTag compoundTag, Player player, IInvestedPlayerData playerCapability, int metalReserve, String metalKey, boolean nicConsume) {
        if (havePlayerAnyReserve(playerCapability)) {
            compoundTag = saveAllomanticReserve(playerCapability, compoundTag);
            compoundTag.putInt(metalKey,1);
        }
        storagePower(player);
        return compoundTag;
    }

    /**
     * Returns if target player has any allomantic reserves
     *
     * @param playerCapability capabilities (data) of the player.
     * @return Boolean
     */
    public boolean havePlayerAnyReserve (IInvestedPlayerData playerCapability) {
        for (MetalTagEnum metal: MetalTagEnum.values()){
            if (playerCapability.getAllomanticAmount(metal)>0) {
                return true;
            }
        }
        return false;
    }

    /**
     * This is a unique method in the helpers, that is in charge of storing allomantic reserves in the metalmind and eliminating them from the target player.
     *
     * @param playerCapability capabilities (data) of the player.
     * @param compoundTag the inside information of the metalmind.
     * @return CompoundTag metalmind information update.
     */
    public CompoundTag saveAllomanticReserve(IInvestedPlayerData playerCapability, CompoundTag compoundTag) {
        ArrayList<MetalTagEnum> metals = playerCapability.getAllomanticPowers();
        int firstQty = 0;
        int qtyToRemove = 0;
        boolean continueSaving;

        for (MetalTagEnum metal: metals) {
            continueSaving = true;
            while (continueSaving) {
                if (firstQty == 0) {
                    firstQty = playerCapability.getAllomanticAmount(metal);
                    qtyToRemove = Math.toIntExact(Math.round(firstQty * 0.1));
                }
                continueSaving = playerCapability.substractAllomanticMetalAmount(metal, qtyToRemove);
                if (!continueSaving || firstQty == 0) {
                    firstQty = 0;
                    qtyToRemove = 0;
                    continueSaving = false;
                } else {
                    if (!compoundTag.contains(metal.getNameLower()+"inLerasiumBand")){ //no existe el tag
                        compoundTag.putInt(metal.getNameLower()+"inLerasiumBand",0);
                    }
                    compoundTag.putInt(metal.getNameLower()+"inLerasiumBand", compoundTag.getInt(metal.getNameLower()+"inLerasiumBand")+qtyToRemove);
                    if (compoundTag.getInt(metal.getNameLower()+"inLerasiumBand") > (metal.getMaxAllomanticTicksStorage()/2)) {
                        compoundTag.putInt(metal.getNameLower()+"inLerasiumBand",(metal.getMaxAllomanticTicksStorage()/2));
                        continueSaving = false;
                    }

                }
            }
        }
        return compoundTag;
    }

    /**
     * This is a unique method in the helpers, which is in charge of recover allomantic reserves the metalmind and return them to the target player.
     *
     * @param playerCapability capabilities (data) of the player.
     * @param compoundTag the inside information of the metalmind.
     * @return CompoundTag metalmind information update.
     */
    public CompoundTag loadAllomanticReserve(IInvestedPlayerData playerCapability, CompoundTag compoundTag) {
        ArrayList<MetalTagEnum> metals = playerCapability.getAllomanticPowers();
        int firstQty = 0;
        int qtyToAdd = 0;
        boolean continueLoading;

        for (MetalTagEnum metal: metals) {
            continueLoading = true;
            while (continueLoading) {
                if (firstQty == 0) {
                    firstQty = compoundTag.getInt(metal.getNameLower()+"inLerasiumBand");
                    qtyToAdd = Math.toIntExact(Math.round(firstQty * 0.1));
                }
                if (compoundTag.getInt(metal.getNameLower()+"inLerasiumBand") == 0) {
                    firstQty = 0;
                    qtyToAdd = 0;
                    continueLoading = false;
                } else {
                    if (!compoundTag.contains(metal.getNameLower()+"inLerasiumBand")){ //no existe el tag
                        compoundTag.putInt(metal.getNameLower()+"inLerasiumBand",0);
                    }
                    compoundTag.putInt(metal.getNameLower()+"inLerasiumBand", compoundTag.getInt(metal.getNameLower()+"inLerasiumBand")-qtyToAdd);
                    continueLoading = playerCapability.addAllomanticMetalAmount(metal, qtyToAdd);
                }
            }
        }
        return compoundTag;
    }
}
