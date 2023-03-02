package net.rudahee.metallics_arts.modules.custom_items.vials.large_vial;

import net.minecraft.world.item.Item;
import net.rudahee.metallics_arts.modules.custom_items.vials.Vial;


/**
 * Class that specify the large vial, we pass to abstract class (That has the behaviour) the quantity of max nuggets.
 *
 * @author SteelCode Team
 * @since 1.5.1
 *
 * @see Item
 * @see Vial
 */
public class LargeVial extends Vial {

    /**
     * Default constructor, its important default the maximum quantity of nuggets of vials,
     *
     * @param properties of the item.
     */
    public LargeVial(Item.Properties properties) {
        super(properties,10);
    }
}