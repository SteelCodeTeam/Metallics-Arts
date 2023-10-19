package net.rudahee.metallics_arts.modules.custom_items.weapons.guns;

import net.minecraft.world.item.Item;
import net.rudahee.metallics_arts.data.enums.implementations.GunType;

public class RifleWithSpyGlass extends BasicGun {
    /**
     * Constructs a RifleWithSpyGlass item with the specified properties.
     *
     * @param properties The properties to configure the RifleWithSpyGlass item.
     */
    public RifleWithSpyGlass(Item.Properties properties) {
        super(properties, GunType.RIFLE_SPYGLASS);
    }
}
