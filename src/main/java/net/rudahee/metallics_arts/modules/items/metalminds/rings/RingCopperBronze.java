package net.rudahee.metallics_arts.modules.items.metalminds.rings;

import net.minecraft.nbt.CompoundNBT;
import net.rudahee.metallics_arts.MetallicsArts;

public class RingCopperBronze extends RingsMindAbstract{
    CompoundNBT nbt = new CompoundNBT();
    public RingCopperBronze(Properties properties){
        super(properties);
        nbt.putInt(MetallicsArts.MOD_ID+".RingCopperBronze.copper",0);
        nbt.putInt(MetallicsArts.MOD_ID+".RingCopperBronze.bronze",0);
        nbt.putInt(MetallicsArts.MOD_ID+".RingCopperBronze.capacityCopper",100);
        nbt.putInt(MetallicsArts.MOD_ID+".RingCopperBronze.capacityBronze",100);
        setNbt(nbt);
    }
}