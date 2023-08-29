package net.rudahee.metallics_arts.data.enums.implementations;


/**
 * An enumeration representing different types of guns in the game.
 * Each gun type has a unique name, reload cooldown, and maximum amount of ammunition.
 *
 * @author SteelCode Team
 * @since 1.6.4
 */
public enum GunType {
    SHOTGUN("shotgun", 10, 2,10),
    RIFLE("rifle", 10, 1,100),

    RIFLE_SPYGLASS("rifle_spyglass", 10, 1,100),
    REVOLVER("revolver", 20, 6, 20),
    VINDICATOR("vindicator", 20, 8, 20);

    private final String name;
    private final int reload_cooldown;
    private  final int maxAmount;
    private  final int despawn;


    /**
     * Creates a new GunType with the specified parameters.
     *
     * @param name The name of the gun type.
     * @param reload_cooldown To reload cooldown in ticks.
     * @param maxAmount The maximum amount of ammunition the gun can hold.
     */
    GunType(String name, int reload_cooldown, int maxAmount, int despawn) {
        this.name = name;
        this.reload_cooldown = reload_cooldown;
        this.maxAmount = maxAmount;
        this.despawn = despawn;
    }

    /**
     * Gets the name of the gun type.
     *
     * @return The name of the gun type.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets to reload cooldown of the gun type.
     *
     * @return To reload cooldown in ticks.
     */
    public int getReload_cooldown() {
        return reload_cooldown;
    }

    /**
     * Gets the maximum amount of ammunition the gun type can hold.
     *
     * @return The maximum ammunition capacity.
     */
    public int getMaxAmount() {
        return maxAmount;
    }

    public int getDespawn() {
        return despawn;
    }
}
