package net.rudahee.metallics_arts.data.enums.implementations.languages;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MagicItemsTranslation {

    // Spikes
    IRON_SPIKE("item.metallics_arts.iron_spike", "Iron Spike", "Clavo de hierro"),
    STEEL_SPIKE("item.metallics_arts.steel_spike", "Steel Spike", "Clavo de acero"),
    TIN_SPIKE("item.metallics_arts.tin_spike", "Tin Spike", "Clavo de estaño"),
    PEWTER_SPIKE("item.metallics_arts.pewter_spike", "Pewter Spike", "Clavo de peltre"),
    COPPER_SPIKE("item.metallics_arts.copper_spike", "Copper Spike", "Clavo de cobre"),
    BRONZE_SPIKE("item.metallics_arts.bronze_spike", "Bronze Spike", "Clavo de bronce"),
    ZINC_SPIKE("item.metallics_arts.zinc_spike", "Zinc Spike", "Clavo de zinc"),
    BRASS_SPIKE("item.metallics_arts.brass_spike", "Brass Spike", "Clavo de latón"),
    CHROMIUM_SPIKE("item.metallics_arts.chromium_spike", "Chromium Spike", "Clavo de cromo"),
    NICROSIL_SPIKE("item.metallics_arts.nicrosil_spike", "Nicrosil Spike", "Clavo de nicrosil"),
    ALUMINUM_SPIKE("item.metallics_arts.aluminum_spike", "Aluminum Spike", "Clavo de aluminio"),
    DURALUMIN_SPIKE("item.metallics_arts.duralumin_spike", "Duralumin Spike", "Clavo de duraluminio"),
    CADMIUM_SPIKE("item.metallics_arts.cadmium_spike", "Cadmium Spike", "Clavo de cadmio"),
    BENDALLOY_SPIKE("item.metallics_arts.bendalloy_spike", "Bendalloy Spike", "Clavo de bendaleo"),
    ELECTRUM_SPIKE("item.metallics_arts.electrum_spike", "Electrum Spike", "Clavo de electro"),
    GOLD_SPIKE("item.metallics_arts.gold_spike", "Gold Spike", "Clavo de oro"),
    ATIUM_SPIKE("item.metallics_arts.atium_spike", "Atium Spike", "Clavo de atium"),
    MALATIUM_SPIKE("item.metallics_arts.malatium_spike", "Malatium Spike", "Clavo de malatium"),
    LERASIUM_SPIKE("item.metallics_arts.lerasium_spike", "Lerasium Spike", "Clavo de lerasium"),
    ETTMETAL_SPIKE("item.metallics_arts.ettmetal_spike", "Ettmetal Spike", "Clavo de ettmetal"),

    // Vials
    LARGE_VIAL("item.metallics_arts.large_vial", "Large Vial", "Vial alomantico grande"),
    SMALL_VIAL("item.metallics_arts.small_vial", "Small Vial", "Vial alomantico pequeño"),

    // Bands
    BAND_ALUMINUM_DURALUMIN("item.metallics_arts.band_aluminum_duralumin", "Band of Aluminum and Duralumin", "Brazal de aluminio y duraluminio"),
    BAND_ATIUM_MALATIUM("item.metallics_arts.band_atium_malatium", "Band of Atium and Malatium", "Brazal de atium y malatium"),
    BAND_CADMIUM_BENDALLOY("item.metallics_arts.band_cadmium_bendalloy", "Band of Cadmium and Bendalloy", "Brazal de cadmio y bendaloy"),
    BAND_CHROMIUM_NICROSIL("item.metallics_arts.band_chromium_nicrosil", "Band of Chromium and Nicrosil", "Brazal de cromo y nicrosil"),
    BAND_COPPER_BRONZE("item.metallics_arts.band_copper_bronze", "Band of Copper and Bronze", "Brazal de cobre y bronce"),
    BAND_GOLD_ELECTRUM("item.metallics_arts.band_gold_electrum", "Band of Gold and Electrum", "Brazal de oro y electro"),
    BAND_LERASIUM_ETTMETAL("item.metallics_arts.band_lerasium_ettmetal", "Band of Lerasium and Ettmetal", "Brazal de lerasium y ettmetal"),
    BAND_TIN_PEWTER("item.metallics_arts.band_tin_pewter", "Band of Tin and Pewter", "Brazal de estaño y peltre"),
    BAND_IRON_STEEL("item.metallics_arts.band_iron_steel", "Band of Iron and Steel", "Brazal de hierro y acero"),
    BAND_ZINC_BRASS("item.metallics_arts.band_zinc_brass", "Band of Zinc and Brass", "Brazal de zinc y latón"),

    // Rings
    RING_ALUMINUM_DURALUMIN("item.metallics_arts.ring_aluminum_duralumin", "Ring of Aluminum and Duralumin", "Anillo de aluminio y duraluminio"),
    RING_ATIUM_MALATIUM("item.metallics_arts.ring_atium_malatium", "Ring of Atium and Malatium", "Anillo de atium y malatium"),
    RING_CADMIUM_BENDALLOY("item.metallics_arts.ring_cadmium_bendalloy", "Ring of Cadmium and Bendalloy", "Anillo de cadmio y bendaloy"),
    RING_CHROMIUM_NICROSIL("item.metallics_arts.ring_chromium_nicrosil", "Ring of Chromium and Nicrosil", "Anillo de cromo y nicrosil"),
    RING_COPPER_BRONZE("item.metallics_arts.ring_copper_bronze", "Ring of Copper and Bronze", "Anillo de cobre y bronce"),
    RING_GOLD_ELECTRUM("item.metallics_arts.ring_gold_electrum", "Ring of Gold and Electrum", "Anillo de oro y electro"),
    RING_LERASIUM_ETTMETAL("item.metallics_arts.ring_lerasium_ettmetal", "Ring of Lerasium and Ettmetal", "Anillo de lerasium y ettmetal"),
    RING_TIN_PEWTER("item.metallics_arts.ring_tin_pewter", "Ring of Tin and Pewter", "Anillo de estaño y peltre"),
    RING_IRON_STEEL("item.metallics_arts.ring_iron_steel", "Ring of Iron and Steel", "Anillo de hierro y acero"),
    RING_ZINC_BRASS("item.metallics_arts.ring_zinc_brass", "Ring of Zinc and Brass", "Anillo de zinc y latón");

    private final String key;
    private final String english;
    private final String spanish;
}