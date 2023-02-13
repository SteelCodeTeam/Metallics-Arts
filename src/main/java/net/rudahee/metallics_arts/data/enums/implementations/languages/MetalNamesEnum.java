package net.rudahee.metallics_arts.data.enums.implementations.languages;


import net.rudahee.metallics_arts.data.enums.interfaces.ILanguage;

public enum MetalNamesEnum implements ILanguage {
    IRON("iron","Iron", "Hierro","鉄"),
    STEEL("steel","Steel", "Acero","鋼"),
    TIN("tin", "Tin", "Estaño","錫"),
    PEWTER("pewter","Pewter", "Peltre","ピューター"),
    ZINC("zinc","Zinc", "Zinc","亜鉛"),
    BRASS("brass","Brass", "Laton","真鍮"),
    COPPER("copper","Copper", "Cobre","銅"),
    BRONZE("bronze","Bronze", "Bronce","ブロンズ"),
    ALUMINUM("aluminum","Aluminum", "Aluminio","アルミニウム"),
    DURALUMIN("duralumin","Duralumin", "Duralumin","ジュラルミン"),
    CHROMIUM("chromium", "Chromium", "Cromo","クロム"),
    NICROSIL("nicrosil","Nicrosil", "Nicrosil","ニクロシル"),
    GOLD("gold", "Gold", "Oro","金"),
    ELECTRUM("electrum", "Electrum", "Electrum","エレクトラム"),
    CADMIUM("cadmium", "Cadmium", "Cadmio","カドミウム"),
    BENDALLOY("bendalloy","Bendalloy", "Bendaleo","ベンダロイ"),
    ATIUM("atium", "Atium", "Atium","アティウム"),
    MALATIUM("malatium", "Malatium", "Malatium","マラアティウム"),
    LERASIUM("lerasium", "Lerasium", "Lerasium",""),
    ETTMETAL("ettmetal", "Ettmetal", "Ettmetal",""),
    SILVER( "silver", "Silver", "Plata",""),
    LEAD("lead", "Lead", "Plomo",""),
    NICKEL("nickel", "Nickel", "Níquel","");
    private final String id;
    private final String english;
    private final String spanish;
    private final String japanese;

    MetalNamesEnum(String id, String english, String spanish, String japanese) {
        this.id = id;
        this.english = english;
        this.spanish = spanish;
        this.japanese = japanese;
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

    public String getNameInLowerSpanish() {
        return this.spanish.toLowerCase();
    }
    public String getNameInLowerEnglish() {
        return this.english.toLowerCase();
    }

    public String getNameInJapanese() {
        return this.japanese;
    }
}
