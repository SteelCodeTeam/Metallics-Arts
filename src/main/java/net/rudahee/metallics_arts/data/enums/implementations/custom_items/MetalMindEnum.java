package net.rudahee.metallics_arts.data.enums.implementations.custom_items;


import net.minecraft.world.item.Item;

/**
 * MetalMindEnum is an enum class that represents different combinations of metals and their associated items.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
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

    /**
     *  Constructor for MetalMindEnum.
     *
     *  @param firstMetal the first metal in the combination.
     *  @param secondMetal the second metal in the combination.
     *  @param band the associated band item.
     *  @param ring the associated ring item.
     *  @param vanilla whether the combination is present in the vanilla game.
     *  @param gems whether the combination involves a gem.
     */
    MetalMindEnum(String firstMetal, String secondMetal, Item band, Item ring, boolean vanilla, boolean gems) {
        this.firstMetal = firstMetal;
        this.secondMetal = secondMetal;
        this.band = band;
        this.ring = ring;
        this.vanilla = vanilla;
        this.gems = gems;
    }

    /**
     * Get the first metal in the combination.
     *
     * @return the first metal.
     */
    public String getFirstMetal() {
        return firstMetal;
    }

    /**
     * Get the second metal in the combination.
     *
     * @return the second metal.
     */
    public String getSecondMetal() {
        return secondMetal;
    }

    /**
     * Get the second metal in the combination.
     *
     * @return the second metal.
     */
    public Item getBand() {
        return band;
    }

    /**
     * Get the associated ring item.
     *
     * @return the associated ring item.
     */
    public Item getRing() {
        return ring;
    }

    /**
     *  Check if the combination is present in the vanilla game.
     *
     *  @return true if the combination is present in the vanilla game, false otherwise.
     */
    public boolean isVanilla() {
        return vanilla;
    }

    /**
     * Check if the combination involves a gem.
     *
     * @return true if the combination involves a gem, false otherwise.
     */
    public boolean isGems() {
        return gems;
    }

    /**
     * Set the associated band item.
     *
     * @param band the new associated band item.
     */
    public void setBand(Item band){
        this.band = band;
    }
    /**
     * Set the associated ring item.
     *
     * @param ring the new associated ring item.
     */
    public void setRing(Item ring){
        this.ring = ring;
    }

}
