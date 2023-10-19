package net.rudahee.metallics_arts.modules.custom_items.weapons.guns;

import net.rudahee.metallics_arts.data.enums.implementations.GunType;

public class ShotGun extends BasicGun {
    /**
     * Constructs a ShotGun item with the specified properties.
     *
     * @param properties The properties to configure the ShotGun item.
     */
    public ShotGun(Properties properties) {
        super(properties, GunType.SHOTGUN);
    }
}
