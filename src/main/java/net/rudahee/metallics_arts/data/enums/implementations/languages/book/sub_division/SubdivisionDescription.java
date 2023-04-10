package net.rudahee.metallics_arts.data.enums.implementations.languages.book.sub_division;

/**
 * This enum contains the description for the book of each subdivision of items and powers in different languages
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public enum SubdivisionDescription {
    WEAPONS("",
            "",
            ""),
    CAFTING("",
            "",
            ""),
    ALLOY_FURNACE("",
            "",
            ""),

    PHYSICAL_ALLOMANCY("Dentro del grupo de metales físicos, encontrarás aquellos que nos permiten interactuar con el plano físico. Mover objetos y aumentar nuestras capacidades.",
            "Dentro del grupo de metales físicos, encontrarás aquellos que nos permiten interactuar con el plano físico. Mover objetos y aumentar nuestras capacidades.",
            ""),
    MENTAL("",
            "Los metales mentales, como su nombre indica, permiten interactuar con las mentes ajenas y defender la nuestra del contacto externo.",
            ""),
    TEMPORAL("",
            "En el grupo de los metales temporales, están los que permiten quebrantar el tiempo, desde conocer tu pasado a acelerar lo que te rodea.",
            ""),
    ENHANCEMENT("",
            "Los metales de mejora, le permiten a un alomantico potenciar este arte, o denegarla por completo.",
            ""),
    DIVINE_ALLOMANCY("",
            "Los metales divinos, son metales extraños y perdidos en el tiempo, poco se sabe de ellos como conjunto, pero algo está claro, ten cuidado al ingerir el metal de Armonía…",
            ""),
    TUTORIAL_ALLOMANCY("","",""),


    PHYSICAL_FERUCHEMY("",
            "Los metales de tipo físicos, son los que permiten a un feruquimista debilitar su cuerpo para así ser más fuerte cuando sea necesario.",
            ""),
    COGNITIVE("",
            "Los metales de tipo cognitivos, permiten acumular las capacidades mentales de tu cuerpo, desde el calor que sientes, hasta tus experiencias y así utilizarlas cuando sean necesarias.",
            ""),
    SPIRITUAL("",
            "Los metales de tipo espirituales, permiten acumular y decantar tus atributos que definen el espíritu de cada uno, desde la buena Fortuna que tendrá, hasta su Conexión con el entorno.",
            ""),
    HYBRID("",
            "Los metales híbridos son aquellos permiten almacenar una combinación entre capacidades físicas y cognitivas, desde salud hasta energía.",
            ""),
    DIVINE_FERUCHEMY("",
            "De los legendarios metales divinos al igual que en la Alomancia, poco se sabe, será aunque se mencionan ciertos efectos sobrenaturales, incluso dentro de la misma Investidura.",
            ""),
    TUTORIAL_FERUCHEMY("","",""),

    ALLOMANCY("",
            "El arte de la Alomancia, es brutal, cruda, y poderosa. Este arte nos permite obtener gran poder a partir de los metales ingeridos, quemándolos en nuestro interior, como si de un horno se tratase. ",
            ""),
    FERUCHEMY("",
            "El arte de la Feruquimia, es el arte de Armonía, esta permite almacenar ciertos atributos en los brazaletes, anillos o fragmentos de metal a los que llamamos “mentes de metal” dependiendo claro, del metal del cual estén compuestos, para luego utilizar el poder almacenado, aumentando así las capacidades de su usuario.",
            ""),
    WELCOME("",
            "Hace siglos, aquellos con la capacidad de ingerir metales y metabolizarlos para obtener poderes eran conocidos como “nacidos de la bruma” pero a día de hoy estas prácticas han quedado cerca de la extinción, si estás leyendo esto significa que tú, probablemente, poseas la habilidad para quemar estos metales en tu interior, tal vez no te des cuenta y lo estés haciendo en este mismo momento.\n" +
                    "También se cuenta, que los místicos del norte, podían hacer otras “cosas” extrañas con los mismos metales… de esto no sé mucho, pero el caso, es que puede que incluso ahora haya descendientes del norte capaz de usar su poder.\n",
            "");

    private final String english;
    private final String spanish;
    private  final String polish;
    /**
     * Creates a new SubdivisionDescription object with the provided English, Spanish, and Polish translations.
     *
     * @param english the English translation for the interaction.
     * @param spanish the Spanish translation for the interaction.
     * @param polish the Polish translation for the interaction.
     */
    SubdivisionDescription(String english, String spanish,String polish ) {
        this.english = english;
        this.spanish = spanish;
        this.polish = polish;
    }
    /**
     * Returns the English translation for the interaction.
     *
     * @return the English translation for the interaction
     */
    public String getEnglish() {
        return english;
    }

    /**
     * Returns the Spanish translation for the interaction.
     *
     * @return the Spanish translation for the interaction
     */
    public String getSpanish() {
        return spanish;
    }
    /**
     * Returns the Polish translation for the interaction.
     *
     * @return the Polish translation for the interaction
     */

    public String getPolish() {
        return polish;
    }
}
