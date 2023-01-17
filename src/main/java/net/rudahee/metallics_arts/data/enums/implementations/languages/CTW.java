package net.rudahee.metallics_arts.data.enums.implementations.languages;

import net.rudahee.metallics_arts.data.enums.interfaces.ILanguage;

//CommonTranslationWords
public enum CTW implements ILanguage {
    RAW("raw", "Raw", "en bruto"),
    ORE("ore", "Ore", "Mena de"),
    DEEPSLATE("deepslate", "Deepslate", "de pizarra profunda"),
    BLOCK("block", "Block", "Bloque de"),
    INGOT("ingot", "Ingot", "Lingote de"),
    GEM("gem", "Gem", "Gema de"),
    NUGGET("nugget", "Nugget", "Pepita de"),
    BUDDING("budding", "Budding", "Brotador de"),
    CLUSTER("budding", "Cluster", "Cúmulo de"),
    BUD("bud", "Budding", "Brote de"),
    SMALL("small", "Small", "Pequeño"),
    MEDIUM("medium", "Medium", "Mediano"),
    LARGE("large", "Large", "Grande"),
    CRISTAL("cristal", "Crystallized", "Cristalizado"),
    BAND("band", "Band", "Brazal de"),
    RING("ring", "Ring", "Anillo de"),
    SPIKE("spike", "Spike", "Clavo de"),
    ALLOMANTIC("allomantic", "Allomantic", "Alomántico"),
    FERUCHEMICAL("feruchemical", "Feruchemical", "Feruqumico"),
    POWER_SELECTOR("metal_selector", "Power Selector", "Selector de poder"),
    STORAGE("storage", "Storage", "Almacenaje"),
    TAPPING("tapping", "Tapping", "Decante"),
    PUSH("push", "Push", "Empuje"),
    PULL("pull", "Pull", "Tiron"),
    VERTICAL("vertical", "Vertical", "Vertical"),
    SWITCH_OVERLAY("switch_overlay", "Switch Overlay", "Alrternar interfaz"),
    METALLICS_ARTS("metallics_arts", "Metallics Arts", "Artes Metalicas"),
    OWNER("ring", "Owner", "Propietario"),
    NOBODY("nobody", "Nobody", "Sin Propietario"),
    HAS_RESERVE("has_reserve", "Has reserve", "Tiene reservas Alomanticas"),
    NOT_HAS_RESERVE("not_has_reserve", "Not has reserve", "No tiene reservas Alomanticas"),
    STORE_IDENTITY("store_identity", "Store Identity", "Almacenando Identidad"),
    POWER_OFF("power_off", "Power Off", "Poder Apagado"),
    TAPPING_IDENTITY("tapping_identity", "Tapping Identity", "Decantando Identidad"),
    OWNER_SOMEONE("owner_someone", "Someone", "Alguien"),
    STORAGE_POWER("storage_power","Storage Power","Poder Almacenado"),
    POWERS("powers", "Powers", "Poderes"),
    METALMIND("metal_mind", "metalmind", "de mente de metal"),
    SLOT("slot", "Slot", "Ranura"),
    VIAL ("vial","Vial","Vial"),
    USES ("uses","Uses","Usos"),
    DECORATIONS ("decorations","Decorations","Decoración"),
    GUIDE ("guide","Guide","Guia"),
    FERUCHEMICAL_SHADING("feruchemical_shading","shading","feruquimico sombreado"),
    FERUCHEMICAL_SOLID ("allomantical_solid","solid","feruquimico solido"),
    ALLOMANTIC_SHADING ("feruchemical_shading","shading","alomantico sombreado"),
    ALLOMANTIC_SOLID ("allomantical_solid","solid","alomantico solido"),
    FERUCHEMICAL_PATTERN ("feruchemical_pattern","pattern","Patrón feruquimico de"),
    ALLOMANTIC_PATTERN ("allomantical_pattern","pattern","Patrón alomántico de");

    private final String id;
    private final String english;
    private final String spanish;

    CTW(String id, String english, String spanish) {
        this.id = id;
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
