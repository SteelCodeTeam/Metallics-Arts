package net.rudahee.metallics_arts.modules.custom_items.weapons.guns;

import net.rudahee.metallics_arts.data.enums.implementations.GunType;

public class Rifle extends BasicGun {

    /**
     * Constructs a Rifle item with the specified properties.
     *
     * @param properties The properties to configure the Rifle item.
     */
    public Rifle(Properties properties) {
        super(properties, GunType.RIFLE);
    }

}
