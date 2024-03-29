package net.rudahee.metallics_arts.data.enums.implementations;

public enum BulletType {
    ALUMINUM("aluminum"),
    LEAD("lead");

    private final String type;

    BulletType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
