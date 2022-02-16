package net.rudahee.metallics_arts.modules.items.metal_spike;

import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.rudahee.metallics_arts.MetallicsArts;

public abstract class MetalSpikeAbstract extends Item {

    private CompoundNBT nbt;

    public MetalSpikeAbstract(Properties properties) {
        super(properties);
    }

    public CompoundNBT getNbt() {
        return nbt;
    }

    public void setNbt(CompoundNBT nbt) {
        this.nbt = nbt;
    }

    public void setFeruchemy(boolean bool){
        this.nbt.putBoolean("feruchemy", bool);
    }

    public void setAllomancy(boolean bool){
        this.nbt.putBoolean("allomancy", bool);
    }

}
