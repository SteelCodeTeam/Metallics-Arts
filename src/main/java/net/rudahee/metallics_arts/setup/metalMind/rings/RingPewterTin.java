package net.rudahee.metallics_arts.setup.metalMind.rings;
import net.minecraft.nbt.CompoundNBT;

public class RingPewterTin extends RingsMindAbstract{
    CompoundNBT nbt;
    public RingPewterTin (Properties properties){
        super(properties);
        nbt.putInt("tin",0);
        nbt.putInt("pewter",0);
        nbt.putInt("capacityTin",100);
        nbt.putInt("capacityPewter",100);
        setNbt(nbt);
    }
}
