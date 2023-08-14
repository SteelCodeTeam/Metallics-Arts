package net.rudahee.metallics_arts.setup.registries.particle;

import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;

public class ModSimpleParticleRegister {

    public static final SimpleParticleType ITEM_BULLET = register("item_bullet", false);

    private static SimpleParticleType register(String p_123825_, boolean p_123826_) {
        return Registry.register(Registry.PARTICLE_TYPE, p_123825_, new SimpleParticleType(p_123826_));
    }
}
