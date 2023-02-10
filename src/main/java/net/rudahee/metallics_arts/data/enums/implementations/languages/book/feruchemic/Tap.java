package net.rudahee.metallics_arts.data.enums.implementations.languages.book.feruchemic;

public enum Tap {

    IRON("Tapping:\\ - As you decant, you seem to get much heavier."),
    STEEL("Tapping:\\ - As you decant, you feel really fast."),
    TIN("Tapping:\\ - As you decant, diminish your senses."),
    PEWTER("Tapping:\\ - While you store, you feel much stronger, you could kill the Wither boss with your fists."),
    ZINC("Tapping:\\ - While you decant, you have a chance to double the loot."),
    BRASS("Tapping:\\ - As you decant, you expel heat, causing you to set other entities on fire on hit.\\ - Comforting against that cold powder snow."),
    COPPER("Tapping:\\ - As you decant, you gain back all of your experience."),
    BRONZE("Tapping:\\ - As you tapping, you will feel more rested, causing the Phantoms to ignore you."),
    ALUMINUM("Tapping:\\ - By decanting, you seal your Identity in a metalmind."),
    DURALUMIN("Tapping:\\ - While you decant, it seems that you find the paths with your eyes closed, it seems as if it were your land."),
    CHROMIUM("Tapping:\\ - While you decant it, you have extraordinary luck, is it time to go exploring?"),
    NICROSIL("Tapping:\\ - When decanting, it seems like your other metal minds are never going to run out. It is necessary to use it together with other powers."),
    GOLD("Tapping:\\ - As you decant, it looks like you could take on any disease."),
    ELECTRUM("Tapping:\\ - As you decant, it seems that your life is increased drastically."),
    CADMIUM("Tapping:\\ - While you decant, you will feel like a fish in water, it almost seems that you can breathe at the bottom of the ocean."),
    BENDALLOY("Tapping:\\ - As you decant, you constantly reduce your hunger"),
    ATIUM("Tapping:\\ - While you store, it allows you to become invisible."),
    MALATIUM("Tapping:\\ - While you decant, the durability of the item in hand increases, thanks to your reserves."),
    LERASIUM("Tapping:\\ - When you decant, you gain the Allomantic reserves you previously stored."),
    ETTMETAL("Tapping:\\ - When decanting, it seems that you are going to release all your inner energy at once.");

    private final String english;

    Tap(String english) {
        this.english = english;
    }

    public String getEnglish() {
        return english;
    }
}
