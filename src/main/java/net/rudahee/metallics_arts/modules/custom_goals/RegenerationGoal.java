package net.rudahee.metallics_arts.modules.custom_goals;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;

public class RegenerationGoal extends Goal {


    private final Mob entity;
    private final int power;
    private final int duration;

    public RegenerationGoal(Mob entity, int power, int duration) {
        this.entity = entity;
        this.power = power;
        this.duration = duration;
    }


    @Override
    public boolean canUse() {

        entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, duration, power, true, true));

        return true;


    }


}
