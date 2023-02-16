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
            ""),
    ALLOMANCY("presento allomancia",""),
    FERUCHEMY("presento feruqimia",""),
    WELCOME("Soy, por desgracia, el Héroe de las Eras, y tengo una pregunta que hacerte, ¿acaso un hombre no tiene derecho a poseer sus propios metales? No, dice el hombre del Imperio. Pertenece al \"dios\". No, dice el superviviente. Pertenece a todos. No, dice el hombre de Elendel. Pertenece a los pobres. Yo rechacé esas propuestas. En vez de eso elegí algo distinto. Elegi mi propio camino, elegí... Artes Metalicas Mod.",
            "Soy, por desgracia, el Héroe de las Eras, y tengo una pregunta que hacerte, ¿acaso un hombre no tiene derecho a poseer sus propios metales? No, dice el hombre del Imperio. Pertenece al \"dios\". No, dice el superviviente. Pertenece a todos. No, dice el hombre de Elendel. Pertenece a los pobres. Yo rechacé esas propuestas. En vez de eso elegí algo distinto. Elegi mi propio camino, elegí... Artes Metalicas Mod.");

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
