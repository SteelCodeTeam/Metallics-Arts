package net.rudahee.metallics_arts.data.enums.implementations.languages.book.weapons;

public enum WeaponDescriptions {
    OBSIDIAN_DAGGER("Durable and fast dagger, with moderate damage. Can produce bleed.",
            "Daga más resistente y rápida que su hermana de cristal, y más peligrosa. Conviene ir con cautela ante alguien que porta una de estas."),
    CRISTAL_DAGGER("Extremely fast dagger, which deals little damage, is very fragile. You can hit critical.",
            "Daga extremadamente ligera y veloz. Desgraciadamente, es muy fragil y no resulta tan peligrosa como algunos podrían pensar."),
    OBSIDIAN_AXE("Heavy weapon, and slow to handle. causes great damage. You can disarm the target.",
            "Arma pesada y lenta de manejar. Pero como te pille... bueno, no hace falta entrar en detalles desagradables."),
    KOLOSS_BLADE("Giant stone sword, of rudimentary elaboration. It's a really heavy weapon, but with the right push it can do extraordinary damage.",
            "Espada gigante de piedra de elaboración rudimentaria. Es un arma realmente pesada, pero con la fuerza adecuada puede llegar a hacer un daño extraordinario."),
    DUELING_STAFF("Wooden cane with a very hard metallic piece of decoration on the pommel. Seems like it does more damage the more you use it.",
            "Bastón de madera con una pieza de metal muy dura en el pomo. Parece que hace más daño cuanto más gastado está.");

    private final String english;
    private final String spanish;
    WeaponDescriptions(String english, String spanish) {
        this.english = english;
        this.spanish = spanish;
    }
    public String getEnglish() {
        return english;
    }
    public String getSpanish() {
        return spanish;
    }
}
