package net.rudahee.metallics_arts.modules.custom_goals;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;

public class JumpGoal extends Goal {

    private final Mob entity;
    private final int power;
    private final int duration;
    private long lastCanUseCheck;

    public JumpGoal(Mob entity, int power, int duration) {
        this.entity = entity;
        this.power = power;
        this.duration = duration;
    }


    @Override
    public boolean canUse() {

        long gameTime = this.entity.level.getGameTime();
        if (gameTime - this.lastCanUseCheck < 160L) {
            return false;
        } else {
            if (entity.getTarget() instanceof ServerPlayer) {
                if (entity.distanceToSqr(entity.getTarget()) > 46.0D) {
                    this.lastCanUseCheck = gameTime;
                    entity.addEffect(new MobEffectInstance(MobEffects.JUMP, duration, power, true, true));
                    entity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, duration, power, true, true));
                    entity.getJumpControl().jump();
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }
}
