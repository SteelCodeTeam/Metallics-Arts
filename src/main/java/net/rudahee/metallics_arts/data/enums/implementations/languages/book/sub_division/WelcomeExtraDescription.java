package net.rudahee.metallics_arts.data.enums.implementations.languages.book.sub_division;

/**
 * This enum contains the description for the book of each metallic art in different languages.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public enum WelcomeExtraDescription {
    ALLOMANCY("In case your power is allomantic, you will need to recharge your power reserves in order to start burning it.\\\n This is done by placing in a $(l:vials_entry/vials)Vials$() the Nuggets of the metal you wish to charge, or of various metals, if you have more than one power to use \\\nonce the vial is loaded, you must ingest it and you will see how your reserves are loaded.$(br) Finally, you must press the \"M\" key and select your power to activate it",
            ""),
    FERUCHEMY("presento feruqimia",""),
    WELCOME("Soy por desgracia","");

    private final String english;
    private final String spanish;

    /**
     * Creates a new WelcomeExtraDescription object with the provided English, Spanish, and Polish translations.
     *
     * @param english the English translation for the interaction.
     * @param spanish the Spanish translation for the interaction.
     */
    WelcomeExtraDescription(String english, String spanish) {
        this.english = english;
        this.spanish = spanish;
    }
    /**
     * Returns the English translation for the interaction.
     *
     * @return the English translation for the interaction
     */
    public String getEnglish() {
        return english;
    }

    /**
     * Returns the Spanish translation for the interaction.
     *
     * @return the Spanish translation for the interaction
     */
    public String getSpanish() {
        return spanish;
    }
}
