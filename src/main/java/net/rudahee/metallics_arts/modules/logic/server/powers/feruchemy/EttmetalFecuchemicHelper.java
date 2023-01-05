package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Explosion;
import net.rudahee.metallics_arts.data.players.IInvestedPlayerData;

import java.util.function.Supplier;

public class EttmetalFecuchemicHelper extends AbstractFechuchemicHelper{
    @Override
    public void decantPower(Player player) {
    }

    @Override
    public void storagePower(Player player) {
    }

    public static Supplier<? extends EttmetalFecuchemicHelper> getInstance() {
        return EttmetalFecuchemicHelper::new;
    }

    @Override
    public CompoundTag calculateDischarge(CompoundTag compoundTag, Player player, IInvestedPlayerData playerCapability, int metalReserve, String metalKey, boolean nicConsume) {
        player.level.explode(player,player.position().x,player.position().y,player.position().z,(float) compoundTag.getInt(metalKey)/683, Explosion.BlockInteraction.NONE);
        player.setHealth((player.getHealth() - ((float) compoundTag.getInt(metalKey)/205)));
        compoundTag.putInt(metalKey,0);
        return compoundTag;
    }

    @Override
    public CompoundTag CalculateCharge(CompoundTag compoundTag, Player player, IInvestedPlayerData playerCapability, int metalReserve, String metalKey, boolean nicConsume) {
        if (player.getLastDamageSource() != null){
            if ((player.getLastDamageSource().isExplosion())){
                compoundTag.putInt(metalKey, metalReserve + 1);
            }
        }
        return compoundTag;
    }
}
