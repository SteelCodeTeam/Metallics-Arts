package net.rudahee.metallics_arts.data.enums.implementations.languages.book.feruchemic;

public enum Storage {
    IRON("++Storing:++\\\n\\\nx As you store, you seem to become lighter.",
            "++Almacenar:++\\\nx Mientras almacenas, notas como el mundo tira de ti con menos fuerza."),
    STEEL("++Storing:++\\\n\\\nx While you store you feel really sluggish.",
            "++Almacenar:++\\\nx Mientras almacenas, notas como tu cuerpo responde más lentamente de lo que debería. ¿Ese caracol te acaba de avanzar?"),
    TIN("++Storing:++\\\n\\\nx While you store, heighten your senses.",
            "++Almacenar:++\\\nx Mientras almacenas, disminuye tus sentidos."),
    PEWTER("++Storing:++\\\n\\\nx While you decant, you feel weaker, I could almost win you a chicken.",
            "++Almacenar:++\\\nx Mientras almacenas, te sientes más debil, casi te podria ganar una gallina."),
    ZINC("++Storing:++\\\n\\\nx While storing, deny all loot from entity.",
            "++Almacenar:++\\\nx Mientras almacenas, te cuesta muchísimo encontrar objetos de los seres a los que aniquilas."),
    BRASS("++Storing:++\\\n\\\nx While you store, it allows you to store your heat, making you less vulnerable to heat sources.\\\n\\\nx Be careful to use it in cold places.",
            "++Almacenar:++\\\nx Mientras almacenas, te permite guardar tu calor corporal, haciéndote más resistente a las fuentes de calor.\\\n\\\nx Poco recomendable almacenar en lugares fríos por... obvios motivos."),
    COPPER("++Storing:++\\\n\\\nx While you store, you lose your experience.",
            "++Almacenar:++\\\nx Mientras almacenas, guardas tu experiencia en la mente de metal."),
    BRONZE("++Storing:++\\\n\\\nx While storing, you will feel tired at all times, and so Phantoms will appear near you.",
            "++Almacenar:++\\\nx Mientras almacenas, vas a sentirte cansado en todo momento, por lo que aparecerán Fantasmas a tu altrededor (incluso de día)."),
    ALUMINUM("++Storing:++\\\n\\\nx By storing, you remove the Identity of a metalmind.",
            "++Almacenar:++\\\nx Al almacenar, quitas la Identidad de una mente de metal."),
    DURALUMIN("++Storing:++\\\n\\\nx While you store, you seem disconnected from the world, it almost seems like it is rejecting you.",
            "++Almacenar:++\\\nx Mientras almacenas, pareces desconectado del mundo, como si este te estuviera rechazando. Y sí, esa frase tiene sentido."),
    CHROMIUM("++Storing:++\\\n\\\nx While you store, it seems that luck is not on your side, it almost seems that you are going to get sick.",
            "++Almacenar:++\\\nx Mientras almacenas, parece que Armonía te ha dado la espalda, porque no paran de sucederte desgracias injustificables."),
    NICROSIL("++Storing:++\\\n\\\nx To store, it seems like your other metal minds are never going to fill up. It is necessary to use it together with other powers.",
            "++Almacenar:++\\\nx Para almacenar, requieres el sacrificio de los atributos que haya en otras mentes de metal que lleves encima. Es necesario usarla junto a otros poderes."),
    GOLD("++Storing:++\\\n\\\nx While you store, you seem to feel unwell, it almost seems that you are going to die.",
            "++Almacenar:++\\\nx Mientras almacenas, parece que te vuelves un contenedor de malestar y náuseas. Vigila tus huesos, quizá se rompen más rápido de lo que deberían."),
    ELECTRUM("++Storing:++\\\n\\\nx While you store, it seems that your life is drastically reduced.",
            "++Almacenar:++\\\nx Mientras almacenas, sientes que tu esperanza de vida se reduce drásticamente."),
    CADMIUM("++Storing:++\\\n\\\nx While storing, you will have difficulty breathing even out of the water.",
            "++Almacenar:++\\\nx Mientras almacenas, tendrás dificultades para respirar... incluso fuera del agua."),
    BENDALLOY("++Storing:++\\\n\\\nx While you store, you constantly increase your hunger",
            "++Almacenar:++\\\nx Mientras almacenas, aumentas tu hambre constantemente."),
    ATIUM("++Storing:++\\\n\\\nx As you decant, it allows you to shine for other players.",
            "++Almacenar:++\\\nx Mientras almacenas, brillas para el resto de jugadores (luminiscencia)."),
    MALATIUM("++Storing:++\\\n\\\nx While you store, you store the item's durability in your hand, so you can use it later.",
            "++Almacenar:++\\\nx Mientras almacenas, guardas la durabilidad del objeto que estés sujetando con la mano en ese instante, incluso pudiendo llegar a romperlo."),
    LERASIUM("++Storing:++\\\n\\\nx When you store, you will save your current Allomantic reserves.",
            "++Almacenar:++\\\nx Cuando almacenas, guardaras las reservas alománticas que hubieras ingerido."),
    ETTMETAL("++Storing:++\\\n\\\nx When you store, it seems that the energy from the explosions that hit you goes somewhere else.",
            "++Almacenar:++\\\nx Cuando almacenas, la energía de las explosiones no solo rompe tu cuerpo, sino que se guarda en la mente de metal.");
    private final String english;
    private final String spanish;

    Storage(String english, String spanish) {
        this.english = english;
        this.spanish = spanish;
    }

    public String getEnglish() {
        return english;
    }

}
