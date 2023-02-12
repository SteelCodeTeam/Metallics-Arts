package net.rudahee.metallics_arts.data.enums.implementations.languages.book.feruchemic;

public enum Storage {
    IRON("++Storing++:\\\n\\\n x As you store, you seem to become lighter.",""),
    STEEL("++Storing++:\\\n\\\n x While you store you feel really sluggish.",""),
    TIN("++Storing++:\\\n\\\n x While you store, heighten your senses.",""),
    PEWTER("++Storing++:\\\n\\\n x While you decant, you feel weaker, I could almost win you a chicken.",""),
    ZINC("++Storing++:\\\n\\\n x While storing, deny all loot from entity.",""),
    BRASS("++Storing++:\\\n\\\n x While you store, it allows you to store your heat, making you less vulnerable to heat sources.\\\n\\\n x Be careful to use it in cold places.",""),
    COPPER("++Storing++:\\\n\\\n x While you store, you lose your experience.",""),
    BRONZE("++Storing++:\\\n\\\n x While storing, you will feel tired at all times, and so Phantoms will appear near you.",""),
    ALUMINUM("++Storing++:\\\n\\\n x By storing, you remove the Identity of a metalmind.",""),
    DURALUMIN("++Storing++:\\\n\\\n x While you store, you seem disconnected from the world, it almost seems like it is rejecting you.",""),
    CHROMIUM( "++Storing++:\\\n\\\n x While you store, it seems that luck is not on your side, it almost seems that you are going to get sick.",""),
    NICROSIL("++Storing++:\\\n\\\n x To store, it seems like your other metal minds are never going to fill up. It is necessary to use it together with other powers.",""),
    GOLD("++Storing++:\\\n\\\n x While you store, you seem to feel unwell, it almost seems that you are going to die.",""),
    ELECTRUM("++Storing++:\\\n\\\n x While you store, it seems that your life is drastically reduced.",""),
    CADMIUM("++Storing++:\\\n\\\n x While storing, you will have difficulty breathing even out of the water.",""),
    BENDALLOY("++Storing++:\\\n\\\n x While you store, you constantly increase your hunger",""),
    ATIUM("++Storing++:\\\n\\\n x As you decant, it allows you to shine for other players.",""),
    MALATIUM("++Storing++:\\\n\\\n x While you store, you store the item's durability in your hand, so you can use it later.",""),
    LERASIUM("++Storing++:\\\n\\\n x When you store, you will save your current Allomantic reserves.",""),
    ETTMETAL("++Storing++:\\\n\\\n x When you store, it seems that the energy from the explosions that hit you goes somewhere else.","");
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
