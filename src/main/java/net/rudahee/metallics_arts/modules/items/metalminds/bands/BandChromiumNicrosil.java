package net.rudahee.metallics_arts.modules.items.metalminds.bands;

import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;

public class BandChromiumNicrosil extends BandMindAbstract {
    CompoundNBT nbt = new CompoundNBT();
    public BandChromiumNicrosil(Item.Properties properties){
        super(properties, MetalsNBTData.CHROMIUM,MetalsNBTData.NICROSIL);
        nbt.putInt(MetallicsArts.MOD_ID+".BandChromiumNicrosil.chromium",0);
        nbt.putInt(MetallicsArts.MOD_ID+".BandChromiumNicrosil.nicrosil",0);
        nbt.putInt(MetallicsArts.MOD_ID+".BandChromiumNicrosil.capacityChromium",100);
        nbt.putInt(MetallicsArts.MOD_ID+".BandChromiumNicrosil.capacityNicrosil",100);
        setNbt(nbt);
    }
}