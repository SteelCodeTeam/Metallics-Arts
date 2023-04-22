package net.rudahee.metallics_arts.data.enums.implementations;

import net.rudahee.metallics_arts.data.enums.interfaces.IGems;
import net.rudahee.metallics_arts.data.enums.interfaces.IMetal;

/**
 * An enumeration representing various metal properties related to Allomancy.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */






public enum MetalTagEnum implements IMetal, IGems {
    /* 1s = 20 ticks, 1600 = 80s */
    IRON("iron", "IRON",0, 6000, true, false, 2,9600,3000),
    STEEL("steel", "STEEL",0, 6000, true, false, 3,9600,3000),
    TIN("tin", "TIN",1, 24000, false, false, 2,24000,7200),
    PEWTER("pewter", "PEWTER",1, 4000, false, false, 3,6000,1200),
    ZINC("zinc", "ZINC", 3, 6000, true, false, 1,6000,2400),
    BRASS("brass", "BRASS",3, 6000, true, false, 0,6000,2800),
    COPPER("copper", "COPPER",2, 8400, false, false, 1,5345,315),
    BRONZE("bronze", "BRONZE",2, 8400, false, false, 0,7200,2200),
    ALUMINUM("aluminum", "ALUMINUM",5, 10, false, false, 5,4,4),
    DURALUMIN("duralumin", "DURALUMIN",5, 100, false, false, 4,12000,4000),
    CHROMIUM("chromium", "CHROMIUM",4, 400, true, false, 5,6000,2400),
    NICROSIL("nicrosil", "NICROSIL",4, 400, true, false, 4,9600,3000),
    GOLD("gold", "GOLD",7, 12000, false, false, 6,6000,1000),
    ELECTRUM("electrum", "ELECTRUM",7, 12000, false, false, 7,4800,1200),
    CADMIUM("cadmium", "CADMIUM",6, 2400, true, false, 6,12000,4000),
    BENDALLOY("bendalloy", "BENDALLOY", 6, 1800, true, false, 7,9600,3000),
    ATIUM("atium", "ATIUM", 8,800, false, true, 0,2400,600),
    MALATIUM("malatium", "MALATIUM",8, 12000, false, true, 1,3000,500),
    LERASIUM("lerasium", "LERASIUM",9, 6000, false, true, 2,1,1),
    ETTMETAL("ettmetal", "ETTMETAL",9, 100, false, true, 3,4100,1640);

    private final String nameLower;
    private final String nameUpper;
    private final int group;
    private final int maxAllomanticTicksStorage;
    private final boolean external;
    private final boolean divine;
    private final int order;
    private final int maxReserveBand;
    private final int maxReserveRing;

    /**
     * Constructs a MetalTagEnum instance with the specified properties.
     *
     * @param nameLower The metal name in lowercase
     * @param nameUpper The metal name in uppercase
     * @param group The group this metal belongs to
     * @param maxAllomanticTicksStorage The maximum allomantic ticks storage
     * @param external Whether the metal is external
     * @param divine Whether the metal is divine
     * @param order The order of the metal
     * @param maxReserveBand The maximum reserve band
     * @param maxReserveRing The maximum reserve ring
     */
    MetalTagEnum(String nameLower, String nameUpper, int group, int maxAllomanticTicksStorage, boolean external, boolean divine, int order, int maxReserveBand, int maxReserveRing) {
        this.nameLower = nameLower;
        this.nameUpper = nameUpper;
        this.group = group;
        this.maxAllomanticTicksStorage = maxAllomanticTicksStorage;
        this.external = external;
        this.divine = divine;
        this.order = order;
        this.maxReserveBand = maxReserveBand;
        this.maxReserveRing = maxReserveRing;
    }

    /**
     * Returns the metal name in lowercase.
     *
     * @return the metal name in lowercase
     */
    public String getNameLower() {
        return nameLower;
    }

    /**
     * Returns the metal name in uppercase.
     *
     * @return the metal name in uppercase
     */
    public String getNameUpper() {
        return nameUpper;
    }

    /**
     * Returns the group this metal belongs to.
     *
     * @return the group
     */
    public int getGroup() {
        return group;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getGemNameLower() {
        return nameLower;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getGemNameUpper() {
        return nameUpper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMetalNameLower() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMetalNameUpper() {
        return null;
    }

    /**
     * Returns the maximum allomantic ticks storage.
     *
     * @return the maximum allomantic ticks storage
     */
    public int getMaxAllomanticTicksStorage() {
        return maxAllomanticTicksStorage;
    }

    /**
     * Returns the index of the metal in the enumeration.
     *
     * @return the index of the metal
     */
    public int getIndex() {
        return ordinal();
    }

    /**
     * Gets the metal associated with the given index.
     *
     * @param index the index of the metal
     * @return the MetalTagEnum object corresponding to the index
     * @throws IllegalArgumentException if the index is invalid
     */
    public static MetalTagEnum getMetal(int index) {
        for (MetalTagEnum metal : values()) {
            if (metal.getIndex() == index) {
                return metal;
            }
        }
        throw new IllegalArgumentException("Allomancy: Bad Metal Index");
    }

    /**
     * Checks if the metal is divine.
     *
     * @return true if the metal is divine, false otherwise
     */
    public boolean isDivine() {
        return divine;
    }

    /**
     * Checks if the metal is external.
     *
     * @return true if the metal is external, false otherwise
     */
    public boolean isExternal() {
        return external;
    }

    /**
     * Returns the order of the metal.
     *
     * @return the order of the metal
     */
    public int getOrder() {
        return order;
    }

    /**
     * Returns the maximum reserve band for the metal.
     *
     * @return the maximum reserve band
     */
    public int getMaxReserveBand() {
        return maxReserveBand;
    }

    /**
     * Returns the maximum reserve ring for the metal.
     *
     * @return the maximum reserve ring
     */
    public int getMaxReserveRing() {
        return maxReserveRing;
    }
}
