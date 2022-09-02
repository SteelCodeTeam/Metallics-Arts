package net.rudahee.metallics_arts.setup.enums.metals;

public enum Metal implements IMetal {
    STEEL("steel", "STEEL",             true, true, true, true, null, null),
    TIN("tin", "TIN",                   true, true, true, false, false, true),
    PEWTER("pewter", "PEWTER",          true, true, true, true, null, null),
    BRONZE("bronze", "BRONZE",          true, true, true, true, null, null),
    ZINC("zinc", "ZINC",                true, true, true, false, false, true),
    BRASS("brass", "BRASS",             true, true, true, true, null, null),
    ELECTRUM("electrum", "ELECTRUM",    true, true, true, true, null, null),
    CADMIUM("cadmium", "CADMIUM",       true, true, true, false, true, false),
    BENDALLOY("bendalloy", "BENDALLOY", true, true, true, true, null, null),
    ALUMINUM("aluminum", "ALUMINUM",    true, true, true, false, true, true),
    DURALUMIN("duralumin", "DURALUMIN", true, true, true, true, null, null),
    CHROMIUM("chromium", "CHROMIUM",    true, true, true, false, true, false),
    NICROSIL("nicrosil", "NICROSIL",    true, true, true, true, null, null),
    SILVER("silver", "SILVER",          false, false, false, false, true, false),
    LEAD("lead", "LEAD",                false, false, false, false, true, true),
    NICKEL("nickel", "NICKEL",          false, false, false, false, true, false);

    private final String metalNameLower;
    private final String metalNameUpper;
    private final Boolean allomantic;
    private final Boolean feruchemic;
    private final Boolean hemalurgic;
    private final Boolean alloy;

    private final Boolean deepslate;

    private final Boolean stone;

    Metal(String metalNameLower, String metalNameUpper, Boolean allomantic, Boolean feruchemic, Boolean hemalurgic, Boolean alloy, Boolean deepslate, Boolean stone) {
        this.metalNameLower = metalNameLower;
        this.metalNameUpper = metalNameUpper;
        this.allomantic = allomantic;
        this.feruchemic = feruchemic;
        this.hemalurgic = hemalurgic;
        this.alloy = alloy;
        this.deepslate = deepslate;
        this.stone = stone;
    }

    public String getMetalNameLower() {
        return metalNameLower;
    }

    public String getMetalNameUpper() {
        return metalNameUpper;
    }

    public Boolean isAllomantic() { return allomantic; }

    public Boolean isFeruchemic() { return feruchemic; }

    public Boolean isHemalurgic() { return hemalurgic; }

    public Boolean isAlloy() { return alloy; }

    public Boolean isDeepslate() {
        return deepslate;
    }

    public Boolean isStone() {
        return stone;
    }

}
