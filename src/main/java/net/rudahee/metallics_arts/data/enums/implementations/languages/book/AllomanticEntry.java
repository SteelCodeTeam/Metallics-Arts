package net.rudahee.metallics_arts.data.enums.implementations.languages.book;


public enum AllomanticEntry {
    IRON("iron","Pull the metal fountain you are aiming at.\n To use this power, you need to left-click while looking at the metal fountain.",
            "[#](0000ff) + Duralumin: [#]()Greatly increases the pull. \n + Lerasium: Slightly increases pull. \n + Both: Maximizes pull."),
    STEEL("steel","Push the metal fountain you are pointing at. \n To use this power, you must right-click while looking at the metal fountain.\n\n Bonus: \n - You can use Ctrl + Spacebar to push towards the block at your feet. \n - In case there are no valid blocks, consume metal nuggets.",
            "Duralumin: Greatly increases thrust. \n + Lerasium: Slightly increases thrust. \n + Both: Maximize thrust."),
    TIN("tin", "Heighten your senses.", "\n + Duralumin: stuns you for a few moments. \n + Lerasium: Nothing. \n + Both: stuns you for a few moments."),
    PEWTER("pewter","\n Increase your physical capabilities.", "\n + Duralumin: Greatly increases upgrades. \n + Lerasium: Slightly increases buffs. \n + Both: Max out upgrades."),

    ZINC("zinc","Zinc", "Zinc"),
    BRASS("brass","Brass", "Laton"),
    COPPER("copper","Copper", "Cobre"),
    BRONZE("bronze","Bronze", "Bronce"),
    ALUMINUM("aluminum","Aluminum", "Aluminio"),
    DURALUMIN("duralumin","Duralumin", "Duralumin"),
    CHROMIUM("chromium", "Chromium", "Cromo"),
    NICROSIL("nicrosil","Nicrosil", "Nicrosil"),
    GOLD("gold", "Gold", "Oro"),
    ELECTRUM("electrum", "Electrum", "Electrum"),
    CADMIUM("cadmium", "Cadmium", "Cadmio"),
    BENDALLOY("bendalloy","Bendalloy", "Bendaleo");
    private final String id;
    private final String description;
    private final String interactions;
    AllomanticEntry(String id, String description, String interactions) {
        this.id = id;
        this.description = description;
        this.interactions = interactions;
    }
    public String getId() {
        return this.id;
    }

    public String getDescription() {
        return description;
    }

    public String getInteractions() {
        return interactions;
    }
}
