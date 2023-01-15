package net.rudahee.metallics_arts.modules.custom_items.metal_minds.rings;

import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.RingsMindAbstract;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.hybrid_metals.BendalloyFecuchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.hybrid_metals.CadmiumFecuchemicHelper;

public class RingCadmiumBendalloy extends RingsMindAbstract <CadmiumFecuchemicHelper, BendalloyFecuchemicHelper> {
    public RingCadmiumBendalloy (Properties properties){
        super(properties, MetalTagEnum.CADMIUM, MetalTagEnum.BENDALLOY, CadmiumFecuchemicHelper.getInstance(), BendalloyFecuchemicHelper.getInstance());
    }

}