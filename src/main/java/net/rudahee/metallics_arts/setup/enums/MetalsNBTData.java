package net.rudahee.metallics_arts.setup.enums;

public enum MetalsNBTData implements IMetal, IGems {
    STEEL("steel", "STEEL", 1),
    TIN("tin", "TIN", 2),
    PEWTER("pewter", "PEWTER",3),
    COPPER("copper", "COPPER", 4),
    BRONZE("bronze", "BRONZE", 5),
    ZINC("zinc", "ZINC", 6),
    BRASS("brass", "BRASS",7),
    ELECTRUM("electrum", "ELECTRUM", 8),
    CADMIUM("cadmium", "CADMIUM", 9),
    BENDALLOY("bendalloy", "BENDALLOY",10),
    ALUMINUM("aluminum", "ALUMINUM", 11),
    DURALUMIN("duralumin", "DURALUMIN",12),
    CHROMIUM("chromium", "CHROMIUM", 13),
    NICROSIL("nicrosil", "NICROSIL",14),
    ATIUM("atium", "ATIUM",15 ),
    MALATIUM("malatium", "MALATIUM", 16),
    LERASIUM("lerasium", "LERASIUM",17),
    ETTMETAL("ettmetal", "ETTMETAL",18),
    GOLD("gold", "GOLD",19),
    IRON("iron", "IRON",20);


    private final String nameLower;
    private final String nameUpper;
    private final int position;


    MetalsNBTData(String nameLower, String nameUpper, int position) {
        this.nameLower = nameLower;
        this.nameUpper = nameUpper;
        this.position = position;
    }

    public String getNameLower() {
        return nameLower;
    }

    public String getNameUpper() {
        return nameUpper;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public String getGemNameLower() {
        return null;
    }

    @Override
    public String getGemNameUpper() {
        return null;
    }

    @Override
    public String getMetalNameLower() {
        return null;
    }

    @Override
    public String getMetalNameUpper() {
        return null;
    }
}
