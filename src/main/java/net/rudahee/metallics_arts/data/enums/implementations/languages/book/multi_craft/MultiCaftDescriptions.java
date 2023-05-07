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
    ICONS("Decorative icons of allomantic and feruchemical metals.",
            "Iconos decorativos de los metales alomanticos y feruquimicos.",
            ""),
    ALLOYS("Alloys of metals that you can find around the world.",
            "Aleaciones de los metales que puedes encontrar por el mundo.",
            "Stopy metali które mogą być znalezione w różnych miejscach na świecie."),
    PATTERNS("Design patterns for banner with the symbols of metals, allomantic and feruchemical.",
            "Patrones de diseño para estandarte con los símbolos de los metales, alomanticos y feruquimicos.",
            ""),
    CORES("Enhancement Cores that allow you to turn mundane items and armor into something superior.",
            "Núcleos de mejora que permiten convertir en una objetos y armaduras mundanas en algo superior.",
            ""),
    ALUMINUM_ARMOR("Armor of great resistance, which protects the wearer from the magic performed by enemies.",
            "Armadura de gran resistencia, que protege al portador de las magias realizadas por enemigos.",
            ""),
    STEEL_ARMOR("Armor of high resistance, that avoids the simple and mundane pushes not equipped with magic.",
            "Armadura de alta resistencia, que evita los simples y mundanos empujones no provistos de magia.",
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
