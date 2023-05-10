package net.rudahee.metallics_arts.data.enums.implementations.languages.book.allomancy;

/**
 * This enum contains the description for the book of allomantic powers in different languages.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public enum PowerDescriptions {
    IRON("\\\nPulls at the metal source you are aiming at.\\\n\\\n++To use this power, you need to left click while looking at the metal source.++",
            "\\\n Tira de la fuente de metal a la que estés apuntando.\\\n Para usar este poder, debes hacer clic izquierdo mientras miras a dicha fuente de metal.",
            "\\\nPrzyciągnij metal na którego linię patrzysz.\\\n\\\n++Żeby użyć tej mocy, naciśnij lewy przycisk myszy podczas patrzenia na linię metalu.++"),
    STEEL("\\\nPushes the metal source you are pointing at.\\\n++To use this power, you must right click while looking at the metal source.++\\\n\\\nBonus:\\\nx You can use Ctrl + Spacebar to push towards the block at your feet.\\\nx In case there are no valid blocks, consume metal nuggets to produce the same effect.",
            "\\\n Empuja la fuente de metal a la que estés señalando. \\\n Para usar este poder, debes hacer clic derecho mientras miras a dicha fuente de metal.\\\n Extra: \\\n x Puedes utilizar Ctrl + Barra espaciadora para empujar hacia el bloque que tengas a tus pies. \\\n x En caso de que no haya bloques válidos, consume pepitas de metales.",
            "\\\nOdepchnij metal na którego linię patrzysz.\\\n++Żeby użyć tej mocy, naciśnij prawy przycisk myszy podczas patrzenia na linię metalu.++\\\n\\\nBonus:\\\nx Możesz nacisnąć Ctrl + Spacja żeby odepchnąć się od bloku metalu pod tobą.\\\nx Jeżeli nie ma takiego bloku, zarodki metalu z ekwipunku zostaną użyte."),
    TIN("\\\nHeightens your senses.",
            "\\\n Aumenta tus sentidos.",
            "\\\nWyostrza twoje zmysły."),
    PEWTER("\\\nIncreases your physical capabilities.",
            "\\\n Aumenta tus capacidades físicas.",
            "\\\nZwiększa twoje możliwości fizyczne"),
    ZINC("\\\nMakes entities aggressive.\\\n++To use this power, you must left click while looking at the entity.++\\\n\\\nBonus:\\\nx You can free pets from their owner.",
            "\\\n Vuelve agresivas a las entidades. \\\n Para usar este poder, debes hacer clic izquierdo mientras miras a dicha entidad.\\\n\\\n\\\n Extra: \\\n x Puedes liberar a las mascotas de su dueño.",
            "\\\nSprawia że istoty stają się agresywne.\\\n++Żeby użyć tej mocy, naciśnij lewyxprzycisk myszy podczas patrzenia na istotę.++\\\n\\\nBonus:\\\nx Możesz uwalniać zwierzęta od ich właścicieli"),
    BRASS("\\\nHostile entities will ignore you.\\\n\\\n++To use this power, you must right click while looking at the entity.++\\\n\\\nBonus:\\\nx You have a chance to tame pets.",
            "\\\n Las entidades hostiles te ignorarán. \\\n Para usar este poder en una entidad, debes hacer clic derecho mientras miras a la entidad.\\\n\\\n\\\n Extra: \\\n x Tienes una probabilidad de domesticar mascotas.",
            "\\\nSprawia że agresywne istoty cię ignorują.\\\\\\n++Żeby użyć tej mocy, naciśnij prawyxprzycisk myszy podczas patrzenia na istotę.++\\\\\\n\\\\\\nBonus:\\\\\\nx Masz szansę na oswojenie zwierzęcia"),
    COPPER("\\\nMakes nearby entities ignore you.\\\n\\\nBonus:\\\nx Prevent other players from seeing that you're burning metals.",
            "\\\n Hace que las entidades cercanas te ignoren. \\\n\\\n\\\n\\\n\\\n Extra: \\\n x Evita que otros jugadores vean que estás quemando metales.",
            "\\\nSprawia że pobliskie istoty cię ignorują.\\\n\\\nBonus:\\\nx Nie pozwala innym graczom zobaczyć że spalasz metal."),
    BRONZE("\\\nMakes nearby entities follow you around.\\\n\\\nBonus:\\\nx Mark players burning metals around you.",
            "\\\n Hace que las entidades cercanas te sigan. \\\n\\\n\\\n\\\n\\\n\\\n Extra: \\\n x Resalta a los jugadores que quemen metales a tu alrededor.",
            "\\\nSprawia że pobliskie istoty za tobą podążają.\\\n\\\nBonus:\\\nx Daję ci możliwość widzenia innych graczy spalającuch metal."),
    ALUMINUM("\\\nDrains your Allomantic reserves.\\\n\\\nBonus: \\\nx The greater the reserve you consume, the greater the probability of removing other adverse effects.",
            "\\\nVacía tus reservas alománticas. \\\n\\\n\\\n Extra: \\\n x Cuanto mayor sea la cantidad que quemes, mayor será la probabilidad de remover tus efectos de estado (tanto positivos como negativos).",
            "\\\nSpal całą rezerwę alomantycznych metali bez wywoływania żadnego efektu.\\\n\\\nBonus:\\\nx Im większą rezerwę w ten sposób spalisz tym większa szansa na pozbycie się innych negatywnych efektów."),
    DURALUMIN("\\\nGreatly increases the power of the metals you are burning.\\\n\\\nBonus:\\\nx It works for a short period of time and empties all reserves that are active at the time of activation.",
            "\\\n Aumenta en gran medida la potencia de los metales que estés quemando \\\n\\\n\\\n Extra: \\\n x Funciona un corto periodo de tiempo y vacía las reservas del metal que hayas activado junto al duralumín",
            "\\\nW ogromnym stopniu zwiększa siłę metali które spalasz.\\\n\\\nBonus:\\\nx Działa przez krutki okres czasu po czym wypala wszystkie metale spalane podczas aktywacji."),
    CHROMIUM("\\\nDrains the allomantic reserves of the player you hit.",
            "\\\n Vacía las reservas alománticas del jugador al que golpees.",
            "\\\nWypala rezerwy alomantyczne gracza którego uderzysz."),
    NICROSIL("\\\nGreatly increases the potency of the metals being burned by the person you hit.\\\n\\\nBonus:\\\nx Works for a short time and drains all Allomantic reserves the target player was burning.",
            "\\\n Aumenta en gran medida la potencia de los metales que esté quemando el jugador al que golpees. \\\n\\\n Extra: \\\n x Funciona un corto periodo de tiempo y vacía las reservas alomanticas del metal que estaba quemando el jugador objetivo.",
            "\\\nW ogromnym stopniu zwiększa siłę metali spalanych przez gracza którego uderzysz.\\\n\\\nBonus:\\\nx Działa przez krutki okres czasu po czym wypala wszystkie metale spalane przez uderzonego gracza."),
    GOLD("\\\nDraws a line towards the point where you last died.",
            "\\\n Señala el punto donde moriste por última vez.",
            "\\\nWskazuje w miejsce gdzie ostatnio umarłeś."),
    ELECTRUM("\\\nDraws a line towards your respawn point.",
            "\\\n Señala tu punto de reaparición con una línea.",
            "\\\nWskazuje w miejsce twojego odrodzenia"),
    CADMIUM("\\\nYou get slow falling and the game slows down around you.",
            "\\\n Experimentas caída lenta, el tiempo se ralentiza a tu alrededor.",
            "\\\nZyskujesz powolne opadanie, gra zwalnia w okół ciebie."),
    BENDALLOY("\\\nSpeeds up the game around you.",
            "\\\n Acelera el tiempo a tu alrededor. \\\n",
            "\\\nGra przyśpiesza w okół ciebie."),
    ATIUM("\\\nGives you ä chance to avoid incoming damage.\\\n\\\nBonus:\\\nx If your attacker burns Atium he can partially cancel the effects.",
            "\\\n Te otorga una probabilidad de evadir el daño.\\\n\\\n\\\n\\\n\\\n Extra: \\\n x Si tu atacante también quema atium, puede cancelar una parte de los efectos.",
            "\\\nDaje ci szansę na uniknięcie obrażeń.\\\n\\\nBonus:\\\nx Jeżeli twój przeciwnik spala atium możę anulować część tego efektu."),
    MALATIUM("\\\nDraws a line towards the last kill point of the player you hit.",
            "\\\n Señala el último punto de muerte del jugador al que golpees.",
            "\\\nWskazuje na miejce ostatniej śmierci uderzonego gracza"),
    LERASIUM("\\\nIncreases the potency of the metals you are burning.",
            "\\\n Aumenta la potencia de los metales que estés quemando.",
            "\\\nZwiększa moc spalanych przez ciebie metali."),
    ETTMETAL("\\\nYou explode.\\\n\\\nBonus:\\\nx By default, it does not break blocks.",
            "\\\n Explotas. \\\n\\\n\\\n\\\n\\\n\\\n\\\n Extra: \\\n x Por defecto, no rompe bloques.",
            "\\\nWybuchasz.\\\n\\\nBonus:\\\nx Eksplozja nie niszczy bloków.");

    private final String english;
    private final String spanish;
    private final String polish;

    /**
     * Creates a new PowerDescriptions object with the provided English, Spanish, and Polish translations.
     *
     * @param english the English translation for the interaction.
     * @param spanish the Spanish translation for the interaction.
     * @param polish the Polish translation for the interaction.
     */
    PowerDescriptions(String english, String spanish, String polish) {
        this.english = english;
        this.spanish = spanish;
        this.polish = polish;
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
     * Returns the English translation for the interaction.
     *
     * @return the English translation for the interaction
     */
    public String getEnglish() {
        return english;
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
