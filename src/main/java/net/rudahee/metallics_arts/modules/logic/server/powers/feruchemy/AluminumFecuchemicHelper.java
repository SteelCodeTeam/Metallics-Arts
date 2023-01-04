package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.players.IInvestedPlayerData;

import java.util.function.Supplier;

public class AluminumFecuchemicHelper extends AbstractFechuchemicHelper{
    @Override
    public void decantPower(Player player) {

    }

    @Override
    public void storagePower(Player player) {

    }

    public static Supplier<? extends AluminumFecuchemicHelper> getInstance() {
        return AluminumFecuchemicHelper::new;
    }

    @Override
    public CompoundTag calculateDischarge(CompoundTag compoundTag, Player player, IInvestedPlayerData playerCapability, int metalReserve, String metalKey, boolean nicConsume) {
        compoundTag.putInt(metalKey, 1);
        return compoundTag;
    }

    @Override
    public CompoundTag CalculateCharge(CompoundTag compoundTag, Player player, IInvestedPlayerData playerCapability, int metalReserve, String metalKey, boolean nicConsume) {
        compoundTag.putInt(metalKey, 2);
        return compoundTag;
    }
}
