package net.rudahee.metallics_arts.data.enums.implementations.languages;

import net.rudahee.metallics_arts.data.enums.interfaces.ILanguage;

//CommonTranslationWords

/**
 * CommonTranslationWords is an enum that implements the ILanguage interface.
 * It contains common translation words for different languages related to items and objects in a game.
 *
 * @author SteelCode Team
 * @since 1.5.1
 *
 * @see ILanguage
 */
public enum CTW implements ILanguage {
    RAW("raw", "Raw", "en bruto","の原石", "Surowy"),
    ORE("ore", "Ore", "Mena de","の鉱石", "Ruda"),
    DEEPSLATE("deepslate", "Deepslate", "de pizarra profunda","深層", "Łupek"),
    BLOCK("block", "Block of", "Bloque de","ブロック", "Blok"),
    INGOT("ingot", "Ingot", "Lingote de","インゴット", "Sztabka"),
    GEM("gem", "Gem", "Gema de","宝石", "Klejnot"),
    NUGGET("nugget", "Nugget", "Pepita de","塊", "Samorodek"),
    BUDDING("budding", "Budding", "Brotador de","芽生えた", "Kiełkujący"),
    CLUSTER("budding", "Cluster", "Cúmulo de","の塊", "Skupisko"),
    BUD("bud", "Budding", "Brote de","の芽", "Zarodek"),
    SMALL("small", "Small", "Pequeño","小さな", "Mały"),
    MEDIUM("medium", "Medium", "Mediano","中くらいの", "Średni"),
    LARGE("large", "Large", "Grande","大きな", "Duży"),
    CRISTAL("cristal", "Crystallized", "Cristalizado","", "Kryształ"),
    BAND("band", "Band", "Brazal de","のバンド", "Opaska"),
    RING("ring", "Ring", "Anillo de","のリング", "Pierścień"),
    SPIKE("spike", "Spike", "Clavo de","のスパイク", "Kolec"),
    ALLOMANTIC("allomantic", "Allomantic", "Alomántico","", "Allomantyczny"),
    ALLOMANCY("allomancy", "Allomancy", "Alomancia","", "Allomancja"),
    FERUCHEMICAL("feruchemical", "Feruchemical", "Feruqumico","", "Feruchemiczny"),
    FERUCHEMY("feruchemy", "Feruchemy", "Feruquimia","", "Feruchemia"),
    POWER_SELECTOR("metal_selector", "Power Selector", "Selector de poder","", "Wybór mocy"),
    STORAGE("storage", "Storage", "Almacenaje","", "Gromadzenie"),
    TAPPING("tapping", "Tapping", "Decante","", "Czerpanie"),
    PUSH("push", "Push", "Empuje","", "Pchaj"),
    PULL("pull", "Pull", "Tiron","", "Ciągnij"),
    VERTICAL("vertical", "Vertical", "Vertical","", "Pionowy"),
    SWITCH_OVERLAY("switch_overlay", "Switch Overlay", "Alrternar interfaz","", "Zmień nakładkę"),
    METALLICS_ARTS("metallics_arts", "Metallics Arts", "Artes Metalicas","金属術", "Sztuki metaliczne"),
    OWNER("ring", "Owner", "Propietario","オーナー", "Właściciel"),
    NOBODY("nobody", "Nobody", "Sin Propietario","誰でもない", "Nikt"),
    HAS_RESERVE("has_reserve", "Has reserve", "Tiene reservas Alomanticas","", "Posiada rezerwę"),
    NOT_HAS_RESERVE("not_has_reserve", "Not has reserve", "No tiene reservas Alomanticas","", "Nie posiada rezerwy"),
    STORE_IDENTITY("store_identity", "Store Identity", "Almacenando Identidad","", "Gromadzenie tożsamośći"),
    POWER_OFF("power_off", "Power Off", "Poder Apagado","", "Wyłącz moc"),
    TAPPING_IDENTITY("tapping_identity", "Tapping Identity", "Decantando Identidad","", "Czerpanie tożsamośći"),
    OWNER_SOMEONE("owner_someone", "Someone", "Alguien","", "Ktoś"),
    STORAGE_POWER("storage_power","Storage Power","Poder Almacenado","", "Gromadź moc"),
    POWERS("powers", "Powers", "Poderes","", "Moce"),
    METALMIND("metal_mind", "Metalmind", "de mente de metal","", "Metal myśl"),
    SLOT("slot", "Slot", "Ranura","", "Slot"),
    VIAL ("vial","Vial","Vial","瓶", "Fiolka"),
    USES ("uses","Uses","Usos","", "Użycia"),
    DECORATIONS ("decorations","Decorations","Decoración","", "Dekoracje"),
    GUIDE ("guide","Guide","Guia","", "Poradnik"),
    FERUCHEMICAL_SHADING("feruchemical_shading","Shading","feruquimico sombreado","", ""),
    FERUCHEMICAL_SOLID ("allomantical_solid","Solid","feruquimico solido","", ""),
    ALLOMANTIC_SHADING ("feruchemical_shading","Shading","alomantico sombreado","", ""),
    ALLOMANTIC_SOLID ("allomantical_solid","Solid","alomantico solido","", ""),
    FERUCHEMICAL_PATTERN ("feruchemical_pattern","Pattern","Patrón feruquimico de","", "Wzór feruchemiczny"),
    ALLOMANTIC_PATTERN ("allomantical_pattern","Pattern","Patrón alomántico de","", "Wzór allomantyczny"),
    KOLOSS_BLADE ("koloss_blade","Koloss Blade","Espada Koloss","コロスの剣", "Ostrze kolosa"),
    CRYSTAL_DAGGER("crystal_dagger","Crystal Dagger","Daga de Cristal","", "Kryształowy sztylet"),
    OBSIDIAN_DAGGER ("obsidian_dagger","Obsidian Dagger","Daga de Obsidiana","", "Obsydianowy sztylet"),
    OBSIDIAN_AXE ("obsidian_axe","Obsidian Axe","Hacha de Obsidiana","", "Obsydianowy topór"),
    DUELING_STAFF ("dueling_staff","Dueling Staff","Baston de Duelos","", "Laska pojedynkowa"),

