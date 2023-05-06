package net.rudahee.metallics_arts.data.enums.implementations.languages.book.multi_craft;

/**
 * This enum contains the description for the crafting book in different languages.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public enum MultiCaftDescriptions {
    VIALS("It looks like the perfect jar to store metal nuggets.",
            "Parece el frasco perfecto para guardar copos de metal en una solución de alcohol.",
            "Idealny zbiornik na odłamki metali."),
    RINGS("Metal rings commonly worn by the Terrispeople. They look good, but they don't seem to be just jewelry.",
            "Anillos de metal que suelen llevar los terrisanos. No parece que los lleven como un simple adorno...",
            "Metalowe obręcze typowo noszone przez Terrisan, bardzo stylowe."),
    BANDS("Metal bracers that some people wear, are they just decoration?",
            "Brazales de metal que llevan algunas personas. No parece que sea solo bisutería...",
            "Metalow opaski, czy są tylko dekoracyjne?."),
    SPIKES("Really sharp metal nails. Why is it that no one use them for their DIY projects?",
            "Clavos de metales realmente afilados. Nadie los parece usar para construir edificios o algo por el estilo...",
            "Bardzo ostre metalowe kolce. Ciekawe do czego mogą zostać użyte?"),
    ICONS("",
            "",
            ""),
    ALLOYS("Alloys of metals that you can find around the world.",
            "Aleaciones de los metales que puedes encontrar por el mundo.",
            "Stopy metali które mogą być znalezione w różnych miejscach na świecie."),
    PATTERNS("",
            "",
            ""),
    CORES("",
            "cosas de cuero",
            ""),
    ALUMINUM_ARMOR("",
            "cosas de cuero",
            ""),
    STEEL_ARMOR("",
            "cosas de cuero",
            "");

    private final String english;
    private final String spanish;
    private final String polish;

    /**
     * Creates a new MultiCraftDescriptions object with the provided English, Spanish, and Polish translations.
     *
     * @param english the English translation for the interaction.
     * @param spanish the Spanish translation for the interaction.
     * @param polish the Polish translation for the interaction.
     */
    MultiCaftDescriptions(String english, String spanish, String polish) {
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
