package net.rudahee.metallics_arts.data.enums.implementations.languages.book.allomancy;

public enum Interactions {
    IRON("+ Duralumin: Greatly increases the pull.\\\n\\\n + Lerasium: Slightly increases pull.\\\n\\\n+ Both: Maximizes pull."
    ,""),
    STEEL("+ Duralumin: Greatly increases thrust.\\\n \\\n+ Lerasium: Slightly increases thrust. \\\n \\\n + Both: Maximize thrust.",
            ""),
    TIN("+ Duralumin: stuns you for a few moments. \\\n \\\n + Lerasium: Nothing. \\\n \\\n + Both: stuns you for a few moments.",
            ""),
    PEWTER("+ Duralumin: Greatly increases upgrades. \\\n \\\n + Lerasium: Slightly increases buffs. \\\n \\\n + Both: Max out upgrades.",
            ""),
    ZINC( "+ Duralumin: Strengthens the entity. \\\n \\\n + Lerasium: Apply the effect in area. \\\n \\\n + Ambos: Strengthens entities in area.",
            ""),
    BRASS( "+ Duralumin: Stops and debilitates the entity. \\\n \\\n + Lerasium: Apply the effect in area. \\\n \\\n + Both: Stops and weakens entities in the area.",
            ""),
    COPPER( "+ Duralumin: stop the entities. \\\n \\\n + Lerasium: Apply the effect in area. \\\n \\\n + Ambos: Stop entities in area.",
            ""),
    BRONZE( "+ Duralumin: Increases damage and speed of entities. \\\n \\\n + Lerasium: Apply the effect in area. \\\n \\\n + Bonus: Increases the damage and speed of the entities in the area.",
            ""),
    ALUMINUM( "+ Duralumin: Nothing. \\\n \\\n + Lerasium: Nothing. \\\n \\\n + Both: Nothing.",
            "+ Duralumín: nada. $(br2) + Lerasium: nada. $(br2) + Ambos: nada."),
    DURALUMIN( "+ Duralumin: Nothing. \\\n \\\n + Lerasium: Nothing. \\\n \\\n + Ambos: Nothing.",
            ""),
    CHROMIUM( "+ Duralumin: Eliminate reservations in a considerable area. \\\n \\\n + Lerasium: Remove reservations in a small area.  \\\n \\\n + Both: Delete reservations in a large area around you.",
            ""),
    NICROSIL( "+ Duralumin: Apply the effect in medium area. \\\n \\\n + Lerasium: Apply the effect to a small area. \\\n \\\n + Both: Apply the effect to a large area.",
            ""),
    GOLD( "+ Duralumin: Teleports you to the marked point. \\\n \\\n + Lerasium: Nothing. \\\n \\\n + Both: Teleports in area to the marked point.",
            ""),
    ELECTRUM( "+ Duralumin: Teleports you to the marked point. \\\n \\\n + Lerasium: Nothing. \\\n \\\n + Both: Teleports in area to the marked point.",
            ""),
    CADMIUM( "+ Duralumin: Doubles the effect and increases the area. \\\n \\\n + Lerasium: Increase the area. \\\n \\\n + Both: Duplicates the effect and increases the area greatly.",
            ""),
    BENDALLOY( "+ Duralumin: Duplicate the effect and increase the area. \\\n \\\n + Lerasium: Increase the area. \\\n \\\n + Both: Duplicates the effect and increases the area greatly.",
            ""),
    ATIUM( "+ Duralumin: Greatly increases evasion chance. \\\n \\\n + Lerasium: Slightly increases evasion chance. \\\n \\\n + Both: Maximizes Evasion Chance.",
            ""),
    MALATIUM( "+ Duralumin: Teleports you to the marked point. \\\n \\\n + Lerasium: Nothing. \\\n \\\n + Both: Teleports in area to the marked point.",
            ""),
    LERASIUM( "+ Duralumin: Nothing. \\\n \\\n + Lerasium: Nothing. \\\n \\\n + Both: Nothing.",
            ""),
    ETTMETAL( "+ Duralumin: The explosion breaks blocks. \\\n \\\n + Lerasium: Nothing. \\\n \\\n + Both: The explosion breaks blocks.",
            "");
    private final String english;
    private final String spanish;


    Interactions(String english, String spanish) {
        this.english = english;
        this.spanish = spanish;
    }

    public String getEnglish() {
        return english;
    }
}
