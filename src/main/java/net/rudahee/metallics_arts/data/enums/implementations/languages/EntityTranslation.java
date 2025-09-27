package net.rudahee.metallics_arts.data.enums.implementations.languages;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EntityTranslation {

    LURCHER("entity.metallics_arts.iron_allomancer_entity", "Lurcher", "Atraedor","Atraedôh"),
    GEN_LURCHER("item.metallics_arts.iron_allomancer_entity_spawn_egg", "Lurcher Spawn Egg", "Generar atraedor","Guebo heneradôh d'atraedôh"),
    COINSHOT("entity.metallics_arts.steel_allomancer_entity", "Coinshot", "Lanzamonedas","Lançamonedâ"),
    GEN_COINSHOT("item.metallics_arts.steel_allomancer_entity_spawn_egg", "Coinshot Spawn Egg", "Generar lanzamonedas","Guebo heneradôh de lançamonedâ"),
    THUG("entity.metallics_arts.pewter_allomancer_entity", "Thug", "Violento","Biolento"),
    GEN_THUG("item.metallics_arts.pewter_allomancer_entity_spawn_egg", "Thug Spawn Egg", "Generar violento","Guebo heneradôh de biolento"),
    ETTMETAL("entity.metallics_arts.ettmetal_allomancer_entity", "Ettmetal Allomancer", "Alomante de ettmetal","Alomante d'êttmetâh"),
    GEN_ETTMETAL("item.metallics_arts.ettmetal_allomancer_entity_spawn_egg", "Ettmetal Allomancer Spawn Egg", "Generar alomante de ettmetal","Guebo heneradôh d'alomante d'êttmetâh"),


    SKIMMER("entity.metallics_arts.iron_ferrin_entity", "Skimmer", "Deslizador","Ahûttadôh"),
    GEN_SKIMMER("item.metallics_arts.iron_ferrin_entity_spawn_egg", "Skimmer Spawn Egg", "Generar deslizador","Guebo heneradôh d'ahûttadôh"),
    BLOODMAKER("entity.metallics_arts.gold_ferrin_entity", "Bloodmaker", "Hacedor de sangre","Açedôh de çangre"),
    GEN_BLOODMAKER("item.metallics_arts.gold_ferrin_entity_spawn_egg", "Bloodmaker Spawn Egg", "Generar hacedor de sangre","Guebo heneradôh d'açedôh de çangre"),
    FIRESOUL("entity.metallics_arts.brass_ferrin_entity", "Firesoul", "Alma de fuego","Arma de fuego"),
    GEN_FIRESOUL("item.metallics_arts.brass_ferrin_entity_spawn_egg", "Firesoul Spawn Egg", "Generar alma de fuego","Guebo heneradôh d'arma de fuego"),

    HAZEKILLER_RANGED("entity.metallics_arts.haze_killer_ranged_entity", "Haze Killer (Ranged)", "Mataneblino (Arquero)","Antineblino (a dîttançia)"),
    GEN_HAZEKILLER_RANGED("item.metallics_arts.haze_killer_ranged_entity_spawn_egg", "Haze Killer (Ranged) Spawn Egg", "Generar mataneblino (Arquero)","Guebo heneradôh d'antineblino (a dîttançia)"),
    HAZEKILLER_MELEE("entity.metallics_arts.haze_killer_melee_entity", "Haze Killer (Melee)", "Mataneblino (Luchador)","Antineblino (cuerpo a cuerpo)"),
    GEN_HAZEKILLER_MELEE("item.metallics_arts.haze_killer_melee_entity_spawn_egg", "Haze Killer (Melee) Spawn Egg", "Generar mataneblino (Luchador)","Guebo heneradôh d'antineblino (cuerpo a cuerpo)"),
    HAZEKILLER_TANK("entity.metallics_arts.haze_killer_tank_entity", "Haze Killer (Tank)", "Mataneblino (Tanque)","Antineblino (tanque)"),
    GEN_HAZEKILLER_TANK("item.metallics_arts.haze_killer_tank_entity_spawn_egg", "Haze Killer (Tank) Spawn Egg", "Generar mataneblino (Tanque)","Guebo heneradôh d'antineblino (tanque)"),

    FORGE_MASTER("entity.minecraft.villager.metallics_arts.forge_master", "Forge Master", "Maestro de la Forja","Maêttro forhadôh"),
    HEMALURGY_MONK("entity.minecraft.villager.metallics_arts.hemalurgy_monk", "Hemalurgy Monk", "Monje hemalurgico","Monhe emalúrhico"),
    HEMALURGY_WARRIOR("entity.minecraft.villager.metallics_arts.hemalurgy_warrior", "Hemalurgy Warrior", "Guerrero hemalurgico","Gerrero emalúrhico"),

    IRON_INQUISITOR("entity.metallics_arts.iron_inquisitor_entity", "Iron Inquisitor", "Inquisidor de hierro","Inquiçidôh de yerro"),
    STEEL_INQUISITOR("entity.metallics_arts.steel_inquisitor_entity", "Steel Inquisitor", "Inquisidor de acero","Inquiçidôh d'açero"),

    PEWTER_INQUISITOR("entity.metallics_arts.pewter_inquisitor_entity", "Pewter Inquisitor", "Inquisidor de peltre","Inquiçidôh de pertre");


    private final String key;
    private final String english;
    private final String spanish;
    private final String andaluz;
}
