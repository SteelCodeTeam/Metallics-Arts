package net.rudahee.metallics_arts.data.enums.implementations.languages;

import net.rudahee.metallics_arts.data.enums.interfaces.ILanguage;

/**
 * This enum represents a list of metal names with their corresponding IDs and translations in different languages.
 *
 * @author SteelCode Team
 * @since 1.5.1
 *
 * @see ILanguage
 */
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


    /**
     * Constructor for MetalNamesEnum.
     *
     * @param id the ID of the metal.
     * @param english the name of the metal in English.
     * @param spanish the name of the metal in Spanish.
     * @param japanese the name of the metal in Japanese.
     * @param polish the name of the metal in Polish.
     */
    MetalNamesEnum(String id, String english, String spanish, String japanese, String polish) {
        this.id = id;
        this.english = english;
        this.spanish = spanish;
        this.japanese = japanese;
        this.polish = polish;
    }

    /**
     * Returns the id of the MetalNamesEnum instance.
     *
     * @return the id of the MetalNamesEnum instance.
     */
    public String getId() {
        return this.id;
    }
    /**
     * Returns the name of the MetalNamesEnum instance in Spanish.
     *
     * @return the name of the MetalNamesEnum instance in Spanish
     */
    @Override
    public String getNameInSpanish() {
        return this.spanish;
    }
    /**
     * Returns the name of the MetalNamesEnum instance in English.
     *
     * @return the name of the MetalNamesEnum instance in English
     */
    @Override
    public String getNameInEnglish() {
        return this.english;
    }
    /**
     * Returns the name of the MetalNamesEnum instance in lower case Spanish.
     *
     * @return the name of the MetalNamesEnum instance in lower case Spanish
     */
    public String getNameInLowerSpanish() {
        return this.spanish.toLowerCase();
    }
    /**
     * Returns the name of the MetalNamesEnum instance in lower case English
     *
     * @return the name of the MetalNamesEnum instance in lower case English
     */
    public String getNameInLowerEnglish() {
        return this.english.toLowerCase();
    }

    /**
     * Returns the name of the MetalNamesEnum instance in Japanese.
     *
     * @return the name of the MetalNamesEnum instance in Japanese
     */
    public String getNameInJapanese() {
        return this.japanese;
    }
    /**
     * Returns the name of the MetalNamesEnum instance in Polish.
     *
     * @return the name of the MetalNamesEnum instance in Polish
     */
    public String getNameInPolish(){
        return this.polish;
    }
}
