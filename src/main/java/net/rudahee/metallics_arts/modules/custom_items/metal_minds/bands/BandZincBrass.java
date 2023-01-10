package net.rudahee.metallics_arts.modules.custom_items.metal_minds.bands;


import net.minecraft.world.item.Item;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.BandMindAbstract;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.BrassFecuchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.ZincFecuchemicHelper;


public class BandZincBrass extends BandMindAbstract <ZincFecuchemicHelper, BrassFecuchemicHelper>{
    public BandZincBrass (Item.Properties properties){
        super(properties, MetalTagEnum.ZINC, MetalTagEnum.BRASS, ZincFecuchemicHelper.getInstance(), BrassFecuchemicHelper.getInstance());
    }
   }