package net.rudahee.metallics_arts.data.enums.implementations.languages;

public enum MetalAuxiliaryInfo {
    IRON("iron",false,true,false,false,false),
    STEEL("steel",true,false,false,false,false),
    TIN("tin",false,false,false,false,false),
    PEWTER("pewter",true,false,false,false,false),
    ZINC("zinc",false,false,false,false,false),
    BRASS("brass",true,false,false,false,false),
    COPPER("copper",false,true,false,false,false),
    BRONZE("bronze",true,false,false,false,false),
    ALUMINUM("aluminum",false,false,false,false,false),
    DURALUMIN("duralumin",true,false,false,false,false),
    CHROMIUM("chromium",false,false,false,false,false),
    NICROSIL("nicrosil",true,false,false,false,false),
    GOLD("Gold",false,true,false,false,false),
    ELECTRUM("electrum",true,false,false,false,false),
    CADMIUM("cadmium",false,false,false,false,false),
    BENDALLOY("bendalloy",true,false,false,false,false),

    SILVER( "silver",false,false, true,false,false),
    LEAD("lead",false,false, true,false,false),
    NICKEL("nickel",false,false, true,false,false);

    private final String id;
    private final boolean isAlloy;
    private final boolean isVanilla;
    private final boolean isOnlyForAlloys;

    private final boolean appearsInStone;
    private final boolean appearsInDeepslate;

    MetalAuxiliaryInfo(String id, boolean isAlloy, boolean isVanilla, boolean isOnlyForAlloys, boolean appearsInStone, boolean appearsInDeepslate) {
        this.id = id;
        this.isAlloy = isAlloy;
        this.isVanilla = isVanilla;
        this.isOnlyForAlloys = isOnlyForAlloys;
        this.appearsInStone = appearsInStone;
        this.appearsInDeepslate = appearsInDeepslate;
    }

    public String getId() {
        return this.id;
    }

    public boolean isAlloy() {
        return isAlloy;
    }

    public boolean isVanilla() {
        return isVanilla;
    }

    public boolean isOnlyForAlloys() {
        return isOnlyForAlloys;
    }

    public boolean isAppearsInStone() {
        return appearsInStone;
    }

    public boolean isAppearsInDeepslate() {
        return appearsInDeepslate;
    }
}
