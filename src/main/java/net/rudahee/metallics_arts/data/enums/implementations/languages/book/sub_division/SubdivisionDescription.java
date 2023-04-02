package net.rudahee.metallics_arts.data.enums.implementations.languages.book.sub_division;

/**
 * This enum contains the description for the book of each subdivision of items and powers in different languages
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public enum SubdivisionDescription {
    WEAPONS("",
            "",
            ""),
    CAFTING("",
            "",
            ""),
    ALLOY_FURNACE("",
            "",
            ""),
    PHYSICAL("",
            "",
            ""),
    SPIRITUAL("",
            "",
            ""),
    COGNITIVE("",
            "",
            ""),
    TEMPORAL("",
            "",
            ""),
    DIVINE("",
            "",
            ""),
    HYBRID("",
            "",
            ""),
    ENHANCEMENT("",
            "",
            ""),
    ALLOMANCY("presento allomancia","",
            ""),
    FERUCHEMY("presento feruqimia","",
            ""),
    WELCOME("Soy, por desgracia, el Héroe de las Eras, y tengo una pregunta que hacerte, ¿acaso un hombre no tiene derecho a poseer sus propios metales? No, dice el hombre del Imperio. Pertenece al \"dios\". No, dice el superviviente. Pertenece a todos. No, dice el hombre de Elendel. Pertenece a los pobres. Yo rechacé esas propuestas. En vez de eso elegí algo distinto. Elegi mi propio camino, elegí... Artes Metalicas Mod.",
            "Soy, por desgracia, el Héroe de las Eras, y tengo una pregunta que hacerte, ¿acaso un hombre no tiene derecho a poseer sus propios metales? No, dice el hombre del Imperio. Pertenece al \"dios\". No, dice el superviviente. Pertenece a todos. No, dice el hombre de Elendel. Pertenece a los pobres. Yo rechacé esas propuestas. En vez de eso elegí algo distinto. Elegi mi propio camino, elegí... Artes Metalicas Mod.",
            "");

    private final String english;
    private final String spanish;
    private  final String polish;
    /**
     * Creates a new SubdivisionDescription object with the provided English, Spanish, and Polish translations.
     *
     * @param english the English translation for the interaction.
     * @param spanish the Spanish translation for the interaction.
     * @param polish the Polish translation for the interaction.
     */
    SubdivisionDescription(String english, String spanish,String polish ) {
        this.english = english;
        this.spanish = spanish;
        this.polish = polish;
    }
    /**
     * Returns the English translation for the interaction.
     *
     * @return the English translation for the interaction
     */
    public String getEnglish() {
        return english;
    }

    /**
     * Returns the Spanish translation for the interaction.
     *
     * @return the Spanish translation for the interaction
     */
    public String getSpanish() {
        return spanish;
    }
    /**
     * Returns the Polish translation for the interaction.
     *
     * @return the Polish translation for the interaction
     */

    public String getPolish() {
        return polish;
    }
}
