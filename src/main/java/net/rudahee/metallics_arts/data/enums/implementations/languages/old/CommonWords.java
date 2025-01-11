package net.rudahee.metallics_arts.data.enums.implementations.languages.old;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonWords {
    RAW("raw", "Raw", "en bruto"),
    ORE("ore", "Ore", "Mena de"),
    DEEPSLATE("deepslate", "Deepslate", "de pizarra profunda"),
    BLOCK("block", "Block of", "Bloque de"),
    INGOT("ingot", "Ingot", "Lingote de"),
    GEM("gem", "Gem", "Gema de"),


    ;


    private final String id;
    private final String english;
    private final String spanish;

}
