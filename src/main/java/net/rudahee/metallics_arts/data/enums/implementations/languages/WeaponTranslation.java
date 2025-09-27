package net.rudahee.metallics_arts.data.enums.implementations.languages;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WeaponTranslation {

    OBSIDIAN_DAGGER("item.metallics_arts.obsidian_dagger", "Obsidian Dagger", "Daga de obsidiana","Daga d'ôççidiana","Daga d’Obsidiana"),
    SILVER_KNIFE("item.metallics_arts.silver_knife", "Silver Knife", "Cuchillo de plata","Cuxiyo de plata","Ganivet de Plata"),
    KOLOSS_BLADE("item.metallics_arts.koloss_blade", "Koloss Blade", "Espada de koloss","Êppá de Koloss","Espasa Koloss"),
    DUELING_STAFF("item.metallics_arts.dueling_staff", "Dueling Staff", "Bastón de duelo","Bâttón de duelo","Bastó de Duel"),
    OBSIDIAN_AXE("item.metallics_arts.obsidian_axe", "Obsidian Axe", "Hacha de obsidiana","Haxa d'ôççidiana","Destral d’Obsidiana"),

    // Monedas
    COPPER_COIN("item.metallics_arts.copper_coin", "Copper Coin", "Moneda de cobre","Monêa de cobre","Moneda de Coure"),
    BRONZE_COIN("item.metallics_arts.bronze_coin", "Bronze Coin", "Moneda de bronce","Monêa de bronçe","Moneda de Bronze"),

    // Armeo y escudos nuevos
    VINDICATOR("item.metallics_arts.vindicator", "Vindicator", "Vindicación","Bindicadôh","Vindicator"),
    REVOLVER("item.metallics_arts.revolver", "Revolver", "Revólver","Rebórbê","Revòlver"),
    SHOTGUN("item.metallics_arts.shotgun", "Shotgun", "Escopeta","Êccopeta","Escopeta"),
    RIFLE("item.metallics_arts.rifle", "Rifle", "Rifle","Rifle","Fusell"),
    RIFLE_WITH_SPYGLASS("item.metallics_arts.rifle_spyglass", "Rifle with Spyglass", "Rifle con mira","Rifle con mira","Fusell amb Mira"),

    // Proyectiles (balas)
    REVOLVER_LEAD_BULLET("item.metallics_arts.revolver_lead_ammo", "Revolver Lead Bullet", "Bala de plomo para revólver","Bala de rebórbê de plomo","Bala de Plom de Revòlver"),
    REVOLVER_ALUMINUM_BULLET("item.metallics_arts.revolver_aluminum_ammo", "Revolver Aluminum Bullet", "Bala de aluminio para revólver","Bala de rebórbê de aluminio","Bala d’Alumini de Revòlver"),
    SHOTGUN_LEAD_BULLET("item.metallics_arts.shotgun_lead_ammo", "Shotgun Lead Bullet", "Bala de plomo para escopeta","Cartuxo de êccopeta de plomo","Cartutx de Plom d’Escopeta"),
    SHOTGUN_ALUMINUM_BULLET("item.metallics_arts.shotgun_aluminum_ammo", "Shotgun Aluminum Bullet", "Bala de aluminio para escopeta","Cartuxo de êccopeta de aluminio","Cartutx d’Alumini d’Escopeta"),
    RIFLE_LEAD_BULLET("item.metallics_arts.rifle_lead_ammo", "Rifle Lead Bullet", "Bala de plomo para rifle","Bala de rifle de plomo","Bala de Plom de Fusell"),
    RIFLE_ALUMINUM_BULLET("item.metallics_arts.rifle_aluminum_ammo", "Rifle Aluminum Bullet", "Bala de aluminio para rifle","Bala de rifle de aluminio","Bala d’Alumini de Fusell"),

    // Escudos
    WOOD_SHIELD("item.metallics_arts.wood_shield", "Wood Shield", "Escudo de madera","Êccúo de madera","Escut de Fusta"),
    BRONZE_ALUMINUM_SHIELD("item.metallics_arts.bronze_aluminum_shield", "Bronze Aluminum Shield", "Escudo de bronce y aluminio","Êccúo de bronçe y aluminio","Escut de Bronze i Alumini"),

    STEEL_HELMET("item.metallics_arts.steel_helmet", "Steel Helmet", "Casco de acero","Câcco d'açero","Casc d’Acer"),
    STEEL_CHESTPLATE("item.metallics_arts.steel_chestplate", "Steel Chestplate", "Peto de acero","Pexera d'açero","Pitral d’Acer"),
    STEEL_LEGGINGS("item.metallics_arts.steel_leggings", "Steel Leggings", "Grebas de acero","Grebâ d'açero","Grebes d’Acer"),
    STEEL_BOOTS("item.metallics_arts.steel_boots", "Steel Boots", "Botas de acero","Botâ d'açero","Botes d’Acer"),

    // Armaduras de aluminio
    ALUMINUM_HELMET("item.metallics_arts.aluminum_helmet", "Aluminum Helmet", "Casco de aluminio","Câcco d'aluminio","Casc d’Alumini"),
    ALUMINUM_CHESTPLATE("item.metallics_arts.aluminum_chestplate", "Aluminum Chestplate", "Peto de aluminio","Pexera d'aluminio","Pitral d’Alumini"),
    ALUMINUM_LEGGINGS("item.metallics_arts.aluminum_leggings", "Aluminum Leggings", "Grebas de aluminio","Grebâ d'aluminio","Grebes d’Alumini"),
    ALUMINUM_BOOTS("item.metallics_arts.aluminum_boots", "Aluminum Boots", "Botas de aluminio","Botâ d'aluminio","Botes d’Alumini"),

    // Armaduras de ettmetal
    ETTMETAL_HELMET("item.metallics_arts.ettmetal_helmet", "Ettmetal Helmet", "Casco de ettmetal","Câcco d'êttmetâh","Casc d’Ettmetal"),
    ETTMETAL_CHESTPLATE("item.metallics_arts.ettmetal_chestplate", "Ettmetal Chestplate", "Peto de ettmetal","Pexera d'êttmetâh","Pitral d’Ettmetal"),
    ETTMETAL_LEGGINGS("item.metallics_arts.ettmetal_leggings", "Ettmetal Leggings", "Grebas de ettmetal","Grebâ d'êttmetâh","Grebes d’Ettmetal"),
    ETTMETAL_BOOTS("item.metallics_arts.ettmetal_boots", "Ettmetal Boots", "Botas de ettmetal","Botâ d'êttmetâh","Botes d’Ettmetal"),

    // Armaduras de lerasium
    LERASIUM_HELMET("item.metallics_arts.lerasium_helmet", "Lerasium Helmet", "Casco de lerasium","Câcco de leraçiun","Casc de Lerasium"),
    LERASIUM_CHESTPLATE("item.metallics_arts.lerasium_chestplate", "Lerasium Chestplate", "Peto de lerasium","Pexera de leraçiun","Pitral de Lerasium"),
    LERASIUM_LEGGINGS("item.metallics_arts.lerasium_leggings", "Lerasium Leggings", "Grebas de lerasium","Grebâ de leraçiun","Grebes de Lerasium"),
    LERASIUM_BOOTS("item.metallics_arts.lerasium_boots", "Lerasium Boots", "Botas de lerasium","Botâ de leraçiun","Botes de Lerasium"),

    // Armaduras de atium
    ATIUM_HELMET("item.metallics_arts.atium_helmet", "Atium Helmet", "Casco de atium","Câcco d'atiun","Casc d’Atium"),
    ATIUTM_CHESTPLATE("item.metallics_arts.atium_chestplate", "Atium Chestplate", "Peto de atium","Pexera d'atiun","Pitral d’Atium"),
    ATIUM_LEGGINGS("item.metallics_arts.atium_leggings", "Atium Leggings", "Grebas de atium","Grebâ d'atiun","Grebes d’Atium"),
    ATIUM_BOOTS("item.metallics_arts.atium_boots", "Atium Boots", "Botas de atium","Botâ d'atiun","Botes d’Atium"),

    // Armaduras de cobre
    COPPER_HELMET("item.metallics_arts.copper_helmet", "Copper Helmet", "Casco de cobre","Câcco de cobre","Casc de Coure"),
    COPPER_CHESTPLATE("item.metallics_arts.copper_chestplate", "Copper Chestplate", "Peto de cobre","Pexera de cobre","Pitral de Coure"),
    COPPER_LEGGINGS("item.metallics_arts.copper_leggings", "Copper Leggings", "Grebas de cobre","Grebâ de cobre","Grebes de Coure"),
    COPPER_BOOTS("item.metallics_arts.copper_boots", "Copper Boots", "Botas de cobre","Botâ de cobre","Botes de Coure"),

    // Mistcloack
    MISTCLOAK("item.metallics_arts.mistcloak", "Mistcloak", "Capa de bruma","Capa de bruma","Capa de Boira"),
    MISTCLOAK_TOOLTIP("metallics_arts.item.tooltip.mistcloak", "Increase your speed while the mists are visible", "Aumenta tu velocidad mientras las brumas sean visibles","Aumenta tu beloçidá mientrâ la bruma çea biçible","Augmenta la teva velocitat mentre la boira és visible");


    private final String key;
    private final String english;
    private final String spanish;
    private final String andaluz;
    private final String catalan;
}
