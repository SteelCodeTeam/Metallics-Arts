package net.rudahee.metallics_arts.data.enums.implementations.languages.book.sub_division;

/**
 * This enum contains the description for the book of each subdivision of items and powers in different languages
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public enum SubdivisionDescription {
    WEAPONS(
            new String[]{"",""},
            new String[]{"",""},
            new String[]{"",""}),
    CAFTING(
            new String[]{"",""},
            new String[]{"",""},
            new String[]{"",""}),
    ALLOY_FURNACE(
            new String[]{"",""},
            new String[]{"",""},
            new String[]{"",""}),

    PHYSICAL_ALLOMANCY(
            new String[]{""},
            new String[]{"Dentro del grupo de metales físicos, encontrarás aquellos que nos permiten interactuar con el plano físico. Mover objetos y aumentar nuestras capacidades."},
            new String[]{""}),
    MENTAL(
            new String[]{""},
            new String[]{"Los metales mentales, como su nombre indica, permiten interactuar con las mentes ajenas y defender la nuestra del contacto externo."},
            new String[]{""}),
    TEMPORAL(
            new String[]{""},
            new String[]{"En el grupo de los metales temporales, están los que permiten quebrantar el tiempo, desde conocer tu pasado a acelerar lo que te rodea."},
            new String[]{""}),
    ENHANCEMENT(
            new String[]{""},
            new String[]{"Los metales de mejora, le permiten a un alomantico potenciar este arte, o denegarla por completo."},
            new String[]{""}),
    DIVINE_ALLOMANCY(
            new String[]{""},
            new String[]{"Los metales divinos, son metales extraños y perdidos en el tiempo, poco se sabe de ellos como conjunto, pero algo está claro, ten cuidado al ingerir el metal de Armonía…"},
            new String[]{""}),
    TUTORIAL_ALLOMANCY(
            new String[]{""},
            new String[]{"Para saber dónde empezar tenemos que ver cuáles son tus poderes, muchos aquí empiezan como brumosos, personas que solo tienen la capacidad de quemar un mental, por lo que deberíamos ver si puedes quemar algún metal, para esto presiona M, en caso de que algo hayas visto, serás capaz de ","identificar que metal es el que te dará poder. Para poder utilizarlo, rellena un [vial](entry://intro/vials_entry) con fragmentos de este metal y trágatelo, luego solo deberás volver a tocar M y activarlo."},
            new String[]{""}),

    PHYSICAL_FERUCHEMY(
            new String[]{""},
            new String[]{"Los metales de tipo físicos, son los que permiten a un feruquimista debilitar su cuerpo para así ser más fuerte cuando sea necesario."},
            new String[]{""}),
    COGNITIVE(
            new String[]{""},
            new String[]{"Los metales de tipo cognitivos, permiten acumular las capacidades mentales de tu cuerpo, desde el calor que sientes, hasta tus experiencias y así utilizarlas cuando sean necesarias."},
            new String[]{""}),
    SPIRITUAL(
            new String[]{""},
            new String[]{"Los metales de tipo espirituales, permiten acumular y decantar tus atributos que definen el espíritu de cada uno, desde la buena Fortuna que tendrá, hasta su Conexión con el entorno."},
            new String[]{""}),
    HYBRID(
            new String[]{""},
            new String[]{"Los metales híbridos son aquellos permiten almacenar una combinación entre capacidades físicas y cognitivas, desde salud hasta energía."},
            new String[]{""}),
    DIVINE_FERUCHEMY(
            new String[]{""},
            new String[]{"De los legendarios metales divinos al igual que en la Alomancia, poco se sabe, será aunque se mencionan ciertos efectos sobrenaturales, incluso dentro de la misma Investidura."},
            new String[]{""}),
    TUTORIAL_FERUCHEMY(
            new String[]{""},
            new String[]{"Para empezar con este arte, deberíamos primero saber si tienes la capacidad de utilizar como reservas algún metal, esto lo verás al presionar N, no te será difícil identificar cuál puedes usar. Una vez que lo tengas, según dicen las historias, los místicos del norte utilizaban [anillos](entry://intro/rings_entry) o [brazales](entry://intro/bands_entry)  para poder ","guardar su poder, así que intenta conseguir uno y tras equipártelo, utiliza N para activar este misterioso poder."},
            new String[]{""}),

    ALLOMANCY(new String[]{""},
            new String[]{"El arte de la Alomancia, es brutal, cruda, y poderosa. Este arte nos permite obtener gran poder a partir de los metales ingeridos, quemándolos en nuestro interior, como si de un horno se tratase. "},
            new String[]{""}),
    FERUCHEMY(new String[]{""},
            new String[]{"El arte de la Feruquimia, es el arte de Armonía, esta permite almacenar ciertos atributos en los brazaletes, anillos o fragmentos de metal a los que llamamos “mentes de metal” dependiendo claro, del metal del cual estén compuestos, para luego utilizar el poder almacenado, aumentando así las capacidades de su usuario.", "A pesar de que se denomina un arte armónica, tiene una ligera desventaja, ya que todo el poder que su usuario podrá utilizar, deberá ser obtenido de la misma persona …\\\n ¿O quizá no …?"},
            new String[]{""}),
    WELCOME(new String[]{""},
            new String[]{"Hace siglos, aquellos con la capacidad de ingerir metales y metabolizarlos para obtener poderes eran conocidos como “nacidos de la bruma” pero a día de hoy estas prácticas han quedado cerca de la extinción, si estás leyendo esto significa que tú, probablemente, poseas la habilidad para quemar","estos metales en tu interior, tal vez no te des cuenta y lo estés haciendo en este mismo momento. \\\nTambién se cuenta, que los místicos del norte, podían hacer otras “cosas” extrañas con los mismos metales… de esto no sé mucho, pero el caso, es que puede que incluso ahora haya descendientes del norte ", " capaz de usar su poder. \\\n Quizá hayas heredado alguna de estas grandes capacidades, quizá no, pero para sobrevivir en este mundo es mi deber mostrarte el camino de las Artes Metálicas, puede que algunas de estas prácticas te lleven por caminos oscuros repletos "," de muerte, otras pueden ayudar a la reconstrucción del mundo y la salvación de la humanidad, tú decidirás qué camino tomar. \\\n\\\n\\\n\\\n\\\n  Mi nombre es Lemmeg y este es el Diario del Superviviente."},
            new String[]{""}),
    LEATHER(new String[]{""},
            new String[]{"mierda de cuero"},
            new String[]{""});

    private final String[] english;
    private final String[] spanish;
    private  final String[] polish;
    /**
     * Creates a new SubdivisionDescription object with the provided English, Spanish, and Polish translations.
     *
     * @param english the English translation for the interaction.
     * @param spanish the Spanish translation for the interaction.
     * @param polish the Polish translation for the interaction.
     */
    SubdivisionDescription(String[] english, String[] spanish,String[] polish ) {
        this.english = english;
        this.spanish = spanish;
        this.polish = polish;
    }
    /**
     * Returns the English translation for the interaction.
     *
     * @return the English translation for the interaction
     */
    public String[] getEnglish() {
        return english;
    }

    /**
     * Returns the Spanish translation for the interaction.
     *
     * @return the Spanish translation for the interaction
     */
    public String[] getSpanish() {
        return spanish;
    }
    /**
     * Returns the Polish translation for the interaction.
     *
     * @return the Polish translation for the interaction
     */

    public String[] getPolish() {
        return polish;
    }
}
