package net.rudahee.metallics_arts.utils;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.error_handling.exceptions.PlayerException;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;

public class MetalMindsUtils {
    public static String unkeyedString = "Nobody";

    /**
     * Modify the internal information of the metalmind with the player as owner if necessary.
     *
     * @param player to whom the effect will be applied.
     * @param compoundTag the inside information of the metalmind.
     * @param iStoreMetal true if the player is storing any metal, else if not
     * @param metal0 first metal of the metalmind
     * @param metal1 second metal of the metalmind
     *
     * @return CompoundTag metalmind information update.
     */
    public static CompoundTag changeOwner(Player player, CompoundTag compoundTag, boolean iStoreMetal, MetalTagEnum metal0, MetalTagEnum metal1) {

        boolean isFirstReserveZero = metal0.equals(MetalTagEnum.ALUMINUM) || compoundTag.getInt(metal0.getNameLower() + "_feruchemic_reserve") == 0;
        boolean isSecondReserveZero = compoundTag.getInt(metal1.getNameLower()+"_feruchemic_reserve") == 0;

        IInvestedPlayerData playerCapability;
        try {
            playerCapability = CapabilityUtils.getCapability(player);

            if (playerCapability.isStoring(MetalTagEnum.ALUMINUM)) {
                compoundTag.putString("key", unkeyedString);
            } else if (playerCapability.isTapping(MetalTagEnum.ALUMINUM)) {
                compoundTag.putString("key", player.getStringUUID());
            } else  {
                if (isFirstReserveZero && isSecondReserveZero) {
                    if (iStoreMetal) {
                        compoundTag.putString("key", player.getStringUUID());
                    } else {
                        compoundTag.putString("key", unkeyedString);
                    }
                }
            }
        } catch (PlayerException ex) {
            ex.printCompleteLog();
            return compoundTag;
        }
        return compoundTag;
    }
}
