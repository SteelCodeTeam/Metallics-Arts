package net.rudahee.metallics_arts.setup.metalMind.rings;
import net.minecraft.nbt.CompoundNBT;
import net.rudahee.metallics_arts.MetallicsArts;

public class RingPewterTin extends RingsMindAbstract{
    CompoundNBT nbt = new CompoundNBT();
    public RingPewterTin (Properties properties){
        super(properties);
        nbt.putInt(MetallicsArts.MOD_ID+".RingPewterTin.tin",0);
        nbt.putInt(MetallicsArts.MOD_ID+".RingPewterTin.pewter",0);
        nbt.putInt(MetallicsArts.MOD_ID+".RingPewterTin.capacityTin",100);
        nbt.putInt(MetallicsArts.MOD_ID+".RingPewterTin.capacityPewter",100);
        setNbt(nbt);
    }
}
