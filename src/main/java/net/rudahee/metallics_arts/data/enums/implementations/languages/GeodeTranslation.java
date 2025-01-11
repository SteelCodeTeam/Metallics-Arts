package net.rudahee.metallics_arts.data.enums.implementations.languages;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GeodeTranslation {

    ETTMETAL_BUDDING("block.metallics_arts.budding_ettmetal", "Ettmetal Budding", "Brotador de ettmetal"),
    ETTMETAL_CRISTAL_BLOCK("block.metallics_arts.ettmetal_cristal_block", "Ettmetal Crystal Block", "Bloque de cristal de ettmetal"),
    SMALL_ETTMETAL_BUD("block.metallics_arts.small_ettmetal_bud", "Small Ettmetal Bud", "Brote de ettmetal pequeño"),
    MEDIUM_ETTMETAL_BUD("block.metallics_arts.medium_ettmetal_bud", "Medium Ettmetal Bud", "Brote de ettmetal medio"),
    LARGE_ETTMETAL_BUD("block.metallics_arts.large_ettmetal_bud", "Large Ettmetal Bud", "Brote de ettmetal grande"),
    ETTMETAL_CLUSTER("block.metallics_arts.ettmetal_cluster", "Ettmetal Cluster", "Cúmulo de ettmetal"),


    LERASIUM_BUDDING("block.metallics_arts.budding_lerasium", "Lerasium Budding", "Brotador de lerasium"),
    LERASIUM_CRISTAL_BLOCK("block.metallics_arts.lerasium_cristal_block", "Lerasium Crystal Block", "Bloque de cristal de lerasium"),
    SMALL_LERASIUM_BUD("block.metallics_arts.small_lerasium_bud", "Small Lerasium Bud", "Brote de lerasium pequeño"),
    MEDIUM_LERASIUM_BUD("block.metallics_arts.medium_lerasium_bud", "Medium Lerasium Bud", "Brote de lerasium medio"),
    LARGE_LERASIUM_BUD("block.metallics_arts.large_lerasium_bud", "Large Lerasium Bud", "Brote de lerasium grande"),
    LERASIUM_CLUSTER("block.metallics_arts.lerasium_cluster", "Lerasium Cluster", "Cúmulo de lerasium"),

    ATIUM_BUDDING("block.metallics_arts.budding_atium", "Atium Budding", "Brotador de atium"),
    ATIUM_CRISTAL_BLOCK("block.metallics_arts.atium_cristal_block", "Atium Crystal Block", "Bloque de cristal de atium"),
    SMALL_ATIUM_BUD("block.metallics_arts.small_atium_bud", "Small Atium Bud", "Brote de atium pequeño"),
    MEDIUM_ATIUM_BUD("block.metallics_arts.medium_atium_bud", "Medium Atium Bud", "Brote de atium medio"),
    LARGE_ATIUM_BUD("block.metallics_arts.large_atium_bud", "Large Atium Bud", "Brote de atium grande"),
    ATIUM_CLUSTER("block.metallics_arts.atium_cluster", "Atium Cluster", "Cúmulo de atium");

    private final String key;
    private final String english;
    private final String spanish;
}
