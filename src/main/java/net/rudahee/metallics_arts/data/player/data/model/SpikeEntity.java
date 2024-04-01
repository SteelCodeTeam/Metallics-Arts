package net.rudahee.metallics_arts.data.player.data.model;

import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.data.model.enums.TypeOfSpikeEnum;

import java.util.Objects;

/**
 * The SpikeEntity class represents a spike entity, which can have a metal tag and a type.
 */
public class SpikeEntity {

    public MetalTagEnum metal;

    public TypeOfSpikeEnum type;

    /**
     * Initializes a new instance of the SpikeEntity class with the specified metal tag and spike type.
     *
     * @param metal The metal tag of the spike entity. Must not be null.
     * @param type The type of the spike entity. Must not be null.
     *
     * @throws NullPointerException if either metal or type is null.
     */
    public SpikeEntity(MetalTagEnum metal, TypeOfSpikeEnum type) {
        this.metal = metal;
        this.type = type;
    }

    /**
     * Retrieves the metal tag of the spike entity.
     *
     * @return The metal tag of the spike entity.
     */
    public MetalTagEnum getMetal() {
        return metal;
    }

    /**
     * Sets the metal tag for the spike entity.
     *
     * @param metal The metal tag to be set. Must not be null.
     *
     * @throws NullPointerException if metal is null.
     */
    public void setMetal(MetalTagEnum metal) {
        this.metal = metal;
    }

    /**
     * Retrieves the type of the spike entity.
     *
     * @return The type of the spike entity.
     */
    public TypeOfSpikeEnum getType() {
        return type;
    }

    /**
     * Sets the type of the spike entity.
     *
     * @param type The type of the spike entity. Must not be null.
     *
     * @throws NullPointerException if type is null.
     */
    public void setType(TypeOfSpikeEnum type) {
        this.type = type;
    }

    /**
     * Returns a string representation of the SpikeEntity object.
     *
     * @return A string representation of the SpikeEntity object.
     */
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SpikeEntity{");
        sb.append("metal=").append(metal);
        sb.append(", type=").append(type);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SpikeEntity that)) return false;
        return getMetal() == that.getMetal() && getType() == that.getType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMetal(), getType());
    }
}
