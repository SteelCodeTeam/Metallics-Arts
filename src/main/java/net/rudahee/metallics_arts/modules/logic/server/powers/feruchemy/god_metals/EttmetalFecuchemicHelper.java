package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.god_metals;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Explosion;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.AbstractFechuchemicHelper;
import net.rudahee.metallics_arts.modules.test.ModEffects;

import java.util.function.Supplier;

/**
 * Helper class containing the methods and implementations for using feruchemical Ettmetal.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class EttmetalFecuchemicHelper extends AbstractFechuchemicHelper {

    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * This method is not used, because the power logic is applied in the discharge methods of this class.
     *
     * @param player to whom the effect will be applied.
     */
    @Override
    public void tapPower(Player player) {
        ModEffects.giveFeruchemicalTapEffect(player, MetalTagEnum.ETTMETAL);
    }

    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * This method is not used, because the power logic is applied in the charge methods of this class.
     *
     * @param player to whom the effect will be applied.
     */
    @Override
    public void storagePower(Player player) {
        ModEffects.giveFeruchemicalStorageEffect(player,MetalTagEnum.ETTMETAL);
    }

    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, it generates the explosion based on the charge values.
     *
     * @param compoundTag the inside information of the metalmind.
     * @param player with the mindmetal equipped.
     * @param playerCapability capabilities (data) of the player.
     * @param metalReserve present value of the metal reserve.
     * @param metalKey metal key to be modified.
     * @param nicConsume control value of whether it is necessary to store charge or not.
     * @return CompoundTag metalmind information update.
     *
     * @see AbstractFechuchemicHelper#tapPower(Player)
     */
    /*@Override
    public CompoundTag calculateDischarge(CompoundTag compoundTag, Player player, IInvestedPlayerData playerCapability, int metalReserve, String metalKey, boolean nicConsume) {
        player.level.explode(player,player.position().x,player.position().y,player.position().z,(float) compoundTag.getInt(metalKey)/683, Explosion.BlockInteraction.NONE);
        player.setHealth((player.getHealth() - ((float) compoundTag.getInt(metalKey)/205)));
        compoundTag.putInt(metalKey,0);
        return compoundTag;
    }*/

    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, only charge when target player take damage from explosions.
     *
     * @param compoundTag the inside information of the metalmind.
     * @param player with the mindmetal equipped.
     * @param playerCapability capabilities (data) of the player.
     * @param metalReserve present value of the metal reserve.
     * @param metalKey metal key to be modified.
     * @param nicConsume control value of whether it is necessary to store charge or not.
     * @return CompoundTag metalmind information update.
     *
     * @see AbstractFechuchemicHelper#storagePower(Player)
     */
    /*@Override
    public CompoundTag calculateCharge(CompoundTag compoundTag, Player player, IInvestedPlayerData playerCapability, int metalReserve, String metalKey, boolean nicConsume) {
        if (player.getLastDamageSource() != null){
            if ((player.getLastDamageSource().isExplosion())){
                compoundTag.putInt(metalKey, metalReserve + 1);
            }
        }
        return compoundTag;
    }*/

    /**
     * Returns an instance of EttmetalFecuchemicHelper using a factory method pattern.
     * This method allows you to create instances of EttmetalFecuchemicHelper with a consistent interface.
     *
     * @return a Supplier that returns a new instance of EttmetalFecuchemicHelper when called
     */
    public static Supplier<? extends EttmetalFecuchemicHelper> getInstance() {
        return EttmetalFecuchemicHelper::new;
    }
}
