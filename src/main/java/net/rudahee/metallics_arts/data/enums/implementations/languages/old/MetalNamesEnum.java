package net.rudahee.metallics_arts.data.enums.implementations.languages.old;

import net.rudahee.metallics_arts.data.enums.interfaces.ILanguage;

/**
 * This enum represents a list of metal names with their corresponding IDs and translations in different languages.
 *
 * @author SteelCode Team
 * @since 1.5.1
 *
 * @see ILanguage
 */
public enum MetalNamesEnum {
    IRON("iron","Iron", "Hierro"),
    STEEL("steel","Steel", "Acero"),
    TIN("tin", "Tin", "Estaño"),
    PEWTER("pewter","Pewter", "Peltre"),
    ZINC("zinc","Zinc", "Zinc"),
    BRASS("brass","Brass", "Laton"),
    COPPER("copper","Copper", "Cobre"),
    BRONZE("bronze","Bronze", "Bronce"),
    ALUMINUM("aluminum","Aluminum", "Aluminio"),
    DURALUMIN("duralumin","Duralumin", "Duralumin"),
    CHROMIUM("chromium", "Chromium", "Cromo"),
    NICROSIL("nicrosil","Nicrosil", "Nicrosil"),
    GOLD("gold", "Gold", "Oro"),
    ELECTRUM("electrum", "Electrum", "Electrum"),
    CADMIUM("cadmium", "Cadmium", "Cadmio"),
    BENDALLOY("bendalloy","Bendalloy", "Bendaleo"),
    ATIUM("atium", "Atium", "Atium"),
    MALATIUM("malatium", "Malatium", "Malatium"),
    LERASIUM("lerasium", "Lerasium", "Lerasium"),
    ETTMETAL("ettmetal", "Ettmetal", "Ettmetal"),
    SILVER( "silver", "Silver", "Plata"),
    LEAD("lead", "Lead", "Plomo"),
    NICKEL("nickel", "Nickel", "Níquel");
    private final String id;
    private final String english;
    private final String spanish;


    /**
     * Constructor for MetalNamesEnum.
     *
     * @param id the ID of the metal.
     * @param english the name of the metal in English.
     * @param spanish the name of the metal in Spanish.
     */
    MetalNamesEnum(String id, String english, String spanish) {
        this.id = id;
        this.english = english;
        this.spanish = spanish;

    }

}
