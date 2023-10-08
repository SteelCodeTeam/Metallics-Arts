package net.rudahee.metallics_arts.data.enums.implementations;

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
