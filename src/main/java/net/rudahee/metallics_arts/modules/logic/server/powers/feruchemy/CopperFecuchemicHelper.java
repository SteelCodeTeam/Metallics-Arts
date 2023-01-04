package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.data.players.IInvestedPlayerData;

import java.util.function.Supplier;

public class CopperFecuchemicHelper extends AbstractFechuchemicHelper{
    @Override
    public void decantPower(Player player) {
        player.giveExperiencePoints(1);
    }

    @Override
    public void storagePower(Player player) {
        player.giveExperiencePoints(-1);
    }

    public static Supplier<? extends CopperFecuchemicHelper> getInstance() {
        return CopperFecuchemicHelper::new;
    }


    @Override
    public CompoundTag calculateDischarge(CompoundTag compoundTag, Player player, IInvestedPlayerData playerCapability, int metalReserve, String metalKey, boolean nicConsume) {
        decantPower(player);
        compoundTag.putInt(metalKey, metalReserve - 1);
        return compoundTag;
    }

    @Override
    public CompoundTag CalculateCharge(CompoundTag compoundTag, Player player, IInvestedPlayerData playerCapability, int metalReserve, String metalKey, boolean nicConsume) {
        if (player.totalExperience>0) {
            storagePower(player);
            compoundTag.putInt(metalKey, metalReserve + 1);
        }
        return compoundTag;
    }
}
