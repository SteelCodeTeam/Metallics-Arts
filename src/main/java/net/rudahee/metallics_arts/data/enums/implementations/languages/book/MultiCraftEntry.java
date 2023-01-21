package net.rudahee.metallics_arts.data.enums.implementations.languages.book;

import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.languages.CTW;

public enum MultiCraftEntry {
    VIALS("vials","Vilas","", MetallicsArts.MOD_ID + ":textures/item/consumables/large_vial_fill.png"),
    RINGS("rings","Rings","",MetallicsArts.MOD_ID + ":textures/item/metal_mind/gold_electrum_ring.png"),
    BANDS("bands","Bands","",MetallicsArts.MOD_ID + ":textures/item/metal_mind/gold_electrum_band.png"),
    SPIKES("spikes","Spikes","",MetallicsArts.MOD_ID + ":textures/item/spikes/gold_spike.png"),
    ICONS("icons","Icons","",MetallicsArts.MOD_ID + ":textures/item/symbols/allomantic_symbols/iron_symbol.png"),
    ALLOYS("alloys","Alloys","",MetallicsArts.MOD_ID + ":textures/item/metal/ingot/cadmium_ingot.png"),

    PATTERNS("patterns","Patterns","",MetallicsArts.MOD_ID + ":textures/item/pattern/a_aluminum_pattern.png");

    private final String id;
    private final String title;
    private final String presentation;
    private final String icon;

    MultiCraftEntry(String id, String title, String presentation, String icon) {
        this.id = id;
        this.title = title;
        this.presentation = presentation;
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPresentation() {
        return presentation;
    }

    public String getIcon() {
        return icon;
    }
}
