package net.rudahee.metallics_arts.data.enums.implementations.languages.book.weapons;

/**
 * This enum contains the description for the book of each weapon in different languages
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public enum WeaponDescriptions {
    OBSIDIAN_DAGGER("Durable and fast dagger, with moderate damage. Can produce bleed.",
            "Daga más resistente y rápida que su hermana de cristal, y más peligrosa. Conviene ir con cautela ante alguien que porta una de estas.",
            "Wytrzymały i szybki sztylet zadający średnie obrażenia. Może wywołać krwawienie."),
    SILVER_KNIFE("Extremely fast dagger, which deals low amount of damage. It's very fragile and it is possible to perform a critical hit, though this weapon isn't as dangerous as some people might think",
            "Daga extremadamente ligera y veloz. Desgraciadamente, es muy fragil y no resulta tan peligrosa como algunos podrían pensar.",
            "Ekstremalnie szybki i delikatny sztylet zadający małe obrażenia. Ma szansę na zadanie obrażeń krytycznych."),
    OBSIDIAN_AXE("Heavy weapon, and slow to handle. Causes great damage. It can disarm the target.",
            "Arma pesada y lenta de manejar. Pero como te pille... bueno, no hace falta entrar en detalles desagradables.",
            "Ciężka i powolna broń zadająca duże obrażenia. Możesz rozbroić przeciwnika."),
    KOLOSS_BLADE("Giant stone and steel sword, of rudimentary construction. It's a really heavy weapon, but with the right strength it can do extraordinary damage.",
            "Espada gigante de piedra y acero de elaboración rudimentaria. Es un arma realmente pesada, pero con la fuerza adecuada puede llegar a hacer un daño extraordinario.",
            "Gigantyczny kamienno-stalowy miecz o prymitywnym wykonaniu. Bardzo ciężka broń, ale przy odpowiedniej sile może zadać wielkie obrażenia."),
    DUELING_STAFF("Wooden cane with a very hard metallic piece as decoration on the tip. Seems like it does more damage the more you use it.",
            "Bastón de madera con una pieza de metal muy dura en el pomo. Parece que hace más daño cuanto más gastado está.",
            "Drewniana laska z metaliczną ozdobą na rękojeści. Wydaje się że zadaje więcej obrażeń im więcej jest używana.");

    private final String english;
    private final String spanish;
    private final String polish;
    /**
     * Creates a new WeaponDescriptions object with the provided English, Spanish, and Polish translations.
     *
     * @param english the English translation for the interaction.
     * @param spanish the Spanish translation for the interaction.
     * @param polish the Polish translation for the interaction.
     */
    WeaponDescriptions(String english, String spanish, String polish) {
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
