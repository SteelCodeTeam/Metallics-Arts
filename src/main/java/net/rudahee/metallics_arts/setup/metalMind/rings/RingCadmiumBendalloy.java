package net.rudahee.metallics_arts.setup.metalMind.rings;

import net.minecraft.nbt.CompoundNBT;
import net.rudahee.metallics_arts.MetallicsArts;

public class RingCadmiumBendalloy extends RingsMindAbstract{
    CompoundNBT nbt = new CompoundNBT();
    public RingCadmiumBendalloy (Properties properties){
        super(properties);
        nbt.putInt(MetallicsArts.MOD_ID+".RingCadmiumBendalloy.candmium",0);
        nbt.putInt(MetallicsArts.MOD_ID+".RingCadmiumBendalloy.bendalloy",0);
        nbt.putInt(MetallicsArts.MOD_ID+".RingCadmiumBendalloy.capacityCadmium",100);
        nbt.putInt(MetallicsArts.MOD_ID+".RingCadmiumBendalloy.capacityBendalloy",100);
        setNbt(nbt);
    }
}