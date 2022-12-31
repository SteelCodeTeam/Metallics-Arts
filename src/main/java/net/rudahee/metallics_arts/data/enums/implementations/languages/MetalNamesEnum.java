package net.rudahee.metallics_arts.data.enums.implementations.languages;


import net.rudahee.metallics_arts.data.enums.interfaces.ILanguage;

public enum MetalNamesEnum implements ILanguage {
    IRON("Iron", "Hierro"),
    STEEL("Steel", "Acero"),
    TIN("Tin", "Estaño"),
    PEWTER("Pewter", "Peltre"),
    ZINC("Zinc", "Zinc"),
    BRASS("Brass", "Laton"),
    COPPER("Copper", "Cobre"),
    BRONZE("Bronze", "Bronce"),
    ALUMINUM("Aluminum", "Aluminio"),
    DURALUMIN("Duralumin", "Duralumin"),
    CHROMIUM("Chromium", "Cromo"),
    NICROSIL("Nicrosil", "Nicrosil"),
    GOLD("Gold", "Oro"),
    ELECTRUM("Electrum", "Electrum"),
    CADMIUM("Cadmium", "Cadmio"),
    BENDALLOY("Bendalloy", "Bendaleo"),
    ATIUM("Atium", "Atium"),
    MALATIUM("Malatium", "Malatium"),
    LERASIUM("Lerasium", "Lerasium"),
    ETTMETAL("Ettmetal", "Ettmetal");

    private final String english;
    private final String spanish;

    MetalNamesEnum(String english, String spanish) {
        this.english = english;
        this.spanish = spanish;
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
