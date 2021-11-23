package net.rudahee.metallics_arts.setup.enums;

public enum MetalsNBTData implements IMetal, IGems {
    STEEL("steel", "STEEL", 1),
    IRON("iron", "IRON", 1),
    TIN("tin", "TIN", 2),
    PEWTER("pewter", "PEWTER", 2),
    COPPER("copper", "COPPER", 3),
    BRONZE("bronze", "BRONZE", 3),
    ZINC("zinc", "ZINC", 4),
    BRASS("brass", "BRASS", 4),
    CHROMIUM("chromium", "CHROMIUM", 5),
    NICROSIL("nicrosil", "NICROSIL", 5),
    ALUMINUM("aluminum", "ALUMINUM", 6),
    DURALUMIN("duralumin", "DURALUMIN", 6),
    CADMIUM("cadmium", "CADMIUM", 7),
    BENDALLOY("bendalloy", "BENDALLOY", 7),
    ELECTRUM("electrum", "ELECTRUM", 8),
    GOLD("gold", "GOLD", 8),
    ATIUM("atium", "ATIUM", 9),
    MALATIUM("malatium", "MALATIUM", 9),
    LERASIUM("lerasium", "LERASIUM", 10),
    ETTMETAL("ettmetal", "ETTMETAL", 10);


    private final String nameLower;
    private final String nameUpper;
    private final int group;

    MetalsNBTData(String nameLower, String nameUpper, int group) {
        this.nameLower = nameLower;
        this.nameUpper = nameUpper;
        this.group = group;
    }

    public String getNameLower() {
        return nameLower;
    }

    public String getNameUpper() {
        return nameUpper;
    }

    public int getGroup() {
        return group;
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
