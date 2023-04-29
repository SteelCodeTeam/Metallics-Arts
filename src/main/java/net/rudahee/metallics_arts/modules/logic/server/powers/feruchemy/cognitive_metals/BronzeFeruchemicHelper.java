package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.cognitive_metals;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.modules.effects.ModEffects;
import net.rudahee.metallics_arts.utils.CapabilityUtils;

/**
 * Helper class containing the methods and implementations for using feruchemical Bronze.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class BronzeFeruchemicHelper {
    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, for the power of the Bronze: Eliminate the target of the phantoms that chase you.
     *
     * @param player to whom the effect will be applied.
     *
     */
    public static void tapPower(Player player) {
        player.getLevel().getEntitiesOfClass(Phantom.class, CapabilityUtils.getBubble(player,12)).forEach(entity -> {
            if (entity instanceof Phantom) {
                if (entity.getTarget() == player) {
                    entity.setAggressive(false);
                    entity.setTarget(null);
                }
            }
        });
        ModEffects.giveFeruchemicalTapEffect(player, MetalTagEnum.BRONZE);
    }
    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, for the power of the Bronze: Spawns phantoms with fire resistance with the target in the player.
     *
     * @param player to whom the effect will be applied.
     *
     */
    public static void storagePower(Player player) {
        Level level = player.getLevel();
        Phantom phantom = new Phantom(EntityType.PHANTOM, level);
        phantom.setPos(player.position().x,player.position().y + 4, player.position().z);
        phantom.setTarget(player);
        phantom.setAggressive(true);
        phantom.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 280, 2, false, true));
        level.addFreshEntity(phantom);

        ModEffects.giveFeruchemicalStorageEffect(player,MetalTagEnum.BRONZE);
    }


}
