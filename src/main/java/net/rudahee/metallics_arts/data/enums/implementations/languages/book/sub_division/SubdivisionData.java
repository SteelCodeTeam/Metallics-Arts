package net.rudahee.metallics_arts.data.enums.implementations.languages.book.sub_division;

import net.rudahee.metallics_arts.MetallicsArts;

/**
 * This enum contains the auxiliary information about the subdivisions of the book.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public enum SubdivisionData {
    WEAPONS("weapon", "minecraft:textures/item/netherite_sword.png",false,false,false),
    CAFTING("crafting","minecraft:crafting_table",false,false,false),
    ALLOY_FURNACE("alloy_furnace","minecraft:blast_furnace",false,false,false),

    PHYSICAL_ALLOMANCY("physical_allomancy",MetallicsArts.MOD_ID + ":textures/icons/physical.png",false,false,true),
    MENTAL("mental",MetallicsArts.MOD_ID + ":textures/icons/cognitive.png",false,false,true),
    TEMPORAL("temporal",MetallicsArts.MOD_ID + ":textures/icons/temporal.png",false,false,true),
    ENHANCEMENT("enhancement",MetallicsArts.MOD_ID + ":textures/icons/enhancement.png",false,false,true),
    DIVINE_ALLOMANCY("divine_allomancy", MetallicsArts.MOD_ID + ":textures/icons/divine.png",false,false,true),
    TUTORIAL_ALLOMANCY("tutorial_allomancy","minecraft:textures/item/compass_19.png",false,false,true),

    PHYSICAL_FERUCHEMY("physical_feruchemy",MetallicsArts.MOD_ID + ":textures/icons/physical.png",false,true,false),
    SPIRITUAL("spiritual",MetallicsArts.MOD_ID + ":textures/icons/spiritual.png",false,true,false),
    COGNITIVE("cognitive",MetallicsArts.MOD_ID + ":textures/icons/cognitive.png",false,true,false),
    DIVINE_FERUCHEMY("divine_feruchemy", MetallicsArts.MOD_ID + ":textures/icons/divine.png",false,true,false),
    HYBRID("hybrid",MetallicsArts.MOD_ID + ":textures/icons/hybrid.png",false,true,false),
    TUTORIAL_FERUCHEMY("tutorial_feruchemy","minecraft:textures/item/compass_19.png",false,true,false),

    ALLOMANCY("allomancy",MetallicsArts.MOD_ID + ":textures/icons/enhancement.png",true,false,false),
    FERUCHEMY("feruchemy",MetallicsArts.MOD_ID + ":textures/icons/enhancement.png",true,false,false),
    WELCOME("welcome",MetallicsArts.MOD_ID + ":textures/icons/enhancement.png",true,false,false),
    LEATHER("leather","minecraft:textures/item/leather_chestplate.png",false,false,false);

    private final String id;
    private final String icon;
    private final boolean feruchemical;
    private final boolean allomantic;
    private final boolean welcome;

    /**
     * Constructs a new subdivision data type.
     *
     * @param id the id of the subdivision data type.
     * @param icon the icon path of the subdivision data type.
     * @param welcome if the subdivision data type is a welcome message.
     * @param feruchemical if the subdivision data type is feruchemical.
     * @param allomantic if the subdivision data type is allomantic.
     */
    SubdivisionData(String id, String icon, boolean welcome, boolean feruchemical, boolean allomantic) {
        this.id = id;
        this.icon = icon;
        this.welcome = welcome;
        this.feruchemical = feruchemical;
        this.allomantic = allomantic;
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
     * Returns whether the subdivision data type is feruchemical.
     *
     * @return true if the subdivision data type is feruchemical, false otherwise.
     */
    public boolean isFeruchemical() {
        return feruchemical;
    }

    /**
     * Returns whether the subdivision data type is allomantic.
     *
     * @return true if the subdivision data type is allomantic, false otherwise.
     */
    public boolean isAllomantic() {
        return allomantic;
    }

    /**
     * Returns the icon path of the crafting material.
     *
     * @return the icon path of the crafting material.
     */
    public String getIcon() {
        return icon;
    }

    /**
     * Returns whether this object is considered a welcome object.
     *
     * @return true if the object is a welcome object, false otherwise.
     */
    public boolean isWelcome() {
        return welcome;
    }
}
