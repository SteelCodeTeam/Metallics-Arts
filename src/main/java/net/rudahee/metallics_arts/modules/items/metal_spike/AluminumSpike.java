package net.rudahee.metallics_arts.modules.items.metal_spike;

import net.minecraft.nbt.CompoundNBT;

public class AluminumSpike extends MetalSpikeAbstract{
    CompoundNBT nbt = new CompoundNBT();
    public AluminumSpike(Properties properties) {
        super(properties);
        nbt.putBoolean("feruchemy", false);
        nbt.putBoolean("allomancy", false);
        setNbt(nbt);
    }
}
