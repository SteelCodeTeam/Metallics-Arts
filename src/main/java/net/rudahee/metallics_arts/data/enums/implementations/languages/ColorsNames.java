package net.rudahee.metallics_arts.data.enums.implementations.languages;

import net.rudahee.metallics_arts.data.enums.interfaces.ILanguage;


/**
 * Enum that represents a set of colors with their names in English, Spanish and Polish.
 * Implements the ILanguage interface.
 *
 * @author SteelCode Team
 * @since 1.5.1
 *
 * @see ILanguage
 */
public enum ColorsNames implements ILanguage {
    WHITE("white","white", "blanco", "biały"),
    ORANGE("orange","orange","naranja", "pomarańczowy"),
    MAGENTA("magenta","magenta", "magenta", "magenta"),
    LIGHT_BLUE("light_blue","light_blue",  "celeste", "jasno niebieski"),
    YELLOW("yellow","yellow", "amarillo", "żółty"),
    LIME( "lime","lime","verde lima", "limonkowy"),
    PINK("pink", "pink","rosa", "różowy"),
    GRAY("gray", "gray", "gris", "szary"),
    LIGHT_GRAY("light_gray","light_gray", "gris claro", "jasno szary"),
    CYAN("cyan","cyan","cian", "cyjan"),
    PURPLE("purple","purple", "morado", "fioletowy"),
    BLUE("blue","blue", "azul", "niebieski"),
    BROWN("brown","brown", "marron", "brązowy"),
    GREEN("green","green", "verde", "zielony"),
    RED("red","red", "rojo", "czerwony"),
    BLACK("black","black", "negro", "czarny");

    private final String id;
    private final String english;
    private final String spanish;
    private final String polish;

    /**
     * Constructor for ColorsNames object.
     *
     * @param id The id of the color.
     * @param english The name of the color in English.
     * @param spanish The name of the color in Spanish.
     * @param polish The name of the color in Polish.
     */
    ColorsNames(String id, String english, String spanish, String polish) {
        this.id = id;
        this.english = english;
        this.spanish = spanish;
        this.polish = polish;
    }

    /**
     * Gets the identifier of the color.
     *
     * @return the identifier of the color
     */
    public String getId() {
        return this.id;
    }

    /**
     * Gets the name of the color in Spanish.
     *
     * @return the name of the color in Spanish
     */
    @Override
    public String getNameInSpanish() {
        return this.spanish;
    }

    /**
     * Gets the name of the color in English.
     *
     * @return the name of the color in English
     */
    @Override
    public String getNameInEnglish() {
        return this.english;
    }

    /**
     * Gets the name of the color in Polish.
     *
     * @return the name of the color in Polish
     */
    public String getNameInPolish(){
        return this.polish;
    }
}
