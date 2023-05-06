package net.rudahee.metallics_arts.data.enums.implementations.languages.book.multi_craft;

import net.rudahee.metallics_arts.MetallicsArts;

/**
 * This enum contains auxiliary information about the crafting of the book entries.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public enum MultiCraftData {
    VIALS("vials", MetallicsArts.MOD_ID + ":textures/item/consumables/large_vial_fill.png"),
    RINGS("rings",MetallicsArts.MOD_ID + ":textures/item/metal_mind/gold_electrum_ring.png"),
    BANDS("bands",MetallicsArts.MOD_ID + ":textures/item/metal_mind/gold_electrum_band.png"),
    SPIKES("spikes",MetallicsArts.MOD_ID + ":textures/item/spikes/gold_spike.png"),
    ICONS("icons",MetallicsArts.MOD_ID + ":textures/item/symbols/allomantic_symbols/iron_symbol.png"),
    ALLOYS("alloys",MetallicsArts.MOD_ID + ":textures/item/metal/ingot/cadmium_ingot.png"),
    PATTERNS("patterns",MetallicsArts.MOD_ID + ":textures/item/pattern/a_aluminum_pattern.png"),
    CORES("cores",MetallicsArts.MOD_ID + ":textures/item/cores/core_aluminum.png"),
    ALUMINUM_ARMOR("aluminum_armor",MetallicsArts.MOD_ID + ":textures/item/armors/aluminum_chestplate.png"),
    STEEL_ARMOR("steel_armor",MetallicsArts.MOD_ID + ":textures/item/armors/steel_chestplate.png");

    private final String id;
    private final String icon;

    /**
     * Constructs a new MultiCraftData object with the given id and icon.
     *
     * @param id the id of the crafting material.
     * @param icon the icon path of the crafting material.
     */
    MultiCraftData(String id, String icon) {
        this.id = id;
        this.icon = icon;
    }

    /**
     * Returns the id of the crafting material.
     *
     * @return the id of the crafting material.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the icon path of the crafting material.
     *
     * @return the icon path of the crafting material.
     */
    public String getIcon() {
        return icon;
    }
}
