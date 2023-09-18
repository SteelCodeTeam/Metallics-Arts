package net.rudahee.metallics_arts.data.player.data.model;

import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.enums.implementations.TypeOfSpikeEnum;

public class SpikeEntity {

    public MetalTagEnum metal;

    public TypeOfSpikeEnum type;

    public SpikeEntity(MetalTagEnum metal, TypeOfSpikeEnum type) {
        this.metal = metal;
        this.type = type;
    }

    public MetalTagEnum getMetal() {
        return metal;
    }

    public void setMetal(MetalTagEnum metal) {
        this.metal = metal;
    }

    public TypeOfSpikeEnum getType() {
        return type;
    }

    public void setType(TypeOfSpikeEnum type) {
        this.type = type;
    }
}
