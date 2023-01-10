package net.rudahee.metallics_arts.modules.custom_items.metal_minds.rings;

import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.RingsMindAbstract;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.BrassFecuchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.ZincFecuchemicHelper;

public class RingZincBrass extends RingsMindAbstract <ZincFecuchemicHelper, BrassFecuchemicHelper> {
    public RingZincBrass (Properties properties){
        super(properties, MetalTagEnum.ZINC, MetalTagEnum.BRASS, ZincFecuchemicHelper.getInstance(), BrassFecuchemicHelper.getInstance());
    }
}