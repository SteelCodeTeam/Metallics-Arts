package net.rudahee.metallics_arts.setup.enums.metals;

public enum Metal implements IMetal {
    STEEL("steel", "STEEL", true, true, true, true),
    TIN("tin", "TIN", true, true, true, false),
    PEWTER("pewter", "PEWTER", true, true, true, true),
    COPPER("copper", "COPPER", true, true, true, false),
    BRONZE("bronze", "BRONZE", true, true, true, true),
    ZINC("zinc", "ZINC", true, true, true, false),
    BRASS("brass", "BRASS", true, true, true, true),
    ELECTRUM("electrum", "ELECTRUM", true, true, true, true),
    CADMIUM("cadmium", "CADMIUM", true, true, true, false),
    BENDALLOY("bendalloy", "BENDALLOY", true, true, true, true),
    ALUMINUM("aluminum", "ALUMINUM", true, true, true, false),
    DURALUMIN("duralumin", "DURALUMIN", true, true, true, true),
    CHROMIUM("chromium", "CHROMIUM", true, true, true, false),
    NICROSIL("nicrosil", "NICROSIL", true, true, true, true),

    SILVER("silver", "SILVER", false, false, false, false),
    LEAD("lead", "LEAD", false, false, false, false),
    NICKEL("nickel", "NICKEL", false, false, false, false);

    private final String metalNameLower;
    private final String metalNameUpper;
    private final Boolean allomantic;
    private final Boolean feruchemic;
    private final Boolean hemalurgic;
    private final Boolean alloy;

    Metal(String metalNameLower, String metalNameUpper, Boolean allomantic, Boolean feruchemic, Boolean hemalurgic, Boolean alloy) {
        this.metalNameLower = metalNameLower;
        this.metalNameUpper = metalNameUpper;
        this.allomantic = allomantic;
        this.feruchemic = feruchemic;
        this.hemalurgic = hemalurgic;
        this.alloy = alloy;
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

}
