package net.rudahee.metallics_arts.modules.custom_items.metal_minds.bands;


import net.minecraft.world.item.Item;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.BandMindAbstract;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.spiritual_metals.ChromiumFecuchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.spiritual_metals.NicrosilFecuchemicHelper;

public class BandChromiumNicrosil extends BandMindAbstract <ChromiumFecuchemicHelper, NicrosilFecuchemicHelper> {

    public BandChromiumNicrosil(Item.Properties properties){
        super(properties, MetalTagEnum.CHROMIUM, MetalTagEnum.NICROSIL, ChromiumFecuchemicHelper.getInstance(), NicrosilFecuchemicHelper.getInstance());
    }

}