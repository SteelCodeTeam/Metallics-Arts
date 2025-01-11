package net.rudahee.metallics_arts.data.enums.implementations.languages;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GUIAndScreensTranslation {

    CRUCIBLE_FURNACE("block.metallics_arts.menu.crucible_furnace", "Crucible Furnace", "Horno de crisol"),
    DISTILLERY("block.metallics_arts.menu.distillery_menu.name", "Distillery", "Destilería"),
    HEMALURGY_ALTAR_FRONT("block.metallics_arts.menu.hemalurgy_altar_front", "Hemalurgy Altar (Front)", "Altar hemalurgico (Frontal)"),
    HEMALURGY_ALTAR_BACK("block.metallics_arts.menu.hemalurgy_altar_back", "Hemalurgy Altar (Back)", "Altar hemalurgico (Trasero)");

    private final String key;
    private final String english;
    private final String spanish;
}
