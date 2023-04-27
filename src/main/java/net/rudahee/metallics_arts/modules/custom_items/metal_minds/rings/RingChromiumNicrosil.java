package net.rudahee.metallics_arts.modules.custom_items.metal_minds.rings;


import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.RingsMindAbstract;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.AbstractFechuchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.spiritual_metals.ChromiumFecuchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.spiritual_metals.NicrosilFecuchemicHelper;

/**
 * Class that specifies the chromium and nicrosil ring, we pass to the abstract class (which has the behavior) the metals that compose it,
 * in the order: metal and its alloy, along with their corresponding suppliers.
 *
 * @author SteelCode Team
 * @since 1.5.1
 *
 * @see RingsMindAbstract
 * @see AbstractFechuchemicHelper
 */
public class RingChromiumNicrosil extends RingsMindAbstract {

    /**
     * Default constructor, it is important to send the metals by parameter in the correct order, metal and its alloy.
     *
     * @param properties of the item.
     */
    public RingChromiumNicrosil(Properties properties){
        super(properties, MetalTagEnum.CHROMIUM, MetalTagEnum.NICROSIL, new ChromiumFecuchemicHelper(), new NicrosilFecuchemicHelper());
    }

}