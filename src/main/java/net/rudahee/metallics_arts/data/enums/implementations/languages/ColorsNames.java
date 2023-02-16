package net.rudahee.metallics_arts.data.enums.implementations.languages;

import net.rudahee.metallics_arts.data.enums.interfaces.ILanguage;

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

    ColorsNames(String id, String english, String spanish, String polish) {
        this.id = id;
        this.english = english;
        this.spanish = spanish;
        this.polish = polish;
    }

    public String getId() {
        return this.id;
    }

    @Override
    public String getNameInSpanish() {
        return this.spanish;
    }

    @Override
    public String getNameInEnglish() {
        return this.english;
    }

    public String getNameInPolish(){
        return this.polish;
    }
}
