package net.rudahee.metallics_arts.data.enums.implementations;

public enum GunsAccess {
    BULLETS("bullets_count"),
    BULLET_TYPE("bullets_type"),
    STATE("state"),
    RELOAD("reload"),
    READY("ready");

    private final String key;

    GunsAccess(String type) {
        this.key = type;
    }

    public String getKey() {
        return key;
    }
}
