package net.rudahee.metallics_arts.data.enums.implementations.languages;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WeaponTranslation {

    OBSIDIAN_DAGGER("item.metallics_arts.obsidian_dagger", "Obsidian Dagger", "Daga de obsidiana"),
    SILVER_KNIFE("item.metallics_arts.silver_knife", "Silver Knife", "Cuchillo de plata"),
    KOLOSS_BLADE("item.metallics_arts.koloss_blade", "Koloss Blade", "Espada de koloss"),
    DUELING_STAFF("item.metallics_arts.dueling_staff", "Dueling Staff", "Bastón de duelo"),
    OBSIDIAN_AXE("item.metallics_arts.obsidian_axe", "Obsidian Axe", "Hacha de obsidiana"),

    // Monedas
    COPPER_COIN("item.metallics_arts.copper_coin", "Copper Coin", "Moneda de cobre"),
    BRONZE_COIN("item.metallics_arts.bronze_coin", "Bronze Coin", "Moneda de bronce"),

    // Armeo y escudos nuevos
    VINDICATOR("item.metallics_arts.vindicator", "Vindicator", "Vindicación"),
    REVOLVER("item.metallics_arts.revolver", "Revolver", "Revólver"),
    SHOTGUN("item.metallics_arts.shotgun", "Shotgun", "Escopeta"),
    RIFLE("item.metallics_arts.rifle", "Rifle", "Rifle"),
    RIFLE_WITH_SPYGLASS("item.metallics_arts.rifle_spyglass", "Rifle with Spyglass", "Rifle con mira"),

    // Proyectiles (balas)
    REVOLVER_LEAD_BULLET("item.metallics_arts.revolver_lead_ammo", "Revolver Lead Bullet", "Bala de plomo para revólver"),
    REVOLVER_ALUMINUM_BULLET("item.metallics_arts.revolver_aluminum_ammo", "Revolver Aluminum Bullet", "Bala de aluminio para revólver"),
    SHOTGUN_LEAD_BULLET("item.metallics_arts.shotgun_lead_ammo", "Shotgun Lead Bullet", "Bala de plomo para escopeta"),
    SHOTGUN_ALUMINUM_BULLET("item.metallics_arts.shotgun_aluminum_ammo", "Shotgun Aluminum Bullet", "Bala de aluminio para escopeta"),
    RIFLE_LEAD_BULLET("item.metallics_arts.rifle_lead_ammo", "Rifle Lead Bullet", "Bala de plomo para rifle"),
    RIFLE_ALUMINUM_BULLET("item.metallics_arts.rifle_aluminum_ammo", "Rifle Aluminum Bullet", "Bala de aluminio para rifle"),

    // Escudos
    WOOD_SHIELD("item.metallics_arts.wood_shield", "Wood Shield", "Escudo de madera"),
    BRONZE_ALUMINUM_SHIELD("item.metallics_arts.bronze_aluminum_shield", "Bronze Aluminum Shield", "Escudo de bronce y aluminio"),

    STEEL_HELMET("item.metallics_arts.steel_helmet", "Steel Helmet", "Casco de acero"),
    STEEL_CHESTPLATE("item.metallics_arts.steel_chestplate", "Steel Chestplate", "Peto de acero"),
    STEEL_LEGGINGS("item.metallics_arts.steel_leggings", "Steel Leggings", "Grebas de acero"),
    STEEL_BOOTS("item.metallics_arts.steel_boots", "Steel Boots", "Botas de acero"),

    // Armaduras de aluminio
    ALUMINUM_HELMET("item.metallics_arts.aluminum_helmet", "Aluminum Helmet", "Casco de aluminio"),
    ALUMINUM_CHESTPLATE("item.metallics_arts.aluminum_chestplate", "Aluminum Chestplate", "Peto de aluminio"),
    ALUMINUM_LEGGINGS("item.metallics_arts.aluminum_leggings", "Aluminum Leggings", "Grebas de aluminio"),
    ALUMINUM_BOOTS("item.metallics_arts.aluminum_boots", "Aluminum Boots", "Botas de aluminio"),

    // Armaduras de ettmetal
    ETTMETAL_HELMET("item.metallics_arts.ettmetal_helmet", "Ettmetal Helmet", "Casco de ettmetal"),
    ETTMETAL_CHESTPLATE("item.metallics_arts.ettmetal_chestplate", "Ettmetal Chestplate", "Peto de ettmetal"),
    ETTMETAL_LEGGINGS("item.metallics_arts.ettmetal_leggings", "Ettmetal Leggings", "Grebas de ettmetal"),
    ETTMETAL_BOOTS("item.metallics_arts.ettmetal_boots", "Ettmetal Boots", "Botas de ettmetal"),

    // Armaduras de lerasium
    LERASIUM_HELMET("item.metallics_arts.lerasium_helmet", "Lerasium Helmet", "Casco de lerasium"),
    LERASIUM_CHESTPLATE("item.metallics_arts.lerasium_chestplate", "Lerasium Chestplate", "Peto de lerasium"),
    LERASIUM_LEGGINGS("item.metallics_arts.lerasium_leggings", "Lerasium Leggings", "Grebas de lerasium"),
    LERASIUM_BOOTS("item.metallics_arts.lerasium_boots", "Lerasium Boots", "Botas de lerasium"),

    // Armaduras de atium
    ATIUM_HELMET("item.metallics_arts.atium_helmet", "Atium Helmet", "Casco de atium"),
    ATIUTM_CHESTPLATE("item.metallics_arts.atium_chestplate", "Atium Chestplate", "Peto de atium"),
    ATIUM_LEGGINGS("item.metallics_arts.atium_leggings", "Atium Leggings", "Grebas de atium"),
    ATIUM_BOOTS("item.metallics_arts.atium_boots", "Atium Boots", "Botas de atium"),

    // Armaduras de cobre
    COPPER_HELMET("item.metallics_arts.copper_helmet", "Copper Helmet", "Casco de cobre"),
    COPPER_CHESTPLATE("item.metallics_arts.copper_chestplate", "Copper Chestplate", "Peto de cobre"),
    COPPER_LEGGINGS("item.metallics_arts.copper_leggings", "Copper Leggings", "Grebas de cobre"),
    COPPER_BOOTS("item.metallics_arts.copper_boots", "Copper Boots", "Botas de cobre"),

    // Mistcloack
    MISTCLOAK("item.metallics_arts.mistcloak", "Mistcloak", "Capa de bruma"),
    MISTCLOAK_TOOLTIP("metallics_arts.item.tooltip.mistcloak", "Increase your speed while the mists are visible", "Aumenta tu velocidad mientras las brumas sean visibles");


    private final String key;
    private final String english;
    private final String spanish;
}
