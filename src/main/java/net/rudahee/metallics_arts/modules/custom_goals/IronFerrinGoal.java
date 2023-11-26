package net.rudahee.metallics_arts.modules.custom_goals;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;

public class GoldFerrinGoal extends Goal {

    private final Mob entity;
    private final int power;
    private final int duration;
    private final int jump;

    public GoldFerrinGoal(Mob entity, int power, int duration, int jump) {
        this.entity = entity;
        this.power = power;
        this.duration = duration;
        this.jump = jump;
    }


    @Override
    public boolean canUse() {

        entity.addEffect(new MobEffectInstance(MobEffects.Fe, duration, power, true, true));

        return true;


    }
}
