package net.rudahee.metallics_arts.data.enums.implementations.languages;

import net.rudahee.metallics_arts.data.enums.interfaces.ILanguage;

public enum GemNames implements ILanguage {
    ATIUM("atium", "Atium", "Atium"),
    MALATIUM("malatium", "Malatium", "Malatium"),
    LERASIUM("lerasium", "Lerasium", "Lerasium"),
    ETTMETAL("ettmetal", "Ettmetal", "Ettmetal");

    private final String id;
    private final String english;
    private final String spanish;

    GemNames(String id, String english, String spanish) {
        this.id = id;
        this.english = english;
        this.spanish = spanish;
    }

    public String getId() {
        return this.id;
    }

    @Override
    public String getNameInSpanish() {
        return this.spanish;
    }

    @Override
    public String getNameInEnglish() {
        return this.english;
    }
}
