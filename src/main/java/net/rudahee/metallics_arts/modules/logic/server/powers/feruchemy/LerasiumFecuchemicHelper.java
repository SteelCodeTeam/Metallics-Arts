package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.players.IInvestedPlayerData;

import java.util.ArrayList;
import java.util.function.Supplier;

public class LerasiumFecuchemicHelper extends AbstractFechuchemicHelper{
    @Override
    public void decantPower(Player player) {

    }

    @Override
    public void storagePower(Player player) {

    }

    public static Supplier<? extends LerasiumFecuchemicHelper> getInstance() {
        return LerasiumFecuchemicHelper::new;
    }

    @Override
    public CompoundTag calculateDischarge(CompoundTag compoundTag, Player player, IInvestedPlayerData playerCapability, int metalReserve, String metalKey, boolean nicConsume) {
        compoundTag.putInt(metalKey,0);
        return loadAllomanticReserve(playerCapability, compoundTag);
    }

    @Override
    public CompoundTag CalculateCharge(CompoundTag compoundTag, Player player, IInvestedPlayerData playerCapability, int metalReserve, String metalKey, boolean nicConsume) {
        if (havePlayerAnyReserve(playerCapability)) {
            compoundTag = saveAllomanticReserve(playerCapability, compoundTag);
            compoundTag.putInt(metalKey,1);
        }
        return compoundTag;
    }

    public boolean havePlayerAnyReserve (IInvestedPlayerData playerCapability) {
        for (MetalTagEnum metal: MetalTagEnum.values()){
            if (playerCapability.getAllomanticAmount(metal)>0) {
                return true;
            }
        }
        return false;
    }

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

    public CompoundTag loadAllomanticReserve(IInvestedPlayerData playerCapability, CompoundTag compoundTag) {
        ArrayList<MetalTagEnum> metals = playerCapability.getAllomanticPowers();
        int firstQty = 0;
        int qtyToAdd = 0;
        boolean continueLoading = true;

        for (MetalTagEnum metal: metals) {
            continueLoading = true;
            while (continueLoading) {
                if (firstQty == 0) {
                    firstQty = compoundTag.getInt(metal.getNameLower()+"inLerasiumBand");
                    qtyToAdd = Math.toIntExact(Math.round(firstQty * 0.1));
                }
                if (!continueLoading || compoundTag.getInt(metal.getNameLower()+"inLerasiumBand") == 0) {
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
