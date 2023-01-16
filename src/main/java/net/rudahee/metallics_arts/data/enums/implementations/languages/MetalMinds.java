package net.rudahee.metallics_arts.data.enums.implementations.languages;

import mezz.jei.common.gui.overlay.bookmarks.ILeftAreaContent;
import net.rudahee.metallics_arts.data.enums.interfaces.ILanguage;

public enum MetalMinds implements ILanguage {
    IRON_STEEL(MetalNamesEnum.IRON.getId() + "_" + MetalNamesEnum.STEEL.getId(),
            MetalNamesEnum.IRON.getNameInEnglish()+ "-" + MetalNamesEnum.STEEL.getNameInEnglish(),
            MetalNamesEnum.IRON.getNameInSpanish()+ "-" + MetalNamesEnum.STEEL.getNameInSpanish()),
    TIN_PEWTER(MetalNamesEnum.TIN.getId() + "_" + MetalNamesEnum.PEWTER.getId(),
            MetalNamesEnum.TIN.getNameInEnglish()+ "-" + MetalNamesEnum.PEWTER.getNameInEnglish(),
            MetalNamesEnum.TIN.getNameInSpanish()+ "-" + MetalNamesEnum.PEWTER.getNameInSpanish()),
    COPPER_BRONZE(MetalNamesEnum.COPPER.getId() + "_" + MetalNamesEnum.BRONZE.getId(),
            MetalNamesEnum.COPPER.getNameInEnglish()+ "-" + MetalNamesEnum.BRONZE.getNameInEnglish(),
            MetalNamesEnum.COPPER.getNameInSpanish()+ "-" + MetalNamesEnum.BRONZE.getNameInSpanish()),
    ZINC_BRASS(MetalNamesEnum.ZINC.getId() + "_" + MetalNamesEnum.BRASS.getId(),
            MetalNamesEnum.ZINC.getNameInEnglish()+ "-" + MetalNamesEnum.BRASS.getNameInEnglish(),
            MetalNamesEnum.ZINC.getNameInSpanish()+ "-" + MetalNamesEnum.BRASS.getNameInSpanish()),
    CHROMIUM_NICROSIL(MetalNamesEnum.CHROMIUM.getId() + "_" + MetalNamesEnum.NICROSIL.getId(),
            MetalNamesEnum.CHROMIUM.getNameInEnglish()+ "-" + MetalNamesEnum.NICROSIL.getNameInEnglish(),
            MetalNamesEnum.CHROMIUM.getNameInSpanish()+ "-" + MetalNamesEnum.NICROSIL.getNameInSpanish()),
    ALUMINUM_DURALUMIN(MetalNamesEnum.ALUMINUM.getId() + "_" + MetalNamesEnum.DURALUMIN.getId(),
            MetalNamesEnum.ALUMINUM.getNameInEnglish()+ "-" + MetalNamesEnum.DURALUMIN.getNameInEnglish(),
            MetalNamesEnum.ALUMINUM.getNameInSpanish()+ "-" + MetalNamesEnum.DURALUMIN.getNameInSpanish()),
    CADMIUM_BENDALLOY(MetalNamesEnum.CADMIUM.getId() + "_" + MetalNamesEnum.BENDALLOY.getId(),
            MetalNamesEnum.CADMIUM.getNameInEnglish()+ "-" + MetalNamesEnum.BENDALLOY.getNameInEnglish(),
            MetalNamesEnum.CADMIUM.getNameInSpanish()+ "-" + MetalNamesEnum.BENDALLOY.getNameInSpanish()),
    GOLD_ELECTRUM(MetalNamesEnum.GOLD.getId() + "_" + MetalNamesEnum.ELECTRUM.getId(),
            MetalNamesEnum.GOLD.getNameInEnglish()+ "-" + MetalNamesEnum.ELECTRUM.getNameInEnglish(),
            MetalNamesEnum.GOLD.getNameInSpanish()+ "-" + MetalNamesEnum.ELECTRUM.getNameInSpanish()),
    ATIUM_MALTIUM(GemNames.ATIUM.getId() + "_" + GemNames.MALATIUM.getId(),
            GemNames.ATIUM.getNameInEnglish()+ "-" + GemNames.MALATIUM.getNameInEnglish(),
            GemNames.ATIUM.getNameInSpanish()+ "-" + GemNames.MALATIUM.getNameInSpanish()),
    LERASIUM_ETTMETAL(GemNames.LERASIUM.getId() + "_" + GemNames.ETTMETAL.getId(),
            GemNames.LERASIUM.getNameInEnglish()+ "-" + GemNames.ETTMETAL.getNameInEnglish(),
            GemNames.LERASIUM.getNameInSpanish()+ "-" + GemNames.ETTMETAL.getNameInSpanish());
    private final String id;
    private final String english;
    private final String spanish;
    MetalMinds (String id, String english, String spanish) {
        this.id = id;
        this.english = english;
        this.spanish = spanish;
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
}