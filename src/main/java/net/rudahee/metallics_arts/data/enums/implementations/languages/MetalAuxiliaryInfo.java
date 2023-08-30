package net.rudahee.metallics_arts.data.enums.implementations.languages;


/**
 * Enum class for auxiliary information about metals.
 * It contains information about metal types, such as their id, whether they are alloys or not,
 * whether they are vanilla metals, only for alloys, appears in stone, appears in deepslate, or is divine.
 *
 * @author SteelCode Team
 * @since 1.5.1
 *
 */
public enum MetalAuxiliaryInfo {
    IRON("iron",false,true,false,true,true, false),
    STEEL("steel",false,false,false,false,false, false),
    TIN("tin",false,false,false,true,false, false),
    PEWTER("pewter",false,false,false,false,false, false),
    ZINC("zinc",false,false,false,true,false, false),
    BRASS("brass",false,false,false,false,false, false),
    COPPER("copper",false,true,false,true,true, false),
    BRONZE("bronze",false,false,false,false,false, false),
    ALUMINUM("aluminum",false,false,false,true,true, false),
    DURALUMIN("duralumin",false,false,false,false,false, false),
    CHROMIUM("chromium",false,false,false,false,true, false),
    NICROSIL("nicrosil",false,false,false,false,false, false),
    GOLD("gold",false,true,false,true,true, false),
    ELECTRUM("electrum",false,false,false,false,false, false),
    CADMIUM("cadmium",false,false,false,false,true, false),
    BENDALLOY("bendalloy",false,false,false,false,false, false),

    ATIUM("atium", false, false, false,false,false, true),
    MALATIUM("malatium", false, false, false,false,false, true),
    LERASIUM("lerasium", false, false, false,false,false, true),
    ETTMETAL("ettmetal", false, false, false,false,false, true),

    SILVER( "silver",false,false, true,false,true, false),
    LEAD("lead",false,false, true,true,true, false),
    NICKEL("nickel",false,false, true,false,true, false);

    private final String id;
    private final boolean isAlloy;
    private final boolean isVanilla;
    private final boolean isOnlyForAlloys;
    private final boolean appearsInStone;
    private final boolean appearsInDeepslate;
    private final boolean isDivine;

    /**
     * Constructs a new metal with the given id and properties.
     *
     * @param id the id of the metal
     * @param isAlloy whether the metal is an alloy or not
     * @param isVanilla whether the metal is a vanilla metal or not
     * @param isOnlyForAlloys whether the metal is only used for alloys or not
     * @param appearsInStone whether the metal appears in stone or not
     * @param appearsInDeepslate whether the metal appears in deepslate or not
     * @param isDivine whether the metal is a divine metal or not
     */
    MetalAuxiliaryInfo(String id, boolean isAlloy, boolean isVanilla, boolean isOnlyForAlloys, boolean appearsInStone, boolean appearsInDeepslate, boolean isDivine) {
        this.id = id;
        this.isAlloy = isAlloy;
        this.isVanilla = isVanilla;
        this.isOnlyForAlloys = isOnlyForAlloys;
        this.appearsInStone = appearsInStone;
        this.appearsInDeepslate = appearsInDeepslate;
        this.isDivine = isDivine;
    }

    /**
     * Returns the identifier of the metal auxiliary information.
     *
     * @return The identifier of the metal auxiliary information.
     */
    public String getId() {
        return this.id;
    }
    /**
     * Returns true if the metal auxiliary information is an alloy, false otherwise.
     *
     * @return True if the metal auxiliary information is an alloy, false otherwise.
     */
    public boolean isAlloy() {
        return isAlloy;
    }
    /**
     * Returns true if the metal auxiliary information is a vanilla metal, false otherwise.
     *
     * @return True if the metal auxiliary information is a vanilla metal, false otherwise.
     */
    public boolean isVanilla() {
        return isVanilla;
    }
    /**
     * Returns true if the metal auxiliary information is only used for alloys, false otherwise.
     *
     * @return True if the metal auxiliary information is only used for alloys, false otherwise.
     */
    public boolean isOnlyForAlloys() {
        return isOnlyForAlloys;
    }
    /**
     * Returns true if the metal auxiliary information appears in stone, false otherwise.
     *
     * @return True if the metal auxiliary information appears in stone, false otherwise.
     */
    public boolean isAppearsInStone() {
        return appearsInStone;
    }
    /**
     * Returns true if the metal auxiliary information appears in deepslate, false otherwise.
     *
     * @return True if the metal auxiliary information appears in deepslate, false otherwise.
     */
    public boolean isAppearsInDeepslate() {
        return appearsInDeepslate;
    }
    /**
     * Returns true if the metal auxiliary information is divine, false otherwise.
     *
     * @return True if the metal auxiliary information is divine, false otherwise.
     */
    public boolean isDivine() {
        return isDivine;
    }


}