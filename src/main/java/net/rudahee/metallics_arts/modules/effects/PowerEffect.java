package net.rudahee.metallics_arts.modules.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

/**
 * A custom MobEffect that represents a power effect for the MetallicsArts mod.
 *
 *  @author SteelCode Team
 *  @since 1.5.1
 */
public class PowerEffect extends MobEffect {

    /**
     * Creates a new PowerEffect with the specified category and color.
     *
     * @param mobEffectCategory the category of the effect.
     * @param color the color of the effect.
     */
    public PowerEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }
    /**
     * Checks if the effect should be applied at the current tick of the duration.
     *
     * @param pDuration the current duration of the effect.
     * @param pAmplificador the current amplifier of the effect.
     * @return true if the effect should be applied, false otherwise.
     */
    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplificador) {
        return super.isDurationEffectTick(pDuration, pAmplificador);
    }
}
