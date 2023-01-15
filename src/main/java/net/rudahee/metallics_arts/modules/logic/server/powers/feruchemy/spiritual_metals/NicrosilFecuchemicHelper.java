package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.spiritual_metals;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.AbstractFechuchemicHelper;

import java.util.function.Supplier;

public class NicrosilFecuchemicHelper extends AbstractFechuchemicHelper {
    @Override
    public void tappingPower(Player player) {

    }

    @Override
    public void storagePower(Player player) {

    }

    public static Supplier<? extends NicrosilFecuchemicHelper> getInstance() {
        return NicrosilFecuchemicHelper::new;
    }

    @Override
    public CompoundTag calculateDischarge(CompoundTag compoundTag, Player player, IInvestedPlayerData playerCapability, int metalReserve, String metalKey, boolean nicConsume) {
        int value = playerCapability.cantMetalsTapping();
        if (nicConsume) {
            value = 0;
        }
        compoundTag.putInt(metalKey, metalReserve - value);
        return compoundTag;
    }

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
        return compoundTag;
    }
}
