package net.rudahee.metallics_arts.modules.test;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class PowerEffect extends MobEffect {

    public PowerEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }
    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplificador) {
        return super.isDurationEffectTick(pDuration, pAmplificador);
    }
}
