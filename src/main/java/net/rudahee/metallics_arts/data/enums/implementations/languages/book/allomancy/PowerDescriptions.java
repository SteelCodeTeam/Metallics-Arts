package net.rudahee.metallics_arts.data.enums.implementations.languages.book.allomancy;


public enum PowerDescriptions {
    IRON("Pull the metal fountain you are aiming at.\\\n\\\n++To use this power, you need to leftxclick while looking at the metal fountain.++",
            "\\\n Tira de la fuente de metal a la que estés apuntando.\\\n Para usar este poder, debes hacer clic izquierdo mientras miras a dicha fuente de metal."),
    STEEL("Push the metal fountain you are pointing at.\\\n++To use this power, you must rightxclick while looking at the metal fountain.++\\\n\\\nBonus:\\\nx You can use Ctrl + Spacebar to push towards the block at your feet.\\\nx In case there are no valid blocks, consume metal nuggets.",
            "\\\n Empuja la fuente de metal a la que estés señalando. \\\n Para usar este poder, debes hacer clic derecho mientras miras a dicha fuente de metal.\\\n Extra: \\\n x Puedes utilizar Ctrl + Barra espaciadora para empujar hacia el bloque que tengas a tus pies. \\\n x En caso de que no haya bloques válidos, consume pepitas de metales."),
    TIN("Heighten your senses.",
            "\\\n Aumenta tus sentidos."),
    PEWTER("Increase your physical capabilities.",
            "\\\n Aumenta tus capacidades físicas."),
    ZINC("Makes entities aggressive.\\\n++To use this power, you must leftxclick while looking at the entity.++\\\n\\\nBonus:\\\nx You can free pets from their owner.",
            "\\\n Vuelve agresivas a las entidades. \\\n Para usar este poder, debes hacer clic izquierdo mientras miras a dicha entidad.\\\n\\\n\\\n Extra: \\\n x Puedes liberar a las mascotas de su dueño."),
    BRASS("Hostile entities will ignore you.\\\n\\\n++To use this power, you must rightxclick while looking at the entity.++\\\n\\\nBonus:\\\nx You have a chance to tame pets.",
            "\\\n Las entidades hostiles te ignorarán. \\\n Para usar este poder en una entidad, debes hacer clic derecho mientras miras a la entidad.\\\n\\\n\\\n Extra: \\\n x Tienes una probabilidad de domesticar mascotas."),
    COPPER("Makes nearby entities ignore you.\\\n\\\nBonus:\\\nx Prevent other players from seeing that you're burning metal.",
            "\\\n Hace que las entidades cercanas te ignoren. \\\n\\\n\\\n\\\n\\\n Extra: \\\n x Evita que otros jugadores vean que estás quemando metales."),
    BRONZE("Makes nearby entities follow you around.\\\n\\\nBonus:\\\nx Mark players burning metals around you.",
            "\\\n Hace que las entidades cercanas te sigan. \\\n\\\n\\\n\\\n\\\n\\\n Extra: \\\n x Resalta a los jugadores que quemen metales a tu alrededor."),
    ALUMINUM("Drain your Allomantic reserves.\\\n\\\nBonus: \\\nx The greater the reserve you consume, the greater the probability of removing other adverse effects.","Vacía tus reservas alománticas. \\\n\\\n\\\n Extra: \\\n x Cuanto mayor sea la cantidad que quemes, mayor será la probabilidad de remover tus efectos de estado (tanto positivos como negativos)."),
    DURALUMIN("Greatly increases the power of the metals you are burning.\\\n\\\nBonus:\\\nx It works for a short period of time and empties all reserves that are active at the time of activation.",
            "\\\n Aumenta en gran medida la potencia de los metales que estés quemando \\\n\\\n\\\n Extra: \\\n x Funciona un corto periodo de tiempo y vacía las reservas del metal que hayas activado junto al duralumín"),
    CHROMIUM("Drains the allomantic reserves of the player you hit.",
            "\\\n Vacía las reservas alománticas del jugador al que golpees."),
    NICROSIL("Greatly increases the potency of the metals you are burning for the player you hit.\\\n\\\nBonus:\\\nx Works for a short time and drains all Allomantic reserves the target player was burning.",
            "\\\n Aumenta en gran medida la potencia de los metales que esté quemando el jugador al que golpees. \\\n\\\n Extra: \\\n x Funciona un corto periodo de tiempo y vacía las reservas alomanticas del metal que estaba quemando el jugador objetivo."),
    GOLD("Mark the point where you last died.",
            "\\\n Señala el punto donde moriste por última vez."),
    ELECTRUM("Mark your respawn point with a line.",
            "\\\n Señala tu punto de reaparición con una línea."),
    CADMIUM("You get slow drop, the game slows down around you.",
            "\\\n Experimentas caída lenta, el tiempo se ralentiza a tu alrededor."),
    BENDALLOY("Speed up the game around you.",
            "\\\n Acelera el tiempo a tu alrededor. \\\n"),
    ATIUM("Gives you chance to avoid incoming damage.\\\n\\\nBonus:\\\nx If your attacker burns Atium he can cancel a part of the effects.",
            "\\\n Te otorga una probabilidad de evadir el daño recibido.\\\n\\\n\\\n\\\n\\\n Extra: \\\n x Si tu atacante también quema atium, puede cancelar una parte de los efectos."),
    MALATIUM("Marks the last kill point of the player you hit.",
            "\\\n Señala el último punto de muerte del jugador al que golpees."),
    LERASIUM("Increases the potency of the metals you are burning.",
            "\\\n Aumenta la potencia de los metales que estés quemando."),
    ETTMETAL("You explode.\\\n\\\nBonus:\\\nx By default, it does not break blocks.",
            "\\\n Explotas. \\\n\\\n\\\n\\\n\\\n\\\n\\\n Extra: \\\n x Por defecto, no rompe bloques.");

    private final String english;
    private final String spanish;

    PowerDescriptions(String english, String spanish) {
        this.english = english;
        this.spanish = spanish;
    }


    public String getEnglish() {
        return english;
    }

}
