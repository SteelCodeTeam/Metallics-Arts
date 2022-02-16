package net.rudahee.metallics_arts.modules.items.metal_spike;

import net.minecraft.nbt.CompoundNBT;

public class ChromiumSpike extends MetalSpikeAbstract{
    CompoundNBT nbt = new CompoundNBT();
    public ChromiumSpike(Properties properties) {
        super(properties);
        nbt.putBoolean("feruchemy", false);
        nbt.putBoolean("allomancy", false);
        setNbt(nbt);
    }
}
