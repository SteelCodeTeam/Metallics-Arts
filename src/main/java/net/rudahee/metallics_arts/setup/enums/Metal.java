package net.rudahee.metallics_arts.setup.enums;

public enum Metal {
    STEEL("steel", "STEEL"),
    TIN("tin", "TIN"),
    PEWTER("pewter", "PEWTER"),
    BRONZE("bronze", "BRONZE"),
    COPPER("copper", "COPPER"),
    ZINC("zinc", "ZINC"),
    BRASS("brass", "BRASS"),
    ELECTRUM("electrum", "ELECTRUM"),
    CADMIUM("cadmium", "CADMIUM"),
    BENDALLOY("bendalloy", "BENDALLOY"),
    ALUMINUM("aluminum", "ALUMINUM"),
    DURALUMIN("duralumin", "DURALUMIN"),
    CHROMIUM("chromium", "CHROMIUM"),
    NICROSIL("nicrosil", "NICROSIL");


    private final String metalNameLower;
    private final String metalNameUpper;

    Metal(String metalNameLower, String metalNameUpper) {
        this.metalNameLower = metalNameLower;
        this.metalNameUpper = metalNameUpper;
    }

    public String getMetalNameLower() {
        return metalNameLower;
    }

    public String getMetalNameUpper() {
        return metalNameUpper;
    }
}
