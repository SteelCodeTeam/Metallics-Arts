package net.rudahee.metallics_arts.data.enums.implementations.languages.book.weapons;

public enum WeaponDescriptions {
    OBSIDIAN_DAGGER("Durable and fast dagger, with moderate damage. Can produce bleed."),
    CRISTAL_DAGGER("Extremely fast dagger, which deals little damage, is very fragile. You can hit critical."),
    OBSIDIAN_AXE("Heavy weapon, and slow to handle. causes great damage. You can disarm the target."),
    KOLOSS_BLADE("Giant stone sword, of rudimentary elaboration. It's a really heavy weapon, but with the right push it can do extraordinary damage."),
    DUELING_STAFF("Wooden cane with a very hard metallic piece of decoration on the pommel. Seems like it does more damage the more you use it.");

    private final String english;
    WeaponDescriptions(String english) {
        this.english = english;
    }

    public String getEnglish() {
        return english;
    }
}
