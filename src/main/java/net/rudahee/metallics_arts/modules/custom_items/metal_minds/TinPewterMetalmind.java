package net.rudahee.metallics_arts.modules.custom_items.metal_minds;

import net.minecraft.world.item.Item;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.enums.implementations.MetalmindType;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.abstracts.MetalmindAbstract;

/**
 * Class that specifies the tin and pewter band, we pass to the abstract class (which has the behavior) the metals that compose it,
 * in the order: metal and its alloy, along with their corresponding suppliers.
 *
 * @author SteelCode Team
 * @since 1.5.1
 *
 * @see MindAbstract
 */
public class TinPewterMetalmind extends MetalmindAbstract {

    /**
     * Default constructor, it is important to send the metals by parameter in the correct order, metal and its alloy.
     *
     * @param properties of the item.
     */
    public TinPewterMetalmind(Item.Properties properties, MetalmindType type){
        super(properties, MetalTagEnum.TIN, MetalTagEnum.PEWTER, type);
    }

}
