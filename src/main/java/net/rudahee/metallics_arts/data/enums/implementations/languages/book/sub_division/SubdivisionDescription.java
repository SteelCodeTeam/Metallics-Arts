package net.rudahee.metallics_arts.data.enums.implementations.languages.book.sub_division;

import net.rudahee.metallics_arts.MetallicsArts;

public enum SubdivisionDescription {
    WEAPONS("Sirve para"),
    CAFTING("Sirve para"),
    ALLOY_FURNACE("Sirver para"),
    PHYSICAL("Sirve para"),
    SPIRITUAL("Sirve para"),
    COGNITIVE("Sirve para"),
    TEMPORAL("Sirve para"),
    DIVINE("Sirve para"),
    HYBRID("Sirve para"),
    ENHANCEMENT("Sirve para");

    private final String english;
    SubdivisionDescription(String english) {
        this.english = english;
    }

    public String getEnglish() {
        return english;
    }
}
