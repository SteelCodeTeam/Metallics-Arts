package net.rudahee.metallics_arts.utils;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;

public class MetalMindsUtils {
    public static String unkeyedString = "Nobody";

    /**
     * Modify the internal information of the metalmind with the player as owner if necessary.
     *
     * @param player to whom the effect will be applied.
     * @param compoundTag the inside information of the metalmind.
     * @param iStoreMetal if the player is storing any metal.
     * @param metal0 first metal of the metalmind
     * @param metal1 second metal of the metalmind
     *
     * @return CompoundTag metalmind information update.
     */
    public static CompoundTag changeOwner(Player player, CompoundTag compoundTag, boolean iStoreMetal, MetalTagEnum metal0, MetalTagEnum metal1) {
        boolean isFirstReserveZero = compoundTag.getInt(metal0.getNameLower()+"_feruchemic_reserve") == 0;
        boolean isSecondReserveZero = compoundTag.getInt(metal1.getNameLower()+"_feruchemic_reserve") == 0;

        player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(data -> {
            if (isFirstReserveZero && isSecondReserveZero && !data.isStoring(MetalTagEnum.ALUMINUM) &&
                    !data.isTapping(MetalTagEnum.ALUMINUM) && iStoreMetal){
                compoundTag.putString("key", player.getStringUUID());
            } else if (isFirstReserveZero && isSecondReserveZero && !data.isStoring(MetalTagEnum.ALUMINUM) &&
                    !data.isTapping(MetalTagEnum.ALUMINUM) && !iStoreMetal){
                compoundTag.putString("key", unkeyedString);
            }
            else if (data.isStoring(MetalTagEnum.ALUMINUM)) {
                compoundTag.putString("key", unkeyedString);
            } else if (data.isTapping(MetalTagEnum.ALUMINUM)){
                compoundTag.putString("key", player.getStringUUID());
            }
        });
        return compoundTag;
    }

    /**
     * Modify the internal information of the metalmind with the player as owner if necessary, using only the second metal.
     *
     * @param player to whom the effect will be applied.
     * @param compoundTag the inside information of the metalmind.
     * @param iStoreMetal if the player is storing
     * @param metal1 second metal of the metalmind
     *
     * @return CompoundTag metalmind information update.
     */
    public static CompoundTag changeOwner(Player player, CompoundTag compoundTag,boolean iStoreMetal, MetalTagEnum metal1) {
        boolean isSecondReserveZero = compoundTag.getInt(metal1.getNameLower()+"_feruchemic_reserve") == 0;
        player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(data -> {
            if (isSecondReserveZero && !data.isStoring(MetalTagEnum.ALUMINUM) &&
                    !data.isTapping(MetalTagEnum.ALUMINUM) && iStoreMetal){
                compoundTag.putString("key", player.getStringUUID());
            } else if (isSecondReserveZero && !data.isStoring(MetalTagEnum.ALUMINUM) &&
                    !data.isTapping(MetalTagEnum.ALUMINUM) && !iStoreMetal){
                compoundTag.putString("key", unkeyedString);
            }
            else if (data.isStoring(MetalTagEnum.ALUMINUM)) {
                compoundTag.putString("key", unkeyedString);
            } else if (data.isTapping(MetalTagEnum.ALUMINUM)){
                compoundTag.putString("key", player.getStringUUID());
            }
        });
        return compoundTag;
    }
}
