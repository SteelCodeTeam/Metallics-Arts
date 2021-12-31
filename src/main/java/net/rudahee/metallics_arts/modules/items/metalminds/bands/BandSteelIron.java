package net.rudahee.metallics_arts.modules.items.metalminds.bands;

import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.rudahee.metallics_arts.MetallicsArts;

public class BandSteelIron extends BandMindAbstract {
    CompoundNBT nbt = new CompoundNBT();
    public BandSteelIron (Item.Properties properties){
        super(properties);
        nbt.putInt(MetallicsArts.MOD_ID+".BandSteelIron.steel",0);
        nbt.putInt(MetallicsArts.MOD_ID+".BandSteelIron.iron",0);
        nbt.putInt(MetallicsArts.MOD_ID+".BandSteelIron.capacitySteel",100);
        nbt.putInt(MetallicsArts.MOD_ID+".BandSteelIron.capacityIron",100);
        setNbt(nbt);
    }
}