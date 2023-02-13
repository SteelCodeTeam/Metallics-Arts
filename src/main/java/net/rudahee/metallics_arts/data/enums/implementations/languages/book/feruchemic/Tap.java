package net.rudahee.metallics_arts.data.enums.implementations.languages.book.feruchemic;

public enum Tap {
    IRON("\\\n\\\n++Tapping:++\\\n\\\n x As you decant, you seem to get much heavier.",
            "\\\n\\\n++Decantar:++\\\nx Mientras decantas, sientes como te vuelves mucho más pesado. Esperemos que no lo hagas en los dos sentidos de la expresión."),
    STEEL("\\\n\\\n++Tapping:++\\\n\\\n x As you decant, you feel really fast.",
            "\\\n\\\n++Decantar:++\\\nx Mientras decantas, sientes que puedes dejar atrás el viento."),
    TIN("\\\n\\\n++Tapping:++\\\n\\\n x As you decant, diminish your senses.",
            "\\\n\\\n++Decantar:++\\\nx Mientras decantas, aumenta tus sentidos."),
    PEWTER("\\\n\\\n++Tapping:++\\\n\\\n x While you store, you feel much stronger, you could kill the Wither boss with your fists.",
            "\\\n\\\n++Decantar:++\\\nx Mientras decantas, te sientes mucho más fuerte, como si pudieras matar al Wither a puñetazos."),
    ZINC("\\\n\\\n++Tapping:++\\\n\\\n x While you decant, you have a chance to double the loot.",
            "\\\n\\\n++Decantar:++\\\nx Mientras decantas, sientes que todos los seres están a rebosar de objetos que puedes (y quieres) obtener."),
    BRASS("\\\n\\\n++Tapping:++\\\n\\\n x As you decant, you expel heat, causing you to set other entities on fire on hit.\\\n\\\n x Comforting against that cold powder snow.",
            "\\\n\\\n++Decantar:++\\\nx Mientras decantas, expulsas el calor corporal acumulado, haciendo que puedas prender en llamas a otras entidades al golpearlas.$(br2)\\\nx Útil para combatir el frío, además."),
    COPPER("\\\n\\\n++Tapping:++\\\n\\\n x As you decant, you gain back all of your experience.",
            "\\\n\\\n++Decantar:++\\\nx Mientras decantas, ganas toda la experiencia que hubiera en la mente de metal."),
    BRONZE("\\\n\\\n++Tapping:++\\\n\\\n x As you tapping, you will feel more rested, causing the Phantoms to ignore you.",
            "\\\n\\\n++Decantar:++\\\nx Mientras decantas, te sentirás mas descansado, haciendo que los Fantasmas que pueda haber te ignoren."),
    ALUMINUM("\\\n\\\n++Tapping:++\\\n\\\n x By decanting, you seal your Identity in a metalmind.",
            "\\\n\\\n++Decantar:++\\\nx Al decantar, sellas una mente de metal con tu Identidad."),
    DURALUMIN("\\\n\\\n++Tapping:++\\\n\\\n x While you decant, it seems that you find the paths with your eyes closed, it seems as if it were your land.",
            "\\\n\\\n++Decantar:++\\\nx Mientras decantas, parece que encuentras los caminos con los ojos cerrados, justo lo contrario que te sucedía antes. Lo entenderás cuando lo pruebes."),
    CHROMIUM("\\\n\\\n++Tapping:++\\\n\\\n x While you decant it, you have extraordinary luck, is it time to go exploring?",
            "\\\n\\\n++Decantar:++\\\nx Mientras decantas, casi es como si Dios te hubiera bendecido. ¿Sería ese un momento para salir a explorar?"),
    NICROSIL("\\\n\\\n++Tapping:++\\\n\\\n x When decanting, it seems like your other metal minds are never going to run out. It is necessary to use it together with other powers.",
            "\\\n\\\n++Decantar:++\\\nx Al decantar, logras estirar la duración de otras mentes de metal que estés decantando. Es necesario usarla junto a otros poderes."),
    GOLD("\\\n\\\n++Tapping:++\\\n\\\n x As you decant, it looks like you could take on any disease.",
            "\\\n\\\n++Decantar:++\\\nx Mientras decantas, sientes que no hay bicho en el mundo que pueda dañarte. Tu cuerpo está hecho para absorber el dolor."),
    ELECTRUM("\\\n\\\n++Tapping:++\\\n\\\n x As you decant, it seems that your life is increased drastically.",
            "\\\n\\\n++Decantar:++\\\nx Mientras decantas, es como si de repente te vieras capaz de vivir más años que cualquier otro ser humano."),
    CADMIUM("\\\n\\\n++Tapping:++\\\n\\\n x While you decant, you will feel like a fish in water, it almost seems that you can breathe at the bottom of the ocean.",
            "\\\n\\\n++Decantar:++\\\nx Mientras decantas, te sentirás como pez en el agua, hasta el punto que casi creerás que no necesitas salir a buscar aire cuando bucees."),
    BENDALLOY("\\\n\\\n++Tapping:++\\\n\\\n x As you decant, you constantly reduce your hunger",
            "\\\n\\\n++Decantar:++\\\nx Mientras decantas, reduces tu hambre constantemente."),
    ATIUM("\\\n\\\n++Tapping:++\\\n\\\n x While you store, it allows you to become invisible.",
            "\\\n\\\n++Decantar:++\\\nx Mientras decantas, te vuelves invisible."),
    MALATIUM("\\\n\\\n++Tapping:++\\\n\\\n x While you decant, the durability of the item in hand increases, thanks to your reserves.",
            "\\\n\\\n++Decantar:++\\\nx Mientras decantas, aumenta la durabilidad del objeto que tengas en la mano. Aunque la durabilidad no es universal, aviso."),
    LERASIUM("\\\n\\\n++Tapping:++\\\n\\\n x When you decant, you gain the Allomantic reserves you previously stored.",
            "\\\n\\\n++Decantar:++\\\nx Cuando decantas, obtienes las reservas alománticas que hubiera en la mente de metal."),
    ETTMETAL("\\\n\\\n++Tapping:++\\\n\\\n x When decanting, it seems that you are going to release all your inner energy at once.",
            "\\\n\\\n++Decantar:++\\\nx Cuando decantas, sientes como si tu interior fuera a, literalmente, explotar.");

    private final String english;
    private final String spanish;

    Tap(String english, String spanish) {
        this.english = english;
        this.spanish = spanish;
    }

    public String getEnglish() {
        return english;
    }

    public String getSpanish() {
        return spanish;
    }
}
