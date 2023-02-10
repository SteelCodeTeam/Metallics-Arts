package net.rudahee.metallics_arts.data.enums.implementations.languages.book.multi_craft;

import net.rudahee.metallics_arts.MetallicsArts;

public enum MultiCraftData {
    VIALS("vials", MetallicsArts.MOD_ID + ":textures/item/consumables/large_vial_fill.png"),
    RINGS("rings",MetallicsArts.MOD_ID + ":textures/item/metal_mind/gold_electrum_ring.png"),
    BANDS("bands",MetallicsArts.MOD_ID + ":textures/item/metal_mind/gold_electrum_band.png"),
    SPIKES("spikes",MetallicsArts.MOD_ID + ":textures/item/spikes/gold_spike.png"),
    ICONS("icons",MetallicsArts.MOD_ID + ":textures/item/symbols/allomantic_symbols/iron_symbol.png"),
    ALLOYS("alloys",MetallicsArts.MOD_ID + ":textures/item/metal/ingot/cadmium_ingot.png"),
    PATTERNS("patterns",MetallicsArts.MOD_ID + ":textures/item/pattern/a_aluminum_pattern.png");

    private final String id;
    private final String icon;

    MultiCraftData(String id, String icon) {
        this.id = id;
        this.icon = icon;
    }

    public String getId() {
        return id;
    }


    public String getIcon() {
        return icon;
    }
}
