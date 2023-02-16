package net.rudahee.metallics_arts.data.enums.implementations.languages;

import net.rudahee.metallics_arts.data.enums.interfaces.ILanguage;

public enum MetalMinds implements ILanguage {
    IRON_STEEL(MetalNamesEnum.IRON.getId() + "_" + MetalNamesEnum.STEEL.getId(),
            MetalNamesEnum.IRON.getNameInEnglish()+ "-" + MetalNamesEnum.STEEL.getNameInEnglish(),
            MetalNamesEnum.IRON.getNameInSpanish()+ "-" + MetalNamesEnum.STEEL.getNameInSpanish(),
            MetalNamesEnum.IRON.getNameInPolish()+ "-" + MetalNamesEnum.STEEL.getNameInPolish()),
    TIN_PEWTER(MetalNamesEnum.TIN.getId() + "_" + MetalNamesEnum.PEWTER.getId(),
            MetalNamesEnum.TIN.getNameInEnglish()+ "-" + MetalNamesEnum.PEWTER.getNameInEnglish(),
            MetalNamesEnum.TIN.getNameInSpanish()+ "-" + MetalNamesEnum.PEWTER.getNameInSpanish(),
            MetalNamesEnum.TIN.getNameInPolish()+ "-" + MetalNamesEnum.PEWTER.getNameInPolish()),
    COPPER_BRONZE(MetalNamesEnum.COPPER.getId() + "_" + MetalNamesEnum.BRONZE.getId(),
            MetalNamesEnum.COPPER.getNameInEnglish()+ "-" + MetalNamesEnum.BRONZE.getNameInEnglish(),
            MetalNamesEnum.COPPER.getNameInSpanish()+ "-" + MetalNamesEnum.BRONZE.getNameInSpanish(),
            MetalNamesEnum.COPPER.getNameInPolish()+ "-" + MetalNamesEnum.BRONZE.getNameInPolish()),
    ZINC_BRASS(MetalNamesEnum.ZINC.getId() + "_" + MetalNamesEnum.BRASS.getId(),
            MetalNamesEnum.ZINC.getNameInEnglish()+ "-" + MetalNamesEnum.BRASS.getNameInEnglish(),
            MetalNamesEnum.ZINC.getNameInSpanish()+ "-" + MetalNamesEnum.BRASS.getNameInSpanish(),
            MetalNamesEnum.ZINC.getNameInPolish()+ "-" + MetalNamesEnum.BRASS.getNameInPolish()),
    CHROMIUM_NICROSIL(MetalNamesEnum.CHROMIUM.getId() + "_" + MetalNamesEnum.NICROSIL.getId(),
            MetalNamesEnum.CHROMIUM.getNameInEnglish()+ "-" + MetalNamesEnum.NICROSIL.getNameInEnglish(),
            MetalNamesEnum.CHROMIUM.getNameInSpanish()+ "-" + MetalNamesEnum.NICROSIL.getNameInSpanish(),
            MetalNamesEnum.CHROMIUM.getNameInPolish()+ "-" + MetalNamesEnum.NICROSIL.getNameInPolish()),
    ALUMINUM_DURALUMIN(MetalNamesEnum.ALUMINUM.getId() + "_" + MetalNamesEnum.DURALUMIN.getId(),
            MetalNamesEnum.ALUMINUM.getNameInEnglish()+ "-" + MetalNamesEnum.DURALUMIN.getNameInEnglish(),
            MetalNamesEnum.ALUMINUM.getNameInSpanish()+ "-" + MetalNamesEnum.DURALUMIN.getNameInSpanish(),
            MetalNamesEnum.ALUMINUM.getNameInPolish()+ "-" + MetalNamesEnum.DURALUMIN.getNameInPolish()),
    CADMIUM_BENDALLOY(MetalNamesEnum.CADMIUM.getId() + "_" + MetalNamesEnum.BENDALLOY.getId(),
            MetalNamesEnum.CADMIUM.getNameInEnglish()+ "-" + MetalNamesEnum.BENDALLOY.getNameInEnglish(),
            MetalNamesEnum.CADMIUM.getNameInSpanish()+ "-" + MetalNamesEnum.BENDALLOY.getNameInSpanish(),
            MetalNamesEnum.CADMIUM.getNameInPolish()+ "-" + MetalNamesEnum.BENDALLOY.getNameInPolish()),
    GOLD_ELECTRUM(MetalNamesEnum.GOLD.getId() + "_" + MetalNamesEnum.ELECTRUM.getId(),
            MetalNamesEnum.GOLD.getNameInEnglish()+ "-" + MetalNamesEnum.ELECTRUM.getNameInEnglish(),
            MetalNamesEnum.GOLD.getNameInSpanish()+ "-" + MetalNamesEnum.ELECTRUM.getNameInSpanish(),
            MetalNamesEnum.GOLD.getNameInPolish()+ "-" + MetalNamesEnum.ELECTRUM.getNameInPolish()),
    ATIUM_MALTIUM(MetalNamesEnum.ATIUM.getId() + "_" + MetalNamesEnum.MALATIUM.getId(),
            MetalNamesEnum.ATIUM.getNameInEnglish()+ "-" + MetalNamesEnum.MALATIUM.getNameInEnglish(),
            MetalNamesEnum.ATIUM.getNameInSpanish()+ "-" + MetalNamesEnum.MALATIUM.getNameInSpanish(),
            MetalNamesEnum.ATIUM.getNameInPolish()+ "-" + MetalNamesEnum.MALATIUM.getNameInPolish()),
    LERASIUM_ETTMETAL(MetalNamesEnum.LERASIUM.getId() + "_" + MetalNamesEnum.ETTMETAL.getId(),
            MetalNamesEnum.LERASIUM.getNameInEnglish()+ "-" + MetalNamesEnum.ETTMETAL.getNameInEnglish(),
            MetalNamesEnum.LERASIUM.getNameInSpanish()+ "-" + MetalNamesEnum.ETTMETAL.getNameInSpanish(),
            MetalNamesEnum.LERASIUM.getNameInPolish()+ "-" + MetalNamesEnum.ETTMETAL.getNameInPolish());
    private final String id;
    private final String english;
    private final String spanish;
    private final String polish;
    MetalMinds (String id, String english, String spanish, String polish) {
        this.id = id;
        this.english = english;
        this.spanish = spanish;
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

    public String getNameInPolish(){
        return  this.polish;
    }
}
