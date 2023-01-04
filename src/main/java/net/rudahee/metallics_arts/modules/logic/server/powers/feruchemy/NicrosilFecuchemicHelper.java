package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.players.IInvestedPlayerData;

import java.util.function.Supplier;

public class NicrosilFecuchemicHelper extends AbstractFechuchemicHelper{
    @Override
    public void decantPower(Player player) {

    }

    @Override
    public void storagePower(Player player) {

    }

    public static Supplier<? extends NicrosilFecuchemicHelper> getInstance() {
        return NicrosilFecuchemicHelper::new;
    }

    @Override
    public CompoundTag calculateDischarge(CompoundTag compoundTag, Player player, IInvestedPlayerData playerCapability, int metalReserve, String metalKey, boolean nicConsume) {
        int value = playerCapability.cantMetalsDecanting();
        if (nicConsume) {
            value = 0;
        }
        compoundTag.putInt(metalKey, metalReserve - value);

        return compoundTag;
    }

    @Override
    public CompoundTag CalculateCharge(CompoundTag compoundTag, Player player, IInvestedPlayerData playerCapability, int metalReserve, String metalKey, boolean nicConsume) {
        int value = playerCapability.cantMetalsStoring();

        if (playerCapability.isStoring(MetalTagEnum.BRASS) && !player.isOnFire()){
            value = value - 1;
        }

        if (nicConsume) {
            value = 0;
        }
        compoundTag.putInt(metalKey, metalReserve + value);

        return compoundTag;
    }
}
