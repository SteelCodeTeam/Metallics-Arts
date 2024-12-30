package net.rudahee.metallics_arts.data.enums.implementations;


/**
 * An enumeration representing different types of guns in the game.
 * Each gun type has a unique name, reload cooldown, and maximum amount of ammunition.
 *
 * @author SteelCode Team
 * @since 1.6.4
 */
public enum GunType {
    SHOTGUN("shotgun",1F,30, 2,10),
    RIFLE("rifle",1F,20, 1,100),
    RIFLE_SPYGLASS("rifle_spyglass",300F,10, 1,190),
    REVOLVER("revolver", 1F,25, 6, 20),
    VINDICATOR("vindicator", 1F,25, 8, 20),
    COPPER_COIN("copper_coin",1F,5, 0, 0),
    BRONZE_COIN("bronze_coin",1F,20, 0, 0);

    private final String name;
    private final int reload_cooldown;
    private final float damage;
    private  final int maxAmount;
    private  final int despawn;


    /**
     * Creates a new GunType with the specified parameters.
     *
     * @param name The name of the gun type.
     * @param reload_cooldown To reload cooldown in ticks.
     * @param maxAmount The maximum amount of ammunition the gun can hold.
     */
    GunType(String name, float damage, int reload_cooldown, int maxAmount, int despawn) {
        this.name = name;
        this.reload_cooldown = reload_cooldown;
        this.maxAmount = maxAmount;
        this.despawn = despawn;
        this.damage = damage;
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

    public float getDamage() {
        return damage;
    }
}
