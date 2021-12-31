package net.rudahee.metallics_arts.setup.metalMind.band;

import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.rudahee.metallics_arts.MetallicsArts;

public class BandCadmiumBendalloy extends BandMindAbstract {
    CompoundNBT nbt = new CompoundNBT();
    public BandCadmiumBendalloy (Item.Properties properties){
        super(properties);
        nbt.putInt(MetallicsArts.MOD_ID+".BandCadmiumBendalloy.candmium",0);
        nbt.putInt(MetallicsArts.MOD_ID+".BandCadmiumBendalloy.bendalloy",0);
        nbt.putInt(MetallicsArts.MOD_ID+".BandCadmiumBendalloy.capacityCadmium",100);
        nbt.putInt(MetallicsArts.MOD_ID+".BandCadmiumBendalloy.capacityBendalloy",100);
        setNbt(nbt);
    }
}