package net.rudahee.metallics_arts.modules.items.metalminds.bands;

import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;

public class BandCadmiumBendalloy extends BandMindAbstract {
    CompoundNBT nbt = new CompoundNBT();
    public BandCadmiumBendalloy (Item.Properties properties){
        super(properties, MetalsNBTData.CADMIUM,MetalsNBTData.BENDALLOY);
        nbt.putInt(MetallicsArts.MOD_ID+".BandCadmiumBendalloy.candmium",0);
        nbt.putInt(MetallicsArts.MOD_ID+".BandCadmiumBendalloy.bendalloy",0);
        nbt.putInt(MetallicsArts.MOD_ID+".BandCadmiumBendalloy.capacityCadmium",100);
        nbt.putInt(MetallicsArts.MOD_ID+".BandCadmiumBendalloy.capacityBendalloy",100);
        setNbt(nbt);
    }
}