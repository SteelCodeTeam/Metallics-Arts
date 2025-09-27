package net.rudahee.metallics_arts.data.enums.implementations.languages;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GUIAndScreensTranslation {

    CRUCIBLE_FURNACE("block.metallics_arts.menu.crucible_furnace", "Crucible Furnace", "Horno de crisol","Ônno de criçôh"),
    DISTILLERY("block.metallics_arts.menu.distillery_menu.name", "Distillery", "Destilería","Dêttiladora"),
    HEMALURGY_ALTAR_FRONT("block.metallics_arts.menu.hemalurgy_altar_front", "Hemalurgy Altar (Front)", "Altar hemalurgico (Frontal)","Artâh emalúrhico (frente)"),
    HEMALURGY_ALTAR_BACK("block.metallics_arts.menu.hemalurgy_altar_back", "Hemalurgy Altar (Back)", "Altar hemalurgico (Trasero)","Artâh emalúrhico (detrâ)");

    private final String key;
    private final String english;
    private final String spanish;
    private final String andaluz;

}
