package net.rudahee.metallics_arts.data.enums.implementations.languages.book.sub_division;

import net.rudahee.metallics_arts.MetallicsArts;

public enum SubdivisionDescription {
    WEAPONS("",
            ""),
    CAFTING("",
            ""),
    ALLOY_FURNACE("",
            ""),
    PHYSICAL("",
            ""),
    SPIRITUAL("",
            ""),
    COGNITIVE("",
            ""),
    TEMPORAL("",
            ""),
    DIVINE("",
            ""),
    HYBRID("",
            ""),
    ENHANCEMENT("",
            "");

    private final String english;
    private final String spanish;
    SubdivisionDescription(String english, String spanish) {
        this.english = english;
        this.spanish = spanish;
    }

    public String getEnglish() {
        return english;
    }
}
