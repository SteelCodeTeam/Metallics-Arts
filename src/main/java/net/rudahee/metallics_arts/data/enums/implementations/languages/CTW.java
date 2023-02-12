package net.rudahee.metallics_arts.data.enums.implementations.languages;

import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.interfaces.ILanguage;

//CommonTranslationWords
public enum CTW implements ILanguage {
    RAW("raw", "Raw", "en bruto",""),
    ORE("ore", "Ore", "Mena de",""),
    DEEPSLATE("deepslate", "Deepslate", "de pizarra profunda",""),
    BLOCK("block", "Block of", "Bloque de",""),
    INGOT("ingot", "Ingot", "Lingote de",""),
    GEM("gem", "Gem", "Gema de",""),
    NUGGET("nugget", "Nugget", "Pepita de",""),
    BUDDING("budding", "Budding", "Brotador de",""),
    CLUSTER("budding", "Cluster", "Cúmulo de",""),
    BUD("bud", "Budding", "Brote de",""),
    SMALL("small", "Small", "Pequeño",""),
    MEDIUM("medium", "Medium", "Mediano",""),
    LARGE("large", "Large", "Grande",""),
    CRISTAL("cristal", "Crystallized", "Cristalizado",""),
    BAND("band", "Band", "Brazal de",""),
    RING("ring", "Ring", "Anillo de",""),
    SPIKE("spike", "Spike", "Clavo de","スパイク"),
    ALLOMANTIC("allomantic", "Allomantic", "Alomántico",""),
    ALLOMANCY("allomancy", "Allomancy", "Alomancia",""),
    FERUCHEMICAL("feruchemical", "Feruchemical", "Feruqumico",""),
    FERUCHEMY("feruchemy", "Feruchemy", "Feruquimia",""),
    POWER_SELECTOR("metal_selector", "Power Selector", "Selector de poder",""),
    STORAGE("storage", "Storage", "Almacenaje",""),
    TAPPING("tapping", "Tapping", "Decante",""),
    PUSH("push", "Push", "Empuje",""),
    PULL("pull", "Pull", "Tiron",""),
    VERTICAL("vertical", "Vertical", "Vertical",""),
    SWITCH_OVERLAY("switch_overlay", "Switch Overlay", "Alrternar interfaz",""),
    METALLICS_ARTS("metallics_arts", "Metallics Arts", "Artes Metalicas",""),
    OWNER("ring", "Owner", "Propietario",""),
    NOBODY("nobody", "Nobody", "Sin Propietario",""),
    HAS_RESERVE("has_reserve", "Has reserve", "Tiene reservas Alomanticas",""),
    NOT_HAS_RESERVE("not_has_reserve", "Not has reserve", "No tiene reservas Alomanticas",""),
    STORE_IDENTITY("store_identity", "Store Identity", "Almacenando Identidad",""),
    POWER_OFF("power_off", "Power Off", "Poder Apagado",""),
    TAPPING_IDENTITY("tapping_identity", "Tapping Identity", "Decantando Identidad",""),
    OWNER_SOMEONE("owner_someone", "Someone", "Alguien",""),
    STORAGE_POWER("storage_power","Storage Power","Poder Almacenado",""),
    POWERS("powers", "Powers", "Poderes",""),
    METALMIND("metal_mind", "Metalmind", "de mente de metal",""),
    SLOT("slot", "Slot", "Ranura",""),
    VIAL ("vial","Vial","Vial",""),
    USES ("uses","Uses","Usos",""),
    DECORATIONS ("decorations","Decorations","Decoración",""),
    GUIDE ("guide","Guide","Guia",""),
    FERUCHEMICAL_SHADING("feruchemical_shading","Shading","feruquimico sombreado",""),
    FERUCHEMICAL_SOLID ("allomantical_solid","Solid","feruquimico solido",""),
    ALLOMANTIC_SHADING ("feruchemical_shading","Shading","alomantico sombreado",""),
    ALLOMANTIC_SOLID ("allomantical_solid","Solid","alomantico solido",""),
    FERUCHEMICAL_PATTERN ("feruchemical_pattern","Pattern","Patrón feruquimico de",""),
    ALLOMANTIC_PATTERN ("allomantical_pattern","Pattern","Patrón alomántico de",""),
    KOLOSS_BLADE ("koloss_blade","Koloss Blade","Espada Koloss",""),
    CRISTAL_DAGGER ("cristal_dagger","Cristal Dagger","Daga de Cristal",""),
    OBSIDIAN_DAGGER ("obsidian_dagger","Obsidian Dagger","Daga de Obsidiana",""),
    OBSIDIAN_AXE ("obsidian_axe","Obsidian Axe","Hacha de Obsidiana",""),
    DUELING_STAFF ("dueling_staff","Dueling Staff","Baston de Duelos",""),

    WEAPONS("weapon","Weapons", "Armas",""),
    CAFTING("crafting","Crafting","Crafteos",""),
    ALLOY_FURNACE("alloy_furnace","Alloy Furnace","Horno de fundicion",""),

    PHYSICAL("physical","Physical", "Fisico",""),
    SPIRITUAL("spiritual","Spiritual","Espiritual",""),
    COGNITIVE("cognitive","Cognitive","Cognitivo",""),
    TEMPORAL("temporal","Temporal","Temporal",""),
    DIVINE("divine","Divine", "Divino",""),
    HYBRID("hybrid","Hybrid","Hibrido",""),
    ENHANCEMENT("enhancement","Enhancement","Mejora",""),

    VIALS("vials","Vials", "Viales",""),
    RINGS("rings","Rings","Anillos",""),
    BANDS("bands","Bands","Bandas",""),
    SPIKES("spikes","Spikes","Clavos",""),
    ICONS("icons","Icons","Iconos",""),
    ALLOYS("alloys","Alloys","Aleaciones",""),
    PATTERNS("patterns","Patterns","Patrones","");

    private final String id;
    private final String english;
    private final String spanish;
    private final String japanese;

    CTW(String id, String english, String spanish, String japanese) {
        this.id = id;
        this.english = english;
        this.spanish = spanish;
        this.japanese = japanese;
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
}
