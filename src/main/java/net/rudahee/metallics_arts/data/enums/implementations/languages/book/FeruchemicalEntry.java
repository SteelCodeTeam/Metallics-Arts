package net.rudahee.metallics_arts.data.enums.implementations.languages.book;

public enum FeruchemicalEntry {
    IRON("Pull the metal fountain you are aiming at.\n To use this power, you need to left-click while looking at the metal fountain.",
            "[#](0000ff) + Duralumin: [#]()Greatly increases the pull. \n + Lerasium: Slightly increases pull. \n + Both: Maximizes pull."),

    STEEL("Push the metal fountain you are pointing at. \n To use this power, you must right-click while looking at the metal fountain.\n\n Bonus: \n - You can use Ctrl + Spacebar to push towards the block at your feet. \n - In case there are no valid blocks, consume metal nuggets.",
            "Duralumin: Greatly increases thrust. \n + Lerasium: Slightly increases thrust. \n + Both: Maximize thrust."),

    TIN( "Heighten your senses.", "\n + Duralumin: stuns you for a few moments. \n + Lerasium: Nothing. \n + Both: stuns you for a few moments."),

    PEWTER("\n Increase your physical capabilities.",
            "\n + Duralumin: Greatly increases upgrades. \n + Lerasium: Slightly increases buffs. \n + Both: Max out upgrades."),

    ZINC("Zinc", "Zinc"),
    BRASS("Brass", "Laton"),
    COPPER("Copper", "Cobre"),
    BRONZE("Bronze", "Bronce"),
    ALUMINUM("Aluminum", "Aluminio"),
    DURALUMIN("Duralumin", "Duralumin"),
    CHROMIUM( "Chromium", "Cromo"),
    NICROSIL("Nicrosil", "Nicrosil"),
    GOLD( "Gold", "Oro"),
    ELECTRUM( "Electrum", "Electrum"),
    CADMIUM( "Cadmium", "Cadmio"),
    BENDALLOY("Bendalloy", "Bendaleo"),
    ATIUM("atium", "Atium"),
    MALATIUM("malatium", "Malatium"),
    LERASIUM("lerasium", "Lerasium"),
    ETTMETAL("ettmetal", "Ettmetal");
    private final String storage;
    private final String tap;
    FeruchemicalEntry(String storage, String tap) {
        this.storage = storage;
        this.tap = tap;
    }

    public String getStorage() {
        return storage;
    }

    public String getTap() {
        return tap;
    }
}
