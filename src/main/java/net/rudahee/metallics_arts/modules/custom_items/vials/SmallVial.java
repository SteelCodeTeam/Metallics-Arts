package net.rudahee.metallics_arts.modules.custom_items.vials;


import net.minecraft.world.item.Item;

/**
 * Class that specify the small vial, we pass to abstract class (That has the behaviour) the quantity of max nuggets.
 *
 * @author SteelCode Team
 * @since 1.5.1
 *
 * @see Item
 * @see Vial
 */
public class SmallVial extends Vial {

    /**
     * Default constructor, its important default the maximum quantity of nuggets of vials,
     *
     * @param properties of the item.
     */
    public SmallVial(Properties properties) {
        super(properties,5);
    }

}
