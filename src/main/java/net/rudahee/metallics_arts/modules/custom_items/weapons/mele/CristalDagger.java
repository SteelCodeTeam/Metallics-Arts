package net.rudahee.metallics_arts.modules.custom_items.weapons.mele;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.rudahee.metallics_arts.MetallicsArts;

/**
 * Class to create a custom sword item. This item it's a new iron tier item.
 *
 * @author SteelCode Team
 * @since 1.5.1
 *
 * @see Item
 * @see SwordItem
 * @see Tiers
 */
public class CristalDagger extends SwordItem {
    private static final int ATTACK_DAMAGE = -1;
    private static final float ATTACK_SPEED = 2F;

    /**
     * Default constructor, we define the properties of to set a durability and creative tab.
     *
     * @param properties of the item.
     */
    public CristalDagger(Properties properties) {
        super(Tiers.IRON, ATTACK_DAMAGE, ATTACK_SPEED, properties.durability(50).tab(MetallicsArts.MA_TAB));
    }
}

