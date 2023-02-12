package net.rudahee.metallics_arts.data.enums.implementations.languages.book.allomancy;

public enum Interactions {
    IRON("x Duralumin: Greatly increases the pull.\\\n\\\n x Lerasium: Slightly increases pull.\\\n\\\nx Both: Maximizes pull."
    ,""),
    STEEL("x Duralumin: Greatly increases thrust.\\\n \\\nx Lerasium: Slightly increases thrust. \\\n \\\n x Both: Maximize thrust.",
            ""),
    TIN("x Duralumin: stuns you for a few moments. \\\n \\\n x Lerasium: Nothing. \\\n \\\n x Both: stuns you for a few moments.",
            ""),
    PEWTER("x Duralumin: Greatly increases upgrades. \\\n \\\n x Lerasium: Slightly increases buffs. \\\n \\\n x Both: Max out upgrades.",
            ""),
    ZINC( "x Duralumin: Strengthens the entity. \\\n \\\n x Lerasium: Apply the effect in area. \\\n \\\n x Ambos: Strengthens entities in area.",
            ""),
    BRASS( "x Duralumin: Stops and debilitates the entity. \\\n \\\n x Lerasium: Apply the effect in area. \\\n \\\n x Both: Stops and weakens entities in the area.",
            ""),
    COPPER( "x Duralumin: stop the entities. \\\n \\\n x Lerasium: Apply the effect in area. \\\n \\\n x Ambos: Stop entities in area.",
            ""),
    BRONZE( "x Duralumin: Increases damage and speed of entities. \\\n \\\n x Lerasium: Apply the effect in area. \\\n \\\n x Bonus: Increases the damage and speed of the entities in the area.",
            ""),
    ALUMINUM( "x Duralumin: Nothing. \\\n \\\n x Lerasium: Nothing. \\\n \\\n x Both: Nothing.",
            "x Duralumín: nada. \\\n x Lerasium: nada. \\\n x Ambos: nada."),
    DURALUMIN( "x Duralumin: Nothing. \\\n \\\n x Lerasium: Nothing. \\\n \\\n x Ambos: Nothing.",
            ""),
    CHROMIUM( "x Duralumin: Eliminate reservations in a considerable area. \\\n \\\n x Lerasium: Remove reservations in a small area.  \\\n \\\n x Both: Delete reservations in a large area around you.",
            ""),
    NICROSIL( "x Duralumin: Apply the effect in medium area. \\\n \\\n x Lerasium: Apply the effect to a small area. \\\n \\\n x Both: Apply the effect to a large area.",
            ""),
    GOLD( "x Duralumin: Teleports you to the marked point. \\\n \\\n x Lerasium: Nothing. \\\n \\\n x Both: Teleports in area to the marked point.",
            ""),
    ELECTRUM( "x Duralumin: Teleports you to the marked point. \\\n \\\n x Lerasium: Nothing. \\\n \\\n x Both: Teleports in area to the marked point.",
            ""),
    CADMIUM( "x Duralumin: Doubles the effect and increases the area. \\\n \\\n x Lerasium: Increase the area. \\\n \\\n x Both: Duplicates the effect and increases the area greatly.",
            ""),
    BENDALLOY( "x Duralumin: Duplicate the effect and increase the area. \\\n \\\n x Lerasium: Increase the area. \\\n \\\n x Both: Duplicates the effect and increases the area greatly.",
            ""),
    ATIUM( "x Duralumin: Greatly increases evasion chance. \\\n \\\n x Lerasium: Slightly increases evasion chance. \\\n \\\n x Both: Maximizes Evasion Chance.",
            ""),
    MALATIUM( "x Duralumin: Teleports you to the marked point. \\\n \\\n x Lerasium: Nothing. \\\n \\\n x Both: Teleports in area to the marked point.",
            ""),
    LERASIUM( "x Duralumin: Nothing. \\\n \\\n x Lerasium: Nothing. \\\n \\\n x Both: Nothing.",
            ""),
    ETTMETAL( "x Duralumin: The explosion breaks blocks. \\\n \\\n x Lerasium: Nothing. \\\n \\\n x Both: The explosion breaks blocks.",
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
