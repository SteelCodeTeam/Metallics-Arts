package net.rudahee.metallics_arts.modules.custom_items.metal_minds.rings;

import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.RingsMindAbstract;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.PewterFeruchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.TinFeruchemicHelper;

public class RingTinPewter extends RingsMindAbstract<TinFeruchemicHelper, PewterFeruchemicHelper> {
    public RingTinPewter(Properties properties){
        super(properties, MetalTagEnum.TIN, MetalTagEnum.PEWTER,TinFeruchemicHelper.getInstance(), PewterFeruchemicHelper.getInstance());
    }

}
