package net.rudahee.metallics_arts.modules.items.metalminds.bands;

import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class BandAluminumDuralumin extends BandMindAbstract implements ICurioItem {

    public BandAluminumDuralumin (Item.Properties properties){
        super(properties, MetalsNBTData.ALUMINUM,MetalsNBTData.DURALUMIN,MetalsNBTData.ALUMINUM.getMaxReserveBand(),MetalsNBTData.DURALUMIN.getMaxReserveBand());
    }

    @Override
    public ActionResultType useOn(ItemUseContext p_195939_1_) {
        return super.useOn(p_195939_1_);
    }

}