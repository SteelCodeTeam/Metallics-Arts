package net.rudahee.metallics_arts.modules.custom_items.metal_minds.rings;


import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.RingsMindAbstract;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.IronFecuchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.SteelFecuchemicHelper;

public class RingIronSteel extends RingsMindAbstract <IronFecuchemicHelper, SteelFecuchemicHelper> {
    public RingIronSteel(Properties properties){
        super(properties, MetalTagEnum.IRON, MetalTagEnum.STEEL, IronFecuchemicHelper.getInstance(), SteelFecuchemicHelper.getInstance());
    }
}