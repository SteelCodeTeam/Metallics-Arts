package net.rudahee.metallics_arts.data.enums.implementations.languages.book.multi_craft;

public enum MultiCaftDescriptions {
    VIALS("It looks like the perfect jar to store metal shards."),
    RINGS("Metal rings commonly worn by Terrisians. they look really good."),
    BANDS("Metal bracers that some people wear, are they just decoration?"),
    SPIKES("Really sharp metal nails. What will be its use?"),
    ICONS(""),
    ALLOYS("Alloys of metals that you can find around the world."),
    PATTERNS("");

    private final String english;
    MultiCaftDescriptions(String english) {
        this.english = english;
    }

    public String getEnglish() {
        return english;
    }
}