    WEAPONS("weapon","Weapons", "Armas","", "Bronie"),
    CAFTING("crafting","Crafting","Crafteos","", "Tworzenie"),
    ALLOY_FURNACE("alloy_furnace","Alloy Furnace","Horno de fundicion","", "Piec stopowy"),

    PHYSICAL_ALLOMANCY("physical_allomancy","Physical", "Fisico","物理的", "Fizyczne"),
    TEMPORAL("temporal","Temporal","Temporal","時間的", "Czasowe"),
    ENHANCEMENT("enhancement","Enhancement","Mejora","", "Wzmocnienie"),
    MENTAL("mental","Mental","Mental","",""),
    DIVINE_ALLOMANCY("divine_allomancy","Divine", "Divino","神々しい", "Boskie"),
    TUTORIAL_ALLOMANCY("tutorial_allomacy","Allomacy Tutorial","Tutorial de Alomancia","",""),

    PHYSICAL_FERUCHEMY("physical_feruchemy","Physical", "Fisico","物理的", "Fizyczne"),
    SPIRITUAL("spiritual","Spiritual","Espiritual","霊的", "Duchowe"),
    COGNITIVE("cognitive","Cognitive","Cognitivo","認知", "Kognitywne"),
    HYBRID("hybrid","Hybrid","Hibrido","ハイブリッド", "Hybrydowe"),
    DIVINE_FERUCHEMY("divine_feruchemy","Divine", "Divino","神々しい", "Boskie"),
    TUTORIAL_FERUCHEMY("tutorial_feruchemy","Feruchemic Tutorial","Tutorial de Feruquimia","",""),

    WELCOME("welcome","Welcome", "Bienvenido","", ""),

    INTERACTIONS("interactions","Interactions", "Interacciones","", ""),
    INTRODUCTION("introduction","Introduction", "Introducción","", ""),
    BOOK_TOOLTIP("book_tooltip","Diary of a Survivor","Diario de un Superviviente","",""),



    VIALS("vials","Vials", "Viales","瓶", "Fiolki"),
    RINGS("rings","Rings","Anillos","リング", "Pierścienie"),
    BANDS("bands","Bands","Bandas","", "Opaski"),
    SPIKES("spikes","Spikes","Clavos","スパイク", "Kolce"),
    ICONS("icons","Icons","Iconos","", "Ikony"),
    ALLOYS("alloys","Alloys","Aleaciones","合金", "Stopy"),
    PATTERNS("patterns","Patterns","Patrones","", "Wzory");

    private final String id;
    private final String english;
    private final String spanish;
    private final String japanese;
    private final String polish;

    /**
     * Creates a new CTW object with the given ID and language names.
     *
     * @param id the ID of the CTW object.
     * @param english the name of the word in English.
     * @param spanish the name of the word in Spanish.
     * @param japanese the name of the word in Japanese.
     * @param polish the name of the word in Polish.
     */
    CTW(String id, String english, String spanish, String japanese, String polish) {
        this.id = id;
        this.english = english;
        this.spanish = spanish;
        this.japanese = japanese;
        this.polish = polish;
    }
    /**
     * Returns the ID of the CTW object.
     *
     * @return the ID of the CTW object
     */
    public String getId() {
        return id;
    }
    /**
     * Returns the name of the word in Spanish.
     *
     * @return the name of the word in Spanish
     */
    @Override
    public String getNameInSpanish() {
        return this.spanish;
    }
    /**
     * Returns the name of the word in English.
     *
     * @return the name of the word in English
     */
    @Override
    public String getNameInEnglish() {
        return this.english;
    }
    /**
     * Returns the name of the word in Japanese.
     *
     * @return the name of the word in Japanese
     */
    public String getNameInJapanese() {
        return this.japanese;
    }
    /**
     * Returns the name of the word in Polish.
     *
     * @return the name of the word in Polish
     */
    public String getNameInPolish() {
        return this.polish;
    }
}
