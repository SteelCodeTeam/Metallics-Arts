package net.rudahee.metallics_arts.data.enums.implementations.languages.book.sub_division;

import net.rudahee.metallics_arts.MetallicsArts;

public enum SubdivisionData {
    WEAPONS("weapon", "minecraft:textures/item/netherite_sword.png",false,false,false),
    CAFTING("crafting","minecraft:crafting_table",false,false,false),
    ALLOY_FURNACE("alloy_furnace","minecraft:blast_furnace",false,false,false),
    PHYSICAL("physical",MetallicsArts.MOD_ID + ":textures/icons/physical.png",false,true,true),
    SPIRITUAL("spiritual",MetallicsArts.MOD_ID + ":textures/icons/spiritual.png",false,true,false),
    COGNITIVE("cognitive",MetallicsArts.MOD_ID + ":textures/icons/cognitive.png",false,true,true),
    TEMPORAL("temporal",MetallicsArts.MOD_ID + ":textures/icons/temporal.png",false,false,true),
    DIVINE("divine", MetallicsArts.MOD_ID + ":textures/icons/divine.png",false,true,true),
    HYBRID("hybrid",MetallicsArts.MOD_ID + ":textures/icons/hybrid.png",false,true,false),
    ENHANCEMENT("enhancement",MetallicsArts.MOD_ID + ":textures/icons/enhancement.png",false,false,true),
    ALLOMANCY("allomancy",MetallicsArts.MOD_ID + ":textures/icons/enhancement.png",true,false,false),
    FERUCHEMY("feruchemy",MetallicsArts.MOD_ID + ":textures/icons/enhancement.png",true,false,false),
    WELCOME("welcome",MetallicsArts.MOD_ID + ":textures/icons/enhancement.png",true,false,false);
    private final String id;
    private final String icon;
    private final boolean feruchemical;
    private final boolean allomantic;
    private final boolean welcome;

    SubdivisionData(String id, String icon, boolean welcome, boolean feruchemical, boolean allomantic) {
        this.id = id;
        this.icon = icon;
        this.welcome = welcome;
        this.feruchemical = feruchemical;
        this.allomantic = allomantic;
    }

    public String getId() {
        return id;
    }


    public boolean isFeruchemical() {
        return feruchemical;
    }

    public boolean isAllomantic() {
        return allomantic;
    }

    public String getIcon() {
        return icon;
    }

    public boolean isWelcome() {
        return welcome;
    }
}
