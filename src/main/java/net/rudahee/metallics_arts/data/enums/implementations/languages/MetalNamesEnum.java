package net.rudahee.metallics_arts.data.enums.implementations.languages;


import net.rudahee.metallics_arts.data.enums.interfaces.ILanguage;

public enum MetalNamesEnum implements ILanguage {
    IRON("iron","Iron", "Hierro","鉄", "Żelazo"),
    STEEL("steel","Steel", "Acero","鋼", "Stal"),
    TIN("tin", "Tin", "Estaño","錫", "Cyna"),
    PEWTER("pewter","Pewter", "Peltre","ピューター", "Cyna z ołowiem"),
    ZINC("zinc","Zinc", "Zinc","亜鉛", "Cynk"),
    BRASS("brass","Brass", "Laton","真鍮", "Mosiądz"),
    COPPER("copper","Copper", "Cobre","銅", "Miedź"),
    BRONZE("bronze","Bronze", "Bronce","ブロンズ", "Brąz"),
    ALUMINUM("aluminum","Aluminum", "Aluminio","アルミニウム", "Aluminium"),
    DURALUMIN("duralumin","Duralumin", "Duralumin","ジュラルミン", "Duraluminium"),
    CHROMIUM("chromium", "Chromium", "Cromo","クロム", "Chrom"),
    NICROSIL("nicrosil","Nicrosil", "Nicrosil","ニクロシル", "Nicrosil"),
    GOLD("gold", "Gold", "Oro","金", "Złoto"),
    ELECTRUM("electrum", "Electrum", "Electrum","エレクトラム", "Elektrum"),
    CADMIUM("cadmium", "Cadmium", "Cadmio","カドミウム", "Kadm"),
    BENDALLOY("bendalloy","Bendalloy", "Bendaleo","ベンダロイ", "Stop bizmutowy"),
    ATIUM("atium", "Atium", "Atium","アティウム", "Atium"),
    MALATIUM("malatium", "Malatium", "Malatium","マラアティウム", "Malatium"),
    LERASIUM("lerasium", "Lerasium", "Lerasium","レラシウム", "Lerasium"),
    ETTMETAL("ettmetal", "Ettmetal", "Ettmetal","エットメッタル", "Ettmetal"),
    SILVER( "silver", "Silver", "Plata","銀", "Srebro"),
    LEAD("lead", "Lead", "Plomo","鉛", "Ołów"),
    NICKEL("nickel", "Nickel", "Níquel","ニッケル", "Nikiel");
    private final String id;
    private final String english;
    private final String spanish;
    private final String japanese;
    private final String polish;

    MetalNamesEnum(String id, String english, String spanish, String japanese, String polish) {
        this.id = id;
        this.english = english;
        this.spanish = spanish;
        this.japanese = japanese;
        this.polish = polish;
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
    public String getNameInPolish(){
        return this.polish;
    }
}
