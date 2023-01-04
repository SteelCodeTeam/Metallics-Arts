package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.players.IInvestedPlayerData;

public abstract class AbstractFechuchemicHelper {
    public abstract void decantPower(Player player);
    public abstract void storagePower(Player player);


    //charge = storing
    //discharge = decanting

    public CompoundTag calculateDischarge(CompoundTag compoundTag, Player player, IInvestedPlayerData playerCapability, int metalReserve, String metalKey, boolean nicConsume) {
        if (!playerCapability.isDecanting(MetalTagEnum.NICROSIL) || !nicConsume) {
            compoundTag.putInt(metalKey, metalReserve - 1);
        }
        decantPower(player);
        return compoundTag;
    }
    public CompoundTag CalculateCharge(CompoundTag compoundTag, Player player, IInvestedPlayerData playerCapability, int metalReserve, String metalKey, boolean nicConsume) {
        if (!playerCapability.isStoring(MetalTagEnum.NICROSIL) || !nicConsume) {
            compoundTag.putInt(metalKey, metalReserve + 1);
        }
        storagePower(player);
        return compoundTag;
    }
}
