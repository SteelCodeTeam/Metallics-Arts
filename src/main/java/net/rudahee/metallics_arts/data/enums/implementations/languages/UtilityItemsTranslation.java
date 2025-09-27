package net.rudahee.metallics_arts.data.enums.implementations.languages;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UtilityItemsTranslation {

    // Core items
    OBSIDIAN_CORE("item.metallics_arts.core_obsidian", "Obsidian Core", "Núcleo de obsidiana","Núcleo d'ôççidiana"),
    ALUMINUM_CORE("item.metallics_arts.core_aluminum", "Aluminum Core", "Núcleo de aluminio","Núcleo d'aluminio"),
    STEEL_CORE("item.metallics_arts.core_steel", "Steel Core", "Núcleo de acero","Núcleo d'açero"),
    ETTMETAL_CORE("item.metallics_arts.core_ettmetal", "Ettmetal Core", "Núcleo de ettmetal","Núcleo d'êttmetâh"),
    LERASIUM_CORE("item.metallics_arts.core_lerasium", "Lerasium Core", "Núcleo de lerasium","Núcleo de leraçiun"),
    ATIUM_CORE("item.metallics_arts.core_atium", "Atium Core", "Núcleo de atium","Núcleo d'atiun"),
    COPPER_CORE("item.metallics_arts.core_copper", "Copper Core", "Núcleo de cobre","Núcleo de cobre"),

    // Signs
    IRON_SIGN("block.metallics_arts.iron_standing_sign", "Iron Sign", "Cartel de hierro","Cartêh de yerro"),
    GOLD_SIGN("block.metallics_arts.gold_standing_sign", "Gold Sign", "Cartel de oro","Cartêh d'oro"),
    COPPER_SIGN("block.metallics_arts.copper_standing_sign", "Copper Sign", "Cartel de cobre","Cartêh de cobre"),
    ALUMINUM_SIGN("block.metallics_arts.aluminum_standing_sign", "Aluminum Sign", "Cartel de aluminio","Cartêh d'aluminio"),

    // Disc
    SAZED_DISC("item.metallics_arts.sazed_disc", "Sazed's Disc", "Disco de Sazed","Dîcco de Sazed"),
    SAZED_DISC_DESC("item.metallics_arts.sazed_disc.desc", "Sazed's Disc", "El Perreo De Las Eras - Kain_H","Dîcco de Sazed"),

    // Table
    CRUCIBLE_FURNACE("block.metallics_arts.crucible_furnace", "Crucible Furnace", "Horno de crisol","Ônno de criçôh"),
    HEMALURGY_ALTAR_FRONT("block.metallics_arts.hemalurgy_altar_front", "Hemalurgy Altar (Front)", "Altar hemalurgico (frontal)","Artâh emalúrhico (frente)"),
    HEMALURGY_ALTAR_BACK("block.metallics_arts.hemalurgy_altar_back", "Hemalurgy Altar (Back)", "Altar hemalurgico (trasero)","Artâh emalúrhico (detrâ)"),
    DISTILLERY("block.metallics_arts.distillery", "Distillery", "Destilería","Dêttiladora");


    private final String key;
    private final String english;
    private final String spanish;
    private final String andaluz;
}