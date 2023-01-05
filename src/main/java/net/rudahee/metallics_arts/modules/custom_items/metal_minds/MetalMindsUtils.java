package net.rudahee.metallics_arts.modules.custom_items.metal_minds;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;

public class MetalMindsUtils {

    public static String unkeyedString = "Nobody";


    public static CompoundTag changeOwner(Player player, CompoundTag compoundNBT, boolean iStoreMetal, MetalTagEnum metal0, MetalTagEnum metal1) {
        compoundNBT = (metal1 != MetalTagEnum.DURALUMIN) ?
                doubleMetal(player,compoundNBT,iStoreMetal,metal0,metal1) :
                singleMetal(player,compoundNBT,iStoreMetal,metal1);

        return compoundNBT;
    }

    public static CompoundTag doubleMetal(Player player, CompoundTag compoundNBT, boolean iStoreMetal, MetalTagEnum metal0, MetalTagEnum metal1) {

        boolean isFirstReserveZero = compoundNBT.getInt(metal0.getNameLower()+"_feruchemic_reserve") == 0;
        boolean isSecondReserveZero = compoundNBT.getInt(metal1.getNameLower()+"_feruchemic_reserve") == 0;

        player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(data -> {
            if (isFirstReserveZero && isSecondReserveZero && !data.isStoring(MetalTagEnum.ALUMINUM) &&
                    !data.isDecanting(MetalTagEnum.ALUMINUM) && iStoreMetal){
                compoundNBT.putString("key", player.getStringUUID());

            } else if (isFirstReserveZero && isSecondReserveZero && !data.isStoring(MetalTagEnum.ALUMINUM) &&
                    !data.isDecanting(MetalTagEnum.ALUMINUM) && !iStoreMetal){
                compoundNBT.putString("key", unkeyedString);

            }
            else if (data.isStoring(MetalTagEnum.ALUMINUM)) {
                compoundNBT.putString("key", unkeyedString);
            } else if (data.isDecanting(MetalTagEnum.ALUMINUM)){
                compoundNBT.putString("key", player.getStringUUID());
            }
        });

        return compoundNBT;
    }



    public static CompoundTag singleMetal(Player player, CompoundTag compoundNBT,boolean iStoreMetal, MetalTagEnum metal1) {
        boolean isSecondReserveZero = compoundNBT.getInt(metal1.getNameLower()+"_feruchemic_reserve") == 0;
        player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(data -> {
            if (isSecondReserveZero && !data.isStoring(MetalTagEnum.ALUMINUM) &&
                    !data.isDecanting(MetalTagEnum.ALUMINUM) && iStoreMetal){
                compoundNBT.putString("key", player.getStringUUID());
            } else if (isSecondReserveZero && !data.isStoring(MetalTagEnum.ALUMINUM) &&
                    !data.isDecanting(MetalTagEnum.ALUMINUM) && !iStoreMetal){
                compoundNBT.putString("key", unkeyedString);
            }
            else if (data.isStoring(MetalTagEnum.ALUMINUM)) {
                compoundNBT.putString("key", unkeyedString);
            } else if (data.isDecanting(MetalTagEnum.ALUMINUM)){
                compoundNBT.putString("key", player.getStringUUID());
            }
        });
        return compoundNBT;
    }

}
