package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.cognitive_metals;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.AbstractFechuchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.god_metals.AtiumFecuchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.server_events.OnDamageEvent;
import net.rudahee.metallics_arts.modules.test.ModEffects;

import java.util.function.Supplier;

/**
 * Helper class containing the methods and implementations for using feruchemical Copper.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class CopperFecuchemicHelper extends AbstractFechuchemicHelper {
    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, for the power of the Copper: Increases the target player's amount experience.
     *
     * @param player to whom the effect will be applied.
     *
     * @see AbstractFechuchemicHelper#tappingPower(Player)
     */
    @Override
    public void tappingPower(Player player) {
        player.giveExperiencePoints(1);
        player.addEffect(new MobEffectInstance(ModEffects.POWER_EFFECTS.get("copper").get(), 1, 1, true, true));
    }
    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, for the power of the Copper: Decreases the target player's amount experience.
     *
     * @param player to whom the effect will be applied.
     *
     * @see AbstractFechuchemicHelper#tappingPower(Player)
     */
    @Override
    public void storagePower(Player player) {
        player.giveExperiencePoints(-1);
        player.addEffect(new MobEffectInstance(ModEffects.POWER_EFFECTS.get("copper").get(), 1, 1, true, true));
    }
    //todo
    public static Supplier<? extends CopperFecuchemicHelper> getInstance() {
        return CopperFecuchemicHelper::new;
    }

    /**
     * Redefine of the method of the AbstractFechuchemicHelper class.
     * In this specific case, removes the basic interaction of nicrosil.
     *
     * @param compoundTag the inside information of the metalmind.
     * @param player with the mindmetal equipped.
     * @param playerCapability capabilities (data) of the player.
     * @param metalReserve present value of the metal reserve.
     * @param metalKey metal key to be modified.
     * @param nicConsume control value of whether it is necessary to store charge or not.
     * @return CompoundTag metalmind information update.
     *
     * @see AbstractFechuchemicHelper#tappingPower(Player)
     */
    @Override
    public CompoundTag calculateDischarge(CompoundTag compoundTag, Player player, IInvestedPlayerData playerCapability, int metalReserve, String metalKey, boolean nicConsume) {
        tappingPower(player);
        compoundTag.putInt(metalKey, metalReserve - 1);
        return compoundTag;
    }

    /**
     * Redefine of the method of the AbstractFechuchemicHelper class.
     * In this specific case, only charge when target player has experience.
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
    @Override
    public CompoundTag calculateCharge(CompoundTag compoundTag, Player player, IInvestedPlayerData playerCapability, int metalReserve, String metalKey, boolean nicConsume) {
        if (player.totalExperience>0) {
            storagePower(player);
            compoundTag.putInt(metalKey, metalReserve + 1);
        }
        return compoundTag;
    }
}
