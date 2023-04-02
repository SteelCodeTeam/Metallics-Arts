package net.rudahee.metallics_arts.data.enums.implementations;

import net.rudahee.metallics_arts.data.enums.interfaces.IGems;

/**
 * An enumeration of gemstones with Allomantic, Feruchemic, and Hemalurgic properties.
 * This class implements the IGems interface.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public enum GemsEnum implements IGems {

    ATIUM("atium", "ATIUM", true, true, true),
    MALATIUM("malatium", "MALATIUM", true, true, true),
    LERASIUM("lerasium", "LERASIUM", true, true, true),
    ETTMETAL("ettmetal", "ETTMETAL", true, true, true);
    private final String gemNameLower;
    private final String gemNameUpper;
    private final Boolean allomantic;
    private final Boolean feruchemic;
    private final Boolean hemalurgic;

    /**
     * Constructs a new GemsEnum object with the provided parameters.
     *
     * @param gemNameLower the lowercase name of the gem.
     * @param gemNameUpper the uppercase name of the gem.
     * @param allomantic whether the gem is allomantic or not.
     * @param feruchemic whether the gem is feruchemic or not.
     * @param hemalurgic whether the gem is hemalurgic or not.
     */
    GemsEnum(String gemNameLower, String gemNameUpper, Boolean allomantic, Boolean feruchemic, Boolean hemalurgic) {
        this.gemNameLower = gemNameLower;
        this.gemNameUpper = gemNameUpper;
        this.allomantic = allomantic;
        this.feruchemic = feruchemic;
        this.hemalurgic = hemalurgic;
    }

    /**
     * This method returns the lowercase name of the gem.
     *
     * @return the lowercase name of the gem
     */
    public String getGemNameLower() {
        return gemNameLower;
    }
    /**
     * This method returns the uppercase name of the gem.
     *
     * @return the uppercase name of the gem
     */
    public String getGemNameUpper() {
        return gemNameUpper;
    }

    /**
     * This method returns whether the gem is Allomantic or not.
     *
     * @return true if the gem is Allomantic, false otherwise
     */
    public Boolean isAllomantic() { return allomantic; }

    /**
     * This method returns whether the gem is Feruchemic or not.
     *
     * @return true if the gem is Feruchemic, false otherwise
     */
    public Boolean isFeruchemic() { return feruchemic; }
    /**
     * This method returns whether the gem is Hemalurgic or not.
     *
     * @return true if the gem is Hemalurgic, false otherwise
     */
    public Boolean isHemalurgic() { return hemalurgic; }

}
