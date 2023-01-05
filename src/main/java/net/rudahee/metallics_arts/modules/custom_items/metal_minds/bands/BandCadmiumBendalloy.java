package net.rudahee.metallics_arts.modules.custom_items.metal_minds.bands;

import net.minecraft.world.item.Item;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.BandMindAbstract;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.AtiumFecuchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.BendalloyFecuchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.CadmiumFecuchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.MalatiumFecuchemicHelper;

public class BandCadmiumBendalloy extends BandMindAbstract <CadmiumFecuchemicHelper, BendalloyFecuchemicHelper> {
    public BandCadmiumBendalloy (Item.Properties properties){
        super(properties, MetalTagEnum.CADMIUM, MetalTagEnum.BENDALLOY, CadmiumFecuchemicHelper.getInstance(), BendalloyFecuchemicHelper.getInstance());
    }

}