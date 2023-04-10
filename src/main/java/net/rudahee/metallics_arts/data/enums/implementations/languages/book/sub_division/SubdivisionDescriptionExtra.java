package net.rudahee.metallics_arts.data.enums.implementations.languages.book.sub_division;

/**
 * This enum contains the description for the book of each metallic art in different languages.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public enum SubdivisionDescriptionExtra {
    ALLOMANCY("",
            ""),
    FERUCHEMY("",
            "A pesar de que se denomina un arte armónica, tiene una ligera desventaja, ya que todo el poder que su usuario podrá utilizar, deberá ser obtenido de la misma persona … ¿O quizá no …?"),
    WELCOME("",
            "Quizá hayas heredado alguna de estas grandes capacidades, quizá no, pero para sobrevivir en este mundo es mi deber mostrarte el camino de las Artes Metálicas, puede que algunas de estas prácticas te lleven por caminos oscuros repletos de muerte, otras pueden ayudar a la reconstrucción del mundo y la salvación de la humanidad, tú decidirás qué camino tomar.\n" +
                    "\n" +
                    "Mi nombre es Lemmeg y este es el Diario del Superviviente.\n");

    private final String english;
    private final String spanish;

    /**
     * Creates a new WelcomeExtraDescription object with the provided English, Spanish, and Polish translations.
     *
     * @param english the English translation for the interaction.
     * @param spanish the Spanish translation for the interaction.
     */
    SubdivisionDescriptionExtra(String english, String spanish) {
        this.english = english;
        this.spanish = spanish;
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
}
