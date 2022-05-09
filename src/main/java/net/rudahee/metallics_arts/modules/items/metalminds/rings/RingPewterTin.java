package net.rudahee.metallics_arts.modules.items.metalminds.rings;
import net.minecraft.nbt.CompoundNBT;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;

public class RingPewterTin extends RingsMindAbstract{
    CompoundNBT nbt = new CompoundNBT();
    public RingPewterTin (Properties properties){
        super(properties, MetalsNBTData.TIN,MetalsNBTData.PEWTER);
        nbt.putInt(MetallicsArts.MOD_ID+".RingPewterTin.tin",0);
        nbt.putInt(MetallicsArts.MOD_ID+".RingPewterTin.pewter",0);
        nbt.putInt(MetallicsArts.MOD_ID+".RingPewterTin.capacityTin",100);
        nbt.putInt(MetallicsArts.MOD_ID+".RingPewterTin.capacityPewter",100);
        setNbt(nbt);
    }
}
