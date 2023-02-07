package net.rudahee.metallics_arts.data.enums.implementations.languages;

public enum MetalAuxiliaryInfo {
    IRON("iron",false,true,false,true,false, false),
    STEEL("steel",true,false,false,false,false, false),
    TIN("tin",false,false,false,true,true, false),
    PEWTER("pewter",true,false,false,false,false, false),
    ZINC("zinc",false,false,false,true,true, false),
    BRASS("brass",true,false,false,false,false, false),
    COPPER("copper",false,true,false,true,false, false),
    BRONZE("bronze",true,false,false,false,false, false),
    ALUMINUM("aluminum",false,false,false,true,true, false),
    DURALUMIN("duralumin",true,false,false,false,false, false),
    CHROMIUM("chromium",false,false,false,true,true, false),
    NICROSIL("nicrosil",true,false,false,false,false, false),
    GOLD("Gold",false,true,false,false,false, false),
    ELECTRUM("electrum",true,false,false,false,false, false),
    CADMIUM("cadmium",false,false,false,true,true, false),
    BENDALLOY("bendalloy",true,false,false,false,false, false),

    ATIUM("atium", false, false, false,false,false, true),
    MALATIUM("malatium", true, false, false,false,false, true),
    LERASIUM("lerasium", false, false, false,false,false, true),
    ETTMETAL("ettmetal", false, false, false,false,false, true),

    SILVER( "silver",false,false, true,true,true, false),
    LEAD("lead",false,false, true,true,true, false),
    NICKEL("nickel",false,false, true,true,true, false);

    private final String id;
    private final boolean isAlloy;
    private final boolean isVanilla;
    private final boolean isOnlyForAlloys;
    private final boolean appearsInStone;
    private final boolean appearsInDeepslate;
    private final boolean isDivine;

    MetalAuxiliaryInfo(String id, boolean isAlloy, boolean isVanilla, boolean isOnlyForAlloys, boolean appearsInStone, boolean appearsInDeepslate, boolean isDivine) {
        this.id = id;
        this.isAlloy = isAlloy;
        this.isVanilla = isVanilla;
        this.isOnlyForAlloys = isOnlyForAlloys;
        this.appearsInStone = appearsInStone;
        this.appearsInDeepslate = appearsInDeepslate;
        this.isDivine = isDivine;
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

    public boolean isDivine() {
        return isDivine;
    }


}
