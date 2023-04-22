package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.physical_metals;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.AbstractFechuchemicHelper;
import net.rudahee.metallics_arts.modules.effects.ModEffects;

import java.util.function.Supplier;

/**
 * Helper class containing the methods and implementations for using feruchemical Iron.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class IronFecuchemicHelper extends AbstractFechuchemicHelper {
    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, for the power of the Iron: slow movement and abort jump will be applied to the target player.
     *
     * @param player to whom the effect will be applied.
     *
     * @see AbstractFechuchemicHelper#tapPower(Player)
     */
    @Override
    public void tapPower(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 120, 5, false, false));
        // apply jump 128 nullifies the target player's ability to jump
        player.addEffect(new MobEffectInstance(MobEffects.JUMP, 120, 128, false, false));
        ModEffects.giveFeruchemicalTapEffect(player, MetalTagEnum.IRON);
    }
    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, for the power of the Iron: slow falling, jump and weakness will be applied to the target player.
     *
     * @param player to whom the effect will be applied.
     *
     * @see AbstractFechuchemicHelper#storagePower(Player) 
     */
    @Override
    public void storagePower(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 120, 1, false, false));
        player.addEffect(new MobEffectInstance(MobEffects.JUMP, 120, 2, false, false));
        player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 120, 1, false, false));
        ModEffects.giveFeruchemicalStorageEffect(player,MetalTagEnum.IRON);
    }

    /**
     * Returns an instance of IronFecuchemicHelper using a factory method pattern.
     * This method allows you to create instances of IronFecuchemicHelper with a consistent interface.
     *
     * @return a Supplier that returns a new instance of IronFecuchemicHelper when called
     */
    public static Supplier<? extends IronFecuchemicHelper> getInstance() {
        return IronFecuchemicHelper::new;
    }
}
