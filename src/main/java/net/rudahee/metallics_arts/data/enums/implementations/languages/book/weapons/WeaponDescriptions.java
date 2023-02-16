package net.rudahee.metallics_arts.data.enums.implementations.languages.book.weapons;

public enum WeaponDescriptions {
    OBSIDIAN_DAGGER("Durable and fast dagger, with moderate damage. Can produce bleed.",
            "Daga más resistente y rápida que su hermana de cristal, y más peligrosa. Conviene ir con cautela ante alguien que porta una de estas.",
            "Wytrzymały i szybki sztylet zadający średnie obrażenia. Może wywołać krwawienie."),
    CRISTAL_DAGGER("Extremely fast dagger, which deals little damage, is very fragile. You can hit critical.",
            "Daga extremadamente ligera y veloz. Desgraciadamente, es muy fragil y no resulta tan peligrosa como algunos podrían pensar.",
            "Ekstremalnie szybki i delikatny sztylet zadający małe obrażenia. Ma szansę na zadanie obrażeń krytycznych."),
    OBSIDIAN_AXE("Heavy weapon, and slow to handle. causes great damage. You can disarm the target.",
            "Arma pesada y lenta de manejar. Pero como te pille... bueno, no hace falta entrar en detalles desagradables.",
            "Ciężka i powolna broń zadająca duże obrażenia. Możesz rozbroić przeciwnika."),
    KOLOSS_BLADE("Giant stone sword, of rudimentary elaboration. It's a really heavy weapon, but with the right push it can do extraordinary damage.",
            "Espada gigante de piedra de elaboración rudimentaria. Es un arma realmente pesada, pero con la fuerza adecuada puede llegar a hacer un daño extraordinario.",
            "Gigantyczny kamienny miecz o prymitywnym wykonaniu. Bardzo ciężka broń, ale przy odpowiedniej sile może zadać wielkie obrażenia."),
    DUELING_STAFF("Wooden cane with a very hard metallic piece of decoration on the pommel. Seems like it does more damage the more you use it.",
            "Bastón de madera con una pieza de metal muy dura en el pomo. Parece que hace más daño cuanto más gastado está.",
            "Drewniana laska z metaliczną ozdobą na rękojeści. Wydaje się że zadaje więcej obrażeń im więcej jest używana.");

    private final String english;
    private final String spanish;
    private final String polish;
    WeaponDescriptions(String english, String spanish, String polish) {
        this.english = english;
        this.spanish = spanish;
        this.polish = polish;
    }
    public String getEnglish() {
        return english;
    }
    public String getSpanish() {
        return spanish;
    }
    public String getPolish() {
        return polish;
    }
}
