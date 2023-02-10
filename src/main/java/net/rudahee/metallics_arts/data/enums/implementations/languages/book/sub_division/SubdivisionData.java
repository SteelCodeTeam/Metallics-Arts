package net.rudahee.metallics_arts.data.enums.implementations.languages.book.sub_division;

import net.rudahee.metallics_arts.MetallicsArts;

public enum SubdivisionData {
    WEAPONS("weapon", "minecraft:textures/item/netherite_sword.png",false,false),
    CAFTING("crafting","minecraft:crafting_table",false,false),
    ALLOY_FURNACE("alloy_furnace","minecraft:blast_furnace",false,false),
    PHYSICAL("physical",MetallicsArts.MOD_ID + ":textures/icons/physical.png",true,true),
    SPIRITUAL("spiritual",MetallicsArts.MOD_ID + ":textures/icons/spiritual.png",true,false),
    COGNITIVE("cognitive",MetallicsArts.MOD_ID + ":textures/icons/cognitive.png",true,true),
    TEMPORAL("temporal",MetallicsArts.MOD_ID + ":textures/icons/temporal.png",false,true),
    DIVINE("divine", MetallicsArts.MOD_ID + ":textures/icons/divine.png",true,true),
    HYBRID("hybrid",MetallicsArts.MOD_ID + ":textures/icons/hybrid.png",true,false),
    ENHANCEMENT("enhancement",MetallicsArts.MOD_ID + ":textures/icons/enhancement.png",false,true);
    private final String id;
    private final String icon;
    private final boolean feruchemical;
    private final boolean allomantic;

    SubdivisionData(String id, String icon, boolean feruchemical, boolean allomantic) {
        this.id = id;
        this.feruchemical = feruchemical;
        this.allomantic = allomantic;
        this.icon = icon;
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

}
