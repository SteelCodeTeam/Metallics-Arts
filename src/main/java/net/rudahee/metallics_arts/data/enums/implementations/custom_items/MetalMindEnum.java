package net.rudahee.metallics_arts.data.enums.implementations.custom_items;


import net.minecraft.world.item.Item;

public enum MetalMindEnum {
    IRON_STEEL("iron","steel", null,null,true,false),
    TIN_PEWTER("tin", "pewter",null,null,false,false),
    COPPER_BRONZE("copper","bronze", null,null,true,false),
    ZINC_BRASS("zinc", "brass",null,null,false,false),
    CHROMIUM_NICROSIL("chromium", "nicrosil",null,null,false,false),
    ALUMINUM_DURALUMIN("aluminum", "duralumin",null,null,false,false),
    CADMIUM_BENDALLOY("cadmium", "bendalloy",null,null,false,false),
    GOLD_ELECTRUM( "gold","electrum",null,null,true,false),
    ATIUM_MALTIUM("atium", "malatium",null,null,false,true),
    LERASIUM_ETTMETAL("lerasium", "ettmetal",null,null,false,true);

    private final String firstMetal;
    private final String secondMetal;
    private final boolean vanilla;
    private final boolean gems;
    private Item band;
    private Item ring;

    MetalMindEnum(String firstMetal, String secondMetal, Item band, Item ring, boolean vanilla, boolean gems) {
        this.firstMetal = firstMetal;
        this.secondMetal = secondMetal;
        this.band = band;
        this.ring = ring;
        this.vanilla = vanilla;
        this.gems = gems;
    }

    public String getFirstMetal() {
        return firstMetal;
    }

    public String getSecondMetal() {
        return secondMetal;
    }

    public Item getBand() {
        return band;
    }

    public Item getRing() {
        return ring;
    }

    public boolean isVanilla() {
        return vanilla;
    }

    public boolean isGems() {
        return gems;
    }

    public void setBand(Item band){
        this.band = band;
    }

    public void setRing(Item ring){
        this.ring = ring;
    }
}
