package net.rudahee.metallics_arts.setup.enums.langs;


import net.rudahee.metallics_arts.setup.enums.metals.IMetal;

public enum MetalSpanish implements IMetal {
    IRON("hierro", "HIERRO", "Hierro"),
    STEEL("acero", "ACERO", "Acero"),
    TIN("estaño", "ESTAÑO", "Estaño"),
    PEWTER("peltre", "PELTRE", "Peltre"),
    ZINC("zinc", "ZINC", "Zinc"),
    BRASS("laton", "LATON", "Laton"),
    COPPER("cobre", "COBRE", "Cobre"),
    BRONZE("bronce", "BRONCE", "Bronce"),
    ALUMINUM("aluminio", "ALUMINIO", "Aluminio"),
    DURALUMIN("duralumin", "DURALUMIN", "Duralumin"),
    CHROMIUM("chromo", "CHROMO", "Chromo"),
    NICROSIL("nicrosil", "NICROSIL", "Nicrosil"),
    GOLD("oro", "ORO", "Oro"),
    ELECTRUM("electrum", "ELECTRUM", "Electrum"),
    CADMIUM("cadmio", "CADMIO", "Cadmio"),
    BENDALLOY("bendaleo", "BENDALEO", "Bendaleo"),
    ATIUM("atium", "ATIUM", "Atium"),
    MALATIUM("malatium", "MALATIUM", "Malatium"),
    LERASIUM("lerasium", "LERASIUM", "Lerasium"),
    ETTMETAL("ettmetal", "ETTMETAL", "Ettmetal");
    private final String metalNameLower;
    private final String metalNameUpper;
    private final String metalName;

    MetalSpanish (String metalNameLower, String metalNameUpper, String metalName) {
        this.metalNameLower = metalNameLower;
        this.metalNameUpper = metalNameUpper;
        this.metalName = metalName;
    }

    @Override
    public String getMetalNameLower() {
        return this.metalNameLower;
    }

    @Override
    public String getMetalNameUpper() {
        return this.metalNameUpper;
    }

    public String getMetalName() {
        return this.metalName;
    }
}
