package net.rudahee.metallics_arts.data.enums.implementations.languages;

import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.interfaces.ILanguage;

//CommonTranslationWords
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
    CRISTAL_DAGGER ("cristal_dagger","Cristal Dagger","Daga de Cristal","", "Kryształowy sztylet"),
    OBSIDIAN_DAGGER ("obsidian_dagger","Obsidian Dagger","Daga de Obsidiana","", "Obsydianowy sztylet"),
    OBSIDIAN_AXE ("obsidian_axe","Obsidian Axe","Hacha de Obsidiana","", "Obsydianowy topór"),
    DUELING_STAFF ("dueling_staff","Dueling Staff","Baston de Duelos","", "Laska pojedynkowa"),

    WEAPONS("weapon","Weapons", "Armas","", "Bronie"),
    CAFTING("crafting","Crafting","Crafteos","", "Tworzenie"),
    ALLOY_FURNACE("alloy_furnace","Alloy Furnace","Horno de fundicion","", "Piec stopowy"),

    PHYSICAL("physical","Physical", "Fisico","物理的", "Fizyczne"),
    SPIRITUAL("spiritual","Spiritual","Espiritual","霊的", "Duchowe"),
    COGNITIVE("cognitive","Cognitive","Cognitivo","認知", "Kognitywne"),
    TEMPORAL("temporal","Temporal","Temporal","時間的", "Czasowe"),
    DIVINE("divine","Divine", "Divino","神々しい", "Boskie"),
    HYBRID("hybrid","Hybrid","Hibrido","ハイブリッド", "Hybrydowe"),
    ENHANCEMENT("enhancement","Enhancement","Mejora","", "Wzmocnienie"),

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

    CTW(String id, String english, String spanish, String japanese, String polish) {
        this.id = id;
        this.english = english;
        this.spanish = spanish;
        this.japanese = japanese;
        this.polish = polish;
    }

    public String getId() {
        return id;
    }

    @Override
    public String getNameInSpanish() {
        return this.spanish;
    }

    @Override
    public String getNameInEnglish() {
        return this.english;
    }

    public String getNameInJapanese() {
        return this.japanese;
    }
    public String getNameInPolish() {
        return this.polish;
    }
}
