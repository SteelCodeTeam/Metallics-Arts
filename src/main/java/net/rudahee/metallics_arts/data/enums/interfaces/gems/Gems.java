package net.rudahee.metallics_arts.data.enums.interfaces.gems;

public enum Gems implements IGems {

    ATIUM("atium", "ATIUM", true, true, true),
    MALATIUM("malatium", "MALATIUM", true, true, true),
    LERASIUM("lerasium", "LERASIUM", true, true, true),
    ETTMETAL("ettmetal", "ETTMETAL", true, true, true);


    private final String gemNameLower;
    private final String gemNameUpper;
    private final Boolean allomantic;
    private final Boolean feruchemic;
    private final Boolean hemalurgic;

    Gems(String gemNameLower, String gemNameUpper, Boolean allomantic, Boolean feruchemic, Boolean hemalurgic) {
        this.gemNameLower = gemNameLower;
        this.gemNameUpper = gemNameUpper;
        this.allomantic = allomantic;
        this.feruchemic = feruchemic;
        this.hemalurgic = hemalurgic;
    }

    public String getGemNameLower() {
        return gemNameLower;
    }

    public String getGemNameUpper() {
        return gemNameUpper;
    }

    public Boolean isAllomantic() { return allomantic; }

    public Boolean isFeruchemic() { return feruchemic; }

    public Boolean isHemalurgic() { return hemalurgic; }

}
