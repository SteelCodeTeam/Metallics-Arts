package net.rudahee.metallics_arts.modules.items.metal_spike;

import net.minecraft.nbt.CompoundNBT;

public class NicrosilSpike extends MetalSpikeAbstract{
    CompoundNBT nbt = new CompoundNBT();
    public NicrosilSpike(Properties properties) {
        super(properties);
        nbt.putBoolean("feruchemy", false);
        nbt.putBoolean("allomancy", false);
        setNbt(nbt);
    }
}
