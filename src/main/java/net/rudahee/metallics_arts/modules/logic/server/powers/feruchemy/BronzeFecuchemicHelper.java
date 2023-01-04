package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.rudahee.metallics_arts.modules.logic.server.ServerEventHandler;
import net.rudahee.metallics_arts.utils.CapabilityUtils;
import net.rudahee.metallics_arts.utils.event_utils.OnWorldTickEvent;

import java.util.function.Supplier;


public class BronzeFecuchemicHelper extends AbstractFechuchemicHelper{
    @Override
    public void decantPower(Player player) {
        player.getLevel().getEntitiesOfClass(Phantom.class, CapabilityUtils.getBubble(player,12)).forEach(entity -> {
            if (entity instanceof Phantom) {
                if (entity.getTarget() == player) {
                    entity.setAggressive(false);
                    entity.setTarget(null);
                }
            }
        });
    }

    @Override
    public void storagePower(Player player) {

        Level level = player.getLevel();

        if (OnWorldTickEvent.activationEvery240Tick(ServerEventHandler.getActualTick())) {
            Phantom phantom = new Phantom(EntityType.PHANTOM, level);
            phantom.setPos(player.position().x,player.position().y + 4, player.position().z);
            phantom.setTarget(player);
            phantom.setAggressive(true);
            phantom.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 240, 2, true, true, true));
            level.addFreshEntity(phantom);
        }


    }

    public static Supplier<? extends BronzeFecuchemicHelper> getInstance() {
        return BronzeFecuchemicHelper::new;
    }
}
