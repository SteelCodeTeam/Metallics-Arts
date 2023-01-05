package net.rudahee.metallics_arts.modules.custom_items.metal_minds.rings;


import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.RingsMindAbstract;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.AluminumFecuchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.ChromiumFecuchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.DuraluminFecuchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.NicrosilFecuchemicHelper;

public class RingChromiumNicrosil extends RingsMindAbstract <ChromiumFecuchemicHelper, NicrosilFecuchemicHelper> {

    public RingChromiumNicrosil(Properties properties){
        super(properties, MetalTagEnum.CHROMIUM, MetalTagEnum.NICROSIL, ChromiumFecuchemicHelper.getInstance(), NicrosilFecuchemicHelper.getInstance());
    }

}