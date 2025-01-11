package net.rudahee.metallics_arts.data.enums.implementations.languages;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UtilityItemsTranslation {

    // Core items
    OBSIDIAN_CORE("item.metallics_arts.core_obsidian", "Obsidian Core", "Núcleo de obsidiana"),
    ALUMINUM_CORE("item.metallics_arts.core_aluminum", "Aluminum Core", "Núcleo de aluminio"),
    STEEL_CORE("item.metallics_arts.core_steel", "Steel Core", "Núcleo de acero"),
    ETTMETAL_CORE("item.metallics_arts.core_ettmetal", "Ettmetal Core", "Núcleo de ettmetal"),
    LERASIUM_CORE("item.metallics_arts.core_lerasium", "Lerasium Core", "Núcleo de lerasium"),
    ATIUM_CORE("item.metallics_arts.core_atium", "Atium Core", "Núcleo de atium"),
    COPPER_CORE("item.metallics_arts.core_copper", "Copper Core", "Núcleo de cobre"),

    // Signs
    IRON_SIGN("block.metallics_arts.iron_standing_sign", "Iron Sign", "Cartel de hierro"),
    GOLD_SIGN("block.metallics_arts.gold_standing_sign", "Gold Sign", "Cartel de oro"),
    COPPER_SIGN("block.metallics_arts.copper_standing_sign", "Copper Sign", "Cartel de cobre"),
    ALUMINUM_SIGN("block.metallics_arts.aluminum_standing_sign", "Aluminum Sign", "Cartel de aluminio"),

    // Disc
    SAZED_DISC("item.metallics_arts.sazed_disc", "Sazed's Disc", "Disco de Sazed"),
    SAZED_DISC_DESC("item.metallics_arts.sazed_disc.desc", "Sazed's Disc", "El Perreo De Las Eras - Kain_H"),

    // Table
    CRUCIBLE_FURNACE("block.metallics_arts.crucible_furnace", "Crucible Furnace", "Horno de crisol"),
    HEMALURGY_ALTAR_FRONT("block.metallics_arts.hemalurgy_altar_front", "Hemalurgy Altar (Front)", "Altar hemalurgico (frontal)"),
    HEMALURGY_ALTAR_BACK("block.metallics_arts.hemalurgy_altar_back", "Hemalurgy Altar (Back)", "Altar hemalurgico (trasero)"),
    DISTILLERY("block.metallics_arts.distillery", "Distillery", "Destilería");


    private final String key;
    private final String english;
    private final String spanish;
}