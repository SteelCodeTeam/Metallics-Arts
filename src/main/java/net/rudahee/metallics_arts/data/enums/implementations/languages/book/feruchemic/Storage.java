package net.rudahee.metallics_arts.data.enums.implementations.languages.book.feruchemic;

public enum Storage {
    IRON("Storing:\\\n - As you store, you seem to become lighter.",""),
    STEEL("Storing:\\\n - While you store you feel really sluggish.",""),
    TIN("Storing:\\\n - While you store, heighten your senses.",""),
    PEWTER("Storing:\\\n - While you decant, you feel weaker, I could almost win you a chicken.",""),
    ZINC("Storing:\\\n - While storing, deny all loot from entity.",""),
    BRASS("Storing:\\\n - While you store, it allows you to store your heat, making you less vulnerable to heat sources.\\\n - Be careful to use it in cold places.",""),
    COPPER("Storing:\\\n - While you store, you lose your experience.",""),
    BRONZE("Storing:\\\n - While storing, you will feel tired at all times, and so Phantoms will appear near you.",""),
    ALUMINUM("Storing:\\\n - By storing, you remove the Identity of a metalmind.",""),
    DURALUMIN("Storing:\\\n - While you store, you seem disconnected from the world, it almost seems like it is rejecting you.",""),
    CHROMIUM( "Storing:\\\n - While you store, it seems that luck is not on your side, it almost seems that you are going to get sick.",""),
    NICROSIL("Storing:\\\n - To store, it seems like your other metal minds are never going to fill up. It is necessary to use it together with other powers.",""),
    GOLD("Storing:\\\n - While you store, you seem to feel unwell, it almost seems that you are going to die.",""),
    ELECTRUM("Storing:\\\n - While you store, it seems that your life is drastically reduced.",""),
    CADMIUM("Storing:\\\n - While storing, you will have difficulty breathing even out of the water.",""),
    BENDALLOY("Storing:\\\n - While you store, you constantly increase your hunger",""),
    ATIUM("Storing:\\\n - As you decant, it allows you to shine for other players.",""),
    MALATIUM("Storing:\\\n - While you store, you store the item's durability in your hand, so you can use it later.",""),
    LERASIUM("Storing:\\\n - When you store, you will save your current Allomantic reserves.",""),
    ETTMETAL("Storing:\\\n - When you store, it seems that the energy from the explosions that hit you goes somewhere else.","");
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
