package net.rudahee.metallics_arts.modules.custom_items.metal_minds.bands;

import net.minecraft.world.item.Item;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.BandMindAbstract;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.IronFecuchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.SteelFecuchemicHelper;

public class BandSteelIron extends BandMindAbstract <IronFecuchemicHelper, SteelFecuchemicHelper> {
    public BandSteelIron(Item.Properties properties){
        super(properties, MetalTagEnum.IRON, MetalTagEnum.STEEL, IronFecuchemicHelper.getInstance(), SteelFecuchemicHelper.getInstance());
    }

}