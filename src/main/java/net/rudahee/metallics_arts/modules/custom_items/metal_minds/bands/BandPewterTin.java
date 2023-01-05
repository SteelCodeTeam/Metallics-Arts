package net.rudahee.metallics_arts.modules.custom_items.metal_minds.bands;

import net.minecraft.world.item.Item;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.BandMindAbstract;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.AtiumFecuchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.MalatiumFecuchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.PewterFeruchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.TinFeruchemicHelper;

public class BandPewterTin extends BandMindAbstract <TinFeruchemicHelper, PewterFeruchemicHelper> {
    public BandPewterTin(Item.Properties properties){
        super(properties, MetalTagEnum.TIN, MetalTagEnum.PEWTER, TinFeruchemicHelper.getInstance(), PewterFeruchemicHelper.getInstance());
    }

}
