package net.rudahee.metallics_arts.data.enums.implementations.languages;

import net.rudahee.metallics_arts.data.enums.interfaces.ILanguage;

public enum ColorsNames implements ILanguage {
    WHITE("white","white", "blanco"),
    ORANGE("orange","orange","naranja"),
    MAGENTA("magenta","magenta", "magenta"),
    LIGHT_BLUE("light_blue","light_blue",  "celeste"),
    YELLOW("yellow","yellow", "amarillo"),
    LIME( "lime","lime","verde lima"),
    PINK("pink", "pink","rosa"),
    GRAY("gray", "gray", "gris"),
    LIGHT_GRAY("light_gray","light_gray", "gris claro"),
    CYAN("cyan","cyan","cian"),
    PURPLE("purple","purple", "morado"),
    BLUE("blue","blue", "azul"),
    BROWN("brown","brown", "marron"),
    GREEN("green","green", "verde"),
    RED("red","red", "rojo"),
    BLACK("black","black", "negro");

    private final String id;
    private final String english;
    private final String spanish;

    ColorsNames(String id, String english, String spanish) {
        this.id = id;
        this.english = english;
        this.spanish = spanish;
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
}
