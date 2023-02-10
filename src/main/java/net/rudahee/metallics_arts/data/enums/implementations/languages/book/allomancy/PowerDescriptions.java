package net.rudahee.metallics_arts.data.enums.implementations.languages.book.allomancy;


public enum PowerDescriptions {
    IRON("Pull the metal fountain you are aiming at.\\ ++To use this power, you need to left-click while looking at the metal fountain.++"),
    STEEL("Push the metal fountain you are pointing at. \\ ++To use this power, you must right-click while looking at the metal fountain.++\\\\ Bonus: \\ - You can use Ctrl + Spacebar to push towards the block at your feet. \\ - In case there are no valid blocks, consume metal nuggets."),
    TIN("Heighten your senses."),
    PEWTER("Increase your physical capabilities."),
    ZINC("Makes entities aggressive. ++To use this power, you must left-click while looking at the entity.++\\\\ Bonus: \\ - You can free pets from their owner."),
    BRASS("Hostile entities will ignore you. \\ ++To use this power, you must right-click while looking at the entity.++\\\\ Bonus: \\ - You have a chance to tame pets."),
    COPPER("Makes nearby entities ignore you. \\\\ Bonus: \\ - Prevent other players from seeing that you're burning metal."),
    BRONZE("Makes nearby entities follow you around. \\\\ Bonus: \\ - Mark players burning metals around you."),
    ALUMINUM("Drain your Allomantic reserves. \\\\ Bonus: \\ - The greater the reserve you consume, the greater the probability of removing other adverse effects."),
    DURALUMIN("Greatly increases the power of the metals you are burning. \\\\ Bonus: \\ - It works for a short period of time and empties all reserves that are active at the time of activation."),
    CHROMIUM("Drains the allomantic reserves of the player you hit."),
    NICROSIL("Greatly increases the potency of the metals you are burning for the player you hit. \\\\ Bonus: \\ - Works for a short time and drains all Allomantic reserves the target player was burning."),
    GOLD("Mark the point where you last died."),
    ELECTRUM("Mark your respawn point with a line."),
    CADMIUM("You get slow drop, the game slows down around you."),
    BENDALLOY("Speed up the game around you."),
    ATIUM("Gives you chance to avoid incoming damage. \\\\ Bonus:\\ - If your attacker burns Atium he can cancel a part of the effects."),
    MALATIUM("Marks the last kill point of the player you hit."),
    LERASIUM("Increases the potency of the metals you are burning."),
    ETTMETAL("You explode. \\\\ Bonus: \\ - By default, it does not break blocks.");

    private final String english;

    PowerDescriptions(String english) {
        this.english = english;
    }

    public String getEnglish() {
        return english;
    }

}
