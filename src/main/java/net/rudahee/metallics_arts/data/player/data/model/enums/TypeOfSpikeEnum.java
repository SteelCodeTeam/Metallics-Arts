package net.rudahee.metallics_arts.data.player.data.model.enums;

public enum TypeOfSpikeEnum {
    ALLOMANTIC("allomantic"), FERUCHEMIC("feruchemic");

    String type;
    TypeOfSpikeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
