package net.rudahee.metallics_arts.data.enums.implementations.languages.book.multi_craft;

public enum MultiCaftDescriptions {
    VIALS("It looks like the perfect jar to store metal shards.",
            "Parece el frasco perfecto para guardar copos de metal en una solución de alcohol."),
    RINGS("Metal rings commonly worn by Terrisians. they look really good.",
            "Anillos de metal que suelen llevar los terrisanos. No parece que los lleven como un simple adorno..."),
    BANDS("Metal bracers that some people wear, are they just decoration?",
            "Brazales de metal que llevan algunas personas. No parece que sea solo bisutería..."),
    SPIKES("Really sharp metal nails. What will be its use?",
            "Clavos de metales realmente afilados. Nadie los parece usar para construir edificios o algo por el estilo..."),
    ICONS("",
            ""),
    ALLOYS("Alloys of metals that you can find around the world.",
            "Aleaciones de los metales que puedes encontrar por el mundo."),
    PATTERNS("",
            "");

    private final String english;
    private final String spanish;
    MultiCaftDescriptions(String english, String spanish) {
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
