package net.rudahee.metallics_arts.modules.custom_items.metal_minds.bands;


import net.minecraft.world.item.Item;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.BandMindAbstract;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.AbstractFechuchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.cognitive_metals.BrassFecuchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.cognitive_metals.ZincFecuchemicHelper;

/**
 * Class that specifies the zinc and brass band, we pass to the abstract class (which has the behavior) the metals that compose it,
 * in the order: metal and its alloy, along with their corresponding suppliers.
 *
 * @author SteelCode Team
 * @since 1.5.1
 *
 * @see BandMindAbstract
 * @see AbstractFechuchemicHelper
 */
public class BandZincBrass extends BandMindAbstract <ZincFecuchemicHelper, BrassFecuchemicHelper>{
    /**
     * Default constructor, it is important to send the metals by parameter in the correct order, metal and its alloy.
     *
     * @param properties of the item.
     */
    public BandZincBrass (Item.Properties properties){
        super(properties, MetalTagEnum.ZINC, MetalTagEnum.BRASS, ZincFecuchemicHelper.getInstance(), BrassFecuchemicHelper.getInstance());
    }
}