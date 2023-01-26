package net.rudahee.metallics_arts.data.enums.implementations.languages.book;

import net.minecraft.world.item.Items;
import net.rudahee.metallics_arts.MetallicsArts;

public enum SubdivisionEntry {
    WEAPONS("weapon","Weapons", "minecraft:textures/item/netherite_sword.png",false,false,"","Sirve para"),
    CAFTING("crafting","Crafting","minecraft:crafting_table",false,false,"","Sirve para"),
    ALLOY_FURNACE("alloy_furnace","Alloy Furnace","minecraft:blast_furnace",false,false,"","Sirver para"),

    PHYSICAL("physical","Physical",MetallicsArts.MOD_ID + ":textures/icons/physical.png",true,true,"","Sirve para"),
    SPIRITUAL("spiritual","Spiritual",MetallicsArts.MOD_ID + ":textures/icons/spiritual.png",true,false,"","Sirve para"),
    COGNITIVE("cognitive","Cognitive",MetallicsArts.MOD_ID + ":textures/icons/cognitive.png",true,true,"","Sirve para"),
    TEMPORAL("temporal","Temporal",MetallicsArts.MOD_ID + ":textures/icons/temporal.png",false,true,"","Sirve para"),
    DIVINE("divine","Divine", MetallicsArts.MOD_ID + ":textures/icons/divine.png",true,true,"","Sirve para"),
    HYBRID("hybrid","Hybrid",MetallicsArts.MOD_ID + ":textures/icons/hybrid.png",true,false,"","Sirve para"),
    ENHANCEMENT("enhancement","Enhancement",MetallicsArts.MOD_ID + ":textures/icons/enhancement.png",false,true,"","Sirve para"),;
    private final String id;
    private final String title;
    private final String icon;
    private final String description;
    private final String textPage;
    private final boolean feruchemical;
    private final boolean allomantic;

    SubdivisionEntry(String id, String title, String icon, boolean feruchemical, boolean allomantic, String description, String textPage) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.textPage = textPage;
        this.feruchemical = feruchemical;
        this.allomantic = allomantic;
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
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

    public String getTextPage() {
        return textPage;
    }
}
