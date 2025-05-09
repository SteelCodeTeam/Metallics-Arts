package net.rudahee.metallics_arts.data.enums.implementations.languages;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EntityTranslation {

    LURCHER("entity.metallics_arts.iron_allomancer_entity", "Lurcher", "Atraedor"),
    GEN_LURCHER("item.metallics_arts.iron_allomancer_entity_spawn_egg", "Lurcher Spawn Egg", "Generar atraedor"),
    COINSHOT("entity.metallics_arts.steel_allomancer_entity", "Coinshot", "Lanzamonedas"),
    GEN_COINSHOT("item.metallics_arts.steel_allomancer_entity_spawn_egg", "Coinshot Spawn Egg", "Generar lanzamonedas"),
    THUG("entity.metallics_arts.pewter_allomancer_entity", "Thug", "Violento"),
    GEN_THUG("item.metallics_arts.pewter_allomancer_entity_spawn_egg", "Thug Spawn Egg", "Generar violento"),
    ETTMETAL("entity.metallics_arts.ettmetal_allomancer_entity", "Ettmetal Allomancer", "Alomante de ettmetal"),
    GEN_ETTMETAL("item.metallics_arts.ettmetal_allomancer_entity_spawn_egg", "Ettmetal Allomancer Spawn Egg", "Generar alomante de ettmetal"),


    SKIMMER("entity.metallics_arts.iron_ferrin_entity", "Skimmer", "Deslizador"),
    GEN_SKIMMER("item.metallics_arts.iron_ferrin_entity_spawn_egg", "Skimmer Spawn Egg", "Generar deslizador"),
    BLOODMAKER("entity.metallics_arts.gold_ferrin_entity", "Bloodmaker", "Hacedor de sangre"),
    GEN_BLOODMAKER("item.metallics_arts.gold_ferrin_entity_spawn_egg", "Bloodmaker Spawn Egg", "Generar hacedor de sangre"),
    FIRESOUL("entity.metallics_arts.brass_ferrin_entity", "Firesoul", "Alma de fuego"),
    GEN_FIRESOUL("item.metallics_arts.brass_ferrin_entity_spawn_egg", "Firesoul Spawn Egg", "Generar alma de fuego"),

    HAZEKILLER_RANGED("entity.metallics_arts.haze_killer_ranged_entity", "Haze Killer (Ranged)", "Mataneblino (Arquero)"),
    GEN_HAZEKILLER_RANGED("item.metallics_arts.haze_killer_ranged_entity_spawn_egg", "Haze Killer (Ranged) Spawn Egg", "Generar mataneblino (Arquero)"),
    HAZEKILLER_MELEE("entity.metallics_arts.haze_killer_melee_entity", "Haze Killer (Melee)", "Mataneblino (Luchador)"),
    GEN_HAZEKILLER_MELEE("item.metallics_arts.haze_killer_melee_entity_spawn_egg", "Haze Killer (Melee) Spawn Egg", "Generar mataneblino (Luchador)"),
    HAZEKILLER_TANK("entity.metallics_arts.haze_killer_tank_entity", "Haze Killer (Tank)", "Mataneblino (Tanque)"),
    GEN_HAZEKILLER_TANK("item.metallics_arts.haze_killer_tank_entity_spawn_egg", "Haze Killer (Tank) Spawn Egg", "Generar mataneblino (Tanque)"),

    FORGE_MASTER("entity.minecraft.villager.metallics_arts.forge_master", "Forge Master", "Maestro de la Forja"),
    HEMALURGY_MONK("entity.minecraft.villager.metallics_arts.hemalurgy_monk", "Hemalurgy Monk", "Monje hemalurgico"),
    HEMALURGY_WARRIOR("entity.minecraft.villager.metallics_arts.hemalurgy_warrior", "Hemalurgy Warrior", "Guerrero hemalurgico"),

    IRON_INQUISITOR("entity.metallics_arts.iron_inquisitor_entity", "Iron Inquisitor", "Inquisidor de hierro"),
    STEEL_INQUISITOR("entity.metallics_arts.steel_inquisitor_entity", "Steel Inquisitor", "Inquisidor de acero"),

    PEWTER_INQUISITOR("entity.metallics_arts.pewter_inquisitor_entity", "Pewter Inquisitor", "Inquisidor de peltre");


    private final String key;
    private final String english;
    private final String spanish;
}
