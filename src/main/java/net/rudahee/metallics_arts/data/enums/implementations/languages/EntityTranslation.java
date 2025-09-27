package net.rudahee.metallics_arts.data.enums.implementations.languages;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EntityTranslation {

    LURCHER("entity.metallics_arts.iron_allomancer_entity", "Lurcher", "Atraedor","Atraedôh","Estirador"),
    GEN_LURCHER("item.metallics_arts.iron_allomancer_entity_spawn_egg", "Lurcher Spawn Egg", "Generar atraedor","Guebo heneradôh d'atraedôh","Ou d’aparició d’Estirador"),
    COINSHOT("entity.metallics_arts.steel_allomancer_entity", "Coinshot", "Lanzamonedas","Lançamonedâ","Llançamonedes"),
    GEN_COINSHOT("item.metallics_arts.steel_allomancer_entity_spawn_egg", "Coinshot Spawn Egg", "Generar lanzamonedas","Guebo heneradôh de lançamonedâ","Ou d’aparició de Llançamonedes"),
    THUG("entity.metallics_arts.pewter_allomancer_entity", "Thug", "Violento","Biolento","Perdonavides"),
    GEN_THUG("item.metallics_arts.pewter_allomancer_entity_spawn_egg", "Thug Spawn Egg", "Generar violento","Guebo heneradôh de biolento","Ous d’aparició de Perdonavides"),
    ETTMETAL("entity.metallics_arts.ettmetal_allomancer_entity", "Ettmetal Allomancer", "Alomante de ettmetal","Alomante d'êttmetâh","Alomant d’Ettmetal"),
    GEN_ETTMETAL("item.metallics_arts.ettmetal_allomancer_entity_spawn_egg", "Ettmetal Allomancer Spawn Egg", "Generar alomante de ettmetal","Guebo heneradôh d'alomante d'êttmetâh","Ou d’aparició d’Alomant d’Ettmetal"),


    SKIMMER("entity.metallics_arts.iron_ferrin_entity", "Skimmer", "Deslizador","Ahûttadôh","Flotador"),
    GEN_SKIMMER("item.metallics_arts.iron_ferrin_entity_spawn_egg", "Skimmer Spawn Egg", "Generar deslizador","Guebo heneradôh d'ahûttadôh","Ou d’aparició de Flotador"),
    BLOODMAKER("entity.metallics_arts.gold_ferrin_entity", "Bloodmaker", "Hacedor de sangre","Açedôh de çangre","Fabricasang"),
    GEN_BLOODMAKER("item.metallics_arts.gold_ferrin_entity_spawn_egg", "Bloodmaker Spawn Egg", "Generar hacedor de sangre","Guebo heneradôh d'açedôh de çangre","Ou d’aparició de Fabricasang"),
    FIRESOUL("entity.metallics_arts.brass_ferrin_entity", "Firesoul", "Alma de fuego","Arma de fuego","Ànima de Foc"),
    GEN_FIRESOUL("item.metallics_arts.brass_ferrin_entity_spawn_egg", "Firesoul Spawn Egg", "Generar alma de fuego","Guebo heneradôh d'arma de fuego","Ou d’aparició d’Ànima de Foc"),

    HAZEKILLER_RANGED("entity.metallics_arts.haze_killer_ranged_entity", "Haze Killer (Ranged)", "Mataneblino (Arquero)","Antineblino (a dîttançia)","Mataboires (A distància)"),
    GEN_HAZEKILLER_RANGED("item.metallics_arts.haze_killer_ranged_entity_spawn_egg", "Haze Killer (Ranged) Spawn Egg", "Generar mataneblino (Arquero)","Guebo heneradôh d'antineblino (a dîttançia)","Ou d’aparició de Mataboires (A distància)"),
    HAZEKILLER_MELEE("entity.metallics_arts.haze_killer_melee_entity", "Haze Killer (Melee)", "Mataneblino (Luchador)","Antineblino (cuerpo a cuerpo)","Mataboires (Cos a cos)"),
    GEN_HAZEKILLER_MELEE("item.metallics_arts.haze_killer_melee_entity_spawn_egg", "Haze Killer (Melee) Spawn Egg", "Generar mataneblino (Luchador)","Guebo heneradôh d'antineblino (cuerpo a cuerpo)","Ou d’aparició de Mataboires (Cos a cos)"),
    HAZEKILLER_TANK("entity.metallics_arts.haze_killer_tank_entity", "Haze Killer (Tank)", "Mataneblino (Tanque)","Antineblino (tanque)","Mataboires (Pesant)"),
    GEN_HAZEKILLER_TANK("item.metallics_arts.haze_killer_tank_entity_spawn_egg", "Haze Killer (Tank) Spawn Egg", "Generar mataneblino (Tanque)","Guebo heneradôh d'antineblino (tanque)","Ou d’aparició de Mataboires (Pesant)"),

    FORGE_MASTER("entity.minecraft.villager.metallics_arts.forge_master", "Forge Master", "Maestro de la Forja","Maêttro forhadôh","Mestre de Forja"),
    HEMALURGY_MONK("entity.minecraft.villager.metallics_arts.hemalurgy_monk", "Hemalurgy Monk", "Monje hemalurgico","Monhe emalúrhico","Monjo d’Hemalúrgia"),
    HEMALURGY_WARRIOR("entity.minecraft.villager.metallics_arts.hemalurgy_warrior", "Hemalurgy Warrior", "Guerrero hemalurgico","Gerrero emalúrhico","Guerrer d’Hemalúrgia"),

    IRON_INQUISITOR("entity.metallics_arts.iron_inquisitor_entity", "Iron Inquisitor", "Inquisidor de hierro","Inquiçidôh de yerro","Inquisidor de Ferro"),
    STEEL_INQUISITOR("entity.metallics_arts.steel_inquisitor_entity", "Steel Inquisitor", "Inquisidor de acero","Inquiçidôh d'açero","Inquisidor d’Acer"),

    PEWTER_INQUISITOR("entity.metallics_arts.pewter_inquisitor_entity", "Pewter Inquisitor", "Inquisidor de peltre","Inquiçidôh de pertre","Inquisidor de Peltre");


    private final String key;
    private final String english;
    private final String spanish;
    private final String andaluz;
    private final String catalan;
}
