package net.rudahee.metallics_arts.data.enums.implementations;

import net.rudahee.metallics_arts.data.enums.interfaces.IMetal;

/**
 * This enum class represents metals with their different properties and abilities, that can be used in a specific game.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public enum MetalEnum implements IMetal {
    STEEL("steel", "STEEL",true, true, true, true, false, false),
    TIN("tin", "TIN",true, true, true, false, false, true),
    PEWTER("pewter", "PEWTER",true, true, true, true, false, false),
    BRONZE("bronze", "BRONZE",true, true, true, true, false, false),
    ZINC("zinc", "ZINC",true, true, true, false, false, true),
    BRASS("brass", "BRASS",true, true, true, true, false, false),
    ELECTRUM("electrum", "ELECTRUM",true, true, true, true, false, false),
    CADMIUM("cadmium", "CADMIUM",true, true, true, false, true, false),
    BENDALLOY("bendalloy", "BENDALLOY",true, true, true, true, false, false),
    ALUMINUM("aluminum", "ALUMINUM",true, true, true, false, true, true),
    DURALUMIN("duralumin", "DURALUMIN",true, true, true, true, false, false),
    CHROMIUM("chromium", "CHROMIUM",true, true, true, false, true, false),
    NICROSIL("nicrosil", "NICROSIL",true, true, true, true, false, false),
    SILVER("silver", "SILVER",false, false, false, false, true, false),
    LEAD("lead", "LEAD",false, false, false, false, true, true),
    NICKEL("nickel", "NICKEL",false, false, false, false, true, false);

    private final String metalNameLower;
    private final String metalNameUpper;
    private final Boolean allomantic;
    private final Boolean feruchemic;
    private final Boolean hemalurgic;
    private final Boolean alloy;
    private final Boolean deepslate;
    private final Boolean stone;

    /**
     * Constructs a new MetalEnum with the given properties.
     *
     * @param metalNameLower the lowercase name of the metal.
     * @param metalNameUpper the uppercase name of the metal.
     * @param allomantic a Boolean indicating whether the metal has allomantic ability.
     * @param feruchemic a Boolean indicating whether the metal has feruchemic ability.
     * @param hemalurgic a Boolean indicating whether the metal has hemalurgic ability.
     * @param alloy a Boolean indicating whether the metal is an alloy.
     * @param deepslate a Boolean indicating whether the metal is deepslate.
     * @param stone a Boolean indicating whether the metal is stone.
     */
    MetalEnum(String metalNameLower, String metalNameUpper, Boolean allomantic, Boolean feruchemic, Boolean hemalurgic, Boolean alloy, Boolean deepslate, Boolean stone) {
        this.metalNameLower = metalNameLower;
        this.metalNameUpper = metalNameUpper;
        this.allomantic = allomantic;
        this.feruchemic = feruchemic;
        this.hemalurgic = hemalurgic;
        this.alloy = alloy;
        this.deepslate = deepslate;
        this.stone = stone;
    }

    /**
     * Returns the lower case name of the metal.
     *
     * @return the lower case name of the metal.
     */
    public String getMetalNameLower() {
        return metalNameLower;
    }

    /**
     * Returns the upper case name of the metal.
     *
     * @return the upper case name of the metal.
     */
    public String getMetalNameUpper() {
        return metalNameUpper;
    }

    /**
     * Returns whether the metal has allomantic properties.
     *
     * @return true if the metal has allomantic properties, false otherwise.
     */
    public Boolean isAllomantic() { return allomantic; }
    /**
     * Returns whether the metal has feruchemic properties.
     *
     * @return true if the metal has feruchemic properties, false otherwise.
     */
    public Boolean isFeruchemic() { return feruchemic; }

    /**
     * Returns whether the metal has hemalurgic properties.
     *
     * @return true if the metal has hemalurgic properties, false otherwise.
     */
    public Boolean isHemalurgic() { return hemalurgic; }

    /**
     * Returns whether the metal is an alloy.
     *
     * @return true if the metal is an alloy, false otherwise.
     */
    public Boolean isAlloy() { return alloy; }

    /**
     * Returns whether the metal is found in deepslate.
     *
     * @return true if the metal is found in deepslate, false otherwise.
     */
    public Boolean isDeepslate() {
        return deepslate;
    }

    /**
     * Returns whether the metal is a type of stone.
     *
     * @return true if the metal is a type of stone, false otherwise.
     */
    public Boolean isStone() {
        return stone;
    }

}
