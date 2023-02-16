package net.rudahee.metallics_arts.data.enums.implementations.languages.book.multi_craft;

public enum MultiCaftDescriptions {
    VIALS("It looks like the perfect jar to store metal shards.",
            "Parece el frasco perfecto para guardar copos de metal en una solución de alcohol.",
            "Idealny zbiornik na odłamki metali."),
    RINGS("Metal rings commonly worn by Terrisians. they look really good.",
            "Anillos de metal que suelen llevar los terrisanos. No parece que los lleven como un simple adorno...",
            "Metalowe obręcze typowo noszone przez Terrisan, bardzo stylowe."),
    BANDS("Metal bracers that some people wear, are they just decoration?",
            "Brazales de metal que llevan algunas personas. No parece que sea solo bisutería...",
            "Metalow opaski, czy są tylko dekoracyjne?."),
    SPIKES("Really sharp metal nails. What will be its use?",
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
            "");

    private final String english;
    private final String spanish;
    private final String polish;
    MultiCaftDescriptions(String english, String spanish, String polish) {
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
